package me.mioclient.internal;

import com.mojang.blaze3d.systems.RenderSystem;
import java.nio.file.Path;
import java.util.List;
import me.mioclient.enum_.Class_0836;
import net.minecraft.client.gui.DrawContext;

public class Class_0311 extends Class_1117 {
   public final Class_0040 field_1034 = new Class_0040(this);
   public final Class_0516 field_1035 = new Class_0516(this);
   public Class_0836 field_1036 = Class_0836.MODULES;
   public final Class_0055 field_1037 = new Class_0055();

   public Class_0311() {
      super();
      this.field_3462.add(this.field_1034);
      this.field_1035.setX(this.field_1034.getX() + this.field_1034.method_216() + 3);
      this.field_3462.add(this.field_1035);
   }

   @Override
   public void method_2(DrawContext var1, int var2, int var3, float var4) {
      this.field_1037.method_2(!field_4219.isWindowFocused(), 250L);
      float var5 = this.field_1037.method_45();
      if (var5 != 0.0F) {
         RenderSystem.setShaderColor(
            Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1061158912) * var5
         );
         var1.drawCenteredTextWithShadow(
            field_4219.textRenderer, "Drop preset files here", var1.getScaledWindowWidth() / 2, var1.getScaledWindowHeight() / 2, -1
         );
         RenderSystem.setShaderColor(
            Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216)
         );
      }
   }

   public void filesDragged(List<Path> paths) {
      Class_0227.method_2(this, paths);
   }

   public Class_0040 method_367() {
      return this.field_1034;
   }

   public Class_0516 method_368() {
      return this.field_1035;
   }

   public Class_0836 method_369() {
      return this.field_1036;
   }

   public void method_9(Class_0836 var1) {
      this.field_1036 = var1;
   }
}
