package me.mioclient.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import java.io.File;
import java.util.function.Consumer;
import me.mioclient.Hub;
import me.mioclient.module.misc.ExtraScreenshotModule;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.util.ScreenshotRecorder;
import net.minecraft.text.Text;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ScreenshotRecorder.class})
public class MixinScreenshotRecorder {
   private static ExtraScreenshotModule screenshot = Hub.field_2595.method_78(ExtraScreenshotModule.class);

   public MixinScreenshotRecorder() {
      super();
   }

   @Inject(
      method = {"saveScreenshotInner"},
      at = {@At(
         value = "INVOKE_ASSIGN",
         target = "Lnet/minecraft/client/util/ScreenshotRecorder;takeScreenshot(Lnet/minecraft/client/gl/Framebuffer;)Lnet/minecraft/client/texture/NativeImage;",
         shift = Shift.AFTER
      )},
      cancellable = true
   )
   private static void saveScreenshotInner(File var0, String var1, Framebuffer var2, Consumer<Text> var3, CallbackInfo var4, @Local NativeImage var5) {
      if (screenshot.isToggled()) {
         Util.getIoWorkerExecutor().execute(() -> screenshot.method_2(var5));
         if (screenshot.method_807()) {
            var4.cancel();
         }
      }
   }

   @ModifyVariable(
      method = {"saveScreenshotInner"},
      at = @At("HEAD"),
      ordinal = 0,
      argsOnly = true
   )
   private static Consumer<Text> modifyMessageReceiver(Consumer<Text> var0) {
      return screenshot.isToggled() && !screenshot.method_808() ? new Consumer<Text>() {
         public void accept(Text var1) {
         }
      } : var0;
   }
}
