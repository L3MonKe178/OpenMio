package me.mioclient.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.authlib.GameProfile;
import me.mioclient.Hub;
import me.mioclient.api.Class_1194;
import me.mioclient.api.MioAPI;
import me.mioclient.enum_.Class_0813;
import me.mioclient.enum_.Class_1229;
import me.mioclient.enum_.PreType;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_17;
import me.mioclient.event.Event_19;
import me.mioclient.event.Event_38;
import me.mioclient.event.Event_48;
import me.mioclient.event.Event_54;
import me.mioclient.event.Event_9;
import me.mioclient.module.misc.SwingModule;
import me.mioclient.module.movement.ElytraFlyModule;
import me.mioclient.module.movement.EntityControlModule;
import me.mioclient.module.movement.NoSlowModule;
import me.mioclient.module.movement.SprintModule;
import net.minecraft.client.input.Input;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.MovementType;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({ClientPlayerEntity.class})
public abstract class MixinClientPlayerEntity extends AbstractClientPlayerEntity implements MioAPI, Class_1194 {
   private static ElytraFlyModule elytrafly = Hub.field_2595.method_78(ElytraFlyModule.class);
   private static SwingModule swing = Hub.field_2595.method_78(SwingModule.class);
   private static NoSlowModule noslow = Hub.field_2595.method_78(NoSlowModule.class);
   private static EntityControlModule entitycontrol = Hub.field_2595.method_78(EntityControlModule.class);
   private static SprintModule sprint = Hub.field_2595.method_78(SprintModule.class);
   @Shadow
   public Input input;
   @Unique
   private Event_19 motionEvent = new Event_19();

   public MixinClientPlayerEntity(ClientWorld var1, GameProfile var2) {
      super(var1, var2);
   }

   @Shadow
   protected abstract void autoJump(float var1, float var2);

   @Shadow
   public abstract float getPitch(float tickDelta);

   @Shadow
   public abstract float getYaw(float tickDelta);

   @Shadow
   public abstract void init();

   @Shadow
   public abstract boolean isUsingItem();

   @Shadow
   private void sendMovementPackets() {
   }

   @Inject(
      method = {"swingHand"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void swingHandHook(Hand var1, CallbackInfo var2) {
      if (swing.isToggled() && swing.field_504.getValue() != Class_0813.VANILLA) {
         if (swing.field_504.getValue() != Class_0813.PACKET) {
            super.swingHand(swing.field_504.getValue().method_12());
         }

         field_4219.player.networkHandler.sendPacket(new HandSwingC2SPacket(var1));
         var2.cancel();
      }
   }

   @Inject(
      method = {"tick"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;tick()V",
         shift = Shift.BEFORE
      )}
   )
   public void tickPost(CallbackInfo var1) {
      if (field_4219.player != null && field_4219.world != null) {
         field_4220.method_36(new Event_17());
      }
   }

   @Inject(
      method = {"tick"},
      at = {@At("RETURN")}
   )
   public void tick(CallbackInfo var1) {
      if (field_4219.player != null && field_4219.world != null) {
         field_4220.method_36(new Event_1());
      }
   }

   @Inject(
      method = {"move"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void move(MovementType var1, Vec3d var2, CallbackInfo var3) {
      Vec3d var4 = new Vec3d(var2.x, var2.y, var2.z);
      Event_9 var5 = new Event_9(var2, var1);
      field_4220.method_36(var5);
      if (!var5.method_464()) {
         if (var4.equals(var5.method_1066())) {
            return;
         }

         var3.cancel();
         double var6 = this.getX();
         double var8 = this.getZ();
         super.move(var5.method_1067(), var5.method_1066());
         this.autoJump((float)(this.getX() - var6), (float)(this.getZ() - var8));
      } else {
         var3.cancel();
      }
   }

   @Inject(
      method = {"tick"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;tick()V",
         shift = Shift.AFTER
      )},
      cancellable = true
   )
   private void tickPreHook(CallbackInfo var1) {
      this.resetEvent();
      field_4220.method_36(this.motionEvent);
      this.setPosition(this.motionEvent.method_380(), this.motionEvent.method_395(), this.motionEvent.method_396());
      this.setYaw(this.motionEvent.method_704());
      this.setPitch(this.motionEvent.method_705());
      this.setOnGround(this.motionEvent.method_707());
      if (this.motionEvent.method_464()) {
         var1.cancel();
      }
   }

   @Inject(
      method = {"tick"},
      at = {@At(
         value = "INVOKE",
         target = "Ljava/util/List;iterator()Ljava/util/Iterator;",
         shift = Shift.BEFORE
      )},
      cancellable = true
   )
   private void tickPostHook(CallbackInfo var1) {
      if (this.motionEvent.method_380() != this.motionEvent.method_698()
         || this.motionEvent.method_395() != this.motionEvent.method_699()
         || this.motionEvent.method_396() != this.motionEvent.method_700()) {
         this.setPosition(new Vec3d(this.motionEvent.method_698(), this.motionEvent.method_699(), this.motionEvent.method_700()));
      }

      this.resetRotations();
      if (this.isOnGround() == this.motionEvent.method_707()) {
         this.setOnGround(this.motionEvent.method_703());
      }
   }

   @Inject(
      method = {"sendMovementPackets"},
      at = {@At("RETURN")}
   )
   private void sendMovementPacketsPostHook(CallbackInfo var1) {
      Event_19 var2 = new Event_19(PreType.POST, this.motionEvent);
      if (this.motionEvent.method_464()) {
         var2.method_463();
      }

      field_4220.method_36(var2);
   }

   @ModifyExpressionValue(
      method = {"sendMovementPackets"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/network/ClientPlayerEntity;getX()D"
      )}
   )
   private double getXHook(double var1) {
      return this.motionEvent.method_380();
   }

   @ModifyExpressionValue(
      method = {"sendMovementPackets"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/network/ClientPlayerEntity;getY()D"
      )}
   )
   private double getYHook(double var1) {
      return this.motionEvent.method_395();
   }

   @ModifyExpressionValue(
      method = {"sendMovementPackets"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/network/ClientPlayerEntity;getZ()D"
      )}
   )
   private double getZHook(double var1) {
      return this.motionEvent.method_396();
   }

   @ModifyExpressionValue(
      method = {"sendMovementPackets"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/network/ClientPlayerEntity;getYaw()F"
      )}
   )
   private float getYawHook(float var1) {
      if (this.motionEvent.method_701() != var1) {
         return var1;
      } else {
         return this.motionEvent.method_701() == this.motionEvent.method_500() ? var1 : this.motionEvent.method_500();
      }
   }

   @ModifyExpressionValue(
      method = {"sendMovementPackets"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/network/ClientPlayerEntity;getPitch()F"
      )}
   )
   private float getPitchHook(float var1) {
      if (this.motionEvent.method_702() != var1) {
         return var1;
      } else {
         return this.motionEvent.method_702() == this.motionEvent.method_501() ? var1 : this.motionEvent.method_501();
      }
   }

   @ModifyExpressionValue(
      method = {"sendMovementPackets"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/network/ClientPlayerEntity;isOnGround()Z"
      )}
   )
   private boolean isOnGroundHook(boolean var1) {
      return this.motionEvent.method_707();
   }

   @Redirect(
      method = {"tickMovement"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/network/ClientPlayerEntity;setSprinting(Z)V",
         ordinal = 3
      ),
      require = 0
   )
   private void setSprintingHook(ClientPlayerEntity var1, boolean var2) {
      var1.setSprinting(sprint.isToggled() && sprint.method_363());
   }

   @Inject(
      method = {"tickMovement"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/network/ClientPlayerEntity;canStartSprinting()Z"
      )}
   )
   private void onSprintUpdate(CallbackInfo var1) {
      field_4220.method_36(new Event_38());
   }

   @ModifyExpressionValue(
      method = {"tickMovement"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z"
      )}
   )
   private boolean tickMovementHook(boolean var1) {
      Event_48 var2 = new Event_48();
      field_4220.method_36(var2);
      return var2.method_464() ? false : var1;
   }

   @Inject(
      method = {"pushOutOfBlocks"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void pushOutOfBlocksHook(double var1, double var3, CallbackInfo var5) {
      Event_54 var6 = new Event_54();
      field_4220.method_36(var6);
      if (var6.method_464()) {
         var5.cancel();
      }
   }

   @Inject(
      method = {"getMountJumpStrength"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void getJumpMountStrengthHook(CallbackInfoReturnable<Float> var1) {
      if (entitycontrol.isToggled() && entitycontrol.field_3688.getValue()) {
         var1.setReturnValue(1.0F);
      }
   }

   @Inject(
      method = {"tickNausea"},
      at = {@At("HEAD")}
   )
   private void updateNauseaHook(CallbackInfo var1) {
      if (noslow.isToggled() && noslow.field_1686.getValue() && this.portalManager != null) {
         this.portalManager.setInPortal(false);
      }
   }

   @Inject(
      method = {"tickMovement"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/network/ClientPlayerEntity;isFallFlying()Z",
         shift = Shift.BEFORE
      )}
   )
   private void tickMovementElytra(CallbackInfo var1) {
      if (elytrafly.isToggled() && elytrafly.method_558()) {
         boolean var2 = field_4219.player.input.jumping;
         if ((elytrafly.field_4349.getValue() != Class_1229.PACKET || elytrafly.field_4373.getValue() && field_4219.player.input.jumping) && var2) {
            elytrafly.method_1099();
         } else {
            Hub.field_2596.method_38(elytrafly);
         }
      }
   }

   @Inject(
      method = {"shouldSlowDown"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void shouldSlowdown(CallbackInfoReturnable<Boolean> var1) {
      boolean var2 = field_4219.player.isCrawling() && noslow.field_1689.getValue();
      boolean var3 = !var2 && field_4219.player.isSneaking() && noslow.field_1688.getValue();
      if (noslow.isToggled() && (var2 || var3)) {
         var1.setReturnValue(false);
      }
   }

   @Override
   public void sendMovementPacketsWrapper() {
      this.sendMovementPackets();
   }

   @Override
   public void superTick() {
      super.tick();
   }

   @Override
   public void resetEvent() {
      this.motionEvent = new Event_19(PreType.PRE, this.getX(), this.getBoundingBox().minY, this.getZ(), this.getYaw(), this.getPitch(), this.isOnGround());
   }

   @Override
   public void resetRotations() {
      Hub.field_2598
         .method_29(
            this.hasVehicle() && !this.motionEvent.method_706() ? this.getVehicle().getYaw() : this.motionEvent.method_500(), this.motionEvent.method_501()
         );
      if (this.getYaw() == this.motionEvent.method_704()) {
         this.setYaw(this.motionEvent.method_701());
      }

      if (this.getPitch() == this.motionEvent.method_705()) {
         this.setPitch(this.motionEvent.method_702());
      }
   }
}
