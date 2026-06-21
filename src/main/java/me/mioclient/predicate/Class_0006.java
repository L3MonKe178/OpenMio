package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.NameTagsModule;

public class Class_0006 implements Predicate {
   public NameTagsModule field_15;

   public Class_0006(NameTagsModule var1) {
      super();
      this.field_15 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_15.field_1728.method_194() && this.field_15.field_1733.method_194();
   }
}
