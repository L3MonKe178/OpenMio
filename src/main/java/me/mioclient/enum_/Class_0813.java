package me.mioclient.enum_;

import me.mioclient.api.Class_0013;
import net.minecraft.util.Hand;

public enum Class_0813 implements Class_0013 {
   MAINHAND("Mainhand") {
      @Override
      public Hand method_12() {
         return Hand.MAIN_HAND;
      }
   },
   OFFHAND("Offhand") {
      @Override
      public Hand method_12() {
         return Hand.OFF_HAND;
      }
   },
   PACKET("Packet"),
   VANILLA("Vanilla");

   public final String field_2568;

    Class_0813(String var3) {
      this.field_2568 = var3;
   }

   @Override
   public String getName() {
      return this.field_2568;
   }

   public Hand method_12() {
      return null;
   }
}
