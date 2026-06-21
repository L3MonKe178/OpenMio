package me.mioclient.mixin;

import me.mioclient.Hub;
import me.mioclient.module.render.NoRenderModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({InGameOverlayRenderer.class})
public class MixinInGameOverlayRenderer {
   private static NoRenderModule norender = Hub.field_2595.method_78(NoRenderModule.class);

   public MixinInGameOverlayRenderer() {
      super();
   }

   @Inject(
      method = {"renderFireOverlay"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void onRenderFireOverlay(MinecraftClient var0, MatrixStack var1, CallbackInfo var2) {
      if (norender.isToggled() && norender.field_750.getValue()) {
         var2.cancel();
      }
   }

   @Inject(
      method = {"renderUnderwaterOverlay"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void onRenderUnderwaterOverlay(MinecraftClient var0, MatrixStack var1, CallbackInfo var2) {
      if (norender.isToggled() && norender.field_720.getValue()) {
         var2.cancel();
      }
   }

   @Inject(
      method = {"renderInWallOverlay"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void render(Sprite var0, MatrixStack var1, CallbackInfo var2) {
      if (norender.isToggled() && norender.field_720.getValue()) {
         var2.cancel();
      }
   }
}
