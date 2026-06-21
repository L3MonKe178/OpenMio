package me.mioclient.internal;

import me.mioclient.mixin.ducks.DuckHandledScreen;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents.AfterInit;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.HopperScreen;
import net.minecraft.client.gui.screen.ingame.ShulkerBoxScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;

public class Class_0665 implements AfterInit {
   public Class_0665() {
      super();
   }

   public void afterInit(MinecraftClient var1, Screen var2, int var3, int var4) {
      if (!Class_0982.field_3025.isToggled()) {
         Class_0982.field_3026 = null;
      } else {
         if (!(var2 instanceof GenericContainerScreen) && !(var2 instanceof ShulkerBoxScreen) && !(var2 instanceof HopperScreen)) {
            Class_0982.field_3026 = null;
         } else {
            MinecraftClient var5 = MinecraftClient.getInstance();
            HandledScreen var6 = (HandledScreen)var2;
            DuckHandledScreen var7 = (DuckHandledScreen)var6;
            Class_0982.field_3026 = new TextFieldWidget(var5.textRenderer, var7.getX() + 81, var7.getY() + 5, 88, 10, Text.literal(""));
            Class_0982.field_3026.setText(Class_0982.text);
            Class_0982.field_3026.setFocused(false);
            Class_0982.field_3026.setMaxLength(32);
            Class_0982.field_3026.setDrawsBackground(false);
            Class_0982.field_3026.setChangedListener(var0 -> Class_0982.text = var0);
            Screens.getButtons(var2).add(Class_0982.field_3026);
         }
      }
   }
}
