package me.mioclient.internal;

import java.awt.Color;
import me.mioclient.Hub;
import me.mioclient.api.MioAPI;
import me.mioclient.module.client.FontsModule;
import me.mioclient.module.player.NameProtectModule;
import net.minecraft.client.font.TextRenderer.TextLayerType;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public class FontRenderer implements MioAPI {
   public static final NameProtectModule nameprotect = Hub.field_2595.method_78(NameProtectModule.class);
   public static final FontRenderer field_3143 = new FontRenderer();
   public static int field_3144 = 0;
   public Class_0436 field_3145;

   public FontRenderer() {
      super();
   }

   public static String method_3(Object var0) {
      return Character.toUpperCase(var0.toString().charAt(0)) + var0.toString().toLowerCase().substring(1);
   }

   public static boolean method_2(String var0, char var1) {
      for (int var2 = 0; var2 < var0.length(); var2++) {
         if (var0.charAt(var2) == var1) {
            return true;
         }
      }

      return false;
   }

   public Class_0436 method_914() {
      if (this.field_3145 == null) {
         this.field_3145 = this.method_915();
      }

      return this.field_3145;
   }

   public void method_2(Class_0436 var1) {
      if (var1 != null) {
         this.field_3145 = var1;
         System.gc();
      }
   }

   public Class_0436 method_915() {
      FontsModule var1 = FontsModule.field_364;
      return Class_0436.method_2(var1.field_367.getValue(), var1.field_371.getValue(), var1.field_368.getValue());
   }

   public void method_2(DrawContext var1, String var2, float var3, float var4, Color var5) {
      this.method_2(var1, Text.of(var2), var3, var4, var5);
   }

   public void method_2(DrawContext var1, Text var2, float var3, float var4, Color var5) {
      if (FontsModule.field_364.isToggled()) {
         this.method_914().method_2(var1.getMatrices(), var2.asOrderedText(), var3, var4, var5.hashCode(), false);
      } else {
         field_4219.textRenderer
            .draw(
               var2,
               (float)((int)var3),
               (float)((int)var4),
               var5.hashCode(),
               false,
               var1.getMatrices().peek().getPositionMatrix(),
               this.method_914().method_476(),
               TextLayerType.NORMAL,
               0,
               15728880
            );
      }
   }

   public void method_9(DrawContext var1, String var2, float var3, float var4, Color var5) {
      this.method_9(var1, Text.of(var2), var3, var4, var5);
   }

   public void method_9(DrawContext var1, Text var2, float var3, float var4, Color var5) {
      if (FontsModule.field_364.isToggled()) {
         this.method_914().method_2(var1.getMatrices(), var2.asOrderedText(), var3, var4, var5.hashCode(), true);
      } else {
         field_4219.textRenderer
            .draw(
               var2,
               (float)((int)var3),
               (float)((int)var4),
               var5.hashCode(),
               true,
               var1.getMatrices().peek().getPositionMatrix(),
               this.method_914().method_476(),
               TextLayerType.NORMAL,
               0,
               15728880
            );
      }
   }

   public void method_2(DrawContext var1, String var2, float var3, float var4, float var5, Color var6) {
      this.method_2(var1, Text.of(var2), var3, var4, var5, var6);
   }

   public void method_2(DrawContext var1, Text var2, float var3, float var4, float var5, Color var6) {
      var1.getMatrices().push();
      var1.getMatrices().scale(var5, var5, var5);
      this.method_2(var1, var2, var3 / var5, var4 / var5, var6);
      var1.getMatrices().scale(1.0F / var5, 1.0F / var5, 1.0F / var5);
      var1.getMatrices().pop();
   }

   public void method_9(DrawContext var1, String var2, float var3, float var4, float var5, Color var6) {
      this.method_9(var1, Text.of(var2), var3, var4, var5, var6);
   }

   public void method_9(DrawContext var1, Text var2, float var3, float var4, float var5, Color var6) {
      var1.getMatrices().push();
      var1.getMatrices().scale(var5, var5, var5);
      this.method_9(var1, var2, var3 / var5, var4 / var5, var6);
      var1.getMatrices().scale(1.0F / var5, 1.0F / var5, 1.0F / var5);
      var1.getMatrices().pop();
   }

   public void method_474() {
      this.method_914().method_474();
   }

   public float method_221(String var1) {
      if (nameprotect != null && nameprotect.isToggled() && !this.method_535()) {
         String repl = nameprotect.field_1851 == null ? "" : nameprotect.field_1851.getValue();
         var1 = var1.replace(field_4219.player.getName().getString(), repl);
      }

      if (FontsModule.field_364 != null && FontsModule.field_364.isToggled()) {
         return (float)this.method_914().method_473(var1);
      }
      return (float)field_4219.textRenderer.getWidth(var1);
   }

   public int method_66() {
      if (FontsModule.field_364 != null && FontsModule.field_364.isToggled()) {
         return this.method_914().method_472() - 1;
      } else {
         return field_4219 == null || field_4219.textRenderer == null ? 9 : 9;
      }
   }

   public static int method_916() {
      return FontsModule.field_364.field_372.getValue();
   }

   public static float method_917() {
      return FontsModule.field_364.field_373.getValue();
   }

   public static int method_918() {
      return FontsModule.field_364.field_374.getValue();
   }

   public static void method_35(int var0) {
      field_3144 = var0;
   }

   public static int method_919() {
      return field_3144;
   }

   public static String method_222(String var0) {
      return var0.replaceAll("[^a-zA-Z0-9.\\-]", "_");
   }
}
