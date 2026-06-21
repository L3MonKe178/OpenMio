package me.mioclient.mixin;

import me.mioclient.Hub;
import me.mioclient.module.movement.EntityControlModule;
import net.minecraft.entity.passive.AbstractHorseEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({AbstractHorseEntity.class})
public class MixinAbstractHorseEntity {
   private static EntityControlModule entitycontrol = Hub.field_2595.method_78(EntityControlModule.class);

   public MixinAbstractHorseEntity() {
      super();
   }

   @Inject(
      method = {"isSaddled"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void isSaddledHook(CallbackInfoReturnable<Boolean> var1) {
      if (entitycontrol.isToggled()) {
         var1.setReturnValue(true);
      }
   }
}
