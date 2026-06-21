package me.mioclient.internal;

import com.google.gson.annotations.SerializedName;
import me.mioclient.api.Nameable;

public class Class_0580 implements Nameable {
   @SerializedName("id")
   public final String field_1834;
   @SerializedName("x")
   public final int field_1835;
   @SerializedName("z")
   public final int field_1836;
   @SerializedName("dimension")
   public final String field_1837;
   @SerializedName("server")
   public final String field_1838;

   public Class_0580(String var1, int var2, int var3, String var4, String var5) {
      super();
      this.field_1834 = var1;
      this.field_1835 = var2;
      this.field_1836 = var3;
      this.field_1837 = var4;
      this.field_1838 = var5;
   }

   public String method_599() {
      int var10000 = this.field_1835;
      int var10001 = this.field_1836;
      return new TextBuilder().method_2(var10001).method_2(var10000).method_9("\u0001, \u0001.");
   }

   public double method_380() {
      return (double)this.field_1835;
   }

   public double method_396() {
      return (double)this.field_1836;
   }

   public String method_600() {
      return this.field_1837;
   }

   public String method_106() {
      return this.field_1838;
   }

   @Override
   public String getName() {
      return this.field_1834;
   }
}
