package me.mioclient.module.movement;

import me.mioclient.Hub;
import me.mioclient.enum_.Class_0042;
import me.mioclient.enum_.PreType;
import me.mioclient.event.Event_19;
import me.mioclient.event.Event_38;
import me.mioclient.event.Event_9;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0382;
import me.mioclient.internal.Class_0464;
import me.mioclient.internal.Class_1261;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.abstract_.AbstractModule_28;
import me.mioclient.setting.Setting;
import net.minecraft.block.Blocks;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket.Action;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Direction.Axis;
import nick.Settings;

public class FastWebModule extends Module {
   public static final AbstractModule_28 field_2449 = Hub.field_2595.method_78(AbstractModule_28.class);
   public Setting<Class_0042> field_2450;
   public Setting<Boolean> field_2451;
   public Setting<Float> field_2452;
   public Setting<Boolean> field_2453;
   public Setting<Float> field_2454;
   public Setting<Boolean> field_2455;
   public boolean field_2456;

   public FastWebModule() {
      super("FastWeb", "Makes you faster in cobwebs.", Category.MOVEMENT);
      Settings.initialize(this);
      this.field_2456 = false;
      this.setDrawn(false);
   }

   @Subscribe
   public void method_9(Event_19 var1) {
      if (var1.method_213() == PreType.PRE) {
         Vec3d var2 = new Vec3d(
            field_4219.player.getX() - field_4219.player.prevX,
            field_4219.player.getY() - field_4219.player.prevY,
            field_4219.player.getZ() - field_4219.player.prevZ
         );
         if (this.method_726() && var2.lengthSquared() > 0.0) {
            BlockPos.stream(field_4219.player.getBoundingBox())
               .<BlockPos>map(BlockPos::toImmutable)
               .filter(var0 -> field_4219.world.getBlockState(var0).isOf(Blocks.COBWEB))
               .forEach(var0 -> {
                  if (!field_2449.method_1052()) {
                     Class_1261.method_2(Action.ABORT_DESTROY_BLOCK, var0, Direction.UP);
                  }

                  Class_1261.method_2(Action.STOP_DESTROY_BLOCK, var0, Direction.UP);
               });
         }

         if (this.field_2450.getValue() == Class_0042.GRIM
            && field_4219.player.input.sneaking
            && !field_4219.player.isOnGround()
            && Class_0382.method_5(field_4219.player)) {
            var1.setY(field_4219.player.getY() + Math.random() + Double.longBitsToDouble(4607182418800017408L));
         }
      }
   }

   @Subscribe(
      method_800 = -100
   )
   public void method_2(Event_38 var1) {
      if (this.method_726() && !field_4219.player.isOnGround() && field_4219.player.input.jumping && Class_0382.method_5(field_4219.player)) {
         field_4219.player.setSprinting(false);
      }
   }

   @Subscribe
   public void method_2(Event_9 var1) {
      if (Hub.field_2602.method_984().method_9(500L) && Class_0382.method_5(field_4219.player) && this.field_2450.getValue() != Class_0042.GRIM) {
         if (this.field_2453.getValue()) {
            if (!field_4219.player.input.sneaking || field_4219.player.isOnGround()) {
               this.reset();
            } else if (this.method_724()) {
               this.field_2456 = true;
               Hub.field_2617.method_2(this, this.field_2454.getValue() * Float.intBitsToFloat(1086324736));
            } else {
               this.reset();
               field_4219.player.setVelocity(field_4219.player.getVelocity().withAxis(Axis.Y, (double)(-this.field_2454.getValue())));
               var1.setY((double)(-this.field_2454.getValue()));
            }

            if (this.field_2455.getValue() && field_4219.player.input.jumping && !this.method_724()) {
               field_4219.player.setVelocity(field_4219.player.getVelocity().withAxis(Axis.Y, (double)this.field_2454.getValue().floatValue()));
               var1.setY((double)this.field_2454.getValue().floatValue());
            }
         }

         if (Class_0464.method_363() && this.field_2451.getValue()) {
            double[] var2 = Class_0464.method_2(var1, Class_0464.method_78(true) * (double)this.field_2452.getValue().floatValue());
            field_4219.player.setVelocity(var2[0], field_4219.player.getVelocity().y, var2[1]);
         }
      } else {
         this.reset();
      }
   }

   public boolean method_724() {
      return this.field_2450.getValue() == Class_0042.STRICT;
   }

   public boolean method_725() {
      return this.field_2456;
   }

   public void reset() {
      Hub.field_2617.method_38(this);
      this.field_2456 = false;
   }

   public boolean method_726() {
      return this.isToggled() && this.field_2450.getValue() == Class_0042.GRIM;
   }
}
