package me.mioclient.mixin.ducks;

import com.mojang.datafixers.util.Pair;
import java.util.Map;
import net.minecraft.client.render.entity.BoatEntityRenderer;
import net.minecraft.client.render.entity.model.CompositeEntityModel;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.BoatEntity.Type;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({BoatEntityRenderer.class})
public interface DuckBoatEntityRenderer {
   @Accessor("texturesAndModels")
   Map<Type, Pair<Identifier, CompositeEntityModel<BoatEntity>>> mio$getTexturesAndModels();
}
