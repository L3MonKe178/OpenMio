package me.mioclient.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.mioclient.Hub;
import me.mioclient.api.Class_1309;
import me.mioclient.enum_.Class_0286;
import me.mioclient.internal.Class_0860;
import me.mioclient.internal.Class_1355;
import me.mioclient.module.misc.SwingModule;
import me.mioclient.module.render.ShaderModule;
import me.mioclient.module.render.ViewModelModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OutlineVertexConsumerProvider;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.joml.Quaternionf;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(
   value = {HeldItemRenderer.class},
   priority = 9999
)
public abstract class MixinHeldItemRenderer {
   private static ShaderModule shader = Hub.field_2595.method_78(ShaderModule.class);
   private static ViewModelModule viewmodel = Hub.field_2595.method_78(ViewModelModule.class);
   private static SwingModule swing = Hub.field_2595.method_78(SwingModule.class);
   @Shadow
   private ItemStack field_4047;
   @Shadow
   private float field_4043;
   @Shadow
   @Final
   private MinecraftClient field_4050;

   public MixinHeldItemRenderer() {
      super();
   }

   @Shadow
   protected abstract void method_3228(
      AbstractClientPlayerEntity var1,
      float var2,
      float var3,
      Hand var4,
      float var5,
      ItemStack var6,
      float var7,
      MatrixStack var8,
      VertexConsumerProvider var9,
      int var10
   );

   @Inject(
      method = {"renderFirstPersonItem"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/render/item/HeldItemRenderer;renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"
      )}
   )
   private void onRenderItem(
      AbstractClientPlayerEntity var1,
      float var2,
      float var3,
      Hand var4,
      float var5,
      ItemStack var6,
      float var7,
      MatrixStack var8,
      VertexConsumerProvider var9,
      int var10,
      CallbackInfo var11
   ) {
      if (viewmodel.isToggled()) {
         boolean var12 = var6.contains(DataComponentTypes.FOOD) || var6.getItem() instanceof PotionItem;
         boolean var13 = Class_1309.field_4219.player.getActiveItem().getItem() == var6.getItem();
         if (var12 && Class_1309.field_4219.player.getActiveItem() == var6 && !viewmodel.field_1910.getValue()) {
            return;
         }

         if (var4 == Hand.MAIN_HAND) {
            float var14 = var12 && var13 ? 0.0F : -viewmodel.field_1913.getValue();
            float var15 = var12 && var13 ? 0.0F : viewmodel.field_1915.getValue();
            if (Class_1309.field_4219.player.getMainArm() == Arm.LEFT) {
               var14 = -var14;
            }

            var8.translate(var14, viewmodel.field_1914.getValue(), var15);
            var8.scale(viewmodel.field_1916.getValue(), viewmodel.field_1917.getValue(), viewmodel.field_1918.getValue());
            var8.multiply(RotationAxis.NEGATIVE_X.rotationDegrees(viewmodel.field_1919.getValue()));
            var8.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(viewmodel.field_1920.getValue()));
            var8.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(viewmodel.field_1921.getValue()));
         } else {
            float var16 = var12 && var13 ? 0.0F : viewmodel.field_1923.getValue();
            float var17 = var12 && var13 ? 0.0F : viewmodel.field_1925.getValue();
            if (Class_1309.field_4219.player.getMainArm() == Arm.LEFT) {
               var16 = -var16;
            }

            var8.translate(var16, viewmodel.field_1924.getValue(), var17);
            var8.scale(viewmodel.field_1926.getValue(), viewmodel.field_1927.getValue(), viewmodel.field_1928.getValue());
            var8.multiply(RotationAxis.NEGATIVE_X.rotationDegrees(viewmodel.field_1929.getValue()));
            var8.multiply(RotationAxis.NEGATIVE_Y.rotationDegrees(viewmodel.field_1930.getValue()));
            var8.multiply(RotationAxis.NEGATIVE_Z.rotationDegrees(viewmodel.field_1931.getValue()));
         }
      }
   }

   @Inject(
      method = {"renderArmHoldingItem"},
      at = {@At("HEAD")}
   )
   private void renderArmHook(MatrixStack var1, VertexConsumerProvider var2, int var3, float var4, float var5, Arm var6, CallbackInfo var7) {
      if (viewmodel.isToggled() && viewmodel.field_1912.getValue()) {
         float var8 = -viewmodel.field_1913.getValue();
         if (Class_1309.field_4219.player.getMainArm() == Arm.LEFT) {
            var8 = -var8;
         }

         var1.translate(var8, viewmodel.field_1914.getValue(), viewmodel.field_1915.getValue());
         var1.scale(viewmodel.field_1916.getValue(), viewmodel.field_1917.getValue(), viewmodel.field_1918.getValue());
      }
   }

   @Redirect(
      method = {"renderItem(FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider$Immediate;Lnet/minecraft/client/network/ClientPlayerEntity;I)V"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/render/item/HeldItemRenderer;renderFirstPersonItem(Lnet/minecraft/client/network/AbstractClientPlayerEntity;FFLnet/minecraft/util/Hand;FLnet/minecraft/item/ItemStack;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"
      ),
      require = 0
   )
   private void modifySwing(
      HeldItemRenderer var1,
      AbstractClientPlayerEntity var2,
      float var3,
      float var4,
      Hand var5,
      float var6,
      ItemStack var7,
      float var8,
      MatrixStack var9,
      VertexConsumerProvider var10,
      int var11
   ) {
      this.method_3228(var2, var3, var4, var5, viewmodel.method_2(var5, var6), var7, var8, var9, var10, var11);
   }

   @ModifyVariable(
      method = {"renderFirstPersonItem"},
      at = @At("HEAD"),
      argsOnly = true
   )
   private VertexConsumerProvider renderFirstPersonItemHook(VertexConsumerProvider var1) {
      if (Class_1355.field_4422) {
         OutlineVertexConsumerProvider var2 = shader.field_2017.getValue().method_177().field_2301;
         var2.setColor(255, 255, 255, 255);
         return var2;
      } else {
         return var1;
      }
   }

   @Redirect(
      method = {"renderItem(FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider$Immediate;Lnet/minecraft/client/network/ClientPlayerEntity;I)V"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/util/math/MatrixStack;multiply(Lorg/joml/Quaternionf;)V"
      )
   )
   private void multiplyHook(MatrixStack var1, Quaternionf var2) {
      Class_1309.field_4220.method_36(new Class_0860());
      if (!viewmodel.isToggled() || !viewmodel.field_1908.getValue()) {
         var1.multiply(var2);
      }
   }

   @ModifyExpressionValue(
      method = {"updateHeldItems"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/util/math/MathHelper;clamp(FFF)F",
         ordinal = 2
      )}
   )
   private float updateHeldItemsHook(float var1) {
      if (swing.isToggled() && swing.field_505.getValue() != Class_0286.VANILLA) {
         boolean var2 = swing.field_505.getValue() == Class_0286.ONE_EIGHT;
         float var3 = this.field_4050.player.getAttackCooldownProgress(1.0F);
         if (var2) {
            var3 = 1.0F;
         }

         boolean var4 = !ItemStack.areItemsAndComponentsEqual(this.field_4047, Class_1309.field_4219.player.getMainHandStack());
         float var5 = var2 ? -0.6F : -0.4F;
         return MathHelper.clamp((var4 ? 0.0F : var3 * var3 * var3) - this.field_4043, var5, 0.4F);
      } else {
         return var1;
      }
   }

   @ModifyExpressionValue(
      method = {"renderFirstPersonItem"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;isUsingRiptide()Z"
      )}
   )
   private boolean isUsingRiptideHook(boolean var1) {
      return viewmodel.isToggled() && viewmodel.field_1934.getValue() ? false : var1;
   }

   @ModifyArgs(
      method = {"applyEquipOffset"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/util/math/MatrixStack;translate(FFF)V"
      )
   )
   private void applyEquipOffsetHook(Args var1) {
      if (viewmodel.isToggled() && viewmodel.field_1909.getValue()) {
         var1.set(1, -0.52F);
      }
   }

   @ModifyArgs(
      method = {"applyEatOrDrinkTransformation"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/util/math/MatrixStack;translate(FFF)V",
         ordinal = 0
      )
   )
   private void applyEatOrDrinkTransformationHook(Args var1) {
      if (viewmodel.isToggled()) {
         var1.set(1, (Float)var1.get(1) * viewmodel.field_1933.getValue());
      }
   }
}
