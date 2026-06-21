package me.mioclient.module.render;

import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import nick.Settings;

public class AnimationsModule extends Module {
   public Setting<Boolean> field_334;
   public Setting<Float> field_335;
   public Setting<Float> field_336;
   public Setting<Float> field_337;
   public Setting<Boolean> field_338;
   public Setting<Boolean> field_339;
   public Setting<Boolean> field_340;
   public Setting<Boolean> field_341;
   public Setting<Boolean> field_342;
   public Setting<Boolean> field_343;
   public Setting<Boolean> field_344;
   public Setting<Boolean> field_345;
   public Setting<Float> field_346;

   public AnimationsModule() {
      super("Animations", "Modifies entity animations.", Category.RENDER);
      Settings.initialize(this);
   }

   public boolean method_128() {
      return this.isToggled() && this.field_343.getValue() && this.field_346.getValue() != Float.intBitsToFloat(1065353216);
   }

   public boolean method_129() {
      return this.isToggled() && this.field_343.getValue() && this.field_344.getValue();
   }

   public boolean method_130() {
      return this.isToggled() && this.field_343.getValue() && this.field_345.getValue();
   }

   public boolean method_131() {
      return this.isToggled() && this.field_334.getValue();
   }

   public boolean method_132(int var1) {
      return switch (var1) {
         case 0 -> this.method_136();
         case 1 -> this.method_134();
         case 2 -> this.method_133();
         case 3 -> this.method_135();
         default -> false;
      };
   }

   public boolean method_133() {
      return this.method_131() && !this.field_339.getValue();
   }

   public boolean method_134() {
      return this.method_131() && !this.field_340.getValue();
   }

   public boolean method_135() {
      return this.method_131() && !this.field_341.getValue();
   }

   public boolean method_136() {
      return this.method_131() && !this.field_342.getValue();
   }
}
