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
   TextFieldWidget field_21599;
   @Shadow
   boolean field_21614;
   @Shadow
   private ParseResults<CommandSource> field_21610;
   @Shadow
   private CompletableFuture<Suggestions> field_21611;
   @Shadow
   private SuggestionWindow field_21612;

   public MixinChatInputSuggester() {
      super();
   }

   @Shadow
   public abstract void method_23920(boolean var1);

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
         if (this.field_21610 == null) {
            this.field_21610 = var5.parse(var3, CommandManager.field_3190);
         }

         int var6 = this.field_21599.getCursor();
         if (var6 >= 1 && (this.field_21612 == null || !this.field_21614)) {
            this.field_21611 = var5.getCompletionSuggestions(this.field_21610, var6);
            this.field_21611.thenRun(() -> {
               if (this.field_21611.isDone()) {
                  this.method_23920(false);
               }
            });
         }

         var1.cancel();
      }
   }
}
