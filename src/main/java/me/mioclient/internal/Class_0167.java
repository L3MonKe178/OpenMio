package me.mioclient.internal;

import io.netty.channel.ChannelHandlerContext;
import java.io.DataInputStream;
import me.mioclient.api.Class_0098;

public class Class_0167 implements Class_0098 {
   public String field_483;

   public Class_0167() {
      super();
   }

   public Class_0167(String var1) {
      super();
      this.field_483 = var1;
   }

   @Override
   public void method_2(ChannelHandlerContext var1) {
   }

   @Override
   public Class_0167 method_9(byte[] var1) {
      DataInputStream var2 = this.method_5(var1);
      Class_0167 var3 = new Class_0167();
      try { var3.field_483 = var2.readUTF(); } catch (java.io.IOException e) { throw new RuntimeException(e); }
      return var3;
   }

   @Override
   public byte[] method_26() {
      Class_1216 var1 = this.method_123();
      try { var1.writeUTF(this.field_483); } catch (java.io.IOException e) { throw new RuntimeException(e); }
      return var1.method_1009();
   }

   @Override
   public short method_27() {
      return 4;
   }
}
