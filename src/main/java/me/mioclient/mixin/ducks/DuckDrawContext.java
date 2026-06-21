package me.mioclient.mixin.ducks;

import java.util.List;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.gui.tooltip.TooltipPositioner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({DrawContext.class})
public interface DuckDrawContext {
   @Invoker("drawTooltip")
   void drawTooltipsHook(TextRenderer var1, List<TooltipComponent> var2, int var3, int var4, TooltipPositioner var5);
}
