package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_1018 implements Class_0013 {
   ALPHABET("Alphabet"),
   SOCIALS("Socials"),
   LATENCY("Latency"),
   LENGTH("Length");

   public final String field_3170;

    Class_1018(String var3) {
      this.field_3170 = var3;
   }

   @Override
   public String getName() {
      return this.field_3170;
   }
}
