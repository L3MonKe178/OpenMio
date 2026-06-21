package me.mioclient.module.render;

import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import me.mioclient.Hub;
import me.mioclient.api.Class_0468;
import me.mioclient.api.Class_0718;
import me.mioclient.api.Class_0964;
import me.mioclient.enum_.Class_0670;
import me.mioclient.enum_.Class_1176;
import me.mioclient.enum_.Class_1285;
import me.mioclient.enum_.SettingMode;
import me.mioclient.event.Event_12;
import me.mioclient.event.Event_13;
import me.mioclient.event.Event_14;
import me.mioclient.event.Event_15;
import me.mioclient.event.Event_3;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0396;
import me.mioclient.internal.Class_0482;
import me.mioclient.internal.RotationManager;
import me.mioclient.internal.Class_0809;
import me.mioclient.internal.RenderUtil;
import me.mioclient.internal.Class_0878;
import me.mioclient.internal.Class_1081;
import me.mioclient.internal.Class_1135;
import me.mioclient.internal.Class_1355;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.combat.AutoCrystalModule;
import me.mioclient.module.player.FreecamModule;
import me.mioclient.setting.Setting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.Perspective;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.VertexFormat.DrawMode;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import nick.Settings;

public class ChamsModule extends Module {
   public static final Identifier field_218 = Identifier.of("mio-mount", "textures/shine.png");
   public static AutoCrystalModule field_219 = Hub.field_2595.method_78(AutoCrystalModule.class);
   public static NoRenderModule norender = Hub.field_2595.method_78(NoRenderModule.class);
   public static AnimationsModule animations = Hub.field_2595.method_78(AnimationsModule.class);
   public static final FreecamModule field_220 = Hub.field_2595.method_78(FreecamModule.class);
   public Setting<Integer> field_221;
   public Setting<Boolean> field_222;
   public Setting<Integer> field_223;
   public Setting<Float> field_224;
   public Setting<Boolean> field_225;
   public Setting<Boolean> field_226;
   public Setting<Boolean> field_227;
   public Setting<Integer> field_228;
   public Setting<Boolean> field_229;
   public Setting<Float> field_230;
   public Setting<Float> field_231;
   public Setting<Integer> field_232;
   public Setting<Boolean> field_233;
   public Setting<Class_0670> field_234;
   public Setting<Class_0670> field_235;
   public Setting<Class_0670> field_236;
   public Setting<Class_0670> field_237;
   public Setting<Class_0670> field_238;
   public Setting<Boolean> field_239;
   public Setting<Boolean> field_240;
   public Setting<Class_1285> field_241;
   public Setting<Boolean> field_242;
   public Setting<Boolean> field_243;
   public Setting<Float> field_244;
   public Setting<Float> field_245;
   public Setting<Boolean> field_246;
   public Setting<Boolean> field_247;
   public Setting<Color> field_248;
   public Setting<Color> field_249;
   public Setting<Color> field_250;
   public Setting<Color> field_251;
   public Setting<Color> field_252;
   public static boolean field_253 = false;
   public static boolean field_254 = false;
   public final Class_0809 field_255;
   public final Map<Class_0878, Long> field_256;
   public boolean field_257;

   public ChamsModule() {
      super("Chams", "Wallhack on entities.", Category.RENDER);
      Settings.initialize(this);
      this.field_255 = Class_0809.method_740();
      this.field_256 = new HashMap<>();
      this.field_241.method_31("PopMode");
      this.field_250.method_31("ShineColor");
      this.field_230.method_2("Static", SettingMode.MIN);
   }

   @Subscribe(
      method_800 = 200
   )
   public void method_5(Event_12 var1) {
      if (this.field_229.getValue()) {
         this.field_255.method_9(DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);
      }

      boolean var2 = (Integer)field_4219.options.getFov().getValue() > 115 && this.method_102();
      float var3 = var2 ? Float.intBitsToFloat(1075000115) : Float.intBitsToFloat(1065353216);
      Class_1135.method_1013();
      RenderSystem.enablePolygonOffset();
      RenderSystem.polygonOffset(Float.intBitsToFloat(1065353216), Float.intBitsToFloat(-900358272) * var3);

      for (Entity var5 : field_4219.world.getEntities()) {
         if (this.method_98(var5)) {
            if (this.field_226.getValue() && this.field_227.getValue() && !norender.method_230(var5) && this.field_228.getValue() > 0) {
               this.field_257 = true;
               RenderSystem.setShaderColor(
                  Float.intBitsToFloat(1065353216),
                  Float.intBitsToFloat(1065353216),
                  Float.intBitsToFloat(1065353216),
                  (float)(this.field_228.getValue() != null ? this.field_228.getValue().intValue() : 0) / Float.intBitsToFloat(1120403456)
               );
               RenderUtil.field_2672.method_2(var5, var1.method_880(), var1.method_10(), field_4219.getBufferBuilders().getEntityVertexConsumers());
               this.field_257 = false;
            }

            if (this.field_229.getValue()) {
               field_253 = true;
               RenderSystem.setShaderColor(
                  Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216)
               );
               var1.method_10().push();
               RenderUtil.method_9(var1.method_10());
               RenderUtil.field_2672.method_2(var5, var1.method_880(), var1.method_10(), field_4219.getBufferBuilders().getEntityVertexConsumers());
               var1.method_10().pop();
               field_253 = false;
            }
         }
      }

      if (this.field_226.getValue() && this.field_227.getValue()) {
         RenderSystem.setShaderColor(
            Float.intBitsToFloat(1065353216),
            Float.intBitsToFloat(1065353216),
            Float.intBitsToFloat(1065353216),
            (float)(this.field_228.getValue() != null ? this.field_228.getValue().intValue() : 0) / Float.intBitsToFloat(1120403456)
         );
         field_4219.getBufferBuilders().getEntityVertexConsumers().draw();
         RenderSystem.setShaderColor(
            Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216)
         );
      }

      RenderSystem.disablePolygonOffset();
      RenderSystem.polygonOffset(Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1247125376) * var3);
      Class_1135.method_1014();
   }

   @Subscribe(
      method_800 = -1000
   )
   public void method_7(Event_3 var1) {
      Class_0482.method_5((double)(this.field_224.getValue() != null ? this.field_224.getValue().floatValue() : 0.0f));

      for (Entity var3 : field_4219.world.getEntities()) {
         if (this.method_98(var3) && (this.method_105(var3).getValue() != null ? this.method_105(var3).getValue().method_670() : false)) {
            if (this.method_105(var3).getValue() != null) this.method_105(var3).getValue().method_2(this, var3, var1.method_10());
         }
      }

      if (this.field_239.getValue()) {
         synchronized (this.field_256) {
            this.field_256
               .entrySet()
               .removeIf(
                  var1x -> (float)var1x.getValue().longValue() + this.field_244.getValue() * Float.intBitsToFloat(1148846080)
                        < (float)System.currentTimeMillis()
               );
            this.field_256
               .forEach(
                  (var2, var3x) -> {
                     float var4x = Float.intBitsToFloat(1065353216)
                        - MathHelper.clamp(
                           (float)(System.currentTimeMillis() - var3x) / (this.field_244.getValue() * Float.intBitsToFloat(1148846080)),
                           0.0F,
                           Float.intBitsToFloat(1065353216)
                        );
                     var2.setPosition(
                        var2.getX(), var2.method_805().y + (double)((Float.intBitsToFloat(1065353216) - var4x) * this.field_245.getValue()), var2.getZ()
                     );
                     Color[] var5 = (this.field_241.getValue() != null ? this.field_241.getValue().method_2(this) : null);
                     if (var5 == null) return;
                     Class_0482.method_2(
                        Class_1081.method_2(var5[0], (float)var5[0].getAlpha() / Float.intBitsToFloat(1132396544) * var4x),
                        Class_1081.method_2(var5[1], (float)var5[1].getAlpha() / Float.intBitsToFloat(1132396544) * var4x)
                     );
                     Class_0482.method_2(var1.method_10(), var2);
                  }
               );
         }
      }

      if (this.field_227.getValue()) {
         field_4219.getBufferBuilders().getEntityVertexConsumers().draw();
      }

      if (this.field_229.getValue()) {
         double var9 = (Double)field_4219.options.getGlintSpeed().getValue();
         double var4 = (Double)field_4219.options.getGlintStrength().getValue();
         ((Class_0718)(Object)field_4219.options.getGlintSpeed()).forceSetValue((this.field_230.getValue() != null ? this.field_230.getValue().doubleValue() : 0.0));
         field_4219.options.getGlintStrength().setValue((this.field_231.getValue() != null ? this.field_231.getValue().doubleValue() : 0.0));
         Color var6 = this.field_250.getValue();
         RenderSystem.enableBlend();
         field_254 = true;
         RenderLayer.getArmorEntityGlint().startDrawing();
         MinecraftClient.getInstance().getTextureManager().getTexture(field_218).setFilter(true, false);
         RenderSystem.setShaderTexture(0, field_218);
         RenderSystem.setShaderColor(
            (float)var6.getRed() / Float.intBitsToFloat(1132396544),
            (float)var6.getGreen() / Float.intBitsToFloat(1132396544),
            (float)var6.getBlue() / Float.intBitsToFloat(1132396544),
            Float.intBitsToFloat(1065353216)
         );
         RenderSystem.disableDepthTest();
         this.field_255.method_529();
         RenderSystem.enableDepthTest();
         RenderLayer.getArmorEntityGlint().endDrawing();
         RenderSystem.setShaderColor(
            Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216)
         );
         field_254 = false;
         field_4219.options.getGlintSpeed().setValue(var9);
         field_4219.options.getGlintStrength().setValue(var4);
      }
   }

   @Subscribe
   public void method_9(Event_13 var1) {
      if (!Class_1355.field_4422) {
         if (!this.field_226.getValue() && this.method_98(var1.method_11()) && !this.field_257) {
            var1.method_463();
         }
      }
   }

   @Subscribe
   public void method_2(Event_14 var1) {
      if (this.method_98(var1.method_11())) {
         var1.method_294(15728880);
      }
   }

   @Subscribe
   public void method_9(Event_15 var1) {
      if (this.field_239.getValue() && (var1.method_1050() != Class_1176.DEATH || this.field_240.getValue())) {
         PlayerEntity var2 = var1.method_1048();
         if (var2 != field_4219.player) {
            Class_0878 var3 = new Class_0878(field_4219.world);
            var3.method_78(var2);
            var3.limbAnimator.pos = var3.limbAnimator.pos * Float.intBitsToFloat(1073741824);
            if (!this.field_242.getValue()) {
               var3.limbAnimator.pos = 0.0F;
               var3.limbAnimator.speed = 0.0F;
               var3.limbAnimator.prevSpeed = 0.0F;
            } else if (this.field_243.getValue()) {
               var3.method_14(true);
               var3.handSwingProgress = 0.0F;
               var3.limbAnimator.speed = (float)(Math.random() * Double.longBitsToDouble(4605380979056443392L) + Double.longBitsToDouble(4596373779801702400L));
               var3.limbAnimator.pos = 0.0F;
            }

            synchronized (this.field_256) {
               this.field_256.putIfAbsent(var3, System.currentTimeMillis());
            }
         }
      }
   }

   public boolean method_98(Entity var1) {
      if (LogoutSpotsModule.method_708()) {
         return false;
      } else {
         boolean var2;
         try {
            var2 = !RotationManager.method_4(var1.getBoundingBox())
               || field_4219.gameRenderer.getCamera().getPos().distanceTo(var1.getPos()) > (double)(this.field_221.getValue() != null ? this.field_221.getValue().intValue() : 0);
         } catch (Exception var4) {
            return false;
         }

         if (var2) {
            return false;
         } else if (Class_0396.method_7(var1)) {
            return false;
         } else if (var1 instanceof EndCrystalEntity var5) {
            return System.currentTimeMillis() - ((Class_0468)var5).getSpawnTime() > (long)Math.max(Hub.field_2602.method_983(), 50)
                  && var5.age < 10
                  && field_219.isToggled()
                  && field_219.field_4076.getValue()
                  && ((Class_0468)var5).isMioAttacked()
               ? false
               : (this.field_238.getValue() != null ? this.field_238.getValue().method_670() : false);
         } else if (var1 instanceof PlayerEntity) {
            boolean var3 = field_220.isToggled() || field_4219.options.getPerspective() != Perspective.FIRST_PERSON;
            if (this.field_236 == null || this.field_236.getValue() == null) return false;
            if (this.field_237 == null || this.field_237.getValue() == null) return false;
            return var1 != field_4219.player ? (this.field_236.getValue() != null ? this.field_236.getValue().method_670() : false) : (this.field_237.getValue() != null ? this.field_237.getValue().method_670() : false) && var3;
         } else {
            if (this.field_234 == null || this.field_234.getValue() == null) return false;
            if (this.field_235 == null || this.field_235.getValue() == null) return false;
            return var1 instanceof PassiveEntity && (this.field_234.getValue() != null ? this.field_234.getValue().method_670() : false) || var1 instanceof Monster && (this.field_235.getValue() != null ? this.field_235.getValue().method_670() : false);
         }
      }
   }

   public float method_99(Entity var1) {
      float var2 = (float)(this.field_223.getValue() != null ? this.field_223.getValue().intValue() : 0) / Float.intBitsToFloat(1120403456);
      double var3 = field_4219.gameRenderer.getCamera().getPos().distanceTo(var1.getPos()) / (double)(this.field_221.getValue() != null ? this.field_221.getValue().intValue() : 0);
      var3 -= (double)var2;
      return (float)(
         Double.longBitsToDouble(4607182418800017408L)
            - MathHelper.clamp(var3 / (double)(Float.intBitsToFloat(1065353216) - var2), 0.0, Double.longBitsToDouble(4607182418800017408L))
      );
   }

   public boolean method_100(Entity var1) {
      return this.isToggled() && this.field_227.getValue() ? this.method_98(var1) : false;
   }

   public boolean method_101(Entity var1) {
      boolean var2 = (this.method_105(var1).getValue() != null ? this.method_105(var1).getValue().method_670() : false) || var1 == field_4219.player;
      return this.isToggled() && var2 && this.field_229.getValue() && field_253;
   }

   public boolean method_102() {
      float var1 = Float.intBitsToFloat(1050253722);
      Vec3d var2 = field_4219.player.getEyePos();
      BlockPos var3 = BlockPos.ofFloored(var2);
      Box var4 = Box.of(var2, (double)var1, (double)var1, (double)var1);
      return !field_4219.world.isBlockSpaceEmpty(field_4219.player, var4)
         && field_4219.world.getBlockState(var3).getCollisionShape(field_4219.world, var3).isEmpty();
   }

   public boolean method_103() {
      return animations.method_129();
   }

   public Class_0809 method_104() {
      return this.field_255;
   }

   public Setting<? extends Class_0964> method_105(Entity var1) {
      if (var1 == field_4219.player) {
         return this.field_237;
      } else if (var1 instanceof EndCrystalEntity) {
         return this.field_238;
      } else if (var1 instanceof PlayerEntity) {
         return this.field_236;
      } else {
         return var1 instanceof PassiveEntity ? this.field_234 : this.field_235;
      }
   }
}
