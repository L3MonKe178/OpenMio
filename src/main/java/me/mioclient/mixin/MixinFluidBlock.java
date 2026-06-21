package me.mioclient.mixin;

import me.mioclient.Hub;
import me.mioclient.module.exploit.ReachModule;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.BlockItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({FluidBlock.class})
public class MixinFluidBlock {
   private static ReachModule reach = Hub.field_2595.method_78(ReachModule.class);

   public MixinFluidBlock() {
      super();
   }

   @Inject(
      method = {"getOutlineShape"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void getOutlineShapeHook(BlockState var1, BlockView var2, BlockPos var3, ShapeContext var4, CallbackInfoReturnable<VoxelShape> var5) {
      MinecraftClient var6 = MinecraftClient.getInstance();
      if (reach.isToggled()
         && reach.field_3337.getValue()
         && (var6.player.getMainHandStack().getItem() instanceof BlockItem || var6.player.getOffHandStack().getItem() instanceof BlockItem)) {
         boolean var7 = false;

         for (Direction var11 : Direction.values()) {
            if (var2.getBlockState(var3.offset(var11)).isAir()) {
               var7 = true;
               break;
            }
         }

         double var12 = MinecraftClient.getInstance().gameRenderer.getCamera().getPos().squaredDistanceTo(var3.toCenterPos());
         if (var12 < (double)(reach.field_3336.getValue() * reach.field_3336.getValue()) && !var7) {
            return;
         }

         var5.setReturnValue(VoxelShapes.fullCube());
         var5.cancel();
      }
   }
}
