package me.mioclient.mixin;

import me.mioclient.api.MioAPI;
import me.mioclient.event.Event_18;
import me.mioclient.event.Event_60;
import me.mioclient.module.misc.BetterChatModule;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({Keyboard.class})
public class MixinKeyboard {
   @Shadow
   @Final
   private MinecraftClient field_1678;

   public MixinKeyboard() {
      super();
   }

   @Inject(
      method = {"onKey"},
      at = {@At("TAIL")},
      cancellable = true
   )
   private void onKey(long var1, int var3, int var4, int var5, int var6, CallbackInfo var7) {
      if (var3 >= 0 && this.field_1678.currentScreen == null && var5 == 1) {
         Event_18 var8 = new Event_18(var3, false);
         MioAPI.field_4220.method_36(var8);
         if (var8.method_464()) {
            var7.cancel();
         }
      }
   }

   @Inject(
      method = {"onChar"},
      at = {@At("HEAD")}
   )
   public void onChar(long var1, int var3, int var4, CallbackInfo var5) {
      if (this.field_1678.currentScreen == null && var1 == this.field_1678.getWindow().getHandle()) {
         Event_60 var6 = new Event_60(var3);
         MioAPI.field_4220.method_36(var6);
      }
   }

   @Inject(
      method = {"processF3"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/hud/ChatHud;clear(Z)V",
         shift = Shift.BEFORE
      )}
   )
   private void processF3Pre(int var1, CallbackInfoReturnable<Boolean> var2) {
      BetterChatModule.field_3922 = true;
   }

   @Inject(
      method = {"processF3"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/hud/ChatHud;clear(Z)V",
         shift = Shift.AFTER
      )}
   )
   private void processF3Post(int var1, CallbackInfoReturnable<Boolean> var2) {
      BetterChatModule.field_3922 = false;
   }
}
