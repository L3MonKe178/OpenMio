package me.mioclient.mixin.ducks;

import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({WorldTimeUpdateS2CPacket.class})
public interface DuckWorldTimeUpdateS2CPacket {
   @Mutable
   @Accessor("time")
   void setTime(long var1);

   @Mutable
   @Accessor("timeOfDay")
   void setTimeOfDay(long var1);
}
