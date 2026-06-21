package me.mioclient.enum_;

import me.mioclient.api.Nameable;

public enum Class_0568 implements Nameable {
   NONE("None"),
   COPY("Copy"),
   IMGUR("Imgur");

   public final String field_1796;

    Class_0568(String var3) {
      this.field_1796 = var3;
   }

   @Override
   public String getName() {
      return this.field_1796;
   }
}
