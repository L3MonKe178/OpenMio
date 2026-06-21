package me.mioclient.mixin;

import me.mioclient.Hub;
import me.mioclient.module.movement.NoSlowModule;
import net.minecraft.block.HoneyBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({HoneyBlock.class})
public class MixinHoneyBlock {
   private static NoSlowModule noslow = Hub.field_2595.method_78(NoSlowModule.class);

   public MixinHoneyBlock() {
      super();
   }

   @Shadow
   private boolean method_23356(BlockPos var1, Entity var2) {
      return false;
   }

   @Redirect(
      method = {"onEntityCollision"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/block/HoneyBlock;isSliding(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/Entity;)Z"
      )
   )
   private boolean onEntityCollisionHook(HoneyBlock var1, BlockPos var2, Entity var3) {
      return noslow.isToggled() && noslow.field_1699.getValue() ? false : this.method_23356(var2, var3);
   }
}
