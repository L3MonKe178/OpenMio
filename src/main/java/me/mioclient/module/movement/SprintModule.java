package me.mioclient.module.movement;

import me.mioclient.Hub;
import me.mioclient.enum_.Class_0474;
import me.mioclient.enum_.Class_1054;
import me.mioclient.enum_.Class_1229;
import me.mioclient.enum_.PreType;
import me.mioclient.event.Event_19;
import me.mioclient.event.Event_33;
import me.mioclient.event.Event_36;
import me.mioclient.event.Event_37;
import me.mioclient.event.Event_38;
import me.mioclient.event.Event_39;
import me.mioclient.event.Event_4;
import me.mioclient.event.Event_9;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Timer;
import me.mioclient.internal.Class_0382;
import me.mioclient.internal.Class_0464;
import me.mioclient.internal.FontRenderer;
import me.mioclient.internal.PacketUtil;
import me.mioclient.mixin.ducks.DuckEntity;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.abstract_.AbstractModule_28;
import me.mioclient.module.player.RotationLockModule;
import me.mioclient.module.player.ScaffoldModule;
import me.mioclient.setting.Setting;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket.Mode;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket.Full;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import nick.Settings;

public class SprintModule extends Module {
   public static final ScaffoldModule field_1016 = Hub.field_2595.method_78(ScaffoldModule.class);
   public static final ElytraFlyModule efly = Hub.field_2595.method_78(ElytraFlyModule.class);
   public static final LongJumpModule field_1017 = Hub.field_2595.method_78(LongJumpModule.class);
   public static final FastFallModule field_1018 = Hub.field_2595.method_78(FastFallModule.class);
   public static final SpeedModule field_1019 = Hub.field_2595.method_78(SpeedModule.class);
   public static final FastWebModule field_1020 = Hub.field_2595.method_78(FastWebModule.class);
   public static final AbstractModule_28 field_1021 = Hub.field_2595.method_78(AbstractModule_28.class);
   public static RotationLockModule field_711 = Hub.field_2595.method_78(RotationLockModule.class);
   public Setting<Class_0474> field_1022;
   public Setting<Boolean> field_1023;
   public Setting<Boolean> field_1024;
   public Setting<Boolean> field_1025;
   public Setting<Float> field_1026;
   public Setting<Boolean> field_1027;
   public Setting<Boolean> field_1028;
   public Setting<Boolean> field_1029;
   public final Timer field_1030;
   public float field_1031;
   public boolean field_1032;
   public boolean field_1033;

   public SprintModule() {
      super("Sprint", "Sprints automatically.", Category.MOVEMENT);
      Settings.initialize(this);
      this.field_1030 = new Timer();
      this.field_1031 = 0.0F;
   }

   @Override
   public String getInfo() {
      return FontRenderer.method_3(this.field_1022.getValue());
   }

   @Subscribe
   public void method_5(Event_4 var1) {
      if (var1.method_127() instanceof PlayerPositionLookS2CPacket) {
         this.field_1031 = 0.0F;
      }
   }

   @Subscribe
   public void method_5(Event_36 var1) {
      if (!Hub.field_2630.method_261()) {
         if (field_1021.field_3744.getValue() && field_1021.field_3745.getValue() == Class_1054.SILENT) {
            if (this.field_1028.getValue()
               && Hub.field_2598.method_510() == null
               && Class_0464.method_363()
               && Class_0464.method_496() != field_4219.player.getYaw()
               && !this.field_1033) {
               PacketUtil.method_2(
                  new Full(
                     field_4219.player.getX(),
                     field_4219.player.getY(),
                     field_4219.player.getZ(),
                     field_4219.player.getYaw(),
                     field_4219.player.getPitch(),
                     field_4219.player.isOnGround()
                  )
               );
            }
         }
      }
   }

   @Subscribe
   public void method_2(Event_37 var1) {
      if (!Hub.field_2630.method_261()) {
         Vec3d var2 = new Vec3d(var1.method_380(), var1.method_395(), var1.method_396());
         if (Class_0464.method_363()
            && Hub.field_2602.method_984().method_9(750L)
            && this.field_1025.getValue()
            && !(Math.sqrt(field_4219.player.squaredDistanceTo(var2)) > Double.longBitsToDouble(4618441417868443648L))) {
            this.field_1030.reset();
            this.field_1031 = (float)Math.hypot((double)var1.method_398(), (double)var1.method_400()) * this.field_1026.getValue();
         }
      }
   }

   @Subscribe(
      method_800 = 100
   )
   public void method_9(Event_19 var1) {
      if (!Hub.field_2630.method_261()) {
         if (!this.method_535() && !this.method_364() && !field_711.isToggled()) {
            if (this.field_1028.getValue() && Class_0464.method_363() && !field_4219.player.isFallFlying()) {
               var1.setYaw(Class_0464.method_496());
            }
         }
      }
   }

   @Subscribe
   public void method_9(Event_38 var1) {
      if (!Hub.field_2630.method_261()) {
         if (!this.method_535() && !this.method_364()) {
            if (this.field_1022.getValue() != Class_0474.INSTANT || this.field_1023.getValue()) {
               this.method_101(this.method_363());
            }
         }
      }
   }

   @Subscribe(
      method_800 = 1000
   )
   public void method_2(Event_9 var1) {
      if (!Hub.field_2630.method_261()) {
         if (!this.method_535() && !this.method_364() && this.field_1022.getValue() == Class_0474.INSTANT) {
            if (!Class_0382.method_427()
               && !field_4219.player.isFallFlying()
               && !field_4219.player.getAbilities().flying
               && !field_4219.player.isTouchingWater()
               && !field_4219.player.isInLava()
               && !field_4219.player.isSneaking()
               && !field_4219.player.isInSwimmingPose()
               && !field_1018.field_4406
               && !field_1017.isToggled()) {
               if (!field_1019.isToggled()) {
                  double var2 = Class_0464.method_78(this.field_1024.getValue());
                  if (Hub.field_2602.method_984().method_9(500L)) {
                     var2 = Math.max(var2, Math.hypot(var1.method_380(), var1.method_396()));
                  }

                  if (this.field_1024.getValue()) {
                     Block var4 = field_4219.world.getBlockState(((DuckEntity)field_4219.player).mio$getVelocityAffectingPos()).getBlock();
                     if (var4 == Blocks.SLIME_BLOCK) {
                        var2 *= Double.longBitsToDouble(4604480259023595110L);
                     }

                     var2 *= (double)var4.getVelocityMultiplier();
                  }

                  if (this.field_1025.getValue() && !this.field_1030.method_9(500L)) {
                     var2 += (double)this.field_1031;
                  }

                  Class_0464.method_2(var1, var2);
               }
            }
         }
      }
   }

   @Subscribe
   public void method_9(Event_39 var1) {
      if (!Hub.field_2630.method_261()) {
         if (this.field_1028.getValue()
            && Class_0464.method_363()
            && !field_4219.player.isFallFlying()
            && (var1.method_1025() || field_4219.player.isInSwimmingPose())
            && !this.method_364()) {
            if (field_4219.player.isCrawling() && !var1.method_1025() || field_711.isToggled()) {
               return;
            }

            var1.setYaw(Class_0464.method_496());
            var1.method_463();
         }
      }
   }

   @Subscribe
   public void method_9(Event_33 var1) {
      if (!Hub.field_2630.method_261()) {
         if (this.field_1029.getValue()) {
            if (var1.method_213() == PreType.PRE) {
               this.field_1032 = field_4219.player.isSprinting();
               if (this.field_1032) {
                  PacketUtil.method_2(field_4219.player, Mode.STOP_SPRINTING, 0);
               }
            } else if (this.field_1032) {
               field_4219.player.setSprinting(true);
               PacketUtil.method_2(field_4219.player, Mode.START_SPRINTING, 0);
            }
         }
      }
   }

   public boolean method_363() {
      if (this.method_364()) {
         return false;
      } else if (!this.field_1028.getValue() || Hub.field_2598.method_510() == null && !field_4219.player.isFallFlying()) {
         boolean var1 = this.field_1022.getValue() == Class_0474.LEGIT || field_4219.player.isSubmergedInWater();
         if (Class_0382.method_427() || var1 && (field_4219.player.input.movementForward <= 0.0F || field_4219.player.horizontalCollision) || this.method_365()
            )
          {
            return false;
         } else {
            return !field_1020.method_726() || !Class_0382.method_5(field_4219.player) || Hub.field_2615.method_1161() >= 2 && !field_4219.player.input.jumping
               ? field_4219.player.input.movementForward != 0.0F || field_4219.player.input.movementSideways != 0.0F
               : false;
         }
      } else {
         return false;
      }
   }

   public boolean method_364() {
      return Hub.field_2630.method_264() && Hub.field_2630.method_261()
         ? true
         : field_1016.isToggled() && field_1016.field_156.getValue()
            || Class_0382.method_29(field_4219.player) && this.field_1027.getValue()
            || efly.isToggled() && efly.field_4349.getValue() == Class_1229.BOUNCE && efly.method_558();
   }

   public void method_101(boolean var1) {
      try {
         field_4219.player.setSprinting(var1);
      } catch (Exception var3) {
      }
   }

   public boolean method_365() {
      return field_4219.player.isOnGround()
         ? false
         : BlockPos.stream(field_4219.player.getBoundingBox().stretch(0.0, Double.longBitsToDouble(-4631501856787818086L), 0.0))
            .anyMatch(var0 -> field_4219.world.isWater(var0));
   }
}
