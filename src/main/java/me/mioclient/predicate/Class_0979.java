package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.client.UIModule;

public class Class_0979 implements Predicate {
   public UIModule field_3000;

   public Class_0979(UIModule var1) {
      super();
      this.field_3000 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3000.field_2848.method_194();
   }
}
