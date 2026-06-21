package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.client.UIModule;

public class Class_1144 implements Predicate {
   public UIModule field_3540;

   public Class_1144(UIModule var1) {
      super();
      this.field_3540 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3540.field_2878.method_194();
   }
}
