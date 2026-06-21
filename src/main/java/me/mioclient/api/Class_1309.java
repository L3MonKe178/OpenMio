package me.mioclient.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.invoke.MethodHandles;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import me.mioclient.internal.Class_1205;
import net.minecraft.client.MinecraftClient;

public interface Class_1309 {
   Gson field_4218 = new GsonBuilder().setLenient().setPrettyPrinting().create();
   MinecraftClient field_4219 = MinecraftClient.getInstance();
   // The original referenced the deobfuscator-deleted `me.mioclient.w` central
   // dispatcher to acquire a Lookup for a non-public package. The stub keeps
   // event registration working without that crutch.
   Class_1205 field_4220 = new Class_1205() {
      {
         this.method_2("me.mioclient", (var0, var1) -> {
            try {
               return MethodHandles.lookup();
            } catch (Throwable t) {
               throw new RuntimeException(t);
            }
         });
      }
   };
   ExecutorService field_4221 = Executors.newCachedThreadPool();

   default boolean method_535() {
      return field_4219.player == null || field_4219.world == null;
   }

   static boolean method_31(Object var0) {
      return field_4219.player == var0;
   }

   static boolean method_244() {
      try {
         Class.forName("me.mioclient.loader.Globals", false, Class_1309.class.getClassLoader());
         return true;
      } catch (Exception var1) {
         return false;
      }
   }
}
