package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.client.UIModule;

public class Class_0804 implements Predicate {
   public UIModule field_2536;

   public Class_0804(UIModule var1) {
      super();
      this.field_2536 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2536.field_2864.method_194();
   }
}
