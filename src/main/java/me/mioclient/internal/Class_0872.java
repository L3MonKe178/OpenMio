package me.mioclient.internal;

import me.mioclient.Hub;
import me.mioclient.api.Class_0988;
import me.mioclient.enum_.Class_1200;
import me.mioclient.setting.Setting;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.glfw.GLFW;

public class Class_0872 extends Class_1015<String> {
   public final Class_0242 field_2779 = new Class_0242();
   public boolean field_1309 = false;
   public String field_2780;
   public int field_2781 = 0;

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Class_0872(Class_0746 var1, Class_0988 var2, Setting<?> var3) {
      super(var1, var2, (Setting<String>)(Setting)var3);
   }

   @Override
   public void method_2(double var1, double var3, int var5) {
      if (this.method_911()) {
         this.field_3142 = false;
      } else {
         super.method_2(var1, var3, var5);
         if (this.method_5(var1, var3) && var5 == 0) {
            this.field_3142 = !this.field_3142;
            if (this.field_3142) {
               if (this.method_912().method_454() != null && this.method_912().method_454() != this) {
                  this.method_912().method_454().field_3142 = false;
               }

               this.method_912().method_2(this);
            } else if (this.method_912().method_454() == this) {
               this.method_912().method_2(null);
            }

            this.field_2780 = this.field_3138.getValue();
            if (this.field_2780 != null && !this.field_2780.isEmpty()) {
               this.field_2781 = this.field_2780.length();
            }
         }
      }
   }

   @Override
   public void method_2(DrawContext var1, MatrixStack var2, double var3, double var5) {
      if (this.method_911()) {
         if (this.field_3142 && this.method_912().method_454() == this) {
            this.method_912().method_2(null);
         }

         this.field_3142 = false;
      } else {
         super.method_2(var1, var2, var3, var5);
         if (this.field_421) {
            Class_1117.field_3460 = Class_1200.INPUT;
         }

         Class_0745.method_4(
            var2,
            (float)(this.field_418.getX() + this.method_170()),
            (float)(this.field_418.getY() + this.field_419) + Float.intBitsToFloat(1056964608),
            (float)(this.field_418.getX() + this.field_418.method_216() - 1),
            (float)(this.field_418.getY() + this.field_419 + this.method_66()) - Float.intBitsToFloat(1056964608),
            this.method_852().field_2879.getValue()
         );
         if (this.field_2780 != null) {
            this.field_2781 = MathHelper.clamp(this.field_2781, 0, this.field_2780.length());
         }

         if (this.field_3142) {
            var2.push();
            String var7 = this.method_453();
            float var8 = Class_1016.field_3143.method_221(new Class_1303().method_2(this.field_2780.substring(0, this.field_2781)).method_9("\u0001_"));
            if (var8 > (float)(this.method_167().method_216() - this.method_170() * 2)) {
               var2.translate((float)(this.method_167().method_216() - this.method_170() * 2) - var8, 0.0F, 0.0F);
            }

            if (!this.field_2780.isEmpty()) {
               Class_1016.field_3143
                  .method_9(
                     var1,
                     this.field_2780,
                     (float)(this.field_418.getX() + 4),
                     (float)this.field_418.getY() + this.method_850() - this.method_169() + (float)this.field_419,
                     this.method_852().field_2876.getValue()
                  );
            }

            if (!var7.isBlank()) {
               Class_1016.field_3143
                  .method_9(
                     var1,
                     var7,
                     (float)(this.field_418.getX() + 4) + Class_1016.field_3143.method_221(this.field_2780.substring(0, this.field_2781)),
                     (float)this.field_418.getY() + this.method_850() - this.method_169() + (float)this.field_419,
                     this.method_852().field_2876.getValue()
                  );
            }

            var2.pop();
         } else {
            String var9 = new Class_1303().method_2(this.field_3138.getValue()).method_2(this.field_3138.getName()).method_9("\u0001: \u0001");
            this.method_2(
               var2,
               var9,
               () -> Class_1016.field_3143
                     .method_9(
                        var1,
                        new Class_1303().method_2(this.field_3138.getValue()).method_2(this.field_3138.getName()).method_9("\u0001: \u0001"),
                        (float)(this.field_418.getX() + 4),
                        (float)this.field_418.getY() + this.method_850() - this.method_169() + (float)this.field_419,
                        this.method_852().field_2876.getValue()
                     )
            );
         }
      }
   }

   @Override
   public void method_68(int var1) {
      if (!this.method_911()) {
         if (this.field_3142) {
            Hub.method_755().method_1003();
            switch (var1) {
               case 86:
                  if (GLFW.glfwGetKey(field_4219.getWindow().getHandle(), 341) == 1) {
                     this.field_2780 = new StringBuilder(this.field_2780).insert(this.field_2781, field_4219.keyboard.getClipboard()).toString();
                  }
                  break;
               case 256:
                  this.field_3142 = false;
                  break;
               case 257:
                  this.field_3142 = false;
                  this.field_3138.method_78(this.field_2780);
                  this.field_2781 = this.field_2780.length();
                  break;
               case 259:
                  if (this.field_2780.length() == 0 || this.field_2781 == 0) {
                     return;
                  }

                  int var2 = this.field_2781 - 1;
                  if (this.field_2781 == this.field_2780.length()) {
                     this.field_2780 = this.field_2780.substring(0, this.field_2780.length() - 1);
                  } else if (var2 >= 0 && var2 <= this.field_2780.length()) {
                     this.field_2780 = new StringBuilder(this.field_2780).deleteCharAt(var2).toString();
                  }

                  this.field_2781--;
                  break;
               case 262:
                  if (this.field_2781 < this.field_2780.length()) {
                     this.field_2781++;
                  }
                  break;
               case 263:
                  if (this.field_2781 > 0) {
                     this.field_2781--;
                  }
            }
         }
      }
   }

   @Override
   public void method_9(char var1) {
      if (!this.method_911()) {
         if (this.field_3142) {
            this.field_2780 = new StringBuilder(this.field_2780).insert(this.field_2781, var1).toString();
            this.field_2781++;
         }
      }
   }

   public String method_453() {
      if (this.field_2779.method_9(500L)) {
         this.field_1309 = !this.field_1309;
         this.field_2779.reset();
      }

      return this.field_1309 ? "_" : "";
   }
}
