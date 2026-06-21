package me.mioclient.mixin;

import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.particle.FireworksSparkParticle.Explosion;
import net.minecraft.client.particle.FireworksSparkParticle.FireworkParticle;
import net.minecraft.particle.ParticleTypes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({FireworkParticle.class})
public class MixinFireworkParticle {
   @Final
   @Shadow
   private ParticleManager field_3805;

   public MixinFireworkParticle() {
      super();
   }

   @Inject(
      method = {"addExplosionParticle"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void addExplosionParticleHook(
      double var1,
      double var3,
      double var5,
      double var7,
      double var9,
      double var11,
      IntList var13,
      IntList var14,
      boolean var15,
      boolean var16,
      CallbackInfo var17
   ) {
      Explosion var18 = (Explosion)this.field_3805.addParticle(ParticleTypes.FIREWORK, var1, var3, var5, var7, var9, var11);
      if (var18 == null) {
         var17.cancel();
      }
   }
}
