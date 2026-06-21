package me.mioclient.mixin;

import me.mioclient.Hub;
import me.mioclient.module.movement.NoSlowModule;
import me.mioclient.module.render.XrayModule;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({Block.class})
public class MixinBlock {
   private static NoSlowModule noslow = Hub.field_2595.method_78(NoSlowModule.class);

   public MixinBlock() {
      super();
   }

   @Inject(
      method = {"shouldDrawSide"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void onShouldDrawHook(BlockState var0, BlockView var1, BlockPos var2, Direction var3, BlockPos var4, CallbackInfoReturnable<Boolean> var5) {
      if (XrayModule.method_844().isToggled()) {
         var5.cancel();
         var5.setReturnValue(XrayModule.method_844().method_2(var2, var0.getBlock()));
      }
   }

   @Inject(
      method = {"getSlipperiness"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void getSlipperinessHook(CallbackInfoReturnable<Float> var1) {
      if (noslow.isToggled()) {
         Block var2 = (Block)(Object)this;
         if (noslow.field_1695.getValue() && (var2 == Blocks.ICE || var2 == Blocks.PACKED_ICE || var2 == Blocks.BLUE_ICE)) {
            var1.setReturnValue(0.6F);
         }

         if (noslow.field_1696.getValue() && var2 == Blocks.SLIME_BLOCK) {
            var1.setReturnValue(0.6F);
         }

         if (noslow.field_1699.getValue() && var2 == Blocks.HONEY_BLOCK) {
            var1.setReturnValue(0.6F);
         }
      }
   }
}
