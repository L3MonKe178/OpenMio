package me.mioclient.mixin;

import com.mojang.authlib.GameProfile;
import me.mioclient.api.Class_0977;
import me.mioclient.internal.Class_0922;
import net.minecraft.client.network.OtherClientPlayerEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({OtherClientPlayerEntity.class})
public abstract class MixinOtherClientPlayerEntity extends PlayerEntity implements Class_0977 {
   public MixinOtherClientPlayerEntity(World var1, BlockPos var2, float var3, GameProfile var4) {
      super(var1, var2, var3, var4);
   }

   @Inject(
      method = {"damage"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void damageHook(DamageSource var1, float var2, CallbackInfoReturnable<Boolean> var3) {
      if ((Object)this instanceof Class_0922) {
         var3.setReturnValue(this.damageSuper(var1, var2));
      }
   }

   @Override
   public boolean damageSuper(DamageSource var1, float var2) {
      return super.damage(var1, var2);
   }
}
