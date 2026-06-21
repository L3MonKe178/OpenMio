package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.movement.EntityControlModule;

public class Class_0203 implements Predicate {
   public EntityControlModule field_577;

   public Class_0203(EntityControlModule var1) {
      super();
      this.field_577 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_577.field_3680.method_194();
   }
}
