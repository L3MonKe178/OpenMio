package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.client.NotificationsModule;

public class Class_0831 implements Predicate {
   public NotificationsModule field_2655;

   public Class_0831(NotificationsModule var1) {
      super();
      this.field_2655 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2655.field_2162.method_194() && this.field_2655.field_2166.method_194();
   }
}
