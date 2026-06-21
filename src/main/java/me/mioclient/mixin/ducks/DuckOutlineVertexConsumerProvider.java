package me.mioclient.mixin.ducks;

import net.minecraft.client.render.OutlineVertexConsumerProvider;
import net.minecraft.client.render.VertexConsumerProvider.Immediate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({OutlineVertexConsumerProvider.class})
public interface DuckOutlineVertexConsumerProvider {
   @Accessor("plainDrawer")
   Immediate getParent();
}
