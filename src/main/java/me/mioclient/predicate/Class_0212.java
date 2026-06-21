package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.combat.ArrowsModule;

public class Class_0212 implements Predicate {
   public ArrowsModule field_595;

   public Class_0212(ArrowsModule var1) {
      super();
      this.field_595 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return !this.field_595.field_654.getValue();
   }
}
