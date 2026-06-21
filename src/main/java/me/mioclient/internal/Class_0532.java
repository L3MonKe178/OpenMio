package me.mioclient.internal;

import java.util.List;
import me.mioclient.api.Class_0322;
import me.mioclient.enum_.Class_1172;
import me.mioclient.module.abstract_.AbstractModule_13;
import me.mioclient.record.Class_1183;
import net.minecraft.block.enums.BedPart;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.util.math.Direction.Type;

public class Class_0532 implements Class_0322<LivingEntity, Class_1183> {
   public static final Mutable field_1676 = new Mutable();
   public final AbstractModule_13 field_1677;
   public Direction field_1678;
   public Vec3d field_1679;
   public float field_1492;

   public Class_0532(AbstractModule_13 var1) {
      super();
      this.field_1677 = var1;
   }

   @Override
   public Class_1183 method_35(LivingEntity var1) {
      return this.method_22(var1);
   }

   public Class_1183 method_22(LivingEntity var1) {
      this.field_1492 = this.field_1677.field_3567.getValue();
      Vec3d var2 = var1.getPos();
      float var3 = Float.intBitsToFloat(1073741824);
      Class_1183 var4 = null;

      for (float var5 = -var3; var5 < var3; var5 += Float.intBitsToFloat(1065353216)) {
         for (float var6 = -var3; var6 < Float.intBitsToFloat(1077936128); var6 += Float.intBitsToFloat(1065353216)) {
            for (float var7 = -var3; var7 < var3; var7 += Float.intBitsToFloat(1065353216)) {
               this.field_1679 = null;
               field_1676.set(var2.x + (double)var5, var2.y + (double)var6, var2.z + (double)var7);
               Class_1183 var8 = this.method_2(var1, field_1676);
               if (var8 != null && (var4 == null || var8.method_1039() >= var4.method_1039())) {
                  var4 = var8;
               }
            }
         }
      }

      return var4;
   }

   public Class_1183 method_2(LivingEntity var1, BlockPos var2) {
      if (!field_4219.world.getBlockState(var2).isReplaceable() && !this.field_1677.method_2(var2, BedPart.HEAD)) {
         return null;
      } else {
         BlockPos var3 = null;
         Direction var4 = null;

         for (Direction var6 : Type.HORIZONTAL) {
            BlockPos var7 = var2.offset(var6);
            if (Class_1035.method_9(var7, true, false) || this.field_1677.method_2(var7, BedPart.FOOT)) {
               var4 = this.method_2(var6.getOpposite(), var7, this.field_1677.field_3568.getValue());
               if (var4 != null) {
                  this.field_1678 = var6.getOpposite();
                  var3 = var7;
                  break;
               }
            }
         }

         if (var3 == null) {
            return null;
         } else {
            float var8 = Class_1323.method_9(var2.toCenterPos(), var1, this.field_1677.method_16(var1));
            if (var8 < this.field_1677.field_3587.getValue()) {
               return null;
            } else {
               float var9 = Class_1323.method_9(var2.toCenterPos(), field_4219.player);
               if (var9 > this.field_1677.field_3588.getValue()) {
                  return null;
               } else {
                  if (this.field_1679 == null && !this.field_1677.field_3569.getValue()) {
                     this.field_1679 = var3.toCenterPos().offset(var4, Double.longBitsToDouble(4602678819172646912L));
                  }

                  return new Class_1183(var3, var2.toImmutable(), var8, var9, this.method_2(var4, var3));
               }
            }
         }
      }
   }

   public Direction method_2(Direction var1, BlockPos var2, boolean var3) {
      boolean var4 = this.field_1677.field_3571.getValue();
      Direction[] var5 = Direction.values();
      int var6 = var5.length;
      int var7 = 0;

      Direction var8;
      while (true) {
         if (var7 >= var6) {
            return null;
         }

         var8 = var5[var7];
         BlockPos var9 = var2.offset(var8);
         if (var9.getY() < field_4219.world.getTopY()
            && (Class_1225.method_37(var9) || var4)
            && (!var3 || Class_1035.method_39(var9).contains(var8.getOpposite()))) {
            if (!this.field_1677.field_3569.getValue()) {
               break;
            }

            this.field_1679 = this.method_2(var8, var1, var2);
            if (this.field_1679 != null) {
               break;
            }
         }

         var7++;
      }

      return var8;
   }

   public Vec3d method_2(Direction var1, Direction var2, BlockPos var3) {
      for (Vec3d var5 : this.method_38(var3, var1)) {
         if (!(var5.distanceTo(field_4219.player.getEyePos()) > (double)this.field_1492)) {
            float var6 = RotationManager.method_78(var5)[0];
            Direction var7 = Direction.fromRotation((double)var6);
            if (var2 == var7) {
               return var5;
            }
         }
      }

      return null;
   }

   public List<Vec3d> method_38(BlockPos var1, Direction var2) {
      return var2 == null ? Class_1172.field_3634.method_38(var1) : Class_1172.field_3634.method_2(var1, var2);
   }

   public BlockHitResult method_2(Direction var1, BlockPos var2) {
      if ((!this.field_1677.field_3571.getValue() || Class_1225.method_37(var2.offset(var1)))
         && (!this.field_1677.field_3570.getValue() || !Class_1225.method_17(var2))) {
         return new BlockHitResult(this.field_1679, var1.getOpposite(), var2.offset(var1), false);
      } else {
         this.field_1679 = this.method_2(null, this.field_1678, var2);
         return new BlockHitResult(this.field_1679, var1, var2, false);
      }
   }
}
