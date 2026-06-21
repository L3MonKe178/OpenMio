package me.mioclient.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import me.mioclient.Hub;
import me.mioclient.module.render.NoRenderModule;
import me.mioclient.module.render.SkyColorModule;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.BackgroundRenderer.FogType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({BackgroundRenderer.class})
public class MixinBackgroundRenderer {
   private static NoRenderModule norender = Hub.field_2595.method_78(NoRenderModule.class);
   private static SkyColorModule skycolor = Hub.field_2595.method_78(SkyColorModule.class);

   public MixinBackgroundRenderer() {
      super();
   }

   @Inject(
      method = {"applyFog"},
      at = {@At("TAIL")}
   )
   private static void onApplyFog(Camera var0, FogType var1, float var2, boolean var3, float var4, CallbackInfo var5) {
      if (norender.isToggled() && norender.field_739.getValue() && (var1 == FogType.FOG_TERRAIN || norender.field_741.getValue())) {
         RenderSystem.setShaderFogStart(0.0F);
         RenderSystem.setShaderFogEnd(var2 * norender.field_740.getValue());
      }

      if (skycolor.isToggled() && skycolor.method_125()) {
         Color var6 = skycolor.field_316.getValue();
         RenderSystem.setShaderFogColor(
            (float)var6.getRed() / 255.0F, (float)var6.getGreen() / 255.0F, (float)var6.getBlue() / 255.0F, (float)var6.getAlpha() / 255.0F
         );
      }
   }
}
