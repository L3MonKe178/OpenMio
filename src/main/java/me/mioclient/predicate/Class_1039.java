package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.combat.TriggerModule;

public class Class_1039 implements Predicate {
   public TriggerModule field_3219;

   public Class_1039(TriggerModule var1) {
      super();
      this.field_3219 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3219.field_3061.method_194();
   }
}
