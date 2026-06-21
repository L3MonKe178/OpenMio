package me.mioclient.module.render;

import java.awt.Color;
import me.mioclient.event.Event_26;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_1117;
import me.mioclient.internal.Class_1299;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ChatScreen;
import nick.Settings;

public class BlurModule extends Module {
   public Setting<Integer> field_2466;

   public BlurModule() {
      super("Blur", "Blurs the background in GUI's.", Category.RENDER);
      Settings.initialize(this);
   }

   @Subscribe(
      method_800 = -200
   )
   public void method_2(Event_26 var1) {
      this.method_7(var1.method_881());
   }

   public void method_7(DrawContext var1) {
      if (!(field_4219.currentScreen instanceof Class_1117 var4)) {
         boolean var2 = field_4219.currentScreen != null && !(field_4219.currentScreen instanceof ChatScreen);
         if (var2) {
            Class_1299.method_2(
               () -> var1.fill(
                     0,
                     0,
                     field_4219.getWindow().getScaledWidth(),
                     field_4219.getWindow().getScaledHeight(),
                     new Color(0.0F, 0.0F, 0.0F, Float.intBitsToFloat(1065353216)).hashCode()
                  ),
               (float)this.method_730()
            );
         }
      }
   }

   public int method_730() {
      return this.field_2466.getValue();
   }
}
