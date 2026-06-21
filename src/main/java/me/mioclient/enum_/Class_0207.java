package me.mioclient.enum_;

import me.mioclient.api.Nameable;

public enum Class_0207 implements Nameable {
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
