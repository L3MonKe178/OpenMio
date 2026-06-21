package me.mioclient.record;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;

public final class Class_1287 {
   public final int field_4166;
   public final int field_4167;
   public static final int field_4168 = 9;

   public Class_1287(int var1, int var2) {
      super();
      this.field_4166 = var1;
      this.field_4167 = var2;
   }

   public static Class_1287 method_9(ChunkPos var0) {
      return new Class_1287(var0.getStartX() >> 9, var0.getStartZ() >> 9);
   }

   public static Class_1287 method_535(BlockPos var0) {
      return new Class_1287(var0.getX() >> 9, var0.getZ() >> 9);
   }

   public Class_1287 method_30(int var1, int var2) {
      return new Class_1287(this.method_1149() + var1, this.method_1150() + var2);
   }

   @Override
   public int hashCode() {
      long var1 = 17L;
      var1 = 31L * var1 + (long)this.field_4166;
      var1 = 31L * var1 + (long)this.field_4167;
      return (int)var1;
   }

   public int method_1149() {
      return this.field_4166;
   }

   public int method_1150() {
      return this.field_4167;
   }
}
