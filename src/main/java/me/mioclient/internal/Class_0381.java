package me.mioclient.internal;

import java.util.function.Function;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_0046;
import me.mioclient.enum_.Class_1200;
import me.mioclient.record.Class_0702;
import me.mioclient.setting.Setting;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;

public class Class_0381 extends Class_0145 {
   public final Class_0050 field_1227;
   public final Setting<Class_0702> field_1228;
   public boolean field_1229;

   public Class_0381(int var1, Class_0050 var2) {
      super(var2.method_167(), var1);
      this.field_1227 = var2;
      this.field_1228 = null;
   }

   public Class_0381(int var1, Class_0050 var2, Setting<Class_0702> var3) {
      super(var2.method_167(), var1);
      this.field_1227 = var2;
      this.field_1228 = var3;
   }

   @Override
   public void method_2(double var1, double var3, int var5) {
      if (this.field_1227.field_111) {
         super.method_2(var1, var3, var5);
         if (this.method_5(var1, var3) && this.field_1227.field_111) {
            if (var5 == 0) {
               this.field_1229 = !this.field_1229;
               return;
            }

            if (this.field_1229) {
               this.modifyKeybind(var1x -> var1x.method_9(var5).method_9(true));
               this.field_1229 = !this.field_1229;
               return;
            }

            if (var5 == 1) {
               this.modifyKeybind(var0 -> var0.method_2(Class_0046.values()[(var0.method_78().ordinal() + 1) % Class_0046.values().length]));
            }
         }
      }
   }

   @Override
   public void method_68(int var1) {
      if (this.field_1229) {
         boolean var2 = var1 == 261 || var1 == 256;
         this.modifyKeybind(var2x -> var2x.method_9(var2 ? -1 : var1).method_9(false));
         this.field_1229 = false;
         Hub.method_755().method_1003();
      }
   }

   @Override
   public void method_2(DrawContext var1, MatrixStack var2, double var3, double var5) {
      super.method_2(var1, var2, var3, var5);
      if (this.field_421) {
         Class_1117.field_3460 = Class_1200.INPUT;
      }

      String var7 = this.field_421 ? this.getKeybind().method_78().getName() : "Key";
      String var8 = this.getKeybind().method_4();
      if (this.field_1229) {
         var8 = "...";
      }

      FontRenderer.field_3143
         .method_9(
            var1,
            new TextBuilder().method_2((Object)var8).method_2((Object)var7).method_9("\u0001 \u0001"),
            (float)(this.field_418.getX() + 4),
            (float)this.field_418.getY() + this.method_850() - this.method_169() + (float)this.field_419,
            this.method_852().field_2876.getValue()
         );
   }

   public void modifyKeybind(Function<Class_0702, Class_0702> var1) {
      if (this.field_1228 == null) {
         this.field_1227.field_109.modifyKeybind(var1);
      } else {
         this.field_1228.method_78((Class_0702)var1.apply(this.field_1228.getValue()));
      }
   }

   public Class_0702 getKeybind() {
      return this.field_1228 == null ? this.field_1227.field_109.getKeybind() : this.field_1228.getValue();
   }
}
