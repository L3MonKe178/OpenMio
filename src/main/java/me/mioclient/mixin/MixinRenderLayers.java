package me.mioclient.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import me.mioclient.Hub;
import me.mioclient.module.render.NoRenderModule;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.TexturedRenderLayers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({RenderLayers.class})
public class MixinRenderLayers {
   private static NoRenderModule norender = Hub.field_2595.method_78(NoRenderModule.class);

   public MixinRenderLayers() {
      super();
   }

   @Inject(
      method = {"getEntityBlockLayer"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void getEntityBlockLayer(BlockState var0, boolean var1, CallbackInfoReturnable<RenderLayer> var2) {
      if (norender.method_282() != 1.0F && RenderSystem.getShaderColor()[3] != 1.0F) {
         var2.setReturnValue(TexturedRenderLayers.getEntityTranslucentCull());
         var2.cancel();
      }
   }
}
