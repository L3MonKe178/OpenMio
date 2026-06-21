package me.mioclient.module.render;

import java.awt.Color;
import me.mioclient.enum_.Class_0647;
import me.mioclient.internal.Class_1225;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import nick.Settings;

public class SkyColorModule extends Module {
   public Setting<Class_0647> field_314;
   public Setting<Boolean> field_315;
   public Setting<Color> field_316;
   public Setting<Color> field_317;
   public Setting<Boolean> field_318;
   public Setting<Boolean> field_319;
   public Setting<Boolean> field_320;
   public Setting<Boolean> field_321;

   public SkyColorModule() {
      super("SkyColor", "Changes the fog color.", Category.RENDER);
      Settings.initialize(this);
      this.setDrawn(false);
      this.field_316.method_5(var1 -> this.field_314.getValue() != Class_0647.END);
      this.field_317.method_5(var1 -> this.field_314.getValue() != Class_0647.END);
      this.field_315
         .method_5(
            var1 -> this.field_314.getValue() == Class_0647.FLAT
                  || (this.field_320.getValue() || this.field_321.getValue()) && this.field_314.getValue() == Class_0647.NONE
         );
   }

   public boolean method_125() {
      return switch (Class_1225.method_1071()) {
         case OVERWORLD -> this.field_319.getValue();
         case THE_NETHER -> this.field_320.getValue();
         default -> this.field_321.getValue();
      };
   }
}
