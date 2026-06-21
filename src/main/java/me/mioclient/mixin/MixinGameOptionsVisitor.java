package me.mioclient.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_0574;
import me.mioclient.module.render.AmbienceModule;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(
   targets = {"net.minecraft.client.option.GameOptions$3"}
)
public class MixinGameOptionsVisitor {
   private static final AmbienceModule ambience = Hub.field_2595.method_78(AmbienceModule.class);

   public MixinGameOptionsVisitor() {
      super();
   }

   @WrapWithCondition(
      method = {"method_42572"},
      at = {@At(
         value = "INVOKE",
         target = "Lorg/slf4j/Logger;error(Ljava/lang/String;)V"
      )}
   )
   private static boolean acceptHook(Logger var0, String var1) {
      return ambience.field_207.getValue() != Class_0574.GAMMA;
   }
}
