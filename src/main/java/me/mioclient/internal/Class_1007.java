package me.mioclient.internal;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.Nullable;

public class Class_1007 extends DrawContext {
   public Class_1007(DrawContext var1) {
      super(MinecraftClient.getInstance(), (net.minecraft.client.render.VertexConsumerProvider.Immediate)var1.getVertexConsumers());
   }

   public void drawItemInSlot(TextRenderer textRenderer, ItemStack stack, int x, int y, @Nullable String countOverride) {
      if (stack.getCount() == 69) {
         countOverride = new TextBuilder().method_2(String.valueOf(Formatting.RED)).method_9("\u00010");
      }

      super.drawItemInSlot(textRenderer, stack, x, y, countOverride);
   }
}
