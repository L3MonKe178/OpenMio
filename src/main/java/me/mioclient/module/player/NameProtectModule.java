package me.mioclient.module.player;

import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import nick.Settings;

public class NameProtectModule extends Module {
   public Setting<String> field_1851;
   public Setting<Boolean> field_1852;
   public Setting<Boolean> field_1853;

   public NameProtectModule() {
      super("NameProtect", "Hides your nickname from the curious people.", Category.PLAYER);
      Settings.initialize(this);
   }
}
