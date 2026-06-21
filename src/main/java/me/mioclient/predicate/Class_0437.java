package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.combat.OffhandModule;

public class Class_0437 implements Predicate {
   public OffhandModule field_1378;

   public Class_0437(OffhandModule var1) {
      super();
      this.field_1378 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1378.field_1968.method_194();
   }
}
