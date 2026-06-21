package me.mioclient.module.misc;

import me.mioclient.Hub;
import me.mioclient.enum_.PreType;
import me.mioclient.event.Event_46;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0211;
import me.mioclient.internal.ChatUtil;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import nick.Settings;

public class PMSoundModule extends Module {
   public Setting<Class_0211> field_2809;
   public Setting<Float> field_2810;

   public PMSoundModule() {
      super("PMSound", "Plays a sound whenever you get a private message.", Category.MISC);
      Settings.initialize(this);
   }

   @Subscribe(
      method_800 = 100
   )
   public void method_9(Event_46 var1) {
      if (!var1.method_464()) {
         if (var1.method_213() == PreType.PRE && var1.method_1033() != null && ChatUtil.method_107(var1.method_1033().getString())) {
            field_4219.executeSync(() -> Hub.field_2606.method_2(this.field_2809.getValue()).method_230(this.field_2810.getValue()));
         }
      }
   }
}
