package me.mioclient.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import java.util.Random;
import me.mioclient.Hub;
import me.mioclient.api.Class_0514;
import me.mioclient.api.MioAPI;
import me.mioclient.enum_.Class_0647;
import me.mioclient.enum_.Class_1296;
import me.mioclient.event.Event_49;
import me.mioclient.internal.Class_1225;
import me.mioclient.module.render.AmbienceModule;
import me.mioclient.module.render.SkyColorModule;
import net.minecraft.block.Block;
import net.minecraft.client.render.DimensionEffects;
import net.minecraft.client.render.DimensionEffects.End;
import net.minecraft.client.render.DimensionEffects.SkyType;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity.RemovalReason;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.world.biome.BiomeParticleConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({ClientWorld.class})
public abstract class MixinClientWorld implements Class_0514 {
   private static SkyColorModule skycolor = Hub.field_2595.method_78(SkyColorModule.class);
   private static AmbienceModule ambience = Hub.field_2595.method_78(AmbienceModule.class);
   @Shadow
   @Final
   private DimensionEffects field_24606;
   @Unique
   private final DimensionEffects mio$customEnd = new End();
   @Unique
   private final DimensionEffects customSky = new DimensionEffects(Float.NaN, true, SkyType.NONE, false, false) {
      public Vec3d adjustFogColor(Vec3d color, float sunHeight) {
         return Vec3d.unpackRgb(MixinClientWorld.skycolor.field_317.getValue().getRGB());
      }

      public boolean useThickFog(int camX, int camY) {
         return MixinClientWorld.skycolor.field_315.getValue();
      }

      public float[] getFogColorOverride(float skyAngle, float tickDelta) {
         return null;
      }
   };
   @Unique
   private final Random random = new Random();

   public MixinClientWorld() {
      super();
   }

   @Shadow
   public abstract void method_8406(ParticleEffect var1, double var2, double var4, double var6, double var8, double var10, double var12);

   @ModifyArg(
      method = {"method_24462"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/world/ClientWorld;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V"
      )
   )
   private ParticleEffect randomBlockDisplayTickHook(ParticleEffect var1, @Local(argsOnly = true) Mutable var2) {
      double var3 = MioAPI.field_4219.gameRenderer.getCamera().getPos().squaredDistanceTo((double)var2.getX(), (double)var2.getY(), (double)var2.getZ());
      return ambience.isToggled() && ambience.field_214.getValue() == Class_1296.DUSTY && ambience.field_213.getValue() && var3 < 25.0
         ? new BiomeParticleConfig(this.random.nextBoolean() ? ParticleTypes.WHITE_ASH : ParticleTypes.ASH, 0.1F).getParticle()
         : var1;
   }

   @Inject(
      method = {"randomBlockDisplayTick"},
      at = {@At(
         value = "INVOKE",
         target = "Ljava/util/Optional;ifPresent(Ljava/util/function/Consumer;)V",
         shift = Shift.BEFORE
      )}
   )
   private void randomBlockDisplayTickHook0(
      int var1, int var2, int var3, int var4, net.minecraft.util.math.random.Random var5, Block var6, Mutable var7, CallbackInfo var8
   ) {
      if (ambience.isToggled() && ambience.field_214.getValue() == Class_1296.DUSTY && ambience.field_213.getValue()) {
         BiomeParticleConfig var9 = new BiomeParticleConfig(
            var5.nextBoolean() ? ParticleTypes.WHITE_ASH : ParticleTypes.ASH, 0.2F * ambience.field_216.getValue()
         );
         if (!var9.shouldAddParticle(var5)) {
            return;
         }

         this.method_8406(
            var9.getParticle(),
            (double)var7.getX() + this.random.nextDouble(),
            (double)var7.getY() + this.random.nextDouble(),
            (double)var7.getZ() + this.random.nextDouble(),
            0.0,
            0.0,
            0.0
         );
      }
   }

   @Inject(
      method = {"removeEntity"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void removeEntityHook(int var1, RemovalReason var2, CallbackInfo var3) {
      Event_49 var4 = new Event_49(var1);
      MioAPI.field_4220.method_36(var4);
      if (var4.method_464()) {
         var3.cancel();
      }
   }

   @Inject(
      method = {"getSkyColor"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void getSkyColorHook(Vec3d var1, float var2, CallbackInfoReturnable<Vec3d> var3) {
      if (skycolor.isToggled() && skycolor.method_125()) {
         var3.setReturnValue(Vec3d.unpackRgb(skycolor.field_317.getValue().getRGB()));
         var3.cancel();
      }
   }

   @Inject(
      method = {"getDimensionEffects"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onGetSkyProperties(CallbackInfoReturnable<DimensionEffects> var1) {
      if (MioAPI.field_4219.world != null) {
         DimensionEffects var2 = null;
         if (skycolor.field_314.getValue() == Class_0647.END) {
            var2 = this.mio$customEnd;
         } else if (!Class_1225.method_1071().method_232() || skycolor.field_314.getValue() == Class_0647.FLAT) {
            var2 = this.customSky;
         }

         if (skycolor.isToggled() && skycolor.method_125() && var2 != null) {
            var1.setReturnValue(var2);
            var1.cancel();
         }
      }
   }

   @Override
   public DimensionEffects mio$getOriginalEffects() {
      return this.field_24606;
   }
}
