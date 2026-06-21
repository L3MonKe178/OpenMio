package me.mioclient.enum_;

import java.util.function.Supplier;
import me.mioclient.api.Class_0013;
import me.mioclient.internal.Class_0914;
import me.mioclient.internal.Class_1355;

public enum Class_0151 implements Class_0013 {
   SOLID("Solid", () -> Class_1355.field_4416),
   RAINBOW("Rainbow", () -> Class_1355.field_4417),
   GRADIENT("Gradient", () -> Class_1355.field_4418),
   BLOOM("Bloom", () -> Class_1355.field_4419);

   public final String field_439;
   public final Supplier<Class_0914> field_440;

    Class_0151(String var3, Supplier<Class_0914> var4) {
      this.field_439 = var3;
      this.field_440 = var4;
   }

   public Class_0914 method_177() {
      return this.field_440.get();
   }

   @Override
   public String getName() {
      return this.field_439;
   }
}
