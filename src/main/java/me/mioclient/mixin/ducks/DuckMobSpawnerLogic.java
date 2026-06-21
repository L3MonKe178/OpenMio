package me.mioclient.mixin.ducks;

import net.minecraft.block.spawner.MobSpawnerLogic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({MobSpawnerLogic.class})
public interface DuckMobSpawnerLogic {
   @Accessor("spawnDelay")
   int getSpawnDelay();
}
