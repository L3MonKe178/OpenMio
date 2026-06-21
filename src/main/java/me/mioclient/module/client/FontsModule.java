package me.mioclient.module.client;

import java.util.concurrent.atomic.AtomicBoolean;
import me.mioclient.enum_.Class_0511;
import me.mioclient.event.Event_21;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_1016;
import me.mioclient.internal.Class_1117;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import nick.Settings;
import org.lwjgl.glfw.GLFW;

public class FontsModule extends Module {
   public static FontsModule field_364;
   public final AtomicBoolean field_365 = new AtomicBoolean(false);
   public static final Runnable field_366 = () -> {
      if (field_4219.player != null || field_4219.currentScreen instanceof Class_1117) {
         Class_1016.field_3143.method_2(Class_1016.field_3143.method_915());
      }
   };
   public Setting<String> field_367;
   public Setting<Class_0511> field_368;
   public Setting<Boolean> field_369;
   public Setting<Boolean> field_370;
   public Setting<Integer> field_371;
   public Setting<Integer> field_372;
   public Setting<Float> field_373;
   public Setting<Integer> field_374;

   public FontsModule() {
      super("Fonts", "Manages the client's font renderer.", Category.CLIENT);
      Settings.initialize(this);
      this.field_368.method_9(field_366);
      this.field_369.method_9(field_366);
      this.field_371.method_9(() -> this.field_365.set(true));
      this.field_367.method_9(field_366);
      field_364 = this;
      this.setDrawn(false);
   }

   @Subscribe
   public void method_2(Event_21 var1) {
      if (GLFW.glfwGetMouseButton(field_4219.getWindow().getHandle(), 0) != 1 && this.field_365.get()) {
         Class_1016.field_3143.method_2(Class_1016.field_3143.method_915());
         this.field_365.set(false);
      }
   }
}
