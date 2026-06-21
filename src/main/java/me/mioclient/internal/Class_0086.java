package me.mioclient.internal;

import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.function.Consumer;
import me.mioclient.api.Class_0671;
import me.mioclient.api.Class_0961;
import me.mioclient.event.Subscribe;

public class Class_0086 implements Class_0671 {
   public static boolean field_286;
   public static Constructor<Lookup> field_287;
   public static Method field_288;
   public final Class<?> field_289;
   public final boolean field_290;
   public final int field_291;
   public Consumer<Object> field_292;

   public Class_0086(Class_0961 var1, Class<?> var2, Object var3, Method var4) {
      super();
      this.field_289 = var4.getParameters()[0].getType();
      this.field_290 = Modifier.isStatic(var4.getModifiers());
      this.field_291 = var4.getAnnotation(Subscribe.class).method_800();

      try {
         String var5 = var4.getName();
         Lookup var6;
         if (field_286) {
            boolean var7 = field_287.isAccessible();
            field_287.setAccessible(true);
            var6 = field_287.newInstance(var2);
            field_287.setAccessible(var7);
         } else {
            var6 = var1.create(field_288, var2);
         }

         MethodType var12 = MethodType.methodType(void.class, var4.getParameters()[0].getType());
         MethodHandle var8;
         MethodType var9;
         if (this.field_290) {
            var8 = var6.findStatic(var2, var5, var12);
            var9 = MethodType.methodType(Consumer.class);
         } else {
            var8 = var6.findVirtual(var2, var5, var12);
            var9 = MethodType.methodType(Consumer.class, var2);
         }

         MethodHandle var10 = LambdaMetafactory.metafactory(var6, "accept", var9, MethodType.methodType(void.class, Object.class), var8, var12).getTarget();
         if (this.field_290) {
            this.field_292 = (Consumer)var10.invoke();
         } else {
            this.field_292 = (Consumer)var10.invoke((Object)var3);
         }
      } catch (Throwable var11) {
         var11.printStackTrace();
      }
   }

   @Override
   public void method_37(Object var1) {
      this.field_292.accept(var1);
   }

   @Override
   public Class<?> method_119() {
      return this.field_289;
   }

   @Override
   public int method_52() {
      return this.field_291;
   }

   @Override
   public boolean method_120() {
      return this.field_290;
   }

   static {
      try {
         field_286 = System.getProperty("java.version").startsWith("1.8");
         if (field_286) {
            field_287 = Lookup.class.getDeclaredConstructor(Class.class);
         } else {
            field_288 = MethodHandles.class.getDeclaredMethod("privateLookupIn", Class.class, Lookup.class);
         }
      } catch (NoSuchMethodException var1) {
         var1.printStackTrace();
      }
   }
}
