package me.mioclient.record;

import java.awt.Color;
import me.mioclient.api.MioAPI;
import me.mioclient.internal.Class_0612;
import me.mioclient.internal.RenderUtil;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Box;

public final class Class_1030 {
   public final int field_3186;
   public final int field_3187;
   public final int field_3188;

   public Class_1030(int var1, int var2, int var3) {
      super();
      this.field_3186 = var1;
      this.field_3187 = var2;
      this.field_3188 = var3;
   }

   public void method_2(MatrixStack var1, Color var2, int var3, float var4) {
      double var5 = (double)var3;
      if (var3 == -1) {
         var5 = MioAPI.field_4219.player.getLerpedPos(RenderUtil.method_776()).y;
      }

      Box var7 = this.method_78(var5);
      Class_0612.method_2(var1, var7, var2, var4);
   }

   public Box method_78(double var1) {
      int var3 = this.field_3188 / 2;
      return new Box(
         (double)(this.field_3186 - var3), var1, (double)(this.field_3187 - var3), (double)(this.field_3186 + var3), var1, (double)(this.field_3187 + var3)
      );
   }

   public int method_401() {
      return this.field_3186;
   }

   public int method_791() {
      return this.field_3187;
   }

   public int method_926() {
      return this.field_3188;
   }
}
