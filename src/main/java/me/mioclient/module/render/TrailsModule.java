package me.mioclient.module.render;

import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import me.mioclient.event.Event_12;
import me.mioclient.event.Event_3;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0242;
import me.mioclient.internal.Class_0245;
import me.mioclient.internal.Class_0329;
import me.mioclient.internal.Class_0838;
import me.mioclient.internal.Class_1081;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferRenderer;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.VertexFormat.DrawMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.entity.projectile.thrown.ExperienceBottleEntity;
import net.minecraft.util.math.MathHelper;
import nick.Settings;
import org.lwjgl.opengl.GL32C;

public class TrailsModule extends Module {
   public final HashMap<Integer, List<Class_0329>> field_3546 = new HashMap<>();
   public Setting<Boolean> field_3547;
   public Setting<Float> field_3548;
   public Setting<Boolean> field_3549;
   public Setting<Boolean> field_3550;
   public Setting<Boolean> field_3551;
   public Setting<Boolean> field_3552;
   public Setting<Boolean> field_3553;
   public Setting<Boolean> field_3554;
   public Setting<Float> field_3555;
   public Setting<Boolean> field_3556;
   public Setting<Float> field_3557;
   public Setting<Float> field_3558;
   public Setting<Color> field_3559;
   public Setting<Boolean> field_3560;
   public Setting<Double> field_3561;
   public final Class_0242 field_3562;

   public TrailsModule() {
      super("Trails", "Draws trails behind certain entities.", Category.RENDER);
      Settings.initialize(this);
      this.field_3562 = new Class_0242();
      this.setDrawn(false);
   }

   @Override
   public void onToggle() {
      this.field_3546.clear();
   }

   @Subscribe
   public void method_9(Event_3 var1) {
      if (!this.method_535() && this.field_3547.getValue()) {
         for (Entry var3 : this.field_3546.entrySet()) {
            if (((List)var3.getValue()).size() > 2) {
               this.method_2(var1.method_10(), (List<Class_0329>)var3.getValue());
            }
         }
      }
   }

   @Subscribe(
      method_800 = -100
   )
   public void method_9(Event_12 var1) {
      if (!this.method_535()) {
         if (this.field_3548.getValue() == 0.0F || this.field_3562.method_2((double)this.field_3548.getValue().floatValue(), TimeUnit.SECONDS)) {
            this.field_3562.reset();
            synchronized (this.field_3546) {
               if (field_4219.player.age % 4 == 0) {
                  int var3 = 0;

                  for (Integer var5 : this.field_3546.keySet()) {
                     List<Class_0329> var6 = this.field_3546.get(var5);
                     var6.removeIf(
                        var1x -> this.field_3556.getValue() && System.currentTimeMillis() - var1x.field_1103 > this.method_1022() + this.method_1023()
                     );
                     if (var6.size() == 0) {
                        var3 = var5;
                        break;
                     }
                  }

                  if (var3 != 0) {
                     this.field_3546.remove(var3);
                  }
               }

               for (Entity var10 : field_4219.world.getEntities()) {
                  if (this.method_98(var10)) {
                     this.field_3546.compute(var10.getId(), (var1x, var2) -> {
                        Class_0329 var3x = new Class_0329(var10.getLerpedPos(Class_0838.method_776()));
                        if (var2 == null) {
                           return new ArrayList<>(Collections.singleton(var3x));
                        } else {
                           var2.add(var3x);
                           return var2;
                        }
                     });
                  }
               }
            }
         }
      }
   }

   public void method_2(MatrixStack var1, List<Class_0329> var2) {
      Tessellator var3 = Tessellator.getInstance();
      BufferBuilder var4 = var3.begin(DrawMode.DEBUG_LINES, VertexFormats.POSITION_COLOR);
      Class_0838.field_2672.method_780();
      RenderSystem.setShader(GameRenderer::getPositionColorProgram);
      RenderSystem.enableBlend();
      GL32C.glLineWidth(this.field_3555.getValue());
      RenderSystem.lineWidth(this.field_3555.getValue());
      float[] var5 = Color.RGBtoHSB(this.field_3559.getValue().getRed(), this.field_3559.getValue().getGreen(), this.field_3559.getValue().getBlue(), null);

      for (int var6 = 0; var6 < var2.size() - 2; var6++) {
         Class_0838.field_2672
            .method_2(
               var1,
               var4,
               ((Class_0329)var2.get(var6)).method_384(),
               ((Class_0329)var2.get(var6 + 1)).method_384(),
               this.method_2(var5, (Class_0329)var2.get(var6)).hashCode(),
               this.method_2(var5, (Class_0329)var2.get(var6 + 1)).hashCode()
            );
      }

      BufferRenderer.drawWithGlobalProgram(var4.end());
      Class_0838.field_2672.method_782();
      GL32C.glLineWidth(Float.intBitsToFloat(1065353216));
   }

   public Color method_2(float[] var1, Class_0329 var2) {
      Color var3 = this.field_3560.getValue()
         ? Color.getHSBColor(
            (float)(
               Math.ceil((double)var2.method_383() / (Double.longBitsToDouble(4626322717216342016L) * this.field_3561.getValue()))
                  % (double)Class_0245.field_686
                  / (double)Class_0245.field_686
            ),
            var1[1],
            var1[2]
         )
         : this.field_3559.getValue();
      if (this.field_3556.getValue() && System.currentTimeMillis() - var2.method_383() >= this.method_1022()) {
         float var4 = Float.intBitsToFloat(1065353216)
            - (float)(System.currentTimeMillis() - var2.method_383() - this.method_1022()) / (float)this.method_1023();
         return Class_1081.method_9(var3, (int)MathHelper.clamp(var4 * (float)this.field_3559.getValue().getAlpha(), 0.0F, Float.intBitsToFloat(1132396544)));
      } else {
         return Class_1081.method_9(var3, this.field_3559.getValue().getAlpha());
      }
   }

   public boolean method_98(Entity var1) {
      return var1 == field_4219.player && this.field_3550.getValue()
         || var1 instanceof PlayerEntity && this.field_3551.getValue() && var1 != field_4219.player
         || var1 instanceof EnderPearlEntity && this.field_3552.getValue()
         || var1 instanceof ExperienceBottleEntity && this.field_3554.getValue()
         || var1 instanceof ArrowEntity && this.field_3553.getValue();
   }

   public long method_1022() {
      return (long)(this.field_3557.getValue() * Float.intBitsToFloat(1148846080));
   }

   public long method_1023() {
      return (long)(this.field_3558.getValue() * Float.intBitsToFloat(1148846080));
   }
}
