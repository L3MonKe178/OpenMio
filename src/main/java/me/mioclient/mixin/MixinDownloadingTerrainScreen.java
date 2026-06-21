package me.mioclient.mixin;

import net.minecraft.client.gui.screen.DownloadingTerrainScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({DownloadingTerrainScreen.class})
public class MixinDownloadingTerrainScreen {
   public MixinDownloadingTerrainScreen() {
      super();
   }

   @Inject(
      method = {"shouldCloseOnEsc"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void shouldCloseOnEscHook(CallbackInfoReturnable<Boolean> var1) {
      var1.setReturnValue(true);
   }
}
