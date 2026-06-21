package me.mioclient.mixin.ducks;

import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({WorldRenderer.class})
public interface DuckWorldRenderer {
   @Accessor
   void setEntityOutlinesFramebuffer(Framebuffer var1);

   @Accessor("frustum")
   Frustum getFrustum();
}
