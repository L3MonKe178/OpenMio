package me.mioclient.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_1229;
import me.mioclient.internal.Class_0245;
import me.mioclient.internal.Class_0392;
import me.mioclient.internal.Class_1355;
import me.mioclient.module.movement.ElytraFlyModule;
import me.mioclient.module.render.AnimationsModule;
import me.mioclient.module.render.NoRenderModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin({PlayerEntityRenderer.class})
public abstract class MixinPlayerEntityRenderer extends LivingEntityRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {
   private static AnimationsModule animations = Hub.field_2595.method_78(AnimationsModule.class);
   private static final NoRenderModule norender = Hub.field_2595.method_78(NoRenderModule.class);
   private static final ElytraFlyModule elytrafly = Hub.field_2595.method_78(ElytraFlyModule.class);
   @Unique
   AbstractClientPlayerEntity rendered;

   public MixinPlayerEntityRenderer(Context var1, PlayerEntityModel<AbstractClientPlayerEntity> var2, float var3) {
      super(var1, var2, var3);
   }

   @Inject(
      method = {"getPositionOffset(Lnet/minecraft/client/network/AbstractClientPlayerEntity;F)Lnet/minecraft/util/math/Vec3d;"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void mio$getPositionOffset(AbstractClientPlayerEntity var1, float var2, CallbackInfoReturnable<Vec3d> var3) {
      if (animations.method_130() && var1 != MinecraftClient.getInstance().player) {
         var3.setReturnValue(new Vec3d(0.0, (double)(var1.getScale() * -2.0F) / 16.0, 0.0));
      }
   }

   @ModifyExpressionValue(
      method = {"setModelPose(Lnet/minecraft/client/network/AbstractClientPlayerEntity;)V"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;isInSneakingPose()Z"
      )}
   )
   private boolean mio$isInSneakingPose(boolean var1, @Local(argsOnly = true) AbstractClientPlayerEntity var2) {
      return animations.method_130() && var2 != MinecraftClient.getInstance().player ? true : var1;
   }

   @Inject(
      method = {"scale(Lnet/minecraft/client/network/AbstractClientPlayerEntity;Lnet/minecraft/client/util/math/MatrixStack;F)V"},
      at = {@At("HEAD")}
   )
   private void scalePre(AbstractClientPlayerEntity var1, MatrixStack var2, float var3, CallbackInfo var4) {
      this.rendered = var1;
   }

   @ModifyArgs(
      method = {"scale(Lnet/minecraft/client/network/AbstractClientPlayerEntity;Lnet/minecraft/client/util/math/MatrixStack;F)V"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/util/math/MatrixStack;scale(FFF)V"
      )
   )
   private void scaleHook(Args var1) {
      if (animations.isToggled() && animations.method_128() && this.rendered != MinecraftClient.getInstance().player) {
         var1.set(0, (Float)var1.get(0) * animations.field_346.getValue());
         var1.set(1, (Float)var1.get(1) * animations.field_346.getValue());
         var1.set(2, (Float)var1.get(2) * animations.field_346.getValue());
      }
   }

   @ModifyArgs(
      method = {"setupTransforms(Lnet/minecraft/client/network/AbstractClientPlayerEntity;Lnet/minecraft/client/util/math/MatrixStack;FFFF)V"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/util/math/MatrixStack;multiply(Lorg/joml/Quaternionf;)V"
      )
   )
   private void setupTransformsHook(Args var1) {
      if (elytrafly.isToggled()) {
         if (elytrafly.field_4349.getValue() == Class_1229.BOUNCE
            && elytrafly.method_41()
            && elytrafly.field_4381.getValue()
            && elytrafly.field_4382.getValue()) {
            var1.set(0, RotationAxis.POSITIVE_X.rotationDegrees((float)(-Class_0245.field_684)));
         }
      }
   }

   @ModifyExpressionValue(
      method = {"renderArm"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/render/RenderLayer;getEntityTranslucent(Lnet/minecraft/util/Identifier;)Lnet/minecraft/client/render/RenderLayer;"
      )}
   )
   private RenderLayer renderArmHook(RenderLayer var1, @Local Identifier var2) {
      return norender.isToggled() && norender.field_723.getValue() && Class_1355.field_4422 ? RenderLayer.getEntitySolid(var2) : var1;
   }

   @ModifyExpressionValue(
      method = {"renderArm"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/render/RenderLayer;getEntitySolid(Lnet/minecraft/util/Identifier;)Lnet/minecraft/client/render/RenderLayer;"
      )}
   )
   private RenderLayer renderArm(RenderLayer var1, @Local Identifier var2) {
      return norender.method_282() != 1.0F ? Class_0392.method_9(var2) : var1;
   }
}
