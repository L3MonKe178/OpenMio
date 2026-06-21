package me.mioclient.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.blaze3d.systems.RenderSystem;
import me.mioclient.Hub;
import me.mioclient.api.Class_1309;
import me.mioclient.enum_.Class_0760;
import me.mioclient.event.Event_26;
import me.mioclient.internal.Class_0745;
import me.mioclient.internal.Class_0838;
import me.mioclient.module.abstract_.AbstractModule_38;
import me.mioclient.module.client.HUDModule;
import me.mioclient.module.render.CrosshairModule;
import me.mioclient.module.render.NoRenderModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.option.AttackIndicator;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.scoreboard.ScoreboardObjective;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin({InGameHud.class})
public class MixinInGameHud implements Class_1309 {
   private static HUDModule hud = Hub.field_2595.method_78(HUDModule.class);
   private static NoRenderModule norender = Hub.field_2595.method_78(NoRenderModule.class);
   private static CrosshairModule crosshair = Hub.field_2595.method_78(CrosshairModule.class);
   private static AbstractModule_38 nohitdelay = Hub.field_2595.method_78(AbstractModule_38.class);

   public MixinInGameHud() {
      super();
   }

   @Inject(
      method = {"renderPlayerList"},
      at = {@At("HEAD")}
   )
   public void render(DrawContext var1, RenderTickCounter var2, CallbackInfo var3) {
      hud.method_340(field_4219.currentScreen instanceof ChatScreen ? 12.0F : 0.0F);
      if (!MinecraftClient.getInstance().inGameHud.getDebugHud().shouldShowDebugHud() && !MinecraftClient.getInstance().options.hudHidden) {
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         var1.fill(-1, -1, 0, 0, 0);
         RenderSystem.disableDepthTest();
         RenderSystem.enableBlend();
         RenderSystem.blendFunc(770, 771);
         RenderSystem.disableCull();
         GL11.glEnable(2848);
         Event_26 var4 = Event_26.method_2(var1.getMatrices(), var1, Class_0838.method_776());
         field_4220.method_36(var4);
         Class_0745.method_474();
         RenderSystem.enableDepthTest();
         GL11.glDisable(2848);
      }
   }

   @Inject(
      method = {"renderHeldItemTooltip(Lnet/minecraft/client/gui/DrawContext;)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void renderHeldItemTooltipHook(DrawContext var1, CallbackInfo var2) {
      if (norender.isToggled() && norender.field_733.getValue()) {
         var2.cancel();
      }
   }

   @Inject(
      method = {"renderStatusEffectOverlay"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void renderStatusEffectOverlayHook(DrawContext var1, RenderTickCounter var2, CallbackInfo var3) {
      if (hud.isToggled() && hud.field_955.getValue() == Class_0760.HIDE) {
         var3.cancel();
      }
   }

   @Inject(
      method = {"renderCrosshair"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onRenderCrosshair(DrawContext var1, RenderTickCounter var2, CallbackInfo var3) {
      if (crosshair.isToggled()) {
         var3.cancel();
      }
   }

   @ModifyExpressionValue(
      method = {"renderCrosshair"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/option/SimpleOption;getValue()Ljava/lang/Object;",
         ordinal = 1
      )}
   )
   public <T> Object renderAttackIndicatorHook(T var1) {
      return nohitdelay.isToggled() ? AttackIndicator.OFF : var1;
   }

   @Inject(
      method = {"renderScoreboardSidebar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/scoreboard/ScoreboardObjective;)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void renderScoreboardSidebarHook(DrawContext var1, ScoreboardObjective var2, CallbackInfo var3) {
      if (norender.isToggled() && norender.field_731.getValue()) {
         var3.cancel();
      }

      var1.getMatrices().push();
      var1.getMatrices().translate((float)(-Math.max(hud.method_341() - 1, 0)), 0.0F, 0.0F);
   }

   @Inject(
      method = {"renderScoreboardSidebar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/scoreboard/ScoreboardObjective;)V"},
      at = {@At("TAIL")},
      cancellable = true
   )
   private void nigger(DrawContext var1, ScoreboardObjective var2, CallbackInfo var3) {
      var1.getMatrices().pop();
   }

   @ModifyArgs(
      method = {"renderMiscOverlays"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/hud/InGameHud;renderOverlay(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/util/Identifier;F)V",
         ordinal = 0
      )
   )
   private void renderOverlayHook(Args var1) {
      if (norender.isToggled() && norender.field_720.getValue()) {
         var1.set(2, 0.0F);
      }
   }
}
