package me.mioclient.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import com.mojang.authlib.GameProfile;
import java.awt.Color;
import java.util.Comparator;
import java.util.List;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_0829;
import me.mioclient.module.misc.ExtraTabModule;
import me.mioclient.module.player.NameProtectModule;
import me.mioclient.module.render.NoRenderModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.PlayerListHud;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.network.ClientConnection;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin({PlayerListHud.class})
public class MixinPlayerListHud {
   @Shadow
   @Final
   private MinecraftClient client;
   private static final NoRenderModule norender = Hub.field_2595.method_78(NoRenderModule.class);
   private static final ExtraTabModule extratab = Hub.field_2595.method_78(ExtraTabModule.class);
   private static final NameProtectModule nameprotect = Hub.field_2595.method_78(NameProtectModule.class);

   public MixinPlayerListHud() {
      super();
   }

   @ModifyExpressionValue(
      method = {"collectPlayerEntries"},
      at = {@At(
         value = "FIELD",
         target = "Lnet/minecraft/client/gui/hud/PlayerListHud;ENTRY_ORDERING:Ljava/util/Comparator;"
      )}
   )
   private Comparator<PlayerListEntry> collectPlayerEntries(Comparator<PlayerListEntry> var1) {
      return extratab.isToggled() && extratab.field_75.getValue() ? extratab.method_55() : var1;
   }

   @ModifyArg(
      method = {"collectPlayerEntries"},
      at = @At(
         value = "INVOKE",
         target = "Ljava/util/stream/Stream;limit(J)Ljava/util/stream/Stream;"
      )
   )
   private long renderHook(long var1) {
      return extratab.isToggled() ? Long.MAX_VALUE : var1;
   }

   @ModifyExpressionValue(
      method = {"render"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/entity/player/PlayerEntity;isPartVisible(Lnet/minecraft/entity/player/PlayerModelPart;)Z"
      )}
   )
   private boolean renderHook(boolean var1) {
      return extratab.isToggled() && extratab.field_71.getValue() ? true : var1;
   }

   @Inject(
      method = {"getPlayerName"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void renderHook2(PlayerListEntry var1, CallbackInfoReturnable<Text> var2) {
      if (extratab.isToggled() && extratab.field_72.getValue()) {
         String var3 = Formatting.strip(((Text)var2.getReturnValue()).getString());
         if (var3 == null) {
            return;
         }

         String var4 = var1.getProfile().getName();
         if (extratab.field_74.getValue()) {
            Color var5 = Hub.field_2603.method_9(var4, null);
            if (var5 != null) {
               var2.setReturnValue(Text.literal(var3).styled(var1x -> var1x.withColor(var5.hashCode())));
            }
         }

         if (MinecraftClient.getInstance().getSession().getUsername().equals(var4)) {
            var2.setReturnValue(Text.literal(var3).styled(var0 -> var0.withColor(extratab.field_73.getValue().hashCode())));
         }
      }
   }

   @Inject(
      method = {"render"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/MinecraftClient;isInSingleplayer()Z"
      )}
   )
   private void renderHook(
      DrawContext var1,
      int var2,
      Scoreboard var3,
      ScoreboardObjective var4,
      CallbackInfo var5,
      @Local(ordinal = 4) LocalIntRef var6,
      @Local(ordinal = 5) LocalIntRef var7,
      @Local(ordinal = 6) LocalIntRef var8,
      @Local(ordinal = 0) List<PlayerListEntry> var9
   ) {
      if (extratab.isToggled()) {
         int var10 = var9.size();
         int var11 = var10;

         int var12;
         for (var12 = 1; var11 > 40; var11 = (var10 + var12 - 1) / var12) {
            var12++;
         }

         var8.set(var12);
         var7.set(var11);
         var6.set(var10);
      }
   }

   @Inject(
      method = {"renderLatencyIcon"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void renderLatencyIconHook(DrawContext var1, int var2, int var3, int var4, PlayerListEntry var5, CallbackInfo var6) {
      if (extratab.isToggled() && extratab.field_69.getValue() != Class_0829.VANILLA) {
         if (extratab.field_69.getValue() == Class_0829.NONE) {
            var6.cancel();
         } else {
            String var7 = "" + Formatting.YELLOW + var5.getLatency();
            var1.drawTextWithShadow(this.client.textRenderer, var7, var3 + var2 - this.client.textRenderer.getWidth(var7), var4, 16777215);
            var6.cancel();
         }
      }
   }

   @ModifyConstant(
      method = {"render"},
      constant = {@Constant(
         intValue = 13
      )}
   )
   private int renderConstant(int var1) {
      return extratab.isToggled() && extratab.field_69.getValue() == Class_0829.NONE ? 0 : var1;
   }

   @ModifyArgs(
      method = {"render"},
      at = @At(
         value = "INVOKE",
         target = "Ljava/lang/Math;max(II)I",
         ordinal = 0
      )
   )
   private void render(Args var1, @Local PlayerListEntry var2) {
      if (extratab.isToggled() && extratab.field_69.getValue() == Class_0829.TEXT) {
         var1.set(1, (Integer)var1.get(1) + this.client.textRenderer.getWidth(String.valueOf(var2.getLatency())));
      }
   }

   @Redirect(
      method = {"render"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/network/ClientConnection;isEncrypted()Z"
      )
   )
   private boolean africanAmerican(ClientConnection var1) {
      return var1.isEncrypted() && !norender.method_280();
   }

   @Inject(
      method = {"render"},
      at = {@At("HEAD")}
   )
   private void renderHead(DrawContext var1, int var2, Scoreboard var3, ScoreboardObjective var4, CallbackInfo var5) {
      if (extratab.method_54() != 1.0F) {
         var1.getMatrices().push();
         var1.getMatrices().scale(extratab.method_54(), extratab.method_54(), 1.0F);
      }
   }

   @Inject(
      method = {"render"},
      at = {@At("TAIL")}
   )
   private void renderTail(DrawContext var1, int var2, Scoreboard var3, ScoreboardObjective var4, CallbackInfo var5) {
      if (extratab.method_54() != 1.0F) {
         var1.getMatrices().pop();
      }
   }

   @ModifyVariable(
      method = {"render"},
      at = @At("HEAD"),
      argsOnly = true
   )
   private int renderArgs(int var1) {
      return extratab.method_54() == 1.0F ? var1 : (int)((float)var1 / extratab.method_54());
   }

   @Inject(
      method = {"method_46511"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void methodHook(PlayerListEntry var0, CallbackInfoReturnable<String> var1) {
      GameProfile var2 = MinecraftClient.getInstance().player.getGameProfile();
      if (nameprotect.isToggled() && var0.getProfile().equals(var2)) {
         var1.setReturnValue(nameprotect.field_1851.getValue());
      }
   }
}
