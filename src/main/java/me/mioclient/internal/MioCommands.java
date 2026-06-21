package me.mioclient.internal;

import java.lang.reflect.Constructor;

/**
 * Replaces {@code nick.Commands.initialize}, which scans for legacy
 * {@code me.mioclient.deobf.Class_XXXX} entries that don't exist after rename
 * and silently registers nothing. Every command extends {@link Class_0618};
 * the registered list is the same set nick.Commands knew about, mapped to the
 * classes that survived the rename.
 */
public final class MioCommands {
   private MioCommands() { super(); }

   public static void init(Class_1032 manager) {
      String[] classes = new String[] {
         "me.mioclient.internal.Class_0712",
         "me.mioclient.internal.Class_0355",
         "me.mioclient.internal.Class_0591",
         "me.mioclient.internal.Class_0103",
         "me.mioclient.internal.Class_0917",
         "me.mioclient.internal.Class_0732",
         "me.mioclient.internal.Class_1207",
         "me.mioclient.internal.Class_1105",
         "me.mioclient.internal.Class_0495",
         "me.mioclient.internal.Class_0657",
         "me.mioclient.internal.Class_0858",
         "me.mioclient.internal.Class_0848",
         "me.mioclient.internal.Class_0335",
         "me.mioclient.internal.Class_0460",
         "me.mioclient.internal.Class_0152",
         "me.mioclient.internal.Class_0294",
         "me.mioclient.internal.Class_0480",
         "me.mioclient.internal.Class_0264",
         "me.mioclient.internal.Class_0662",
         "me.mioclient.internal.Class_0138",
         "me.mioclient.internal.Class_0076",
         "me.mioclient.internal.Class_0751",
         "me.mioclient.internal.Class_0343",
         "me.mioclient.internal.Class_0479",
         "me.mioclient.internal.Class_1028",
         "me.mioclient.internal.Class_0348",
         "me.mioclient.internal.Class_0567",
         "me.mioclient.internal.Class_0216",
         "me.mioclient.internal.Class_0093",
         "me.mioclient.internal.Class_0630",
         "me.mioclient.internal.Class_0553",
         "me.mioclient.internal.Class_0249",
         "me.mioclient.internal.Class_0213",
         "me.mioclient.internal.Class_0299",
         "me.mioclient.internal.Class_0985",
         "me.mioclient.internal.Class_0283",
      };
      int ok = 0, failed = 0;
      for (String name : classes) {
         try {
            Class<?> klass = Class.forName(name);
            Constructor<?>[] ctors = klass.getDeclaredConstructors();
            if (ctors.length == 0) { failed++; continue; }
            ctors[0].setAccessible(true);
            Class_0618 cmd = (Class_0618) ctors[0].newInstance();
            manager.method_2(cmd);
            ok++;
         } catch (Throwable t) {
            failed++;
            System.err.println("[Mio] Failed to register command: " + name + " - " + t);
         }
      }
      // Class_0881 FRIEND/ENEMY are registered separately by Class_1032.method_931().
      System.err.println("[Mio] Commands: " + ok + " registered, " + failed + " failed");
   }
}
