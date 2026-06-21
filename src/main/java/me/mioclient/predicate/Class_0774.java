package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.client.UIModule;

public class Class_0774 implements Predicate {
   public UIModule field_2432;

   public Class_0774(UIModule var1) {
      super();
      this.field_2432 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2432.field_2878.method_194();
   }
}
