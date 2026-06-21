package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.NameTagsModule;

public class Class_0105 implements Predicate {
   public NameTagsModule field_330;

   public Class_0105(NameTagsModule var1) {
      super();
      this.field_330 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_330.field_1728.method_194();
   }
}
