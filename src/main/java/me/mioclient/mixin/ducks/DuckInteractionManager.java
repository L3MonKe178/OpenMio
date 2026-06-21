package me.mioclient.mixin.ducks;

import net.minecraft.client.network.ClientPlayerInteractionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({ClientPlayerInteractionManager.class})
public interface DuckInteractionManager {
   @Invoker("syncSelectedSlot")
   void sync();
}
