package me.mioclient.internal;

import com.mojang.authlib.GameProfile;
import me.mioclient.api.MioAPI;
import me.mioclient.event.Event_4;
import net.minecraft.client.network.OtherClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.explosion.Explosion;

public class Class_0922 extends OtherClientPlayerEntity {
   public Class_0922(PlayerEntity var1, ClientWorld var2, GameProfile var3) {
      super(var2, var3);
      this.copyPositionAndRotation(var1);
      this.prevYaw = this.getYaw();
      this.prevPitch = this.getPitch();
      this.headYaw = var1.headYaw;
      this.prevHeadYaw = this.headYaw;
      this.bodyYaw = var1.bodyYaw;
      this.prevBodyYaw = this.bodyYaw;
      this.setPose(var1.getPose());
      this.capeX = this.getX();
      this.capeY = this.getY();
      this.capeZ = this.getZ();
      this.setHealth(Float.intBitsToFloat(1101004800));
      this.getInventory().clone(var1.getInventory());
   }

   public void setHealth(float health) {
      super.setHealth(health);
      if (this.getHealth() <= 0.0F) {
         super.setHealth(Float.intBitsToFloat(1091567616));
         this.clearStatusEffects();
         this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 900, 1));
         MioAPI.field_4219.particleManager.addEmitter(this, ParticleTypes.TOTEM_OF_UNDYING, 30);
         MioAPI.field_4219
            .world
            .playSound(
               MioAPI.field_4219.player,
               this.getBlockPos(),
               SoundEvents.ITEM_TOTEM_USE,
               SoundCategory.PLAYERS,
               Float.intBitsToFloat(1065353216),
               Float.intBitsToFloat(1065353216)
            );
         MioAPI.field_4220.method_36(new Event_4(new EntityStatusS2CPacket(this, (byte)35)));
      }
   }

   public void tick() {
      this.age++;
      if (this.age % 80 == 0) {
         this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 400, 1));
         this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 6000, 0));
         this.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 6000, 0));
         this.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 3));
         this.setAbsorptionAmount(Float.intBitsToFloat(1098907648));
      }

      if (!this.getOffHandStack().isOf(Items.TOTEM_OF_UNDYING)) {
         this.getInventory().setStack(40, new ItemStack(Items.TOTEM_OF_UNDYING, 1));
      }

      super.tick();
   }

   public boolean isImmuneToExplosion(Explosion explosion) {
      return false;
   }

   public boolean isAttackable() {
      return true;
   }

   public boolean isFireImmune() {
      return true;
   }

   public boolean isPushable() {
      return false;
   }

   public boolean isAlive() {
      return true;
   }

   public void updatePostDeath() {
      this.deathTime = 0;
   }

   public void pushAway(Entity entity) {
   }
}
