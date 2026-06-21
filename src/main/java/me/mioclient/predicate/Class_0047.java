package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.HitmarkerModule;

public class Class_0047 implements Predicate {
   public HitmarkerModule field_98;

   public Class_0047(HitmarkerModule var1) {
      super();
      this.field_98 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_98.field_1604.method_194();
   }
}
