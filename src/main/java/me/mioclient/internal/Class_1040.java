package me.mioclient.internal;

import it.unimi.dsi.fastutil.ints.IntIterator;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import me.mioclient.api.Class_0037;
import me.mioclient.enum_.Class_0404;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.collection.PaletteStorage;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.chunk.ArrayPalette;
import net.minecraft.world.chunk.BiMapPalette;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.Palette;
import net.minecraft.world.chunk.PalettedContainer;
import net.minecraft.world.chunk.WorldChunk;

public final class Class_1040 {
   public static final IntSet field_3220 = new IntOpenHashSet();

   public Class_1040() {
      super();
   }

   public boolean method_9(WorldChunk var1) {
      return switch (Class_1225.method_1071()) {
         case OVERWORLD -> {
            switch (this.method_2(var1, true)) {
               case NO_PLAINS:
                  yield false;
               case PLAINS_IN_PALETTE:
                  yield true;
               case PLAINS_PRESENT:
                  yield this.method_5(var1);
               default:
                  throw new MatchException(null, null);
            }
         }
         case THE_NETHER, THE_END -> this.method_2(var1, false) == Class_0404.PLAINS_IN_PALETTE;
      };
   }

   public boolean method_2(Palette<?> var1) {
      return var1.getSize() <= 0 || !(var1 instanceof ArrayPalette) && !(var1 instanceof BiMapPalette);
   }

   public synchronized boolean method_5(PalettedContainer<BlockState> var1) {
      field_3220.clear();
      Palette var2 = Class_0037.method_9(var1);
      PaletteStorage var3 = Class_0037.method_2(var1);
      var3.forEach(field_3220::add);
      return var2.getSize() > field_3220.size();
   }

   public boolean method_5(WorldChunk var1) {
      ChunkSection[] var2 = var1.getSectionArray();
      if (var2.length == 0) {
         return false;
      } else {
         ChunkSection var3 = var2[0];
         Palette var4 = Class_0037.method_9(var3.getBlockStateContainer());
         if (this.method_2(var4)) {
            return false;
         } else if (var4 instanceof ArrayPalette) {
            return ((BlockState)var4.get(0)).getBlock() == Blocks.AIR;
         } else {
            for (int var5 = 0; var5 < Math.min(var2.length, 3); var5++) {
               ChunkSection var6 = var2[var5];
               PalettedContainer var7 = var6.getBlockStateContainer();
               Palette var8 = Class_0037.method_9(var7);
               if (!this.method_2(var8) && this.method_5(var7)) {
                  return true;
               }
            }

            return false;
         }
      }
   }

   public synchronized Class_0404 method_2(WorldChunk var1, boolean var2) {
      ChunkSection[] var3 = var1.getSectionArray();
      if (var3.length == 0) {
         return Class_0404.NO_PLAINS;
      } else {
         ChunkSection var4 = var3[0];
         if (var4.getBiomeContainer() instanceof PalettedContainer var6) {
            Palette<net.minecraft.registry.entry.RegistryEntry<net.minecraft.world.biome.Biome>> var7 = Class_0037.method_9(var6);
            boolean var8 = var7.hasAny(var0 -> var0.matchesKey(BiomeKeys.PLAINS));
            if (var8 && var2) {
               if (var7.getSize() == 1) {
                  return Class_0404.PLAINS_PRESENT;
               }

               PaletteStorage var9 = Class_0037.method_2(var6);
               field_3220.clear();
               var9.forEach(field_3220::add);
               IntIterator var10 = field_3220.iterator();

               while (var10.hasNext()) {
                  int var11 = (Integer)var10.next();
                  if (((RegistryEntry)var7.get(var11)).matchesKey(BiomeKeys.PLAINS)) {
                     return Class_0404.PLAINS_PRESENT;
                  }
               }
            }

            if (var8) {
               return Class_0404.PLAINS_IN_PALETTE;
            }
         }

         return Class_0404.NO_PLAINS;
      }
   }
}
