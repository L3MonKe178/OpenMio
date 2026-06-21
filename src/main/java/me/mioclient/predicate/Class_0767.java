package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.client.UIModule;

public class Class_0767 implements Predicate {
   public UIModule field_2413;

   public Class_0767(UIModule var1) {
      super();
      this.field_2413 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2413.field_2874.method_194();
   }
}
