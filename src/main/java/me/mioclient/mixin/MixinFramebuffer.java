package me.mioclient.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import me.mioclient.internal.Class_1355;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({Framebuffer.class})
public class MixinFramebuffer {
   public MixinFramebuffer() {
      super();
   }

   @Inject(
      method = {"bind"},
      at = {@At("TAIL")},
      cancellable = true
   )
   private void bind(boolean var1, CallbackInfo var2) {
      if (Class_1355.field_4422 && MinecraftClient.getInstance().getFramebuffer().equals(this)) {
         GlStateManager._glBindFramebuffer(36160, 0);
         var2.cancel();
      }
   }
}
