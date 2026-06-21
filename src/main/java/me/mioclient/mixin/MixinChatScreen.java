package me.mioclient.mixin;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.context.ParsedCommandNode;
import com.mojang.brigadier.suggestion.Suggestion;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.tree.CommandNode;
import java.awt.Color;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import me.mioclient.Hub;
import me.mioclient.api.Class_0333;
import me.mioclient.internal.Class_0227;
import me.mioclient.internal.Constants;
import me.mioclient.internal.RenderUtil;
import me.mioclient.internal.Class_0855;
import me.mioclient.internal.CommandManager;
import me.mioclient.mixin.ducks.DuckChatInputSuggester;
import me.mioclient.mixin.ducks.DuckSuggestionWindow;
import me.mioclient.module.client.HUDModule;
import me.mioclient.module.client.IRCModule;
import me.mioclient.module.client.UIModule;
import me.mioclient.module.misc.BetterChatModule;
import me.mioclient.module.render.NoRenderModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.ChatHudLine.Visible;
import net.minecraft.client.gui.screen.ChatInputSuggestor;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.command.CommandSource;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ChatScreen.class})
public class MixinChatScreen extends Screen {
   private static final BetterChatModule betterchat = Hub.field_2595.method_78(BetterChatModule.class);
   private static final IRCModule irc = Hub.field_2595.method_78(IRCModule.class);
   private static HUDModule hud = Hub.field_2595.method_78(HUDModule.class);
   private static NoRenderModule norender = Hub.field_2595.method_78(NoRenderModule.class);
   @Shadow
   protected TextFieldWidget chatField;
   @Shadow
   ChatInputSuggestor chatInputSuggestor;
   @Unique
   private Class_0855 scroll;

   protected MixinChatScreen(Text var1) {
      super(var1);
   }

   @Inject(
      method = {"init"},
      at = {@At("HEAD")}
   )
   private void initHook(CallbackInfo var1) {
      this.scroll = new Class_0855();
      if (betterchat.field_3941.getValue() && betterchat.method_1092()) {
         for (Visible var3 : MinecraftClient.getInstance().inGameHud.getChatHud().visibleMessages) {
            ((Class_0333)(Object)var3).getProgress().method_36(false);
            ((Class_0333)(Object)var3).mio$setAddTime(System.currentTimeMillis());
         }
      }
   }

   @Inject(
      method = {"render"},
      at = {@At("RETURN")}
   )
   private void renderHook1(DrawContext var1, int var2, int var3, float var4, CallbackInfo var5) {
      boolean var6 = this.chatField.getText().startsWith(CommandManager.method_927());
      if (irc.isToggled() && irc.field_566.getValue() && this.chatField.getText().startsWith(irc.field_567.getValue())) {
         var6 = true;
      }

      Color var7 = UIModule.field_2843.field_2879.getValue();
      if (var6) {
         RenderUtil.field_2672
            .method_2(var1.getMatrices(), 1.0F, (float)(this.height - 2) - hud.method_338(), (float)(this.width - 2), (float)(this.height - 2), var7);
      }
   }

   @WrapWithCondition(
      method = {"render"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/DrawContext;fill(IIIII)V"
      )}
   )
   private boolean renderHook2(DrawContext var1, int var2, int var3, int var4, int var5, int var6) {
      float var7 = (float)var3 + (float)(var5 - var3) * (1.0F - hud.method_338() / 13.5F);
      RenderUtil.field_2672.method_9(var1.getMatrices(), (float)var2, var7, (float)var4, (float)var5, new Color(var6, true));
      return false;
   }

   @Redirect(
      method = {"render"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/widget/TextFieldWidget;render(Lnet/minecraft/client/gui/DrawContext;IIF)V"
      )
   )
   private void renderHook3(TextFieldWidget var1, DrawContext var2, int var3, int var4, float var5) {
      if ((double)(hud.method_338() / 13.5F) > Constants.field_689) {
         var1.render(var2, var3, var4, var5);
      }
   }

   @Inject(
      method = {"render"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/widget/TextFieldWidget;render(Lnet/minecraft/client/gui/DrawContext;IIF)V",
         shift = Shift.BEFORE
      )}
   )
   private void renderHook4(DrawContext var1, int var2, int var3, float var4, CallbackInfo var5) {
      if (this.chatField.getText().startsWith(CommandManager.method_927())) {
         DuckSuggestionWindow var6 = (DuckSuggestionWindow)((DuckChatInputSuggester)this.chatInputSuggestor).getWindow();
         CompletableFuture var7 = ((DuckChatInputSuggester)this.chatInputSuggestor).getSuggestion();
         if (var7 != null && var7.isDone() && this.chatField.getText().startsWith(CommandManager.method_927())) {
            String var8 = "";
            Suggestions var9 = (Suggestions)var7.join();
            ParseResults var10 = ((DuckChatInputSuggester)this.chatInputSuggestor).getParse();
            List var11 = Collections.emptyList();
            if (var10 != null && this.chatField.getCursor() == this.chatField.getText().length()) {
               List var12 = var10.getContext().getNodes();
               if (!var10.getContext().getNodes().isEmpty()) {
                  var11 = this.getStrings(((ParsedCommandNode)var12.get(var12.size() - 1)).getNode().getChildren());
               }
            }

            if (var6 != null) {
               String var14 = ((Suggestion)var9.getList().get(var6.getSelection())).apply(var6.getTypedText());
               var8 = var8 + (var14.startsWith(this.chatField.getText()) ? var14.substring(this.chatField.getText().length()) : "");
            }

            if (this.chatField.getCursor() > 0 && this.chatField.getText().charAt(this.chatField.getCursor() - 1) != ' ' || !var8.isEmpty()) {
               var8 = var8 + " ";
            }

            if (var10 != null && var10.getReader().canRead() && (var10.getReader().peek() != ' ' || var6 != null) && !var11.isEmpty()) {
               var11.remove(0);
            }

            var8 = var8 + String.join(" ", var11);
            this.chatField.setSuggestion(var8);
         }
      }
   }

   @WrapWithCondition(
      method = {"render"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/DrawContext;drawOrderedTooltip(Lnet/minecraft/client/font/TextRenderer;Ljava/util/List;II)V"
      )}
   )
   private boolean renderHook5(DrawContext var1, TextRenderer var2, List<? extends OrderedText> var3, int var4, int var5) {
      return !norender.isToggled() || !norender.field_726.getValue();
   }

   public void filesDragged(List<Path> paths) {
      Class_0227.method_2(this, paths);
   }

   private List<String> getStrings(Collection<CommandNode<CommandSource>> var1) {
      ArrayList var2 = new ArrayList();

      while (true) {
         CommandNode var3 = null;
         int var4 = 0;
         StringBuilder var5 = new StringBuilder("<");

         for (CommandNode var7 : var1) {
            var4++;
            var5.append(var7.getName());
            if (var4 != var1.size()) {
               var5.append(", ");
            } else {
               var3 = var7;
            }
         }

         var5.append(">");
         if (!var1.isEmpty()) {
            var2.add(var5.toString());
         }

         if (var1.size() != 1 || var3 == null) {
            return var2;
         }

         var1 = var3.getChildren();
      }
   }
}
