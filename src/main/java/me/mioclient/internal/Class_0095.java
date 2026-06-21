package me.mioclient.internal;

import me.mioclient.api.Class_1309;
import me.mioclient.event.Event_3;
import me.mioclient.event.Subscribe;
import me.mioclient.module.render.ZoomModule;

public class Class_0095 implements Class_1309 {
   public final ZoomModule field_307;

   public Class_0095(ZoomModule var1) {
      super();
      this.field_307 = var1;
   }

   @Subscribe
   public void method_9(Event_3 var1) {
      int var2 = this.field_307.method_1175();
      this.field_307.method_231(var2);
      field_4219.options.smoothCameraEnabled = this.field_307.isToggled() && this.field_307.field_4306.getValue();
      if (var2 >= this.field_307.field_3076 && !this.field_307.isToggled()) {
         field_4220.method_17(this);
         this.field_307.field_1538 = false;
      } else {
         this.field_307.field_1538 = true;
      }
   }
}
