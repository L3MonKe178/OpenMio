package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.client.NotificationsModule;

public class Class_1075 implements Predicate {
   public NotificationsModule field_3295;

   public Class_1075(NotificationsModule var1) {
      super();
      this.field_3295 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3295.field_2162.method_194() && this.field_3295.field_2166.method_194();
   }
}
