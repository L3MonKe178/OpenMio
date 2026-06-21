package me.mioclient.deobf;

import java.util.Objects;
import me.mioclient.api.Nameable;
import me.mioclient.api.MioAPI;

public class Named implements MioAPI, Nameable {
   public final String field_327;
   public String field_2189 = "";

   public Named(String var1) {
      super();
      this.field_327 = var1;
   }

   @Override
   public String getName() {
      return this.field_327;
   }

   public String getDescription() {
      return this.field_2189;
   }

   public void setDescription(String var1) {
      this.field_2189 = var1;
   }

   public boolean isDescriptionPresent() {
      return !Objects.equals(this.field_2189, "");
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         Named var2 = (Named)var1;
         return Objects.equals(this.field_327, var2.field_327);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.field_327);
   }
}
