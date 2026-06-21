package me.mioclient.module.client;

import java.awt.Color;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicBoolean;
import me.mioclient.Hub;
import me.mioclient.clickgui.ClickGuiScreen;
import me.mioclient.enum_.Class_0046;
import me.mioclient.enum_.Class_0650;
import me.mioclient.enum_.Class_0655;
import me.mioclient.enum_.Class_1200;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_21;
import me.mioclient.event.Event_4;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0211;
import me.mioclient.internal.Class_0406;
import me.mioclient.internal.Class_1032;
import me.mioclient.internal.Class_1117;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.record.Class_0702;
import me.mioclient.setting.Setting;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.network.packet.s2c.common.DisconnectS2CPacket;
import nick.Settings;
import org.lwjgl.glfw.GLFW;

public final class UIModule extends Module {
   public static UIModule field_2843;
   public final AtomicBoolean field_2844 = new AtomicBoolean(false);
   public Setting<Boolean> field_2845;
   public Setting<String> field_2846;
   public Setting<Class_0650> field_2847;
   public Setting<Boolean> field_2848;
   public Setting<Integer> field_2849;
   public Setting<Boolean> field_2850;
   public Setting<Integer> field_2851;
   public Setting<Integer> field_2852;
   public Setting<Integer> field_2853;
   public Setting<Float> field_2854;
   public Setting<Float> field_2855;
   public Setting<Boolean> field_2856;
   public Setting<Boolean> field_2857;
   public Setting<Boolean> field_2858;
   public Setting<Boolean> field_2859;
   public Setting<Boolean> field_2860;
   public Setting<Boolean> field_2861;
   public Setting<Color> field_2862;
   public Setting<Float> field_2863;
   public Setting<Boolean> field_2864;
   public Setting<Boolean> field_2865;
   public Setting<Class_0211> field_2866;
   public Setting<Float> field_2867;
   public Setting<Boolean> field_2868;
   public Setting<Class_0211> field_2869;
   public Setting<Float> field_2870;
   public Setting<Boolean> field_2871;
   public Setting<Class_0211> field_2872;
   public Setting<Float> field_2873;
   public Setting<Boolean> field_2874;
   public Setting<Boolean> field_2875;
   public Setting<Color> field_2876;
   public Setting<Color> field_2877;
   public Setting<Boolean> field_2878;
   public Setting<Color> field_2879;
   public Setting<Color> field_2880;
   public Setting<Color> field_2881;
   public Setting<Color> field_2882;
   public Setting<Color> field_2883;
   public Setting<Color> field_2884;

   public UIModule() {
      super("UI", "Displays the client's click gui.", Category.CLIENT);
      Settings.initialize(this);
      field_4220.method_5(UIModule.class);
      this.setDrawn(false);
      this.field_2845.method_9(() -> {
         if (this.field_2845.getValue()) {
            Hub.method_755().method_997();
            this.field_2845.method_78(false);
         }
      });
      this.field_2852.method_9(() -> this.field_2844.set(true));
      this.modifyKeybind(var0 -> var0.method_9(344));
      field_2843 = this;
      this.field_2866.method_31("HoverSound");
      this.field_2867.method_31("HoverVolume");
      this.field_2869.method_31("LeftClickSound");
      this.field_2870.method_31("LeftClickVolume");
      this.field_2872.method_31("RightClickSound");
      this.field_2873.method_31("RightClickVolume");
      if (!Class_0655.WINTER.method_29(LocalDate.now().getMonthValue())) {
         this.field_2856.method_78(false);
         this.unregister(this.field_2856);
      }

      this.field_2846.method_9(() -> {
         if (!this.field_2846.getValue().equals(Class_1032.method_927())) {
            Class_1032.method_270(this.field_2846.getValue());
         }
      });
      this.field_2846.method_7(true);
   }

   @Override
   public void onEnable() {
      if (this.method_535()) return;
      if (field_4219.currentScreen instanceof ClickGuiScreen) return;
      Runnable open = () -> field_4219.setScreen(ClickGuiScreen.instance());
      if (field_4219.currentScreen instanceof ChatScreen) {
         Hub.field_2619.method_2(open, 0);
      } else {
         open.run();
      }
   }

   @Override
   public void onDisable() {
      if (field_4219.currentScreen instanceof ClickGuiScreen screen) {
         screen.close();
      }
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if (!(field_4219.currentScreen instanceof Class_1117) && !(field_4219.currentScreen instanceof ClickGuiScreen)) {
         this.method_68();
      }
   }

   @Subscribe
   public void method_2(Event_21 var1) {
      if (GLFW.glfwGetMouseButton(field_4219.getWindow().getHandle(), 0) != 1 && this.field_2844.get()) {
         Hub.method_755().method_997();
         this.field_2845.method_78(false);
         this.field_2844.set(false);
      }
   }

   @Subscribe
   public void method_5(Event_4 var1) {
      if (var1.method_127() instanceof DisconnectS2CPacket) {
         this.disable();
      }
   }

   @Subscribe
   public static void method_9(Event_21 var0) {
      if (!(field_4219.currentScreen instanceof Class_1117) && !(field_4219.currentScreen instanceof ClickGuiScreen)) {
         Class_1200.STANDARD.method_580();
      }
   }

   @Override
   public Class_0702 getKeybind() {
      Class_0702 var1 = super.getKeybind();
      if (var1.method_78() != Class_0046.TOGGLE) {
         var1 = var1.method_2(Class_0046.TOGGLE);
         this.setKeybind(var1);
      }

      return var1;
   }
}
