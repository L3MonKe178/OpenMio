package me.mioclient.module.render;

import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import me.mioclient.enum_.PreType;
import me.mioclient.event.Event_2;
import me.mioclient.event.Subscribe;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import nick.Settings;

public class GlintModule extends Module {
   public static final Identifier field_35 = Identifier.of("mio-mount", "textures/shine.png");
   public Setting<Color> field_36;

   public GlintModule() {
      super("Glint", "Changes your enchantment glint's color.", Category.RENDER);
      Settings.initialize(this);
   }

   @Subscribe
   public void method_2(Event_2 var1) {
      if (var1.method_213() == PreType.PRE) {
         Color var2 = this.field_36.getValue();
         MinecraftClient.getInstance().getTextureManager().getTexture(field_35).setFilter(true, false);
         RenderSystem.setShaderTexture(0, field_35);
         RenderSystem.setShaderColor(
            (float)var2.getRed() / Float.intBitsToFloat(1132396544),
            (float)var2.getGreen() / Float.intBitsToFloat(1132396544),
            (float)var2.getBlue() / Float.intBitsToFloat(1132396544),
            Float.intBitsToFloat(1065353216)
         );
      } else {
         RenderSystem.setShaderColor(
            Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216)
         );
      }
   }
}
