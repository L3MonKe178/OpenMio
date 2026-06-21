package me.mioclient.mixin;

import me.mioclient.api.Class_0637;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(
   targets = {"com.mojang.blaze3d.platform.GlStateManager$CapabilityTracker"}
)
public abstract class MixinCapabilityTracker implements Class_0637 {
   @Shadow
   private boolean state;

   public MixinCapabilityTracker() {
      super();
   }

   @Shadow
   public abstract void setState(boolean var1);

   @Override
   public boolean getState() {
      return this.state;
   }

   @Override
   public void set(boolean var1) {
      this.setState(var1);
   }
}
