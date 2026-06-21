package me.mioclient.internal;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import me.mioclient.Hub;
import me.mioclient.api.MioAPI;
import me.mioclient.module.Module;
import sun.misc.Unsafe;

public class Class_0668 {
   public Unsafe field_2144;
   public List<String> field_2145 = new ArrayList<>();

   public Class_0668() {
      super();

      try {
         Field var1 = Unsafe.class.getDeclaredField("theUnsafe");
         var1.setAccessible(true);
         this.field_2144 = (Unsafe)var1.get(null);
      } catch (Exception var2) {
      }
   }

   public <T extends Module> T method_78(Class<T> var1) {
      if (this.field_2144 != null && Hub.field_2599 == null) {
         try {
            String var2 = null;

            for (StackTraceElement var6 : Thread.currentThread().getStackTrace()) {
               String var7 = var6.getClassName();
               if (!var7.contains("java.lang.Thread") && !var7.contains(this.getClass().getName())) {
                  var2 = var7;
                  break;
               }
            }

            if (var2 == null) {
               throw new RuntimeException();
            }

            if (!this.field_2145.contains(var2)) {
               this.field_2145.add(var2);
            }
         } catch (Exception var8) {
         }

         return null;
      } else {
         return Hub.field_2599.method_78(var1);
      }
   }

   public void method_669() {
      for (String var2 : this.field_2145) {
         Class<?> var3 = null;

         try {
            var3 = Class.forName(var2, false, MioAPI.class.getClassLoader());
         } catch (Exception var10) {
            throw new RuntimeException();
         }

         for (Field var7 : var3.getDeclaredFields()) {
            if (Modifier.isStatic(var7.getModifiers()) && Module.class.isAssignableFrom(var7.getType())) {
               try {
                  Module var8 = Hub.field_2599.method_78((Class<Module>)var7.getType());
                  if (var8 == null) {
                     for (Module m : Hub.field_2599.field_2974.values()) {
                        if (var7.getType().isInstance(m)) { var8 = m; break; }
                     }
                  }
                  if (var8 != null) {
                     this.field_2144.putObject(var3, this.field_2144.staticFieldOffset(var7), var8);
                  }
               } catch (Exception var9) {
               }
            }
         }
      }

      this.field_2145.clear();
      System.gc();
   }
}
