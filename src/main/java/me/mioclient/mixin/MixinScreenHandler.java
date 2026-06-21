package me.mioclient.mixin;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import me.mioclient.api.MioAPI;
import me.mioclient.event.Event_62;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({ScreenHandler.class})
public class MixinScreenHandler {
   public MixinScreenHandler() {
      super();
   }

   @WrapWithCondition(
      method = {"insertItem"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/screen/slot/Slot;markDirty()V"
      )}
   )
   private boolean insertItemHook(Slot var1) {
      MioAPI.field_4220.method_36(new Event_62(var1));
      return true;
   }
}
