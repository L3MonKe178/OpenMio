package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_0184 implements Class_0013 {
   PLAIN("Plain"),
   GRIM("Grim"),
   WALLS("Walls");

   public final String field_514;

    Class_0184(String var3) {
      this.field_514 = var3;
   }

   @Override
   public String getName() {
      return this.field_514;
   }
}
