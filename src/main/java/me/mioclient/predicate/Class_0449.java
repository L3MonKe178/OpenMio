package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.client.UIModule;

public class Class_0449 implements Predicate {
   public UIModule field_1442;

   public Class_0449(UIModule var1) {
      super();
      this.field_1442 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1442.field_2878.method_194();
   }
}
