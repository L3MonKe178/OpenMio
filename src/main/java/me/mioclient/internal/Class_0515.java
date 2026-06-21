package me.mioclient.internal;

import io.netty.channel.ChannelHandlerContext;
import java.io.DataInputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import me.mioclient.Hub;
import me.mioclient.api.Class_0098;

public class Class_0515 implements Class_0098 {
   public HashMap<String, String> field_1631;

   public Class_0515() {
      super();
   }

   public Class_0515(HashMap<String, String> var1) {
      super();
      this.field_1631 = var1;
   }

   @Override
   public void method_2(ChannelHandlerContext var1) {
      Hub.field_2610.method_329().clear();

      for (Entry var3 : this.field_1631.entrySet()) {
         Hub.field_2610.method_329().put((String)var3.getKey(), (String)var3.getValue());
      }
   }

   @Override
   public Class_0515 method_9(byte[] var1) {
      DataInputStream var2 = this.method_5(var1);
      HashMap var3 = new HashMap();
      try {
         int var4 = var2.readInt();

         for (int var5 = 0; var5 < var4; var5++) {
            String var6 = var2.readUTF();
            String var7 = var2.readUTF();
            var3.put(var6, var7);
         }
      } catch (java.io.IOException e) { throw new RuntimeException(e); }

      return new Class_0515(var3);
   }

   @Override
   public byte[] method_26() {
      Class_1216 var1 = this.method_123();
      try {
var1.writeInt(this.field_1631.size());

      for (String var3 : this.field_1631.keySet()) {
         var1.writeUTF(var3);
         var1.writeUTF(this.field_1631.get(var3));
      }
      } catch (java.io.IOException e) { throw new RuntimeException(e); }

      return var1.method_1009();
   }

   @Override
   public short method_27() {
      return 8;
   }
}
