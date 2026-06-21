package me.mioclient.enum_;

import me.mioclient.api.Nameable;

public enum Class_0511 implements Nameable {
   PLAIN("Plain"),
   BOLD("Bold"),
   ITALIC("Italic"),
   BOLDITALIC("BoldItalic");

   public final String field_1623;

    Class_0511(String var3) {
      this.field_1623 = var3;
   }

   @Override
   public String getName() {
      return this.field_1623;
   }
}
