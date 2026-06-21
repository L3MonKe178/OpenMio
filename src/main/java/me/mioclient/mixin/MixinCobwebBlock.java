package me.mioclient.mixin;

import me.mioclient.Hub;
import me.mioclient.module.movement.FastWebModule;
import net.minecraft.block.BlockState;
import net.minecraft.block.CobwebBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({CobwebBlock.class})
public class MixinCobwebBlock {
   private static final FastWebModule fastweb = Hub.field_2595.method_78(FastWebModule.class);

   public MixinCobwebBlock() {
      super();
   }

   @Inject(
      method = {"onEntityCollision"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onEntityCollisionHook(BlockState var1, World var2, BlockPos var3, Entity var4, CallbackInfo var5) {
      if (fastweb.method_726()) {
         var5.cancel();
      }
   }
}
