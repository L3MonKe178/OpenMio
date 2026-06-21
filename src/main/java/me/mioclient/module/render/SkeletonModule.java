package me.mioclient.module.render;

import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import me.mioclient.Hub;
import me.mioclient.event.Event_3;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Constants;
import me.mioclient.internal.Class_0809;
import me.mioclient.mixin.ducks.DuckLivingEntityRenderer;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.VertexFormat.DrawMode;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import nick.Settings;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

public class SkeletonModule extends Module {
   public static AnimationsModule animations = Hub.field_2595.method_78(AnimationsModule.class);
   public Setting<Color> field_930;

   public SkeletonModule() {
      super("Skeleton", "Draws skeletons for players.", Category.RENDER);
      Settings.initialize(this);
      this.setDrawn(false);
   }

   @Subscribe
   public void method_9(Event_3 var1) {
      MatrixStack var2 = var1.method_10();
      float var3 = var1.method_880();
      RenderSystem.setShader(GameRenderer::getPositionColorProgram);
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      RenderSystem.disableDepthTest();
      RenderSystem.depthMask(MinecraftClient.isFancyGraphicsOrBetter());
      RenderSystem.enableCull();
      Tessellator var4 = Tessellator.getInstance();
      BufferBuilder var5 = var4.begin(DrawMode.DEBUG_LINES, VertexFormats.POSITION_COLOR);
      var2.push();

      for (AbstractClientPlayerEntity var7 : field_4219.world.getPlayers()) {
         if (!var7.isDead() && var7 != field_4219.player) {
            Color var8 = this.field_930.getValue();
            Vec3d var9 = this.method_9(var7, (double)var3);
            PlayerEntityRenderer var10 = (PlayerEntityRenderer)field_4219.getEntityRenderDispatcher().getRenderer(var7);
            PlayerEntityModel var11 = (PlayerEntityModel)var10.getModel();
            DuckLivingEntityRenderer var12 = (DuckLivingEntityRenderer)var10;
            float var13 = MathHelper.lerpAngleDegrees(var3, var7.prevBodyYaw, var7.bodyYaw);
            float var14 = MathHelper.lerp(var3, var7.prevPitch, var7.getPitch());
            if (!animations.method_129()) {
               float var15 = MathHelper.lerpAngleDegrees(var3, var7.prevBodyYaw, var7.bodyYaw);
               float var16 = MathHelper.lerpAngleDegrees(var3, var7.prevHeadYaw, var7.headYaw);
               float var17 = var16 - var15;
               float var18 = var12.mio$getAnimationProgress(var7, var3);
               float var19 = 0.0F;
               float var20 = 0.0F;
               if (!var7.hasVehicle() && var7.isAlive()) {
                  var19 = Math.min(var7.limbAnimator.getSpeed(var3), Float.intBitsToFloat(1065353216));
                  var20 = var7.limbAnimator.getPos(var3);
                  if (var7.isBaby()) {
                     var20 *= Float.intBitsToFloat(1077936128);
                  }
               }

               var11.animateModel(var7, var20, var19, var3);
               var11.setAngles(var7, var20, var19, var18, var17, var14);
            }

            boolean var24 = var7.isInSwimmingPose();
            boolean var25 = var7.isSneaking() || animations.method_130();
            boolean var26 = var7.isFallFlying();
            ModelPart var27 = var11.head;
            ModelPart var28 = var11.leftArm;
            ModelPart var29 = var11.rightArm;
            ModelPart var21 = var11.leftLeg;
            ModelPart var22 = var11.rightLeg;
            var2.translate(var9.x, var9.y, var9.z);
            if (var24) {
               var2.translate(0.0F, Float.intBitsToFloat(1051931443), 0.0F);
            }

            var2.multiply(
               new Quaternionf()
                  .setAngleAxis(
                     (double)(var13 + Float.intBitsToFloat(1127481344)) * Constants.field_687 / Double.longBitsToDouble(4640537203540230144L),
                     0.0,
                     Double.longBitsToDouble(-4616189618054758400L),
                     0.0
                  )
            );
            if (var24 || var26) {
               var2.multiply(
                  new Quaternionf()
                     .setAngleAxis(
                        (double)((float)Constants.field_685 + var14) * Constants.field_687 / Double.longBitsToDouble(4640537203540230144L),
                        Double.longBitsToDouble(-4616189618054758400L),
                        0.0,
                        0.0
                     )
               );
            }

            if (var24) {
               var2.translate(0.0F, Float.intBitsToFloat(-1082969293), 0.0F);
            }

            if (animations.method_128()) {
               var2.scale(animations.field_346.getValue(), animations.field_346.getValue(), animations.field_346.getValue());
            }

            Matrix4f var23 = var2.peek().getPositionMatrix();
            var5.vertex(
                  var23, 0.0F, var25 ? Float.intBitsToFloat(1058642330) : Float.intBitsToFloat(1060320051), var25 ? Float.intBitsToFloat(1047233823) : 0.0F
               )
               .color(var8.getRGB());
            var5.vertex(var23, 0.0F, var25 ? Float.intBitsToFloat(1065772646) : Float.intBitsToFloat(1068708659), 0.0F).color(var8.getRGB());
            var5.vertex(var23, Float.intBitsToFloat(-1094881116), var25 ? Float.intBitsToFloat(1065772646) : Float.intBitsToFloat(1068289229), 0.0F)
               .color(var8.getRGB());
            var5.vertex(var23, Float.intBitsToFloat(1052602532), var25 ? Float.intBitsToFloat(1065772646) : Float.intBitsToFloat(1068289229), 0.0F)
               .color(var8.getRGB());
            var5.vertex(
                  var23,
                  Float.intBitsToFloat(-1105618534),
                  var25 ? Float.intBitsToFloat(1058642330) : Float.intBitsToFloat(1060320051),
                  var25 ? Float.intBitsToFloat(1047233823) : 0.0F
               )
               .color(var8.getRGB());
            var5.vertex(
                  var23,
                  Float.intBitsToFloat(1041865114),
                  var25 ? Float.intBitsToFloat(1058642330) : Float.intBitsToFloat(1060320051),
                  var25 ? Float.intBitsToFloat(1047233823) : 0.0F
               )
               .color(var8.getRGB());
            var2.push();
            var2.translate(0.0F, var25 ? Float.intBitsToFloat(1065772646) : Float.intBitsToFloat(1068708659), 0.0F);
            this.method_2(var2, var27);
            var23 = var2.peek().getPositionMatrix();
            var5.vertex(var23, 0.0F, 0.0F, 0.0F).color(var8.getRGB());
            var5.vertex(var23, 0.0F, Float.intBitsToFloat(1041865114), 0.0F).color(var8.getRGB());
            var2.pop();
            var2.push();
            var2.translate(
               Float.intBitsToFloat(1041865114),
               var25 ? Float.intBitsToFloat(1058642330) : Float.intBitsToFloat(1060320051),
               var25 ? Float.intBitsToFloat(1047233823) : 0.0F
            );
            this.method_2(var2, var22);
            var23 = var2.peek().getPositionMatrix();
            var5.vertex(var23, 0.0F, 0.0F, 0.0F).color(var8.getRGB());
            var5.vertex(var23, 0.0F, Float.intBitsToFloat(-1088841318), 0.0F).color(var8.getRGB());
            var2.pop();
            var2.push();
            var2.translate(
               Float.intBitsToFloat(-1105618534),
               var25 ? Float.intBitsToFloat(1058642330) : Float.intBitsToFloat(1060320051),
               var25 ? Float.intBitsToFloat(1047233823) : 0.0F
            );
            this.method_2(var2, var21);
            var23 = var2.peek().getPositionMatrix();
            var5.vertex(var23, 0.0F, 0.0F, 0.0F).color(var8.getRGB());
            var5.vertex(var23, 0.0F, Float.intBitsToFloat(-1088841318), 0.0F).color(var8.getRGB());
            var2.pop();
            var2.push();
            var2.translate(Float.intBitsToFloat(1052602532), var25 ? Float.intBitsToFloat(1065772646) : Float.intBitsToFloat(1068289229), 0.0F);
            this.method_2(var2, var29);
            var23 = var2.peek().getPositionMatrix();
            var5.vertex(var23, 0.0F, 0.0F, 0.0F).color(var8.getRGB());
            var5.vertex(var23, 0.0F, Float.intBitsToFloat(-1089680179), 0.0F).color(var8.getRGB());
            var2.pop();
            var2.push();
            var2.translate(Float.intBitsToFloat(-1094881116), var25 ? Float.intBitsToFloat(1065772646) : Float.intBitsToFloat(1068289229), 0.0F);
            this.method_2(var2, var28);
            var23 = var2.peek().getPositionMatrix();
            var5.vertex(var23, 0.0F, 0.0F, 0.0F).color(var8.getRGB());
            var5.vertex(var23, 0.0F, Float.intBitsToFloat(-1089680179), 0.0F).color(var8.getRGB());
            var2.pop();
            if (var24) {
               var2.translate(0.0F, Float.intBitsToFloat(1064514355), 0.0F);
            }

            if (var24 || var26) {
               var2.multiply(
                  new Quaternionf()
                     .setAngleAxis(
                        (double)((float)Constants.field_685 + var14) * Constants.field_687 / Double.longBitsToDouble(4640537203540230144L),
                        Double.longBitsToDouble(4607182418800017408L),
                        0.0,
                        0.0
                     )
               );
            }

            if (var24) {
               var2.translate(0.0F, Float.intBitsToFloat(-1095552205), 0.0F);
            }

            var2.multiply(
               new Quaternionf()
                  .setAngleAxis(
                     (double)(var13 + Float.intBitsToFloat(1127481344)) * Constants.field_687 / Double.longBitsToDouble(4640537203540230144L),
                     0.0,
                     Double.longBitsToDouble(4607182418800017408L),
                     0.0
                  )
            );
            var2.translate(-var9.x, -var9.y, -var9.z);
         }
      }

      var2.pop();
      Class_0809.method_2(var5);
      RenderSystem.disableCull();
      RenderSystem.disableBlend();
      RenderSystem.enableDepthTest();
      RenderSystem.depthMask(true);
      RenderSystem.setShader(GameRenderer::getPositionColorProgram);
   }

   public void method_2(MatrixStack var1, ModelPart var2) {
      if (var2.roll != 0.0F) {
         var1.multiply(RotationAxis.POSITIVE_Z.rotation(var2.roll));
      }

      if (var2.yaw != 0.0F) {
         var1.multiply(RotationAxis.NEGATIVE_Y.rotation(var2.yaw));
      }

      if (var2.pitch != 0.0F) {
         var1.multiply(RotationAxis.NEGATIVE_X.rotation(var2.pitch));
      }
   }

   public Vec3d method_9(Entity var1, double var2) {
      double var4 = var1.prevX + (var1.getX() - var1.prevX) * var2 - field_4219.getEntityRenderDispatcher().camera.getPos().x;
      double var6 = var1.prevY + (var1.getY() - var1.prevY) * var2 - field_4219.getEntityRenderDispatcher().camera.getPos().y;
      double var8 = var1.prevZ + (var1.getZ() - var1.prevZ) * var2 - field_4219.getEntityRenderDispatcher().camera.getPos().z;
      return new Vec3d(var4, var6, var8);
   }
}
