package me.mioclient.mixin.ducks;

import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({LivingEntityRenderer.class})
public interface DuckLivingEntityRenderer<T extends LivingEntity, M extends EntityModel<T>> {
   @Invoker("setupTransforms")
   void mio$setupTransforms(T var1, MatrixStack var2, float var3, float var4, float var5, float var6);

   @Invoker("scale")
   void mio$scale(T var1, MatrixStack var2, float var3);

   @Invoker("getAnimationProgress")
   float mio$getAnimationProgress(T var1, float var2);
}
