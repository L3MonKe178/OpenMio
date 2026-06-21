package me.mioclient.internal;

import java.util.List;
import me.mioclient.record.Class_0146;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;

public class Class_1161 extends Class_0035 {
   public static final List<Class_0146> field_3597 = List.of(
      Class_0146.method_2(
         Direction.EAST,
         Block.createCuboidShape(
            0.0,
            Double.longBitsToDouble(4611686018427387904L),
            Double.longBitsToDouble(4611686018427387904L),
            Double.longBitsToDouble(4607182418800017408L),
            Double.longBitsToDouble(4624070917402656768L),
            Double.longBitsToDouble(4624070917402656768L)
         )
      ),
      Class_0146.method_2(
         Direction.WEST,
         Block.createCuboidShape(
            Double.longBitsToDouble(4624633867356078080L),
            Double.longBitsToDouble(4611686018427387904L),
            Double.longBitsToDouble(4611686018427387904L),
            Double.longBitsToDouble(4625196817309499392L),
            Double.longBitsToDouble(4624070917402656768L),
            Double.longBitsToDouble(4624070917402656768L)
         )
      ),
      Class_0146.method_2(
         Direction.NORTH,
         Block.createCuboidShape(
            Double.longBitsToDouble(4611686018427387904L),
            Double.longBitsToDouble(4611686018427387904L),
            0.0,
            Double.longBitsToDouble(4624070917402656768L),
            Double.longBitsToDouble(4624070917402656768L),
            Double.longBitsToDouble(4607182418800017408L)
         )
      ),
      Class_0146.method_2(
         Direction.SOUTH,
         Block.createCuboidShape(
            Double.longBitsToDouble(4611686018427387904L),
            Double.longBitsToDouble(4611686018427387904L),
            Double.longBitsToDouble(4624633867356078080L),
            Double.longBitsToDouble(4624070917402656768L),
            Double.longBitsToDouble(4624070917402656768L),
            Double.longBitsToDouble(4625196817309499392L)
         )
      ),
      Class_0146.method_2(
         Direction.DOWN,
         Block.createCuboidShape(
            Double.longBitsToDouble(4611686018427387904L),
            0.0,
            Double.longBitsToDouble(4611686018427387904L),
            Double.longBitsToDouble(4624070917402656768L),
            Double.longBitsToDouble(4607182418800017408L),
            Double.longBitsToDouble(4624070917402656768L)
         )
      ),
      Class_0146.method_2(
         Direction.UP,
         Block.createCuboidShape(
            Double.longBitsToDouble(4611686018427387904L),
            Double.longBitsToDouble(4624633867356078080L),
            Double.longBitsToDouble(4611686018427387904L),
            Double.longBitsToDouble(4624070917402656768L),
            Double.longBitsToDouble(4625196817309499392L),
            Double.longBitsToDouble(4624070917402656768L)
         )
      )
   );

   public Class_1161() {
      super();
   }

   @Override
   public Box method_5(BlockPos var1, Direction var2) {
      for (Class_0146 var4 : field_3597) {
         if (var4.method_20() == var2.getOpposite()) {
            return var4.method_172().offset(var1);
         }
      }

      return new Box(var1);
   }
}
