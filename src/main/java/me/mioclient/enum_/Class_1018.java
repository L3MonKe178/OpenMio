package me.mioclient.enum_;

import me.mioclient.api.Nameable;

public enum Class_1018 implements Nameable {
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
