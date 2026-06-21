package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.movement.EntityControlModule;

public class Class_1074 implements Predicate {
   public EntityControlModule field_3294;

   public Class_1074(EntityControlModule var1) {
      super();
      this.field_3294 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3294.field_3680.method_194();
   }
}
