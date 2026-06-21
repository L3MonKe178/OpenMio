package me.mioclient.internal;

import net.minecraft.util.Identifier;

// Originally extended Identifier; that class is final in MC 1.21.1, so we
// hold an Identifier instead and expose a `toIdentifier()` accessor.
public final class Class_0211 {
   public final Identifier identifier;
   public final String field_593;
   public final String field_594;

   public Class_0211(String var1, String var2) {
      this.identifier = Identifier.of(method_244(var1),
            new Class_1303().method_2((Object)var2).method_2((Object)var1).method_9("sounds//.ogg"));
      this.field_593 = var2;
      this.field_594 = var1;
   }

   public Class_0211(String var1) {
      this("system", var1);
   }

   public String getName() {
      return this.field_593;
   }

   public String method_243() {
      return this.field_594;
   }

   public Identifier toIdentifier() {
      return this.identifier;
   }

   public static String method_244(String var0) {
      return var0.equalsIgnoreCase("system") ? "mio" : "mio-mount";
   }
}
