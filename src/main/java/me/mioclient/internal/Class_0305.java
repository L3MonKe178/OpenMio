package me.mioclient.internal;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;
import javax.imageio.ImageIO;
import me.mioclient.Hub;
import me.mioclient.module.client.FontsModule;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.util.math.MatrixStack;
import org.lwjgl.BufferUtils;

public class Class_0305 {
   public static FontsModule field_984 = Hub.field_2595.method_78(FontsModule.class);
   public final Font field_985;
   public final boolean field_986;
   public final boolean field_987;
   public final HashMap<Character, Class_0523> field_988 = new HashMap<>();
   public int field_989;
   public int field_990 = -1;
   public BufferedImage field_991;
   public AbstractTexture field_992;

   public Class_0305(Font var1, boolean var2, boolean var3) {
      super();
      this.field_985 = var1;
      this.field_986 = var2;
      this.field_987 = var3;
   }

   public void method_208() {
      this.field_992.clearGlId();
      this.field_992.close();
      this.field_988.clear();
   }

   public void method_9(char[] var1) {
      double var2 = Double.longBitsToDouble(-4616189618054758400L);
      double var4 = Double.longBitsToDouble(-4616189618054758400L);
      AffineTransform var6 = new AffineTransform();
      FontRenderContext var7 = new FontRenderContext(var6, this.field_986, this.field_987);

      for (char var11 : var1) {
         Rectangle2D var12 = this.field_985.getStringBounds(Character.toString(var11), var7);
         if (var2 < var12.getWidth()) {
            var2 = var12.getWidth();
         }

         if (var4 < var12.getHeight()) {
            var4 = var12.getHeight();
         }
      }

      var2 += Double.longBitsToDouble(4611686018427387904L);
      var4 += Double.longBitsToDouble(4611686018427387904L);
      this.field_989 = (int)Math.ceil(
            Math.max(Math.ceil(Math.sqrt(var2 * var2 * (double)var1.length) / var2), Math.ceil(Math.sqrt(var4 * var4 * (double)var1.length) / var4))
               * Math.max(var2, var4)
         )
         + 1;
      this.field_991 = new BufferedImage(this.field_989, this.field_989, 2);
      Graphics2D var21 = this.field_991.createGraphics();
      var21.setFont(this.field_985);
      var21.setColor(new Color(255, 255, 255, 0));
      var21.fillRect(0, 0, this.field_989, this.field_989);
      var21.setColor(Color.white);
      if (field_984.field_369.getValue() && field_984.isToggled()) {
         var21.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
         var21.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
         var21.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      } else {
         var21.setRenderingHint(
            RenderingHints.KEY_FRACTIONALMETRICS, this.field_987 ? RenderingHints.VALUE_FRACTIONALMETRICS_ON : RenderingHints.VALUE_FRACTIONALMETRICS_OFF
         );
         var21.setRenderingHint(RenderingHints.KEY_ANTIALIASING, this.field_986 ? RenderingHints.VALUE_ANTIALIAS_OFF : RenderingHints.VALUE_ANTIALIAS_ON);
         var21.setRenderingHint(
            RenderingHints.KEY_TEXT_ANTIALIASING, this.field_986 ? RenderingHints.VALUE_TEXT_ANTIALIAS_ON : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF
         );
      }

      FontMetrics var22 = var21.getFontMetrics();
      int var23 = 0;
      int var24 = 0;
      int var25 = 1;

      for (char var16 : var1) {
         Class_0523 var17 = new Class_0523();
         Rectangle2D var18 = var22.getStringBounds(Character.toString(var16), var21);
         var17.field_1656 = var18.getBounds().width + 8;
         var17.field_1657 = var18.getBounds().height;
         if (var24 + var17.field_1656 >= this.field_989) {
            var24 = 0;
            var25 += var23;
            var23 = 0;
         }

         var17.field_1321 = var24;
         var17.field_1322 = var25;
         if (var17.field_1657 > this.field_990) {
            this.field_990 = var17.field_1657;
         }

         if (var17.field_1657 > var23) {
            var23 = var17.field_1657;
         }

         var21.drawString(Character.toString(var16), var24 + 2, var25 + var22.getAscent());
         var24 += var17.field_1656;
         this.field_988.put(var16, var17);
      }

      this.field_991.flush();
      var21.dispose();
   }

   public void method_351() {
      try {
         ByteArrayOutputStream var1 = new ByteArrayOutputStream();
         ImageIO.write(this.field_991, "png", var1);
         byte[] var2 = var1.toByteArray();
         ByteBuffer var3 = BufferUtils.createByteBuffer(var2.length).put(var2);
         var3.flip();
         this.field_992 = new NativeImageBackedTexture(NativeImage.read(var3));
      } catch (Exception var4) {
         var4.printStackTrace();
      }
   }

   public void method_352() {
      GlStateManager._bindTexture(this.field_992.getGlId());
      if (field_984.field_369.getValue()) {
         GlStateManager._texParameter(3553, 10240, 9729);
      } else {
         GlStateManager._texParameter(3553, 10240, 9728);
      }

      RenderSystem.setShaderTexture(0, this.field_992.getGlId());
   }

   public void method_353() {
      RenderSystem.setShaderTexture(0, 0);
      if (field_984.field_369.getValue()) {
         GlStateManager._texParameter(3553, 10240, 9728);
      }
   }

   public float method_2(MatrixStack var1, VertexConsumer var2, char var3, float var4, float var5, float var6, float var7, float var8, float var9) {
      Class_0523 var10 = this.field_988.get(var3);
      if (var10 == null) {
         return 0.0F;
      } else {
         var6 *= RenderSystem.getShaderColor()[0];
         var8 *= RenderSystem.getShaderColor()[1];
         var7 *= RenderSystem.getShaderColor()[2];
         var9 *= RenderSystem.getShaderColor()[3];
         float var11 = (float)var10.field_1321 / (float)this.field_989;
         float var12 = (float)var10.field_1322 / (float)this.field_989;
         float var13 = (float)var10.field_1656 / (float)this.field_989;
         float var14 = (float)var10.field_1657 / (float)this.field_989;
         float var15 = (float)var10.field_1656;
         float var16 = (float)var10.field_1657;
         var2.vertex(var1.peek().getPositionMatrix(), var4, var5 + var16, (float)Class_1016.method_919())
            .color(var6, var8, var7, var9)
            .texture(var11, var12 + var14);
         var2.vertex(var1.peek().getPositionMatrix(), var4 + var15, var5 + var16, (float)Class_1016.method_919())
            .color(var6, var8, var7, var9)
            .texture(var11 + var13, var12 + var14);
         var2.vertex(var1.peek().getPositionMatrix(), var4 + var15, var5, (float)Class_1016.method_919())
            .color(var6, var8, var7, var9)
            .texture(var11 + var13, var12);
         var2.vertex(var1.peek().getPositionMatrix(), var4, var5, (float)Class_1016.method_919()).color(var6, var8, var7, var9).texture(var11, var12);
         return var15 - Float.intBitsToFloat(1090519040);
      }
   }

   public float method_5(char var1) {
      if (!this.field_988.containsKey(var1)) {
         var1 = ' ';
      }

      return (float)this.field_988.get(var1).field_1656;
   }

   public int method_354() {
      return this.field_990;
   }

   public boolean method_355() {
      return this.field_986;
   }

   public boolean method_356() {
      return this.field_987;
   }
}
