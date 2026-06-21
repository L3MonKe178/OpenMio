package me.mioclient.mixin;

import me.mioclient.api.Class_0558;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({StatusEffectInstance.class})
public class MixinStatusEffectInstance implements Class_0558 {
   @Shadow
   private int field_5895;
   @Unique
   private int mio$initialDuration;

   public MixinStatusEffectInstance() {
      super();
   }

   @Inject(
      method = {"<init>(Lnet/minecraft/registry/entry/RegistryEntry;IIZZZLnet/minecraft/entity/effect/StatusEffectInstance;)V"},
      at = {@At("TAIL")}
   )
   private void initHook(
      RegistryEntry<StatusEffect> var1, int var2, int var3, boolean var4, boolean var5, boolean var6, StatusEffectInstance var7, CallbackInfo var8
   ) {
      this.mio$initialDuration = var2;
   }

   @Inject(
      method = {"copyFrom"},
      at = {@At("TAIL")}
   )
   private void copyFrom(StatusEffectInstance var1, CallbackInfo var2) {
      this.mio$initialDuration = var1.getDuration();
   }

   @Inject(
      method = {"upgrade"},
      at = {@At(
         value = "FIELD",
         target = "Lnet/minecraft/entity/effect/StatusEffectInstance;duration:I",
         opcode = 181,
         shift = Shift.AFTER
      )}
   )
   private void updateDuration(CallbackInfoReturnable<Integer> var1) {
      this.mio$initialDuration = this.field_5895;
   }

   @Override
   public float mio$getDurationRation() {
      return (float)this.field_5895 / (float)this.mio$initialDuration;
   }
}
