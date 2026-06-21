package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_0207 implements Class_0013 {
   PACKET("Packet"),
   CLIENT("Client"),
   MOTION("Motion");

   public final String field_587;

    Class_0207(String var3) {
      this.field_587 = var3;
   }

   @Override
   public String getName() {
      return this.field_587;
   }
}
