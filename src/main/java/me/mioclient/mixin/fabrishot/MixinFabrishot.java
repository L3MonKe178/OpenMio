package me.mioclient.mixin.fabrishot;

import java.io.File;
import java.io.FileInputStream;
import me.mioclient.Hub;
import me.mioclient.module.misc.ExtraScreenshotModule;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(
   targets = {"me.ramidzkh.fabrishot.Fabrishot"},
   remap = false
)
public class MixinFabrishot {
   private static ExtraScreenshotModule extrascreenshot = Hub.field_2595.method_78(ExtraScreenshotModule.class);

   public MixinFabrishot() {
      super();
   }

   @Inject(
      method = {"printFileLink"},
      at = {@At("HEAD")},
      cancellable = true,
      remap = false
   )
   private static void printFileLinkHook(File var0, CallbackInfo var1) {
      if (extrascreenshot.isToggled()) {
         try {
            FileInputStream var2 = new FileInputStream(var0);
            byte[] var3 = var2.readAllBytes();
            var2.close();
            extrascreenshot.method_425(var3);
         } catch (Exception var5) {
         }

         if (extrascreenshot.method_807()) {
            try {
               var0.delete();
            } catch (Exception var4) {
            }
         }

         if (!extrascreenshot.method_808()) {
            var1.cancel();
         }
      }
   }
}
