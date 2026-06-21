package me.mioclient.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReceiver;
import me.mioclient.Hub;
import me.mioclient.api.Class_0687;
import me.mioclient.api.Class_1309;
import me.mioclient.enum_.PreType;
import me.mioclient.event.Event_51;
import me.mioclient.internal.Class_0245;
import me.mioclient.internal.Class_0396;
import me.mioclient.module.movement.ElytraFlyModule;
import me.mioclient.module.movement.NoSlowModule;
import me.mioclient.module.movement.VelocityModule;
import me.mioclient.module.player.FreecamModule;
import me.mioclient.module.render.FreeLookModule;
import me.mioclient.module.render.NoRenderModule;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({Entity.class})
public abstract class MixinEntity implements Class_0687 {
   private static NoSlowModule noslow = Hub.field_2595.method_78(NoSlowModule.class);
   private static ElytraFlyModule elytrafly = Hub.field_2595.method_78(ElytraFlyModule.class);
   private static FreecamModule freecam = Hub.field_2595.method_78(FreecamModule.class);
   @Shadow
   private float field_6031;
   @Shadow
   private float field_5965;
   private static VelocityModule velo = Hub.field_2595.method_78(VelocityModule.class);
   private static FreeLookModule fl = Hub.field_2595.method_78(FreeLookModule.class);
   private static NoRenderModule norender = Hub.field_2595.method_78(NoRenderModule.class);
   @Unique
   private float cameraPitch;
   @Unique
   private float cameraYaw;
   @Unique
   private Vec3d mio$prevVelocity;

   public MixinEntity() {
      super();
   }

   @Shadow
   private Vec3d method_17835(Vec3d var1) {
      return null;
   }

   @Shadow
   public abstract void method_5784(MovementType var1, Vec3d var2);

   @Shadow
   @Override
   public abstract boolean equals(Object var1);

   @Shadow
   public abstract void method_33574(Vec3d var1);

   @Shadow
   public abstract void method_5814(double var1, double var3, double var5);

   @Shadow
   protected abstract void method_5710(float var1, float var2);

   @Shadow
   public abstract Vec3d method_18798();

   @Shadow
   public abstract boolean method_41328(EntityPose var1);

   @ModifyReceiver(
      method = {"getVelocityMultiplier"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/block/Block;getVelocityMultiplier()F"
      )},
      require = 0
   )
   private Block getVelocityMultiplierHook(Block var1) {
      if ((Entity)(Object)this != MinecraftClient.getInstance().player) {
         return var1;
      } else {
         return noslow.isToggled() && noslow.field_1698.getValue() ? Blocks.STONE : var1;
      }
   }

   @Inject(
      method = {"pushAwayFrom"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void pushAwayFromHook(Entity var1, CallbackInfo var2) {
      if (this.equals(MinecraftClient.getInstance().player) && velo.isToggled() && velo.field_2514.getValue()) {
         var2.cancel();
      }
   }

   @Redirect(
      method = {"updateMovementInFluid"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/fluid/FluidState;getVelocity(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/util/math/Vec3d;"
      )
   )
   private Vec3d updateMovementInFluidHook(FluidState var1, BlockView var2, BlockPos var3) {
      return velo.isToggled() && velo.field_2515.getValue() ? Vec3d.ZERO : var1.getVelocity(var2, var3);
   }

   @Redirect(
      method = {"move"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/entity/Entity;adjustMovementForCollisions(Lnet/minecraft/util/math/Vec3d;)Lnet/minecraft/util/math/Vec3d;"
      )
   )
   private Vec3d moveHook(Entity var1, Vec3d var2) {
      if ((Entity)(Object)this != MinecraftClient.getInstance().player) {
         return this.method_17835(var2);
      } else {
         Event_51 var3 = new Event_51(PreType.PRE, 0.6F);
         Class_1309.field_4220.method_36(var3);
         Class_0396.method_9((LivingEntity)(Object)this, var3.method_575());
         Vec3d var4 = this.method_17835(var2);
         if (var4 != null) {
            Class_1309.field_4220.method_36(new Event_51(PreType.POST, (float)var4.y));
         }

         return var4;
      }
   }

   @Inject(
      method = {"getPose"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void getPoseHook(CallbackInfoReturnable<EntityPose> var1) {
      if ((Entity)(Object)this == MinecraftClient.getInstance().player) {
         if (elytrafly.method_1178() || elytrafly.method_1183()) {
            var1.setReturnValue(EntityPose.STANDING);
            var1.cancel();
         }
      }
   }

   @Inject(
      method = {"changeLookDirection"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void changeCameraLookDirection(double var1, double var3, CallbackInfo var5) {
      if (!freecam.isToggled()) {
         if (fl.isToggled() && this.equals(MinecraftClient.getInstance().player)) {
            double var6 = var3 * 0.15;
            double var8 = var1 * 0.15;
            this.cameraPitch = MathHelper.clamp(this.cameraPitch + (float)var6, (float)(-Class_0245.field_685), (float)Class_0245.field_685);
            this.cameraYaw += (float)var8;
            var5.cancel();
         }
      }
   }

   @Inject(
      method = {"lerpPosAndRotation"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void lerpPosAndRotationHook(int var1, double var2, double var4, double var6, double var8, double var10, CallbackInfo var12) {
      if (norender.isToggled() && norender.field_749.getValue()) {
         this.method_5814(var2, var4, var6);
         this.method_5710((float)var8, (float)var10);
         var12.cancel();
      }
   }

   @ModifyExpressionValue(
      method = {"getCameraPosVec"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/entity/Entity;getStandingEyeHeight()F"
      )}
   )
   private float getHeightHook(float var1) {
      return norender.isToggled() && norender.field_721.getValue() && this.method_41328(EntityPose.CROUCHING) ? 1.54F : var1;
   }

   @Inject(
      method = {"baseTick"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/util/profiler/Profiler;push(Ljava/lang/String;)V",
         ordinal = 0,
         shift = Shift.AFTER
      )}
   )
   private void baseTick(CallbackInfo var1) {
      this.mio$prevVelocity = this.method_18798();
   }

   @Inject(
      method = {"<init>"},
      at = {@At("TAIL")}
   )
   private void init(EntityType var1, World var2, CallbackInfo var3) {
      this.mio$prevVelocity = Vec3d.ZERO;
   }

   @Unique
   @Override
   public float getCameraPitch() {
      return this.cameraPitch;
   }

   @Unique
   @Override
   public float getCameraYaw() {
      return this.cameraYaw;
   }

   @Unique
   @Override
   public void setCameraPitch(float var1) {
      this.cameraPitch = var1;
   }

   @Unique
   @Override
   public void setCameraYaw(float var1) {
      this.cameraYaw = var1;
   }

   @Override
   public Vec3d mio$getPrevVelocity() {
      return this.mio$prevVelocity;
   }
}
