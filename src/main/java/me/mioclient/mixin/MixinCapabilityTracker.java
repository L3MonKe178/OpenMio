package me.mioclient.mixin;

import me.mioclient.api.Class_0637;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(
   targets = {"com.mojang.blaze3d.platform.GlStateManager$CapabilityTracker"}
)
public abstract class MixinCapabilityTracker implements Class_0637 {
   @Shadow
   private boolean field_5051;

   public MixinCapabilityTracker() {
      super();
   }

   @Shadow
   public abstract void method_4470(boolean var1);

   @Override
   public boolean getState() {
      return this.field_5051;
   }

   @Override
   public void set(boolean var1) {
      this.method_4470(var1);
   }
}
