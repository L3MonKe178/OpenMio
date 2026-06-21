package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.client.UIModule;

public class Class_1052 implements Predicate {
   public UIModule field_3248;

   public Class_1052(UIModule var1) {
      super();
      this.field_3248 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3248.field_2874.method_194();
   }
}
