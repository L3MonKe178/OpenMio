package me.mioclient.record;

public final class Class_0362 {
   public final int field_1166;
   public final int field_1167;

   public Class_0362(int var1, int var2) {
      super();
      this.field_1166 = var1;
      this.field_1167 = var2;
   }

   public double method_2(Class_0362 var1) {
      return Math.pow((double)(this.field_1166 - var1.field_1166), Double.longBitsToDouble(4611686018427387904L))
         + Math.pow((double)(this.field_1167 - var1.field_1167), Double.longBitsToDouble(4611686018427387904L));
   }

   public double method_9(Class_0362 var1) {
      return Math.sqrt(this.method_2(var1));
   }

   public Class_0362 method_5(Class_0362 var1) {
      return new Class_0362(this.field_1166 + var1.field_1166, this.field_1167 + var1.field_1167);
   }

   public int method_401() {
      return this.field_1166;
   }

   public int method_402() {
      return this.field_1167;
   }
}
