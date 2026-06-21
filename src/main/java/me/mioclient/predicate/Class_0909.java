package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.combat.OffhandModule;

public class Class_0909 implements Predicate {
   public OffhandModule field_2885;

   public Class_0909(OffhandModule var1) {
      super();
      this.field_2885 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2885.field_1968.method_194();
   }
}
