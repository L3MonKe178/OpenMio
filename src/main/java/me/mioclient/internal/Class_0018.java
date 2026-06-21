package me.mioclient.internal;

import me.mioclient.api.MioAPI;
import me.mioclient.mixin.ducks.DuckKeyBinding;
import me.mioclient.record.Class_0702;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.InputUtil.Key;
import net.minecraft.client.util.InputUtil.Type;
import org.lwjgl.glfw.GLFW;

public class Class_0018 implements MioAPI {
   public Class_0018() {
      super();
   }

   public static String method_2(Class_0702 var0) {
      return method_2(var0.method_38(), var0.method_39());
   }

   public static String method_2(int var0, boolean var1) {
      if (var0 < 0) {
         return "NONE";
      } else {
         return var1
            ? new TextBuilder().method_2(var0).method_9("MOUSE\u0001")
            : InputUtil.fromKeyCode(var0, 0).getTranslationKey().replaceAll("key.keyboard.", "").replace(".", "_").toUpperCase();
      }
   }

   public static boolean method_9(Class_0702 var0) {
      return var0.method_39() ? method_3(var0.method_38()) : method_22(var0.method_38());
   }

   public static boolean method_2(KeyBinding var0) {
      Key var1 = ((DuckKeyBinding)(Object)var0).getKey();
      return var1.getCategory() == Type.MOUSE ? method_3(var1.getCode()) : method_22(var1.getCode());
   }

   public static boolean method_22(int var0) {
      return GLFW.glfwGetKey(field_4219.getWindow().getHandle(), var0) == 1;
   }

   public static boolean method_3(int var0) {
      return GLFW.glfwGetMouseButton(field_4219.getWindow().getHandle(), var0) == 1;
   }

   public static boolean method_23() {
      return GLFW.glfwGetKey(field_4219.getWindow().getHandle(), 341) == 1;
   }

   public static boolean method_24() {
      return GLFW.glfwGetKey(field_4219.getWindow().getHandle(), 340) == 1;
   }

   public static boolean method_25() {
      return GLFW.glfwGetKey(field_4219.getWindow().getHandle(), 342) == 1;
   }
}
