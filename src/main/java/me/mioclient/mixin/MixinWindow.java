package me.mioclient.mixin;

import net.minecraft.client.WindowEventHandler;
import net.minecraft.client.WindowSettings;
import net.minecraft.client.util.MonitorTracker;
import net.minecraft.client.util.Window;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({Window.class})
public class MixinWindow {
   public MixinWindow() {
      super();
   }

   @Inject(
      method = {"<init>"},
      at = {@At(
         value = "INVOKE_ASSIGN",
         target = "Lorg/lwjgl/glfw/GLFW;glfwCreateWindow(IILjava/lang/CharSequence;JJ)J",
         shift = Shift.BEFORE,
         remap = false
      )}
   )
   private void init(WindowEventHandler var1, MonitorTracker var2, WindowSettings var3, String var4, String var5, CallbackInfo var6) {
      GLFW.glfwWindowHint(139270, 0);
   }
}
