package me.mioclient.internal;

import me.mioclient.Hub;
import me.mioclient.api.Class_1309;
import me.mioclient.mixin.ducks.DuckKeyBinding;
import me.mioclient.module.movement.NoSlowModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.input.Input;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

public class Class_0411 extends Input implements Class_1309 {
   public static NoSlowModule noslow = Hub.field_2595.method_78(NoSlowModule.class);

   public Class_0411() {
      super();
   }

   public void tick(boolean slowDown, float slowDownFactor) {
      this.movementForward = this.method_2(this.method_2(field_4219.options.forwardKey), this.method_2(field_4219.options.backKey));
      this.movementSideways = this.method_2(this.method_2(field_4219.options.leftKey), this.method_2(field_4219.options.rightKey));
   }

   public float method_2(boolean var1, boolean var2) {
      if (var1 == var2) {
         return 0.0F;
      } else {
         return var1 ? Float.intBitsToFloat(1065353216) : Float.intBitsToFloat(-1082130432);
      }
   }

   public boolean method_2(KeyBinding var1) {
      return noslow.isToggled() && noslow.method_569() && noslow.field_1685.getValue()
         ? GLFW.glfwGetKey(MinecraftClient.getInstance().getWindow().getHandle(), ((DuckKeyBinding)var1).getKey().getCode()) == 1
         : var1.isPressed();
   }
}
