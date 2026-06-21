package me.mioclient.module.render;

import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_0034;
import me.mioclient.event.Event_10;
import me.mioclient.event.Event_26;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0144;
import me.mioclient.internal.Class_0211;
import me.mioclient.internal.Class_0838;
import me.mioclient.internal.Class_1081;
import me.mioclient.mixin.ducks.DuckPlayerInteractEntityC2SPacket;
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
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.util.math.MathHelper;
import nick.Settings;
import org.joml.Matrix4f;

public class HitmarkerModule extends Module {
   public Setting<Boolean> field_1595;
   public Setting<Float> field_1596;
   public Setting<Float> field_1597;
   public Setting<Float> field_1598;
   public Setting<Color> field_1599;
   public Setting<Color> field_1600;
   public Setting<Boolean> field_1601;
   public Setting<Class_0211> field_1602;
   public Setting<Float> field_1603;
   public Setting<Boolean> field_1604;
   public Setting<Boolean> field_1605;
   public Setting<Boolean> field_1606;
   public Setting<Boolean> field_1607;
   public Setting<Boolean> field_1608;
   public static CrosshairModule field_1609 = Hub.field_2595.method_78(CrosshairModule.class);
   public long field_1610;
   public int field_1611;
   public boolean field_1612;

   public HitmarkerModule() {
      super("Hitmarker", "Marks your screen when you hit entities.", Category.RENDER);
      Settings.initialize(this);
      this.field_1610 = -1L;
      this.field_1611 = 255;
      this.field_1612 = false;
      this.setDrawn(false);
      this.field_1602.method_31("Type");
   }

   @Override
   public void onToggle() {
      this.field_1610 = -1L;
      this.field_1611 = 255;
      this.field_1612 = false;
   }

   @Subscribe
   public void method_2(Event_26 var1) {
      if (!this.method_535()) {
         if (this.field_1610 != -1L) {
            if (this.field_1612) {
               this.field_1612 = false;
               if (this.field_1601.getValue()) {
                  Hub.field_2606.method_2(this.field_1602.getValue(), this.field_1603.getValue());
               }
            }

            long var2 = System.currentTimeMillis() - this.field_1610;
            long var4 = (long)(this.field_1597.getValue() * Float.intBitsToFloat(1148846080));
            if (var2 > var4) {
               this.field_1610 = -1L;
               return;
            }

            this.method_38(var2);
            if (this.field_1611 > 0 && this.field_1595.getValue()) {
               float var6 = (float)field_4219.getWindow().getScaledWidth() / Float.intBitsToFloat(1073741824);
               float var7 = (float)field_4219.getWindow().getScaledHeight() / Float.intBitsToFloat(1073741824) - Float.intBitsToFloat(1056964608);
               if (!field_1609.isToggled()) {
                  var6 = (float)((double)var6 - Double.longBitsToDouble(4602678819172646912L));
               } else {
                  var7 = (float)((double)var7 + Double.longBitsToDouble(4602678819172646912L));
               }

               Color var8 = Class_1081.method_9(this.field_1600.getValue(), this.field_1611);
               Color var9 = Class_1081.method_9(this.field_1599.getValue(), this.field_1611);
               float var10 = Float.intBitsToFloat(1073741824) + this.field_1596.getValue();

               for (int var11 = 0; var11 < 2; var11++) {
                  Color var12 = var11 == 0 ? var8 : var9;
                  var1.method_10().push();
                  if (field_1609.isToggled()) {
                     var1.method_10().translate(Double.longBitsToDouble(-4619792497756654797L), 0.0, 0.0);
                  }

                  method_2(
                     var1.method_10(), var12, var6 - var10, var7 - var10, var6 - Float.intBitsToFloat(1073741824), var7 - Float.intBitsToFloat(1073741824)
                  );
                  method_2(
                     var1.method_10(), var12, var6 - var10, var7 + var10, var6 - Float.intBitsToFloat(1073741824), var7 + Float.intBitsToFloat(1073741824)
                  );
                  method_2(
                     var1.method_10(), var12, var6 + Float.intBitsToFloat(1073741824), var7 - Float.intBitsToFloat(1073741824), var6 + var10, var7 - var10
                  );
                  method_2(
                     var1.method_10(), var12, var6 + Float.intBitsToFloat(1073741824), var7 + Float.intBitsToFloat(1073741824), var6 + var10, var7 + var10
                  );
                  var1.method_10().pop();
               }
            }
         }
      }
   }

   @Subscribe(
      method_800 = -100
   )
   public void method_7(Event_10 var1) {
      if (!this.method_535() && !var1.method_464()) {
         if (var1.method_127() instanceof PlayerInteractEntityC2SPacket var2 && Class_0144.method_9(var2) == Class_0034.ATTACK) {
            Entity var4 = field_4219.world.getEntityById(((DuckPlayerInteractEntityC2SPacket)var2).getEntityId());
            if (var4 != null && var4.getWorld() != null && var4 != field_4219.player && var4.getWorld().isClient && this.method_221(var4)) {
               this.field_1610 = System.currentTimeMillis();
               this.field_1611 = 255;
               this.field_1612 = true;
            }
         }
      }
   }

   public void method_38(long var1) {
      long var3 = (long)(this.field_1597.getValue() * Float.intBitsToFloat(1148846080));
      long var5 = (long)(this.field_1598.getValue() * Float.intBitsToFloat(1148846080));
      if (var1 < var3 - var5) {
         this.field_1611 = 255;
      } else {
         float var7 = Float.intBitsToFloat(1132396544) / this.field_1598.getValue();
         this.field_1611 = (int)((float)this.field_1611 - var7 * Hub.field_2601.method_154(Float.intBitsToFloat(1065353216)));
         this.field_1611 = MathHelper.clamp(this.field_1611, 0, 255);
      }
   }

   public boolean method_221(Entity var1) {
      return this.field_1605.getValue() && var1 instanceof PassiveEntity
         || this.field_1606.getValue() && var1 instanceof Monster
         || this.field_1607.getValue() && var1 instanceof PlayerEntity
         || this.field_1608.getValue() && var1 instanceof EndCrystalEntity;
   }

   public static void method_2(MatrixStack var0, Color var1, float var2, float var3, float var4, float var5) {
      float[] var6 = new float[]{
         (float)var1.getRed() / Float.intBitsToFloat(1132396544),
         (float)var1.getGreen() / Float.intBitsToFloat(1132396544),
         (float)var1.getBlue() / Float.intBitsToFloat(1132396544),
         (float)var1.getAlpha() / Float.intBitsToFloat(1132396544)
      };
      Matrix4f var7 = var0.peek().getPositionMatrix();
      BufferBuilder var8 = Tessellator.getInstance().begin(DrawMode.DEBUG_LINES, VertexFormats.POSITION_COLOR);
      var8.vertex(var7, var2, var3, 0.0F).color(var6[0], var6[1], var6[2], var6[3]);
      var8.vertex(var7, var4, var5, 0.0F).color(var6[0], var6[1], var6[2], var6[3]);
      Class_0838.field_2672.method_779();
      RenderSystem.setShader(GameRenderer::getPositionColorProgram);
      BufferRenderer.drawWithGlobalProgram(var8.end());
      Class_0838.field_2672.method_781();
   }
}
