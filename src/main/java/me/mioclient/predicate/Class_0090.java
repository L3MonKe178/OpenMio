package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0574;
import me.mioclient.module.render.AmbienceModule;

public class Class_0090 implements Predicate {
   public AmbienceModule field_300;

   public Class_0090(AmbienceModule var1) {
      super();
      this.field_300 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_300.field_207.getValue() == Class_0574.SCREEN;
   }
}
