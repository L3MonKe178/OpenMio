package me.mioclient.enum_;

import java.util.Locale;
import me.mioclient.api.Class_0013;
import me.mioclient.api.Class_1309;
import net.minecraft.util.math.Vec3d;

public enum Class_1056 implements Class_0013 {
   NONE("None"),
   COORDS("Coords") {
      @Override
      public String method_37(Vec3d var1) {
         return String.format(Locale.US, "X: %.1f, Y: %.1f, Z: %.1f", var1.getX(), var1.getY(), var1.getZ());
      }
   },
   DISTANCE("Distance") {
      @Override
      public String method_37(Vec3d var1) {
         return String.format("%.1fm", var1.distanceTo(Class_1309.field_4219.player.getPos())).replaceAll(",", ".");
      }
   };

   public final String field_3258;

    Class_1056(String var3) {
      this.field_3258 = var3;
   }

   @Override
   public String getName() {
      return this.field_3258;
   }

   public String method_37(Vec3d var1) {
      return "";
   }
}
