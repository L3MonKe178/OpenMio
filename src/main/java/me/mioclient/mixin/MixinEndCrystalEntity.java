package me.mioclient.mixin;

import me.mioclient.api.Class_0468;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({EndCrystalEntity.class})
public class MixinEndCrystalEntity implements Class_0468 {
   @Unique
   private long spawnTime;
   @Unique
   private boolean mioAttacked;

   public MixinEndCrystalEntity() {
      super();
   }

   @Inject(
      method = {"<init>(Lnet/minecraft/entity/EntityType;Lnet/minecraft/world/World;)V"},
      at = {@At("RETURN")}
   )
   private void init(EntityType<?> var1, World var2, CallbackInfo var3) {
      this.spawnTime = System.currentTimeMillis();
      this.mioAttacked = false;
   }

   @Override
   public long getSpawnTime() {
      return this.spawnTime;
   }

   @Override
   public boolean isMioAttacked() {
      return this.mioAttacked;
   }

   @Override
   public void setMioAttacked(boolean var1) {
      this.mioAttacked = var1;
   }
}
