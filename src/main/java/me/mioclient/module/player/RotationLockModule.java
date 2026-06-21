package me.mioclient.module.player;

import me.mioclient.Hub;
import me.mioclient.enum_.PreType;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_19;
import me.mioclient.event.Event_39;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0245;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.render.FreeLookModule;
import me.mioclient.setting.Setting;
import nick.Settings;

public class RotationLockModule extends Module {
   public static FreeLookModule freelook = Hub.field_2595.method_78(FreeLookModule.class);
   public Setting<Boolean> field_1475;
   public Setting<Boolean> field_1476;
   public Setting<Float> field_1477;
   public Setting<Boolean> field_1478;
   public Setting<Float> field_1479;

   public RotationLockModule() {
      super("RotationLock", "Locks your rotation.", Category.PLAYER);
      Settings.initialize(this);
      this.field_1477.method_31("YawValue");
      this.field_1479.method_31("PitchValue");
   }

   @Subscribe(
      method_800 = 200
   )
   public void method_9(Event_19 var1) {
      if (var1.method_213() == PreType.PRE && freelook.isToggled()) {
         if (this.field_1475.getValue()) {
            var1.setYaw(this.method_500());
         }

         if (this.field_1478.getValue()) {
            var1.setPitch(this.method_501());
         }
      }
   }

   @Subscribe(
      method_800 = 200
   )
   public void method_9(Event_39 var1) {
      if (freelook.isToggled()) {
         if (this.field_1475.getValue()) {
            var1.setYaw(this.method_500());
            var1.method_463();
         }

         if (this.field_1478.getValue()) {
            var1.setPitch(this.method_501());
            var1.method_463();
         }
      }
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if (this.field_1475.getValue()) {
         field_4219.player.setYaw(this.method_500());
      }

      if (this.field_1478.getValue()) {
         field_4219.player.setPitch(this.method_501());
      }
   }

   public float method_500() {
      return !this.field_1476.getValue()
         ? (float)(Math.round((field_4219.player.getYaw() + Float.intBitsToFloat(1065353216)) / (float)Class_0245.field_684) * Class_0245.field_684)
         : this.field_1477.getValue();
   }

   public float method_501() {
      return this.field_1479.getValue();
   }
}
