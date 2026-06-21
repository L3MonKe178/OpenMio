package me.mioclient.enum_;

import me.mioclient.api.Nameable;
import me.mioclient.api.MioAPI;
import me.mioclient.mixin.ducks.DuckMinecraftClient;
import net.minecraft.client.option.KeyBinding;

public enum Side3 implements Nameable {
   LEFT("Left"),
   RIGHT("Right");

   public final String field_2273;

    Side3(String var3) {
      this.field_2273 = var3;
   }

   public void method_687() {
      if (this == LEFT) {
         ((DuckMinecraftClient)MioAPI.field_4219).attack();
      } else {
         ((DuckMinecraftClient)MioAPI.field_4219).interact();
      }
   }

   public KeyBinding method_688() {
      return this == LEFT ? MioAPI.field_4219.options.attackKey : MioAPI.field_4219.options.useKey;
   }

   @Override
   public String getName() {
      return this.field_2273;
   }
}
