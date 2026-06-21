package me.mioclient.module.abstract_;

import java.awt.Color;
import me.mioclient.enum_.Class_0800;
import me.mioclient.module.Category;
import me.mioclient.setting.Setting;
import nick.Settings;

public class AbstractModule_21 extends AbstractModule_41 {
   public Setting<Color> field_2130;
   public Setting<Color> field_2131;
   public Setting<Color> field_2132;
   public Setting<Class_0800> field_2133;

   public AbstractModule_21() {
      super("Colors", "Manages the client's color system.", Category.CLIENT);
      Settings.initialize(this);
   }
}
