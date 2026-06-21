package me.mioclient.internal;

import java.util.Objects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

public class Class_0919 {
   public final boolean field_2899;
   public Box field_1887;

   public Class_0919(BlockPos var1, boolean var2) {
      this(new Box(var1), var2);
   }

   public Class_0919(Box var1, boolean var2) {
      super();
      this.field_1887 = var1;
      this.field_2899 = var2;
   }

   public double method_832() {
      return Math.max(Math.max(this.field_1887.getLengthX(), this.field_1887.getLengthZ()), this.field_1887.getLengthY());
   }

   public boolean method_9(Class_0919 var1) {
      if (this.field_2899 != var1.field_2899) {
         return false;
      } else {
         Box var2 = var1.field_1887;
         if (this.field_2899) {
            if (var2.minY != this.field_1887.maxY && var2.maxY != this.field_1887.minY) {
               return false;
            } else {
               boolean var5 = this.field_1887.minX == var2.minX && this.field_1887.maxX == var2.maxX;
               boolean var6 = this.field_1887.minZ == var2.minZ && this.field_1887.maxZ == var2.maxZ;
               return var5 && var6;
            }
         } else if (var2.minY != this.field_1887.minY) {
            return false;
         } else {
            boolean var3 = this.field_1887.minX == var2.maxX || this.field_1887.maxX == var2.minX;
            boolean var4 = this.field_1887.minZ == var2.maxZ || this.field_1887.maxZ == var2.minZ;
            if (var3) {
               return this.field_1887.minZ == var2.minZ && this.field_1887.maxZ == var2.maxZ;
            } else {
               return !var4 ? false : this.field_1887.minX == var2.minX && this.field_1887.maxX == var2.maxX;
            }
         }
      }
   }

   public void method_833(BlockPos var1) {
      this.field_1887 = this.field_1887.union(new Box(var1));
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.field_1887, this.field_2899);
   }

   @Override
   public boolean equals(Object var1) {
      return !(var1 instanceof Class_0919 var2) ? false : var2.field_1887.equals(this.field_1887) && this.field_2899 == var2.field_2899;
   }
}
