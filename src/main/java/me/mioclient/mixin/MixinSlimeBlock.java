package me.mioclient.mixin;

import me.mioclient.Hub;
import me.mioclient.module.movement.NoSlowModule;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlimeBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({SlimeBlock.class})
public class MixinSlimeBlock {
   private static NoSlowModule noslow = Hub.field_2595.method_78(NoSlowModule.class);

   public MixinSlimeBlock() {
      super();
   }

   @Inject(
      method = {"onSteppedOn"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onSteppedOnHook(World var1, BlockPos var2, BlockState var3, Entity var4, CallbackInfo var5) {
      if (noslow.isToggled() && noslow.field_1696.getValue()) {
         var5.cancel();
      }
   }

   @Inject(
      method = {"bounce"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void bounceHook(Entity var1, CallbackInfo var2) {
      if (noslow.isToggled() && noslow.field_1696.getValue() && noslow.field_1697.getValue()) {
         var2.cancel();
      }
   }
}
