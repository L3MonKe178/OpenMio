package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.client.UIModule;

public class Class_1301 implements Predicate {
   public UIModule field_4206;

   public Class_1301(UIModule var1) {
      super();
      this.field_4206 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_4206.field_2857.method_194();
   }
}
