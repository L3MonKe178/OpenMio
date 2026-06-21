package me.mioclient.mixin;

import me.mioclient.api.Class_0718;
import net.minecraft.client.option.SimpleOption;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({SimpleOption.class})
public class MixinSimpleOption<T> implements Class_0718<T> {
   @Shadow
   T value;

   public MixinSimpleOption() {
      super();
   }

   @Override
   public void forceSetValue(T var1) {
      this.value = (T)var1;
   }
}
