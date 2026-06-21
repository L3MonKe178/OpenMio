package me.mioclient.mixin.ducks;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({MinecraftClient.class})
public interface DuckMinecraftClient {
   @Invoker("doAttack")
   boolean attack();

   @Invoker("doItemUse")
   void interact();

   @Accessor("itemUseCooldown")
   void setItemUseCooldown(int var1);

   @Accessor("itemUseCooldown")
   int getItemUseCooldown();

   @Accessor("disconnecting")
   boolean mio$isDisconnecting();
}
