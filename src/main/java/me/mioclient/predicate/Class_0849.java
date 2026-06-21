package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.HoleESPModule;

public class Class_0849 implements Predicate {
   public HoleESPModule field_2742;

   public Class_0849(HoleESPModule var1) {
      super();
      this.field_2742 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2742.field_4329.method_194();
   }
}
