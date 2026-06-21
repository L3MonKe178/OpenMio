package me.mioclient.mixin.ducks;

import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.suggestion.Suggestions;
import java.util.concurrent.CompletableFuture;
import net.minecraft.client.gui.screen.ChatInputSuggestor;
import net.minecraft.client.gui.screen.ChatInputSuggestor.SuggestionWindow;
import net.minecraft.command.CommandSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({ChatInputSuggestor.class})
public interface DuckChatInputSuggester {
   @Accessor("pendingSuggestions")
   CompletableFuture<Suggestions> getSuggestion();

   @Accessor("window")
   SuggestionWindow getWindow();

   @Accessor("parse")
   ParseResults<CommandSource> getParse();
}
