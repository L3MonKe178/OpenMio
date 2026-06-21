package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.client.UIModule;

public class Class_0477 implements Predicate {
   public UIModule field_1520;

   public Class_0477(UIModule var1) {
      super();
      this.field_1520 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1520.field_2857.method_194();
   }
}
