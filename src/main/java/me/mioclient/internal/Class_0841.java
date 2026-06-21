package me.mioclient.internal;

import com.google.common.base.Converter;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import me.mioclient.api.Nameable;

public class Class_0841 extends Converter<Enum, JsonElement> {
   public final Class<? extends Enum> field_2697;

   public Class_0841(Class<? extends Enum<?>> var1) {
      super();
      this.field_2697 = var1;
   }

   public static int method_2(Enum<?> var0) {
      for (int var1 = 0; var1 < ((Enum[])var0.getDeclaringClass().getEnumConstants()).length; var1++) {
         Enum var2 = ((Enum[])var0.getDeclaringClass().getEnumConstants())[var1];
         if (!Class_0345.method_29(var2) && var2.name().equalsIgnoreCase(var0.name())) {
            return var1;
         }
      }

      return -1;
   }

   public static Enum<?> method_9(Enum var0) {
      int var1 = method_2(var0);

      for (int var2 = 0; var2 < var0.getDeclaringClass().getEnumConstants().length; var2++) {
         Enum var3 = (Enum)var0.getDeclaringClass().getEnumConstants()[var2];
         if (!Class_0345.method_29(var3) && var2 > var1) {
            return var3;
         }
      }

      return (Enum<?>)var0.getDeclaringClass().getEnumConstants()[0];
   }

   public static String method_5(Enum<?> var0) {
      if (var0 instanceof Nameable var1) {
         return var1.getName();
      } else {
         throw new IllegalArgumentException();
      }
   }

   public JsonElement method_7(Enum var1) {
      if (var1 instanceof Nameable var2) {
         return new JsonPrimitive(var2.getName());
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   protected Enum doBackward(JsonElement var1) {
      return this.method_9(var1);
   }

   @Override
   protected JsonElement doForward(Enum var1) {
      return this.method_7(var1);
   }

   public Enum method_9(JsonElement var1) {
      for (Enum var5 : this.field_2697.getEnumConstants()) {
         if (!Nameable.method_16(var5)) {
            throw new IllegalArgumentException();
         }

         if (!Class_0345.method_29(var5) && var5 instanceof Nameable var6 && var1.getAsString().equalsIgnoreCase(var6.getName())) {
            return var5;
         }
      }

      return this.field_2697.getEnumConstants()[0];
   }
}
