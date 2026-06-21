package me.mioclient.internal;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicReference;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_1239;
import me.mioclient.module.abstract_.AbstractModule_8;
import me.mioclient.module.combat.CombatmineModule;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

public final class Class_1128 extends Class_0844 {
   public static AbstractModule_8 field_3483 = Hub.field_2595.method_78(AbstractModule_8.class);

   public Class_1128(CombatmineModule var1) {
      super(var1);
   }

   @Override
   public void method_2(Class_0994 var1) {
      PlayerEntity var2 = this.field_2705.field_3711.method_691();
      if (var2 != null && !var2.isCrawling() && !this.method_263(var2)) {
         Box var3 = var2.getBoundingBox();
         boolean var4 = this.field_2705.method_1047();
         boolean var5 = BlockPos.stream(var3.withMaxY(var3.minY)).anyMatch(var0 -> Class_1035.method_30(var0) == Blocks.BEDROCK);
         if (BlockPos.stream(
               var3.shrink(
                  Double.longBitsToDouble(4587366580439587226L), Double.longBitsToDouble(4587366580439587226L), Double.longBitsToDouble(4587366580439587226L)
               )
            )
            .filter(
               var1x -> Class_1035.method_30(var1x).getBlastResistance() >= Float.intBitsToFloat(1142292480)
                     && !this.method_513()
                     && Class_1035.method_30(var1x) != Blocks.AIR
            )
            .toList()
            .isEmpty()) {
            AtomicReference var6 = new AtomicReference<>(
               BlockPos.stream(var3.withMaxY(var3.maxY + Double.longBitsToDouble(4602678819172646912L)))
                  .<BlockPos>map(BlockPos::toImmutable)
                  .filter(this::method_123)
                  .max(Comparator.comparing(var1x -> this.method_494(var1x)))
                  .orElse(null)
            );
            AtomicReference var7 = new AtomicReference<>(
               Class_0382.method_5((LivingEntity)var2)
                  .stream()
                  .map(BlockPos::up)
                  .<BlockPos>map(BlockPos::toImmutable)
                  .filter(this::method_123)
                  .max(Comparator.comparing(var1x -> this.method_494(var1x)))
                  .orElse(null)
            );
            if (var6.get() == null && var7.get() != null && !this.method_513()) {
               var6.set((BlockPos)var7.get());
               var7.set(null);
            }

            if (var6.get() != null) {
               int var8 = 599;
               if (this.method_513()) {
                  if (var5) {
                     var8 = var4 ? 900 : 1000;
                  } else {
                     var8 = var4 ? 499 : 599;
                  }

                  var1.method_2(var8, var3x -> {
                     if (!var4) {
                        var3x.method_425((BlockPos)var6.get());
                     } else {
                        if (var7.get() != null) {
                           var3x.method_465((BlockPos)var6.get());
                           var3x.method_425((BlockPos)var7.get());
                        } else {
                           var3x.method_425((BlockPos)var6.get());
                        }
                     }
                  });
               } else {
                  boolean var9 = this.method_494((BlockPos)var6.get());
                  if (var9) {
                     var8 = 650;
                  }

                  if (this.field_2705.field_3702.getValue() && field_3483.isToggled()) {
                     var8 = 650;
                  }

                  var1.method_2(var8, var1x -> var1x.method_425((BlockPos)var6.get()));
               }
            }
         }
      }
   }

   @Override
   public boolean method_206() {
      return this.field_2705.field_3701.getValue();
   }

   public boolean method_123(BlockPos var1) {
      boolean var2 = Class_1035.method_30(var1) == Blocks.OBSIDIAN || this.method_513();
      boolean var3 = field_4219.world.isAir(var1.up()) || this.method_513();
      return (Class_1225.method_3(var1) && var2 || this.method_494(var1)) && var3;
   }

   public boolean method_513() {
      return this.field_2705.field_3692.getValue() == Class_1239.GRIMV3;
   }
}
