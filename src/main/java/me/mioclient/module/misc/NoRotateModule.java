package me.mioclient.module.misc;

import me.mioclient.event.Event_10;
import me.mioclient.event.Event_4;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0382;
import me.mioclient.internal.Class_0464;
import me.mioclient.internal.Class_0485;
import me.mioclient.mixin.ducks.DuckPlayerPositionLookS2CPacket;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.shape.VoxelShape;
import nick.Settings;

public class NoRotateModule extends Module {
   public Setting<Boolean> field_2787;
   public Setting<Boolean> field_2788;

   public NoRotateModule() {
      super("NoRotate", "Cancels the rotations server sets for you.", Category.MISC);
      Settings.initialize(this);
   }

   @Subscribe
   public void method_2(Event_10 var1) {
      if (this.field_2787.getValue()
         && !Class_0464.method_363()
         && !field_4219.player.isHoldingOntoLadder()
         && var1.method_127() instanceof PlayerMoveC2SPacket
         && (!this.field_2788.getValue() || !Class_0382.method_5(field_4219.player))) {
         BlockState var2 = field_4219.world.getBlockState(field_4219.player.getBlockPos());
         if (!(var2.getBlock() instanceof FallingBlock)) {
            VoxelShape var3 = var2.getCollisionShape(field_4219.world, BlockPos.ORIGIN);
            if (!var3.isEmpty() && !(field_4219.player.getY() - Math.floor(field_4219.player.getY()) >= var3.getBoundingBox().maxY)) {
               Box var4 = var3.getBoundingBox().offset(field_4219.player.getBlockPos());
               if (var4.intersects(field_4219.player.getBoundingBox().expand(Double.longBitsToDouble(-4636005456415188582L)))) {
                  var1.method_463();
               }
            }
         }
      }
   }

   @Subscribe
   public void method_5(Event_4 var1) {
      if (!field_4219.isInSingleplayer() && !field_4219.player.isHoldingOntoLadder()) {
         if (!Class_0485.method_513() || field_4219.player.age > 20) {
            if (var1.method_127() instanceof PlayerPositionLookS2CPacket var2 && !this.method_535() && field_4219.player.age > 5) {
               float var5 = field_4219.player.getYaw();
               float var4 = field_4219.player.getPitch();
               if (var2.getFlags().contains(PositionFlag.X_ROT)) {
                  var5 = 0.0F;
               }

               if (var2.getFlags().contains(PositionFlag.Y_ROT)) {
                  var4 = 0.0F;
               }

               ((DuckPlayerPositionLookS2CPacket)var2).setYaw(var5);
               ((DuckPlayerPositionLookS2CPacket)var2).setPitch(var4);
            }
         }
      }
   }
}
