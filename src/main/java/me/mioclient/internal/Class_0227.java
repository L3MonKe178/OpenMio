package me.mioclient.internal;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_0836;
import net.minecraft.client.gui.screen.Screen;

public class Class_0227 extends Class_1117 {
   public final Class_0746 field_622 = new Class_0746("Category");
   public final Screen field_623;

   public Class_0227(Screen var1, Path var2) {
      super();
      this.field_623 = var1;
      this.field_622.method_714().add(new Class_0906(this.field_622, new Class_1303().method_2(this.method_4(var2)).method_9("Preset: \u0001")));
      Class_0836 var3 = this.method_38(var2);
      if (var3 != null) {
         this.field_622.method_9(new Class_1266(this.field_622, "Import", () -> this.method_2(var2, var3)));
      } else {
         for (Class_0836 var7 : Class_0836.values()) {
            this.field_622.method_9(new Class_1266(this.field_622, Class_0841.method_5(var7), () -> this.method_2(var2, var7)));
         }
      }

      this.field_622.method_9(new Class_1266(this.field_622, "Cancel", this::method_257));
      this.method_999().add(this.field_622);
   }

   @Override
   public void method_257() {
      field_4219.setScreen(this.field_623);
   }

   public String method_4(Path var1) {
      return var1.toFile().getName().replace(".json", "");
   }

   public void method_2(Path var1, Class_0836 var2) {
      String var3 = var1.toFile().getName();

      try {
         Files.copy(var1, var2.method_774().resolve(var3));
      } catch (IOException var5) {
         var5.printStackTrace();
      }

      Hub.field_2597.method_2(var2).method_56();
      field_4219.setScreen(this.field_623);
   }

   public Class_0836 method_38(Path var1) {
      Object var2 = null;

      try {
         var2 = Files.readString(var1);
         JsonElement var3 = JsonParser.parseString((String)var2);
         if (!var3.isJsonObject()) {
            return null;
         } else {
            JsonObject var4 = var3.getAsJsonObject();
            if (!var4.has("category")) {
               return null;
            } else {
               String var5 = var4.get("category").getAsString();
               return Class_0836.method_113(var5);
            }
         }
      } catch (IOException var6) {
         return null;
      }
   }

   public static boolean method_2(Screen var0, List<Path> var1) {
      ArrayList var2 = new ArrayList();
      var1.stream().filter(var0x -> var0x.toString().endsWith(".json")).forEach(var2::add);
      if (var2.size() != 1) {
         return false;
      } else {
         field_4219.setScreen(new Class_0227(var0, (Path)var2.getFirst()));
         return true;
      }
   }
}
