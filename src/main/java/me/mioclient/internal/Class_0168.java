package me.mioclient.internal;

import java.util.Comparator;
import java.util.List;
import me.mioclient.module.combat.CombatmineModule;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

public final class Class_0168 extends Class_0844 {
   public Class_0168(CombatmineModule var1) {
      super(var1);
   }

   @Override
   public void method_2(Class_0994 var1) {
      boolean var2 = this.field_2705.method_1047();
      PlayerEntity var3 = this.field_2705.field_3711.method_691();
      if (var3 != null) {
         List var4 = BlockPos.stream(var3.getBoundingBox().expand(-Class_0719.field_2280, 0.0, -Class_0719.field_2280).withMaxY(var3.getY()))
            .<BlockPos>map(BlockPos::toImmutable)
            .filter(var1x -> this.method_493(var1x))
            .filter(var0 -> {
               Block var1x = field_4219.world.getBlockState(var0).getBlock();
               return var1x.getBlastResistance() >= Float.intBitsToFloat(1142292480) && var1x != Blocks.COBWEB && var1x != Blocks.ANVIL;
            })
            .sorted(
               Comparator.comparing(
                  var2x -> this.method_494(var2x) ? Double.longBitsToDouble(-4616189618054758400L) : var3.squaredDistanceTo(var2x.toCenterPos())
               )
            )
            .toList();
         if (!var4.isEmpty()) {
            short var5 = 700;
            if (var2 && var4.size() == 1) {
               var5 = 500;
            }

            var1.method_2(var5, var2x -> {
               if (!var2) {
                  var2x.method_425((BlockPos)var4.getFirst());
               } else {
                  if (var4.size() == 1) {
                     var2x.method_465((BlockPos)var4.getFirst());
                  } else {
                     var2x.method_425((BlockPos)var4.getFirst());
                     var2x.method_465((BlockPos)var4.get(1));
                  }
               }
            });
         }
      }
   }

   @Override
   public boolean method_206() {
      return this.field_2705.field_3699.getValue();
   }
}
