package me.mioclient.record;

import me.mioclient.api.Class_1309;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;

public final class Class_0773 implements Class_1309 {
   public final Direction field_2429;
   public final Vec3d field_2430;
   public final VoxelShape field_2431;

   public Class_0773(Direction var1, Vec3d var2, VoxelShape var3) {
      super();
      this.field_2429 = var1;
      this.field_2430 = var2;
      this.field_2431 = var3;
   }

   public static Class_0773 method_2(Class_0407 var0, boolean var1) {
      Direction var2 = var0.method_457();
      Vec3d var3 = var1 ? method_492(var0.method_406().offset(var2)) : var0.method_406().offset(var2).toCenterPos();
      VoxelShape var4 = field_4219.world.getBlockState(var0.method_406().offset(var2)).getCollisionShape(field_4219.world, BlockPos.ORIGIN);
      return new Class_0773(var2, var3, var4);
   }

   public static Vec3d method_492(BlockPos var0) {
      return new Vec3d(
         MathHelper.clamp(field_4219.player.getX(), (double)var0.getX(), (double)(var0.getX() + 1)),
         (double)var0.getY(),
         MathHelper.clamp(field_4219.player.getZ(), (double)var0.getZ(), (double)(var0.getZ() + 1))
      );
   }

   public Direction method_457() {
      return this.field_2429;
   }

   public Vec3d method_564() {
      return this.field_2430;
   }

   public VoxelShape method_721() {
      return this.field_2431;
   }
}
