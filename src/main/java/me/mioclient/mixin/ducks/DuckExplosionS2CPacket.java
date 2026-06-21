package me.mioclient.mixin.ducks;

import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({ExplosionS2CPacket.class})
public interface DuckExplosionS2CPacket {
   @Mutable
   @Accessor("playerVelocityX")
   void setX(float var1);

   @Mutable
   @Accessor("playerVelocityY")
   void setY(float var1);

   @Mutable
   @Accessor("playerVelocityZ")
   void setZ(float var1);
}
