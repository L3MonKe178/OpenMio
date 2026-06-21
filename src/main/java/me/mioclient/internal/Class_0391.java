package me.mioclient.internal;

import java.util.Collection;
import java.util.Collections;
import me.mioclient.api.Class_0078;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class Class_0391 implements Class_0078 {
   public Class_0391() {
      super();
   }

   @Override
   public Collection<String> method_114(String var1) {
      Collection var2 = Class_0078.method_2(Registries.BLOCK).method_114(var1);
      return (Collection<String>)(!var2.isEmpty() ? var2 : Collections.emptyList());
   }

   @Override
   public Collection<String> method_115() {
      return Class_0078.method_2(Registries.BLOCK).method_115();
   }

   @Override
   public Identifier method_116() {
      return RegistryKeys.ITEM.getValue();
   }
}
