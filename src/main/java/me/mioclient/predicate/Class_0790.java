package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.NameTagsModule;

public class Class_0790 implements Predicate {
   public NameTagsModule field_2486;

   public Class_0790(NameTagsModule var1) {
      super();
      this.field_2486 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2486.field_1728.method_194();
   }
}
