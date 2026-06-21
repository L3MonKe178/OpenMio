package me.mioclient.internal;

import me.mioclient.api.Class_0988;
import me.mioclient.module.client.UIModule;
import me.mioclient.setting.Setting;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;

public class Class_1031 extends Class_1015<Boolean> {
   public final Class_0585 field_3189 = new Class_0585(() -> Float.intBitsToFloat(1073741824) * UIModule.field_2843.field_2854.getValue(), true);

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Class_1031(Class_0746 var1, Class_0988 var2, Setting<?> var3) {
      super(var1, var2, (Setting<Boolean>)(Setting)var3);
   }

   @Override
   public void method_2(double var1, double var3, int var5) {
      if (!this.method_911()) {
         if (!this.field_3138.field_3120 || var5 != 0) {
            super.method_2(var1, var3, var5);
            if (this.method_5(var1, var3)) {
               if (var5 == 0) {
                  this.field_3138.method_78(!this.field_3138.getValue());
               }

               if (var5 == 1 && this.field_3138.field_3118) {
                  this.field_3138.field_111 = !this.field_3138.field_111;
               }
            }
         }
      }
   }

   @Override
   public void method_2(DrawContext var1, MatrixStack var2, double var3, double var5) {
      if (this.method_911()) {
         this.field_3189.reset();
      } else {
         this.field_3189.method_3(this.field_3138.getValue());
         float var7 = this.field_3189.method_45();
         super.method_2(var1, var2, var3, var5);
         if ((this.field_3138.getValue() || (double)var7 > Double.longBitsToDouble(4576918229304087675L)) && !this.field_3138.field_3120) {
            Class_0745.method_4(
               var2,
               (float)(this.field_418.getX() + this.method_170()),
               (float)(this.field_418.getY() + this.field_419) + Float.intBitsToFloat(1056964608),
               (float)this.field_418.getX()
                  + Math.max((float)this.field_418.method_216() * var7 - Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1073741824)),
               (float)(this.field_418.getY() + this.field_419 + this.method_66()) - Float.intBitsToFloat(1056964608),
               this.method_852().field_2879.getValue()
            );
         }

         String var8 = this.field_3138.getName();
         if (!var8.isEmpty()) {
            this.method_2(
               var2,
               var8,
               () -> Class_1016.field_3143
                     .method_9(
                        var1,
                        var8,
                        (float)(this.field_418.getX() + 4),
                        (float)this.field_418.getY() + this.method_850() - this.method_169() + (float)this.field_419,
                        this.method_852().field_2876.getValue()
                     )
            );
         }

         if (this.field_3138.field_3118 && !this.field_3138.field_3120) {
            String var9 = this.field_3138.field_111 ? "-" : "+";
            Class_1016.field_3143
               .method_9(
                  var1,
                  var9,
                  (float)(this.field_418.getX() + this.field_418.method_216() - 4) - Class_1016.field_3143.method_221(var9),
                  (float)this.field_418.getY() + this.method_850() - this.method_169() + (float)this.field_419,
                  this.method_852().field_2876.getValue()
               );
         }

         if (this.field_3138.field_3120) {
            String var10 = "...";
            Class_1016.field_3143
               .method_9(
                  var1,
                  var10,
                  (float)(this.field_418.getX() + this.field_418.method_216() - 4) - Class_1016.field_3143.method_221(var10),
                  (float)this.field_418.getY() + this.method_850() - this.method_169() + (float)this.field_419,
                  this.method_852().field_2876.getValue()
               );
         }
      }
   }
}
