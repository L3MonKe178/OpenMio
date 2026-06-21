package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.combat.OffhandModule;

public class Class_0603 implements Predicate {
   public OffhandModule field_1876;

   public Class_0603(OffhandModule var1) {
      super();
      this.field_1876 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1876.field_1968.method_194();
   }
}
