package me.mioclient.enum_;

import me.mioclient.api.Nameable;
import me.mioclient.api.MioAPI;
import me.mioclient.internal.RotationManager;
import net.minecraft.util.math.Vec3d;

public enum Class_0823 implements MioAPI, Nameable {
   KEEP("Keep"),
   INVERT("Invert"),
   POS("Pos");

   public final String field_2636;

    Class_0823(String var3) {
      this.field_2636 = var3;
   }

   @Override
   public String getName() {
      return this.field_2636;
   }

   public float method_3(Vec3d var1) {
      if (this == KEEP) {
         return field_4219.player.getYaw();
      } else {
         float var2 = RotationManager.method_78(var1)[0];
         if (this == INVERT) {
            var2 += 180.0F;
         }

         return var2;
      }
   }
}
