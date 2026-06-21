package me.mioclient.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import me.mioclient.Hub;
import me.mioclient.api.MioAPI;
import me.mioclient.record.Class_0944;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Direction.Axis;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class Class_0308 implements MioAPI {
   public static final float field_995 = Float.intBitsToFloat(-1113550802);
   public static final float field_996 = Class_0464.field_1470;
   public static final float field_997 = field_996 * Float.intBitsToFloat(1050253722);
   public static final float field_998 = field_996 * Float.intBitsToFloat(1068708659) + Float.intBitsToFloat(1045220557);
   public final List<Box> field_999 = new ArrayList<>();
   public final PlayerEntity field_1000;

   public Class_0308(PlayerEntity var1) {
      super();
      this.field_1000 = var1;
   }

   public void method_142() {
      synchronized (this.field_999) {
         this.field_999.clear();
         double var2 = this.field_1000.getX() - this.field_1000.prevX;
         double var4 = this.field_1000.getY() - this.field_1000.prevY;
         double var6 = this.field_1000.getZ() - this.field_1000.prevZ;
         double var8 = Math.hypot(var2, var6);
         double var10 = MathHelper.clamp(var8, this.field_1000.isSneaking() ? (double)field_997 : (double)field_996, (double)field_998);
         if (var8 != 0.0) {
            var2 = var2 / var8 * var10;
            var6 = var6 / var8 * var10;
         }

         if (Class_0396.method_2(this.field_1000) && var4 > 0.0) {
            var4 = Double.longBitsToDouble(-4633058300752035840L);
         }

         Box var12 = Class_0719.method_4(this.field_1000);
         if (var4 > 0.0) {
            var4 = method_7(var4);
         }

         if (var8 < (double)Class_0464.field_1471) {
            var2 = 0.0;
            var6 = 0.0;
         }

         if (var8 < (double)Class_0464.field_1471 && Math.abs(var4) < Double.longBitsToDouble(4591870180066957722L)) {
            this.field_999.addAll(Collections.nCopies(20, var12));
         } else {
            for (int var13 = 0; var13 <= 20; var13++) {
               boolean var14 = this.method_2(this.field_1000, var12.offset(0.0, Double.longBitsToDouble(-4646453807550688133L), 0.0));
               if (!var14) {
                  var4 = method_7(var4);
               } else {
                  var4 = Double.longBitsToDouble(-4633058300752035840L);
               }

               List var15 = field_4219.world.getEntityCollisions(this.field_1000, var12.stretch(var2, var4, var6));
               ArrayList var16 = new ArrayList();
               synchronized (Hub.field_2622.method_345()) {
                  for (Entry var19 : Hub.field_2622.method_345().entrySet()) {
                     Class_0944 var20 = (Class_0944)var19.getValue();
                     var16.add(var20.method_862().getDefaultState().getOutlineShape(field_4219.world, (BlockPos)var19.getKey()));
                  }
               }

               var16.addAll(Entity.findCollisionsForMovement(this.field_1000, field_4219.world, var15, var12.stretch(var2, var4, var6)));
               var12 = method_2(var12, var16, var2, var4, var6);
               this.field_999.add(var12);
            }
         }
      }
   }

   public static double method_7(double var0) {
      return (var0 + Double.longBitsToDouble(4590429028186199163L)) * Double.longBitsToDouble(4607002274814922588L);
   }

   public static Box method_2(Box var0, List<VoxelShape> var1, double var2, double var4, double var6) {
      if (var1.isEmpty()) {
         return var0.offset(var2, var4, var6);
      } else {
         Box var8 = var0;
         if (var4 != 0.0) {
            var4 = VoxelShapes.calculateMaxOffset(Axis.Y, var0, var1, var4);
            if (var4 != 0.0) {
               var0 = var0.offset(0.0, var4, 0.0);
            }
         }

         boolean var9 = Math.abs(var2) < Math.abs(var6);
         if (var9 && var6 != 0.0) {
            var6 = VoxelShapes.calculateMaxOffset(Axis.Z, var0, var1, var6);
            if (var6 != 0.0) {
               var0 = var0.offset(0.0, 0.0, var6);
            }
         }

         if (var2 != 0.0) {
            var2 = VoxelShapes.calculateMaxOffset(Axis.X, var0, var1, var2);
            if (!var9 && var2 != 0.0) {
               var0 = var0.offset(var2, 0.0, 0.0);
            }
         }

         if (!var9 && var6 != 0.0) {
            var6 = VoxelShapes.calculateMaxOffset(Axis.Z, var0, var1, var6);
         }

         return var8.offset(var2, var4, var6);
      }
   }

   public boolean method_2(PlayerEntity var1, Box var2) {
      return field_4219.world.getBlockCollisions(var1, var2).iterator().hasNext();
   }

   public synchronized List<Box> method_359() {
      return this.field_999;
   }
}
