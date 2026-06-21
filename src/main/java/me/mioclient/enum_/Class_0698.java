package me.mioclient.enum_;

import me.mioclient.api.Nameable;

public enum Class_0698 implements Nameable {
   USD("USD"),
   EUR("EUR"),
   RUB("RUB"),
   CNY("CNY"),
   TRY("TRY"),
   JPY("JPY"),
   PLN("PLN"),
   BRL("BRL");

   public final String field_2221;

    Class_0698(String var3) {
      this.field_2221 = var3;
   }

   @Override
   public String getName() {
      return this.field_2221;
   }
}
