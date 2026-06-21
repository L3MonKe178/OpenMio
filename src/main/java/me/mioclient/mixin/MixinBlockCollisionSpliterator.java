package me.mioclient.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import me.mioclient.api.MioAPI;
import me.mioclient.event.Event_35;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockCollisionSpliterator;
import net.minecraft.world.CollisionView;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({BlockCollisionSpliterator.class})
public class MixinBlockCollisionSpliterator {
   @Shadow
   @Final
   private CollisionView world;
   @Shadow
   @Final
   private Mutable pos;

   public MixinBlockCollisionSpliterator() {
      super();
   }

   @ModifyExpressionValue(
      method = {"computeNext"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/block/BlockState;getCollisionShape(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/ShapeContext;)Lnet/minecraft/util/shape/VoxelShape;"
      )}
   )
   private VoxelShape computeNextHook(VoxelShape var1, @Local BlockState var2) {
      if (this.world != MinecraftClient.getInstance().world) {
         return var1;
      } else {
         Event_35 var3 = MioAPI.field_4220.method_36(Event_35.method_2(var1, this.pos, var2));
         return var3.method_464() ? VoxelShapes.empty() : var3.method_957();
      }
   }
}
