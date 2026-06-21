package me.mioclient.internal;

import java.awt.Color;
import me.mioclient.Hub;
import me.mioclient.module.client.UIModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.MathHelper;

public class Class_0325 {
   public static final String field_1094 = "❆";
   public static final float field_1095 = Float.intBitsToFloat(1023879938);
   public final float field_1086 = (float)MathHelper.clamp(
      Math.random(), Double.longBitsToDouble(4589708452267294720L), Double.longBitsToDouble(4595653204011646976L)
   );
   public final float field_1096 = Class_0930.method_2((float)Class_0245.field_688, Float.intBitsToFloat(1066611507));
   public final double field_608 = Math.random();
   public final double field_1097 = (double)Class_0930.method_2(Float.intBitsToFloat(-1123603710), Float.intBitsToFloat(1023879938));
   public float field_877 = Class_0930.method_2(Float.intBitsToFloat(-1092196762), 0.0F);

   public Class_0325() {
      super();
   }

   public void method_2(DrawContext var1) {
      TextRenderer var2 = MinecraftClient.getInstance().textRenderer;
      double var3 = this.field_608 + (double)this.field_877 * this.field_1097;
      float var5 = (float)(var3 * (double)var1.getScaledWindowWidth());
      float var6 = this.field_877 * (float)var1.getScaledWindowHeight() * Float.intBitsToFloat(1056964608)
         - Float.intBitsToFloat(1091567616)
         - Float.intBitsToFloat(1065353216);
      this.field_877 = this.field_877 + Hub.field_2601.method_154(Float.intBitsToFloat(1056964608)) * this.field_1086;
      this.field_877 = Math.min(this.field_877, Float.intBitsToFloat(1065353216));
      if (UIModule.field_2843.field_2856.getValue() && this.field_877 >= 0.0F) {
         int var7 = MathHelper.ceil(
            Math.max(Float.intBitsToFloat(1132396544) - this.field_877 * Float.intBitsToFloat(1132396544), Float.intBitsToFloat(1082130432))
         );
         var1.getMatrices().push();
         var1.getMatrices().scale(this.field_1096, this.field_1096, Float.intBitsToFloat(1065353216));
         var1.getMatrices().translate(var5 / this.field_1096, var6 / this.field_1096, 0.0F);
         var1.drawCenteredTextWithShadow(var2, "❆", 0, 0, new Color(255, 255, 255, var7).hashCode());
         var1.getMatrices().pop();
      }
   }

   public double method_380() {
      return this.field_608;
   }

   public boolean method_381() {
      return this.field_877 >= Float.intBitsToFloat(1065353216);
   }
}
