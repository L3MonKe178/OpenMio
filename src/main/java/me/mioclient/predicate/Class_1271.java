package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_1229;
import me.mioclient.module.movement.ElytraFlyModule;

public class Class_1271 implements Predicate {
   public ElytraFlyModule field_4036;

   public Class_1271(ElytraFlyModule var1) {
      super();
      this.field_4036 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_4036.field_4349.getValue() == Class_1229.BOUNCE && this.field_4036.field_4381.method_194();
   }
}
