package me.mioclient.module.movement;

import me.mioclient.Hub;
import me.mioclient.enum_.Class_0184;
import me.mioclient.event.Event_10;
import me.mioclient.event.Event_17;
import me.mioclient.event.Event_19;
import me.mioclient.event.Event_36;
import me.mioclient.event.Event_37;
import me.mioclient.event.Event_4;
import me.mioclient.event.Event_54;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0030;
import me.mioclient.internal.Class_0043;
import me.mioclient.internal.Class_0198;
import me.mioclient.internal.Class_0552;
import me.mioclient.internal.Class_0930;
import me.mioclient.internal.Class_1016;
import me.mioclient.internal.Class_1295;
import me.mioclient.internal.Class_1303;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.abstract_.AbstractModule_28;
import me.mioclient.setting.Setting;
import nick.Settings;

public class VelocityModule extends Module {
   public static final SprintModule sprint = Hub.field_2595.method_78(SprintModule.class);
   public static final AbstractModule_28 field_2508 = Hub.field_2595.method_78(AbstractModule_28.class);
   public Setting<Class_0184> field_2509;
   public Setting<Integer> field_2510;
   public Setting<Integer> field_2511;
   public Setting<Boolean> field_2512;
   public Setting<Boolean> field_2513;
   public Setting<Boolean> field_2514;
   public Setting<Boolean> field_2515;
   public final Class_1295<Class_0184, Class_0043> field_2516;

   public VelocityModule() {
      super("Velocity", "Cancels all the pushing you receive.", Category.MOVEMENT);
      Settings.initialize(this);
      this.field_2516 = new Class_1295<>(this.field_2509);
      this.field_2516.method_2(Class_0184.PLAIN, new Class_0198(this));
      this.field_2516.method_2(Class_0184.GRIM, new Class_0552(this));
      this.field_2516.method_2(Class_0184.WALLS, new Class_0030(this));
   }

   @Override
   public String getInfo() {
      return this.field_2509.getValue() != Class_0184.PLAIN
         ? Class_1016.method_3(this.field_2509.getValue().getName())
         : new Class_1303()
            .method_2(Class_0930.method_2((float)this.field_2511.getValue().intValue(), 1))
            .method_2(Class_0930.method_2((float)this.field_2510.getValue().intValue(), 1))
            .method_9("H\u0001% V\u0001%");
   }

   @Override
   public void onDisable() {
      sprint.field_1033 = false;
      this.field_2516.method_1156().onDisable();
   }

   @Subscribe
   public void method_2(Event_10 var1) {
      this.field_2516.method_1156().method_2(var1);
   }

   @Subscribe
   public void method_5(Event_4 var1) {
      this.field_2516.method_1156().method_5(var1);
   }

   @Subscribe(
      method_800 = -200
   )
   public void method_2(Event_17 var1) {
      if (this.field_2509.getValue() != Class_0184.WALLS) {
         sprint.field_1033 = false;
      }

      this.field_2516.method_1156().method_40();
   }

   @Subscribe
   public void method_5(Event_36 var1) {
      this.field_2516.method_1156().method_5(var1);
   }

   @Subscribe
   public void method_9(Event_19 var1) {
      this.field_2516.method_1156().method_9(var1);
   }

   @Subscribe
   public void method_2(Event_37 var1) {
      this.field_2516.method_1156().method_2(var1);
   }

   @Subscribe
   public void method_2(Event_54 var1) {
      if (this.field_2514.getValue()) {
         var1.method_463();
      }
   }

   public boolean method_734() {
      if (!this.isToggled()) {
         return false;
      } else if (this.field_2516.method_1156() instanceof Class_0198 && this.field_2510.getValue() == 0 && this.field_2511.getValue() == 0) {
         return true;
      } else {
         if (this.field_2516.method_1156() instanceof Class_0552 var1 && !var1.method_581()) {
            return true;
         }

         return false;
      }
   }

   public Class_0043 method_735() {
      return this.field_2516.method_1156();
   }
}
