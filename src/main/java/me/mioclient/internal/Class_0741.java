package me.mioclient.internal;

import io.netty.channel.ChannelHandlerContext;
import java.io.DataInputStream;
import me.mioclient.api.Class_0098;
import net.minecraft.text.Text;

public class Class_0741 implements Class_0098 {
   public String field_483;

   public Class_0741() {
      super();
   }

   @Override
   public void method_2(ChannelHandlerContext var1) {
      ChatUtil.method_2(Text.literal(this.field_483), ChatUtil.method_38(this.field_483.hashCode()));
   }

   @Override
   public Class_0741 method_9(byte[] var1) {
      DataInputStream var2 = this.method_5(var1);
      Class_0741 var3 = new Class_0741();
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
      return 13;
   }
}
