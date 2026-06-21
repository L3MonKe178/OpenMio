package me.mioclient.module.misc;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import nick.Settings;

public class UnfocusedFPSModule extends Module {
   public Setting<Integer> field_2211;

   public UnfocusedFPSModule() {
      super("UnfocusedFPS", "Lowers your FPS when Minecraft is in the background.", Category.MISC);
      Settings.initialize(this);
      this.setDrawn(false);
   }

   public int method_681() {
      GraphicsEnvironment var1 = GraphicsEnvironment.getLocalGraphicsEnvironment();
      GraphicsDevice[] var2 = var1.getScreenDevices();
      Function<GraphicsDevice, Integer> var3 = var0 -> var0.getDisplayMode().getRefreshRate();
      return Arrays.stream(var2).max(Comparator.comparing(var3)).<Integer>map(var3).orElse(60);
   }
}
