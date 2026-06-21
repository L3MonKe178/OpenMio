package me.mioclient.mixin.ducks;

import net.minecraft.client.gui.screen.ChatInputSuggestor.SuggestionWindow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({SuggestionWindow.class})
public interface DuckSuggestionWindow {
   @Accessor("completed")
   boolean isCompleted();

   @Accessor("selection")
   int getSelection();

   @Accessor("typedText")
   String getTypedText();
}
