package me.mioclient.enum_;

import me.mioclient.api.Class_0013;
import me.mioclient.module.player.SpeedMineModule;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public enum Class_1186 implements Class_0013 {
   PLAIN("Plain") {
      @Override
      public Box method_2(SpeedMineModule var1, Box var2, float var3) {
         return var2;
      }
   },
   IN("In") {
      @Override
      public Box method_2(SpeedMineModule var1, Box var2, float var3) {
         float var4 = 1.0F - var3;
         double var5 = Math.abs(var2.getLengthX());
         double var7 = Math.abs(var2.getLengthY());
         double var9 = Math.abs(var2.getLengthZ());
         Vec3d var11 = var2.getCenter();
         return new Box(
            var11.subtract((double)var4 * var5 / 2.0, (double)var4 * var7 / 2.0, (double)var4 * var9 / 2.0),
            var11.add((double)var4 * var5 / 2.0, (double)var4 * var7 / 2.0, (double)var4 * var9 / 2.0)
         );
      }
   },
   OUT("Out") {
      @Override
      public Box method_2(SpeedMineModule var1, Box var2, float var3) {
         double var4 = Math.abs(var2.getLengthX());
         double var6 = Math.abs(var2.getLengthY());
         double var8 = Math.abs(var2.getLengthZ());
         Vec3d var10 = var2.getCenter();
         return new Box(
            var10.subtract((double)var3 * var4 / 2.0, (double)var3 * var6 / 2.0, (double)var3 * var8 / 2.0),
            var10.add((double)var3 * var4 / 2.0, (double)var3 * var6 / 2.0, (double)var3 * var8 / 2.0)
         );
      }
   };

   public final String field_3672;

    Class_1186(String var3) {
      this.field_3672 = var3;
   }

   @Override
   public String getName() {
      return this.field_3672;
   }

   public Box method_2(SpeedMineModule var1, Box var2, float var3) {
      return null;
   }
}
