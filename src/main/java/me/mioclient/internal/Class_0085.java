package me.mioclient.internal;

import io.netty.channel.ChannelHandlerContext;
import me.mioclient.api.Class_0098;

public class Class_0085 implements Class_0098 {
   public String username;
   public String[] field_285;

   public Class_0085() {
      super();
   }

   public Class_0085(String var1, String[] var2) {
      super();
      this.username = var1;
      this.field_285 = var2;
   }

   @Override
   public void method_2(ChannelHandlerContext var1) {
   }

   public Class_0085 method_9(byte[] var1) {
      return null;
   }

   @Override
   public byte[] method_26() {
      Class_1216 var1 = this.method_123();
      try {
         var1.writeUTF(this.username);
         var1.writeInt(this.field_285.length);

         for (String var5 : this.field_285) {
            var1.writeUTF(var5);
         }
      } catch (java.io.IOException e) { throw new RuntimeException(e); }

      return var1.method_1009();
   }

   @Override
   public short method_27() {
      return 7;
   }
}
