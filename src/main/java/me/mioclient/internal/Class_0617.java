package me.mioclient.internal;

import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import me.mioclient.Hub;
import me.mioclient.api.MioAPI;
import me.mioclient.enum_.Class_0274;
import me.mioclient.module.Category;
import me.mioclient.record.Class_0123;
import me.mioclient.record.Class_0362;
import net.minecraft.client.gui.DrawContext;

public class Class_0617 extends Class_1117 implements MioAPI {
   public static final List<Class_0535> field_1950 = Collections.synchronizedList(new ArrayList<>());
   public static final Class_0255 field_1951 = new Class_0255();
   public final Class_0051 field_1952 = new Class_0051();
   public final Class_0585 field_1953 = new Class_0585(Float.intBitsToFloat(1073741824), true);
   public boolean field_470;
   public float field_1954;
   public float field_1955;
   public float field_1956;
   public float field_1957;

   public Class_0617() {
      super();

      for (Class_0274 var4 : Class_0274.values()) {
         field_1950.add(new Class_0535(var4));
      }

      Class_0967 var5 = new Class_0967(Category.HUD);
      var5.method_714().sort(Comparator.comparing(var0 -> var0 instanceof Class_0050 var1 ? var1.method_65().getName() : ""));
      var5.method_216();
      var5.setX(85);
      var5.setY(10);
      this.field_3462.add(var5);
   }

   @Override
   public void render(DrawContext context, int mouseX, int mouseY, float delta) {
      RenderSystem.setShaderColor(Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216), this.method_937());
      field_1951.method_220(false);
      this.field_1956 = (float)mouseX;
      this.field_1957 = (float)mouseY;
      float var5 = (float)context.getScaledWindowWidth() / Float.intBitsToFloat(1073741824);
      float var6 = (float)context.getScaledWindowHeight() / Float.intBitsToFloat(1073741824);
      Color var7 = Class_1081.method_2(Color.white, RenderSystem.getShaderColor()[3] * Float.intBitsToFloat(1053609165));
      Class_0745.method_4(
         context.getMatrices(),
         var5 - Float.intBitsToFloat(1056964608),
         0.0F,
         var5 + Float.intBitsToFloat(1056964608),
         var6 * Float.intBitsToFloat(1073741824),
         var7
      );
      Class_0745.method_4(
         context.getMatrices(),
         0.0F,
         var6 - Float.intBitsToFloat(1056964608),
         var5 - Float.intBitsToFloat(1056964608),
         var6 + Float.intBitsToFloat(1056964608),
         var7
      );
      Class_0745.method_4(
         context.getMatrices(),
         var5 + Float.intBitsToFloat(1056964608),
         var6 - Float.intBitsToFloat(1056964608),
         var5 * Float.intBitsToFloat(1073741824),
         var6 + Float.intBitsToFloat(1056964608),
         var7
      );
      this.field_1952.method_9(context, mouseX, mouseY, delta);
      this.method_2(context, (double)mouseX, (double)mouseY);
      if (this.field_470) {
         Class_0535 var8 = this.method_5(Class_0274.NONE);
         Class_0158 var9 = var8.method_566().method_606();
         float[] var10 = var9.method_193();
         var9.method_2(Math.max(0.0F, Math.min((float)context.getScaledWindowWidth() - var10[0], (float)mouseX - this.field_1954)), true);
         var9.method_9(Math.max(0.0F, Math.min((float)context.getScaledWindowHeight() - var10[1], (float)mouseY - this.field_1955)), true);
         var9.method_197();
         if (Math.abs(var9.method_59() + var10[0] / Float.intBitsToFloat(1073741824) - var5) <= Float.intBitsToFloat(1084227584)) {
            float var11 = var5 - var10[0] / Float.intBitsToFloat(1073741824);
            var9.method_2(var11, true);
            this.method_2(context, (double)var11 - Double.longBitsToDouble(4602678819172646912L), true);
            this.method_2(context, (double)(var11 + var10[0]), true);
         }

         if (Math.abs(var9.method_60() + var10[1] / Float.intBitsToFloat(1073741824) - var6) <= Float.intBitsToFloat(1084227584)) {
            float var13 = var6 - var10[1] / Float.intBitsToFloat(1073741824);
            var9.method_9(var13, true);
            this.method_2(context, (double)var13 - Double.longBitsToDouble(4602678819172646912L), false);
            this.method_2(context, (double)(var13 + var10[1]), false);
         }
      }

      float var12 = this.field_1953.method_45();
      this.field_1953.method_3(field_1951.method_290());
      if (field_1951.method_290() || (double)var12 > Double.longBitsToDouble(4591870180066957722L)) {
         FontRenderer.field_3143
            .method_9(
               context,
               field_1951.method_286(),
               field_1951.method_59() + Float.intBitsToFloat(1092616192) * (Float.intBitsToFloat(1065353216) - var12) * (float)field_1951.method_288(),
               field_1951.method_60(),
               Class_1081.method_9(Color.white, (int)(var12 * Float.intBitsToFloat(1132396544)))
            );
      }

      super.render(context, mouseX, mouseY, delta);
   }

   @Override
   public boolean mouseClicked(double mouseX, double mouseY, int button) {
      Class_0123 var6 = null;

      for (Class_0535 var8 : field_1950) {
         for (int var9 = var8.method_566().size() - 1; var9 >= 0; var9--) {
            Class_0158 var10 = var8.method_566().get(var9);
            if (var10.method_176() && button == 0 && var6 == null) {
               if (var10 instanceof Class_0149) {
                  Class_0149 var11 = (Class_0149)var10;
                  if (var11.method_39(mouseX, mouseY)) {
                     continue;
                  }
               }

               if (var10.method_5(mouseX, mouseY)) {
                  var6 = new Class_0123<>(var8.method_3(), var10);
                  this.field_1954 = (float)((int)(mouseX - (double)var10.method_59()));
                  this.field_1955 = (float)((int)(mouseY - (double)var10.method_60()));
                  this.field_470 = true;
                  var10.method_6(true);
               }
            }
         }
      }

      if (var6 != null) {
         this.method_5((Class_0274)var6.method_144()).method_566().remove(var6.method_145());
         this.method_5(Class_0274.NONE).method_566().addLast((Class_0158)var6.method_145());
         ((Class_0158)var6.method_145()).method_2(Class_0274.NONE);
      } else {
         this.field_1952.method_2(mouseX, mouseY, button);
      }

      return super.mouseClicked(mouseX, mouseY, button);
   }

   @Override
   public boolean mouseReleased(double mouseX, double mouseY, int button) {
      this.field_1952.method_9(mouseX, mouseY, button);
      if (this.field_470) {
         this.field_470 = false;
         Class_0535 var6 = this.method_5(Class_0274.NONE);
         Class_0158 var7 = var6.method_566().method_606();
         var7.method_6(false);
         var6.method_566().remove(var7);

         for (Class_0535 var9 : field_1950) {
            for (Class_0158 var11 : var9.method_566()) {
               if (var11.method_198().method_2(var7.method_198())) {
                  this.method_5(var9.method_3()).method_566().addLast(var7);
                  var7.method_2(var9.method_3());
                  return true;
               }
            }
         }

         for (Class_0274 var15 : Class_0274.values()) {
            if (var15.method_198().method_2(var7.method_198().method_22(Float.intBitsToFloat(1084227584)))) {
               this.method_5(var15).method_566().addLast(var7);
               var7.method_2(var15);
               return true;
            }
         }
      }

      return super.mouseReleased(mouseX, mouseY, button);
   }

   @Override
   public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
      boolean var4 = false;

      for (Class_0746 var6 : this.field_3462) {
         if (var6.method_5((double)this.field_1956, (double)this.field_1957)) {
            var4 = true;
            break;
         }
      }

      if (!var4) {
         Class_0158 var7 = this.method_630();
         Class_0362 var8 = this.method_632(keyCode);
         if (var8 != null && var7 != null && var7.method_176()) {
            var7.method_2(var7.method_59() + (float)var8.method_401(), true);
            var7.method_9(var7.method_60() + (float)var8.method_402(), true);
            var7.method_197();
         } else {
            switch (keyCode) {
               case 73:
                  if (!(Hub.method_755().method_454() instanceof Class_0872)) {
                     this.method_2(Class_0274.BOTTOM_LEFT, Class_0274.BOTTOM_RIGHT);
                     this.method_2(Class_0274.TOP_LEFT, Class_0274.TOP_RIGHT);
                  }
                  break;
               case 259:
               case 261:
                  if (var7 != null && var7.method_176()) {
                     var7.method_199().method_38(false);
                  }
            }
         }
      }

      return super.keyPressed(keyCode, scanCode, modifiers);
   }

   @Override
   public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
      return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
   }

   @Override
   public void close() {
      super.close();
      if (this.field_470) {
         this.method_5(Class_0274.NONE).method_566().method_606().method_6(false);
         this.field_470 = false;
      }
   }

   public void method_2(Class_0274 var1, Class_0158 var2) {
      this.method_5(var1).method_566().add(var2);
   }

   public void method_9(Class_0158 var1) {
      this.method_2(Class_0274.NONE, var1);
   }

   public void method_5(Class_0158 var1) {
      for (Class_0535 var3 : this.method_629()) {
         if (var3.method_566().remove(var1)) {
            return;
         }
      }
   }

   public List<Class_0535> method_629() {
      return field_1950;
   }

   public void method_2(DrawContext var1, double var2, double var4) {
      for (Class_0535 var7 : this.method_629()) {
         var7.method_2(var1, var1.getMatrices(), var2, var4);
      }

      Class_0745.method_474();
   }

   public Class_0535 method_5(Class_0274 var1) {
      for (Class_0535 var3 : this.method_629()) {
         if (var3.method_3() == var1) {
            return var3;
         }
      }

      return null;
   }

   public void method_2(Class_0274 var1, Class_0274 var2) {
      Class_0535 var3 = this.method_5(var1);
      Class_0535 var4 = this.method_5(var2);
      ArrayList var5 = new ArrayList<>(var3.method_566());
      var3.method_566().clear();

      for (Class_0158 var7 : var4.method_566()) {
         var3.method_566().add(var7);
      }

      var4.method_566().clear();

      for (Class_0158 var9 : (Iterable<Class_0158>)(Iterable<?>)(var5)) {
         var4.method_566().add(var9);
      }
   }

   public Class_0158 method_630() {
      for (Class_0535 var2 : field_1950) {
         for (Class_0158 var4 : var2.method_566()) {
            if (var4.method_5((double)this.field_1956, (double)this.field_1957)) {
               return var4;
            }
         }
      }

      return null;
   }

   public void method_2(DrawContext var1, double var2, boolean var4) {
      float var5 = (float)var2;
      if (var4) {
         Class_0745.method_4(var1.getMatrices(), var5, 0.0F, var5 + Float.intBitsToFloat(1056964608), (float)var1.getScaledWindowHeight(), Color.yellow);
      } else {
         Class_0745.method_4(var1.getMatrices(), 0.0F, var5, (float)var1.getScaledWindowWidth(), var5 + Float.intBitsToFloat(1056964608), Color.yellow);
      }
   }

   @Override
   public void method_38(DrawContext var1) {
   }

   @Override
   public boolean method_631() {
      return false;
   }

   public Class_0362 method_632(int var1) {
      return switch (var1) {
         case 262 -> new Class_0362(1, 0);
         case 263 -> new Class_0362(-1, 0);
         case 264 -> new Class_0362(0, 1);
         case 265 -> new Class_0362(0, -1);
         default -> null;
      };
   }
}
