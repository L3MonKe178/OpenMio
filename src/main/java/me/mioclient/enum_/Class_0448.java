package me.mioclient.enum_;

import me.mioclient.api.Nameable;

public enum Class_0448 implements Nameable {
   PACKET("Packet"),
   NCP("NCP"),
   GRIM("Grim"),
   GRIMV3("2b2t");

   public final String field_1440;

    Class_0448(String var3) {
      this.field_1440 = var3;
   }

   @Override
   public String getName() {
      return this.field_1440;
   }
}
