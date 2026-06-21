package me.mioclient.module.misc;

import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import nick.Settings;

public class AutoReconnectModule extends Module {
   public Setting<Float> field_2007;

   public AutoReconnectModule() {
      super("AutoReconnect", "Reconnects to the server automatically if you get kicked.", Category.MISC);
      Settings.initialize(this);
      this.setDrawn(false);
   }
}
