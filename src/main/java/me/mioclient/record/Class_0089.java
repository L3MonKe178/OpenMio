package me.mioclient.record;

import java.util.List;
import me.mioclient.Hub;
import me.mioclient.api.MioAPI;
import me.mioclient.event.Event_41;
import me.mioclient.internal.Class_0018;
import me.mioclient.internal.TextBuilder;
import me.mioclient.module.abstract_.AbstractModule_37;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.render.VertexConsumerProvider.Immediate;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.MapIdComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.map.MapState;
import net.minecraft.util.Formatting;

public final class Class_0089 implements MioAPI, TooltipComponent {
   public final List<ItemStack> field_298;
   public static final AbstractModule_37 field_299 = Hub.field_2595.method_78(AbstractModule_37.class);

   public Class_0089(List<ItemStack> var1) {
      super();
      this.field_298 = var1;
   }

   public int getHeight() {
      return 53;
   }

   public int getWidth(TextRenderer textRenderer) {
      return 155;
   }

   public void drawItems(TextRenderer textRenderer, int x, int y, DrawContext context) {
      int var5 = 0;
      int var6 = 0;
      int var7 = Event_41.field_1663;
      int var8 = Event_41.field_1664;
      ItemStack var9 = null;

      for (ItemStack var11 : this.field_298) {
         int var12 = x + 17 * var5;
         int var13 = y + 17 * var6;
         boolean var14 = var11.getCount() == 69;
         if (var7 >= var12 && var7 <= var12 + 17 && var8 >= var13 && var8 <= var13 + 17 && var9 == null) {
            var9 = var11;
         }

         int var15 = ((MapIdComponent)var11.getOrDefault(DataComponentTypes.MAP_ID, new MapIdComponent(-1))).id();
         MapState var16 = Hub.field_2624.method_9(var11, var15);
         if (field_299.field_1189.getValue() && var15 != -1 && var16 != null) {
            method_2(context.getMatrices(), var16, var15, (float)(var12 + 1), (float)(var13 + 1));
            if (!Class_0018.method_22(342)) {
               context.drawItemInSlot(textRenderer, var11, var12 + 1, var13 + 1);
            }
         } else {
            context.drawItem(var11, var12 + 1, var13 + 1);
            context.drawItemInSlot(
               textRenderer, var11, var12 + 1, var13 + 1, var14 ? new TextBuilder().method_2(String.valueOf(Formatting.RED)).method_9("\u00010") : null
            );
         }

         if (++var5 >= 9) {
            var5 = 0;
            var6++;
         }
      }

      if (var9 != null && !var9.isOf(Items.AIR)) {
         context.drawItemTooltip(field_4219.textRenderer, var9, var7, var8);
      }
   }

   public static void method_2(MatrixStack var0, MapState var1, int var2, float var3, float var4) {
      if (var1 != null) {
         Immediate var5 = field_4219.getBufferBuilders().getEntityVertexConsumers();
         var0.push();
         var0.translate(var3, var4, 0.0F);
         var0.scale(Float.intBitsToFloat(1040187392), Float.intBitsToFloat(1040187392), 0.0F);
         var0.translate(0.0F, 0.0F, 0.0F);
         field_4219.gameRenderer.getMapRenderer().draw(var0, var5, new MapIdComponent(var2), var1, false, 15728880);
         var5.draw();
         var0.pop();
      }
   }

   public List<ItemStack> method_122() {
      return this.field_298;
   }
}
