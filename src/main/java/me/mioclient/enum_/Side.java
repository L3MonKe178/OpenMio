package me.mioclient.enum_;

import me.mioclient.record.Class_0362;

public enum Side {
   UP(0, -1),
   RIGHT(1, 0),
   DOWN(0, 1),
   LEFT(-1, 0);

   public final Class_0362 field_1082;

    Side(int var3, int var4) {
      this.field_1082 = new Class_0362(var3, var4);
   }

   public Class_0362 method_376() {
      return this.field_1082;
   }

   public Side method_377() {
      return switch (this) {
         case UP -> DOWN;
         case RIGHT -> LEFT;
         case DOWN -> UP;
         case LEFT -> RIGHT;
      };
   }
}
