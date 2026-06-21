package me.mioclient.internal;

import com.mojang.blaze3d.platform.GlStateManager;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_0151;
import me.mioclient.module.render.ShaderModule;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class Class_1130 extends Class_0914 {
   public static final Identifier field_3486 = Identifier.of("mio-mount", "textures/overlay.png");
   public static ShaderModule field_786 = Hub.field_2595.method_78(ShaderModule.class);

   public Class_1130() {
      super();
      this.method_251("solid");
   }

   @Override
   public boolean method_291() {
      return field_786.isToggled() && field_786.field_2017.getValue() == Class_0151.SOLID;
   }

   @Override
   public boolean method_31(Entity var1) {
      return field_786 != null && var1 != null ? field_786.method_98(var1) : false;
   }

   @Override
   public void method_292() {
      this.field_2303.method_2("u_Texture", 0);
      int var1 = field_4219.getTextureManager().getTexture(field_3486).getGlId();
      GlStateManager._activeTexture(33985);
      GlStateManager._bindTexture(var1);
      this.field_2303.method_2("u_Image", field_786.field_2029.getValue());
      this.field_2303.method_2("u_Overlay", 1);
      this.field_2303.method_2("u_OverlayAlpha", (double)field_786.method_650());
      this.field_2303.method_2("u_Width", field_786.method_652() ? 0 : field_786.field_2019.getValue());
      this.field_2303.method_2("u_FastLines", field_786.field_2020.getValue() || Class_1355.field_2003);
      this.field_2303.method_2("u_ShapeMode", 2);
      this.field_2303.method_2("u_GlowMultiplier", (double)field_786.field_2021.getValue().floatValue());
      this.field_2303.method_2("u_FillColor", field_786.method_651() ? Class_1081.field_3321 : field_786.method_30(field_786.field_2036.getValue()));
      this.field_2303.method_2("u_OutlineColor", field_786.field_2038.getValue());
      this.field_2303.method_2("u_Dots", field_786.field_2032.getValue().ordinal());
      this.field_2303.method_2("u_DotsRadius", field_786.field_2033.getValue());
      this.field_2303.method_2("u_DotsAlpha", (double)field_786.field_2034.getValue().floatValue());
   }
}
