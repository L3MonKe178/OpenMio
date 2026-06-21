package me.mioclient.mixin;

import java.awt.Color;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_0574;
import me.mioclient.module.render.AmbienceModule;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.texture.NativeImage;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({LightmapTextureManager.class})
public class MixinLightmapTextureManager {
   private static AmbienceModule ambience = Hub.field_2595.method_78(AmbienceModule.class);
   @Final
   @Shadow
   private NativeImage image;

   public MixinLightmapTextureManager() {
      super();
   }

   @Redirect(
      method = {"update"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/texture/NativeImage;setColor(III)V"
      )
   )
   private void updateHook(NativeImage var1, int var2, int var3, int var4) {
      if (!ambience.isToggled() || ambience.field_207.getValue() != Class_0574.SCREEN || var2 == 15 && var3 == 15) {
         var1.setColor(var2, var3, var4);
      } else {
         Color var5 = ambience.field_208.getValue();
         this.image.setColor(var2, var3, 0xFF000000 | var5.getBlue() << 16 | var5.getGreen() << 8 | var5.getRed());
      }
   }

   @Inject(
      method = {"pack"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void packHook(int var0, int var1, CallbackInfoReturnable<Integer> var2) {
      if (ambience.isToggled() && ambience.field_207.getValue() == Class_0574.SCREEN) {
         var2.setReturnValue(0);
         var2.cancel();
      }
   }
}
