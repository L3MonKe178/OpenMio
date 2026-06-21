package me.mioclient.mixin;

import me.mioclient.Hub;
import me.mioclient.internal.CommandManager;
import me.mioclient.internal.Class_1117;
import me.mioclient.internal.Class_1299;
import me.mioclient.module.render.BlurModule;
import me.mioclient.module.render.NoRenderModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Style;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({Screen.class})
public abstract class MixinScreen {
   private static final NoRenderModule norender = Hub.field_2595.method_78(NoRenderModule.class);
   private static final BlurModule blur = Hub.field_2595.method_78(BlurModule.class);
   @Shadow
   @Nullable
   protected MinecraftClient field_22787;
   @Shadow
   public int field_22789;
   @Shadow
   public int field_22790;

   public MixinScreen() {
      super();
   }

   @Shadow
   protected abstract void method_57735(DrawContext var1);

   @Inject(
      method = {"handleTextClick"},
      at = {@At(
         value = "INVOKE",
         target = "Ljava/lang/String;startsWith(Ljava/lang/String;)Z",
         shift = Shift.BEFORE
      )}
   )
   private void handleTextClickHook(Style var1, CallbackInfoReturnable<Boolean> var2) {
      if (this.field_22787.getNetworkHandler() != null) {
         String var3 = var1.getClickEvent().getValue();
         if (var3.startsWith(CommandManager.method_927())) {
            this.field_22787.getNetworkHandler().sendChatMessage(var3);
         }
      }
   }

   @Inject(
      method = {"renderInGameBackground"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void renderInGameBackgroundHook(DrawContext var1, CallbackInfo var2) {
      if (norender.method_285()) {
         var2.cancel();
      }
   }

   @Inject(
      method = {"renderBackground"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/screen/Screen;applyBlur(F)V",
         shift = Shift.BEFORE
      )},
      cancellable = true
   )
   private void renderBackground(DrawContext var1, int var2, int var3, float var4, CallbackInfo var5) {
      if (blur.isToggled() && this.field_22787.world != null && this.field_22787.player != null) {
         this.method_57735(var1);
         var5.cancel();
      } else if (MinecraftClient.getInstance().currentScreen instanceof Class_1117) {
         float var6 = (float)this.field_22787.options.getMenuBackgroundBlurrinessValue();
         this.method_57735(var1);
         Class_1299.method_2(() -> var1.fill(0, 0, this.field_22789, this.field_22790, -1), var6);
         var5.cancel();
      }
   }
}
