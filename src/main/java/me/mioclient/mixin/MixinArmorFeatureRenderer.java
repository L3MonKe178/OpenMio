package me.mioclient.mixin;

import java.awt.Color;
import me.mioclient.Hub;
import me.mioclient.internal.Class_0392;
import me.mioclient.internal.Class_1081;
import me.mioclient.module.render.ChamsModule;
import me.mioclient.module.render.NoRenderModule;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin({ArmorFeatureRenderer.class})
public class MixinArmorFeatureRenderer {
   private static final NoRenderModule norender = Hub.field_2595.method_78(NoRenderModule.class);
   private static final ChamsModule chams = Hub.field_2595.method_78(ChamsModule.class);
   @Unique
   private LivingEntity mio$lastEntity;

   public MixinArmorFeatureRenderer() {
      super();
   }

   @Inject(
      method = {"render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/LivingEntity;FFFFFF)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private <T extends LivingEntity> void renderHook(
      MatrixStack var1,
      VertexConsumerProvider var2,
      int var3,
      T var4,
      float var5,
      float var6,
      float var7,
      float var8,
      float var9,
      float var10,
      CallbackInfo var11
   ) {
      this.mio$lastEntity = var4;
      if (norender.method_283() == 0.0F) {
         var11.cancel();
      }
   }

   @ModifyArgs(
      method = {"renderArmorParts"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/render/entity/model/BipedEntityModel;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;III)V"
      )
   )
   private void renderArmorParts(Args var1) {
      Color var2 = new Color((Integer)var1.get(4));
      float var3 = norender.method_283() * norender.method_284();
      var1.set(4, Class_1081.method_2(var2, var3).hashCode());
   }

   @Redirect(
      method = {"renderArmorParts"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/render/RenderLayer;getArmorCutoutNoCull(Lnet/minecraft/util/Identifier;)Lnet/minecraft/client/render/RenderLayer;"
      )
   )
   private RenderLayer renerArmorParts(Identifier var1) {
      float var2 = norender.method_283() * norender.method_284();
      if (chams.method_100(this.mio$lastEntity)) {
         var2 *= (float)chams.field_228.getValue().intValue();
      }

      return var2 == 1.0F ? RenderLayer.getArmorCutoutNoCull(var1) : Class_0392.method_2(var1);
   }
}
