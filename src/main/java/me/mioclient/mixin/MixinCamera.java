package me.mioclient.mixin;

import me.mioclient.Hub;
import me.mioclient.api.Class_0687;
import me.mioclient.api.Class_1309;
import me.mioclient.enum_.Class_1229;
import me.mioclient.event.Event_31;
import me.mioclient.internal.Class_0838;
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
   private Vector3f field_18714;
   @Final
   @Shadow
   private Vector3f field_18715;
   @Final
   @Shadow
   private Vector3f field_18716;
   @Shadow
   private Vec3d field_18712;
   @Shadow
   private float field_18721;
   @Shadow
   private float field_18722;
   @Shadow
   private Entity field_18711;
   @Unique
   private boolean hold = true;

   public MixinCamera() {
      super();
   }

   @Shadow
   protected void method_19322(Vec3d var1) {
   }

   @Shadow
   protected abstract void method_19325(float var1, float var2);

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
      if (norender.isToggled() && norender.field_721.getValue() && this.field_18711 != null) {
         var1.cancel();
         this.field_18722 = this.field_18721;
         if (this.field_18711 instanceof PlayerEntity var2) {
            if (var2.isInPose(EntityPose.CROUCHING)) {
               this.field_18721 = 1.54F;
            } else if (this.field_18721 < 1.62F && var2.isInPose(EntityPose.STANDING)) {
               float var4 = 1.62F - this.field_18721;
               var4 = (float)((double)var4 * 0.4);
               this.field_18721 = 1.62F - var4;
            } else {
               this.field_18721 = this.field_18721 + (this.field_18711.getStandingEyeHeight() - this.field_18721) * 0.5F;
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
         double var5 = (double)this.field_18714.x() * (double)var1 + (double)this.field_18715.x() * (double)var2 + (double)this.field_18716.x() * (double)var3;
         double var7 = (double)this.field_18714.y() * (double)var1 + (double)this.field_18715.y() * (double)var2 + (double)this.field_18716.y() * (double)var3;
         double var9 = (double)this.field_18714.z() * (double)var1 + (double)this.field_18715.z() * (double)var2 + (double)this.field_18716.z() * (double)var3;
         float var11 = viewclip.field_4496.method_45();
         this.method_19322(
            new Vec3d(this.field_18712.x + var5 * (double)var11, this.field_18712.y + var7 * (double)var11, this.field_18712.z + var9 * (double)var11)
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
      Class_1309.field_4220.method_36(var2);
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

      float var2 = Class_0838.method_776();
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

            this.method_19325(var8.getCameraYaw(), var8.getCameraPitch());
         }

         if (!fl.isToggled() && var2 instanceof ClientPlayerEntity) {
            this.hold = true;
         }
      }
   }
}
