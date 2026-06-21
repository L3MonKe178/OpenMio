package me.mioclient.internal;

import com.mojang.authlib.GameProfile;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import me.mioclient.mixin.ducks.DuckLivingEntity;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.OtherClientPlayerEntity;
import net.minecraft.client.util.SkinTextures;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerModelPart;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;

public class Class_0878 extends OtherClientPlayerEntity {
   public final Set<PlayerModelPart> field_2784 = new HashSet<>();
   public SkinTextures field_2785;
   public boolean field_2005;
   public Vec3d field_2786;

   public Class_0878(ClientWorld var1) {
      super(var1, new GameProfile(UUID.randomUUID(), ""));
   }

   public void tickMovement() {
   }

   public void method_78(PlayerEntity var1) {
      this.field_2786 = new Vec3d(var1.getX(), var1.getY(), var1.getZ());
      super.copyFrom(var1);
      this.copyPositionAndRotation(var1);
      this.prevYaw = var1.getYaw();
      this.setYaw(var1.getYaw());
      this.prevPitch = var1.getPitch();
      this.setPitch(var1.getPitch());
      this.bodyYaw = var1.bodyYaw;
      this.prevBodyYaw = var1.bodyYaw;
      this.headYaw = var1.headYaw;
      this.prevHeadYaw = var1.headYaw;
      this.limbAnimator.speed = var1.limbAnimator.speed;
      this.limbAnimator.prevSpeed = var1.limbAnimator.speed;
      this.limbAnimator.pos = var1.limbAnimator.pos;
      this.deathTime = 0;
      this.lastVelocity = new Vec3d(0.0, 0.0, 0.0);
      this.setVelocity(0.0, 0.0, 0.0);
      this.setPose(var1.getPose());
      this.setSneaking(var1.isSneaking());
      if (var1 instanceof AbstractClientPlayerEntity var2) {
         this.field_2785 = var2.getSkinTextures();

         for (PlayerModelPart var6 : PlayerModelPart.values()) {
            if (var6 != PlayerModelPart.CAPE && var2.isPartVisible(var6)) {
               this.field_2784.add(var6);
            }
         }
      }

      for (int var7 = 0; var7 < this.getInventory().size(); var7++) {
         this.getInventory().setStack(var7, var1.getInventory().getStack(var7).copy());
      }

      this.setStackInHand(Hand.MAIN_HAND, var1.getMainHandStack().copy());
      this.setStackInHand(Hand.OFF_HAND, var1.getOffHandStack().copy());
      this.getInventory().selectedSlot = var1.getInventory().selectedSlot;
      ((DuckLivingEntity)this).mio$setLeaningPitch(((DuckLivingEntity)var1).mio$getLeaningPitch());
      ((DuckLivingEntity)this).mio$setLastLeaningPitch(((DuckLivingEntity)var1).mio$getLastLeaningPitch());
   }

   public void setPosition(double x, double y, double z) {
      super.setPosition(x, y, z);
      this.lastRenderX = x;
      this.lastRenderY = y;
      this.lastRenderZ = z;
   }

   public Vec3d method_805() {
      return this.field_2786;
   }

   public SkinTextures getSkinTextures() {
      return this.field_2785 != null ? this.field_2785 : super.getSkinTextures();
   }

   public boolean isPartVisible(PlayerModelPart modelPart) {
      return this.field_2784.contains(modelPart);
   }

   public boolean method_806() {
      return this.field_2005;
   }

   public void method_14(boolean var1) {
      this.field_2005 = var1;
   }
}
