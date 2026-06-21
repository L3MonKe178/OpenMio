package me.mioclient.internal;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import me.mioclient.Hub;
import me.mioclient.module.client.IRCModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class Class_0709 extends SimpleChannelInboundHandler<ByteBuf> {
   public static IRCModule field_906 = Hub.field_2595.method_78(IRCModule.class);

   public Class_0709() {
      super();
   }

   public void channelActive(ChannelHandlerContext var1) {
   }

   public void channelInactive(ChannelHandlerContext var1) {
      Class_0968.reset();
      Class_0170.method_208();
   }

   public void channelRead0(ChannelHandlerContext var1, ByteBuf var2) throws java.io.IOException {
      if (!Class_0968.method_869()) {
         byte[] var3 = new byte[var2.readableBytes()];
         var2.readBytes(var3);
         ByteArrayInputStream var4 = new ByteArrayInputStream(var3);
         DataInputStream var5 = new DataInputStream(var4);
         int var6 = var5.readInt();
         byte[] var7 = new byte[var6];
         var5.readFully(var7);
         byte[] var8 = new byte[16];
         var5.readFully(var8);
         Class_0968.method_2(var7, var8);
         byte[] var9 = method_289();
         if (var9 == null) {
            new Class_0921().method_2(null);
         } else {
            Class_0188 var10 = new Class_0188();
            var10.method_5(Hub.field_2609.method_802());
            var10.method_33(MinecraftClient.getInstance().getSession().getUsername());
            var10.method_4(var9);
            var10.method_37(field_906.method_237());
            var1.channel().writeAndFlush(var10);
            Hub.field_2619
               .method_2(
                  () -> {
                     try {
                        ChatUtil.method_2(
                           Text.literal(
                              new TextBuilder()
                                 .method_2(CommandManager.method_927())
                                 .method_2(String.valueOf(Formatting.GREEN))
                                 .method_9("\u0001Connected to the chat server. Type \u0001irc help to view all commands.")
                           ),
                           ChatUtil.method_38(-836156)
                        );
                     } catch (Exception var1x) {
                     }
                  },
                  0
               );
         }
      } else {
         var1.fireChannelRead(var2.retain());
      }
   }

   public void exceptionCaught(ChannelHandlerContext var1, Throwable var2) {
      Hub.field_2610.method_114(true);
   }

   public static byte[] method_289() {
      FileInputStream var0 = null;

      byte[] var2;
      try {
         String var10002 = System.getProperty("user.home");
         String var10003 = File.separator;
         String var10004 = File.separator;
         File var1 = new File(
            new TextBuilder().method_2((Object)var10004).method_2((Object)var10003).method_2((Object)var10002).method_9("\u0001\u0001Mio\u0001.authtoken")
         );
         if (!var1.exists() || var1.length() <= 3L) {
            return null;
         }

         var0 = new FileInputStream(var1);
         var2 = var0.readAllBytes();
      } catch (Exception var13) {
         return null;
      } finally {
         if (var0 != null) {
            try {
               var0.close();
            } catch (Exception var12) {
            }
         }
      }

      return var2;
   }
}
