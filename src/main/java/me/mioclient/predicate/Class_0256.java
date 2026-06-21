package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.client.NotificationsModule;

public class Class_0256 implements Predicate {
   public NotificationsModule field_783;

   public Class_0256(NotificationsModule var1) {
      super();
      this.field_783 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_783.field_2162.method_194() && this.field_783.field_2166.method_194();
   }
}
