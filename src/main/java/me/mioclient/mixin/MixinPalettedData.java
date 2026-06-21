package me.mioclient.mixin;

import me.mioclient.api.Class_0037;
import net.minecraft.util.collection.PaletteStorage;
import net.minecraft.world.chunk.Palette;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(
   targets = {"net.minecraft.world.chunk.PalettedContainer$Data"}
)
public class MixinPalettedData<T> implements Class_0037<T> {
   @Shadow
   @Final
   PaletteStorage comp_118;
   @Shadow
   @Final
   Palette<T> comp_119;

   public MixinPalettedData() {
      super();
   }

   @Override
   public PaletteStorage mio$storage() {
      return this.comp_118;
   }

   @Override
   public Palette<T> mio$palette() {
      return this.comp_119;
   }
}
