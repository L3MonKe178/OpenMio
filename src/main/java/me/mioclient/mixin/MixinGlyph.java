package me.mioclient.mixin;

import me.mioclient.Hub;
import me.mioclient.module.render.NoRenderModule;
import net.minecraft.client.font.Glyph;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({Glyph.class})
public interface MixinGlyph {
   @Inject(
      method = {"getShadowOffset"},
      at = {@At("HEAD")},
      cancellable = true
   )
   default void getShadowOffsetHook(CallbackInfoReturnable<Float> var1) {
      NoRenderModule var2 = Hub.field_2599.method_78(NoRenderModule.class);
      if (var2.isToggled() && var2.field_729.getValue()) {
         var1.setReturnValue(0.6F);
      } else {
         var1.setReturnValue(1.0F);
      }
   }
}
