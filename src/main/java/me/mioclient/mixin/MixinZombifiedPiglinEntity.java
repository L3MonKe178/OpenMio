package me.mioclient.mixin;

import me.mioclient.api.Class_0875;
import me.mioclient.internal.Timer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ZombifiedPiglinEntity.class})
public abstract class MixinZombifiedPiglinEntity extends MobEntity implements Class_0875 {
   @Unique
   private Timer stopwatch;

   protected MixinZombifiedPiglinEntity(EntityType<? extends MobEntity> var1, World var2) {
      super(var1, var2);
   }

   @Inject(
      method = {"<init>"},
      at = {@At("TAIL")}
   )
   private void initHook(EntityType<?> var1, World var2, CallbackInfo var3) {
      this.stopwatch = new Timer();
   }

   @Override
   public boolean mio$isAttacking() {
      if (this.isAttacking()) {
         this.stopwatch.reset();
      }

      return !this.stopwatch.method_9(1000L);
   }
}
