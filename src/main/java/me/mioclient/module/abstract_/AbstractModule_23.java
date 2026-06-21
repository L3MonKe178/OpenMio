package me.mioclient.module.abstract_;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_0274;
import me.mioclient.enum_.Class_1272;
import me.mioclient.event.Event_1;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0723;
import me.mioclient.internal.Class_0747;
import me.mioclient.internal.Class_1016;
import me.mioclient.setting.BooleanSetting;
import me.mioclient.setting.CustomSetting;
import me.mioclient.setting.Setting;

public class AbstractModule_23 extends AbstractModule_26 {
   public final Setting<Boolean> field_1379 = this.add(new BooleanSetting("OnlyBound", false));
   public final Setting<Class_1272> field_1380 = this.add(new CustomSetting<>("Sort", Class_1272.LENGTH));
   public final List<Class_0747> field_1381 = new ArrayList<>();

   public AbstractModule_23() {
      super("ModuleList", "arraylist");
      this.method_2(new Class_0723(this, this.field_1381));
      this.method_14().method_2(Class_0274.TOP_RIGHT);
   }

   @Override
   public void onEnable() {
      this.field_1381.clear();
      ((List<me.mioclient.module.Module>)Hub.field_2599.getRegistry()).stream().map(var1 -> new Class_0747(this, var1)).forEach(this.field_1381::add);
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if (this.field_1380.getValue() == Class_1272.ALPHABET) {
         this.field_1381.sort(Comparator.comparing(var0 -> var0.method_65().getInfoString()));
      } else {
         this.field_1381.sort(Comparator.comparingDouble(var0 -> (double)(-Class_1016.field_3143.method_221(var0.method_65().getInfoString()))));
      }
   }
}
