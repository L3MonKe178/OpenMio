package me.mioclient.mixin;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.suggestion.Suggestions;
import java.util.concurrent.CompletableFuture;
import me.mioclient.api.MioAPI;
import me.mioclient.internal.CommandManager;
import net.minecraft.client.gui.screen.ChatInputSuggestor;
import net.minecraft.client.gui.screen.ChatInputSuggestor.SuggestionWindow;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.command.CommandSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin({ChatInputSuggestor.class})
public abstract class MixinChatInputSuggester implements MioAPI {
   @Shadow
   @Final
   TextFieldWidget textField;
   @Shadow
   boolean completingSuggestions;
   @Shadow
   private ParseResults<CommandSource> parse;
   @Shadow
   private CompletableFuture<Suggestions> pendingSuggestions;
   @Shadow
   private SuggestionWindow window;

   public MixinChatInputSuggester() {
      super();
   }

   @Shadow
   public abstract void show(boolean var1);

   @Inject(
      method = {"refresh"},
      at = {@At(
         value = "INVOKE",
         target = "Lcom/mojang/brigadier/StringReader;canRead()Z",
         remap = false
      )},
      cancellable = true,
      locals = LocalCapture.CAPTURE_FAILHARD
   )
   public void refresh(CallbackInfo var1, String var2, StringReader var3) {
      String var4 = CommandManager.method_927();
      if (var3.canRead(var4.length()) && var3.getString().startsWith(var4, var3.getCursor())) {
         var3.setCursor(var3.getCursor() + var4.length());
         CommandDispatcher var5 = CommandManager.field_3191;
         if (this.parse == null) {
            this.parse = var5.parse(var3, CommandManager.field_3190);
         }

         int var6 = this.textField.getCursor();
         if (var6 >= 1 && (this.window == null || !this.completingSuggestions)) {
            this.pendingSuggestions = var5.getCompletionSuggestions(this.parse, var6);
            this.pendingSuggestions.thenRun(() -> {
               if (this.pendingSuggestions.isDone()) {
                  this.show(false);
               }
            });
         }

         var1.cancel();
      }
   }
}
