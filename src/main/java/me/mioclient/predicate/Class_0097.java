package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.client.NotificationsModule;

public class Class_0097 implements Predicate {
   public NotificationsModule field_309;

   public Class_0097(NotificationsModule var1) {
      super();
      this.field_309 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_309.field_2162.method_194();
   }
}
