package me.mioclient.internal;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import me.mioclient.enum_.Side;
import me.mioclient.record.Class_0362;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import org.lwjgl.glfw.GLFW;

public class Class_0571 extends Class_0370 {
   public Side field_1799 = Side.UP;
   public Side field_1800 = null;
   public final List<Class_0362> field_1801 = new ArrayList<>();
   public Class_0362 field_1802;
   public int field_1803;
   public boolean field_1804;

   public Class_0571(Class_0746 var1, int var2) {
      super(var1, var2);
      this.method_47();
   }

   @Override
   public void method_411() {
      if (!this.field_1804) {
         if (this.field_1800 != null) {
            this.field_1799 = this.field_1800;
            this.field_1800 = null;
         }

         if (!this.field_1801.isEmpty()) {
            Class_0362 var1 = this.field_1801.getFirst();
            Class_0362 var2 = var1.method_5(this.field_1799.method_376());
            if (var2.method_401() > this.field_1200 - 1) {
               var2 = new Class_0362(0, var2.method_402());
            } else if (var2.method_401() < 0) {
               var2 = new Class_0362(this.field_1200 - 1, var2.method_402());
            } else if (var2.method_402() > this.field_1201 - 1) {
               var2 = new Class_0362(var2.method_401(), 0);
            } else if (var2.method_402() < 0) {
               var2 = new Class_0362(var2.method_401(), this.field_1201 - 1);
            }

            if (this.field_1801.contains(var2)) {
               this.field_1804 = true;
               return;
            }

            this.field_1801.addFirst(var2);
            if (this.field_1801.getFirst().equals(this.field_1802)) {
               this.field_1802 = this.method_591();
               this.field_1803++;
            } else {
               this.field_1801.removeLast();
            }
         }
      }
   }

   @Override
   public void method_2(DrawContext var1, MatrixStack var2, double var3, double var5) {
      super.method_2(var1, var2, var3, var5);
      this.method_2(var2, this.field_1802.method_401(), this.field_1802.method_402(), Color.red);
      boolean var7 = false;

      for (Class_0362 var9 : this.field_1801) {
         if (!var7) {
            this.method_2(var2, var9.method_401(), var9.method_402(), Color.GREEN);
            var7 = true;
         } else {
            this.method_2(var2, var9.method_401(), var9.method_402(), this.field_1804 ? Color.GRAY : new Color(0, 150, 0));
         }
      }

      if (this.field_1804) {
         int var10000 = this.field_1803;
         String var10 = new TextBuilder().method_2(var10000).method_9("Score \u0001. Press R.");
         FontRenderer.field_3143
            .method_9(
               var1,
               var10,
               (float)(
                  (double)this.method_167().getX()
                     + (double)this.method_167().method_216() * Constants.field_688
                     - (double)FontRenderer.field_3143.method_221(var10) * Constants.field_688
               ),
               (float)((double)(this.method_167().getY() + this.field_419) + (double)this.method_66() * Constants.field_688),
               Color.white
            );
      } else {
         FontRenderer.field_3143
            .method_9(
               var1,
               String.valueOf(this.field_1803),
               (float)this.method_167().getX() + Float.intBitsToFloat(1069547520),
               (float)(this.method_167().getY() + this.field_419 + 1),
               Float.intBitsToFloat(1061158912),
               Color.white
            );
      }
   }

   @Override
   public void method_68(int var1) {
      Side var2 = null;
      switch (var1) {
         case 82:
            this.method_47();
            break;
         case 262:
            var2 = Side.RIGHT;
            break;
         case 263:
            var2 = Side.LEFT;
            break;
         case 264:
            var2 = Side.DOWN;
            break;
         case 265:
            var2 = Side.UP;
      }

      if (var2 != null && var2 != this.field_1799.method_377()) {
         this.field_1800 = var2;
      }
   }

   @Override
   public int method_412() {
      return GLFW.glfwGetKey(field_4219.getWindow().getHandle(), 32) == 1 ? 200 : 500;
   }

   @Override
   public int method_66() {
      return this.method_167().method_216();
   }

   public void method_47() {
      this.field_1801.clear();
      this.field_1801.add(new Class_0362(11, 11));
      this.field_1802 = this.method_591();
      this.field_1803 = 0;
      this.field_1804 = false;
   }

   public Class_0362 method_591() {
      Class_0362 var1 = this.field_1801.getFirst();

      while (this.field_1801.contains(var1)) {
         var1 = this.method_592();
      }

      return var1;
   }

   public Class_0362 method_592() {
      return new Class_0362((int)(Math.random() * (double)this.field_1200), (int)(Math.random() * (double)this.field_1201));
   }
}
