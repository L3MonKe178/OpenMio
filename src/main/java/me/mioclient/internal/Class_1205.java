package me.mioclient.internal;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import me.mioclient.api.Class_0422;
import me.mioclient.api.Class_0491;
import me.mioclient.api.Class_0671;
import me.mioclient.api.Class_0961;
import me.mioclient.event.Subscribe;
import me.mioclient.exception.Class_0805;

public class Class_1205 implements Class_0491 {
   public final Map<Object, List<Class_0671>> field_3737 = new ConcurrentHashMap<>();
   public final Map<Class<?>, List<Class_0671>> field_3738 = new ConcurrentHashMap<>();
   public final Map<Class<?>, List<Class_0671>> field_3739 = new ConcurrentHashMap<>();
   public final List<Class_1236> field_3740 = new ArrayList<>();

   public Class_1205() {
      super();
   }

   @Override
   public void method_2(String var1, Class_0961 var2) {
      synchronized (this.field_3740) {
         this.field_3740.add(new Class_1236(var1, var2));
      }
   }

   @Override
   public <T> T method_36(T var1) {
      List<Class_0671> var2 = this.field_3739.get(var1.getClass());
      if (var2 != null) {
         for (Class_0671 var4 : var2) {
            var4.method_37(var1);
         }
      }

      return (T)var1;
   }

   @Override
   public <T extends Class_0422> T method_2(T var1) {
      List<Class_0671> var2 = this.field_3739.get(var1.getClass());
      if (var2 != null) {
         var1.method_52(false);

         for (Class_0671 var4 : var2) {
            var4.method_37(var1);
            if (var1.method_464()) {
               break;
            }
         }
      }

      return (T)var1;
   }

   @Override
   public void method_14(Object var1) {
      this.method_2(this.method_2(var1.getClass(), var1), false);
   }

   @Override
   public void method_5(Class<?> var1) {
      this.method_2(this.method_2(var1, null), true);
   }

   @Override
   public void method_2(Class_0671 var1) {
      this.method_2(var1, false);
   }

   public void method_2(List<Class_0671> var1, boolean var2) {
      for (Class_0671 var4 : var1) {
         this.method_2(var4, var2);
      }
   }

   public void method_2(Class_0671 var1, boolean var2) {
      if (var2) {
         if (var1.method_120()) {
            this.method_2(this.field_3739.computeIfAbsent(var1.method_119(), var0 -> new CopyOnWriteArrayList<>()), var1);
         }
      } else {
         this.method_2(this.field_3739.computeIfAbsent(var1.method_119(), var0 -> new CopyOnWriteArrayList<>()), var1);
      }
   }

   public void method_2(List<Class_0671> var1, Class_0671 var2) {
      int var3 = 0;

      while (var3 < var1.size() && var2.method_52() <= ((Class_0671)var1.get(var3)).method_52()) {
         var3++;
      }

      var1.add(var3, var2);
   }

   @Override
   public void method_17(Object var1) {
      this.method_9(this.method_2(var1.getClass(), var1), false);
   }

   @Override
   public void method_7(Class<?> var1) {
      this.method_9(this.method_2(var1, null), true);
   }

   @Override
   public void method_9(Class_0671 var1) {
      this.method_9(var1, false);
   }

   public void method_9(List<Class_0671> var1, boolean var2) {
      for (Class_0671 var4 : var1) {
         this.method_9(var4, var2);
      }
   }

   public void method_9(Class_0671 var1, boolean var2) {
      List var3 = this.field_3739.get(var1.method_119());
      if (var3 != null) {
         if (var2) {
            if (var1.method_120()) {
               var3.remove(var1);
            }
         } else {
            var3.remove(var1);
         }
      }
   }

   public List<Class_0671> method_2(Class<?> var1, Object var2) {
      Function var3 = var3x -> {
         CopyOnWriteArrayList var4 = new CopyOnWriteArrayList();
         this.method_2(var4, var1, var2);
         return var4;
      };
      if (var2 == null) {
         return this.field_3738.computeIfAbsent(var1, var3);
      } else {
         for (Object var5 : this.field_3737.keySet()) {
            if (var5 == var2) {
               return this.field_3737.get(var2);
            }
         }

         List var6 = (List)var3.apply(var2);
         this.field_3737.put(var2, var6);
         return var6;
      }
   }

   public void method_2(List<Class_0671> var1, Class<?> var2, Object var3) {
      for (Method var7 : var2.getDeclaredMethods()) {
         if (this.method_2(var7)) {
            var1.add(new Class_0086(this.method_29(var2), var2, var3, var7));
         }
      }

      if (var2.getSuperclass() != null) {
         this.method_2(var1, var2.getSuperclass(), var3);
      }
   }

   public boolean method_2(Method var1) {
      if (!var1.isAnnotationPresent(Subscribe.class)) {
         return false;
      } else if (var1.getReturnType() != void.class) {
         return false;
      } else {
         return var1.getParameterCount() != 1 ? false : !var1.getParameters()[0].getType().isPrimitive();
      }
   }

   public Class_0961 method_29(Class<?> var1) {
      synchronized (this.field_3740) {
         for (Class_1236 var4 : this.field_3740) {
            if (var1.getName().startsWith(var4.field_3884)) {
               return var4.field_3885;
            }
         }
      }

      throw new Class_0805(var1);
   }
}
