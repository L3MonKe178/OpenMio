package me.mioclient.internal;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import me.mioclient.api.Class_0078;
import net.minecraft.block.AbstractSignBlock;
import net.minecraft.block.BannerBlock;
import net.minecraft.block.BedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.CarpetBlock;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StainedGlassBlock;
import net.minecraft.block.StainedGlassPaneBlock;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry.Reference;
import net.minecraft.util.Identifier;

public class Class_1098 implements Class_0078 {
   public static final Map<String, List<String>> field_3410 = new HashMap<>();

   public Class_1098() {
      super();
   }

   @Override
   public Collection<String> method_114(String var1) {
      return field_3410.getOrDefault(var1.toLowerCase(), Collections.emptyList());
   }

   @Override
   public Collection<String> method_115() {
      return field_3410.keySet();
   }

   @Override
   public Identifier method_116() {
      return RegistryKeys.BLOCK.getValue();
   }

   public static void method_2(String var0, Stream<Block> var1) {
      field_3410.put(var0, var1.map(Class_1098::method_9).toList());
   }

   public static String method_9(Block var0) {
      return Registries.BLOCK.getId(var0).toShortTranslationKey();
   }

   static {
      method_2("beds", Registries.BLOCK.stream().filter(var0 -> var0 instanceof BedBlock));
      method_2("banners", Registries.BLOCK.stream().filter(var0 -> var0 instanceof BannerBlock));
      method_2("doors", Registries.BLOCK.stream().filter(var0 -> var0 instanceof DoorBlock));
      method_2("slabs", Registries.BLOCK.stream().filter(var0 -> var0 instanceof SlabBlock));
      method_2("shulkers", Registries.BLOCK.stream().filter(var0 -> var0 instanceof ShulkerBoxBlock));
      method_2("carpets", Registries.BLOCK.stream().filter(var0 -> var0 instanceof CarpetBlock));
      method_2("glasses", Registries.BLOCK.stream().filter(var0 -> var0 instanceof StainedGlassBlock || var0 == Blocks.GLASS));
      method_2("glass_panes", Registries.BLOCK.stream().filter(var0 -> var0 instanceof StainedGlassPaneBlock || var0 == Blocks.GLASS_PANE));
      method_2("signs", Registries.BLOCK.stream().filter(var0 -> var0 instanceof AbstractSignBlock));
      method_2(
         "ores",
         Registries.BLOCK.streamEntries().filter(var0 -> var0.matches(var0x -> var0x.getValue().toTranslationKey().contains("ore"))).map(Reference::value)
      );
   }
}
