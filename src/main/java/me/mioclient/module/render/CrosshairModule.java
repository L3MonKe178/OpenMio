package me.mioclient.module.render;

import java.awt.Color;
import me.mioclient.event.Event_26;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0585;
import me.mioclient.internal.Class_0745;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.client.util.math.MatrixStack;
import nick.Settings;

public class CrosshairModule extends Module {
   public Setting<Boolean> field_3501;
   public Setting<Boolean> field_3502;
   public Setting<Boolean> field_3503;
   public Setting<Integer> field_3504;
   public Setting<Boolean> field_3505;
   public Setting<Boolean> field_3506;
   public Setting<Float> field_3507;
   public Setting<Integer> field_3508;
   public Setting<Integer> field_3509;
   public Setting<Integer> field_3510;
   public Setting<Color> field_3511;
   public final Class_0585 field_3512;

   public CrosshairModule() {
      super("Crosshair", "Allows you to customize your crosshair.", Category.RENDER);
      Settings.initialize(this);
      this.field_3512 = new Class_0585(Float.intBitsToFloat(1073741824), true);
      this.setDrawn(false);
   }

   @Subscribe
   public void method_2(Event_26 var1) {
      if (!this.method_535() && (!field_4219.gameRenderer.getCamera().isThirdPerson() || this.field_3501.getValue())) {
         float var2 = (float)(this.field_3508.getValue() != null ? this.field_3508.getValue().intValue() : 0);
         if (this.field_3505.getValue()) {
            if (this.field_3506.getValue()) {
               this.field_3512
                  .method_3(
                     field_4219.player.input.movementForward != 0.0F || field_4219.player.input.movementSideways != 0.0F || field_4219.player.input.jumping
                  );
            } else {
               this.field_3512
                  .method_36(
                     field_4219.player.input.movementForward != 0.0F || field_4219.player.input.movementSideways != 0.0F || field_4219.player.input.jumping
                  );
            }

            var2 += this.field_3507.getValue() * this.field_3512.method_45();
         }

         float var3 = (float)field_4219.getWindow().getScaleFactor();
         var1.method_10().push();
         var1.method_10().scale(Float.intBitsToFloat(1065353216) / var3, Float.intBitsToFloat(1065353216) / var3, Float.intBitsToFloat(1065353216) / var3);
         if (this.field_3503.getValue()) {
            MatrixStack var4 = var1.method_10();
            var4.push();
            var4.translate((float)(this.field_3504.getValue() != null ? this.field_3504.getValue().intValue() : 0), (float)(this.field_3504.getValue() != null ? this.field_3504.getValue().intValue() : 0), 0.0F);
            this.method_5(
               var1.method_10(),
               (float)(this.field_3509.getValue() != null ? this.field_3509.getValue().intValue() : 0),
               (float)(this.field_3510.getValue() != null ? this.field_3510.getValue().intValue() : 0) / Float.intBitsToFloat(1073741824),
               var2,
               Color.BLACK
            );
            var4.pop();
         }

         this.method_5(
            var1.method_10(),
            (float)(this.field_3509.getValue() != null ? this.field_3509.getValue().intValue() : 0),
            (float)(this.field_3510.getValue() != null ? this.field_3510.getValue().intValue() : 0) / Float.intBitsToFloat(1073741824),
            var2,
            this.field_3511.getValue()
         );
         var1.method_10().pop();
      }
   }

   public void method_5(MatrixStack var1, float var2, float var3, float var4, Color var5) {
      int var6 = field_4219.getWindow().getWidth() / 2;
      int var7 = field_4219.getWindow().getHeight() / 2;
      if (Math.ceil((double)var3) - (double)var3 != 0.0) {
         var4 += Float.intBitsToFloat(1056964608);
      } else {
         var6--;
         var7++;
      }

      if (this.field_3502.getValue()) {
         Class_0745.method_4(var1, (float)var6 - var3, (float)var7 - var3, (float)var6 + var3, (float)var7 + var3, var5);
      }

      if (var2 != 0.0F) {
         Class_0745.method_4(var1, (float)var6 - var3, (float)var7 - var4 - var2, (float)var6 + var3, (float)var7 - var4, var5);
         Class_0745.method_4(var1, (float)var6 + var4, (float)var7 - var3, (float)var6 + var4 + var2, (float)var7 + var3, var5);
         Class_0745.method_4(var1, (float)var6 - var3, (float)var7 + var4, (float)var6 + var3, (float)var7 + var4 + var2, var5);
         Class_0745.method_4(var1, (float)var6 - var4 - var2, (float)var7 - var3, (float)var6 - var4, (float)var7 + var3, var5);
      }
   }
}
