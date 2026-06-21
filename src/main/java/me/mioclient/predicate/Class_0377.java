package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.movement.EntityControlModule;

public class Class_0377 implements Predicate {
   public EntityControlModule field_1225;

   public Class_0377(EntityControlModule var1) {
      super();
      this.field_1225 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1225.field_3680.method_194();
   }
}
