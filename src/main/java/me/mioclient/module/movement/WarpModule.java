package me.mioclient.module.movement;

import me.mioclient.Hub;
import me.mioclient.enum_.Class_0399;
import me.mioclient.enum_.Class_1269;
import me.mioclient.enum_.PreType;
import me.mioclient.event.Event_19;
import me.mioclient.event.Event_4;
import me.mioclient.event.Event_9;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_1359;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import nick.Settings;

public class WarpModule extends Module {
   public static FakeLagModule field_1107 = Hub.field_2595.method_78(FakeLagModule.class);
   public Setting<Class_1269> field_1108;
   public Setting<Integer> field_1109;
   public Setting<Integer> field_1110;
   public Setting<Integer> field_1111;
   public Setting<Class_0399> field_1112;
   public Setting<Boolean> field_1113;
   public Setting<Boolean> field_1114;
   public Setting<Boolean> field_1115;
   public Setting<Boolean> field_1116;
   public final Class_1359 field_1117;
   public boolean field_1118;
   public volatile int field_1119;

   public WarpModule() {
      super("Warp", "Allows you to dash forward after standing still for a certain amount of time.", Category.MOVEMENT, "tickshift");
      Settings.initialize(this);
      this.field_1117 = new Class_1359(this);
   }

   @Override
   public String getInfo() {
      return this.field_1108.getValue() == Class_1269.ALTERNATIVE ? String.valueOf(this.field_1117.field_4427.size()) : String.valueOf(this.field_1119);
   }

   @Override
   public void onEnable() {
      if (this.field_1112.getValue() == Class_0399.INSTANT) {
         this.field_1119 = this.field_1110.getValue();
      }
   }

   @Override
   public void onDisable() {
      this.method_105(false);
      if (this.field_1108.getValue() == Class_1269.ALTERNATIVE && !this.method_535()) {
         try {
            while (!this.field_1117.field_4427.isEmpty()) {
               field_4219.player.networkHandler.sendPacket(this.field_1117.field_4427.poll());
            }
         } catch (Exception var2) {
         }

         this.field_1117.field_4427.clear();
      }
   }

   @Subscribe(
      method_800 = -200
   )
   public void method_7(Event_9 var1) {
      if (this.field_1108.getValue() == Class_1269.PLAIN) {
         if (!this.method_385()) {
            Hub.field_2617.method_38(this);
            this.method_105(false);
            this.field_1118 = false;
         } else if (this.field_1119 == 0 || this.field_1118) {
            Hub.field_2617.method_38(this);
            if (this.field_1116.getValue()) {
               this.method_68();
            }

            this.method_105(false);
         } else if (this.field_1119 > 0) {
            if (this.field_1113.getValue()) {
               boolean var2 = field_1107.isToggled();
               this.method_105(field_4219.player.isOnGround() || !this.field_1114.getValue());
               if (this.field_1109.getValue() > 1 && this.field_1115.getValue()) {
                  Hub.field_2602.method_985().reset();
               }

               if (!var2) {
                  return;
               }
            }

            Hub.field_2617.method_2(this, (float)(this.field_1109.getValue() != null ? this.field_1109.getValue().intValue() : 0));
         }
      }
   }

   @Subscribe
   public void method_9(Event_19 var1) {
      if (this.field_1108.getValue() == Class_1269.ALTERNATIVE) {
         if (var1.method_213() == PreType.PRE) {
            if (!this.field_1117.field_4427.isEmpty() && this.method_385()) {
               Hub.field_2617.method_2(this, (float)(this.field_1109.getValue() != null ? this.field_1109.getValue().intValue() : 0));
               field_4219.player.networkHandler.sendPacket(this.field_1117.field_4427.poll());
            } else if (!this.field_1117.field_4427.isEmpty() && !this.field_1118) {
               Hub.field_2617.method_38(this);
            } else {
               Hub.field_2617.method_38(this);
               if (this.field_1116.getValue()) {
                  this.method_68();
               }
            }
         }
      }
   }

   @Subscribe
   public void method_5(Event_4 var1) {
      if (var1.method_127() instanceof PlayerPositionLookS2CPacket) {
         this.field_1118 = true;
      }
   }

   public void method_105(boolean var1) {
      if (this.field_1113.getValue() && field_4219.player != null) {
         field_1107.method_38(var1);
      }
   }

   public boolean method_385() {
      return field_4219.player.input.pressingRight
         || field_4219.player.input.pressingLeft
         || field_4219.player.input.pressingBack
         || field_4219.player.input.pressingForward
         || field_4219.player.getX() - field_4219.player.prevX != 0.0
         || field_4219.player.getY() - field_4219.player.prevY != 0.0
         || field_4219.player.getZ() - field_4219.player.prevZ != 0.0;
   }

   public int method_165() {
      return this.field_1108.getValue() == Class_1269.ALTERNATIVE ? this.field_1117.field_4427.size() : this.field_1119;
   }

   public boolean method_363() {
      return field_4219.player.getPos().squaredDistanceTo(field_4219.player.prevX, field_4219.player.prevY, field_4219.player.prevZ) > 0.0;
   }
}
