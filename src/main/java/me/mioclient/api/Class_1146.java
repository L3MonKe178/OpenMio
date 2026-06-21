package me.mioclient.api;

import com.google.gson.JsonElement;

public interface Class_1146 {
   JsonElement toJson();

   void fromJson(JsonElement var1);

   default String getConfigName() {
      return "";
   }
}
