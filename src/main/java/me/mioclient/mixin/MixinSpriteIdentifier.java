package me.mioclient.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.function.Function;
import me.mioclient.Hub;
import me.mioclient.module.render.NoRenderModule;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({SpriteIdentifier.class})
public class MixinSpriteIdentifier {
   @Shadow
   @Final
   private Identifier field_21769;
   private static final NoRenderModule norender = Hub.field_2595.method_78(NoRenderModule.class);

   public MixinSpriteIdentifier() {
      super();
   }

   @Inject(
      method = {"getRenderLayer"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void getRenderLayerHook(Function<Identifier, RenderLayer> var1, CallbackInfoReturnable<RenderLayer> var2) {
      if (norender.method_282() != 1.0F && RenderSystem.getShaderColor()[3] != 1.0F) {
         var2.setReturnValue(RenderLayer.getEntityTranslucent(this.field_21769));
         var2.cancel();
      }
   }
}
