package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_0440 implements Class_0013 {
   CHAT("Chat"),
   COMMANDS("Commands"),
   BOTH("Both");

   public final String field_1398;

    Class_0440(String var3) {
      this.field_1398 = var3;
   }

   @Override
   public String getName() {
      return this.field_1398;
   }
}
