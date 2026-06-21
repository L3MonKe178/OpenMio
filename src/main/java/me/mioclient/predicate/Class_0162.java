package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.client.NotificationsModule;

public class Class_0162 implements Predicate {
   public NotificationsModule field_475;

   public Class_0162(NotificationsModule var1) {
      super();
      this.field_475 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_475.field_2162.method_194();
   }
}
