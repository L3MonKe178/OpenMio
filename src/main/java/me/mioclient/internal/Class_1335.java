package me.mioclient.internal;

import io.netty.channel.ChannelHandlerContext;
import java.io.DataInputStream;
import me.mioclient.api.Class_0098;

public class Class_1335 implements Class_0098 {
   public String field_4314;
   public String username;

   public Class_1335(String var1, String var2) {
      super();
      this.field_4314 = var1;
      this.username = var2;
   }

   public Class_1335() {
      super();
   }

   @Override
   public void method_2(ChannelHandlerContext var1) {
   }

   public Class_1335 method_9(byte[] var1) {
      DataInputStream var2 = this.method_5(var1);
      Class_1335 var3 = new Class_1335();
      try {
var3.field_4314 = var2.readUTF();
      var3.username = var2.readUTF();
      } catch (java.io.IOException e) { throw new RuntimeException(e); }
      return var3;
   }

   @Override
   public byte[] method_26() {
      Class_1216 var1 = this.method_123();
      try {
var1.writeUTF(this.field_4314);
      var1.writeUTF(this.username);
      } catch (java.io.IOException e) { throw new RuntimeException(e); }
      return var1.method_1009();
   }

   @Override
   public short method_27() {
      return 10;
   }
}
