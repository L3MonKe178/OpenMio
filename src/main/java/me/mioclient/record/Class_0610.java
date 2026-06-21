package me.mioclient.record;

import java.awt.Color;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Box;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public final class Class_0610 {
   public final Matrix4f field_1885;
   public final Matrix3f field_1886;
   public final Box field_1887;
   public final Color field_1888;
   public final float field_1889;

   public Class_0610(Matrix4f var1, Matrix3f var2, Box var3, Color var4, float var5) {
      super();
      this.field_1885 = var1;
      this.field_1886 = var2;
      this.field_1887 = var3;
      this.field_1888 = var4;
      this.field_1889 = var5;
   }

   public static Class_0610 method_9(MatrixStack var0, Box var1, Color var2, float var3) {
      return new Class_0610(new Matrix4f(var0.peek().getPositionMatrix()), new Matrix3f(var0.peek().getNormalMatrix()), var1, var2, var3);
   }

   public Matrix4f method_619() {
      return this.field_1885;
   }

   public Matrix3f method_620() {
      return this.field_1886;
   }

   public Box method_172() {
      return this.field_1887;
   }

   public Color method_621() {
      return this.field_1888;
   }

   public float method_622() {
      return this.field_1889;
   }
}
