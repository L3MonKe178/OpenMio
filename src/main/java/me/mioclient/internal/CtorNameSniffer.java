package me.mioclient.internal;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Reads a class file directly and returns the first {@code String} constant
 * referenced by an {@code LDC} / {@code LDC_W} instruction inside any
 * {@code <init>} method. Mio's modules all start their constructor with
 * {@code super("Name", "description", Category.X)} — that first string is the
 * module's real display name. When the runtime constructor cannot complete
 * (Settings.initialize stops mid-way → NPE → fallback shell instance), this
 * sniffer recovers the name from the .class file directly.
 *
 * No ASM dependency on purpose: this code only needs to read the constant
 * pool, find the {@code <init>} method, and walk a tiny subset of opcodes.
 */
final class CtorNameSniffer {
   private static final int CP_UTF8     = 1;
   private static final int CP_INTEGER  = 3;
   private static final int CP_FLOAT    = 4;
   private static final int CP_LONG     = 5;
   private static final int CP_DOUBLE   = 6;
   private static final int CP_CLASS    = 7;
   private static final int CP_STRING   = 8;
   private static final int CP_FIELDREF = 9;
   private static final int CP_METHODREF= 10;
   private static final int CP_IMETHODREF= 11;
   private static final int CP_NAMETYPE = 12;
   private static final int CP_METHODHANDLE = 15;
   private static final int CP_METHODTYPE   = 16;
   private static final int CP_DYNAMIC      = 17;
   private static final int CP_INVOKEDYNAMIC= 18;
   private static final int CP_MODULE       = 19;
   private static final int CP_PACKAGE      = 20;

   private CtorNameSniffer() {}

   /**
    * @return real super-call name, or null if not found / class unreadable.
    */
   static String sniff(Class<?> klass) {
      String resource = klass.getName().replace('.', '/') + ".class";
      ClassLoader cl = klass.getClassLoader();
      if (cl == null) cl = ClassLoader.getSystemClassLoader();
      try (InputStream is = cl.getResourceAsStream(resource)) {
         if (is == null) return null;
         try (DataInputStream in = new DataInputStream(is)) {
            return parse(in);
         }
      } catch (IOException e) {
         return null;
      }
   }

   private static String parse(DataInputStream in) throws IOException {
      if (in.readInt() != 0xCAFEBABE) return null;
      in.readUnsignedShort(); // minor version
      in.readUnsignedShort(); // major version

      int cpCount = in.readUnsignedShort();
      Object[] cp = new Object[cpCount];
      // tag 8 (String) → Integer pointing to UTF8 cp index
      for (int i = 1; i < cpCount; i++) {
         int tag = in.readUnsignedByte();
         switch (tag) {
            case CP_UTF8:    cp[i] = in.readUTF(); break;
            case CP_INTEGER: case CP_FLOAT: in.readInt(); break;
            case CP_LONG:    case CP_DOUBLE: in.readLong(); i++; break; // 8-byte entries take two slots
            case CP_CLASS:   in.readUnsignedShort(); break;
            case CP_STRING:  cp[i] = Integer.valueOf(in.readUnsignedShort()); break;
            case CP_FIELDREF: case CP_METHODREF: case CP_IMETHODREF: in.readUnsignedShort(); in.readUnsignedShort(); break;
            case CP_NAMETYPE: in.readUnsignedShort(); in.readUnsignedShort(); break;
            case CP_METHODHANDLE: in.readUnsignedByte(); in.readUnsignedShort(); break;
            case CP_METHODTYPE:   in.readUnsignedShort(); break;
            case CP_DYNAMIC: case CP_INVOKEDYNAMIC: in.readUnsignedShort(); in.readUnsignedShort(); break;
            case CP_MODULE: case CP_PACKAGE: in.readUnsignedShort(); break;
            default: return null; // unknown tag → bail
         }
      }

      in.readUnsignedShort(); // access flags
      in.readUnsignedShort(); // this_class
      in.readUnsignedShort(); // super_class
      int ifaces = in.readUnsignedShort();
      for (int i = 0; i < ifaces; i++) in.readUnsignedShort();

      int fields = in.readUnsignedShort();
      for (int i = 0; i < fields; i++) skipMember(in);

      int methods = in.readUnsignedShort();
      for (int i = 0; i < methods; i++) {
         in.readUnsignedShort();                                 // access
         int nameIdx = in.readUnsignedShort();
         in.readUnsignedShort();                                 // descriptor
         int attrCount = in.readUnsignedShort();
         String mname = asUtf8(cp, nameIdx);
         boolean isInit = "<init>".equals(mname);
         String found = null;
         for (int a = 0; a < attrCount; a++) {
            int attrNameIdx = in.readUnsignedShort();
            int attrLen = in.readInt();
            String attrName = asUtf8(cp, attrNameIdx);
            if (isInit && "Code".equals(attrName) && found == null) {
               found = parseCode(in, attrLen, cp);
            } else {
               skip(in, attrLen);
            }
         }
         if (isInit && found != null) return found;
      }
      return null;
   }

   private static String parseCode(DataInputStream in, int attrLen, Object[] cp) throws IOException {
      int start = attrLen;
      in.readUnsignedShort(); // max_stack
      in.readUnsignedShort(); // max_locals
      int codeLen = in.readInt();
      byte[] code = new byte[codeLen];
      in.readFully(code);
      // skip remaining: exception table + attributes
      int remaining = start - 2 - 2 - 4 - codeLen;
      skip(in, remaining);

      // Walk code looking for LDC (0x12, 1-byte index) or LDC_W (0x13, 2-byte index)
      // referring to a String constant. Return the first match.
      int p = 0;
      while (p < codeLen) {
         int op = code[p] & 0xFF;
         int idx;
         if (op == 0x12) {                          // LDC
            idx = code[p + 1] & 0xFF;
            String s = stringAt(cp, idx);
            if (s != null) return s;
            p += 2;
         } else if (op == 0x13 || op == 0x14) {     // LDC_W / LDC2_W
            idx = ((code[p + 1] & 0xFF) << 8) | (code[p + 2] & 0xFF);
            if (op == 0x13) {
               String s = stringAt(cp, idx);
               if (s != null) return s;
            }
            p += 3;
         } else {
            int adv = opcodeLength(op, code, p);
            if (adv <= 0) return null;              // unknown opcode → stop
            p += adv;
         }
      }
      return null;
   }

   private static String stringAt(Object[] cp, int idx) {
      if (idx <= 0 || idx >= cp.length) return null;
      Object o = cp[idx];
      if (o instanceof Integer i) {            // CONSTANT_String → utf8 idx
         int uidx = i.intValue();
         if (uidx > 0 && uidx < cp.length && cp[uidx] instanceof String s) return s;
      }
      return null;
   }

   private static String asUtf8(Object[] cp, int idx) {
      return idx > 0 && idx < cp.length && cp[idx] instanceof String s ? s : null;
   }

   private static void skipMember(DataInputStream in) throws IOException {
      in.readUnsignedShort(); in.readUnsignedShort(); in.readUnsignedShort();
      int attrCount = in.readUnsignedShort();
      for (int i = 0; i < attrCount; i++) {
         in.readUnsignedShort();
         int len = in.readInt();
         skip(in, len);
      }
   }

   private static void skip(DataInputStream in, int n) throws IOException {
      while (n > 0) {
         long s = in.skip(n);
         if (s <= 0) { in.readByte(); n--; }
         else n -= (int) s;
      }
   }

   /**
    * JVMS opcode lengths for everything except LDC family (handled by caller).
    * Returns 0 for opcodes we don't support — that ends the walk safely.
    */
   private static int opcodeLength(int op, byte[] code, int p) {
      // single-byte (no operands)
      if (op == 0x00 || (op >= 0x01 && op <= 0x0F) || (op >= 0x1A && op <= 0x35)
            || (op >= 0x3B && op <= 0x83) || (op >= 0x85 && op <= 0x98)
            || op == 0xAC || op == 0xAD || op == 0xAE || op == 0xAF || op == 0xB0 || op == 0xB1
            || op == 0xBE || op == 0xBF || op == 0xC2 || op == 0xC3) return 1;
      // BIPUSH (0x10), NEWARRAY (0xBC), LDC (0x12 handled in caller)
      if (op == 0x10 || op == 0xBC) return 2;
      // single-byte index (xLOAD/xSTORE) — 0x15..0x19 and 0x36..0x3A and 0xA9 (RET)
      if ((op >= 0x15 && op <= 0x19) || (op >= 0x36 && op <= 0x3A) || op == 0xA9) return 2;
      // SIPUSH, branches with 2-byte offset, GETSTATIC/PUTSTATIC/GETFIELD/PUTFIELD,
      // INVOKEVIRTUAL/SPECIAL/STATIC, NEW, ANEWARRAY, CHECKCAST, INSTANCEOF, LDC_W, LDC2_W, IINC variant1, GOTO, JSR, IFs
      if (op == 0x11 || (op >= 0x99 && op <= 0xA8) || op == 0xC6 || op == 0xC7
            || op == 0xB2 || op == 0xB3 || op == 0xB4 || op == 0xB5
            || op == 0xB6 || op == 0xB7 || op == 0xB8 || op == 0xBB
            || op == 0xBD || op == 0xC0 || op == 0xC1) return 3;
      if (op == 0x84) return 3;     // IINC
      if (op == 0xB9 || op == 0xBA) return 5; // INVOKEINTERFACE / INVOKEDYNAMIC
      if (op == 0xC5) return 4;     // MULTIANEWARRAY
      if (op == 0xC8 || op == 0xC9) return 5; // GOTO_W / JSR_W
      if (op == 0xC4) {             // WIDE
         int next = code[p + 1] & 0xFF;
         return next == 0x84 ? 6 : 4;
      }
      return 0;                     // unhandled (TABLESWITCH/LOOKUPSWITCH/RET/etc.) → stop walk
   }
}
