package me.mioclient.enum_;

import me.mioclient.api.Nameable;

public enum Class_0787 implements Nameable {
   TETRIS("Tetris"),
   SNAKE("Snake");

   public final String field_2464;

    Class_0787(String var3) {
      this.field_2464 = var3;
   }

   @Override
   public String getName() {
      return this.field_2464;
   }
}
