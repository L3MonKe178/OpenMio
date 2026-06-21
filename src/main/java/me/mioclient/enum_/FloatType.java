package me.mioclient.enum_;

public enum FloatType {
   FLOAT(1, 4, false),
   VEC2(2, 4, false),
   VEC3(3, 4, false),
   COLOR(4, 1, true);

   public final int field_501;
   public final int field_4462;
   public final boolean field_4463;

    FloatType(int var3, int var4, boolean var5) {
      this.field_501 = var3;
      this.field_4462 = var3 * var4;
      this.field_4463 = var5;
   }

   public int method_1192() {
      return this == COLOR ? 5121 : 5126;
   }
}
