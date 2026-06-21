package me.mioclient.internal;

import net.minecraft.client.MinecraftClient;

public final class Class_0873 {
   public static String username = null;
   public static int field_532 = -1;
   public static int field_2782 = -1;

   public Class_0873() {
      super();
      if (username == null) {
         username = MinecraftClient.getInstance().getSession().getUsername();
      }
   }

   public String method_801() {
      return username;
   }

   public int method_802() {
      return field_532;
   }

   public boolean method_803() {
      return field_2782 == -1 || field_2782 == 4;
   }

   public boolean method_804() {
      return this.method_803() || field_2782 == 3;
   }
}
