package me.mioclient.internal;

import java.util.Comparator;
import java.util.List;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_1239;
import me.mioclient.module.combat.AutoCrystalModule;
import me.mioclient.module.combat.CombatmineModule;
import me.mioclient.module.player.SpeedMineModule;
import me.mioclient.record.Class_1002;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3i;

public final class Class_0771 extends Class_0844 {
   public static AutoCrystalModule field_219 = Hub.field_2595.method_78(AutoCrystalModule.class);
   public static SpeedMineModule speedmine = Hub.field_2595.method_78(SpeedMineModule.class);
   public static final List<Vec3i> field_2427 = List.of(new Vec3i(1, 0, 0), new Vec3i(0, 0, 1), new Vec3i(0, 1, 0), new Vec3i(0, 0, -1), new Vec3i(-1, 0, 0));

   public Class_0771(CombatmineModule var1) {
      super(var1);
   }

   @Override
   public void method_2(Class_0994 var1) {
      PlayerEntity var2 = this.field_2705.field_3711.method_691();
      boolean var3 = this.field_2705.field_3700.getValue() && var1.method_52() == 400 && var1.method_897() != null;
      if (var2 != null && !this.method_263(var2) && (!this.method_425(var2) || var3)) {
         BlockPos var4 = BlockPos.ofFloored(var2.getPos());
         List var5 = Class_0382.method_5((LivingEntity)var2);
         Block var6 = field_4219.world.getBlockState(var4).getBlock();
         boolean var7 = var6.getBlastResistance() >= Float.intBitsToFloat(1142292480) && var6 != Blocks.AIR;
         if (!this.field_2705.field_3712.method_29(500L) || var4.equals(this.field_2705.field_3712.method_111())) {
            var7 = false;
         }

         Class_1002 var8 = this.method_2(var5, var7, var3);
         if (var8.field_3090 != null) {
            var1.method_2(600, var1x -> {
               if (var1x.method_897() == null) {
                  var1x.method_465(var8.field_3091);
               }

               var1x.method_425(var8.field_3090);
            });
         }
      }
   }

   public Class_1002 method_2(List<BlockPos> var1, boolean var2, boolean var3) {
      BlockPos var4 = null;
      BlockPos var5 = null;
      boolean var6 = false;
      var1.sort(Comparator.comparing(var1x -> {
         if (field_4219.player.getBoundingBox().intersects(new Box(var1x))) {
            return Double.longBitsToDouble(-4556649414143246336L);
         } else if (Class_0382.method_5((LivingEntity)field_4219.player).contains(var1x)) {
            return Double.longBitsToDouble(-4571373524106608640L);
         } else {
            boolean var2x = false;

            for (Vec3i var4x : field_2427) {
               if (var4x.getY() == 0) {
                  BlockPos var5x = var1x.add(var4x);
                  if (this.method_15(var5x.down())) {
                     var2x = true;
                     break;
                  }
               }
            }

            double var6x = var1x.getSquaredDistance(field_4219.player.getPos());
            if (!var2x) {
               var6x += Math.pow((double)speedmine.field_3989.method_101().floatValue(), Double.longBitsToDouble(4611686018427387904L));
            }

            return -var6x;
         }
      }));

      for (BlockPos var8 : var1) {
         if (var3) {
            var8 = var8.down();
         }

         boolean var9 = Class_1225.method_14(var8) && var8.equals(speedmine.method_1120());
         boolean var10 = this.field_2705.field_3692.getValue() == Class_1239.GRIM || this.field_2705.field_3692.getValue() == Class_1239.GRIMV3;
         if (var2) {
            var9 = false;
         }

         if (speedmine.method_4(400L) && var10) {
            var9 = false;
         }

         if ((this.method_323(var8) || var3 || var10) && (this.method_493(var8) || var9)) {
            if (this.method_494(var8)) {
               var4 = var8;
               var6 = true;
            } else if (var6) {
               var5 = var8;
            } else {
               var5 = var4;
               var4 = var8;
            }
         }
      }

      return new Class_1002(var4, var5);
   }

   public boolean method_323(BlockPos var1) {
      for (Vec3i var3 : field_2427) {
         BlockPos var4 = var1.add(var3);
         if (this.method_15(var4.down())) {
            return true;
         }
      }

      return false;
   }

   public boolean method_231(BlockPos var1) {
      if (this.field_2705.field_3704.getValue()) {
         for (BlockPos var3 : Class_0382.method_5((LivingEntity)field_4219.player)) {
            if (var3.equals(var1)) {
               return true;
            }
         }
      }

      List var4 = BlockPos.stream(field_4219.player.getBoundingBox().withMaxY(field_4219.player.getY()))
         .<BlockPos>map(BlockPos::toImmutable)
         .filter(var0 -> field_4219.world.getBlockState(var0).getBlock().getBlastResistance() >= Float.intBitsToFloat(1142292480))
         .toList();
      return var4.size() == 1 && var4.contains(var1);
   }

   public boolean method_425(PlayerEntity var1) {
      return BlockPos.stream(var1.getBoundingBox().withMaxY(var1.getY()))
         .<BlockPos>map(BlockPos::toImmutable)
         .allMatch(var0 -> field_4219.world.getBlockState(var0).isOf(Blocks.BEDROCK));
   }

   public boolean method_15(BlockPos var1) {
      return Class_1035.method_2(var1, field_219.field_4058.getValue(), true, false, false, field_219.field_4062.getValue(), field_219.field_4120.getValue());
   }

   @Override
   public boolean method_206() {
      return this.field_2705.field_3703.getValue();
   }

   @Override
   public boolean method_493(BlockPos var1) {
      if (this.method_231(var1)) {
         return false;
      } else {
         return Class_1225.method_33(var1) == Blocks.COBWEB ? false : super.method_493(var1);
      }
   }
}
