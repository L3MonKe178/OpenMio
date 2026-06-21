package me.mioclient.mixin.ducks;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.VillagerResemblingModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({VillagerResemblingModel.class})
public interface DuckVillagerResemblingModel {
   @Accessor("hatRim")
   ModelPart mio$getHatRim();
}
