package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.client.UIModule;

public class Class_0932 implements Predicate {
   public UIModule field_2916;

   public Class_0932(UIModule var1) {
      super();
      this.field_2916 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2916.field_2878.method_194();
   }
}
