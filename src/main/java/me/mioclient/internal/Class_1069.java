package me.mioclient.internal;

public final class Class_1069 {
   public final int field_3280;
   public final int field_3281;
   public int field_3282;

   public Class_1069(int var1, int var2, int var3) {
      super();
      this.field_3280 = var1;
      this.field_3282 = var3;
      this.field_3281 = var2;
   }

   public int method_34() {
      return this.field_3280;
   }

   public int method_954() {
      return this.field_3281;
   }

   public int method_955() {
      return this.field_3282;
   }

   public void method_494(int var1) {
      this.field_3282 = Math.min(var1, this.method_954());
   }
}
