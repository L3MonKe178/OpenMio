package me.mioclient.internal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import me.mioclient.api.MioAPI;
import me.mioclient.mixin.ducks.DuckLivingEntity;
import me.mioclient.setting.Setting;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class Class_0719 implements MioAPI {
   public static double field_2280 = Double.longBitsToDouble(4589168020290535424L);

   public Class_0719() {
      super();
   }

   public static Vec3d method_2(Box var0) {
      return new Vec3d(MathHelper.lerp(Constants.field_688, var0.minX, var0.maxX), var0.minY, MathHelper.lerp(Constants.field_688, var0.minZ, var0.maxZ));
   }

   public static Vec3d method_9(Box var0) {
      return method_2(var0).add(0.0, var0.getLengthY() * Double.longBitsToDouble(4598175219545276416L), 0.0);
   }

   public static Vec3d method_5(Box var0) {
      return new Vec3d(MathHelper.lerp(Constants.field_688, var0.minX, var0.maxX), var0.maxY, MathHelper.lerp(Constants.field_688, var0.minZ, var0.maxZ));
   }

   public static Box method_2(Box var0, Vec3d var1) {
      double var2 = var0.getLengthX() * Constants.field_688;
      double var4 = var0.getLengthY() * Constants.field_688;
      double var6 = var0.getLengthZ() * Constants.field_688;
      return new Box(var1.x - var2, var1.y - var4, var1.z - var6, var1.x + var2, var1.y + var4, var1.z + var6);
   }

   public static Box method_2(Box var0, BlockPos var1) {
      return method_2(
         var0,
         new Vec3d(
            (double)var1.getX() + Constants.field_688,
            (double)var1.getY() + var0.getLengthY() * Constants.field_688,
            (double)var1.getZ() + Constants.field_688
         )
      );
   }

   public static Box method_34(BlockPos var0) {
      BlockState var1 = field_4219.world.getBlockState(var0);
      if (!var1.isAir() && !var1.isOf(Blocks.AIR)) {
         VoxelShape var2 = var1.getOutlineShape(field_4219.world, var0);
         if (var2.isEmpty()) {
            var2 = VoxelShapes.cuboid(
               0.0,
               0.0,
               0.0,
               Double.longBitsToDouble(4607182418800017408L),
               Double.longBitsToDouble(4607182418800017408L),
               Double.longBitsToDouble(4607182418800017408L)
            );
         }

         return var2.getBoundingBox().offset(var0);
      } else {
         return new Box(var0);
      }
   }

   public static List<Vec3d> method_2(Box var0, double var1) {
      ArrayList<Vec3d> var3 = new ArrayList<>(
         List.of(
            new Vec3d(var0.minX, var1, var0.minZ),
            new Vec3d(var0.maxX, var1, var0.minZ),
            new Vec3d(var0.minX, var1, var0.maxZ),
            new Vec3d(var0.maxX, var1, var0.maxZ)
         )
      );
      var3.sort(Comparator.comparing((Vec3d var0x) -> field_4219.player.getEyePos().squaredDistanceTo(var0x)));
      return var3;
   }

   public static boolean method_7(Box var0) {
      return var0.getLengthZ() == Double.longBitsToDouble(4607182418800017408L)
         && var0.getLengthY() == Double.longBitsToDouble(4607182418800017408L)
         && var0.getLengthX() == Double.longBitsToDouble(4607182418800017408L);
   }

   public static boolean method_2(VoxelShape var0) {
      return var0.isEmpty() ? false : method_7(var0.getBoundingBox());
   }

   public static int method_2(PlayerEntity var0, Setting<Integer> var1) {
      if (var1.method_468()) {
         double var2 = Math.hypot(var0.getX() - var0.prevX, var0.getZ() - var0.prevZ)
            * Double.longBitsToDouble(4626322717216342016L)
            * Double.longBitsToDouble(4615288898129284301L);
         if (var2 < Double.longBitsToDouble(4613937818241073152L)) {
            return 0;
         } else if (var2 <= Double.longBitsToDouble(4621819117588971520L)) {
            return 1;
         } else if (var2 <= Double.longBitsToDouble(4626322717216342016L)) {
            return 3;
         } else {
            return var2 <= Double.longBitsToDouble(4629137466983448576L) ? 4 : 5;
         }
      } else {
         return (Integer)var1.getValue();
      }
   }

   public static Vec3d method_9(Vec3d var0, Box var1) {
      return new Vec3d(
         MathHelper.clamp(var0.getX(), var1.minX, var1.maxX),
         MathHelper.clamp(var0.getY(), var1.minY, var1.maxY),
         MathHelper.clamp(var0.getZ(), var1.minZ, var1.maxZ)
      );
   }

   public static Box method_2(Entity var0, float var1) {
      double var2 = MathHelper.lerp((double)var1, var0.lastRenderX, var0.getX()) - var0.getX();
      double var4 = MathHelper.lerp((double)var1, var0.lastRenderY, var0.getY()) - var0.getY();
      double var6 = MathHelper.lerp((double)var1, var0.lastRenderZ, var0.getZ()) - var0.getZ();
      Box var8 = var0.getBoundingBox();
      return var8.offset(var2, var4, var6);
   }

   public static Box method_4(LivingEntity var0) {
      if (var0 instanceof Class_0922) {
         return var0.getBoundingBox();
      } else {
         Box var1 = var0.getBoundingBox();
         DuckLivingEntity var2 = (DuckLivingEntity)var0;
         Vec3d var3 = new Vec3d(var2.mio$getServerX(), var2.mio$getServerY(), var2.mio$getServerZ());
         if (var3.lengthSquared() == 0.0) {
            return var0.getBoundingBox();
         } else {
            double var4 = var1.getLengthX() / Double.longBitsToDouble(4611686018427387904L);
            double var6 = var1.getLengthZ() / Double.longBitsToDouble(4611686018427387904L);
            var1 = new Box(-var4, 0.0, -var6, var4, var1.getLengthY(), var6);
            return var1.offset(var3);
         }
      }
   }

   public static boolean method_2(Box var0, Box var1) {
      Box var2 = var0.intersection(var1);
      float var3 = (float)Math.min(var2.getLengthX(), Math.min(var2.getLengthY(), var2.getLengthZ()));
      return (double)var3 > Double.longBitsToDouble(4502148214488346440L);
   }
}
