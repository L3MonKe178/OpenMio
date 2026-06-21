package me.mioclient.module;

import me.mioclient.api.Class_0013;

public enum Category implements Class_0013 {
   COMBAT("Combat"),
   MISC("Misc"),
   RENDER("Render"),
   MOVEMENT("Movement"),
   PLAYER("Player"),
   EXPLOIT("Exploit"),
   CLIENT("Client"),
   HUD("HUD");

   public final String field_2496;

    Category(String var3) {
      this.field_2496 = var3;
   }

   @Override
   public String getName() {
      return this.field_2496;
   }
}
