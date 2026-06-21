package me.mioclient.mixin;

import me.mioclient.Hub;
import net.minecraft.client.render.RenderTickCounter.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({Dynamic.class})
public class MixinRenderTickCounter {
   @Shadow
   private float field_51958;

   public MixinRenderTickCounter() {
      super();
   }

   @Inject(
      method = {"beginRenderTick(J)I"},
      at = {@At(
         value = "FIELD",
         target = "Lnet/minecraft/client/render/RenderTickCounter$Dynamic;prevTimeMillis:J"
      )}
   )
   public void beginRenderTick(long var1, CallbackInfoReturnable<Integer> var3) {
      if (Hub.field_2596 != null) {
         this.field_51958 = this.field_51958 * Hub.field_2596.method_537();
      }
   }
}
