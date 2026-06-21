package me.mioclient.mixin;

import net.minecraft.client.main.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({Main.class})
public class MixinMain {
   public MixinMain() {
      super();
   }

   @Inject(
      method = {"main*"},
      at = {@At("HEAD")},
      remap = false
   )
   private static void mainHook(CallbackInfo var0) {
      // On macOS, forcing AWT to non-headless makes Cocoa fight LWJGL for the main thread,
      // which stalls the render thread (terrain never finishes loading). Mio only needs AWT
      // for clipboard/screenshot helpers — those work fine in headless mode on macOS.
      if (!System.getProperty("os.name", "").toLowerCase().contains("mac")) {
         System.setProperty("java.awt.headless", "false");
      }
   }
}
