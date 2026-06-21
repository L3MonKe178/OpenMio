package me.mioclient.mixin.ducks;

import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({EntityStatusS2CPacket.class})
public interface DuckEntityStatusS2CPacket {
   @Accessor("entityId")
   int getId();
}
