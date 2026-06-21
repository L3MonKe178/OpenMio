package me.mioclient.record;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public final class Class_1013 {
   public final BlockPos field_3133;
   public final Direction field_3134;
   public final Box field_3135;

   public Class_1013(BlockPos var1, Direction var2, Box var3) {
      super();
      this.field_3133 = var1;
      this.field_3134 = var2;
      this.field_3135 = var3;
   }

   public Vec3d method_908() {
      return this.field_3133.toCenterPos().offset(this.field_3134, Double.longBitsToDouble(4602678819172646912L));
   }

   public BlockPos method_406() {
      return this.field_3133;
   }

   public Direction method_20() {
      return this.field_3134;
   }

   public Box method_172() {
      return this.field_3135;
   }
}
