package me.mioclient.enum_;

import me.mioclient.api.Class_0013;
import me.mioclient.api.Class_1309;
import me.mioclient.mixin.ducks.DuckMinecraftClient;
import net.minecraft.client.option.KeyBinding;

public enum Side3 implements Class_0013 {
   LEFT("Left"),
   RIGHT("Right");

   public final String field_2273;

    Side3(String var3) {
      this.field_2273 = var3;
   }

   public void method_687() {
      if (this == LEFT) {
         ((DuckMinecraftClient)Class_1309.field_4219).attack();
      } else {
         ((DuckMinecraftClient)Class_1309.field_4219).interact();
      }
   }

   public KeyBinding method_688() {
      return this == LEFT ? Class_1309.field_4219.options.attackKey : Class_1309.field_4219.options.useKey;
   }

   @Override
   public String getName() {
      return this.field_2273;
   }
}
