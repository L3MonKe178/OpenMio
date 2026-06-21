package me.mioclient.record;

import com.mojang.blaze3d.systems.RenderSystem;
import me.mioclient.api.Class_1309;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.VertexConsumerProvider.Immediate;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.type.MapIdComponent;
import net.minecraft.item.map.MapState;
import net.minecraft.util.Identifier;

public final class Class_1356 implements Class_1309, TooltipComponent {
   public final int field_4423;
   public final MapState field_4424;
   public static final Identifier field_4425 = Identifier.of("textures/map/map_background.png");

   public Class_1356(int var1, MapState var2) {
      super();
      this.field_4423 = var1;
      this.field_4424 = var2;
   }

   public int getHeight() {
      return 110;
   }

   public int getWidth(TextRenderer textRenderer) {
      return 108;
   }

   public void drawItems(TextRenderer textRenderer, int x, int y, DrawContext context) {
      MatrixStack var5 = context.getMatrices();
      var5.push();
      var5.translate((float)x, (float)y, 0.0F);
      var5.scale(Float.intBitsToFloat(1069547520), Float.intBitsToFloat(1069547520), 0.0F);
      var5.scale(Float.intBitsToFloat(1066401792), Float.intBitsToFloat(1066401792), 0.0F);
      RenderSystem.setShader(GameRenderer::getPositionTexProgram);
      context.drawTexture(field_4425, 0, 0, 0, 0.0F, 0.0F, 64, 64, 64, 64);
      var5.pop();
      Immediate var6 = field_4219.getBufferBuilders().getEntityVertexConsumers();
      var5.push();
      var5.translate((float)x, (float)y, 0.0F);
      var5.scale(Float.intBitsToFloat(1061158912), Float.intBitsToFloat(1061158912), 0.0F);
      var5.translate(Float.intBitsToFloat(1090519040), Float.intBitsToFloat(1090519040), 0.0F);
      field_4219.gameRenderer.getMapRenderer().draw(var5, var6, new MapIdComponent(this.field_4423), this.field_4424, false, 15728880);
      var6.draw();
      var5.pop();
   }

   public int method_684() {
      return this.field_4423;
   }

   public MapState method_1189() {
      return this.field_4424;
   }
}
