package me.mioclient.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.mojang.authlib.GameProfile;
import me.mioclient.Hub;
import me.mioclient.api.Class_0415;
import me.mioclient.api.Class_1309;
import me.mioclient.enum_.Class_0304;
import me.mioclient.enum_.PreType;
import me.mioclient.event.Event_33;
import me.mioclient.event.Event_8;
import me.mioclient.internal.Class_0922;
import me.mioclient.internal.Class_1035;
import me.mioclient.module.exploit.ReachModule;
import me.mioclient.module.movement.SprintModule;
import me.mioclient.module.player.ScaffoldModule;
import me.mioclient.record.Class_0210;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({PlayerEntity.class})
public abstract class MixinPlayerEntity extends LivingEntity implements Class_0415 {
   private static final ScaffoldModule scaffold = Hub.field_2595.method_78(ScaffoldModule.class);
   private static final SprintModule sprint = Hub.field_2595.method_78(SprintModule.class);
   private static final ReachModule reach = Hub.field_2595.method_78(ReachModule.class);
   @Unique
   private long mio$lastEatingTime;
   @Unique
   private Class_0304 mio$role;
   @Unique
   private boolean mio$isNextToWall;

   protected MixinPlayerEntity(EntityType<? extends LivingEntity> var1, World var2) {
      super(var1, var2);
   }

   @Inject(
      method = {"<init>"},
      at = {@At("TAIL")}
   )
   private void initHook(World var1, BlockPos var2, float var3, GameProfile var4, CallbackInfo var5) {
      for (Class_0210 var7 : Hub.field_2603.getRegistry()) {
         if (var4.getName().equalsIgnoreCase(var7.method_228())) {
            this.mio$setRole(var7.method_242());
            break;
         }
      }
   }

   @Inject(
      method = {"tick"},
      at = {@At("HEAD")}
   )
   private void tick(CallbackInfo var1) {
      this.mio$isNextToWall = !this.getWorld().isSpaceEmpty(this.getBoundingBox().expand(1.0E-4, 0.0, 1.0E-4));
      if (!this.isUsingItem() || this.method_2(this.getActiveItem(), this) > 1.0F) {
         this.mio$lastEatingTime = System.currentTimeMillis();
      }
   }

   @Inject(
      method = {"clipAtLedge"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void clipAtLedgeHook(CallbackInfoReturnable<Boolean> var1) {
      if (Class_1309.field_4219.player != null) {
         Event_8 var2 = new Event_8();
         Class_1309.field_4220.method_36(var2);
         if (var2.method_464()) {
            var1.setReturnValue(true);
         }
      }
   }

   @ModifyExpressionValue(
      method = {"adjustMovementForSneaking"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/entity/player/PlayerEntity;getStepHeight()F"
      )}
   )
   private float clipAtLedgeHook2(float var1) {
      if (scaffold.isToggled() && scaffold.field_151.getValue()) {
         Item var2 = Class_1309.field_4219.player.getStackInHand(Hand.MAIN_HAND).getItem();
         if (!(var2 instanceof BlockItem)) {
            return var1;
         } else {
            VoxelShape var3 = ((BlockItem)var2).getBlock().getDefaultState().getCollisionShape(Class_1309.field_4219.world, BlockPos.ORIGIN);
            return var3 != null && !var3.isEmpty() ? (float)var3.getBoundingBox().getLengthY() : var1;
         }
      } else {
         return var1;
      }
   }

   @Inject(
      method = {"attack"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void attackHookPre(Entity var1, CallbackInfo var2) {
      Event_33 var3 = new Event_33(PreType.PRE, var1);
      Class_1309.field_4220.method_36(var3);
      if (var3.method_464()) {
         var2.cancel();
      }
   }

   @Inject(
      method = {"attack"},
      at = {@At("TAIL")}
   )
   private void attackHookPost(Entity var1, CallbackInfo var2) {
      Event_33 var3 = new Event_33(PreType.POST, var1);
      Class_1309.field_4220.method_36(var3);
   }

   @WrapWithCondition(
      method = {"attack"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/entity/player/PlayerEntity;setVelocity(Lnet/minecraft/util/math/Vec3d;)V"
      )}
   )
   private boolean attackHook(PlayerEntity var1, Vec3d var2) {
      return !sprint.isToggled() || !sprint.field_1029.getValue();
   }

   @WrapWithCondition(
      method = {"attack"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/entity/LivingEntity;takeKnockback(DDD)V"
      )}
   )
   private boolean attack(LivingEntity var1, double var2, double var4, double var6) {
      return !((Object)this instanceof Class_0922) || var1 != Class_1309.field_4219.player;
   }

   @Inject(
      method = {"getEntityInteractionRange"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void getEntityInteractionRangeHook(CallbackInfoReturnable<Double> var1) {
      if (reach.isToggled()) {
         var1.setReturnValue(reach.field_3336.getValue().doubleValue());
      }
   }

   @Inject(
      method = {"getBlockInteractionRange"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void getBlockInteractionRangeHook(CallbackInfoReturnable<Double> var1) {
      if (reach.isToggled()) {
         var1.setReturnValue(reach.field_3336.getValue().doubleValue());
      }
   }

   @Inject(
      method = {"shouldCancelInteraction"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void shouldCancelInteractionHook(CallbackInfoReturnable<Boolean> var1) {
      if (Class_1035.field_3196) {
         var1.setReturnValue(true);
      }
   }

   @Override
   public long mio$getLastEatingTime() {
      return this.mio$lastEatingTime;
   }

   @Override
   public Class_0304 mio$getRole() {
      return this.mio$role;
   }

   @Override
   public void mio$setRole(Class_0304 var1) {
      this.mio$role = var1;
   }

   @Override
   public boolean mio$isNextToWall() {
      return this.mio$isNextToWall;
   }
}
