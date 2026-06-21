package me.mioclient.module.render;

import me.mioclient.event.Event_1;
import me.mioclient.event.Subscribe;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import nick.Settings;

public class NoBobModule extends Module {
   public Setting<Float> field_4483;

   public NoBobModule() {
      super("NoBob", "Modifies the bobbing animation.", Category.RENDER);
      Settings.initialize(this);
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if (!this.method_535()) {
         if (this.field_4483.getValue() <= 0.0F) {
            field_4219.player.horizontalSpeed = 0.0F;
         }
      }
   }
}
