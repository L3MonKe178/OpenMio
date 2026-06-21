package me.mioclient.module.abstract_;

import baritone.api.BaritoneAPI;
import java.awt.Color;
import me.mioclient.Hub;
import me.mioclient.event.Event_21;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_1081;
import me.mioclient.module.Category;
import me.mioclient.module.movement.JesusModule;
import me.mioclient.module.movement.SafeWalkModule;
import me.mioclient.module.movement.StepModule;
import me.mioclient.module.player.AutoToolModule;
import me.mioclient.setting.Setting;
import nick.Settings;

public class AbstractModule_22 extends AbstractModule_41 {
   public static final AbstractModule_28 field_1382 = Hub.field_2595.method_78(AbstractModule_28.class);
   public static final StepModule field_1383 = Hub.field_2595.method_78(StepModule.class);
   public static final AutoToolModule field_1384 = Hub.field_2595.method_78(AutoToolModule.class);
   public static SafeWalkModule field_1385 = Hub.field_2595.method_78(SafeWalkModule.class);
   public static JesusModule jesus = Hub.field_2595.method_78(JesusModule.class);
   public Setting<Boolean> field_1386;
   public Setting<Boolean> field_1387;
   public Setting<Boolean> field_1388;
   public Setting<Boolean> field_1389;
   public Setting<Boolean> field_1390;
   public Setting<Color> field_1391;
   public Setting<Color> field_1392;
   public boolean field_1393;
   public boolean field_1394;

   public AbstractModule_22() {
      super("Baritone", "Manages baritone settings.", Category.CLIENT);
      Settings.initialize(this);
      BaritoneAPI.getSettings().chatControl.value = false;
      field_4220.method_14(this);
   }

   @Subscribe
   public void method_2(Event_21 var1) {
      this.method_478();
      BaritoneAPI.getSettings().disconnectOnArrival.value = this.field_1387.getValue();
      BaritoneAPI.getSettings().freeLook.value = this.field_1386.getValue();
      BaritoneAPI.getSettings().blockFreeLook.value = this.field_1386.getValue();
      BaritoneAPI.getSettings().elytraFreeLook.value = this.field_1386.getValue();
      BaritoneAPI.getSettings().censorCoordinates.value = this.field_1389.getValue();
      BaritoneAPI.getSettings().censorRanCommands.value = this.field_1389.getValue();
      if (this.field_1390.getValue()) {
         Color var2 = Class_1081.method_9(this.field_1391.getValue(), 255);
         Color var3 = Class_1081.method_9(this.field_1392.getValue(), 255);
         BaritoneAPI.getSettings().colorBestPathSoFar.value = var2;
         BaritoneAPI.getSettings().colorGoalBox.value = var3;
         BaritoneAPI.getSettings().colorInvertedGoalBox.value = var3;
         BaritoneAPI.getSettings().colorCurrentPath.value = var2;
         BaritoneAPI.getSettings().colorMostRecentConsidered.value = var2;
         BaritoneAPI.getSettings().colorBlocksToBreak.value = var2;
         BaritoneAPI.getSettings().colorBlocksToPlace.value = var2;
         BaritoneAPI.getSettings().colorBlocksToWalkInto.value = var2;
         BaritoneAPI.getSettings().colorNextPath.value = var2;
         BaritoneAPI.getSettings().pathRenderLineWidthPixels.value = Float.intBitsToFloat(1069547520);
         BaritoneAPI.getSettings().goalRenderLineWidthPixels.value = Float.intBitsToFloat(1069547520);
      }

      if (this.field_1388.getValue()) {
         BaritoneAPI.getSettings().assumeStep.value = field_1383.isToggled();
         BaritoneAPI.getSettings().assumeExternalAutoTool.value = field_1384.isToggled();
         BaritoneAPI.getSettings().assumeSafeWalk.value = field_1385.isToggled();
         BaritoneAPI.getSettings().assumeWalkOnWater.value = jesus.isToggled() && jesus.method_442();
      }
   }

   public void method_478() {
      baritone.api.Settings.Setting var1 = BaritoneAPI.getSettings().antiCheatCompatibility;
      boolean var2 = field_1382.method_1053().method_826();
      if (var2 != this.field_1394) {
         if (var2) {
            this.field_1393 = (Boolean)var1.value;
         } else {
            var1.value = this.field_1393;
         }
      } else if (var2) {
         var1.value = false;
      }

      this.field_1394 = var2;
   }
}
