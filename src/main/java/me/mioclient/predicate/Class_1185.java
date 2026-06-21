package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.client.UIModule;

public class Class_1185 implements Predicate {
   public UIModule field_3668;

   public Class_1185(UIModule var1) {
      super();
      this.field_3668 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3668.field_2848.method_194() && this.field_3668.field_2849.getValue() > 0;
   }
}
