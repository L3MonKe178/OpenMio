package me.mioclient.internal;

import java.util.function.Supplier;
import me.mioclient.Hub;
import net.minecraft.util.math.MathHelper;

public class Class_0585 extends Class_0031 {
   public Class_0585(float var1, boolean var2) {
      super(var1, var2);
   }

   public Class_0585(float var1) {
      super(var1);
   }

   public Class_0585(Supplier<Float> var1, boolean var2) {
      super(var1, var2);
   }

   @Override
   public float method_45() {
      float var1 = Hub.field_2601.method_154(Float.intBitsToFloat(1056964608)) * this.field_59.get();
      if (this.field_58) {
         float var2;
         var1 = (float)(
            Double.longBitsToDouble(4607182418800017408L)
               - (double)(var2 = var1 - Float.intBitsToFloat(1065353216)) * Math.pow((double)var2, Double.longBitsToDouble(4613937818241073152L))
         );
      }

      this.field_55 = this.field_57 + (this.field_55 - this.field_57) * var1;
      if (this.field_55 == this.field_56 || (double)Math.abs(this.field_56 - this.field_55) < Double.longBitsToDouble(4576918229304087675L)) {
         this.method_36(this.field_56);
      }

      if (Hub.field_2601.method_814() <= 15) {
         this.field_55 = this.field_56;
      }

      return MathHelper.clamp(this.field_55 - Float.intBitsToFloat(1065353216), 0.0F, Float.intBitsToFloat(1065353216));
   }

   @Override
   public void method_3(float var1) {
      super.method_3(var1 + Float.intBitsToFloat(1065353216));
   }

   public float method_605() {
      return this.field_55;
   }

   public void method_3(boolean var1) {
      this.method_3(var1 ? Float.intBitsToFloat(1065353216) : 0.0F);
   }

   public void method_36(boolean var1) {
      this.method_36(var1 ? Float.intBitsToFloat(1073741824) : Float.intBitsToFloat(1065353216));
   }

   public void reset() {
      this.method_3(0.0F);
   }
}
