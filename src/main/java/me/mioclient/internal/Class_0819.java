package me.mioclient.internal;

import java.util.Comparator;
import java.util.List;
import me.mioclient.Hub;
import me.mioclient.api.MioAPI;
import me.mioclient.module.combat.PusherModule;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public final class Class_0819 implements MioAPI {
   public static final List<Block> field_2593 = List.of(Blocks.OBSIDIAN, Blocks.CRYING_OBSIDIAN, Blocks.REINFORCED_DEEPSLATE, Blocks.RESPAWN_ANCHOR);
   public final PusherModule field_2594;

   public Class_0819(PusherModule var1) {
      super();
      this.field_2594 = var1;
   }

   public PlayerEntity method_691() {
      return field_4219.world
         .getPlayers()
         .stream()
         .filter(
            var1 -> var1.isAlive()
                  && var1 != field_4219.player
                  && Class_0396.method_2(var1)
                  && field_4219.player.distanceTo(var1) <= this.field_2594.method_884()
                  && !Hub.field_2603.method_30(var1)
         )
         .filter(var1 -> !this.field_2594.field_3009.getValue() || Class_0382.method_29((LivingEntity)var1))
         .filter(this::method_52)
         .min(Comparator.comparing(var0 -> MathHelper.angleBetween(field_4219.player.getYaw(), RotationManager.method_14(var0)[0])))
         .orElse(null);
   }

   public boolean method_52(PlayerEntity var1) {
      BlockPos var2 = var1.getBlockPos();
      BlockState var3 = field_4219.world.getBlockState(var2.up());
      if (field_2593.contains(var3.getBlock())
         || var3.getHardness(field_4219.world, var2.up()) == Float.intBitsToFloat(-1082130432)
         || Hub.field_2622.method_105(var2.up(2))) {
         return false;
      } else if (this.field_2594.field_3011.getValue() && !Hub.field_2605.method_221(var2)) {
         return false;
      } else if (this.field_2594.field_3010.getValue()
         && !Hub.field_2605.method_221(var2)
         && field_4219.world.getBlockState(var1.getBlockPos()).getBlock().getBlastResistance() < Float.intBitsToFloat(1142292480)) {
         return false;
      } else {
         List var4 = Class_0382.method_5((LivingEntity)var1);
         return var4.size() <= 6;
      }
   }
}
