package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_1239 implements Class_0013 {
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
