package me.mioclient.enum_;

import java.awt.Color;
import me.mioclient.api.Class_0013;
import me.mioclient.api.Class_0964;
import me.mioclient.module.render.ChamsModule;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public enum Class_0670 implements Class_0013, Class_0964 {
   BOTH("Both"),
   FILL("Fill") {
      @Override
      public Color[] method_2(ChamsModule var1) {
         return new Color[]{field_2973, var1.field_248.getValue()};
      }
   },
   LINE("Line") {
      @Override
      public Color[] method_2(ChamsModule var1) {
         return new Color[]{var1.field_249.getValue(), field_2973};
      }
   },
   PLAIN("Plain") {
      @Override
      public void method_2(ChamsModule var1, Entity var2, MatrixStack var3) {
      }
   },
   OFF("Off");

   public final String field_2152;

    Class_0670(String var3) {
      this.field_2152 = var3;
   }

   @Override
   public String getName() {
      return this.field_2152;
   }

   @Override
   public boolean method_670() {
      return this != OFF;
   }
}
