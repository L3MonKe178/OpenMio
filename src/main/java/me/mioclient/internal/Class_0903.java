package me.mioclient.internal;

import io.netty.channel.ChannelHandlerContext;
import java.io.DataInputStream;
import me.mioclient.api.Class_0098;
import net.minecraft.text.Text;

public class Class_0903 implements Class_0098 {
   public String[] field_2833;

   public Class_0903() {
      super();
   }

   public Class_0903(String[] var1) {
      super();
      this.field_2833 = var1;
   }

   @Override
   public void method_2(ChannelHandlerContext var1) {
      String var2 = String.join(", ", this.field_2833);
      String var3 = "%d online players: %s".formatted(this.field_2833.length, var2);
      Class_1245.method_2(Text.literal(var3), Class_1245.method_38(var3.hashCode()));
   }

   @Override
   public Class_0903 method_9(byte[] var1) {
      DataInputStream var2 = this.method_5(var1);
      Class_0903 var4 = new Class_0903();
      try {
         int var3 = var2.readInt();
         var4.field_2833 = new String[var3];

         for (int var5 = 0; var5 < var3; var5++) {
            var4.field_2833[var5] = var2.readUTF();
         }
      } catch (java.io.IOException e) { throw new RuntimeException(e); }

      return var4;
   }

   @Override
   public byte[] method_26() {
      Class_1216 var1 = this.method_123();
      try {
var1.writeInt(this.field_2833.length);

      for (String var5 : this.field_2833) {
         var1.writeUTF(var5);
      }
      } catch (java.io.IOException e) { throw new RuntimeException(e); }

      return var1.method_1009();
   }

   @Override
   public short method_27() {
      return 3;
   }
}
