package me.mioclient.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_1296;
import me.mioclient.module.render.AmbienceModule;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({World.class})
public class MixinWorld {
   private static AmbienceModule ambience = Hub.field_2595.method_78(AmbienceModule.class);

   public MixinWorld() {
      super();
   }

   @Inject(
      method = {"getRainGradient"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void getRainGradientHook(CallbackInfoReturnable<Float> var1) {
      if (ambience.field_213.getValue() && ambience.isToggled()) {
         var1.setReturnValue(ambience.field_214.getValue() == Class_1296.CLEAR ? 0.0F : 1.0F);
         var1.cancel();
      }
   }

   @ModifyExpressionValue(
      method = {"<init>"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/util/math/random/Random;create()Lnet/minecraft/util/math/random/Random;"
      )},
      require = 0
   )
   private Random initHook(Random var1) {
      return Random.createThreadSafe();
   }
}
