package me.mioclient.module.movement;

import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import nick.Settings;

public class FastLadderModule extends Module {
   public Setting<Float> field_2205;

   public FastLadderModule() {
      super("FastLadder", "Makes you move faster on ladders.", Category.MOVEMENT);
      Settings.initialize(this);
   }
}
