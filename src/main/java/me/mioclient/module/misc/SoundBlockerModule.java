package me.mioclient.module.misc;

import java.util.Set;
import me.mioclient.event.Event_40;
import me.mioclient.event.Subscribe;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.sound.SoundEvent;
import nick.Settings;

public class SoundBlockerModule extends Module {
   public Setting<Set<SoundEvent>> field_1075;

   public SoundBlockerModule() {
      super("SoundBlocker", "Blocks certain sounds from being played.", Category.MISC);
      Settings.initialize(this);
   }

   @Subscribe
   public void method_2(Event_40 var1) {
      for (SoundEvent var3 : this.field_1075.getValue()) {
         if (var3.getId().equals(var1.method_863().getId())) {
            var1.method_463();
            break;
         }
      }
   }
}
