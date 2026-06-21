package me.mioclient.mixin;

import me.mioclient.Hub;
import me.mioclient.api.MioAPI;
import me.mioclient.enum_.Class_0126;
import me.mioclient.event.Event_37;
import me.mioclient.event.Event_50;
import me.mioclient.event.Event_59;
import me.mioclient.mixin.ducks.DuckExplosionS2CPacket;
import me.mioclient.module.movement.VelocityModule;
import me.mioclient.module.render.NoRenderModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.BlockUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.ChunkDeltaUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;
import net.minecraft.network.packet.s2c.play.UpdateSelectedSlotS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ClientPlayNetworkHandler.class})
public class MixinClientPlayNetworkHandler {
   private static NoRenderModule norender = Hub.field_2595.method_78(NoRenderModule.class);
   private static VelocityModule velocity = Hub.field_2595.method_78(VelocityModule.class);

   public MixinClientPlayNetworkHandler() {
      super();
   }

   @Inject(
      method = {"onExplosion"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/network/NetworkThreadUtils;forceMainThread(Lnet/minecraft/network/packet/Packet;Lnet/minecraft/network/listener/PacketListener;Lnet/minecraft/util/thread/ThreadExecutor;)V",
         shift = Shift.AFTER
      )},
      cancellable = true
   )
   private void onExplosionVelocity(ExplosionS2CPacket var1, CallbackInfo var2) {
      Event_37 var3 = new Event_37(var1);
      MioAPI.field_4220.method_36(var3);
      if (var3.method_464()) {
         var2.cancel();
      } else {
         DuckExplosionS2CPacket var4 = (DuckExplosionS2CPacket)var1;
         var4.setX(var3.method_398());
         var4.setY(var3.method_399());
         var4.setZ(var3.method_400());
      }
   }

   @Inject(
      method = {"onEntityVelocityUpdate"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/network/NetworkThreadUtils;forceMainThread(Lnet/minecraft/network/packet/Packet;Lnet/minecraft/network/listener/PacketListener;Lnet/minecraft/util/thread/ThreadExecutor;)V",
         shift = Shift.AFTER
      )},
      cancellable = true
   )
   private void onEntityVelocityUpdate(EntityVelocityUpdateS2CPacket var1, CallbackInfo var2) {
      if (velocity.method_734() && var1.getEntityId() == MinecraftClient.getInstance().player.getId()) {
         var2.cancel();
      }
   }

   @Inject(
      method = {"onEntitySpawn"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onEntitySpawn(EntitySpawnS2CPacket var1, CallbackInfo var2) {
      if (norender.isToggled()) {
         if (var1 != null && var1.getEntityType() != null && norender.method_2(var1.getEntityType()) && norender.field_763.getValue() == Class_0126.FULL) {
            var2.cancel();
         }
      }
   }

   @Inject(
      method = {"onUpdateSelectedSlot"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/network/NetworkThreadUtils;forceMainThread(Lnet/minecraft/network/packet/Packet;Lnet/minecraft/network/listener/PacketListener;Lnet/minecraft/util/thread/ThreadExecutor;)V",
         shift = Shift.AFTER
      )},
      cancellable = true
   )
   private void onUpdateSelectedSlot(UpdateSelectedSlotS2CPacket var1, CallbackInfo var2) {
      Event_59 var3 = new Event_59(var1.getSlot());
      MioAPI.field_4220.method_36(var3);
      if (var3.method_464()) {
         var2.cancel();
      }
   }

   @Inject(
      method = {"onBlockUpdate"},
      at = {@At("TAIL")}
   )
   private void onBlockUpdateHook(BlockUpdateS2CPacket var1, CallbackInfo var2) {
      MioAPI.field_4220.method_36(new Event_50());
   }

   @Inject(
      method = {"onChunkDeltaUpdate"},
      at = {@At("TAIL")}
   )
   private void onChunkDeltaUpdateHook(ChunkDeltaUpdateS2CPacket var1, CallbackInfo var2) {
      MioAPI.field_4220.method_36(new Event_50());
   }
}
