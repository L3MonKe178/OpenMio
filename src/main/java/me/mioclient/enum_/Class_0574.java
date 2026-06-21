package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_0574 implements Class_0013 {
   SCREEN("Screen"),
   GAMMA("Gamma"),
   SKY("Sky"),
   POTION("Potion"),
   NONE("None");

   public final String field_1812;

    Class_0574(String var3) {
      this.field_1812 = var3;
   }

   @Override
   public String getName() {
      return this.field_1812;
   }
}
