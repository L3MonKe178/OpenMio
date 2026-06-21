package me.mioclient.mixin;

import me.mioclient.Hub;
import me.mioclient.api.Class_1309;
import me.mioclient.enum_.Class_0574;
import me.mioclient.event.Event_27;
import me.mioclient.internal.Class_1355;
import me.mioclient.module.render.AmbienceModule;
import me.mioclient.module.render.NameTagsModule;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({EntityRenderer.class})
public abstract class MixinEntityRenderer<T extends Entity> {
   private static NameTagsModule nametags = Hub.field_2595.method_78(NameTagsModule.class);
   private static AmbienceModule ambience = Hub.field_2595.method_78(AmbienceModule.class);

   public MixinEntityRenderer() {
      super();
   }

   @Inject(
      method = {"renderLabelIfPresent"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onRenderLabel(T var1, Text var2, MatrixStack var3, VertexConsumerProvider var4, int var5, float var6, CallbackInfo var7) {
      if (Class_1355.field_4422) {
         var7.cancel();
      } else {
         Event_27 var8 = new Event_27(var3, var1);
         Class_1309.field_4220.method_36(var8);
         if (var8.method_464()) {
            var7.cancel();
         }
      }
   }

   @Inject(
      method = {"hasLabel"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void hasLabelHook(T var1, CallbackInfoReturnable<Boolean> var2) {
      if (var1 instanceof PlayerEntity && nametags.isToggled()) {
         var2.cancel();
         var2.setReturnValue(var1.hasCustomName());
      }
   }

   @Inject(
      method = {"getSkyLight"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void getSkyLightHook(CallbackInfoReturnable<Integer> var1) {
      if (ambience.isToggled() && ambience.field_207.getValue() == Class_0574.SKY) {
         var1.setReturnValue(Math.max(ambience.field_209.getValue(), var1.getReturnValueI()));
      }
   }
}
