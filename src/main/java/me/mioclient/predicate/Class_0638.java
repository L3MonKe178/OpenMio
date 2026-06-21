package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.client.UIModule;

public class Class_0638 implements Predicate {
   public UIModule field_2011;

   public Class_0638(UIModule var1) {
      super();
      this.field_2011 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2011.field_2857.method_194();
   }
}
