package me.mioclient.record;

public final class Class_0920 {
   public final float field_465;
   public final float field_466;
   public final float field_2900;
   public final float field_2901;

   public Class_0920(float var1, float var2, float var3, float var4) {
      super();
      this.field_465 = var1;
      this.field_466 = var2;
      this.field_2900 = var3;
      this.field_2901 = var4;
   }

   public static Class_0920 method_9(float var0, float var1, float var2, float var3) {
      return new Class_0920(var0, var1, var0 + var2, var1 + var3);
   }

   public float method_834() {
      return this.field_2900 - this.field_465;
   }

   public float method_835() {
      return this.field_2901 - this.field_466;
   }

   public boolean method_5(double var1, double var3) {
      return var1 >= (double)this.field_465 && var1 <= (double)this.field_2900 && var3 >= (double)this.field_466 && var3 <= (double)this.field_2901;
   }

   public boolean method_2(Class_0920 var1) {
      return (this.field_2900 < this.field_465 || this.field_2900 > var1.method_836())
         && (this.field_2901 < this.field_466 || this.field_2901 > var1.method_837())
         && (var1.method_838() < var1.method_836() || var1.method_838() > this.field_465)
         && (var1.method_839() < var1.method_837() || var1.method_839() > this.field_466);
   }

   public Class_0920 method_22(float var1) {
      return this.method_7(var1, var1);
   }

   public Class_0920 method_7(float var1, float var2) {
      return new Class_0920(this.method_836() - var1, this.method_837() - var2, this.method_838() + var1, this.method_839() + var2);
   }

   public Class_0920 method_2(Class_0920... var1) {
      float var2 = this.method_836();
      float var3 = this.method_838();
      float var4 = this.method_837();
      float var5 = this.method_839();

      for (Class_0920 var9 : var1) {
         var2 = Math.min(var2, var9.method_836());
         var4 = Math.min(var4, var9.method_837());
         var3 = Math.max(var3, var9.method_838());
         var5 = Math.max(var5, var9.method_839());
      }

      return new Class_0920(var2, var4, var3, var5);
   }

   public float method_836() {
      return this.field_465;
   }

   public float method_837() {
      return this.field_466;
   }

   public float method_838() {
      return this.field_2900;
   }

   public float method_839() {
      return this.field_2901;
   }
}
