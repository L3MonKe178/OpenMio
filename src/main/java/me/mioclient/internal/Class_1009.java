package me.mioclient.internal;

import java.util.HashMap;
import java.util.Map;
import me.mioclient.api.Class_0013;
import me.mioclient.record.Class_0501;

public final class Class_1009 implements Class_0013 {
   public final Map<Integer, Class_0501> field_3110 = new HashMap<>();
   public final String field_3111;

   public Class_1009(String var1) {
      super();
      this.field_3111 = var1;
   }

   @Override
   public String getName() {
      return this.field_3111;
   }

   public Map<Integer, Class_0501> method_906() {
      return this.field_3110;
   }
}
