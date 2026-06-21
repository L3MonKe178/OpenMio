package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.movement.EntityControlModule;

public class Class_0669 implements Predicate {
   public EntityControlModule field_2146;

   public Class_0669(EntityControlModule var1) {
      super();
      this.field_2146 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2146.field_3680.method_194();
   }
}
