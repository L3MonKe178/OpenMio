package me.mioclient.mixin.ducks;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({FireworkRocketEntity.class})
public interface DuckFireworkEntity {
   @Accessor("life")
   void setLife(int var1);

   @Accessor("life")
   int getLife();

   @Accessor("lifeTime")
   int getLifeTime();

   @Accessor("shooter")
   LivingEntity mio$getShooter();
}
