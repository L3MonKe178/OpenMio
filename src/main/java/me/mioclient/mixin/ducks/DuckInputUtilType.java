package me.mioclient.mixin.ducks;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.client.util.InputUtil.Key;
import net.minecraft.client.util.InputUtil.Type;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({Type.class})
public interface DuckInputUtilType {
   @Accessor("map")
   Int2ObjectMap<Key> getKeyMap();
}
