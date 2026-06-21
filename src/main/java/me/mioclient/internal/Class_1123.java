package me.mioclient.internal;

import me.mioclient.Hub;
import me.mioclient.module.render.ESPModule;
import net.minecraft.entity.Entity;

public class Class_1123 extends Class_0914 {
   public static ESPModule field_3477 = Hub.field_2595.method_78(ESPModule.class);

   public Class_1123() {
      super();
      this.method_251("outline");
   }

   @Override
   public boolean method_291() {
      return field_3477 != null && field_3477.isToggled() && field_3477.field_3850 != null && field_3477.field_3850.method_126();
   }

   @Override
   public boolean method_31(Entity var1) {
      return false;
   }

   @Override
   public void method_292() {
      this.field_2303.method_2("u_Texture", 0);
      this.field_2303.method_2("u_Width", 2);
      this.field_2303.method_2("u_FastLines", true);
      this.field_2303.method_2("u_GlowMultiplier", Double.longBitsToDouble(4607182418800017408L));
   }
}
