package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0574;
import me.mioclient.module.render.AmbienceModule;

public class Class_0528 implements Predicate {
   public AmbienceModule field_1667;

   public Class_0528(AmbienceModule var1) {
      super();
      this.field_1667 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1667.field_207.getValue() == Class_0574.SKY;
   }
}
