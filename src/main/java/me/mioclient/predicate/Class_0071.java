package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.combat.TriggerModule;

public class Class_0071 implements Predicate {
   public TriggerModule field_259;

   public Class_0071(TriggerModule var1) {
      super();
      this.field_259 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_259.field_3061.method_194();
   }
}
