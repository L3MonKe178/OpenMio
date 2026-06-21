package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.abstract_.AbstractModule_42;

public class Class_0738 implements Predicate {
   public AbstractModule_42 field_2352;

   public Class_0738(AbstractModule_42 var1) {
      super();
      this.field_2352 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2352.field_777.method_194();
   }
}
