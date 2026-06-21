package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.NameTagsModule;

public class Class_0677 implements Predicate {
   public NameTagsModule field_2174;

   public Class_0677(NameTagsModule var1) {
      super();
      this.field_2174 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2174.field_1728.method_194();
   }
}
