package me.mioclient.internal;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import me.mioclient.api.Class_0937;
import me.mioclient.api.Class_0988;
import me.mioclient.enum_.Class_1200;
import me.mioclient.setting.ColorSetting;
import me.mioclient.setting.Setting;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class Class_0519 extends Class_1015<Color> implements Class_0988 {
   public final List<Class_0937> field_1641 = new ArrayList<>(3);
   public final Class_0242 field_1642 = new Class_0242();
   public final Class_0242 field_1643 = new Class_0242();
   public final Class_0242 field_1644 = new Class_0242();
   public boolean field_111;
   public boolean field_257;
   public boolean field_1645;
   public boolean field_1646;
   public boolean field_1647;
   public float[] field_1648 = new float[3];
   public final Class_0031 field_1649 = new Class_0031(Float.intBitsToFloat(1084227584), true);
   public final Class_0031 field_1650 = new Class_0031(Float.intBitsToFloat(1084227584), true);
   public final Class_0031 field_1651 = new Class_0031(Float.intBitsToFloat(1084227584), true);
   public final Class_0031 field_1652 = new Class_0031(Float.intBitsToFloat(1084227584), true);
   public final Class_0585 field_1653 = new Class_0585(Float.intBitsToFloat(1082130432), true);

   public Class_0519(Class_0746 var1, Class_0988 var2, Setting<?> var3) {
      super(var1, var2, (Setting<Color>)(Setting)var3);
      int var4 = (int)(
         (double)(this.field_419 + this.method_851() + this.method_167().method_216())
            + Double.longBitsToDouble(4619004367821864960L)
            + (double)this.method_560()
      );
      this.field_1641.add(new Class_0513(var4, this));
      this.field_1641.add(new Class_0735(var4, this));
      if (!((ColorSetting)var3).method_494()) {
         this.field_1641.add(new Class_1311(this));
      }

      this.field_1641.add(new Class_0781(this));
   }

   @Override
   public void method_2(DrawContext var1, MatrixStack var2, double var3, double var5) {
      super.method_2(var1, var2, var3, var5);
      if (this.method_911() || !this.field_111) {
         this.field_1649.method_36(0.0F);
         this.field_1650.method_36(0.0F);
         this.field_1651.method_36(0.0F);
         this.field_1652.method_36(0.0F);
      }

      if (!this.method_911()) {
         if (this.field_421 && this.field_111) {
            Class_1117.field_3460 = Class_1200.STANDARD;
         }

         this.method_2(
            var2,
            this.field_3138.getName(),
            () -> Class_1016.field_3143
                  .method_9(
                     var1,
                     this.field_3138.getName(),
                     (float)(this.field_418.getX() + 4),
                     (float)this.field_418.getY() + this.method_850() - this.method_169() + (float)this.field_419,
                     this.method_852().field_2876.getValue()
                  )
         );
         Class_0745.method_4(
            var2,
            this.method_59() + (float)this.field_418.method_216() - Float.intBitsToFloat(1095237632),
            this.method_60() + Float.intBitsToFloat(1065353216),
            this.method_59() + (float)this.field_418.method_216() - Float.intBitsToFloat(1080033280),
            this.method_60() + Float.intBitsToFloat(1092616192),
            this.method_852().field_2879.getValue()
         );
         Class_0745.method_4(
            var2,
            this.method_59() + (float)this.field_418.method_216() - Float.intBitsToFloat(1094713344),
            this.method_60() + Float.intBitsToFloat(1069547520),
            this.method_59() + (float)this.field_418.method_216() - Float.intBitsToFloat(1082130432),
            this.method_60() + Float.intBitsToFloat(1092091904),
            Class_1081.method_9(this.field_3138.getValue(), 255)
         );
         if (this.field_111) {
            int var7 = this.field_3138.getValue().getAlpha();
            int var8 = this.method_851();
            if (this.field_1645) {
               Class_1117.field_3460 = Class_1200.POINTER;
               this.field_1648[1] = (float)MathHelper.clamp(
                  (var3 - (double)this.method_59() - Double.longBitsToDouble(4617315517961601024L)) / (double)(this.method_167().method_216() - 8),
                  0.0,
                  Double.longBitsToDouble(4607182418800017408L)
               );
               this.field_1648[2] = Float.intBitsToFloat(1065353216)
                  - (float)MathHelper.clamp(
                     (var5 - (double)this.method_60() - (double)var8 - Double.longBitsToDouble(4613937818241073152L))
                        / (double)(this.method_167().method_216() - 8),
                     0.0,
                     Double.longBitsToDouble(4607182418800017408L)
                  );
            } else if (this.field_1646) {
               Class_1117.field_3460 = Class_1200.POINTER;
               float var9 = (float)Math.min(
                  Math.max((double)this.method_59(), var3 - Double.longBitsToDouble(4607182418800017408L)),
                  (double)(this.method_59() + (float)(this.method_167().method_216() - 4))
               );
               this.field_1648[0] = Math.min((var9 - this.method_59()) / (float)(this.method_167().method_216() - 4), Float.intBitsToFloat(1065353216));
            } else if (this.field_1647 && !this.field_3138.field_3119) {
               Class_1117.field_3460 = Class_1200.POINTER;
               float var21 = (float)Math.min(
                  Math.max((double)this.method_59(), var3 - Double.longBitsToDouble(4613937818241073152L)),
                  (double)(this.method_59() + (float)(this.method_167().method_216() - 4))
               );
               var7 = (int)(
                  Math.min(
                        Float.intBitsToFloat(1065353216) - (var21 - this.method_59()) / (float)(this.method_167().method_216() - 4),
                        Float.intBitsToFloat(1065353216)
                     )
                     * Float.intBitsToFloat(1132396544)
               );
            }

            int var22 = Color.HSBtoRGB(this.field_1648[0], Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216));
            float var10 = (float)(var22 >> 16 & 0xFF) / Float.intBitsToFloat(1132396544);
            float var11 = (float)(var22 >> 8 & 0xFF) / Float.intBitsToFloat(1132396544);
            float var12 = (float)(var22 & 0xFF) / Float.intBitsToFloat(1132396544);
            int var13 = this.field_1645 ? -1 : this.method_852().field_2879.getValue().hashCode();
            Class_0745.method_9(
               var2.peek().getPositionMatrix(),
               this.method_59() + Float.intBitsToFloat(1075838976),
               this.method_60() + (float)var8 + Float.intBitsToFloat(1056964608),
               (float)(this.method_167().method_216() - 3),
               (float)(this.method_167().method_216() - 3),
               var13,
               var13,
               var13,
               var13
            );
            Class_0745.method_9(
               var2.peek().getPositionMatrix(),
               this.method_59() + Float.intBitsToFloat(1077936128),
               this.method_60() + (float)var8 + Float.intBitsToFloat(1065353216),
               (float)(this.method_167().method_216() - 4),
               (float)(this.method_167().method_216() - 4),
               -1,
               var22,
               -16777216,
               -16777216
            );
            Class_0745.method_9(
               var2.peek().getPositionMatrix(),
               this.method_59() + Float.intBitsToFloat(1075838976),
               this.method_60() + (float)this.method_167().method_216() + (float)var8 - Float.intBitsToFloat(1056964608),
               (float)(this.method_167().method_216() - 3),
               Float.intBitsToFloat(1088421888),
               this.field_1646 ? -1 : this.method_559().hashCode(),
               this.field_1646 ? -1 : this.method_559().hashCode()
            );
            Class_0745.method_9(
               var2.peek().getPositionMatrix(),
               this.method_59() + Float.intBitsToFloat(1077936128),
               this.method_60() + (float)this.method_167().method_216() + (float)var8,
               (float)(this.method_167().method_216() - 4),
               Float.intBitsToFloat(1086324736),
               -65536,
               -256,
               -16711936,
               -16711681,
               -16776961,
               -65281,
               -65536
            );
            this.method_2(this.field_1651, this.field_1648[0] * (float)(this.method_167().method_216() - 2));
            float var14 = MathHelper.clamp(this.field_1651.method_45(), Float.intBitsToFloat(1077936128), (float)(this.method_167().method_216() - 3));
            Class_0745.method_9(
               var2,
               (float)((int)(this.method_59() + var14)) - Float.intBitsToFloat(1056964608),
               (float)((int)(this.method_60() + (float)this.method_167().method_216() + (float)var8)) - Float.intBitsToFloat(1056964608),
               (float)((int)(this.method_59() + var14 + Float.intBitsToFloat(1073741824))) + Float.intBitsToFloat(1056964608),
               (float)((int)(this.method_60() + (float)this.method_167().method_216() + (float)var8 + Float.intBitsToFloat(1087373312))),
               this.field_1645 ? -1 : this.method_559().hashCode()
            );
            Class_0745.method_9(
               var2,
               (float)((int)(this.method_59() + var14)),
               (float)((int)(this.method_60() + (float)this.method_167().method_216() + (float)var8)),
               (float)((int)(this.method_59() + var14 + Float.intBitsToFloat(1073741824))),
               (float)((int)(this.method_60() + (float)this.method_167().method_216() + (float)var8 + Float.intBitsToFloat(1087373312))),
               -1
            );
            if (!this.field_3138.field_3119) {
               Class_0745.method_9(
                  var2.peek().getPositionMatrix(),
                  this.method_59() + Float.intBitsToFloat(1075838976),
                  (float)((int)this.method_60() + this.method_167().method_216() + var8) + Float.intBitsToFloat(1089470464),
                  (float)(this.method_167().method_216() - 3),
                  Float.intBitsToFloat(1088421888),
                  this.field_1647 ? -1 : this.method_559().hashCode(),
                  this.field_1647 ? -1 : this.method_559().hashCode()
               );
               this.method_2(
                  var2,
                  (int)this.method_59() + 3,
                  (int)this.method_60() + this.method_167().method_216() + var8 + 8,
                  this.method_167().method_216() - 4,
                  6,
                  var10,
                  var11,
                  var12,
                  (float)var7 / Float.intBitsToFloat(1132396544)
               );
            }

            this.method_2(
               this.field_1649, MathHelper.clamp(this.field_1648[1], 0.0F, Float.intBitsToFloat(1065353216)) * (float)(this.method_167().method_216() - 8)
            );
            this.method_2(
               this.field_1650,
               (Float.intBitsToFloat(1065353216) - MathHelper.clamp(this.field_1648[2], 0.0F, Float.intBitsToFloat(1065353216)))
                  * (float)(this.method_167().method_216() - 8)
            );
            float var15 = this.method_59() + Float.intBitsToFloat(1084227584) + this.field_1649.method_45();
            float var16 = this.method_60() + (float)var8 + Float.intBitsToFloat(1077936128) + this.field_1650.method_45();
            this.field_1653.method_3(this.field_1645);
            float var17 = Float.intBitsToFloat(1073741824) * Float.intBitsToFloat(1065353216) * this.field_1653.method_45();
            Color var18 = Color.getHSBColor(this.field_1648[0], this.field_1648[1], this.field_1648[2]);
            Class_0745.method_4(
               var2,
               var15 - Float.intBitsToFloat(1069547520) - var17,
               var16 - Float.intBitsToFloat(1069547520) - var17,
               var15 + Float.intBitsToFloat(1069547520) + var17,
               var16 + Float.intBitsToFloat(1069547520) + var17,
               this.field_1645 ? Color.white : this.method_559()
            );
            Class_0745.method_4(
               var2,
               var15 - Float.intBitsToFloat(1065353216) - var17,
               var16 - Float.intBitsToFloat(1065353216) - var17,
               var15 + Float.intBitsToFloat(1065353216) + var17,
               var16 + Float.intBitsToFloat(1065353216) + var17,
               this.field_1645 ? var18 : var18.darker()
            );
            this.field_1641.forEach(var6 -> var6.method_2(var1, var2, var3, var5));
            this.field_3138
               .method_78(
                  Class_1081.method_9(new Color(Color.HSBtoRGB(this.field_1648[0], this.field_1648[1], this.field_1648[2])), MathHelper.clamp(var7, 0, 255))
               );
            if (((ColorSetting)this.field_3138).method_132()) {
               this.field_1648[0] = Color.RGBtoHSB(
                  this.field_3138.getValue().getRed(), this.field_3138.getValue().getGreen(), this.field_3138.getValue().getBlue(), null
               )[0];
            }
         }

         int var19 = (int)((float)(this.field_419 + this.method_851() + this.method_167().method_216() + 6) + this.method_560());
         float var20 = Float.intBitsToFloat(1065353216);

         for (Class_0937 var24 : this.field_1641) {
            var24.method_4(var3, var5);
            var24.method_26(var19);
            if (!(var24 instanceof Class_0045)) {
               var19 += (int)((float)var24.method_66() + var20);
               var24.method_26(var19);
               var20 = Float.intBitsToFloat(1056964608);
            }
         }
      }
   }

   public void method_2(MatrixStack var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, float var9) {
      boolean var10 = true;
      int var11 = var5 / 2;

      for (int var12 = -var11; var12 < var4; var12 += var11) {
         if (!var10) {
            Class_0745.method_9(var1, (float)(var2 + var12), (float)var3, (float)(var2 + var12 + var11), (float)(var3 + var5), -1);
            Class_0745.method_9(var1, (float)(var2 + var12), (float)(var3 + var11), (float)(var2 + var12 + var11), (float)(var3 + var5), -7303024);
            if (var12 < var4 - var11) {
               int var13 = var2 + var12 + var11;
               int var14 = Math.min(var2 + var4, var2 + var12 + var11 * 2);
               Class_0745.method_9(var1, (float)var13, (float)var3, (float)var14, (float)(var3 + var5), -7303024);
               Class_0745.method_9(var1, (float)var13, (float)(var3 + var11), (float)var14, (float)(var3 + var5), -1);
            }
         }

         var10 = !var10;
      }

      Class_0745.method_9(
         var1.peek().getPositionMatrix(),
         (float)var2,
         (float)var3,
         (float)var4,
         (float)var5,
         new Color(var6, var7, var8, Float.intBitsToFloat(1065353216)).hashCode(),
         0
      );
      this.method_2(this.field_1652, (float)var4 - (float)(var4 - 2) * var9);
      float var15 = (float)var2 + this.field_1652.method_45() - Float.intBitsToFloat(1065353216);
      Class_0745.method_4(
         var1,
         var15 - Float.intBitsToFloat(1069547520),
         (float)var3 - Float.intBitsToFloat(1056964608),
         var15 + Float.intBitsToFloat(1069547520),
         (float)(var3 + var5) + Float.intBitsToFloat(1056964608),
         this.field_1647 ? Color.white : this.method_559()
      );
      Class_0745.method_9(var1, var15 - Float.intBitsToFloat(1065353216), (float)var3, var15 + Float.intBitsToFloat(1065353216), (float)(var3 + var5), -1);
   }

   @Override
   public void method_2(double var1, double var3, int var5) {
      if (!this.method_911()) {
         int var6 = this.method_851();
         if (this.method_38(var1, var3) && var5 == 1) {
            this.field_111 = !this.field_111;
         }

         if (this.field_111) {
            this.field_1641.forEach(var5x -> var5x.method_2(var1, var3, var5));
         }

         if (this.field_111 && var5 == 0) {
            if (var1 > (double)(this.method_59() + Float.intBitsToFloat(1073741824))
               && var1 < (double)(this.method_59() + (float)this.method_167().method_216() - Float.intBitsToFloat(1073741824))
               && var3 > (double)(this.method_60() + (float)var6)
               && var3 < (double)(this.method_60() + (float)var6 + (float)this.method_167().method_216() - Float.intBitsToFloat(1073741824))) {
               this.field_1645 = true;
            }

            if (var1 > (double)(this.method_59() + Float.intBitsToFloat(1073741824))
               && var1 < (double)(this.method_59() + (float)this.method_167().method_216() - Float.intBitsToFloat(1073741824))
               && var3 > (double)(this.method_60() + (float)var6 + (float)this.method_167().method_216())
               && var3 < (double)(this.method_60() + (float)var6 + (float)this.method_167().method_216() + Float.intBitsToFloat(1086324736))) {
               this.field_1646 = true;
            }

            if (var1 > (double)(this.method_59() + Float.intBitsToFloat(1073741824))
               && var1 < (double)(this.method_59() + (float)this.method_167().method_216() - Float.intBitsToFloat(1073741824))
               && var3 > (double)(this.method_60() + (float)var6 + (float)this.method_167().method_216() + Float.intBitsToFloat(1090519040))
               && var3 < (double)(this.method_60() + (float)var6 + (float)this.method_167().method_216() + Float.intBitsToFloat(1096810496))
               && !this.field_3138.field_3119) {
               this.field_1647 = true;
            }

            super.method_2(var1, var3, var5);
         }
      }
   }

   @Override
   public void method_9(double var1, double var3, int var5) {
      if (!this.method_911()) {
         this.field_1641.forEach(var5x -> var5x.method_9(var1, var3, var5));
         super.method_9(var1, var3, var5);
         this.field_1645 = this.field_1646 = this.field_1647 = false;
      }
   }

   @Override
   public void method_9(char var1) {
      if (!this.method_911()) {
         this.field_1641.forEach(var1x -> var1x.method_9(var1));
         super.method_9(var1);
      }
   }

   @Override
   public void method_68(int var1) {
      if (!this.method_911()) {
         this.field_1641.forEach(var1x -> var1x.method_68(var1));
         super.method_68(var1);
      }
   }

   @Override
   public int method_66() {
      if (this.field_111 && !this.method_911()) {
         int var1 = (int)((float)this.method_167().method_216() + Float.intBitsToFloat(1088421888) + (float)(this.method_851() * 2) + this.method_560());
         if (this.field_1641.size() > 2) {
            var1 = (int)((float)var1 + (float)(this.method_851() * (this.field_1641.size() - 2)) + Float.intBitsToFloat(1056964608));
         }

         return var1;
      } else {
         return super.method_66();
      }
   }

   @Override
   public void init() {
      this.method_31(this.field_3138.getValue());
      this.field_1641.forEach(Class_0937::init);
   }

   @Override
   public boolean isClosed() {
      return this.field_3137.isClosed() || !this.field_111;
   }

   public boolean method_38(double var1, double var3) {
      return var1 > (double)this.field_418.getX()
         && var1 < (double)(this.field_418.getX() + this.field_418.method_216())
         && var3 > (double)(this.field_418.getY() + this.field_419)
         && var3 < (double)(this.field_418.getY() + this.method_851() + this.field_419);
   }

   public float method_59() {
      return (float)this.field_418.getX();
   }

   public float method_60() {
      return (float)(this.field_418.getY() + this.field_419);
   }

   public Color method_559() {
      return Class_1081.method_2(this.method_852().field_2879.getValue(), Float.intBitsToFloat(1065353216));
   }

   public float method_560() {
      return this.field_3138.field_3119 ? Float.intBitsToFloat(1073741824) : Float.intBitsToFloat(1092616192);
   }

   @Override
   public boolean method_5(double var1, double var3) {
      return super.method_5(var1, var3) || this.field_1646 || this.field_1645 || this.field_1647;
   }

   public void method_31(Color var1) {
      float[] var2 = Color.RGBtoHSB(var1.getRed(), var1.getGreen(), var1.getBlue(), null);
      this.field_1648[0] = var2[0];
      this.field_1648[1] = var2[1];
      this.field_1648[2] = var2[2];
   }

   public Color method_152() {
      return Class_1081.method_9(Color.getHSBColor(this.field_1648[0], this.field_1648[1], this.field_1648[2]), this.field_3138.getValue().getAlpha());
   }

   public void method_2(Class_0031 var1, float var2) {
      if (this.field_257) {
         var1.method_36(var2);
      } else {
         var1.method_3(var2);
      }
   }
}
