package me.mioclient.enum_;

import java.awt.Color;
import me.mioclient.api.Class_0013;
import me.mioclient.module.exploit.NewChunksModule;

public enum Class_0334 implements Class_0013 {
   NEW("New"),
   OLD("Old") {
      @Override
      public Color[] method_2(NewChunksModule var1) {
         return new Color[]{var1.field_3375.getValue(), var1.field_3376.getValue()};
      }
   },
   BLOCKS("Blocks") {
      @Override
      public Color[] method_2(NewChunksModule var1) {
         return new Color[]{var1.field_3378.getValue(), var1.field_3379.getValue()};
      }
   },
   OVERFLOW("Overflow") {
      @Override
      public Color[] method_2(NewChunksModule var1) {
         return new Color[]{var1.field_3382.getValue(), var1.field_3383.getValue()};
      }
   },
   PLACEHOLDER("Placeholder") {
      @Override
      public Color[] method_2(NewChunksModule var1) {
         return null;
      }
   };

   public final String field_1125;

    Class_0334(String var3) {
      this.field_1125 = var3;
   }

   @Override
   public String getName() {
      return this.field_1125;
   }

   public Color[] method_2(NewChunksModule var1) {
      return new Color[]{var1.field_3372.getValue(), var1.field_3373.getValue()};
   }
}
