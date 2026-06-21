package me.mioclient.record;

import com.google.gson.annotations.SerializedName;
import me.mioclient.api.Nameable;

public final class Class_0559 implements Nameable {
   @SerializedName("sha")
   public final String field_1772;
   @SerializedName("message")
   public final String field_1773;
   @SerializedName("author")
   public final String field_1774;

   public Class_0559(String var1, String var2, String var3) {
      super();
      this.field_1772 = var1;
      this.field_1773 = var2;
      this.field_1774 = var3;
   }

   @Override
   public String getName() {
      return this.field_1773;
   }

   public String method_586() {
      return this.field_1772.substring(0, 6);
   }

   @SerializedName("sha")
   public String method_587() {
      return this.field_1772;
   }

   @SerializedName("message")
   public String method_588() {
      return this.field_1773;
   }

   @SerializedName("author")
   public String method_589() {
      return this.field_1774;
   }
}
