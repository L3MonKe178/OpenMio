package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_0801 implements Class_0013 {
   ALPHABET("Alphabet"),
   COUNT("Count"),
   LENGTH("Length");

   public final String field_2529;

    Class_0801(String var3) {
      this.field_2529 = var3;
   }

   @Override
   public String getName() {
      return this.field_2529;
   }
}
