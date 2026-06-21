package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.client.UIModule;

public class Class_0028 implements Predicate {
   public UIModule field_45;

   public Class_0028(UIModule var1) {
      super();
      this.field_45 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_45.field_2874.method_194();
   }
}
