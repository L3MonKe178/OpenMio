package me.mioclient.internal;

import me.mioclient.api.Class_0937;
import me.mioclient.enum_.Class_0787;

public class Class_0185 extends Class_0746 {
   public Class_0787 field_516 = Class_0787.TETRIS;

   public Class_0185() {
      super("Game");
      this.method_9(new Class_0816(this, 0));
      this.method_9(new Class_0906(this, () -> this.field_516.getName()) {
         @Override
         public void method_2(double var1, double var3, int var5) {
            super.method_2(var1, var3, var5);
            if (this.method_5(var1, var3) && var5 == 0) {
               Class_0185.this.method_2(Class_0787.values()[(Class_0185.this.field_516.ordinal() + 1) % Class_0787.values().length]);
            }
         }
      });
   }

   public void method_2(Class_0787 var1) {
      this.field_516 = var1;

      Object var2 = switch (var1) {
         case TETRIS -> new Class_0816(this, 0);
         case SNAKE -> new Class_0571(this, 0);
      };
      synchronized (this.field_2378) {
         this.field_2378.set(0, (Class_0937)var2);
      }
   }

   @Override
   public int method_216() {
      return 110;
   }
}
