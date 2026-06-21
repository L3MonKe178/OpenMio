package me.mioclient.internal;

import me.mioclient.module.combat.CombatmineModule;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

public final class Class_1164 extends Class_0844 {
   public Class_1164(CombatmineModule var1) {
      super(var1);
   }

   @Override
   public void method_2(Class_0994 var1) {
      BlockPos var2 = this.method_1028();
      if ((this.field_2705.field_3711.method_691() != null || var2 != null) && !field_4219.player.isFallFlying()) {
         Box var3 = field_4219.player.getBoundingBox();
         if (!this.field_2705.field_3707.getValue() || this.method_1029() || var2 != null) {
            int var4 = !this.method_1029() && var2 == null ? 100 : 1500;
            BlockPos var5 = this.method_38(var3);
            if (var5 != null) {
               var1.method_2(var4, var1x -> var1x.method_425(var5));
            }
         }
      }
   }

   public BlockPos method_38(Box var1) {
      BlockPos var2 = BlockPos.ofFloored(field_4219.player.getPos()).down();
      BlockPos var3 = this.method_1028();
      if (this.method_493(var2) && (this.method_494(var2) || this.method_1029()) && this.method_1030()) {
         return var2;
      } else {
         var2 = BlockPos.ofFloored(field_4219.player.getPos().add(0.0, var1.getLengthY() - Class_0719.field_2280, 0.0)).up();
         if (var3 != null) {
            var2 = var3;
         }

         return this.method_493(var2) ? var2 : null;
      }
   }

   public BlockPos method_1028() {
      if (!this.field_2705.field_3706.getValue()) {
         return null;
      } else {
         BlockPos var1 = BlockPos.ofFloored(field_4219.player.getEyePos());
         BlockState var2 = field_4219.world.getBlockState(var1);
         return !var2.isAir() && !(var2.getBlock().getBlastResistance() < Float.intBitsToFloat(1142292480)) && !this.method_1029() && this.method_493(var1)
            ? var1
            : null;
      }
   }

   public boolean method_1029() {
      return field_4219.player.getBoundingBox().getLengthY() <= Double.longBitsToDouble(4607182418800017408L);
   }

   public boolean method_1030() {
      if (this.field_2705.field_3708.getValue()) {
         return true;
      } else if (field_4219.world.isAir(Class_0382.method_425().down(2))) {
         return false;
      } else {
         for (BlockPos var2 : Class_0382.method_5((net.minecraft.entity.LivingEntity)field_4219.player)) {
            BlockState var3 = field_4219.world.getBlockState(var2.down());
            if (var3.isAir() || var3.getBlock().getBlastResistance() < Float.intBitsToFloat(1142292480)) {
               return false;
            }
         }

         return true;
      }
   }

   @Override
   public boolean method_206() {
      return this.field_2705.field_3705.getValue();
   }
}
