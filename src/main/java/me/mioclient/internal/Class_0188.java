package me.mioclient.internal;

import io.netty.channel.ChannelHandlerContext;
import java.io.DataInputStream;
import me.mioclient.api.Class_0098;

public class Class_0188 implements Class_0098 {
   public String field_531;
   public int field_532;
   public String field_533;
   public byte[] field_534;

   public Class_0188() {
      super();
   }

   @Override
   public void method_2(ChannelHandlerContext var1) {
   }

   @Override
   public Class_0188 method_9(byte[] var1) {
      DataInputStream var2 = this.method_5(var1);
      Class_0188 var3 = new Class_0188();
      try {
var3.field_532 = var2.readInt();
      var3.field_533 = var2.readUTF();
      var3.field_531 = var2.readUTF();
      int var4 = var2.readInt();
      if (var4 > 0 && var4 <= 1000) {
         var3.field_534 = new byte[var4];
         var2.readFully(var3.field_534);
      } else {
         var3.field_534 = null;
      }
      } catch (java.io.IOException e) { throw new RuntimeException(e); }

      return var3;
   }

   @Override
   public byte[] method_26() {
      Class_1216 var1 = this.method_123();
      try {
var1.writeInt(this.field_532);
      var1.writeUTF(this.field_533);
      var1.writeUTF(this.field_531);
      if (this.field_534 == null) {
         var1.writeInt(0);
      } else {
         var1.writeInt(this.field_534.length);
         var1.write(this.field_534);
      }
      } catch (java.io.IOException e) { throw new RuntimeException(e); }

      return var1.method_1009();
   }

   @Override
   public short method_27() {
      return 1;
   }

   public void method_5(int var1) {
      this.field_532 = var1;
   }

   public void method_4(byte[] var1) {
      this.field_534 = var1;
   }

   public void method_37(String var1) {
      if (var1 == null) {
         this.field_533 = "none";
      } else {
         this.field_533 = var1;
      }
   }

   public void method_33(String var1) {
      this.field_531 = var1;
   }
}
