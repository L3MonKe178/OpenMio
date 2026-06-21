package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.client.UIModule;

public class Class_1247 implements Predicate {
   public UIModule field_3914;

   public Class_1247(UIModule var1) {
      super();
      this.field_3914 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3914.field_2857.method_194() && this.field_3914.field_2861.method_194();
   }
}
