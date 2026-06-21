package me.mioclient.mixin;

import me.mioclient.api.Class_0037;
import me.mioclient.api.Class_1366;
import net.minecraft.world.chunk.PalettedContainer;
import net.minecraft.world.chunk.PalettedContainer.Data;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({PalettedContainer.class})
public class MixinPalettedContainer<T> implements Class_1366<T> {
   @Shadow
   private volatile Data<T> data;

   public MixinPalettedContainer() {
      super();
   }

   @Override
   @SuppressWarnings({"rawtypes", "unchecked"})
   public Class_0037<T> mio$data() {
      return (Class_0037<T>)(Object)this.data;
   }
}
