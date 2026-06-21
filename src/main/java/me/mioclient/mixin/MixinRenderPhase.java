package me.mioclient.mixin;

import me.mioclient.Hub;
import me.mioclient.api.MioAPI;
import me.mioclient.enum_.PreType;
import me.mioclient.event.Event_2;
import me.mioclient.module.render.ChamsModule;
import net.minecraft.client.render.RenderPhase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin({RenderPhase.class})
public class MixinRenderPhase {
   private static final ChamsModule chams = Hub.field_2595.method_78(ChamsModule.class);
   private static final String TEXTURING_ID = "glint_texturing";
   @Unique
   private boolean mio$isGlintTexturing;

   public MixinRenderPhase() {
      super();
   }

   @Inject(
      method = {"<init>"},
      at = {@At("TAIL")}
   )
   private void init(String var1, Runnable var2, Runnable var3, CallbackInfo var4) {
      this.mio$isGlintTexturing = var1.contains("glint_texturing");
   }

   @ModifyArgs(
      method = {"setupGlintTexturing"},
      at = @At(
         value = "INVOKE",
         target = "Lorg/joml/Matrix4f;translation(FFF)Lorg/joml/Matrix4f;"
      )
   )
   private static void translateHook(Args var0) {
      if (chams.field_230.method_105() && ChamsModule.field_254) {
         float var1 = (float)chams.field_232.getValue().intValue() / 100.0F;
         var0.set(0, -var1);
         var0.set(1, var1);
      }
   }

   @Inject(
      method = {"startDrawing"},
      at = {@At("TAIL")}
   )
   private void startDraw(CallbackInfo var1) {
      if (this.mio$isGlintTexturing) {
         MioAPI.field_4220.method_36(new Event_2(PreType.PRE));
      }
   }

   @Inject(
      method = {"endDrawing"},
      at = {@At("TAIL")}
   )
   private void endDrawing(CallbackInfo var1) {
      if (this.mio$isGlintTexturing) {
         MioAPI.field_4220.method_36(new Event_2(PreType.POST));
      }
   }
}
