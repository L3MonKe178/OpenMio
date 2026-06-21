package me.mioclient.internal;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import me.mioclient.Hub;
import me.mioclient.api.MioAPI;
import me.mioclient.enum_.Class_0655;
import me.mioclient.enum_.Class_1200;
import me.mioclient.module.client.UIModule;
import me.mioclient.module.render.BlurModule;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;

public class Class_1117 extends Screen implements MioAPI {
   public static BlurModule field_3459 = Hub.field_2595.method_78(BlurModule.class);
   public static Class_1200 field_3460 = Class_1200.STANDARD;
   public static final int field_3461 = 25;
   public final ArrayList<Class_0746> field_3462 = new ArrayList<>();
   public final List<Class_0325> field_3463 = new ArrayList<>();
   public final Class_0031 field_3464 = new Class_0031(Float.intBitsToFloat(1075838976), true);
   public final Class_0031 field_3465 = new Class_0031(Float.intBitsToFloat(1075838976), true);
   public boolean field_2383 = false;
   public float field_1096;
   public boolean field_3466;
   public String field_3467 = "";
   public long field_3468 = 0L;
   public long field_3469;
   public Screen field_2898;
   public boolean field_3470;

   public Class_1117() {
      super(Text.literal("mio"));
      this.method_996();
   }

   public void init() {
      int var1 = field_4219.getWindow().getScaledWidth();
      int var2 = field_4219.getWindow().getScaledHeight();
      if (this.method_830() != null) {
         this.method_830().init(field_4219, var1, var2);
      }

      if (!this.method_535()) {
         this.field_3464.method_36((float)var1 / Float.intBitsToFloat(1073741824));
         this.field_3465.method_36((float)var2 / Float.intBitsToFloat(1073741824));
      }

      this.field_2383 = false;
      this.method_999().forEach(var0 -> var0.method_157(false));
      this.field_1096 = UIModule.field_2843.field_2855.getValue();
      this.field_3466 = false;
      this.field_3469 = System.currentTimeMillis();

      for (Class_0746 var4 : this.field_3462) {
         var4.init();
      }
   }

   public void render(DrawContext context, int mouseX, int mouseY, float delta) {
      this.field_3464.method_3((float)mouseX);
      this.field_3465.method_3((float)mouseY);
      float var5 = this.field_3464.method_45();
      float var6 = this.field_3465.method_45();
      if (this.method_830() instanceof TitleScreen || this.method_830() instanceof MultiplayerScreen) {
         GlStateManager._enablePolygonOffset();
         GlStateManager._polygonOffset(Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1148846080));
         this.method_830().render(context, -99, -99, delta);
         field_4219.getBufferBuilders().getEntityVertexConsumers().draw();
         context.draw();
         GlStateManager._polygonOffset(Float.intBitsToFloat(1065353216), Float.intBitsToFloat(-998637568));
         GlStateManager._disablePolygonOffset();
      }

      field_3460.method_580();
      field_3460 = Class_1200.STANDARD;
      this.field_3463.removeIf(Class_0325::method_381);
      if (this.field_3463.size() < 25) {
         this.field_3463.add(new Class_0325());
      }

      RenderSystem.setShaderColor(Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216), this.method_937());
      float var7 = (float)Class_1067.method_78(this.field_1096);
      Class_1067.method_38(this.field_1096);
      context.getMatrices().scale(var7, var7, Float.intBitsToFloat(1065353216));
      mouseX = (int)((float)mouseX / var7);
      mouseY = (int)((float)mouseY / var7);
      var5 /= var7;
      var6 /= var7;
      this.field_3467 = "";
      if (!this.method_1001() && field_3459.isToggled()) {
         Class_1299.method_2(() -> {
            for (Class_0746 var3 : this.method_999()) {
               float var4 = var3.method_715();
               float var5x = (float)(var3.method_66() - 14) * var4;
               context.fill(var3.getX(), var3.getY(), var3.getX() + var3.method_216(), (int)((float)(var3.getY() + 14) + var5x), -1);
            }
         }, (float)field_3459.method_730());
      }

      this.method_38(context);
      if (Class_0655.WINTER.method_29(LocalDate.now().getMonthValue())) {
         for (Class_0325 var9 : this.field_3463) {
            var9.method_2(context);
         }
      }

      for (Class_0746 var27 : this.field_3462) {
         var27.method_22(mouseX, mouseY);
         var27.method_2(context, context.getMatrices(), (double)mouseX, (double)mouseY);
      }

      if (!this.field_3467.isBlank()) {
         Class_0745.method_474();
         String[] var26 = this.field_3467.split("\n");
         float var28 = 0.0F;

         for (String var13 : var26) {
            float var14 = FontRenderer.field_3143.method_221(var13);
            if (var14 > var28) {
               var28 = var14;
            }
         }

         float var29 = var5 + Float.intBitsToFloat(1091567616);
         float var30 = var5 + var28 + Float.intBitsToFloat(1092616192);
         if (var30 > (float)field_4219.getWindow().getScaledWidth()) {
            var29 = var5 - var28 - Float.intBitsToFloat(1073741824);
            var30 = var5 - Float.intBitsToFloat(1065353216);
         }

         float var31 = var29;
         float var32 = var30;
         int var33 = (int)var6;
         float var15 = var6
            - Float.intBitsToFloat(1065353216)
            + ((float)FontRenderer.field_3143.method_66() + Float.intBitsToFloat(1065353216)) * (float)var26.length;
         Class_1299.method_2(
            () -> RenderUtil.field_2672
                  .method_2(context.getMatrices(), var31, (float)var33 - Float.intBitsToFloat(1065353216), var32, var15, Class_1081.method_2(10, 10, 10, 140)),
            Float.intBitsToFloat(1086324736)
         );
         RenderUtil.field_2672.method_9(context.getMatrices(), var29, var6 - Float.intBitsToFloat(1065353216), var30, var15, new Color(10, 10, 10, 80));
         int var16 = 0;

         for (String var20 : var26) {
            FontRenderer.field_3143
               .method_9(
                  context,
                  var20,
                  var29 + Float.intBitsToFloat(1065353216),
                  var6 + ((float)FontRenderer.field_3143.method_66() + Float.intBitsToFloat(1065353216)) * (float)var16,
                  Color.white
               );
            var16++;
         }

         this.field_3468 = System.currentTimeMillis();
      } else if (!UIModule.field_2843.field_2850.getValue() && this.field_3468 != 0L && System.currentTimeMillis() > this.field_3468 + 750L) {
         this.field_3468 = 0L;
         Class_0050.method_69();
      }

      this.method_2(context, mouseX, mouseY, delta);
      Class_0745.method_474();
      RenderSystem.setShaderColor(
         Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216)
      );
      Class_1067.method_159();
   }

   public boolean mouseClicked(double mouseX, double mouseY, int button) {
      if (this.field_3466) {
         return false;
      } else {
         mouseX /= Class_1067.method_78(this.field_1096);
         mouseY /= Class_1067.method_78(this.field_1096);
         boolean var6 = super.mouseClicked(mouseX, mouseY, button);
         Class_0746 var7 = null;

         for (int var8 = this.field_3462.size() - 1; var8 >= 0; var8--) {
            Class_0746 var9 = this.field_3462.get(var8);
            if (var9.method_78(mouseX, mouseY) && button == 0 && var7 == null) {
               var7 = var9;
               var9.method_157(true);
               var9.field_2381 = (int)(mouseX - (double)var9.getX());
               var9.field_2382 = (int)(mouseY - (double)var9.getY());
            }

            var9.method_2(mouseX, mouseY, button);
         }

         if (var7 != null) {
            this.field_3462.remove(var7);
            this.field_3462.add(var7);
         }

         this.reset();
         this.method_5(mouseX, mouseY, button);
         return var6;
      }
   }

   public boolean mouseReleased(double mouseX, double mouseY, int button) {
      mouseX /= Class_1067.method_78(this.field_1096);
      mouseY /= Class_1067.method_78(this.field_1096);

      for (int var6 = this.field_3462.size() - 1; var6 >= 0; var6--) {
         Class_0746 var7 = this.field_3462.get(var6);
         var7.method_157(false);
         var7.method_9(mouseX, mouseY, button);
      }

      this.method_7(mouseX, mouseY, button);
      return super.mouseReleased(mouseX, mouseY, button);
   }

   public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
      double var9 = Class_1067.method_78(this.field_1096);
      mouseX /= var9;
      mouseY /= var9;
      int var11 = 2 + FontRenderer.field_3143.method_66() + UIModule.field_2843.field_2851.getValue();
      int var12 = this.field_3462.stream().mapToInt(Class_0746::method_66).max().orElse(var11);
      if ((double)var12 > (double)(field_4219.getWindow().getScaledHeight() + 5) / var9) {
         var12 -= (int)((double)(field_4219.getWindow().getScaledHeight() - 5) / var9);
      } else {
         var12 = (int)(Double.longBitsToDouble(-4606056518893174784L) / var9);
      }

      if (verticalAmount != 0.0) {
         int var13 = (int)(verticalAmount * (double)var11);

         for (Class_0746 var15 : this.field_3462) {
            if (!var15.method_192()) {
               var15.method_7(mouseX, mouseY, verticalAmount);
               int var16 = var15.getY() + var13;
               if (this.method_631()) {
                  var16 = MathHelper.clamp(var16, -var12, 5);
               }

               var15.setY(var16);
            }
         }
      }

      this.method_29(mouseX, mouseY, verticalAmount);
      return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
   }

   public boolean charTyped(char chr, int modifiers) {
      for (int var3 = this.field_3462.size() - 1; var3 >= 0; var3--) {
         this.field_3462.get(var3).method_9(chr);
      }

      return super.charTyped(chr, modifiers);
   }

   public void tick() {
      if (this.field_3466 && System.currentTimeMillis() >= this.field_3469 + 150L) {
         for (Class_0746 var2 : this.field_3462) {
            var2.method_157(false);
         }

         if (this.field_2898 != null) {
            field_4219.setScreen(this.field_2898);
         } else {
            this.close();
         }

         this.method_5(null);
      }
   }

   public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
      this.field_3470 = true;

      for (int var4 = this.field_3462.size() - 1; var4 >= 0; var4--) {
         this.field_3462.get(var4).method_68(keyCode);
      }

      if (keyCode == 256 && this.field_3470) {
         this.method_257();
      }

      return false;
   }

   public boolean shouldPause() {
      return false;
   }

   public void method_2(DrawContext var1, int var2, int var3, float var4) {
   }

   public void method_5(double var1, double var3, int var5) {
   }

   public void method_7(double var1, double var3, int var5) {
   }

   public void method_29(double var1, double var3, double var5) {
   }

   public void close() {
      super.close();
      Class_1200.STANDARD.method_580();
   }

   public void reset() {
      Class_0050.method_69();
      this.field_3468 = 0L;
   }

   public void method_996() {
      this.field_3463.clear();

      for (int var1 = 0; var1 < 25; var1++) {
         this.field_3463.add(new Class_0325());
      }
   }

   public float method_937() {
      float var1 = (float)(System.currentTimeMillis() - this.field_3469) / Float.intBitsToFloat(1125515264);
      if (this.field_3466) {
         var1 = Float.intBitsToFloat(1065353216) - var1;
      }

      return MathHelper.clamp(var1, 0.0F, Float.intBitsToFloat(1065353216));
   }

   public void method_997() {
      this.method_996();
      this.field_3462.sort(Comparator.comparing(var0 -> var0 instanceof Class_0967 var1x ? var1x.field_37.ordinal() : 100));
      int var1 = 10;

      for (Class_0746 var3 : this.field_3462) {
         var3.setX(var1);
         var1 += var3.method_216() + 3;
      }
   }

   public void method_38(DrawContext var1) {
      var1.fill(0, 0, field_4219.getWindow().getScaledWidth(), field_4219.getWindow().getScaledHeight(), UIModule.field_2843.field_2883.getValue().hashCode());
      var1.fillGradient(
         0,
         0,
         field_4219.getWindow().getScaledWidth(),
         field_4219.getWindow().getScaledHeight(),
         Class_1081.method_5(UIModule.field_2843.field_2884.getValue(), 0),
         UIModule.field_2843.field_2884.getValue().hashCode()
      );
   }

   public void method_257() {
      if (!this.field_3466) {
         this.field_2383 = false;
         this.field_3469 = System.currentTimeMillis();
         if (UIModule.field_2843.isToggled()) {
            UIModule.field_2843.method_68();
         }
      } else {
         this.close();
      }

      this.field_3466 = true;
   }

   public void method_998() {
      this.field_3468 = System.currentTimeMillis();
   }

   public void method_316(String var1) {
      this.field_3467 = var1;
   }

   public ArrayList<Class_0746> method_999() {
      return this.field_3462;
   }

   public boolean method_1000() {
      return this.field_2383;
   }

   public void method_294(boolean var1) {
      this.field_2383 = var1;
   }

   public boolean method_1001() {
      return this.field_3466;
   }

   public void method_1002() {
      if (field_4219.currentScreen instanceof Class_1117 var1) {
         this.method_5(var1.field_2898);
      } else {
         this.method_5(field_4219.currentScreen);
      }

      field_4219.setScreen(this);
   }

   public boolean method_631() {
      return true;
   }

   public void method_5(Screen var1) {
      this.field_2898 = var1;
   }

   public Screen method_830() {
      return this.field_2898;
   }

   public void method_1003() {
      this.field_3470 = false;
   }
}
