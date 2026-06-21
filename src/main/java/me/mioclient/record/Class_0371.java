package me.mioclient.record;

import com.google.gson.JsonObject;
import java.nio.file.Path;
import me.mioclient.api.Class_0013;
import me.mioclient.api.Class_1309;
import me.mioclient.internal.Class_1222;
import me.mioclient.internal.Class_1303;

public final class Class_0371 implements Class_1309, Class_0013 {
   public final String field_1203;
   public final JsonObject field_1204;

   public Class_0371(String var1, JsonObject var2) {
      super();
      this.field_1203 = var1;
      this.field_1204 = var2;
   }

   @Override
   public String getName() {
      return this.field_1203;
   }

   public void method_5(Path var1) throws java.io.IOException {
      String var10001 = this.field_1203;
      Class_1222.method_2(var1.resolve(new Class_1303().method_2((Object)var10001).method_9("\u0001.json")), field_4218.toJson(this.method_415()));
   }

   public String method_228() {
      return this.field_1203;
   }

   public JsonObject method_415() {
      return this.field_1204;
   }
}
