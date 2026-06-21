package me.mioclient.mixin.ducks;

import net.minecraft.network.packet.c2s.play.CommandExecutionC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({CommandExecutionC2SPacket.class})
public interface DuckCommandExecutionC2SPacket {
   @Mutable
   @Accessor("command")
   void setCommand(String var1);
}
