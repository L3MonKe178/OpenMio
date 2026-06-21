package me.mioclient.enum_;

public enum LinesType {
   LINES(2),
   TRIANGLES(3);

   public final int field_3888;

    LinesType(int var3) {
      this.field_3888 = var3;
   }

   public int method_1081() {
      return switch (this) {
         case LINES -> 1;
         case TRIANGLES -> 4;
      };
   }
}
