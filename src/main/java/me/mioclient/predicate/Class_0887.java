package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.client.UIModule;

public class Class_0887 implements Predicate {
   public UIModule field_2808;

   public Class_0887(UIModule var1) {
      super();
      this.field_2808 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2808.field_2857.method_194();
   }
}
