package me.mioclient.mixin;

import me.mioclient.Hub;
import me.mioclient.module.movement.ElytraFlyModule;
import net.minecraft.client.sound.ElytraSoundInstance;
import net.minecraft.client.sound.MovingSoundInstance;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ElytraSoundInstance.class})
public abstract class MixinElytraSoundInstance extends MovingSoundInstance {
   private static ElytraFlyModule elytrafly = Hub.field_2595.method_78(ElytraFlyModule.class);

   protected MixinElytraSoundInstance(SoundEvent var1, SoundCategory var2, Random var3) {
      super(var1, var2, var3);
   }

   @Inject(
      method = {"tick"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void tickHook(CallbackInfo var1) {
      if (elytrafly.isToggled() && elytrafly.field_4386.getValue()) {
         this.volume = 0.0F;
         var1.cancel();
      }
   }
}
