package me.mioclient.module.abstract_;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.List;
import me.mioclient.Hub;
import me.mioclient.event.Event_1;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0149;
import me.mioclient.internal.Class_1081;
import me.mioclient.setting.CustomSetting3;
import me.mioclient.setting.Setting;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferRenderer;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.VertexFormat.DrawMode;
import net.minecraft.util.math.MathHelper;

public class AbstractModule_5 extends AbstractModule_26 {
   public final Setting<Float> field_2656 = this.add(
      new CustomSetting3<>("Ceil", Float.intBitsToFloat(1104150528), Float.intBitsToFloat(1092616192), Float.intBitsToFloat(1120403456))
   );
   public final Setting<Integer> field_2657 = this.add(new CustomSetting3<>("Height", 30, 10, 40));
   public final Setting<Integer> field_2658 = this.add(new CustomSetting3<>("Width", 100, 50, 150));
   public final List<Double> field_2659 = new ArrayList<>();

   public AbstractModule_5() {
      super("Graph");
      this.method_2(new Class_0149(this));
   }

   @Override
   public void onEnable() {
      this.field_2659.clear();

      for (int var1 = 0; var1 < 150; var1++) {
         this.field_2659.add(0.0);
      }
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      this.field_2659.add(Hub.field_2614.method_875());

      while (this.field_2659.size() > this.field_2658.getValue()) {
         this.field_2659.remove(0);
      }
   }

   @Override
   public void method_2(DrawContext var1) {
      Tessellator var2 = Tessellator.getInstance();
      RenderSystem.setShader(GameRenderer::getPositionColorProgram);
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      BufferBuilder var3 = var2.begin(DrawMode.DEBUG_LINE_STRIP, VertexFormats.POSITION_COLOR);
      int var4 = 0;

      for (double var6 : this.field_2659) {
         float var8 = Float.intBitsToFloat(1065353216)
            + (float)(
               (double)(this.field_2657.getValue() - 2)
                  * (
                     Double.longBitsToDouble(4607182418800017408L)
                        - Math.min(var6, (double)(this.field_2656.getValue() != null ? this.field_2656.getValue().floatValue() : 0.0f)) / (double)(this.field_2656.getValue() != null ? this.field_2656.getValue().floatValue() : 0.0f)
                  )
            );
         var3.vertex(var1.getMatrices().peek().getPositionMatrix(), (float)var4, var8, 0.0F)
            .color(Class_1081.method_9(this.method_9(var8 + this.field_1844.method_60()), this.method_310(var4)).hashCode());
         var4++;
      }

      BufferRenderer.drawWithGlobalProgram(var3.end());
      RenderSystem.disableBlend();
   }

   public int method_310(int var1) {
      if (var1 <= 10) {
         return (int)MathHelper.clamp((float)var1 / Float.intBitsToFloat(1092616192) * Float.intBitsToFloat(1132396544), 0.0F, Float.intBitsToFloat(1132396544));
      } else {
         return var1 >= this.field_2658.getValue() - 10
            ? (int)MathHelper.clamp(
               (Float.intBitsToFloat(1065353216) - (float)(var1 - this.field_2658.getValue() + 10) / Float.intBitsToFloat(1092616192))
                  * Float.intBitsToFloat(1132396544),
               0.0F,
               Float.intBitsToFloat(1132396544)
            )
            : 255;
      }
   }

   @Override
   public float[] method_31() {
      return new float[]{(float)(this.field_2658.getValue() != null ? this.field_2658.getValue().intValue() : 0), (float)(this.field_2657.getValue() != null ? this.field_2657.getValue().intValue() : 0)};
   }
}
