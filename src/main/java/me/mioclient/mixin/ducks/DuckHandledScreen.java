package me.mioclient.mixin.ducks;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({HandledScreen.class})
public interface DuckHandledScreen {
   @Accessor("focusedSlot")
   Slot mio$getFocusedSlot();

   @Accessor("x")
   int getX();

   @Accessor("y")
   int getY();

   @Accessor("backgroundWidth")
   int getBgWidth();

   @Accessor("backgroundHeight")
   int getBgHeight();
}
