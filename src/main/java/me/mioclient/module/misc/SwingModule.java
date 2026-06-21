package me.mioclient.module.misc;

import me.mioclient.enum_.Class_0286;
import me.mioclient.enum_.Class_0813;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import nick.Settings;

public class SwingModule extends Module {
   public Setting<Class_0813> field_504;
   public Setting<Class_0286> field_505;
   public Setting<Double> field_506;

   public SwingModule() {
      super("Swing", "Changes your swinging hand.", Category.MISC);
      Settings.initialize(this);
   }
}
