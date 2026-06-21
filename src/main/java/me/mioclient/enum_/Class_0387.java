package me.mioclient.enum_;

import me.mioclient.api.Nameable;

public enum Class_0387 implements Nameable {
   HEAD("Head", 0.85F),
   BODY("Body", 0.5F),
   LEGS("Legs", 0.0F);

   public final String field_1254;
   public final float field_1255;

    Class_0387(String var3, float var4) {
      this.field_1254 = var3;
      this.field_1255 = var4;
   }

   @Override
   public String getName() {
      return this.field_1254;
   }

   public float method_438() {
      return this.field_1255;
   }
}
