package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.combat.AutoExpModule;

public class Class_0354 implements Predicate {
   public AutoExpModule field_1157;

   public Class_0354(AutoExpModule var1) {
      super();
      this.field_1157 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1157.field_3424.method_194();
   }
}
