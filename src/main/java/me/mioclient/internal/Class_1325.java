package me.mioclient.internal;

import io.netty.channel.ChannelHandlerContext;
import java.awt.Color;
import java.io.DataInputStream;
import me.mioclient.Hub;
import me.mioclient.api.Class_0098;
import me.mioclient.module.client.IRCModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

public class Class_1325 implements Class_0098 {
   public static IRCModule field_906 = Hub.field_2595.method_78(IRCModule.class);
   public String field_4278;
   public String field_4279;
   public String field_483;

   public Class_1325() {
      super();
   }

   @Override
   public void method_2(ChannelHandlerContext var1) {
      if (field_906.field_566.getValue()) {
         if (MinecraftClient.getInstance().player != null
            && MinecraftClient.getInstance().world != null
            && !Hub.field_2610.method_331().contains(this.field_4279.toLowerCase())) {
            field_906.field_576++;
            MutableText var2 = Text.empty();
            var2.append("<");
            Color var3 = field_906.field_565.getValue()
               ? Class_1081.method_2(Class_1081.method_959(), Class_1081.method_959().darker(), Double.longBitsToDouble(4658815484840378368L), 0.0)
               : Class_1081.method_959();
            var2.append(Text.literal(this.field_4278).styled(var1x -> ChatUtil.method_2(var1x, var3::hashCode)));
            var2.append("> ");
            var2.append(this.field_483);
            String var10000 = this.field_483;
            String var10001 = this.field_4278;
            int var10002 = field_906.field_576;
            MessageSignatureData var4 = ChatUtil.method_38(
               new TextBuilder().method_2(var10002).method_2((Object)var10001).method_2((Object)var10000).method_9("\u0001\u0001\u0001").hashCode()
            );
            ChatUtil.method_2(var2, var4);
            if (field_906.field_568.getValue()
               && !this.field_4278.contains(Hub.field_2609.method_801())
               && !this.field_4278.contains(MinecraftClient.getInstance().getSession().getUsername())) {
               Hub.field_2606.method_2(field_906.field_569.getValue()).method_230(field_906.field_570.getValue());
            }
         }
      }
   }

   @Override
   public Class_1325 method_9(byte[] var1) {
      DataInputStream var2 = this.method_5(var1);
      Class_1325 var3 = new Class_1325();
      try {
var3.field_4278 = var2.readUTF();
      var3.field_4279 = var2.readUTF();
      var3.field_483 = var2.readUTF();
      } catch (java.io.IOException e) { throw new RuntimeException(e); }
      return var3;
   }

   @Override
   public byte[] method_26() {
      Class_1216 var1 = this.method_123();
      try {
var1.writeUTF(this.field_4278);
      var1.writeUTF(this.field_4279);
      var1.writeUTF(this.field_483);
      } catch (java.io.IOException e) { throw new RuntimeException(e); }
      return var1.method_1009();
   }

   @Override
   public short method_27() {
      return 5;
   }
}
