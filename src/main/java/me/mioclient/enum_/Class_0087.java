package me.mioclient.enum_;

import me.mioclient.api.Nameable;

public enum Class_0087 implements Nameable {
   NAME("Name"),
   ITEM("Item");

   public final String field_295;

    Class_0087(String var3) {
      this.field_295 = var3;
   }

   @Override
   public String getName() {
      return this.field_295;
   }
}
