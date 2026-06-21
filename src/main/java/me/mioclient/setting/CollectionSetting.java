package me.mioclient.setting;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import java.util.Collection;

/**
 * Generic Setting holding any Collection (Set / List / etc.). Used by the deobf
 * runtime to satisfy {@code Setting<Set<Block>>} and similar field types whose
 * generic param the original {@code nick.Settings} machinery would normally fill.
 * Stores the collection as-is; JSON form is a flat array of stringified elements.
 */
public class CollectionSetting<T extends Collection<?>> extends Setting<T> {

   public CollectionSetting(String name, T initial) {
      super(name, initial);
   }

   @Override
   public void method_78(String value) {
      // String-form mutation is not supported for arbitrary collections.
   }

   @Override
   public JsonElement toJson() {
      JsonArray arr = new JsonArray();
      T v = getValue();
      if (v != null) {
         for (Object e : v) arr.add(new JsonPrimitive(String.valueOf(e)));
      }
      return arr;
   }

   @Override
   public void fromJson(JsonElement el) {
      // Best-effort: serialization round-trip not supported without element type info.
   }
}
