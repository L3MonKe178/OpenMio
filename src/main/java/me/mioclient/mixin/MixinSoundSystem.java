package me.mioclient.mixin;

import me.mioclient.api.MioAPI;
import me.mioclient.event.Event_40;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.client.sound.SoundSystem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({SoundSystem.class})
public class MixinSoundSystem {
   public MixinSoundSystem() {
      super();
   }

   @Inject(
      method = {"play(Lnet/minecraft/client/sound/SoundInstance;)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void playHook(SoundInstance var1, CallbackInfo var2) {
      Event_40 var3 = new Event_40(var1);
      MioAPI.field_4220.method_36(var3);
      if (var3.method_464()) {
         var2.cancel();
      }
   }
}
