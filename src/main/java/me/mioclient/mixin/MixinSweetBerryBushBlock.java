package me.mioclient.mixin;

import me.mioclient.Hub;
import me.mioclient.module.movement.NoSlowModule;
import net.minecraft.block.BlockState;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({SweetBerryBushBlock.class})
public class MixinSweetBerryBushBlock {
   private static NoSlowModule noslow = Hub.field_2595.method_78(NoSlowModule.class);

   public MixinSweetBerryBushBlock() {
      super();
   }

   @Redirect(
      method = {"onEntityCollision"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/entity/Entity;slowMovement(Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/Vec3d;)V"
      )
   )
   private void onEntityCollisionHook(Entity var1, BlockState var2, Vec3d var3) {
      if (!noslow.isToggled() || !noslow.field_1700.getValue()) {
         var1.slowMovement(var2, var3);
      }
   }
}
