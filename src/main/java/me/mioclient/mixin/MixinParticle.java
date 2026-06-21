package me.mioclient.mixin;

import me.mioclient.api.Class_0549;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({Particle.class})
public class MixinParticle implements Class_0549 {
   @Shadow
   protected float alpha;
   @Unique
   private float mio$initialAlpha;

   public MixinParticle() {
      super();
   }

   @Inject(
      method = {"<init>(Lnet/minecraft/client/world/ClientWorld;DDD)V"},
      at = {@At("TAIL")}
   )
   private void init(ClientWorld var1, double var2, double var4, double var6, CallbackInfo var8) {
      this.mio$initialAlpha = this.alpha;
   }

   @ModifyVariable(
      method = {"setAlpha"},
      at = @At("HEAD"),
      ordinal = 0,
      argsOnly = true
   )
   private float setAlpha(float var1) {
      return var1 * this.mio$initialAlpha;
   }

   @Override
   public void mio$setInitialAlpha(float var1) {
      this.mio$initialAlpha = var1;
      this.alpha = var1;
   }

   @Override
   public float mio$getInitialAlpha() {
      return this.mio$initialAlpha;
   }
}
