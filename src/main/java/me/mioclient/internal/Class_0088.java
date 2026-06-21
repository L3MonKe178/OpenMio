package me.mioclient.internal;

import java.util.List;
import me.mioclient.api.Class_0742;
import me.mioclient.api.Class_1309;
import me.mioclient.module.combat.AntiPhaseModule;
import me.mioclient.record.Class_0146;
import me.mioclient.record.Class_1013;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;

public class Class_0088 implements Class_1309, Class_0742 {
   private static final VoxelShape EAST_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 3.0, 16.0, 16.0);
   private static final VoxelShape WEST_SHAPE = Block.createCuboidShape(13.0, 0.0, 0.0, 16.0, 16.0, 16.0);
   private static final VoxelShape SOUTH_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 3.0);
   private static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(0.0, 0.0, 13.0, 16.0, 16.0, 16.0);
   public static final List<Class_0146> field_297 = List.of(
      Class_0146.method_2(Direction.EAST, EAST_SHAPE),
      Class_0146.method_2(Direction.WEST, WEST_SHAPE),
      Class_0146.method_2(Direction.NORTH, NORTH_SHAPE),
      Class_0146.method_2(Direction.SOUTH, SOUTH_SHAPE)
   );

   public Class_0088() {
      super();
   }

   @Override
   public Class_1013 method_2(AntiPhaseModule var1, PlayerEntity var2) {
      BlockPos var3 = Class_0382.method_9(var2);
      Direction var4 = null;
      Box var5 = null;
      float var6 = Float.intBitsToFloat(1148829696);
      if (!Class_1225.method_14(var3)) {
         return null;
      } else {
         for (Class_0146 var8 : field_297) {
            Direction var9 = var8.method_20();
            BlockPos var10 = var3.offset(var9);
            if (!this.method_2(var3, var2, var8) && (!var1.field_848.getValue() || !this.method_7(var10, var9))) {
               float var11 = (float)var2.getPos().squaredDistanceTo(var10.toCenterPos());
               if (var11 < var6) {
                  var5 = var8.method_172();
                  var4 = var9;
                  var6 = var11;
               }
            }
         }

         return var4 == null ? null : new Class_1013(var3.offset(var4), var4.getOpposite(), var5.offset(var3));
      }
   }

   public boolean method_2(BlockPos var1, PlayerEntity var2, Class_0146 var3) {
      Direction var4 = var3.method_20();
      BlockPos var5 = var1.offset(var4);
      return Class_0719.method_4(var2).intersects(var3.method_172().offset(var1)) || field_4219.world.getBlockState(var5).isReplaceable();
   }

   public boolean method_7(BlockPos var1, Direction var2) {
      List var3 = Class_1035.method_39(var1);
      return !var3.contains(var2.getOpposite());
   }
}
