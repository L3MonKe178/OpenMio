package me.mioclient.record;

import java.util.Map.Entry;
import me.mioclient.internal.TextBuilder;
import net.minecraft.util.Formatting;

public final class Class_0197 {
   public final String field_553;
   public final int field_554;

   public Class_0197(String var1, int var2) {
      super();
      this.field_553 = var1;
      this.field_554 = var2;
   }

   public static Class_0197 method_5(Entry<String, Integer> var0) {
      return new Class_0197((String)var0.getKey(), (Integer)var0.getValue());
   }

   public String method_2(Formatting var1) {
      String var10000 = this.field_553;
      int var10001 = this.field_554;
      return new TextBuilder().method_2(var10001).method_2((Object)var10000).method_9("\u0001 x\u0001");
   }

   public String method_228() {
      return this.field_553;
   }

   public int method_229() {
      return this.field_554;
   }
}
