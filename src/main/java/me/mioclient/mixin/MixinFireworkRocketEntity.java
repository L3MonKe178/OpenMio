package me.mioclient.mixin;

import me.mioclient.Hub;
import me.mioclient.internal.Class_0930;
import me.mioclient.module.movement.ElytraFlyModule;
import me.mioclient.module.movement.FireworksModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({FireworkRocketEntity.class})
public abstract class MixinFireworkRocketEntity extends Entity {
   private static FireworksModule fireworks = Hub.field_2595.method_78(FireworksModule.class);
   @Shadow
   @Nullable
   private LivingEntity field_7616;

   public MixinFireworkRocketEntity(EntityType<?> var1, World var2) {
      super(var1, var2);
   }

   @Inject(
      method = {"tick"},
      at = {@At("HEAD")}
   )
   private void tickHook(CallbackInfo var1) {
      ElytraFlyModule.field_885 = true;
   }

   @Inject(
      method = {"tick"},
      at = {@At("TAIL")}
   )
   private void tickHook2(CallbackInfo var1) {
      ElytraFlyModule.field_885 = false;
   }

   @Inject(
      method = {"tick"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/entity/LivingEntity;setVelocity(Lnet/minecraft/util/math/Vec3d;)V",
         shift = Shift.AFTER
      )}
   )
   private void tick(CallbackInfo var1) {
      if (fireworks.field_2696) {
         this.age = 1;
         fireworks.field_2696 = false;
      }

      float var2 = fireworks.method_100(true);
      if (this.field_7616 == MinecraftClient.getInstance().player) {
         if (var2 != 0.0F) {
            float var3 = Class_0930.method_7(this.field_7616.getYaw());
            if ((double)var2 > 0.5) {
               var2 = MathHelper.clamp(0.2F + 0.3F * (float)this.age, 0.0F, var2);
            }

            if (fireworks.method_785() > 0) {
               this.field_7616.addVelocity(this.field_7616.getRotationVec(1.0F).multiply((double)var2));
            } else {
               this.field_7616.addVelocity((double)(MathHelper.sin(var3) * -var2), 0.0, (double)(MathHelper.cos(var3) * var2));
            }
         }
      }
   }
}
