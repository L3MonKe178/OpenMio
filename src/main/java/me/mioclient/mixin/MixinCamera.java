package me.mioclient.mixin;

import me.mioclient.Hub;
import me.mioclient.api.Class_0687;
import me.mioclient.api.MioAPI;
import me.mioclient.enum_.Class_1229;
import me.mioclient.event.Event_31;
import me.mioclient.internal.RenderUtil;
import me.mioclient.module.movement.ElytraFlyModule;
import me.mioclient.module.player.FreecamModule;
import me.mioclient.module.render.FreeLookModule;
import me.mioclient.module.render.NoRenderModule;
import me.mioclient.module.render.ViewClipModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin({Camera.class})
public abstract class MixinCamera {
   private static ViewClipModule viewclip = Hub.field_2595.method_78(ViewClipModule.class);
   private static NoRenderModule norender = Hub.field_2595.method_78(NoRenderModule.class);
   private static ElytraFlyModule elytrafly = Hub.field_2595.method_78(ElytraFlyModule.class);
   private static FreecamModule freecam = Hub.field_2595.method_78(FreecamModule.class);
   private static FreeLookModule fl = Hub.field_2595.method_78(FreeLookModule.class);
   @Final
   @Shadow
   private Vector3f horizontalPlane;
   @Final
   @Shadow
   private Vector3f verticalPlane;
   @Final
   @Shadow
   private Vector3f diagonalPlane;
   @Shadow
   private Vec3d pos;
   @Shadow
   private float cameraY;
   @Shadow
   private float lastCameraY;
   @Shadow
   private Entity focusedEntity;
   @Unique
   private boolean hold = true;

   public MixinCamera() {
      super();
   }

   @Shadow
   protected void setPos(Vec3d var1) {
   }

   @Shadow
   protected abstract void setRotation(float var1, float var2);

   @Inject(
      method = {"clipToSpace"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void clipToSpaceHook(float var1, CallbackInfoReturnable<Float> var2) {
      if (viewclip.isToggled()) {
         var2.setReturnValue(viewclip.field_4494.getValue().floatValue());
      }
   }

   @Inject(
      method = {"updateEyeHeight"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void updateEyeHeightHook(CallbackInfo var1) {
      if (norender.isToggled() && norender.field_721.getValue() && this.focusedEntity != null) {
         var1.cancel();
         this.lastCameraY = this.cameraY;
         if (this.focusedEntity instanceof PlayerEntity var2) {
            if (var2.isInPose(EntityPose.CROUCHING)) {
               this.cameraY = 1.54F;
            } else if (this.cameraY < 1.62F && var2.isInPose(EntityPose.STANDING)) {
               float var4 = 1.62F - this.cameraY;
               var4 = (float)((double)var4 * 0.4);
               this.cameraY = 1.62F - var4;
            } else {
               this.cameraY = this.cameraY + (this.focusedEntity.getStandingEyeHeight() - this.cameraY) * 0.5F;
            }
         }
      }
   }

   @Inject(
      method = {"moveBy"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void moveByHook(float var1, float var2, float var3, CallbackInfo var4) {
      if (viewclip.isToggled() && viewclip.field_4495.getValue()) {
         double var5 = (double)this.horizontalPlane.x() * (double)var1 + (double)this.verticalPlane.x() * (double)var2 + (double)this.diagonalPlane.x() * (double)var3;
         double var7 = (double)this.horizontalPlane.y() * (double)var1 + (double)this.verticalPlane.y() * (double)var2 + (double)this.diagonalPlane.y() * (double)var3;
         double var9 = (double)this.horizontalPlane.z() * (double)var1 + (double)this.verticalPlane.z() * (double)var2 + (double)this.diagonalPlane.z() * (double)var3;
         float var11 = viewclip.field_4496.method_45();
         this.setPos(
            new Vec3d(this.pos.x + var5 * (double)var11, this.pos.y + var7 * (double)var11, this.pos.z + var9 * (double)var11)
         );
         var4.cancel();
      }
   }

   @ModifyArgs(
      method = {"update"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/render/Camera;setPos(DDD)V"
      )
   )
   private void onUpdateSetPosArgs(Args var1) {
      Event_31 var2 = new Event_31((Double)var1.get(0), (Double)var1.get(1), (Double)var1.get(2));
      MioAPI.field_4220.method_36(var2);
      if (var2.method_464()) {
         var1.set(0, var2.method_733().x);
         var1.set(1, var2.method_733().y);
         var1.set(2, var2.method_733().z);
      }
   }

   @ModifyArgs(
      method = {"update"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/render/Camera;setRotation(FF)V"
      )
   )
   private void onUpdateSetRotationArgs(Args var1) {
      if (elytrafly.isToggled() && elytrafly.field_4349.getValue() == Class_1229.BOUNCE && elytrafly.field_4381.getValue() && !elytrafly.field_4382.getValue()) {
         var1.set(1, elytrafly.field_4383.getValue());
      }

      float var2 = RenderUtil.method_776();
      if (freecam.isToggled()) {
         var1.set(0, (float)freecam.method_8(var2));
         var1.set(1, (float)freecam.method_334(var2));
      }
   }

   @Inject(
      method = {"update"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/render/Camera;setRotation(FF)V",
         ordinal = 0,
         shift = Shift.AFTER
      )}
   )
   public void lockRotation(BlockView var1, Entity var2, boolean var3, boolean var4, float var5, CallbackInfo var6) {
      if (!freecam.isToggled()) {
         if (fl.isToggled() && var2 instanceof ClientPlayerEntity) {
            MinecraftClient var7 = MinecraftClient.getInstance();
            Class_0687 var8 = (Class_0687)var2;
            if (var7.player != null && this.hold) {
               var8.setCameraYaw(var7.player.getYaw());
               var8.setCameraPitch(var7.player.getPitch());
               this.hold = false;
            }

            this.setRotation(var8.getCameraYaw(), var8.getCameraPitch());
         }

         if (!fl.isToggled() && var2 instanceof ClientPlayerEntity) {
            this.hold = true;
         }
      }
   }
}
