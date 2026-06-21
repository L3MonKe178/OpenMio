package me.mioclient.mixin.ducks;

import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({ClientPlayerEntity.class})
public interface DuckClientPlayerEntity {
   @Accessor("lastSprinting")
   boolean lastSprinting();

   @Accessor("lastSprinting")
   void lastSprinting(boolean var1);

   @Accessor("lastSneaking")
   boolean lastSneaking();

   @Accessor("lastSneaking")
   void lastSneaking(boolean var1);

   @Accessor("lastOnGround")
   boolean lastOnGround();

   @Accessor("lastOnGround")
   void lastOnGround(boolean var1);

   @Accessor("lastX")
   double lastX();

   @Accessor("lastX")
   void lastX(double var1);

   @Accessor("lastBaseY")
   double lastBaseY();

   @Accessor("lastBaseY")
   void lastBaseY(double var1);

   @Accessor("lastZ")
   double lastZ();

   @Accessor("lastZ")
   void lastZ(double var1);

   @Accessor("lastYaw")
   float lastYaw();

   @Accessor("lastYaw")
   void lastYaw(float var1);

   @Accessor("lastPitch")
   float lastPitch();

   @Accessor("lastYaw")
   void lastPitch(float var1);

   @Accessor("autoJumpEnabled")
   boolean autoJumpEnabled();

   @Accessor("autoJumpEnabled")
   void autoJumpEnabled(boolean var1);

   @Accessor("ticksSinceLastPositionPacketSent")
   int ticksSinceLastPositionPacketSent();

   @Accessor("ticksSinceLastPositionPacketSent")
   void ticksSinceLastPositionPacketSent(int var1);
}
