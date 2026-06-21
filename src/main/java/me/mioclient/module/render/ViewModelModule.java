package me.mioclient.module.render;

import me.mioclient.internal.Class_0217;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.util.Hand;
import nick.Settings;

public class ViewModelModule extends Module {
   public static final String field_1905 = "°";
   public Setting<Boolean> field_1906;
   public Setting<Boolean> field_1907;
   public Setting<Boolean> field_1908;
   public Setting<Boolean> field_1909;
   public Setting<Boolean> field_1910;
   public Setting<Boolean> field_1911;
   public Setting<Boolean> field_1912;
   public Setting<Float> field_1913;
   public Setting<Float> field_1914;
   public Setting<Float> field_1915;
   public Setting<Float> field_1916;
   public Setting<Float> field_1917;
   public Setting<Float> field_1918;
   public Setting<Float> field_1919;
   public Setting<Float> field_1920;
   public Setting<Float> field_1921;
   public Setting<Boolean> field_1922;
   public Setting<Float> field_1923;
   public Setting<Float> field_1924;
   public Setting<Float> field_1925;
   public Setting<Float> field_1926;
   public Setting<Float> field_1927;
   public Setting<Float> field_1928;
   public Setting<Float> field_1929;
   public Setting<Float> field_1930;
   public Setting<Float> field_1931;
   public Setting<Boolean> field_1932;
   public Setting<Float> field_1933;
   public Setting<Boolean> field_1934;
   public Setting<Boolean> field_1935;
   public Setting<Boolean> field_1936;
   public Setting<Boolean> field_1937;
   public Setting<Float> field_1938;
   public Setting<Boolean> field_1939;
   public Setting<Float> field_1940;
   public Setting<Boolean> field_1941;
   public Setting<Integer> field_1942;

   public ViewModelModule() {
      super("ViewModel", "Transforms your 1st person view model.", Category.RENDER);
      Settings.initialize(this);
      this.field_1937.method_31("SwingMainHand");
      this.field_1939.method_31("SwingOffHand");
      this.field_1940.method_31("SwingProgressAmountOffHand");
      this.field_1906.method_9(() -> {
         if (this.field_1906.getValue()) {
            field_4219.setScreen(new Class_0217(this));
            this.field_1906.method_78(false);
         }
      });
   }

   public float method_2(Hand var1, float var2) {
      if (!this.isToggled() || !this.field_1935.getValue()) {
         return var2;
      } else if (var1 == Hand.MAIN_HAND && this.field_1937.getValue()) {
         return Math.max(this.field_1936.getValue() ? 0.0F : var2, this.field_1938.getValue());
      } else {
         return var1 == Hand.OFF_HAND && this.field_1939.getValue() ? Math.max(this.field_1936.getValue() ? 0.0F : var2, this.field_1940.getValue()) : var2;
      }
   }
}
