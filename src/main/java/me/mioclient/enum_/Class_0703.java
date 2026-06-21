package me.mioclient.enum_;

import me.mioclient.api.Class_0013;
import me.mioclient.api.Class_1309;
import me.mioclient.internal.Class_1257;
import net.minecraft.util.math.Vec3d;

public enum Class_0703 implements Class_0013 {
   DISTANCE("Distance"),
   COORDS("Coords"),
   NONE("None");

   public final String field_2233;

    Class_0703(String var3) {
      this.field_2233 = var3;
   }

   @Override
   public String getName() {
      return this.field_2233;
   }

   public String method_9(Class_1257 var1) {
      if (this == DISTANCE) {
         return "%.1fm".formatted(Class_1309.field_4219.player.getEyePos().distanceTo(var1.method_111().toCenterPos()));
      } else {
         Vec3d var2 = var1.method_111().toCenterPos();
         return "X:%.1f, Y:%.1f, Z:%.1f".formatted(var2.x, var2.y, var2.z);
      }
   }

   public boolean method_670() {
      return this != NONE;
   }
}
