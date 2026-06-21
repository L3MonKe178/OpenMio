package me.mioclient.module.misc;

import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import nick.Settings;

public class AntiQuitModule extends Module {
   public Setting<Boolean> field_3760;
   public Setting<Boolean> field_3761;

   public AntiQuitModule() {
      super("AntiQuit", "Prevents you from quitting the game/server accidentally.", Category.MISC, "antidisconnect");
      Settings.initialize(this);
   }
}
