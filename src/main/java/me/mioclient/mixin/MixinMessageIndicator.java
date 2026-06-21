package me.mioclient.mixin;

import java.awt.Color;
import me.mioclient.internal.Class_1081;
import me.mioclient.internal.ChatUtil;
import net.minecraft.client.gui.hud.MessageIndicator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({MessageIndicator.class})
public class MixinMessageIndicator {
   public MixinMessageIndicator() {
      super();
   }

   @Inject(
      method = {"indicatorColor"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void indicatorColorHook(CallbackInfoReturnable<Integer> var1) {
      if ((MessageIndicator)(Object)this == ChatUtil.field_3910) {
         Color var2 = Class_1081.method_959();
         var1.setReturnValue(Class_1081.method_2(var2, var2.darker(), 3000.0, 0.0).hashCode());
         var1.cancel();
      }
   }
}
