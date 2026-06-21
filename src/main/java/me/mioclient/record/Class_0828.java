package me.mioclient.record;

import java.awt.Color;
import java.util.Objects;
import me.mioclient.api.Class_1339;
import me.mioclient.deobf.Named;

public final class Class_0828 {
   public final Named field_2642;
   public final Class_1339<Color> field_2643;
   public final Class_1339<Color> field_2644;
   public final Class_1339<Float> field_2645;
   public final Class_1339<Float> field_2646;
   public final Class_1339<Boolean> field_2647;
   public final Class_1339<Boolean> field_2648;
   public final int field_2649;

   public Class_0828(
      Named var1,
      Class_1339<Color> var2,
      Class_1339<Color> var3,
      Class_1339<Float> var4,
      Class_1339<Float> var5,
      Class_1339<Boolean> var6,
      Class_1339<Boolean> var7,
      int var8
   ) {
      super();
      this.field_2642 = var1;
      this.field_2643 = var2;
      this.field_2644 = var3;
      this.field_2645 = var4;
      this.field_2646 = var5;
      this.field_2647 = var6;
      this.field_2648 = var7;
      this.field_2649 = var8;
   }

   public Color method_758() {
      return this.field_2643.getValue();
   }

   public Color method_759() {
      return this.field_2644.getValue();
   }

   public float method_760() {
      return this.field_2645.getValue();
   }

   public float method_761() {
      return this.field_2648.getValue() ? this.field_2646.getValue() * (float)this.field_2649 : 0.0F;
   }

   public boolean method_762() {
      return this.field_2647.getValue();
   }

   public boolean method_763() {
      return this.field_2648.getValue();
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         Class_0828 var2 = (Class_0828)var1;
         return Objects.equals(this.field_2642, var2.field_2642);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.field_2642);
   }

   public Named method_764() {
      return this.field_2642;
   }

   public Class_1339<Color> method_765() {
      return this.field_2643;
   }

   public Class_1339<Color> method_766() {
      return this.field_2644;
   }

   public Class_1339<Float> method_767() {
      return this.field_2645;
   }

   public Class_1339<Float> method_768() {
      return this.field_2646;
   }

   public Class_1339<Boolean> method_769() {
      return this.field_2647;
   }

   public Class_1339<Boolean> method_770() {
      return this.field_2648;
   }

   public int method_771() {
      return this.field_2649;
   }
}
