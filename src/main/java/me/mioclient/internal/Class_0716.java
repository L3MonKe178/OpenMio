package me.mioclient.internal;

import me.mioclient.api.MioAPI;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_10;
import me.mioclient.event.Event_16;
import me.mioclient.event.Event_17;
import me.mioclient.event.Event_19;
import me.mioclient.event.Event_39;
import me.mioclient.event.Event_4;
import me.mioclient.event.Event_9;
import me.mioclient.module.movement.ElytraFlyModule;

public abstract class Class_0716 implements MioAPI {
   public final ElytraFlyModule field_2275;

   public Class_0716(ElytraFlyModule var1) {
      super();
      this.field_2275 = var1;
   }

   public abstract void method_2(Event_1 var1);

   public abstract void method_2(Event_9 var1);

   public abstract void method_5(Event_4 var1);

   public abstract void method_2(Event_10 var1);

   public abstract void method_9(Event_19 var1);

   public abstract void method_2(Event_17 var1);

   public abstract void method_9(Event_39 var1);

   public void method_5(Event_16 var1) {
   }

   public void onEnable() {
   }

   public void onDisable() {
   }
}
