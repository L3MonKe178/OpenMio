package me.mioclient.record;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public final class Class_0835 {
   public final BlockPos field_2660;
   public final Direction field_2661;
   public final Class_1073 field_2662;

   public Class_0835(BlockPos var1, Direction var2, Class_1073 var3) {
      super();
      this.field_2660 = var1;
      this.field_2661 = var2;
      this.field_2662 = var3;
   }

   public Class_0835 method_2(Class_0835 var1) {
      if (var1 == null) {
         return this;
      } else {
         double var2 = var1.field_2662.method_445();
         double var4 = this.field_2662.method_445();
         return var2 > var4 ? var1 : this;
      }
   }

   public BlockPos method_406() {
      return this.field_2660;
   }

   public Direction method_457() {
      return this.field_2661;
   }

   public Class_1073 method_773() {
      return this.field_2662;
   }
}
