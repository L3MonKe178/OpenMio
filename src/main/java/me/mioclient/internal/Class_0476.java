package me.mioclient.internal;

import io.netty.channel.ChannelHandlerContext;
import java.io.DataInputStream;
import me.mioclient.Hub;
import me.mioclient.api.Class_0098;
import me.mioclient.api.Class_1309;
import me.mioclient.event.Event_23;

public class Class_0476 implements Class_0098 {
   public String username;
   public String field_1518;
   public int field_1321;
   public int field_1322;
   public int field_1519;

   public Class_0476() {
      super();
   }

   public Class_0476(String var1, int var2, int var3, int var4) {
      super();
      this.field_1518 = var1;
      this.field_1321 = var2;
      this.field_1322 = var3;
      this.field_1519 = var4;
   }

   public Class_0476(String var1, String var2, int var3, int var4, int var5) {
      this(var2, var3, var4, var5);
      this.username = var1;
   }

   @Override
   public void method_2(ChannelHandlerContext var1) {
      try {
         Class_1257 var2 = new Class_1257(this.username, this.field_1518, this.field_1321, this.field_1322, this.field_1519);
         var2.reset();
         Event_23 var3 = new Event_23(var2);
         Class_1309.field_4220.method_36(var3);
         if (!var3.method_464()) {
            synchronized (Hub.field_2610.method_328()) {
               Hub.field_2610.method_328().add(var2);
            }
         }
      } catch (Exception var7) {
      }
   }

   public Class_0476 method_9(byte[] var1) {
      DataInputStream var2 = this.method_5(var1);
      try {
         return new Class_0476(var2.readUTF(), var2.readUTF(), var2.readInt(), var2.readInt(), var2.readInt());
      } catch (java.io.IOException e) { throw new RuntimeException(e); }
   }

   @Override
   public byte[] method_26() {
      Class_1216 var1 = this.method_123();
      try {
var1.writeUTF(this.username == null ? "" : this.username);
      var1.writeUTF(this.field_1518);
      var1.writeInt(this.field_1321);
      var1.writeInt(this.field_1322);
      var1.writeInt(this.field_1519);
      } catch (java.io.IOException e) { throw new RuntimeException(e); }
      return var1.method_1009();
   }

   @Override
   public short method_27() {
      return 6;
   }
}
