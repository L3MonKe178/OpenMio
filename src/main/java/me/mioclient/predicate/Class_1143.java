package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.combat.TriggerModule;

public class Class_1143 implements Predicate {
   public TriggerModule field_3539;

   public Class_1143(TriggerModule var1) {
      super();
      this.field_3539 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3539.field_3061.method_194() && this.field_3539.field_3062.method_194();
   }
}
