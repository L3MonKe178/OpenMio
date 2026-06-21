package me.mioclient.internal;

import java.util.function.Supplier;
import me.mioclient.Hub;

public class Class_0031 {
   public float field_55;
   public float field_56;
   public float field_57;
   public boolean field_58;
   public Supplier<Float> field_59;

   public Class_0031(float var1, boolean var2) {
      super();
      this.field_59 = () -> var1;
      this.field_58 = var2;
   }

   public Class_0031(float var1) {
      this(var1, true);
   }

   public Class_0031(Supplier<Float> var1, boolean var2) {
      super();
      this.field_59 = var1;
      this.field_58 = var2;
   }

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
      if (this.field_55 == this.field_56 || (double)Math.abs(this.field_56 - this.field_55) < Constants.field_688) {
         this.method_36(this.field_56);
      }

      if (Hub.field_2601.method_814() <= 15) {
         this.field_55 = this.field_56;
      }

      return this.field_55;
   }

   public void method_3(float var1) {
      if (this.field_55 != var1) {
         this.field_57 = this.field_55;
         this.field_55 = var1;
         this.field_56 = var1;
      }
   }

   public void method_36(float var1) {
      if (this.field_55 != var1) {
         this.field_57 = var1;
         this.field_55 = var1;
         this.field_56 = var1;
      }
   }

   public Supplier<Float> method_46() {
      return this.field_59;
   }

   public void method_2(Supplier<Float> var1) {
      this.field_59 = var1;
   }

   public void method_22(boolean var1) {
      this.field_58 = var1;
   }
}
