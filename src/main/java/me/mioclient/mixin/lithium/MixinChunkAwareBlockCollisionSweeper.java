package me.mioclient.mixin.lithium;

import me.mioclient.api.Class_1309;
import me.mioclient.event.Event_35;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Pseudo
@Mixin(
   targets = {"me.jellysquid.mods.lithium.common.entity.movement.ChunkAwareBlockCollisionSweeper"},
   remap = false
)
public class MixinChunkAwareBlockCollisionSweeper {
   public MixinChunkAwareBlockCollisionSweeper() {
      super();
   }

   @Redirect(
      method = {"computeNext"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/block/BlockState;getCollisionShape(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/ShapeContext;)Lnet/minecraft/util/shape/VoxelShape;"
      )
   )
   private VoxelShape onComputeNextCollisionBox(BlockState var1, BlockView var2, BlockPos var3, ShapeContext var4) {
      VoxelShape var5 = var1.getCollisionShape(var2, var3, var4);
      if (var2 != MinecraftClient.getInstance().world) {
         return var5;
      } else {
         Event_35 var6 = Class_1309.field_4220.method_36(Event_35.method_2(var5, var3, var1));
         return var6.method_464() ? VoxelShapes.empty() : var6.method_957();
      }
   }
}
