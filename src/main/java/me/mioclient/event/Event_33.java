package me.mioclient.event;

import me.mioclient.enum_.PreType;
import me.mioclient.internal.Class_0605;
import net.minecraft.entity.Entity;

public class Event_33 extends Class_0605 {
   public final PreType field_1339;
   public Entity field_581;

   public Event_33(PreType var1, Entity var2) {
      super();
      this.field_1339 = var1;
      this.field_581 = var2;
   }

   public Entity method_11() {
      return this.field_581;
   }

   public PreType method_213() {
      return this.field_1339;
   }
}
