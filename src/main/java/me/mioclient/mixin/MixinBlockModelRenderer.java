package me.mioclient.mixin;

import me.mioclient.module.render.XrayModule;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.block.BlockModelRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockRenderView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({BlockModelRenderer.class})
public class MixinBlockModelRenderer {
   public MixinBlockModelRenderer() {
      super();
   }

   @Inject(
      method = {"renderSmooth", "renderFlat"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void renderSmoothHook(
      BlockRenderView var1,
      BakedModel var2,
      BlockState var3,
      BlockPos var4,
      MatrixStack var5,
      VertexConsumer var6,
      boolean var7,
      Random var8,
      long var9,
      int var11,
      CallbackInfo var12
   ) {
      if (XrayModule.method_844().isToggled() && !XrayModule.method_844().method_2(var4, var3.getBlock())) {
         var12.cancel();
      }
   }
}
