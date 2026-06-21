package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.VoidESPModule;

public class Class_1362 implements Predicate {
   public VoidESPModule field_4433;

   public Class_1362(VoidESPModule var1) {
      super();
      this.field_4433 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_4433.field_135.method_194();
   }
}
