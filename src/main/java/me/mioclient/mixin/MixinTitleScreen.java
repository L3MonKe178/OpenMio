package me.mioclient.mixin;

import me.mioclient.Hub;
import me.mioclient.api.MioAPI;
import me.mioclient.internal.Class_0152;
import me.mioclient.module.client.HUDModule;
import me.mioclient.module.client.UIModule;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({TitleScreen.class})
public class MixinTitleScreen extends Screen implements MioAPI {
   private static HUDModule h = Hub.field_2595.method_78(HUDModule.class);
   private static UIModule c = Hub.field_2595.method_78(UIModule.class);

   protected MixinTitleScreen(Text var1) {
      super(var1);
   }

   @Inject(
      method = {"render"},
      at = {@At("TAIL")}
   )
   private void renderHook(DrawContext var1, int var2, int var3, float var4, CallbackInfo var5) {
      if (!Class_0152.field_442) {
         var1.drawTextWithShadow(
            field_4219.textRenderer,
            "Mio " + Formatting.WHITE + "v2 " + Formatting.GRAY + "(patch " + "02/03/2025 22:47".substring(0, 10) + ")",
            2,
            2,
            h.field_961.getValue().method_2(h, 0.0F).getRGB()
         );
      }
   }

   public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
      if (!c.getKeybind().method_39() && c.getKeybind().method_38() == keyCode) {
         c.enable();
         Hub.method_755().method_1002();
      }

      return true;
   }
}
