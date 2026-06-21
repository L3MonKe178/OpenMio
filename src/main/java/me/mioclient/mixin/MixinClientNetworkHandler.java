package me.mioclient.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import me.mioclient.Hub;
import me.mioclient.api.MioAPI;
import me.mioclient.event.Event_24;
import me.mioclient.internal.Class_0542;
import me.mioclient.internal.CommandManager;
import me.mioclient.module.render.AmbienceModule;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.s2c.play.VehicleMoveS2CPacket;
import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ClientPlayNetworkHandler.class})
public class MixinClientNetworkHandler {
   private static final AmbienceModule ambience = Hub.field_2595.method_78(AmbienceModule.class);
   @Shadow
   private ClientWorld field_3699;
   @Unique
   private Event_24 event;

   public MixinClientNetworkHandler() {
      super();
   }

   @Inject(
      method = {"sendChatMessage"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void sendChatMessageHook(String var1, CallbackInfo var2) {
      if (var1.startsWith(CommandManager.method_927())) {
         CommandManager.method_7(var1.substring(CommandManager.method_927().length()));
         var2.cancel();
      }
   }

   @ModifyVariable(
      method = {"sendChatMessage"},
      at = @At(
         value = "INVOKE",
         target = "Ljava/time/Instant;now()Ljava/time/Instant;",
         shift = Shift.BEFORE
      ),
      argsOnly = true
   )
   private String dabigbulletz(String var1) {
      this.event = new Event_24(var1);
      MioAPI.field_4220.method_36(this.event);
      return this.event.method_219();
   }

   @Inject(
      method = {"sendChatMessage"},
      at = {@At(
         value = "INVOKE",
         target = "Ljava/time/Instant;now()Ljava/time/Instant;",
         shift = Shift.AFTER
      )},
      cancellable = true
   )
   private void dabigbulletz(String var1, CallbackInfo var2) {
      if (this.event != null && this.event.method_464()) {
         var2.cancel();
      }
   }

   @Inject(
      method = {"onWorldTimeUpdate"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/network/NetworkThreadUtils;forceMainThread(Lnet/minecraft/network/packet/Packet;Lnet/minecraft/network/listener/PacketListener;Lnet/minecraft/util/thread/ThreadExecutor;)V",
         shift = Shift.AFTER
      )},
      cancellable = true
   )
   private void onWorldTimeUpdateHook(WorldTimeUpdateS2CPacket var1, CallbackInfo var2) {
      if (ambience.isToggled() && ambience.field_210.getValue()) {
         this.field_3699.setTime(ambience.method_94());
         this.field_3699.setTimeOfDay(ambience.method_94());
         var2.cancel();
      }
   }

   @Inject(
      method = {"onVehicleMove"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/entity/Entity;updatePositionAndAngles(DDDFF)V"
      )},
      cancellable = true
   )
   private void onVehicleMove(VehicleMoveS2CPacket var1, CallbackInfo var2, @Local Entity var3) {
      Vec3d var4 = new Vec3d(var1.getX(), var1.getY(), var1.getZ());
      Class_0542 var5 = new Class_0542(var4);
      MioAPI.field_4220.method_36(var5);
      if (var5.method_464()) {
         var2.cancel();
      }
   }
}
