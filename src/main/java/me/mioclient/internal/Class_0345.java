package me.mioclient.internal;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import me.mioclient.annotation.Class_1151;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.fabricmc.loader.api.FabricLoader;

public class Class_0345 {
   public static boolean field_1146 = false;

   public Class_0345() {
      super();
   }

   public static void method_9(Module var0) {
      for (Field var4 : var0.getClass().getDeclaredFields()) {
         if (method_2(var4)) {
            var4.setAccessible(true);

            try {
               var0.unregister((Setting<?>)var4.get(var0));
            } catch (IllegalAccessException var6) {
               var6.printStackTrace();
            }
         }
      }
   }

   public static boolean method_9(Class<?> var0) {
      return method_391() && method_2(var0);
   }

   public static boolean method_29(Enum<?> var0) {
      try {
         return method_391() && method_2((AnnotatedElement)var0.getDeclaringClass().getDeclaredField(var0.name()));
      } catch (NoSuchFieldException var2) {
         return false;
      }
   }

   public static boolean method_2(Field var0) {
      return method_391() && method_2((AnnotatedElement)var0);
   }

   public static boolean method_391() {
      return !field_1146;
   }

   public static boolean method_2(AnnotatedElement var0) {
      return var0.isAnnotationPresent(Class_1151.class);
   }

   static {
      for (String var3 : FabricLoader.getInstance().getLaunchArguments(true)) {
         if ("--mio-allow-experimental".equals(var3)) {
            field_1146 = true;
            break;
         }
      }

      if (System.getProperties().get("mio.allowExperimental") != null && "true".equalsIgnoreCase((String)System.getProperties().get("mio.allowExperimental"))) {
         field_1146 = true;
      }
   }
}
