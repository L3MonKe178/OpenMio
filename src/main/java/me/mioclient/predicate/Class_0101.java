package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.client.UIModule;

public class Class_0101 implements Predicate {
   public UIModule field_322;

   public Class_0101(UIModule var1) {
      super();
      this.field_322 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_322.field_2864.method_194();
   }
}
