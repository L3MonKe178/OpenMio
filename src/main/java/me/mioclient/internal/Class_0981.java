package me.mioclient.internal;

import java.util.List;
import me.mioclient.api.Class_1309;
import me.mioclient.enum_.Class_0694;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.RaycastContext.FluidHandling;
import net.minecraft.world.RaycastContext.ShapeType;

public class Class_0981 implements Class_1309 {
   public Class_0981() {
      super();
   }

   public static BlockHitResult method_2(Class_0512 var0) {
      return (BlockHitResult)BlockView.raycast(var0.method_543(), var0.method_544(), var0, (var1, var2) -> {
         BlockState var3 = field_4219.world.getBlockState(var2);
         Class_0694 var4 = var0.method_548().test(var2, var3);
         if (var4 == Class_0694.IGNORE) {
            var3 = Blocks.AIR.getDefaultState();
         } else if (var4 == Class_0694.SOLID) {
            var3 = Blocks.BEDROCK.getDefaultState();
         }

         Vec3d var5 = var1.method_543();
         Vec3d var6 = var1.method_544();
         VoxelShape var7 = var1.method_2(var3, field_4219.world, var2);
         BlockHitResult var8 = field_4219.world.raycastBlock(var5, var6, var2, var7, var3);
         VoxelShape var9 = VoxelShapes.empty();
         BlockHitResult var10 = var9.raycast(var5, var6, var2);
         double var11 = var8 == null ? Double.longBitsToDouble(9218868437227405311L) : var1.method_543().squaredDistanceTo(var8.getPos());
         double var13 = var10 == null ? Double.longBitsToDouble(9218868437227405311L) : var1.method_544().squaredDistanceTo(var10.getPos());
         return var11 <= var13 ? var8 : var10;
      }, var0x -> {
         Vec3d var1 = var0x.method_543().subtract(var0x.method_544());
         return BlockHitResult.createMissed(var0x.method_543(), Direction.getFacing(var1.x, var1.y, var1.z), BlockPos.ofFloored(var0x.method_544()));
      });
   }

   public static HitResult method_2(Vec3d var0, float var1, float var2, float var3) {
      float var4 = var2 * Class_0245.field_690;
      float var5 = -var1 * Class_0245.field_690;
      float var6 = MathHelper.cos(var5);
      float var7 = MathHelper.sin(var5);
      float var8 = MathHelper.cos(var4);
      float var9 = MathHelper.sin(var4);
      Vec3d var10 = new Vec3d((double)(var7 * var8), (double)(-var9), (double)(var6 * var8));
      Vec3d var11 = var0.add(var10.x * (double)var3, var10.y * (double)var3, var10.z * (double)var3);
      return field_4219.world.raycast(new RaycastContext(var0, var11, ShapeType.OUTLINE, FluidHandling.NONE, field_4219.player));
   }

   public static int method_886() {
      BlockHitResult var0 = field_4219.world
         .raycast(
            new RaycastContext(
               field_4219.player.getPos(),
               new Vec3d(field_4219.player.getX(), (double)field_4219.world.getBottomY(), field_4219.player.getZ()),
               ShapeType.COLLIDER,
               FluidHandling.NONE,
               field_4219.player
            )
         );
      return (int)Math.round(field_4219.player.getY() - var0.getPos().y);
   }

   public static int method_887() {
      int var0 = (int)Math.round(field_4219.player.getY()) + 1;
      BlockHitResult var1 = field_4219.world
         .raycast(
            new RaycastContext(
               field_4219.player.getPos(),
               new Vec3d(field_4219.player.getX(), (double)field_4219.world.getTopY(), field_4219.player.getZ()),
               ShapeType.COLLIDER,
               FluidHandling.NONE,
               field_4219.player
            )
         );
      return var1.getBlockPos().getY() - var0 - 1;
   }

   public static boolean method_30(Entity var0) {
      Box var1 = var0.getBoundingBox();

      for (float var2 = 0.0F; var2 <= Float.intBitsToFloat(1065353216); var2 += Float.intBitsToFloat(1056964608)) {
         List var3 = Class_0719.method_2(var1, var1.minY + (var1.maxY - var1.minY) * (double)var2);

         for (int var4 = 0; var4 < var3.size() - 1; var4++) {
            if (method_9(field_4219.player.getEyePos(), (Vec3d)var3.get(var4))) {
               return true;
            }
         }
      }

      return false;
   }

   public static boolean method_9(Vec3d var0, Vec3d var1) {
      return field_4219.world.raycast(new RaycastContext(var0, var1, ShapeType.COLLIDER, FluidHandling.NONE, field_4219.player)).getType() == Type.MISS;
   }
}
