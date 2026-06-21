package me.mioclient.internal;

import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import me.mioclient.Hub;
import me.mioclient.module.abstract_.AbstractModule_26;
import me.mioclient.setting.CustomSetting3;
import me.mioclient.setting.Setting;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class Class_0149 extends Class_0158 {
   public static final Identifier field_430 = Identifier.of("mio", "textures/triangle.png");
   public static Class_0149 field_431;
   public final Setting<Float> field_432 = new CustomSetting3<>(
      "Size", Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1056964608), Float.intBitsToFloat(1073741824)
   );
   public final Class_0055 field_433 = new Class_0055();
   public boolean field_434;

   public Class_0149(AbstractModule_26 var1) {
      super(var1);
   }

   @Override
   public void method_2(DrawContext var1, MatrixStack var2, double var3, double var5) {
      super.method_2(var1, var2, var3, var5);
      float[] var7 = this.field_461.method_31();
      var2.push();
      if (this.field_434) {
         var2.scale(this.method_174(), this.method_174(), Float.intBitsToFloat(1065353216));
         var2.translate(this.method_59() / this.method_174(), this.method_60() / this.method_174(), 0.0F);
      } else {
         var2.translate(this.method_59(), this.method_60(), 0.0F);
      }

      this.field_461.method_2(var1);
      if (!field_460 && this.field_434) {
         boolean var8 = this.method_39(var3, var5);
         if (field_431 == this) {
            double var9 = (double)var7[0];
            double var11 = (double)var7[1];
            double var13 = Math.min((var3 - (double)this.method_59()) / var9, (var5 - (double)this.method_60()) / var11);
            this.field_432.method_78((float)Class_0930.method_2(var13, 2));
         }

         if (var8 && this.method_175(1)) {
            this.field_432.method_78(Float.intBitsToFloat(1065353216));
         }

         if (var8 && this.method_175(0)) {
            field_431 = this;
         } else if (!this.method_175(0)) {
            field_431 = null;
         }

         if (field_431 == this) {
            this.field_433.method_2(Float.intBitsToFloat(1063675494), 250L);
         } else if (var8) {
            this.field_433.method_2(Float.intBitsToFloat(1053609165), 250L);
         } else {
            this.field_433.method_2(Float.intBitsToFloat(1045220557), 250L);
         }

         var2.push();
         float var15 = Float.intBitsToFloat(1017118720);
         var2.scale(var15, var15, Float.intBitsToFloat(1140457472));
         var2.translate((var7[0] - Float.intBitsToFloat(1084227584)) / var15, (var7[1] - Float.intBitsToFloat(1084227584)) / var15, 0.0F);
         Color var10 = Class_1081.method_959();
         RenderSystem.enableBlend();
         RenderSystem.setShaderColor(
            (float)var10.getRed() / Float.intBitsToFloat(1132396544),
            (float)var10.getGreen() / Float.intBitsToFloat(1132396544),
            (float)var10.getBlue() / Float.intBitsToFloat(1132396544),
            this.field_433.method_45() * Hub.method_756().method_937()
         );
         var1.drawTexture(field_430, 0, 0, 0, 0, 256, 256);
         RenderSystem.setShaderColor(
            Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216)
         );
         RenderSystem.disableBlend();
         var2.pop();
      }

      var2.pop();
   }

   @Override
   public float[] method_31() {
      float[] var1 = this.field_461.method_31();
      var1[0] *= this.method_174();
      var1[1] *= this.method_174();
      return var1;
   }

   public void method_2(AbstractModule_26 var1) {
      this.field_434 = true;
      var1.register(this.field_432);
   }

   public float method_174() {
      return this.field_432.getValue();
   }

   public boolean method_39(double var1, double var3) {
      if (!this.field_434) {
         return false;
      } else {
         float[] var5 = this.method_193();
         float var6 = this.method_59() + Math.max(var5[0], Float.intBitsToFloat(1082130432));
         float var7 = this.method_60() + Math.max(var5[1], Float.intBitsToFloat(1082130432));
         return var1 >= (double)(var6 - Float.intBitsToFloat(1084227584))
            && var1 <= (double)var6
            && var3 >= (double)(var7 - Float.intBitsToFloat(1084227584))
            && var3 <= (double)var7;
      }
   }

   public boolean method_175(int var1) {
      return GLFW.glfwGetMouseButton(field_4219.getWindow().getHandle(), var1) == 1;
   }
}
