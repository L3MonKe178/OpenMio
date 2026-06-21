package me.mioclient.mixin.ducks;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({Entity.class})
public interface DuckEntity {
   @Invoker("getVelocityAffectingPos")
   BlockPos mio$getVelocityAffectingPos();
}
