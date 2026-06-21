package me.mioclient.mixin.ducks;

import java.util.List;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({Screen.class})
public interface DuckScreen {
   @Accessor
   List<Drawable> getDrawables();

   @Accessor
   void setDrawables(List<Drawable> var1);

   @Invoker("addDrawableChild")
   <T extends Element & Drawable & Selectable> T addDrawableChildHook(T var1);
}
