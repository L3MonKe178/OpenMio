package me.mioclient.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.mioclient.Hub;
import me.mioclient.module.abstract_.AbstractModule_9;
import net.minecraft.item.TridentItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin({TridentItem.class})
public class MixinTridentItem {
   private static final AbstractModule_9 trident = Hub.field_2595.method_78(AbstractModule_9.class);

   public MixinTridentItem() {
      super();
   }

   @ModifyExpressionValue(
      method = {"onStoppedUsing"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/entity/player/PlayerEntity;isTouchingWaterOrRain()Z"
      )}
   )
   private boolean onStoppedUsingHook(boolean var1) {
      return var1 || trident.method_321();
   }

   @ModifyExpressionValue(
      method = {"use"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/entity/player/PlayerEntity;isTouchingWaterOrRain()Z"
      )}
   )
   private boolean useHook(boolean var1) {
      return var1 || trident.method_321();
   }

   @ModifyArgs(
      method = {"onStoppedUsing"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/entity/player/PlayerEntity;addVelocity(DDD)V"
      )
   )
   private void onStoppedUsingHook(Args var1) {
      var1.set(0, (Double)var1.get(0) * (double)trident.method_320());
      var1.set(1, (Double)var1.get(1) * (double)trident.method_320());
      var1.set(2, (Double)var1.get(2) * (double)trident.method_320());
   }

   @ModifyConstant(
      method = {"onStoppedUsing"},
      constant = {@Constant(
         intValue = 10
      )}
   )
   private int onStoppedUsing(int var1) {
      return trident.isToggled() ? 0 : var1;
   }
}
