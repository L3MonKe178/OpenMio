package me.mioclient.internal;

import com.google.gson.JsonElement;
import me.mioclient.enum_.Class_0836;

public final class Class_0307 {
   public JsonElement field_994;

   public Class_0307() {
      super();
   }

   public void method_357() {
      this.field_994 = Class_0836.ALL.toJson();
   }

   public JsonElement method_358() {
      JsonElement var1 = this.field_994;
      this.field_994 = null;
      return var1;
   }
}
