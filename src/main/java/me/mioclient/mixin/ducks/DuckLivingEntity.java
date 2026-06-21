package me.mioclient.mixin.ducks;

import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({LivingEntity.class})
public interface DuckLivingEntity {
   @Accessor("itemUseTimeLeft")
   void setItemUseTimeLeft(int var1);

   @Accessor("lastAttackedTicks")
   int mio$getLastAttackedTicks();

   @Accessor("lastAttackedTicks")
   void mio$setLastAttackedTicks(int var1);

   @Accessor("leaningPitch")
   void mio$setLeaningPitch(float var1);

   @Accessor("lastLeaningPitch")
   void mio$setLastLeaningPitch(float var1);

   @Accessor("leaningPitch")
   float mio$getLeaningPitch();

   @Accessor("leaningPitch")
   float mio$getLastLeaningPitch();

   @Accessor("serverX")
   double mio$getServerX();

   @Accessor("serverY")
   double mio$getServerY();

   @Accessor("serverZ")
   double mio$getServerZ();

   @Invoker("getHandSwingDuration")
   int getSwingDuration();
}
