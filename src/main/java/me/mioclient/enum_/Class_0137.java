package me.mioclient.enum_;

import java.util.HashSet;
import me.mioclient.api.Nameable;
import me.mioclient.api.MioAPI;
import me.mioclient.event.Event_10;
import me.mioclient.internal.PlayerUtil;
import me.mioclient.internal.Constants;
import me.mioclient.internal.Class_1225;
import me.mioclient.internal.PacketUtil;
import me.mioclient.mixin.ducks.DuckPlayerMoveC2SPacket;
import me.mioclient.module.movement.NoFallModule;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public enum Class_0137 implements Nameable {
   PACKET("Packet") {
      @Override
      public void method_2(Event_10 var1) {
         if (var1.method_127() instanceof PlayerMoveC2SPacket var2
            && MioAPI.field_4219.player.fallDistance > 3.0F
            && !MioAPI.field_4219.player.isFallFlying()) {
            ((DuckPlayerMoveC2SPacket)var2).setOnGround(true);
         }
      }
   },
   ANTI("Anti") {
      @Override
      public void method_2(Event_10 var1) {
         if (var1.method_127() instanceof PlayerMoveC2SPacket var2
            && MioAPI.field_4219.player.fallDistance > 3.0F
            && !MioAPI.field_4219
               .world
               .isSpaceEmpty(MioAPI.field_4219.player.getBoundingBox().stretch(0.0, MioAPI.field_4219.player.getVelocity().y * 1.1, 0.0))) {
            MioAPI.field_4219
               .player
               .setPos(MioAPI.field_4219.player.getX(), MioAPI.field_4219.player.getY() + 2.0, MioAPI.field_4219.player.getZ());
         }
      }
   },
   TELEPORT("Teleport") {
      @Override
      public void method_2(NoFallModule var1) {
         if (MioAPI.field_4219.player.fallDistance > 3.0F) {
            HashSet var2 = var1.method_78(MioAPI.field_4219.player.getBoundingBox());
            var2.add(BlockPos.ofFloored(MioAPI.field_4219.player.getPos()));

            for (int var3 = 0; (float)var3 <= var1.field_4158.getValue(); var3++) {
               for (BlockPos var5 : (Iterable<BlockPos>)(Iterable<?>)(var2)) {
                  BlockState var6 = MioAPI.field_4219.world.getBlockState(var5.offset(Direction.DOWN, var3));
                  if (var6.getBlock() != Blocks.AIR) {
                     if (Class_1225.method_1071().method_234()) {
                        PacketUtil.method_2(0.0, 64.0, 0.0, false);
                     } else {
                        PacketUtil.method_2(MioAPI.field_4219.player.getX(), 0.0, MioAPI.field_4219.player.getZ(), false);
                     }

                     MioAPI.field_4219.player.fallDistance = 0.0F;
                  }
               }
            }
         }
      }
   },
   MLG("MLG") {
      @Override
      public void method_2(NoFallModule var1) {
         if (MioAPI.field_4219.player.fallDistance > 3.0F) {
            HashSet var2 = var1.method_78(MioAPI.field_4219.player.getBoundingBox());
            var2.add(BlockPos.ofFloored(MioAPI.field_4219.player.getPos()));

            for (int var3 = 0; (float)var3 <= var1.field_4158.getValue(); var3++) {
               for (BlockPos var5 : (Iterable<BlockPos>)(Iterable<?>)(var2)) {
                  BlockState var6 = MioAPI.field_4219.world.getBlockState(var5.offset(Direction.DOWN, var3));
                  if (var6.getBlock() != Blocks.AIR) {
                     boolean var7 = MioAPI.field_4219.player.getOffHandStack().getItem() == Items.WATER_BUCKET;
                     int var8 = PlayerUtil.method_5(Items.WATER_BUCKET);
                     if (var8 != -1 || var7) {
                        MioAPI.field_4219.player.setPitch((float)Constants.field_685);
                        if (!var7) {
                           PlayerUtil.method_16(var8);
                        }

                        Hand var9 = var7 ? Hand.OFF_HAND : Hand.MAIN_HAND;
                        MioAPI.field_4219.interactionManager.interactItem(MioAPI.field_4219.player, var9);
                        MioAPI.field_4219.player.swingHand(var9);
                     }
                  }
               }
            }
         }
      }
   },
   GRIM("Grim") {
      @Override
      public void method_2(NoFallModule var1) {
         if (!var1.method_535() && !var1.method_1147() && !MioAPI.field_4219.player.isOnGround() && MioAPI.field_4219.player.fallDistance > 3.0F) {
            HashSet var2 = var1.method_78(MioAPI.field_4219.player.getBoundingBox());
            var2.add(BlockPos.ofFloored(MioAPI.field_4219.player.getPos()));

            for (int var3 = 0; var3 <= 3; var3++) {
               for (BlockPos var5 : (Iterable<BlockPos>)(Iterable<?>)(var2)) {
                  BlockState var6 = MioAPI.field_4219.world.getBlockState(var5.offset(Direction.DOWN, var3));
                  if (var6.getBlock() != Blocks.AIR) {
                     PacketUtil.method_2(
                        MioAPI.field_4219.player.getX(),
                        MioAPI.field_4219.player.getY() + 1.0E-9,
                        MioAPI.field_4219.player.getZ(),
                        MioAPI.field_4219.player.getYaw(),
                        MioAPI.field_4219.player.getPitch(),
                        false
                     );
                  }
               }
            }
         }
      }
   };

   public final String field_411;

    Class_0137(String var3) {
      this.field_411 = var3;
   }

   @Override
   public String getName() {
      return this.field_411;
   }

   public void method_2(Event_10 var1) {
   }

   public void method_2(NoFallModule var1) {
   }
}
