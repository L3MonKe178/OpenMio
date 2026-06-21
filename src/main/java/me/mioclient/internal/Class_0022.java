package me.mioclient.internal;

import io.netty.channel.ChannelHandlerContext;
import java.io.DataInputStream;
import me.mioclient.Hub;
import me.mioclient.api.Class_0098;

public class Class_0022 implements Class_0098 {
   public String[] field_32;
   public boolean field_33;

   public Class_0022() {
      super();
   }

   @Override
   public void method_2(ChannelHandlerContext var1) {
      Hub.field_2610.method_5(this.field_32);
      Class_0170.method_2(var1.channel());
   }

   @Override
   public Class_0022 method_9(byte[] var1) {
      DataInputStream var2 = this.method_5(var1);
      Class_0022 var3 = new Class_0022();
      try {
         var3.field_33 = var2.readBoolean();
         int var4 = var2.readInt();
         var3.field_32 = new String[var4];
         for (int var5 = 0; var5 < var4; var5++) {
            var3.field_32[var5] = var2.readUTF();
         }
      } catch (java.io.IOException e) { throw new RuntimeException(e); }
      return var3;
   }

   @Override
   public byte[] method_26() {
      Class_1216 var1 = this.method_123();
      try {
         var1.writeBoolean(this.field_33);
         var1.writeInt(this.field_32.length);
         for (String var5 : this.field_32) {
            var1.writeUTF(var5);
         }
      } catch (java.io.IOException e) { throw new RuntimeException(e); }
      return var1.method_1009();
   }

   @Override
   public short method_27() {
      return 12;
   }
}
