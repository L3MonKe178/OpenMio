package me.mioclient.mixin;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import me.mioclient.Hub;
import me.mioclient.api.MioAPI;
import me.mioclient.enum_.Priority;
import me.mioclient.event.Event_10;
import me.mioclient.event.Event_4;
import me.mioclient.event.Event_43;
import me.mioclient.internal.ChatUtil;
import me.mioclient.module.misc.NoPacketKickModule;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.NetworkSide;
import net.minecraft.network.PacketCallbacks;
import net.minecraft.network.packet.Packet;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ClientConnection.class})
public class MixinClientConnection {
   private static NoPacketKickModule nopacketkick = Hub.field_2595.method_78(NoPacketKickModule.class);
   @Shadow
   private Channel channel;
   @Shadow
   @Final
   private NetworkSide side;

   public MixinClientConnection() {
      super();
   }

   @Inject(
      method = {"channelRead0*"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void channelRead0(ChannelHandlerContext var1, Packet<?> var2, CallbackInfo var3) {
      if (this.channel.isOpen() && var2 != null) {
         try {
            Event_4 var4 = new Event_4(var2);
            MioAPI.field_4220.method_36(var4);
            if (var4.method_464()) {
               var3.cancel();
            }
         } catch (Exception var5) {
         }
      }
   }

   @Inject(
      method = {"sendImmediately"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void sendImmediately(Packet<?> var1, PacketCallbacks var2, boolean var3, CallbackInfo var4) {
      if (this.side == NetworkSide.CLIENTBOUND) {
         try {
            Event_10 var5 = new Event_10(var1);
            MioAPI.field_4220.method_36(var5);
            if (var5.method_464()) {
               var4.cancel();
            }
         } catch (Exception var6) {
         }
      }
   }

   @Inject(
      method = {"sendInternal"},
      at = {@At("TAIL")}
   )
   private void sendInternal(Packet<?> var1, PacketCallbacks var2, boolean var3, CallbackInfo var4) {
      if (this.side == NetworkSide.CLIENTBOUND) {
         try {
            Event_43 var5 = new Event_43(var1);
            MioAPI.field_4220.method_36(var5);
         } catch (Exception var6) {
         }
      }
   }

   @Inject(
      method = {"exceptionCaught"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void exceptionCaught(ChannelHandlerContext var1, Throwable var2, CallbackInfo var3) {
      if (nopacketkick.isToggled()) {
         if (nopacketkick.field_4173.getValue()) {
            try {
               ChatUtil.method_2(nopacketkick.method_38(var2), ChatUtil.method_2(nopacketkick), Priority.HIGH);
            } catch (Throwable var5) {
            }
         }

         var3.cancel();
      }
   }
}
