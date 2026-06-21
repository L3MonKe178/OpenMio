package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.VoidESPModule;

public class Class_1150 implements Predicate {
   public VoidESPModule field_3544;

   public Class_1150(VoidESPModule var1) {
      super();
      this.field_3544 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3544.field_135.method_194();
   }
}
