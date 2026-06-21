package me.mioclient.module.render;

import me.mioclient.event.Event_3;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0585;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import nick.Settings;

public class ViewClipModule extends Module {
   public Setting<Double> field_4494;
   public Setting<Boolean> field_4495;
   public final Class_0585 field_4496;

   public ViewClipModule() {
      super("ViewClip", "Allows you to clip into blocks using 3rd person camera.", Category.RENDER);
      Settings.initialize(this);
      this.field_4496 = new Class_0585(Float.intBitsToFloat(1067869798), true);
      this.setDrawn(false);
   }

   @Subscribe
   public void method_9(Event_3 var1) {
      if (field_4219.gameRenderer.getCamera().isThirdPerson()) {
         this.field_4496.method_3(true);
      } else {
         this.field_4496.method_36(Float.intBitsToFloat(1065353216));
      }
   }
}
