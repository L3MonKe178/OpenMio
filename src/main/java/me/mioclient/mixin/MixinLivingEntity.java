package me.mioclient.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.mioclient.Hub;
import me.mioclient.api.Class_0705;
import me.mioclient.api.MioAPI;
import me.mioclient.enum_.Class_0286;
import me.mioclient.enum_.Class_1229;
import me.mioclient.enum_.StrafeType;
import me.mioclient.event.Event_29;
import me.mioclient.event.Event_39;
import me.mioclient.event.Event_64;
import me.mioclient.internal.Class_0464;
import me.mioclient.internal.Class_0654;
import me.mioclient.internal.Class_0922;
import me.mioclient.internal.PacketUtil;
import me.mioclient.internal.Class_1334;
import me.mioclient.module.misc.SwingModule;
import me.mioclient.module.movement.ElytraFlyModule;
import me.mioclient.module.movement.FastLadderModule;
import me.mioclient.module.movement.FireworksModule;
import me.mioclient.module.movement.JesusModule;
import me.mioclient.module.movement.NoJumpDelayModule;
import me.mioclient.module.movement.SpeedModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectUtil;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({LivingEntity.class})
public abstract class MixinLivingEntity extends Entity implements Class_0705 {
   @Shadow
   private int jumpingCooldown;
   @Unique
   private Event_64 mio$moveEvent;
   @Unique
   private Event_39 event;
   @Unique
   private boolean serverDead;
   @Unique
   private boolean prevFlying;
   @Unique
   private float prevLookYaw;
   @Unique
   private float prevLookPitch;
   @Unique
   private long prevTime = 0L;
   private static ElytraFlyModule efly = Hub.field_2595.method_78(ElytraFlyModule.class);
   private static NoJumpDelayModule njd = Hub.field_2595.method_78(NoJumpDelayModule.class);
   private static JesusModule jesus = Hub.field_2595.method_78(JesusModule.class);
   private static SwingModule swing = Hub.field_2595.method_78(SwingModule.class);
   private static final SpeedModule speed = Hub.field_2595.method_78(SpeedModule.class);
   private static final FireworksModule fireworks = Hub.field_2595.method_78(FireworksModule.class);
   private static final FastLadderModule fastladder = Hub.field_2595.method_78(FastLadderModule.class);

   public MixinLivingEntity(EntityType<?> var1, World var2) {
      super(var1, var2);
   }

   @Shadow
   public abstract float getYaw(float tickDelta);

   @Shadow
   public abstract void setBodyYaw(float bodyYaw);

   @Shadow
   public abstract boolean damage(DamageSource source, float amount);

   @Inject(
      method = {"getHandSwingDuration"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void getHandSwingDurationHook(CallbackInfoReturnable<Integer> var1) {
      if (swing.isToggled()) {
         LivingEntity var2 = (LivingEntity)(Object)this;
         var1.cancel();
         boolean var3 = swing.field_505.getValue() == Class_0286.ONE_EIGHT;
         int var4 = (int)((double)(var3 ? 7 : 6) / swing.field_506.getValue());
         if (StatusEffectUtil.hasHaste(var2)) {
            var1.setReturnValue(var4 - (1 + StatusEffectUtil.getHasteAmplifier(var2)));
         } else {
            var1.setReturnValue(
               var2.hasStatusEffect(StatusEffects.MINING_FATIGUE) ? var4 + (1 + var2.getStatusEffect(StatusEffects.MINING_FATIGUE).getAmplifier()) * 2 : var4
            );
         }
      }
   }

   @Inject(
      method = {"isFallFlying"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void isFallFlyingHook(CallbackInfoReturnable<Boolean> var1) {
      if (MioAPI.method_31(this)) {
         if (fireworks.isToggled() && fireworks.field_2686.getValue() && fireworks.method_785() > 0) {
            var1.cancel();
            var1.setReturnValue(true);
         }

         if (efly.method_1178() || efly.method_1183()) {
            var1.setReturnValue(false);
            var1.cancel();
         }
      }
   }

   @Inject(
      method = {"canWalkOnFluid"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void canWalkOnFluidHook(CallbackInfoReturnable<Boolean> var1) {
      if ((LivingEntity)(Object)this == MinecraftClient.getInstance().player && jesus.isToggled() && jesus.method_442() && !MinecraftClient.getInstance().player.isTouchingWater()) {
         var1.setReturnValue(true);
      }
   }

   @Inject(
      method = {"travel"},
      at = {@At("HEAD")}
   )
   private void travelPreHook(Vec3d var1, CallbackInfo var2) {
      if ((LivingEntity)(Object)this == MinecraftClient.getInstance().player) {
         ElytraFlyModule.field_885 = true;
         this.prevLookYaw = this.getYaw(1.0F);
         this.prevLookPitch = this.getPitch();
         this.event = new Event_39(this.prevLookYaw, this.prevLookPitch, false);
         MioAPI.field_4220.method_36(this.event);
         if (this.event.method_464()) {
            this.setYaw(this.event.method_500());
            this.setPitch(this.event.method_501());
         }
      }
   }

   @Inject(
      method = {"travel"},
      at = {@At("TAIL")}
   )
   private void travelPostHook(Vec3d var1, CallbackInfo var2) {
      if ((LivingEntity)(Object)this == MinecraftClient.getInstance().player) {
         ElytraFlyModule.field_885 = false;
         if (this.event.method_464()) {
            this.setYaw(this.prevLookYaw);
            this.setPitch(this.prevLookPitch);
         }
      }
   }

   @ModifyExpressionValue(
      method = {"jump"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/entity/LivingEntity;getYaw()F"
      )}
   )
   private float jumpFix(float var1) {
      this.event = new Event_39(this.getYaw(), this.getPitch(), true);
      MioAPI.field_4220.method_36(this.event);
      return this.event.method_464() ? this.event.method_500() : var1;
   }

   @Inject(
      method = {"shouldSpawnConsumptionEffects"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void shouldSpawnConsumptionEffects(CallbackInfoReturnable<Boolean> var1) {
      if (Class_0654.method_663()) {
         var1.setReturnValue(false);
      }
   }

   @ModifyExpressionValue(
      method = {"damage"},
      at = {@At(
         value = "FIELD",
         target = "Lnet/minecraft/world/World;isClient:Z"
      )}
   )
   private boolean damageHook(boolean var1) {
      return (Object)this instanceof Class_0922 ? false : var1;
   }

   @Override
   public void setServerSideDead(boolean var1) {
      this.serverDead = var1;
   }

   @Override
   public boolean isServerSideDead() {
      return this.serverDead;
   }

   @Inject(
      method = {"tickMovement"},
      at = {@At("HEAD")}
   )
   public void reduceCooldown(CallbackInfo var1) {
      MinecraftClient var2 = MinecraftClient.getInstance();
      if (efly.isToggled() && efly.field_4349.getValue() == Class_1229.BOUNCE && this.equals(var2.player) && this.jumpingCooldown > 2) {
         this.jumpingCooldown = 2;
      } else if (njd.isToggled() && this.equals(var2.player) && !var2.player.isFallFlying()) {
         this.jumpingCooldown = 0;
      }
   }

   @Inject(
      method = {"isFallFlying"},
      at = {@At("TAIL")},
      cancellable = true
   )
   public void onRecast(CallbackInfoReturnable<Boolean> var1) {
      MinecraftClient var2 = MinecraftClient.getInstance();
      if ((LivingEntity)(Object)this == var2.player) {
         if (efly.method_1183()) {
            var1.setReturnValue(false);
         } else {
            long var3 = System.currentTimeMillis();
            long var5 = var3 - this.prevTime;
            if (var5 >= 12L) {
               if (efly.isToggled() && efly.field_4349.getValue() == Class_1229.BOUNCE) {
                  boolean var7 = (Boolean)var1.getReturnValue();
                  if (this.prevFlying && !var7) {
                     if (efly.method_1179()) {
                        PacketUtil.method_1099();
                     }

                     var1.setReturnValue(efly.method_1179());
                  }

                  this.prevFlying = var7;
               }

               this.prevTime = var3;
            }
         }
      }
   }

   @Inject(
      method = {"hasStatusEffect"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void hasStatusEffectHook(RegistryEntry<StatusEffect> var1, CallbackInfoReturnable<Boolean> var2) {
      MinecraftClient var3 = MinecraftClient.getInstance();
      if (this.equals(var3.player)) {
         Event_29 var4 = new Event_29(var1);
         MioAPI.field_4220.method_36(var4);
         if (var4.method_464()) {
            var2.cancel();
            var2.setReturnValue(false);
         }
      }
   }

   @Inject(
      method = {"getStatusEffect"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void getStatusEffectHook(RegistryEntry<StatusEffect> var1, CallbackInfoReturnable<StatusEffectInstance> var2) {
      MinecraftClient var3 = MinecraftClient.getInstance();
      if (this.equals(var3.player)) {
         Event_29 var4 = new Event_29(var1);
         MioAPI.field_4220.method_36(var4);
         if (var4.method_464()) {
            var2.cancel();
            var2.setReturnValue(null);
         }
      }
   }

   @Inject(
      method = {"jump"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void jumpHook(CallbackInfo var1) {
      if (!speed.method_404()) {
         if (speed.isToggled() && speed.field_2191.getValue() == StrafeType.STRAFE_STRICT && Class_0464.method_363()) {
            var1.cancel();
         }
      }
   }

   @ModifyConstant(
      method = {"applyMovementInput"},
      constant = {@Constant(
         doubleValue = 0.2
      )}
   )
   private double applyMovementInputHook(double var1) {
      return fastladder.isToggled() ? (double)fastladder.field_2205.getValue().floatValue() : var1;
   }

   @Inject(
      method = {"applyMovementInput"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/entity/LivingEntity;move(Lnet/minecraft/entity/MovementType;Lnet/minecraft/util/math/Vec3d;)V",
         shift = Shift.BEFORE
      )}
   )
   private void tickPre(Vec3d var1, float var2, CallbackInfoReturnable<Vec3d> var3) {
      if (Event_64.method_33(this)) {
         this.mio$moveEvent = new Event_64(this.getVelocity());
         MioAPI.field_4220.method_36(this.mio$moveEvent);
         Class_1334.method_2(this.getVelocity(), this.mio$moveEvent.method_380(), this.mio$moveEvent.method_395(), this.mio$moveEvent.method_396());
      }
   }

   @Inject(
      method = {"travel"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/entity/LivingEntity;move(Lnet/minecraft/entity/MovementType;Lnet/minecraft/util/math/Vec3d;)V",
         shift = Shift.BEFORE
      )}
   )
   private void tickPre(Vec3d var1, CallbackInfo var2) {
      if (Event_64.method_33(this)) {
         this.mio$moveEvent = new Event_64(this.getVelocity());
         MioAPI.field_4220.method_36(this.mio$moveEvent);
         Class_1334.method_2(this.getVelocity(), this.mio$moveEvent.method_380(), this.mio$moveEvent.method_395(), this.mio$moveEvent.method_396());
      }
   }
}
