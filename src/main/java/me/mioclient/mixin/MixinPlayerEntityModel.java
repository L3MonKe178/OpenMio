package me.mioclient.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.mioclient.Hub;
import me.mioclient.module.render.NoRenderModule;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({PlayerEntityModel.class})
public class MixinPlayerEntityModel {
   private static final NoRenderModule norender = Hub.field_2595.method_78(NoRenderModule.class);

   public MixinPlayerEntityModel() {
      super();
   }

   @ModifyExpressionValue(
      method = {"setAngles(Lnet/minecraft/entity/LivingEntity;FFFFF)V"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/item/ItemStack;isEmpty()Z"
      )}
   )
   private boolean setAnglesHook(boolean var1) {
      return norender.method_283() == 0.0F ? true : var1;
   }
}
