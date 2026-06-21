package me.mioclient.module.abstract_;

import me.mioclient.internal.Class_0149;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.ChatHud;

public class AbstractModule_14 extends AbstractModule_26 {
   public AbstractModule_14() {
      super("Chat");
      Class_0149 var1 = new Class_0149(this);
      this.method_2(var1);
   }

   public float method_647() {
      if (this.field_1844.method_193() == null) {
         return 0.0F;
      } else {
         float var1 = (float)field_4219.getWindow().getScaledHeight();
         float var2 = this.method_174();
         float var3 = var1 - Float.intBitsToFloat(1109393408);
         return (this.field_1844.method_60() - var3) / var2 + this.method_575();
      }
   }

   public float method_648() {
      if (this.field_1844.method_193() == null) {
         return 0.0F;
      } else {
         float var1 = this.method_174();
         return this.field_1844.method_59() / var1;
      }
   }

   public float method_174() {
      return ((Double)field_4219.options.getChatScale().getValue()).floatValue();
   }

   public float method_575() {
      return (float)ChatHud.getHeight((double)((Double)field_4219.options.getChatHeightUnfocused().getValue()).floatValue());
   }

   @Override
   public void method_2(DrawContext var1) {
      super.method_2(var1);
   }

   @Override
   public float[] method_31() {
      return new float[]{
         ((Double)field_4219.options.getChatWidth().getValue()).floatValue() * Float.intBitsToFloat(1133248512)
            + Float.intBitsToFloat(1109393408)
            + Float.intBitsToFloat(1094713344) * this.method_174(),
         this.method_575() * this.method_174()
      };
   }
}
