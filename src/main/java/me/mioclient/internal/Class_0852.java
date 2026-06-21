package me.mioclient.internal;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import me.mioclient.api.MioAPI;
import me.mioclient.enum_.Class_0836;
import me.mioclient.record.Class_0371;

public final class Class_0852 extends Registry<Class_0371> implements MioAPI {
   public static final String field_2747 = ".json";
   public final Class_0836 field_1036;
   public final Path field_2748;

   public Class_0852(Class_0836 var1) {
      super();
      this.field_2748 = var1.method_774();
      this.field_1036 = var1;
   }

   public void method_56() {
      this.field_3243.clear();

      try {
         for (File var4 : Objects.requireNonNull(this.field_2748.toFile().listFiles())) {
            if (var4.getName().endsWith(".json") && var4.length() > 0L) {
               try {
                  JsonObject var5 = JsonParser.parseString(Class_1222.method_9(var4.toPath())).getAsJsonObject();
                  if (var5 != null) {
                     this.field_3243.add(new Class_0371(var4.getName().replace(".json", ""), var5));
                  }
               } catch (Throwable var6) {
                  var6.printStackTrace();
               }
            }
         }
      } catch (Exception var7) {
         var7.printStackTrace();
      }
   }

   public boolean method_465(String var1) {
      synchronized ((List<Class_0371>)this.getRegistry()) {
         for (Class_0371 var4 : (List<Class_0371>)this.getRegistry()) {
            if (var4.getName().equalsIgnoreCase(var1)) {
               this.field_1036.fromJson(var4.method_415());
               return true;
            }
         }

         return false;
      }
   }

   public boolean method_493(String var1) {
      this.field_2748.resolve(new TextBuilder().method_2((Object)var1).method_9("\u0001.json")).toFile().delete();
      return this.field_3243.removeIf(var1x -> var1x.getName().equalsIgnoreCase(var1));
   }

   public boolean method_78(String var1, String var2) {
      for (Class_0371 var4 : this.field_3243) {
         if (var4.getName().equalsIgnoreCase(var1)) {
            try {
               this.method_2(this.field_2748, var2, field_4218.toJson(var4.method_415()));
               this.field_3243.add(new Class_0371(var2, var4.method_415()));
               return true;
            } catch (Exception var6) {
               var6.printStackTrace();
               return false;
            }
         }
      }

      return false;
   }

   public boolean method_39(String var1, String var2) {
      if (var1.equalsIgnoreCase(var2)) {
         return false;
      } else {
         if (this.field_2748.resolve(new TextBuilder().method_2((Object)var1).method_9("\u0001.json")).toFile().exists()) {
            this.field_2748
               .resolve(new TextBuilder().method_2((Object)var1).method_9("\u0001.json"))
               .toFile()
               .renameTo(this.field_2748.resolve(new TextBuilder().method_2((Object)var2).method_9("\u0001.json")).toFile());
         }

         return this.method_78(var1, var2) && this.field_3243.removeIf(var1x -> var1x.getName().equalsIgnoreCase(var1));
      }
   }

   public void method_494(String var1) throws java.io.IOException {
      this.field_3243.removeIf(var1x -> var1x.getName().equalsIgnoreCase(var1));
      Class_0371 var2 = new Class_0371(var1, this.field_1036.toJson().getAsJsonObject());
      var2.method_5(this.field_2748);
      this.field_3243.add(var2);
   }

   public void method_468(String var1) throws java.io.IOException {
      for (Class_0371 var3 : this.field_3243) {
         if (var3.method_228().equals(var1)) {
            var3.method_5(this.field_2748);
         }
      }
   }

   public void method_357() {
      for (Class_0371 var2 : this.field_3243) {
         try {
            var2.method_5(this.field_2748);
         } catch (Exception var4) {
            var4.printStackTrace();
         }
      }
   }

   public void method_2(Path var1, String var2, String var3) throws java.io.IOException {
      Class_1222.method_2(var1.resolve(var2), var3);
   }
}
