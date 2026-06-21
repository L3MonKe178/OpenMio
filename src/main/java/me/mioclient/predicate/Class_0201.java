package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.player.ScaffoldModule;

public class Class_0201 implements Predicate {
   public ScaffoldModule field_562;

   public Class_0201(ScaffoldModule var1) {
      super();
      this.field_562 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_562.field_158.method_194() && this.field_562.field_162.getValue();
   }
}
