package me.mioclient.internal;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import me.mioclient.Hub;
import me.mioclient.api.Class_1146;
import me.mioclient.api.Class_1309;

public class Class_0724 implements Class_1309, Class_1146 {
   public String field_2299 = "";

   public Class_0724() {
      super();
      field_4220.method_14(this);
   }

   @Override
   public JsonElement toJson() {
      JsonObject var1 = new JsonObject();
      var1.addProperty("url", this.field_2299);
      return var1;
   }

   @Override
   public void fromJson(JsonElement var1) {
      this.field_2299 = var1.getAsJsonObject().get("url").getAsString();
   }

   @Override
   public String getConfigName() {
      return "webhook.json";
   }

   public void method_138(String var1) {
      this.field_2299 = var1;
   }

   public String method_695() {
      return this.field_2299;
   }

   public void method_214(String var1) {
      field_4221.submit(
         () -> {
            try {
               JsonObject var1x = new JsonObject();
               var1x.addProperty("content", var1);
               HttpClient var2 = HttpClient.newHttpClient();
               HttpRequest var3 = Class_0130.method_2(Hub.field_2621.method_695(), BodyPublishers.ofString(Class_1309.field_4218.toJson(var1x)))
                  .header("User-Agent", "MioClient/2.0")
                  .headers("Content-Type", "application/json")
                  .build();
               var2.send(var3, BodyHandlers.ofString());
            } catch (Exception var4) {
            }
         }
      );
   }
}
