package me.mioclient.internal;

import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.VertexFormat.DrawMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.CharacterVisitor;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Style;
import net.minecraft.text.TextColor;

public class Class_0352 implements CharacterVisitor {
   public final float field_1151;
   public final Class_0305 field_1152;
   public final int field_1153;
   public final MatrixStack field_1154;
   public float field_1155;
   public float field_1156;

   public Class_0352(MatrixStack var1, int var2, float var3, Class_0305 var4) {
      super();
      this.field_1153 = var2;
      this.field_1151 = var3;
      this.field_1152 = var4;
      this.field_1154 = var1;
   }

   public int method_2(OrderedText var1, float var2, float var3) {
      if (var1 == null) {
         return 0;
      } else {
         this.field_1155 = var2 * Float.intBitsToFloat(1073741824);
         this.field_1156 = var3 * Float.intBitsToFloat(1073741824);
         this.method_2(this.field_1154, var1);
         return (int)(this.field_1155 / Float.intBitsToFloat(1082130432));
      }
   }

   public void method_2(MatrixStack var1, OrderedText var2) {
      var1.push();
      var1.translate((float)Class_1016.method_918(), (float)Class_1016.method_916(), 0.0F);
      var1.scale(Float.intBitsToFloat(1056964608), Float.intBitsToFloat(1056964608), Float.intBitsToFloat(1065353216));
      if (!Class_0436.field_1371.method_435()) {
         Class_0436.field_1371.method_9(DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
      }

      var2.accept(this);
      var1.pop();
   }

   public boolean accept(int var1, Style var2, int var3) {
      TextColor var4 = var2.getColor();
      float var5 = (float)(this.field_1153 >> 24 & 0xFF) / Float.intBitsToFloat(1132396544);
      float var6;
      float var7;
      float var8;
      if (var4 != null) {
         int var9 = var4.getRgb();
         var6 = (float)(var9 >> 16 & 0xFF) / Float.intBitsToFloat(1132396544);
         var7 = (float)(var9 >> 8 & 0xFF) / Float.intBitsToFloat(1132396544);
         var8 = (float)(var9 & 0xFF) / Float.intBitsToFloat(1132396544);
      } else {
         var6 = (float)(this.field_1153 >> 16 & 0xFF) / Float.intBitsToFloat(1132396544);
         var7 = (float)(this.field_1153 >> 8 & 0xFF) / Float.intBitsToFloat(1132396544);
         var8 = (float)(this.field_1153 & 0xFF) / Float.intBitsToFloat(1132396544);
      }

      var6 *= this.field_1151;
      var7 *= this.field_1151;
      var8 *= this.field_1151;
      this.field_1152.method_352();
      float var13 = this.field_1152
         .method_2(this.field_1154, Class_0436.field_1371, Character.toString(var3).charAt(0), this.field_1155, this.field_1156, var6, var8, var7, var5);
      this.field_1155 += var13;
      return true;
   }
}
