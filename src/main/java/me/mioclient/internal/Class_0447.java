package me.mioclient.internal;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import me.mioclient.api.Class_1146;
import me.mioclient.api.MioAPI;
import me.mioclient.record.Class_0501;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public final class Class_0447 extends Registry<Class_1009> implements MioAPI, Class_1146 {
   public String field_1435;

   public Class_0447() {
      super();
   }

   @Override
   public JsonElement toJson() {
      JsonObject var1 = new JsonObject();
      if (this.field_1435 != null) {
         var1.addProperty("current", this.field_1435);
      }

      for (Class_1009 var3 : (Iterable<Class_1009>)(Iterable<?>)((List)this.getRegistry())) {
         JsonArray var4 = new JsonArray();
         var3.method_906().forEach((var1x, var2) -> {
            JsonObject var3x = new JsonObject();
            var3x.addProperty("id", var2.method_530());
            if (var2.method_531() != null) {
               var3x.addProperty("data", var2.method_531());
            }

            JsonObject var4x = new JsonObject();
            var4x.addProperty("slot", var1x);
            var4x.add("item", var3x);
            var4.add(var4x);
         });
         var1.add(var3.getName(), var4);
      }

      return var1;
   }

   @Override
   public void fromJson(JsonElement var1) {
      if (var1.isJsonObject()) {
         JsonObject var2 = var1.getAsJsonObject();
         if (var2.has("current")) {
            this.method_27(var2.get("current").getAsString());
         }

         for (Entry var4 : var2.entrySet()) {
            if (((JsonElement)var4.getValue()).isJsonArray()) {
               Class_1009 var5 = new Class_1009((String)var4.getKey());

               for (JsonElement var7 : ((JsonElement)var4.getValue()).getAsJsonArray()) {
                  if (var7.isJsonObject()) {
                     JsonObject var8 = var7.getAsJsonObject();
                     if (var8.has("slot") && var8.has("item")) {
                        Class_0501 var9 = null;
                        JsonElement var11 = var8.get("item");
                        if (var11 instanceof JsonObject) {
                           JsonObject var10 = (JsonObject)var11;
                           if (var10.has("id")) {
                              int var14 = var10.get("id").getAsInt();
                              String var12 = null;
                              if (var10.has("data")) {
                                 var12 = var10.get("data").getAsString();
                              }

                              var9 = new Class_0501(var14, var12);
                           }
                        }

                        var11 = var8.get("item");
                        if (var11 instanceof JsonPrimitive) {
                           JsonPrimitive var13 = (JsonPrimitive)var11;
                           if (var13.isNumber()) {
                              var9 = new Class_0501(var13.getAsInt(), null);
                           }
                        }

                        if (var9 != null) {
                           var5.method_906().put(var8.get("slot").getAsInt(), var9);
                        }
                     }
                  }
               }

               this.method_2(var5);
            }
         }
      }
   }

   @Override
   public String getConfigName() {
      return "kits.json";
   }

   public Class_1009 method_487() {
      return this.field_1435 == null ? null : this.method_2(var1 -> var1.getName().equalsIgnoreCase(this.field_1435)).orElse(null);
   }

   public void method_27(String var1) {
      this.field_1435 = var1;
   }

   public void method_468(String var1) {
      this.getRegistry().removeIf(var1x -> var1x.getName().equalsIgnoreCase(var1));
      Class_1009 var2 = new Class_1009(var1);
      int var3 = 0;

      for (ItemStack var5 : field_4219.player.getInventory().main) {
         var2.method_906().put(var3, new Class_0501(Item.getRawId(var5.getItem()), method_31(var5)));
         var3++;
      }

      ((List)this.getRegistry()).add(var2);
      this.method_27(var1);
   }

   public static String method_31(ItemStack var0) {
      PotionContentsComponent var1 = (PotionContentsComponent)var0.get(DataComponentTypes.POTION_CONTENTS);
      if (var1 != null) {
         ArrayList var2 = new ArrayList();
         var1.forEachEffect(
            var1x -> var2.add(new TextBuilder().method_2(var1x.getAmplifier() + 1).method_2(var1x.getTranslationKey()).method_9("\u0001 \u0001"))
         );
         var2.sort(Comparator.comparing(var0x -> (String)var0x));
         return String.join("", var2);
      } else {
         return null;
      }
   }
}
