package me.mioclient.mixin;

import me.mioclient.Hub;
import me.mioclient.api.MioAPI;
import me.mioclient.event.Event_16;
import me.mioclient.event.Event_56;
import me.mioclient.mixin.ducks.DuckKeyBinding;
import me.mioclient.module.movement.NoSlowModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.input.Input;
import net.minecraft.client.input.KeyboardInput;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({KeyboardInput.class})
public class MixinKeyboardInput extends Input {
   private static NoSlowModule noslow = Hub.field_2595.method_78(NoSlowModule.class);
   @Shadow
   @Final
   private GameOptions settings;

   public MixinKeyboardInput() {
      super();
   }

   @Inject(
      method = {"tick"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void tickHookPre(boolean var1, float var2, CallbackInfo var3) {
      Event_56 var4 = new Event_56((KeyboardInput)(Object)this, var1 ? var2 : -1.0F);
      MioAPI.field_4220.method_36(var4);
      if (var4.method_464()) {
         Event_16 var5 = new Event_16(var4.method_276(), var4.method_277());
         MioAPI.field_4220.method_36(var5);
         var3.cancel();
      }
   }

   @Inject(
      method = {"tick"},
      at = {@At("TAIL")},
      cancellable = true
   )
   private void tickHook(boolean var1, float var2, CallbackInfo var3) {
      Event_16 var4 = new Event_16((KeyboardInput)(Object)this, var1 ? var2 : -1.0F);
      MioAPI.field_4220.method_36(var4);
   }

   @Redirect(
      method = {"tick"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/option/KeyBinding;isPressed()Z"
      ),
      require = 0
   )
   private boolean tickHook2(KeyBinding var1) {
      return noslow.isToggled() && noslow.method_569() && noslow.field_1685.getValue() && var1 != this.settings.sneakKey
         ? GLFW.glfwGetKey(MinecraftClient.getInstance().getWindow().getHandle(), ((DuckKeyBinding)var1).getKey().getCode()) == 1
         : var1.isPressed();
   }
}
