package me.mioclient.internal;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import me.mioclient.Hub;
import me.mioclient.api.Class_0937;
import me.mioclient.api.Class_0988;
import me.mioclient.enum_.Class_1290;
import me.mioclient.module.Module;
import me.mioclient.module.abstract_.AbstractModule_26;
import me.mioclient.module.abstract_.AbstractModule_41;
import me.mioclient.module.client.UIModule;
import me.mioclient.setting.Setting;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;

public class Class_0050 extends Class_0145 implements Class_0988 {
   public final ArrayList<Class_0937> field_105 = new ArrayList<>();
   public final Class_0031 field_106 = new Class_0031(() -> this.method_852().field_2854.getValue() * Float.intBitsToFloat(1073741824), true);
   public final Class_0585 field_107 = new Class_0585(() -> Float.intBitsToFloat(1073741824) * UIModule.field_2843.field_2854.getValue(), true);
   public final Class_0055 field_108 = new Class_0055();
   public final Module field_109;
   public int field_110 = 0;
   public boolean field_111;
   public float field_112;
   public static long field_113 = 0L;
   public static Class_0050 field_114 = null;
   public static boolean field_115 = false;

   public Class_0050(Module var1, Class_0746 var2, int var3) {
      super(var2, var3);
      this.field_109 = var1;
      int var4 = this.field_419 + this.method_851();

      for (Setting var6 : var1.getRegistry()) {
         Class_1015 var7 = this.method_2(var2, var6);
         if (var7 != null) {
            var7.method_26(var4);
            var7.method_2(new Class_1290[]{Class_1290.PADDING_SHIFT});
            this.field_105.add(var7);
         }

         var4 += 11;
      }

      if (!(var1 instanceof AbstractModule_26) && !(var1 instanceof AbstractModule_41)) {
         this.field_105.add(new Class_0381(var4, this));
      }

      this.field_106.method_36((float)this.method_67());
   }

   @Nullable
   public Class_1015<?> method_2(Class_0746 var1, Setting<?> var2) {
      Object var3 = Objects.requireNonNull(var2.getValue());

      var2.method_99();
      return (Class_1015<?>)(switch (var3) {
         case Boolean var5 -> new Class_1031(var1, this, var2);
         case Number var6 -> new Class_1170(var1, this, var2);
         case Color var7 -> new Class_0519(var1, this, var2);
         case String var8 -> new Class_0872(var1, this, var2);
         case Enum var9 -> new Class_0414(var1, this, var2);
         case Class_0211 var10 -> new Class_0262(var1, this, var2);
         default -> null;
      });
   }

   public Module method_65() {
      return this.field_109;
   }

   @Override
   public int method_66() {
      return (int)this.field_112;
   }

   public int method_67() {
      int var1 = this.method_851();
      if (this.field_111) {
         for (Class_0937 var3 : this.field_105) {
            var1 += var3.method_66();
         }
      }

      return var1;
   }

   @Override
   public void method_26(int var1) {
      this.field_419 = var1;
      int var2 = this.field_419 + this.method_851();

      for (Class_0937 var4 : this.field_105) {
         var4.method_26(var2);
         var2 += var4.method_66();
      }
   }

   @Override
   public void method_4(double var1, double var3) {
      this.field_106.method_3((float)this.method_67());
      this.field_112 = this.field_106.method_45();
      this.field_105.forEach(var4 -> var4.method_4(var1, var3));
   }

   @Override
   public void method_2(double var1, double var3, int var5) {
      super.method_2(var1, var3, var5);
      if (this.method_5(var1, var3) && var5 == 0 && this.method_171()) {
         this.field_109.method_68();
      }

      if (this.method_5(var1, var3) && var5 == 1) {
         this.field_111 = !this.field_111;
         if (this.field_111 && !Hub.method_755().field_1313.contains(this)) {
            Hub.method_755().field_1313.add(this);
         }

         this.field_418.method_142();
         if (!Hub.method_755().method_452().getText().isEmpty()) {
            this.method_70();
         }
      }

      this.field_105.forEach(var5x -> var5x.method_2(var1, var3, var5));
   }

   @Override
   public void method_9(double var1, double var3, int var5) {
      this.field_105.forEach(var5x -> var5x.method_9(var1, var3, var5));
   }

   @Override
   public void method_68(int var1) {
      this.field_105.forEach(var1x -> var1x.method_68(var1));
   }

   @Override
   public void method_7(double var1, double var3, double var5) {
      for (Class_0937 var8 : this.field_105) {
         var8.method_7(var1, var3, var5);
      }
   }

   @Override
   public void method_2(DrawContext var1, MatrixStack var2, double var3, double var5) {
      super.method_2(var1, var2, var3, var5);
      this.field_107.method_3(this.field_109.isToggled());
      float var7 = this.field_107.method_45();
      float var8 = this.method_169();
      if (!this.field_109.isToggled() && !((double)var7 > Double.longBitsToDouble(4576918229304087675L))) {
         Class_0745.method_4(
            var2,
            (float)this.field_418.getX() + Float.intBitsToFloat(1065353216),
            (float)(this.field_418.getY() + this.field_419) + Float.intBitsToFloat(1056964608),
            (float)(this.field_418.getX() + this.field_418.method_216()) - Float.intBitsToFloat(1065353216),
            (float)(this.field_418.getY() + this.field_419 + this.method_851()) - Float.intBitsToFloat(1056964608),
            this.method_852().field_2881.getValue()
         );
      } else {
         Class_0745.method_4(
            var2,
            (float)this.field_418.getX() + Float.intBitsToFloat(1065353216) + ((float)this.field_418.method_216() - Float.intBitsToFloat(1065353216)) * var7,
            (float)(this.field_418.getY() + this.field_419) + Float.intBitsToFloat(1056964608),
            (float)(this.field_418.getX() + this.field_418.method_216()) - Float.intBitsToFloat(1065353216),
            (float)(this.field_418.getY() + this.field_419 + this.method_851()) - Float.intBitsToFloat(1056964608),
            this.method_852().field_2881.getValue()
         );
         Class_0745.method_4(
            var2,
            (float)this.field_418.getX() + Float.intBitsToFloat(1065353216),
            (float)(this.field_418.getY() + this.field_419) + Float.intBitsToFloat(1056964608),
            (float)this.field_418.getX() + ((float)this.field_418.method_216() - Float.intBitsToFloat(1065353216)) * var7,
            (float)(this.field_418.getY() + this.field_419 + this.method_851()) - Float.intBitsToFloat(1056964608),
            this.method_852().field_2882.getValue()
         );
      }

      if (this.field_110 > 0) {
         float var9 = this.field_108.method_45();
         Class_0745.method_9(
            var2,
            (float)this.field_418.getX() + Float.intBitsToFloat(1065353216),
            (float)(this.field_418.getY() + this.field_419) + Float.intBitsToFloat(1056964608),
            (float)(this.field_418.getX() + this.field_418.method_216()) - Float.intBitsToFloat(1065353216),
            (float)(this.field_418.getY() + this.field_419 + this.method_851()) - Float.intBitsToFloat(1056964608),
            Class_1081.method_2(Float.intBitsToFloat(1065353216), 0.0F, 0.0F, var9)
         );
         if (var9 == Float.intBitsToFloat(1065353216) || var9 == 0.0F) {
            this.field_108.method_2((var9 + Float.intBitsToFloat(1065353216)) % Float.intBitsToFloat(1073741824), 150L);
         }

         if (var9 == 0.0F) {
            this.field_110 = (this.field_110 + 1) % 4;
         }
      }

      FontRenderer.field_3143
         .method_9(
            var1,
            this.field_109.getName(),
            (float)(this.field_418.getX() + UIModule.field_2843.field_2853.getValue()),
            (float)this.field_418.getY() + this.method_850() - var8 + (float)this.field_419,
            this.field_109.isToggled() ? this.method_852().field_2877.getValue() : this.method_852().field_2876.getValue()
         );
      if (this.method_852().field_2860.getValue() && !this.field_105.isEmpty()) {
         String var13 = this.field_111 ? "-" : "+";
         FontRenderer.field_3143
            .method_9(
               var1,
               var13,
               (float)(this.field_418.getX() + this.field_418.method_216() - 4) - FontRenderer.field_3143.method_221(var13),
               (float)this.field_418.getY() + this.method_850() - var8 + (float)this.field_419,
               this.method_852().field_2876.getValue()
            );
      }

      MutableText var14 = Text.empty();
      if (this.field_109.isWip()) {
         var14.append("beta ");
      }

      if (this.method_852().field_2859.getValue() && this.field_109.getKeybind().method_38() != -1) {
         String var10 = this.field_109.getKeybind().method_4();
         var14.append("[").append(var10.toUpperCase(Locale.ROOT)).append("]");
      }

      if (!var14.getString().isEmpty()) {
         FontRenderer.field_3143
            .method_9(
               var1,
               var14,
               (float)this.field_418.getX()
                  + FontRenderer.field_3143.method_221(this.field_109.getName())
                  + (float)this.method_852().field_2853.getValue().intValue(),
               (float)this.field_418.getY() + this.method_850() - var8 + (float)this.field_419 + Float.intBitsToFloat(1065353216),
               Float.intBitsToFloat(1056964608),
               this.method_852().field_2876.getValue()
            );
      }

      var2.push();
      if (this.field_112 > (float)this.method_851() || this.field_111) {
         int var15 = (int)MathHelper.clamp(
            this.field_112, (float)this.method_851(), (float)this.field_418.method_66() * this.field_418.method_715() - (float)this.field_419
         );
         Class_0745.method_474();
         Class_0041.method_9(this.field_418.getX(), this.field_418.getY() + this.field_419, this.field_418.method_216(), var15);

         for (Class_0937 var12 : this.field_105) {
            var12.method_2(var1, var2, var3, var5);
         }

         Class_0745.method_474();
         Class_0041.method_57();
      }

      var2.pop();
      if (this.method_852().field_2848.getValue() && this.method_5(var3, var5)) {
         if (this.method_852().field_2849.getValue() == 0) {
            Hub.method_755().method_316(this.field_109.getDescription());
         } else {
            if (!this.method_852().field_2850.getValue()) {
               if (field_114 == null) {
                  field_114 = this;
                  field_113 = System.currentTimeMillis();
                  field_115 = false;
               } else if (field_114 != this) {
                  field_114 = this;
                  if (!field_115) {
                     field_113 = System.currentTimeMillis();
                  }
               }
            } else if (field_114 != this) {
               field_114 = this;
               field_115 = false;
               field_113 = System.currentTimeMillis();
            }

            if (field_115) {
               Hub.method_755().method_316(this.field_109.getDescription());
            } else if (System.currentTimeMillis() >= field_113 + (long)this.method_852().field_2849.getValue().intValue()) {
               field_115 = true;
               Hub.method_755().method_316(this.field_109.getDescription());
            }
         }
      }
   }

   @Override
   public void method_9(char var1) {
      this.field_105.forEach(var1x -> var1x.method_9(var1));
   }

   @Override
   public void init() {
      for (Class_0937 var2 : this.field_105) {
         var2.init();
      }
   }

   @Override
   public boolean method_5(double var1, double var3) {
      return var1 > (double)this.field_418.getX()
         && var1 < (double)(this.field_418.getX() + this.field_418.method_216())
         && var3 > (double)(this.field_418.getY() + this.field_419)
         && var3 < (double)(this.field_418.getY() + this.method_851() + this.field_419);
   }

   public static void method_69() {
      field_113 = 0L;
      field_114 = null;
      field_115 = false;
   }

   @Override
   public boolean isClosed() {
      return this.field_112 > (float)(this.method_851() + 1) ? false : !this.field_111;
   }

   public void method_70() {
      this.field_108.method_36(0.0F);
      this.field_108.method_2(Float.intBitsToFloat(1065353216), 150L);
      this.field_110 = 1;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         Class_0050 var2 = (Class_0050)var1;
         return Objects.equals(this.field_109, var2.field_109);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(this.field_109);
   }
}
