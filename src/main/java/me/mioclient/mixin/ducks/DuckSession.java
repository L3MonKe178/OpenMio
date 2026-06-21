package me.mioclient.mixin.ducks;

import net.minecraft.client.session.Session;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({Session.class})
public interface DuckSession {
   @Mutable
   @Accessor("username")
   void setUsername(String var1);
}
