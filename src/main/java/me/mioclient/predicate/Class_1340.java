package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.client.UIModule;

public class Class_1340 implements Predicate {
   public UIModule field_4332;

   public Class_1340(UIModule var1) {
      super();
      this.field_4332 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_4332.field_2864.method_194();
   }
}
