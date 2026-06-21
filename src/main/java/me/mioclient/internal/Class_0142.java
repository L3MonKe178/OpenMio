package me.mioclient.internal;

import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import me.mioclient.api.Class_1309;
import me.mioclient.mixin.ducks.DuckMinecraftClient;
import org.lwjgl.glfw.GLFW;

public final class Class_0142 implements BooleanConsumer, Class_1309 {
   public Class_0142() {
      super();
   }

   public void accept(boolean var1) {
      if (!((DuckMinecraftClient)field_4219).mio$isDisconnecting()) {
         field_4219.setScreen(null);
      }

      if (var1) {
         field_4219.stop();
      } else {
         GLFW.glfwSetWindowShouldClose(field_4219.getWindow().getHandle(), false);
      }
   }
}
