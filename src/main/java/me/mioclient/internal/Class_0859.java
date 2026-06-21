package me.mioclient.internal;

import java.io.InputStream;
import java.util.Optional;
import java.util.Set;
import net.minecraft.resource.InputSupplier;
import net.minecraft.resource.ResourcePack;
import net.minecraft.resource.ResourcePackInfo;
import net.minecraft.resource.ResourcePackSource;
import net.minecraft.resource.ResourceType;
import net.minecraft.resource.ResourcePack.ResultConsumer;
import net.minecraft.resource.metadata.ResourceMetadataReader;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class Class_0859 implements ResourcePack {
   public ResourcePackInfo field_2769 = new ResourcePackInfo("mio", Text.literal("mio"), ResourcePackSource.BUILTIN, Optional.empty());

   public Class_0859() {
      super();
   }

   @Nullable
   public InputSupplier<InputStream> openRoot(String... segments) {
      return null;
   }

   @Nullable
   public InputSupplier<InputStream> open(ResourceType type, Identifier id) {
      return null;
   }

   public void findResources(ResourceType type, String namespace, String prefix, ResultConsumer consumer) {
   }

   public Set<String> getNamespaces(ResourceType type) {
      return null;
   }

   @Nullable
   public <T> T parseMetadata(ResourceMetadataReader<T> metaReader) {
      return null;
   }

   public ResourcePackInfo getInfo() {
      return this.field_2769;
   }

   public void close() {
   }
}
