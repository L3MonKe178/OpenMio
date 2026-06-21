package me.mioclient.enum_;

import com.mojang.blaze3d.systems.RenderSystem;
import me.mioclient.api.Nameable;

public enum Class_0800 implements Nameable {
   NORMAL("Normal"),
   PROTANOPIA("Protanopia") {
      @Override
      public float[] method_727() {
         return new float[]{0.3F, 0.2F, 1.0F, 1.0F};
      }

      @Override
      public void method_5(Runnable var1) {
         float[] var2 = this.method_727();
         RenderSystem.setShaderColor(var2[0], var2[1], var2[2], var2[3]);
         var1.run();
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      }
   },
   DEUTERANOPIA("Deuteranopia") {
      @Override
      public float[] method_727() {
         return new float[]{0.3F, 0.0F, 1.0F, 1.0F};
      }

      @Override
      public void method_5(Runnable var1) {
         float[] var2 = this.method_727();
         RenderSystem.setShaderColor(var2[0], var2[1], var2[2], var2[3]);
         var1.run();
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      }
   },
   TRITANOPIA("Tritanopia") {
      @Override
      public float[] method_727() {
         return new float[]{1.0F, 0.7F, 0.0F, 1.0F};
      }

      @Override
      public void method_5(Runnable var1) {
         float[] var2 = this.method_727();
         RenderSystem.setShaderColor(var2[0], var2[1], var2[2], var2[3]);
         var1.run();
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      }
   };

   public final String field_2524;

    Class_0800(String var3) {
      this.field_2524 = var3;
   }

   @Override
   public String getName() {
      return this.field_2524;
   }

   public void method_5(Runnable var1) {
      var1.run();
   }

   public float[] method_727() {
      return new float[]{1.0F, 1.0F, 1.0F, 1.0F};
   }
}
