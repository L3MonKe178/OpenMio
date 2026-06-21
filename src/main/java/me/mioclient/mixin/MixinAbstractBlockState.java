package me.mioclient.mixin;

import me.mioclient.module.render.XrayModule;
import net.minecraft.block.AbstractBlock.AbstractBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({AbstractBlockState.class})
public class MixinAbstractBlockState {
   public MixinAbstractBlockState() {
      super();
   }

   @Inject(
      method = {"getAmbientOcclusionLightLevel"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void getAmbientOcclusionLightLevelHook(BlockView var1, BlockPos var2, CallbackInfoReturnable<Float> var3) {
      if (XrayModule.method_844().isToggled()) {
         var3.cancel();
         var3.setReturnValue(1.0F);
      }
   }
}
