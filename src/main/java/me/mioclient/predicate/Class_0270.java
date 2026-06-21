package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.client.UIModule;

public class Class_0270 implements Predicate {
   public UIModule field_841;

   public Class_0270(UIModule var1) {
      super();
      this.field_841 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_841.field_2878.method_194();
   }
}
