package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_1229;
import me.mioclient.module.movement.ElytraFlyModule;

public class Class_0224 implements Predicate {
   public ElytraFlyModule field_616;

   public Class_0224(ElytraFlyModule var1) {
      super();
      this.field_616 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_616.field_4349.getValue() == Class_1229.BOUNCE && this.field_616.field_4381.method_194();
   }
}
