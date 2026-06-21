package me.mioclient.internal;

import java.awt.Color;
import me.mioclient.Hub;
import me.mioclient.api.Class_1309;
import me.mioclient.module.client.HUDModule;
import net.minecraft.client.gui.DrawContext;

public final class Class_0051 implements Class_1309 {
   public static HUDModule hud = Hub.field_2595.method_78(HUDModule.class);
   public boolean field_116;
   public boolean field_117;
   public boolean field_118;
   public boolean field_119;
   public final Class_0055 field_120 = new Class_0055();
   public final Class_0055 field_121 = new Class_0055();

   public Class_0051() {
      super();
   }

   public void method_9(DrawContext var1, int var2, int var3, float var4) {
      int var5 = var1.getScaledWindowWidth();
      int var6 = var1.getScaledWindowHeight();
      boolean var7 = this.method_30((double)var2) || this.field_118;
      boolean var8 = this.method_16((double)var3) || this.field_119;
      this.field_120.method_2(var7 ? Float.intBitsToFloat(1124859904) : 0.0F, 250L);
      this.field_121.method_2(var8 && this.field_120.method_45() == 0.0F ? Float.intBitsToFloat(1124859904) : 0.0F, 250L);
      if (this.field_120.method_45() > 0.0F) {
         if (this.field_118) {
            hud.method_334(this.field_116 ? Math.abs(var2 - var5) : var2);
         }

         int var9 = Class_1081.method_5(Color.black, (int)this.field_120.method_45());
         Class_0745.method_9(var1.getMatrices(), 0.0F, 0.0F, (float)hud.method_341(), (float)var6, var9);
         Class_0745.method_9(var1.getMatrices(), (float)(var5 - hud.method_341()), 0.0F, (float)var5, (float)var6, var9);
      } else if (this.field_121.method_45() > 0.0F) {
         if (this.field_119) {
            hud.method_82(this.field_117 ? Math.abs(var3 - var6) : var3);
         }

         int var10 = Class_1081.method_5(Color.black, (int)this.field_121.method_45());
         Class_0745.method_9(var1.getMatrices(), 0.0F, 0.0F, (float)var5, (float)hud.method_342(), var10);
         Class_0745.method_9(var1.getMatrices(), 0.0F, (float)(var6 - hud.method_342()), (float)var5, (float)var6, var10);
      }
   }

   public void method_2(double var1, double var3, int var5) {
      if (var5 == 0) {
         this.field_118 = this.method_30(var1);
         if (!this.field_118) {
            this.field_119 = this.method_16(var3);
         }

         this.field_116 = var1 > (double)((float)field_4219.getWindow().getScaledWidth() / Float.intBitsToFloat(1073741824));
         this.field_117 = var3 > (double)((float)field_4219.getWindow().getScaledHeight() / Float.intBitsToFloat(1073741824));
      }
   }

   public void method_9(double var1, double var3, int var5) {
      if (var5 == 0) {
         this.field_118 = false;
         this.field_119 = false;
      }
   }

   public boolean method_30(double var1) {
      return var1 < (double)(hud.method_341() + 1) || var1 > (double)(field_4219.getWindow().getScaledWidth() - hud.method_341() - 1);
   }

   public boolean method_16(double var1) {
      return var1 < (double)(hud.method_342() + 1) || var1 > (double)(field_4219.getWindow().getScaledHeight() - hud.method_342() - 1);
   }
}
