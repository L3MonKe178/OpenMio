package me.mioclient.module.movement;

import me.mioclient.enum_.Class_0525;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_10;
import me.mioclient.event.Event_35;
import me.mioclient.event.Subscribe;
import me.mioclient.mixin.ducks.DuckPlayerMoveC2SPacket;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.block.Blocks;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.shape.VoxelShapes;
import nick.Settings;

public class JesusModule extends Module {
   public Setting<Class_0525> field_1268;
   public Setting<Boolean> field_1269;
   public Setting<Float> field_1270;
   public final double[] field_1271;
   public int field_1272;

   public JesusModule() {
      super("Jesus", "Allows you to walk on water.", Category.MOVEMENT);
      Settings.initialize(this);
      this.field_1271 = new double[]{
         Double.longBitsToDouble(4590596676834315394L),
         Double.longBitsToDouble(4594186111131777582L),
         Double.longBitsToDouble(4595128048006538934L),
         Double.longBitsToDouble(4592898539957638545L),
         Double.longBitsToDouble(4586206011794593297L),
         Double.longBitsToDouble(4578280644290880618L)
      };
   }

   @Override
   public void onToggle() {
      this.field_1272 = 0;
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if (this.method_442()) {
         if (field_4219.player.isTouchingWater()) {
            field_4219.player
               .addVelocity(
                  0.0,
                  this.field_1268.getValue() == Class_0525.DOLPHIN
                     ? Double.longBitsToDouble(4585911017040021081L)
                     : Double.longBitsToDouble(4590429028186199163L) * (double)(this.field_1270.getValue() != null ? this.field_1270.getValue().floatValue() : 0.0f),
                  0.0
               );
         }
      }
   }

   @Subscribe
   public void method_2(Event_10 var1) {
      if (this.field_1268.getValue() == Class_0525.SOLID && this.method_442()) {
         if (var1.method_127() instanceof PlayerMoveC2SPacket var2 && this.field_1269.getValue() && this.method_443()) {
            ((DuckPlayerMoveC2SPacket)var2).setOnGround(false);
            ((DuckPlayerMoveC2SPacket)var2).setY(var2.getY(0.0) + this.field_1271[this.field_1272++]);
            this.field_1272 = this.field_1272 % this.field_1271.length;
         }
      }
   }

   @Subscribe
   public void method_2(Event_35 var1) {
      if (!this.method_535() && !field_4219.player.isTouchingWater() && this.field_1268.getValue() == Class_0525.SOLID && this.method_442()) {
         if (var1.method_958().isOf(Blocks.WATER)) {
            var1.method_9(
               VoxelShapes.cuboid(
                  new Box(
                     0.0,
                     0.0,
                     0.0,
                     Double.longBitsToDouble(4607182418800017408L),
                     Double.longBitsToDouble(4607002274814922588L),
                     Double.longBitsToDouble(4607182418800017408L)
                  )
               )
            );
         }
      }
   }

   public boolean method_442() {
      return !this.method_535() && field_4219.player.input != null ? !field_4219.player.hasVehicle() && !field_4219.player.input.sneaking : false;
   }

   public boolean method_443() {
      return field_4219.world
         .getBlockState(BlockPos.ofFloored(field_4219.player.getPos().subtract(0.0, Double.longBitsToDouble(4576918229304087675L), 0.0)))
         .isOf(Blocks.WATER);
   }
}
