package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0042;
import me.mioclient.module.movement.FastWebModule;

public class Class_0895 implements Predicate {
   public FastWebModule field_2821;

   public Class_0895(FastWebModule var1) {
      super();
      this.field_2821 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2821.field_2450.getValue() != Class_0042.GRIM;
   }
}
