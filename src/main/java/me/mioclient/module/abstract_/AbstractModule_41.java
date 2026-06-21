package me.mioclient.module.abstract_;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.record.Class_0702;

public abstract class AbstractModule_41 extends Module {
   public AbstractModule_41(String var1, String var2, Category var3, String... var4) {
      super(var1, var2, var3, var4);
   }

   public AbstractModule_41(String var1, Category var2, String... var3) {
      this(var1, "", var2, var3);
   }

   @Override
   public boolean isDrawn() {
      return false;
   }

   @Override
   public boolean isToggled() {
      return true;
   }

   @Override
   public void disable() {
   }

   @Override
   public Class_0702 getKeybind() {
      return Class_0702.field_2229;
   }

   @Override
   public JsonElement toJson() {
      JsonObject var1 = super.toJson().getAsJsonObject();
      var1.remove("enabled");
      var1.remove("key");
      var1.remove("state");
      return var1;
   }
}
