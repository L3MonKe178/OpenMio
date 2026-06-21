package me.mioclient.mixin;

import me.mioclient.Hub;
import me.mioclient.api.Class_1309;
import me.mioclient.event.Event_28;
import me.mioclient.module.render.NoRenderModule;
import net.minecraft.block.BlockState;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({ParticleManager.class})
public abstract class MixinParticleManager {
   private static final NoRenderModule norender = Hub.field_2595.method_78(NoRenderModule.class);

   public MixinParticleManager() {
      super();
   }

   @Shadow
   @Nullable
   protected abstract <T extends ParticleEffect> Particle method_3055(T var1, double var2, double var4, double var6, double var8, double var10, double var12);

   @Inject(
      method = {"addParticle(Lnet/minecraft/client/particle/Particle;)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void addParticle(Particle var1, CallbackInfo var2) {
      Event_28 var3 = new Event_28(var1);
      Class_1309.field_4220.method_36(var3);
      if (var3.method_464()) {
         var2.cancel();
      }
   }

   @Inject(
      method = {"addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)Lnet/minecraft/client/particle/Particle;"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void addParticleHook(
      ParticleEffect var1, double var2, double var4, double var6, double var8, double var10, double var12, CallbackInfoReturnable<Particle> var14
   ) {
      if (norender.method_2(var1)) {
         var14.setReturnValue(this.method_3055(var1, var2, var4, var6, var8, var10, var12));
         var14.cancel();
      }
   }

   @Inject(
      method = {"addBlockBreakingParticles"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void addBlockBreakingParticlesHook(BlockPos var1, Direction var2, CallbackInfo var3) {
      if (norender.method_281()) {
         var3.cancel();
      }
   }

   @Inject(
      method = {"addBlockBreakParticles"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void addBlockBreakParticlesHook(BlockPos var1, BlockState var2, CallbackInfo var3) {
      if (norender.method_281()) {
         var3.cancel();
      }
   }
}
