package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_0304 implements Class_0013 {
   FRIEND("friend", "friends"),
   ENEMY("enemy", "enemies");

   public final String field_981;
   public final String field_982;

    Class_0304(String var3, String var4) {
      this.field_981 = var3;
      this.field_982 = var4;
   }

   @Override
   public String getName() {
      return this.field_981;
   }

   public String method_349() {
      return this.field_982;
   }
}
