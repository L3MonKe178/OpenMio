package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.NameTagsModule;

public class Class_0953 implements Predicate {
   public NameTagsModule field_2955;

   public Class_0953(NameTagsModule var1) {
      super();
      this.field_2955 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2955.field_1713.method_194() && this.field_2955.field_1716.getValue();
   }
}
