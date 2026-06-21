package me.mioclient.mixin;

import me.mioclient.Hub;
import me.mioclient.internal.Class_0152;
import me.mioclient.module.client.UIModule;
import me.mioclient.module.exploit.FakeVanillaModule;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.widget.CheckboxWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.CheckboxWidget.Builder;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({MultiplayerScreen.class})
public class MixinMultiplayerScreen extends Screen {
   private static final FakeVanillaModule fakevanilla = Hub.field_2595.method_78(FakeVanillaModule.class);
   private static final UIModule clickgui = Hub.field_2595.method_78(UIModule.class);

   protected MixinMultiplayerScreen(Text var1) {
      super(var1);
   }

   @Inject(
      method = {"init"},
      at = {@At("TAIL")}
   )
   private void initHook(CallbackInfo var1) {
      if (!Class_0152.field_442) {
         Builder var2 = CheckboxWidget.builder(Text.of("Vanilla"), this.textRenderer)
            .pos(2, 6)
            .checked(fakevanilla.isToggled())
            .callback((var0, var1x) -> fakevanilla.method_38(var1x));
         this.addDrawableChild(var2.build());
      }
   }

   @Inject(
      method = {"keyPressed"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void keyPressed(int var1, int var2, int var3, CallbackInfoReturnable<Boolean> var4) {
      if (!(this.getFocused() instanceof TextFieldWidget)) {
         if (var1 == clickgui.getKeybind().method_38()) {
            var4.setReturnValue(false);
            var4.cancel();
            clickgui.enable();
            Hub.method_755().method_1002();
         }
      }
   }
}
