package me.mioclient.mixin;

import me.mioclient.Hub;
import me.mioclient.module.abstract_.AbstractModule_37;
import net.minecraft.client.gui.tooltip.TooltipBackgroundRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin({TooltipBackgroundRenderer.class})
public class MixinTooltipBackgroundRenderer {
   private static AbstractModule_37 tooltips = Hub.field_2595.method_78(AbstractModule_37.class);

   public MixinTooltipBackgroundRenderer() {
      super();
   }

   @ModifyArgs(
      method = {"render"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/tooltip/TooltipBackgroundRenderer;renderBorder(Lnet/minecraft/client/gui/DrawContext;IIIIIII)V"
      )
   )
   private static void renderHook(Args var0) {
      if (tooltips.isToggled() && tooltips.method_408() != null) {
         var0.set(6, tooltips.method_408().hashCode());
         var0.set(7, tooltips.method_408().darker().hashCode());
         tooltips.method_16(null);
      }
   }
}
