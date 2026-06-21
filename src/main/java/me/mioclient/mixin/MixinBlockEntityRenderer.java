package me.mioclient.mixin;

import me.mioclient.internal.Class_1355;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({BlockEntityRenderer.class})
public interface MixinBlockEntityRenderer {
   @Inject(
      method = {"getRenderDistance"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void getRenderDistanceHook(CallbackInfoReturnable<Integer> var1) {
      if (Class_1355.field_4422) {
         var1.setReturnValue(1024);
      }
   }
}
