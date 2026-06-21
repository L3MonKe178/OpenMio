package me.mioclient.record;

import com.google.gson.annotations.SerializedName;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public final class Class_1107 {
   @SerializedName("username")
   public final String field_3415;
   @SerializedName("changed_at")
   public final String field_3416;
   @SerializedName("hidden")
   public final boolean field_3417;
   public static final SimpleDateFormat field_3418 = new SimpleDateFormat("d MMM yyyy HH:mm:ss");

   public Class_1107(String var1, String var2, boolean var3) {
      super();
      this.field_3415 = var1;
      this.field_3416 = var2;
      this.field_3417 = var3;
   }

   public String method_977() {
      if (this.field_3416 == null) {
         return "first name";
      } else {
         String var1 = this.field_3416.split("\\+")[0];

         try {
            LocalDateTime var2 = LocalDateTime.parse(var1);
            return field_3418.format(Date.from(var2.atZone(ZoneId.systemDefault()).toInstant()));
         } catch (Exception var3) {
            return var1;
         }
      }
   }

   public String method_978() {
      return this.field_3417 ? "HIDDEN" : this.field_3415;
   }

   @SerializedName("changed_at")
   public String method_979() {
      return this.field_3416;
   }

   @SerializedName("hidden")
   public boolean method_980() {
      return this.field_3417;
   }
}
