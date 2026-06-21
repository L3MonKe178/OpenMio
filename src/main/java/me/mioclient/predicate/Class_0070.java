package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.misc.ExtraTabModule;

public class Class_0070 implements Predicate {
   public ExtraTabModule field_258;

   public Class_0070(ExtraTabModule var1) {
      super();
      this.field_258 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_258.field_72.method_194();
   }
}
