package me.mioclient.mixin.ducks;

import java.util.Map;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({TextureManager.class})
public interface DuckTextureManager {
   @Accessor("textures")
   Map<Identifier, AbstractTexture> getAllTextures();
}
