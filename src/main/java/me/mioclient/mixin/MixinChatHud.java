package me.mioclient.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import java.util.ConcurrentModificationException;
import java.util.List;
import me.mioclient.Hub;
import me.mioclient.api.Class_0333;
import me.mioclient.api.Class_1309;
import me.mioclient.api.Class_1322;
import me.mioclient.enum_.Orientation;
import me.mioclient.event.Event_46;
import me.mioclient.internal.Class_0436;
import me.mioclient.internal.Class_1016;
import me.mioclient.internal.Class_1032;
import me.mioclient.internal.Class_1245;
import me.mioclient.module.abstract_.AbstractModule_14;
import me.mioclient.module.client.FontsModule;
import me.mioclient.module.misc.BetterChatModule;
import me.mioclient.module.render.NoRenderModule;
import net.minecraft.client.font.TextHandler;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.ChatHudLine;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.client.gui.hud.ChatHudLine.Visible;
import net.minecraft.client.gui.hud.MessageIndicator.Icon;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({ChatHud.class})
public abstract class MixinChatHud implements Class_1309, Class_1322 {
   private static final NoRenderModule norender = Hub.field_2595.method_78(NoRenderModule.class);
   private static final BetterChatModule betterchat = Hub.field_2595.method_78(BetterChatModule.class);
   private static final AbstractModule_14 chathud = Hub.field_2595.method_78(AbstractModule_14.class);
   private static final FontsModule fonts = Hub.field_2595.method_78(FontsModule.class);
   @Shadow
   @Final
   private List<Visible> field_2064;
   @Unique
   private MessageSignatureData last;
   @Unique
   private int current;
   @Unique
   private static boolean skip;

   public MixinChatHud() {
      super();
   }

   @Shadow
   protected abstract void method_45027(ChatHudLine var1);

   @Shadow
   protected abstract void method_1815(ChatHudLine var1);

   @Shadow
   protected abstract void method_58744(ChatHudLine var1);

   @Shadow
   public abstract void method_44811(Text var1, @Nullable MessageSignatureData var2, @Nullable MessageIndicator var3);

   @Shadow
   public abstract double method_1814();

   @Shadow
   protected abstract int method_44752();

   private void drawIndicatorIcon(MatrixStack var1, int var2, int var3, Icon var4) {
   }

   @Inject(
      method = {"logChatMessage"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void logChatMessageHook(ChatHudLine var1, CallbackInfo var2) {
      if (var1.indicator() == Class_1245.field_3910) {
         var2.cancel();
      }
   }

   @Inject(
      method = {"addVisibleMessage"},
      at = {@At("HEAD")}
   )
   private void addMessage(ChatHudLine var1, CallbackInfo var2) {
      BetterChatModule.field_3921 = true;
      MessageSignatureData var3 = var1.signature();
      this.last = var3;
      if (var3 != null && var3.toByteBuffer().getInt() < 0) {
         try {
            field_4219.inGameHud
               .getChatHud()
               .visibleMessages
               .removeIf(var1x -> var1x != null && ((Class_0333)(Object)var1x).getSignature() != null && ((Class_0333)(Object)var1x).getSignature().equals(var3));
         } catch (ConcurrentModificationException var5) {
         }
      }
   }

   @Inject(
      method = {"addVisibleMessage"},
      at = {@At("RETURN")}
   )
   private void noracismcattyn(ChatHudLine var1, CallbackInfo var2) {
      BetterChatModule.field_3921 = false;
   }

   @Redirect(
      method = {"addVisibleMessage"},
      at = @At(
         value = "INVOKE",
         target = "Ljava/util/List;add(ILjava/lang/Object;)V",
         ordinal = 0
      )
   )
   private <E> void visibleInitHook(List<E> var1, int var2, E var3) {
      ((Class_0333)var3).setSignature(this.last);
      Visible var4 = (Visible)var3;
      Event_46 var5 = new Event_46(var4);
      Class_1309.field_4220.method_36(var5);
      var1.add(var2, var3);
   }

   @Redirect(
      method = {"render"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/hud/ChatHudLine$Visible;indicator()Lnet/minecraft/client/gui/hud/MessageIndicator;"
      ),
      require = 0
   )
   private MessageIndicator renderHook(Visible var1) {
      return norender.isToggled() && norender.field_726.getValue() ? null : var1.indicator();
   }

   @WrapWithCondition(
      method = {"render"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/DrawContext;fill(IIIII)V",
         ordinal = 1
      )}
   )
   private boolean renderHook2(DrawContext var1, int var2, int var3, int var4, int var5, int var6) {
      return !norender.isToggled() || !norender.field_726.getValue();
   }

   @WrapOperation(
      method = {"render"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/DrawContext;drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/OrderedText;III)I"
      )}
   )
   private int textHook(DrawContext var1, TextRenderer var2, OrderedText var3, int var4, int var5, int var6, Operation<Integer> var7) {
      var4 -= norender.isToggled() && norender.field_726.getValue() ? 3 : 0;
      if (betterchat.method_1092() && this.current < this.field_2064.size()) {
         Class_0333 var8 = (Class_0333)(Object)this.field_2064.get(this.current);
         var8.getProgress().method_3(true);
         float var9 = var8.getProgress().method_45();
         long var10 = var8.mio$getAddTime();
         boolean var12 = betterchat.field_3938.getValue() == Orientation.BOTH;
         if (betterchat.field_3938.getValue() == Orientation.VERTICAL || var12) {
            var5 = (int)((float)var5 + 9.0F * (1.0F - var9));
         }

         if (betterchat.field_3938.getValue() == Orientation.HORIZONTAL || var12) {
            var4 = (int)((float)var4 - (float)var2.getWidth(var3) * (1.0F - var9));
         }

         if (betterchat.field_3940.getValue() != 0) {
            int var13 = var6 >> 24 & 0xFF;
            float var14 = MathHelper.clamp((float)(System.currentTimeMillis() - var10) / (float)betterchat.field_3940.getValue().intValue(), 0.0F, 1.0F);
            var13 = (int)Math.max((float)var13 * var14, 10.0F);
            var6 = var6 & 16777215 | var13 << 24;
         }
      }

      Class_0436 var16 = Class_1016.field_3143.method_914();
      if (var16 != null && fonts.isToggled() && fonts.field_370.getValue()) {
         var16.method_2(var1.getMatrices(), var3, (float)var4, (float)var5, var6, true);
         return 0;
      } else {
         var7.call(new Object[]{var1, var2, var3, var4, var5, var6});
         return 0;
      }
   }

   @Inject(
      method = {"render"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/util/math/MatrixStack;translate(FFF)V",
         ordinal = 0
      )}
   )
   private void renderPreHook(DrawContext var1, int var2, int var3, int var4, boolean var5, CallbackInfo var6) {
      float var7 = 0.0F;
      float var8 = 0.0F;
      if (chathud.isToggled()) {
         var7 += chathud.method_648();
         var8 += chathud.method_647();
      }

      if (betterchat.method_1092() && betterchat.field_3938.getValue() == Orientation.BOUNCE) {
         var8 += (betterchat.field_3948.method_605() - 1.0F) * 8.0F;
      }

      if (var7 != 0.0F || var8 != 0.0F) {
         var1.getMatrices().translate(var7, var8, 0.0F);
      }
   }

   @Inject(
      method = {"render"},
      at = {@At("TAIL")}
   )
   private void render(DrawContext var1, int var2, int var3, int var4, boolean var5, CallbackInfo var6) {
      Class_1016.field_3143.method_474();
   }

   @ModifyArg(
      method = {"render"},
      at = @At(
         value = "INVOKE",
         target = "Ljava/util/List;get(I)Ljava/lang/Object;",
         ordinal = 0
      )
   )
   private int renderHook4(int var1) {
      this.current = var1;
      return var1;
   }

   @Inject(
      method = {"addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;Lnet/minecraft/client/gui/hud/MessageIndicator;)V"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/hud/ChatHud;logChatMessage(Lnet/minecraft/client/gui/hud/ChatHudLine;)V",
         shift = Shift.BEFORE
      )},
      cancellable = true
   )
   private void addMessageHook(Text var1, MessageSignatureData var2, MessageIndicator var3, CallbackInfo var4, @Local ChatHudLine var5) {
      if (!skip) {
         Event_46 var7 = new Event_46(var3, var2, var1);
         Class_1309.field_4220.method_36(var7);
         var1 = var7.method_1033();
         var2 = var7.getSignature();
         var4.cancel();
         if (!var7.method_464()) {
            if (!var1.equals(var1)) {
               skip = true;
               this.method_44811(var1, var2, var3);
               skip = false;
               return;
            }

            this.method_45027(var5);
            this.method_1815(var5);
            this.method_58744(var5);
         }
      }
   }

   @ModifyExpressionValue(
      method = {"addVisibleMessage"},
      at = {@At(
         value = "INVOKE",
         target = "Ljava/util/List;size()I",
         ordinal = 2
      )}
   )
   public int chatHistory(int var1) {
      return betterchat.isToggled() && betterchat.field_3946.getValue() ? 1 : var1;
   }

   @ModifyExpressionValue(
      method = {"addMessage(Lnet/minecraft/client/gui/hud/ChatHudLine;)V"},
      at = {@At(
         value = "INVOKE",
         target = "Ljava/util/List;size()I",
         ordinal = 0
      )}
   )
   public int chatHistory2(int var1) {
      return betterchat.isToggled() && betterchat.field_3946.getValue() ? 1 : var1;
   }

   @Inject(
      method = {"clear"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void clearHook(boolean var1, CallbackInfo var2) {
      if (betterchat.isToggled() && betterchat.field_3947.getValue() && !BetterChatModule.field_3922) {
         var2.cancel();
      }
   }

   @WrapOperation(
      method = {"render"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/DrawContext;fill(IIIII)V"
      )}
   )
   private void fillHook(DrawContext var1, int var2, int var3, int var4, int var5, int var6, Operation<Void> var7) {
      var7.call(new Object[]{var1, var2, var3, var4, var5, var6});
   }

   @WrapOperation(
      method = {"render"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/DrawContext;fill(IIIIII)V"
      )}
   )
   private void fillHook(DrawContext var1, int var2, int var3, int var4, int var5, int var6, int var7, Operation<Void> var8) {
      var8.call(new Object[]{var1, var2, var3, var4, var5, var6, var7});
   }

   @ModifyExpressionValue(
      method = {"addToMessageHistory"},
      at = {@At(
         value = "INVOKE",
         target = "Ljava/lang/String;startsWith(Ljava/lang/String;)Z"
      )}
   )
   private boolean addToMessageHistory(boolean var1, @Local(argsOnly = true) String var2) {
      return var1 || var2.startsWith(Class_1032.method_927());
   }

   @ModifyExpressionValue(
      method = {"getTextStyleAt"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/font/TextRenderer;getTextHandler()Lnet/minecraft/client/font/TextHandler;"
      )}
   )
   private TextHandler getTextStyleAtHook(TextHandler var1) {
      Class_0436 var2 = Class_1016.field_3143.method_914();
      return (TextHandler)(var2 != null && fonts.isToggled() && fonts.field_370.getValue() ? var2.method_477() : var1);
   }

   @Inject(
      method = {"toChatLineX"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void toChatLineX(double var1, CallbackInfoReturnable<Double> var3) {
      if (chathud.isToggled() && chathud.method_648() != 0.0F) {
         var3.setReturnValue((var1 - (double)chathud.method_14().method_59()) / this.method_1814());
      }
   }

   @Inject(
      method = {"toChatLineY"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void toChatLineY(double var1, CallbackInfoReturnable<Double> var3) {
      if (chathud.isToggled() && chathud.method_647() != 0.0F) {
         var3.setReturnValue(
            ((double)chathud.method_14().method_60() - var1 + (double)chathud.method_14().method_193()[1]) / (this.method_1814() * (double)this.method_44752())
         );
      }
   }

   @Override
   public List<Visible> getVisible() {
      return this.field_2064;
   }
}
