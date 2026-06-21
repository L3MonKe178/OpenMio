package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.NameTagsModule;

public class Class_1251 implements Predicate {
   public NameTagsModule field_3917;

   public Class_1251(NameTagsModule var1) {
      super();
      this.field_3917 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3917.field_1728.method_194() && this.field_3917.field_1733.method_194();
   }
}
