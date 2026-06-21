package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.internal.RotationManager;
import me.mioclient.module.movement.HoleSnapModule;

public class Class_0020 implements Predicate {
   public HoleSnapModule field_30;

   public Class_0020(HoleSnapModule var1) {
      super();
      this.field_30 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return !RotationManager.method_513();
   }
}
