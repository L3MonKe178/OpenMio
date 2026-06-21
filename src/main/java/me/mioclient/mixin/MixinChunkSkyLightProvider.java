package me.mioclient.mixin;

import me.mioclient.Hub;
import me.mioclient.module.render.NoRenderModule;
import net.minecraft.world.chunk.light.ChunkSkyLightProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({ChunkSkyLightProvider.class})
public class MixinChunkSkyLightProvider {
   private static NoRenderModule norender = Hub.field_2595.method_78(NoRenderModule.class);

   public MixinChunkSkyLightProvider() {
      super();
   }

   @Inject(
      at = {@At("HEAD")},
      method = {"method_51585"},
      cancellable = true
   )
   private void recalculateLevelhook(int var1, int var2, int var3, CallbackInfoReturnable<Integer> var4) {
      if (norender.isToggled() && norender.field_738.getValue()) {
         var4.setReturnValue(15);
         var4.cancel();
      }
   }
}
