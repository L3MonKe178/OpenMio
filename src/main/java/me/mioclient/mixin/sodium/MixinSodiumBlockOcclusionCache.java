package me.mioclient.mixin.sodium;

import me.mioclient.module.render.XrayModule;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@Mixin(
   targets = {"me.jellysquid.mods.sodium.client.render.chunk.compile.pipeline.BlockOcclusionCache"},
   remap = false
)
public class MixinSodiumBlockOcclusionCache {
   public MixinSodiumBlockOcclusionCache() {
      super();
   }

   @Inject(
      method = {"shouldDrawSide"},
      at = {@At("HEAD")},
      cancellable = true,
      remap = false
   )
   public void shouldDrawSide(BlockState var1, BlockView var2, BlockPos var3, Direction var4, CallbackInfoReturnable<Boolean> var5) {
      if (XrayModule.method_844().isToggled()) {
         var5.cancel();
         var5.setReturnValue(XrayModule.method_844().method_2(var3, var1.getBlock()));
      }
   }
}
