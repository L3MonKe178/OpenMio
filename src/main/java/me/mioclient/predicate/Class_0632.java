package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.combat.TriggerModule;

public class Class_0632 implements Predicate {
   public TriggerModule field_2006;

   public Class_0632(TriggerModule var1) {
      super();
      this.field_2006 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2006.field_3061.method_194();
   }
}
