package me.mioclient.record;

import me.mioclient.api.Class_1309;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public final class Class_1003 implements Class_1309 {
   public final BlockState field_3092;
   public final BlockPos field_3093;
   public final long field_3094;

   public Class_1003(BlockState var1, BlockPos var2, long var3) {
      super();
      this.field_3092 = var1;
      this.field_3093 = var2;
      this.field_3094 = var3;
   }

   public static Class_1003 method_340(BlockPos var0) {
      return new Class_1003(field_4219.world.getBlockState(var0), var0, System.currentTimeMillis());
   }

   public boolean method_644() {
      return System.currentTimeMillis() - this.field_3094 >= 500L;
   }

   public BlockState method_407() {
      return this.field_3092;
   }

   public BlockPos method_406() {
      return this.field_3093;
   }

   public long method_901() {
      return this.field_3094;
   }
}
