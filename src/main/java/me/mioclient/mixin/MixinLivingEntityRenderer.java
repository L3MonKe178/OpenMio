package me.mioclient.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import java.util.Collections;
import java.util.List;
import me.mioclient.Hub;
import me.mioclient.api.MioAPI;
import me.mioclient.event.Event_14;
import me.mioclient.internal.RotationManager;
import me.mioclient.internal.Class_1081;
import me.mioclient.internal.Class_1355;
import me.mioclient.module.render.AnimationsModule;
import me.mioclient.module.render.ChamsModule;
import me.mioclient.module.render.NoRenderModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.ColorHelper.Argb;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(
   value = {LivingEntityRenderer.class},
   priority = 9999
)
public abstract class MixinLivingEntityRenderer<T extends LivingEntity, M extends EntityModel<T>> implements MioAPI {
   private static final ChamsModule chams = Hub.field_2595.method_78(ChamsModule.class);
   private static final AnimationsModule animations = Hub.field_2595.method_78(AnimationsModule.class);
   private static final NoRenderModule norender = Hub.field_2595.method_78(NoRenderModule.class);
   @Unique
   private T lastEntity;

   public MixinLivingEntityRenderer() {
      super();
   }

   @WrapWithCondition(
      method = {"render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/render/entity/model/EntityModel;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;III)V"
      )},
      require = 0
   )
   private boolean onRenderModel(EntityModel var1, MatrixStack var2, VertexConsumer var3, int var4, int var5, int var6) {
      if (Class_1355.field_4422) {
         return true;
      } else {
         float var7 = (float)Argb.getRed(var6) / 255.0F;
         float var8 = (float)Argb.getGreen(var6) / 255.0F;
         float var9 = (float)Argb.getBlue(var6) / 255.0F;
         float var10 = (float)Argb.getAlpha(var6) / 255.0F;
         Event_14 var11 = Event_14.method_2(this.lastEntity, var4, var7, var8, var9, var10);
         field_4220.method_36(var11);
         var6 = Class_1081.method_2(var11.method_934(), var11.method_935(), var11.method_936(), var11.method_937());
         if (ChamsModule.field_253) {
            var1.render(var2, chams.method_104().method_741(), var11.method_938(), var5);
         } else if (!var11.method_464()) {
            var1.render(var2, var3, var11.method_938(), var5, var6);
         }

         return false;
      }
   }

   @ModifyExpressionValue(
      method = {"render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"},
      at = {@At(
         value = "FIELD",
         target = "Lnet/minecraft/client/render/entity/LivingEntityRenderer;features:Ljava/util/List;"
      )}
   )
   private List<FeatureRenderer<T, M>> render2(List<FeatureRenderer<T, M>> var1) {
      return ChamsModule.field_253 ? Collections.emptyList() : var1;
   }

   @Redirect(
      method = {"render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/entity/LivingEntity;hasVehicle()Z",
         ordinal = 2
      )
   )
   public boolean renderHook(LivingEntity var1) {
      return animations.method_129() && var1 instanceof PlayerEntity ? true : var1.hasVehicle();
   }

   @Redirect(
      method = {"render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"},
      at = @At(
         value = "FIELD",
         target = "Lnet/minecraft/entity/LivingEntity;prevBodyYaw:F"
      )
   )
   private float hook1(LivingEntity var1) {
      return var1 == field_4219.player && !RotationManager.field_1538 ? this.rotations().method_520() : var1.prevBodyYaw;
   }

   @Redirect(
      method = {"render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"},
      at = @At(
         value = "FIELD",
         target = "Lnet/minecraft/entity/LivingEntity;prevHeadYaw:F"
      )
   )
   private float hook2(LivingEntity var1) {
      return var1 == field_4219.player && !RotationManager.field_1538 ? this.rotations().method_521() : var1.prevHeadYaw;
   }

   @Redirect(
      method = {"render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"},
      at = @At(
         value = "FIELD",
         target = "Lnet/minecraft/entity/LivingEntity;bodyYaw:F"
      )
   )
   private float hook3(LivingEntity var1) {
      return var1 == field_4219.player && !RotationManager.field_1538 ? this.rotations().method_517() : var1.bodyYaw;
   }

   @Redirect(
      method = {"render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"},
      at = @At(
         value = "FIELD",
         target = "Lnet/minecraft/entity/LivingEntity;headYaw:F"
      )
   )
   private float hook4(LivingEntity var1) {
      return var1 == field_4219.player && !RotationManager.field_1538 ? this.rotations().method_522() : var1.headYaw;
   }

   @Redirect(
      method = {"render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"},
      at = @At(
         value = "FIELD",
         target = "Lnet/minecraft/entity/LivingEntity;prevPitch:F"
      )
   )
   private float hook5(LivingEntity var1) {
      return var1 == field_4219.player && !RotationManager.field_1538 ? this.rotations().method_519() : var1.prevPitch;
   }

   @Redirect(
      method = {"render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/entity/LivingEntity;getPitch()F"
      )
   )
   private float hook6(LivingEntity var1) {
      return var1 == field_4219.player && !RotationManager.field_1538 ? this.rotations().method_516() : var1.getPitch();
   }

   @Inject(
      method = {"render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"},
      at = {@At("HEAD")}
   )
   private void hook7(T var1, float var2, float var3, MatrixStack var4, VertexConsumerProvider var5, int var6, CallbackInfo var7) {
      this.lastEntity = (T)var1;
   }

   @Inject(
      method = {"render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void render(T var1, float var2, float var3, MatrixStack var4, VertexConsumerProvider var5, int var6, CallbackInfo var7) {
      int var8 = norender.field_760.getValue();
      MinecraftClient var9 = MinecraftClient.getInstance();
      if (var1 instanceof WardenEntity
         && norender.isToggled()
         && norender.field_759.getValue()
         && var1.getBlockPos().getSquaredDistance(var9.gameRenderer.getCamera().getBlockPos()) > (double)(var8 * var8)) {
         var7.cancel();
      }
   }

   @ModifyExpressionValue(
      method = {"getOverlay"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/render/OverlayTexture;getV(Z)I"
      )}
   )
   private static int getV(int var0) {
      return norender.isToggled() && norender.field_747.getValue() ? 10 : var0;
   }

   private RotationManager rotations() {
      return Hub.field_2598;
   }
}
