package me.mioclient.api;

import java.util.Collection;
import java.util.List;
import me.mioclient.internal.Class_0391;
import me.mioclient.internal.Class_0779;
import me.mioclient.internal.Class_1098;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public interface Class_0078 {
   List<Class_0078> field_279 = List.of(new Class_1098(), new Class_0391());
   Class_0779 field_280 = new Class_0779();

   Collection<String> method_114(String var1);

   Collection<String> method_115();

   Identifier method_116();

   static Class_0078 method_2(Registry<?> var0) {
      for (Class_0078 var2 : field_279) {
         if (var0.getKey().getValue().equals(var2.method_116())) {
            return var2;
         }
      }

      return field_280;
   }
}
