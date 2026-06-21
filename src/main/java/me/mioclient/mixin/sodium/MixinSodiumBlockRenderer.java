package me.mioclient.mixin.sodium;

import me.jellysquid.mods.sodium.client.render.chunk.compile.ChunkBuildBuffers;
import me.jellysquid.mods.sodium.client.render.chunk.compile.pipeline.BlockRenderContext;
import me.mioclient.Hub;
import me.mioclient.module.render.XrayModule;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(
   targets = {"me.jellysquid.mods.sodium.client.render.chunk.compile.pipeline.BlockRenderer"},
   remap = false
)
public class MixinSodiumBlockRenderer {
   private static XrayModule xray = Hub.field_2595.method_78(XrayModule.class);

   public MixinSodiumBlockRenderer() {
      super();
   }

   @Inject(
      method = {"renderModel"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onRenderModel(BlockRenderContext var1, ChunkBuildBuffers var2, CallbackInfo var3) {
      if (MinecraftClient.getInstance().player != null) {
         if (xray.isToggled()) {
            try {
               BlockPos pos = (BlockPos)(Object)BlockRenderContext.class.getMethod("pos").invoke(var1);
               BlockState state = (BlockState)(Object)BlockRenderContext.class.getMethod("state").invoke(var1);
               if (!xray.method_2(pos, state.getBlock())) {
                  var3.cancel();
               }
            } catch (Exception e) {
            }
         }
      }
   }
}
