package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0042;
import me.mioclient.module.movement.FastWebModule;

public class Class_0233 implements Predicate {
   public FastWebModule field_644;

   public Class_0233(FastWebModule var1) {
      super();
      this.field_644 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_644.field_2450.getValue() == Class_0042.PLAIN;
   }
}
