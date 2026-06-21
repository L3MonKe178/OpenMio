package me.mioclient.enum_;

import me.mioclient.api.Nameable;

public enum Class_0574 implements Nameable {
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
