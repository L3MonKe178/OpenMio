package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.client.NotificationsModule;

public class Class_0141 implements Predicate {
   public NotificationsModule field_415;

   public Class_0141(NotificationsModule var1) {
      super();
      this.field_415 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_415.field_2162.method_194();
   }
}
