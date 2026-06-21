package me.mioclient.internal;

import me.mioclient.api.MioAPI;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.ShulkerBoxScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ShulkerBoxScreenHandler;
import net.minecraft.text.Text;

public class Class_0539 extends ShulkerBoxScreen implements MioAPI {
   public final Screen field_1707;

   public Class_0539(ShulkerBoxScreenHandler var1, PlayerInventory var2, Text var3, Screen var4) {
      super(var1, var2, var3);
      this.field_1707 = var4;
   }

   public void render(DrawContext context, int mouseX, int mouseY, float delta) {
      super.render(new Class_1007(context), mouseX, mouseY, delta);
   }

   public boolean mouseClicked(double mouseX, double mouseY, int button) {
      return false;
   }

   public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
      if (keyCode == 256) {
         field_4219.setScreen(this.field_1707);
         return true;
      } else {
         return super.keyPressed(keyCode, scanCode, modifiers);
      }
   }
}
