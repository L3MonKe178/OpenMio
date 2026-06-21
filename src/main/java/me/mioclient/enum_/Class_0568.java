package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_0568 implements Class_0013 {
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
