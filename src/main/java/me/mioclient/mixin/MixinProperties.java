package me.mioclient.mixin;

import me.mioclient.Hub;
import me.mioclient.api.Class_1309;
import me.mioclient.module.render.SkyColorModule;
import net.minecraft.client.world.ClientWorld.Properties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({Properties.class})
public class MixinProperties {
   private static SkyColorModule skycolor = Hub.field_2595.method_78(SkyColorModule.class);

   public MixinProperties() {
      super();
   }

   @Inject(
      method = {"getHorizonShadingRatio"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onGetSkyProperties(CallbackInfoReturnable<Float> var1) {
      if (Class_1309.field_4219.world != null) {
         if (skycolor.isToggled() && skycolor.method_125() && !Class_1309.field_4219.world.getRegistryKey().getValue().getPath().contains("over")) {
            var1.setReturnValue(1.0F);
            var1.cancel();
         }
      }
   }
}
