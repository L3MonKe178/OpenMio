package me.mioclient.mixin;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import me.mioclient.api.MioAPI;
import me.mioclient.event.Event_18;
import me.mioclient.event.Event_57;
import me.mioclient.event.Event_65;
import net.minecraft.client.Mouse;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({Mouse.class})
public class MixinMouse {
   public MixinMouse() {
      super();
   }

   @Inject(
      method = {"onMouseButton"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/option/KeyBinding;onKeyPressed(Lnet/minecraft/client/util/InputUtil$Key;)V"
      )}
   )
   private void onMouseButtonHook(long var1, int var3, int var4, int var5, CallbackInfo var6) {
      Event_18 var7 = new Event_18(var3, true);
      MioAPI.field_4220.method_36(var7);
   }

   @WrapWithCondition(
      method = {"updateMouse"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/network/ClientPlayerEntity;changeLookDirection(DD)V"
      )}
   )
   private boolean updateMouseHook(ClientPlayerEntity var1, double var2, double var4) {
      Event_57 var6 = new Event_57(var2, var4);
      MioAPI.field_4220.method_36(var6);
      return !var6.method_464();
   }

   @Inject(
      method = {"onMouseScroll"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/network/ClientPlayerEntity;isSpectator()Z",
         shift = Shift.BEFORE
      )},
      cancellable = true
   )
   private void onMouseScroll(long var1, double var3, double var5, CallbackInfo var7) {
      Event_65 var8 = new Event_65(var3, var5);
      MioAPI.field_4220.method_36(var8);
      if (var8.method_464()) {
         var7.cancel();
      }
   }
}
