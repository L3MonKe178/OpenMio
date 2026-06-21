package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.VoidESPModule;

public class Class_1253 implements Predicate {
   public VoidESPModule field_3919;

   public Class_1253(VoidESPModule var1) {
      super();
      this.field_3919 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3919.field_133.getValue();
   }
}
