package me.mioclient.mixin;

import me.mioclient.Hub;
import me.mioclient.module.render.AmbienceModule;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Precipitation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({Biome.class})
public class MixinBiome {
   private static AmbienceModule ambience = Hub.field_2595.method_78(AmbienceModule.class);

   public MixinBiome() {
      super();
   }

   @Inject(
      method = {"getPrecipitation"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void getPrecipitationHook(BlockPos var1, CallbackInfoReturnable<Precipitation> var2) {
      if (ambience.isToggled() && ambience.field_213.getValue()) {
         var2.cancel();
         var2.setReturnValue(ambience.field_214.getValue().method_1157());
      }
   }
}
