package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.misc.ExtraTabModule;

public class Class_1343 implements Predicate {
   public ExtraTabModule field_4335;

   public Class_1343(ExtraTabModule var1) {
      super();
      this.field_4335 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_4335.field_72.method_194();
   }
}
