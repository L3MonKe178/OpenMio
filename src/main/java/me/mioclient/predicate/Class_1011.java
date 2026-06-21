package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.movement.EntityControlModule;

public class Class_1011 implements Predicate {
   public EntityControlModule field_3116;

   public Class_1011(EntityControlModule var1) {
      super();
      this.field_3116 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3116.field_3680.method_194();
   }
}
