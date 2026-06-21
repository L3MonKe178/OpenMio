package me.mioclient.api;

import net.minecraft.util.collection.PaletteStorage;
import net.minecraft.world.chunk.Palette;
import net.minecraft.world.chunk.PalettedContainer;

public interface Class_0037<T> {
   PaletteStorage mio$storage();

   Palette<T> mio$palette();

   static PaletteStorage method_2(PalettedContainer<?> var0) {
      return ((Class_1366)var0).mio$data().mio$storage();
   }

   static <T> Palette<T> method_9(PalettedContainer<T> var0) {
      return ((Class_1366)var0).mio$data().mio$palette();
   }
}
