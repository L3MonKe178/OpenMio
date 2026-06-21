package me.mioclient.enum_;

import me.mioclient.api.Nameable;

public enum Class_1239 implements Nameable {
   PLAIN("Plain"),
   GRIM("Grim"),
   GRIMV3("NoVelocity");

   public final String field_3893;

    Class_1239(String var3) {
      this.field_3893 = var3;
   }

   @Override
   public String getName() {
      return this.field_3893;
   }
}
