package me.mioclient.mixin.ducks;

import net.minecraft.block.AbstractBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({AbstractBlock.class})
public interface DuckAbstractBlock {
   @Accessor("collidable")
   boolean isCollidable();
}
