package me.mioclient.internal;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Set;
import me.mioclient.Hub;
import me.mioclient.api.Class_1146;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;

public class Class_0114 implements Class_1146 {
   public final Class_0199<Item> field_352 = new Class_0199<>("Items", Registries.ITEM);

   public Class_0114() {
      super();
   }

   @Override
   public JsonElement toJson() {
      JsonObject var1 = new JsonObject();
      var1.add("items", this.field_352.toJson());
      return var1;
   }

   @Override
   public void fromJson(JsonElement var1) {
      if (var1 instanceof JsonObject var2 && var2.has("items")) {
         this.field_352.fromJson(var2.get("items"));
      }
   }

   @Override
   public String getConfigName() {
      return "glint.json";
   }

   public Class_0199<Item> method_141() {
      return this.field_352;
   }

   public static boolean method_22(Item var0) {
      return Hub.field_2628 == null ? false : ((Set)Hub.field_2628.field_352.getValue()).contains(var0);
   }
}
