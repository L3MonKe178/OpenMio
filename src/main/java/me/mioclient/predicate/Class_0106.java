package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.NameTagsModule;

public class Class_0106 implements Predicate {
   public NameTagsModule field_331;

   public Class_0106(NameTagsModule var1) {
      super();
      this.field_331 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_331.field_1728.method_194();
   }
}
