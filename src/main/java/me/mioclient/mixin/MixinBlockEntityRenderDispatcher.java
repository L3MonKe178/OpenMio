package me.mioclient.mixin;

import me.mioclient.Hub;
import me.mioclient.module.render.ESPModule;
import me.mioclient.module.render.NoRenderModule;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({BlockEntityRenderDispatcher.class})
public class MixinBlockEntityRenderDispatcher {
   private static final ESPModule esp = Hub.field_2595.method_78(ESPModule.class);
   private static NoRenderModule norender = Hub.field_2595.method_78(NoRenderModule.class);

   public MixinBlockEntityRenderDispatcher() {
      super();
   }

   @Inject(
      method = {"render(Lnet/minecraft/block/entity/BlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private <E extends BlockEntity> void renderHook(E var1, float var2, MatrixStack var3, VertexConsumerProvider var4, CallbackInfo var5) {
      if (!norender.field_758.getValue() || !esp.method_1065()) {
         if (norender.isToggled()
            && norender.field_756.getValue()
            && var1.getPos().getSquaredDistance(MinecraftClient.getInstance().gameRenderer.getCamera().getBlockPos())
               > (double)(norender.field_757.getValue() * norender.field_757.getValue())) {
            var5.cancel();
         }
      }
   }
}
