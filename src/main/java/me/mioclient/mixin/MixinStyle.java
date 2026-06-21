package me.mioclient.mixin;

import java.util.function.Supplier;
import me.mioclient.api.Class_0068;
import me.mioclient.api.Class_1232;
import net.minecraft.text.Style;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin({Style.class})
public class MixinStyle implements Class_1232 {
   public MixinStyle() {
      super();
   }

   @Unique
   @Override
   public Style mio$withColor(Supplier<Integer> var1) {
      Style var2 = (Style)(Object)this;
      if (var2.getColor() != null) {
         ((Class_0068)(Object)var2.getColor()).setSupplier(var1);
      }

      return var2;
   }
}
