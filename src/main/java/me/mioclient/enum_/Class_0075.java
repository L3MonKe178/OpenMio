package me.mioclient.enum_;

import me.mioclient.api.Class_0013;
import net.minecraft.util.Identifier;

public enum Class_0075 implements Class_0013 {
   NONE("none", null),
   MIO("mio", Identifier.of("mio", "capes/mio.png")),
   PEPSI("pepsi", Identifier.of("mio", "capes/pepsi.png")),
   NIGHTLY("nightly", Identifier.of("mio", "capes/nightly.png")),
   TETRIS("tetris", Identifier.of("mio", "capes/tetris.png"));

   public final String field_273;
   public final Identifier field_274;

    Class_0075(String var3, Identifier var4) {
      this.field_273 = var3;
      this.field_274 = var4;
   }

   @Override
   public String getName() {
      return this.field_273;
   }

   public Identifier method_108() {
      return this.field_274;
   }
}
