package me.mioclient.enum_;

import me.mioclient.api.Nameable;

public enum Class_1229 implements Nameable {
   CONTROL("Control"),
   BOOST("Boost"),
   PACKET("Packet"),
   STRICT("Strict"),
   BOUNCE("Bounce");

   public final String field_3875;

    Class_1229(String var3) {
      this.field_3875 = var3;
   }

   @Override
   public String getName() {
      return this.field_3875;
   }
}
