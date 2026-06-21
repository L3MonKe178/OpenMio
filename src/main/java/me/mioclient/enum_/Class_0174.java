package me.mioclient.enum_;

import java.awt.Color;
import me.mioclient.api.Class_0013;
import me.mioclient.module.player.SpeedMineModule;

// Vineflower hallucinated anonymous-class overrides on PROGRESS / CUSTOM that
// the compiled bytecode never had — both values share the base method_2 which
// returns null. The behaviour is dispatched elsewhere by switch on the enum.
public enum Class_0174 implements Class_0013 {
   PROGRESS("Progress"),
   CUSTOM("Custom");

   public final String field_498;

   Class_0174(String var3) {
      this.field_498 = var3;
   }

   @Override
   public String getName() {
      return this.field_498;
   }

   public Color[] method_2(SpeedMineModule var1, float var2) {
      return null;
   }
}
