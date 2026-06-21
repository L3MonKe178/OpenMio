package me.mioclient.mixin;

import me.mioclient.Hub;
import me.mioclient.internal.Class_0372;
import me.mioclient.module.exploit.IllegalDisconnectModule;
import me.mioclient.module.misc.AntiQuitModule;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({GameMenuScreen.class})
public abstract class MixinGameMenuScreen extends Screen {
   private static IllegalDisconnectModule illegaldisconnect = Hub.field_2595.method_78(IllegalDisconnectModule.class);
   @Unique
   private boolean mio$ignoreDisconnect;
   private static AntiQuitModule antiquit = Hub.field_2595.method_78(AntiQuitModule.class);

   @Shadow
   protected abstract void disconnect();

   protected MixinGameMenuScreen(Text var1) {
      super(var1);
   }

   @Inject(
      method = {"disconnect"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void disconnectHook(CallbackInfo var1) {
      if (antiquit.isToggled() && antiquit.field_3760.getValue() && !this.mio$ignoreDisconnect) {
         this.client.setScreen(new Class_0372(() -> {
            this.mio$ignoreDisconnect = true;
            this.disconnect();
         }));
         var1.cancel();
      } else {
         if (illegaldisconnect.isToggled() && this.client.player != null && !this.client.isInSingleplayer()) {
            illegaldisconnect.method_202();
            var1.cancel();
         }
      }
   }
}
