package me.mioclient.internal;

import java.util.concurrent.ConcurrentHashMap;
import me.mioclient.api.MioAPI;
import me.mioclient.event.Event_45;
import me.mioclient.event.Subscribe;
import me.mioclient.module.Module;

public class Class_0508 implements MioAPI {
   public final ConcurrentHashMap<Module, Boolean> field_1614 = new ConcurrentHashMap<>();
   public float field_1615 = Float.intBitsToFloat(1065353216);

   public Class_0508() {
      super();
      field_4220.method_14(this);
   }

   @Subscribe
   public void method_2(Event_45 var1) {
      if (!var1.method_65().isToggled()) {
         this.method_38(var1.method_65());
      }
   }

   public void method_38(Module var1) {
      this.field_1614.compute(var1, (var1x, var2) -> {
         if (Boolean.FALSE.equals(var2)) {
            this.method_114(Float.intBitsToFloat(1065353216));
         }

         return true;
      });
   }

   public void method_2(Module var1, float var2) {
      this.field_1614.compute(var1, (var0, var1x) -> false);
      this.method_114(var2);
   }

   public float method_537() {
      return this.field_1615;
   }

   public void method_114(float var1) {
      this.field_1615 = var1;
   }
}
