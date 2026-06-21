package me.mioclient.record;

import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;

public final class Class_0146 {
   public final Direction field_422;
   public final Box field_423;

   public Class_0146(Direction var1, Box var2) {
      super();
      this.field_422 = var1;
      this.field_423 = var2;
   }

   public static Class_0146 method_2(Direction var0, VoxelShape var1) {
      return new Class_0146(var0.getOpposite(), var1.getBoundingBox());
   }

   public Direction method_20() {
      return this.field_422;
   }

   public Box method_172() {
      return this.field_423;
   }
}
