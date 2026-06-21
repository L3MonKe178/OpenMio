package me.mioclient.internal;

import me.mioclient.Hub;
import me.mioclient.api.Class_0415;
import me.mioclient.api.Class_1309;
import net.minecraft.block.AirBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

public class Class_0794 implements Class_1309 {
   public boolean field_2498;
   public boolean field_2499 = true;
   public boolean field_2500;
   public boolean field_2501 = true;
   public boolean field_2502;
   public boolean field_2503;
   public boolean field_2504;
   public boolean field_2505;
   public int field_2506;
   public float field_2507 = Float.intBitsToFloat(1086324736);

   public Class_0794() {
      super();
   }

   public boolean method_185(BlockPos var1) {
      if (!this.field_2504 && !Class_1225.method_36(var1)) {
         return false;
      } else {
         BlockPos var2 = var1.add(0, 1, 0);
         Block var3 = field_4219.world.getBlockState(var2).getBlock();
         boolean var4 = var3 instanceof AirBlock || var2.equals(Hub.field_2622.method_344());
         if (this.field_2501) {
            if (!var4 && var3 != Blocks.FIRE) {
               return false;
            }
         } else if (!var4) {
            return false;
         }

         if (this.field_2498 && !(field_4219.world.getBlockState(var1.add(0, 2, 0)).getBlock() instanceof AirBlock)) {
            return false;
         } else {
            Box var5 = new Box(var2).withMaxY((double)(var2.getY() + (this.field_2502 ? 1 : 2)));
            int var6 = 0;

            for (Entity var8 : field_4219.world.getEntities()) {
               if (var8 != null && !var8.isRemoved() && var8.isAlive()) {
                  Box var9 = var8.getBoundingBox();
                  if (var8 instanceof PlayerEntity && var8 != field_4219.player) {
                     var9 = Class_0719.method_4((LivingEntity)var8);
                     if (this.field_2503 && !((Class_0415)var8).mio$isNextToWall()) {
                        var9 = var9.expand(Double.longBitsToDouble(4547007122018943789L), 0.0, Double.longBitsToDouble(4547007122018943789L));
                     }

                     for (int var10 = 1; var10 <= this.field_2506; var10++) {
                        Box var11 = Hub.field_2612.method_2((PlayerEntity)var8, var10).withMaxY(var9.maxY).withMinY(var9.minY);
                        if (var11.intersects(var5)) {
                           var6++;
                        }
                     }
                  } else if (var8 instanceof EndCrystalEntity
                        && !this.field_2500
                        && field_4219.player.getEyePos().squaredDistanceTo(var8.getPos()) <= (double)this.field_2507
                     || var8 instanceof ItemEntity && this.field_2505) {
                     continue;
                  }

                  if (var9.intersects(var5)) {
                     var6++;
                     break;
                  }
               }
            }

            return !this.field_2499 || var6 == 0;
         }
      }
   }

   public Class_0794 method_17(boolean var1) {
      this.field_2498 = var1;
      return this;
   }

   public Class_0794 method_37(boolean var1) {
      this.field_2499 = var1;
      return this;
   }

   public Class_0794 method_33(boolean var1) {
      this.field_2500 = var1;
      return this;
   }

   public Class_0794 method_34(boolean var1) {
      this.field_2501 = var1;
      return this;
   }

   public Class_0794 method_35(boolean var1) {
      this.field_2502 = var1;
      return this;
   }

   public Class_0794 method_107(boolean var1) {
      this.field_2503 = var1;
      return this;
   }

   public Class_0794 method_374(boolean var1) {
      this.field_2504 = var1;
      return this;
   }

   public Class_0794 method_425(boolean var1) {
      this.field_2505 = var1;
      return this;
   }

   public Class_0794 method_101(int var1) {
      this.field_2506 = var1;
      return this;
   }

   public Class_0794 method_33(float var1) {
      this.field_2507 = var1 * var1;
      return this;
   }
}
