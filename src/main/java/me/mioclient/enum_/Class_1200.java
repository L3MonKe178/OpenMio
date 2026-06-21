package me.mioclient.enum_;

import java.util.function.Supplier;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

public enum Class_1200 {
   STANDARD(() -> GLFW.glfwCreateStandardCursor(221185)),
   POINTER(() -> GLFW.glfwCreateStandardCursor(221188)),
   INPUT(() -> GLFW.glfwCreateStandardCursor(221186));

   public static Class_1200 INPUT_2 = STANDARD;
   public final Supplier<Long> field_3719;
   public long field_3720 = -1L;

    Class_1200(Supplier<Long> var3) {
      this.field_3719 = var3;
   }

   public long method_366() {
      if (this.field_3720 == -1L) {
         this.field_3720 = this.field_3719.get();
      }

      return this.field_3720;
   }

   public void method_580() {
      if (this != INPUT_2) {
         GLFW.glfwSetCursor(MinecraftClient.getInstance().getWindow().getHandle(), this.method_366());
         INPUT_2 = this;
      }
   }
}
