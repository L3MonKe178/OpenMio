package me.mioclient.mixin.ducks;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.AnimalModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({AnimalModel.class})
public interface DuckAnimalModel {
   @Invoker("getHeadParts")
   Iterable<ModelPart> mio$getHeadParts();

   @Invoker("getBodyParts")
   Iterable<ModelPart> mio$getBodyParts();

   @Accessor("headScaled")
   boolean mio$isHeadScaled();

   @Accessor("invertedChildHeadScale")
   float mio$getInvertedChildHeadScale();

   @Accessor("invertedChildBodyScale")
   float mio$getInvertedChildBodyScale();

   @Accessor("childBodyYOffset")
   float mio$getChildBodyYOffset();

   @Accessor("childHeadZOffset")
   float mio$getChildHeadZOffset();

   @Accessor("childHeadYOffset")
   float mio$getChildHeadYOffset();
}
