package me.mioclient.module.abstract_;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_1087;
import me.mioclient.internal.Class_0041;
import me.mioclient.internal.Class_0149;
import me.mioclient.internal.Class_0745;
import me.mioclient.internal.RenderUtil;
import me.mioclient.internal.Class_1081;
import me.mioclient.internal.TextBuilder;
import me.mioclient.module.exploit.NewChunksModule;
import me.mioclient.record.Class_0853;
import me.mioclient.setting.ColorSetting;
import me.mioclient.setting.CustomSetting;
import me.mioclient.setting.CustomSetting3;
import me.mioclient.setting.Setting;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;

public class AbstractModule_4 extends AbstractModule_26 {
   public static final NewChunksModule field_2958 = Hub.field_2595.method_78(NewChunksModule.class);
   public static final Identifier field_2959 = Identifier.of("mio", "textures/nav.png");
   public static final float field_2960 = Float.intBitsToFloat(1098907648);
   public static final float field_2961 = Float.intBitsToFloat(1090519040);
   public final Setting<Class_1087> field_2962 = this.add(new CustomSetting<>("Pointer", Class_1087.ARROW));
   public final Setting<Float> field_2963 = this.add(
      new CustomSetting3<>("Width", Float.intBitsToFloat(1117257728), Float.intBitsToFloat(1112014848), Float.intBitsToFloat(1140457472))
   );
   public final Setting<Float> field_2964 = this.add(
      new CustomSetting3<>("Height", Float.intBitsToFloat(1117257728), Float.intBitsToFloat(1112014848), Float.intBitsToFloat(1132068864))
   );
   public final Setting<Color> field_2965 = this.add(new ColorSetting("Pointer", Color.white));
   public final Setting<Color> field_2966 = this.add(new ColorSetting("Background", new Color(10, 10, 10, 50)));
   public final Setting<Color> field_2967 = this.add(new ColorSetting("Outline", new Color(10, 10, 10, 100)));

   public AbstractModule_4() {
      super("Map", "minimap");
      this.field_2965.method_31("PointerColor");
      this.field_2965.method_8();
      this.setDescription(new TextBuilder().method_2(String.valueOf(Formatting.YELLOW)).method_9("\u0001Primarily used for NewChunks"));
      Class_0149 var1 = new Class_0149(this);
      var1.method_2(this);
      this.method_2(var1);
   }

   @Override
   public void method_2(DrawContext var1) {
      float[] var2 = this.field_1844.method_193();
      if (var2 != null) {
         float var3 = RenderSystem.getShaderColor()[3];
         RenderSystem.setShaderColor(
            Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216)
         );
         Class_0745.method_474();
         RenderSystem.setShaderColor(Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216), var3);
         RenderUtil.field_2672
            .method_9(
               var1.getMatrices(),
               Float.intBitsToFloat(-1082130432),
               Float.intBitsToFloat(-1082130432),
               this.field_2963.getValue(),
               this.field_2964.getValue(),
               this.field_2966.getValue()
            );
         RenderUtil.field_2672
            .method_2(
               var1.getMatrices(),
               Float.intBitsToFloat(-1073741824),
               Float.intBitsToFloat(-1073741824),
               this.field_2963.getValue(),
               this.field_2964.getValue(),
               this.field_2967.getValue()
            );
         float var4 = this.field_2963.getValue() / Float.intBitsToFloat(1073741824);
         float var5 = this.field_2964.getValue() / Float.intBitsToFloat(1073741824);
         var1.getMatrices().push();
         var1.getMatrices().translate(var4, var5, 0.0F);

         for (BlockPos var7 : field_2958.method_973()) {
            ChunkPos var8 = new ChunkPos(var7);
            if (this.method_2(var8)) {
               Color var9 = Class_1081.method_2(field_2958.field_3387.getValue(), Float.intBitsToFloat(1053609165));
               this.method_2(var1, var8, var9);
            }
         }

         for (Class_0853 var11 : field_2958.method_972()) {
            if (this.method_2(var11.method_794()) && var11.method_795().method_2(field_2958) != null) {
               Color var12 = var11.method_795().method_2(field_2958)[0];
               var12 = Class_1081.method_2(var12, Float.intBitsToFloat(1048576000));
               ChunkPos var14 = var11.method_794();
               this.method_2(var1, var14, var12);
            }
         }

         var1.getMatrices().pop();
         Class_0041.method_9((int)this.field_1844.method_59(), (int)this.field_1844.method_60(), (int)var2[0], (int)var2[1]);
         Class_0745.method_474();
         Class_0041.method_57();
         this.method_2(var1, var4, var5);
      }
   }

   @Override
   public float[] method_31() {
      return new float[]{this.field_2963.getValue(), this.field_2964.getValue()};
   }

   public boolean method_2(ChunkPos var1) {
      int var2 = (int)Math.ceil(
         Math.hypot((double)(this.field_2963.getValue() != null ? this.field_2963.getValue().floatValue() : 0.0f), (double)(this.field_2964.getValue() != null ? this.field_2964.getValue().floatValue() : 0.0f))
            / Double.longBitsToDouble(4625196817309499392L)
      );
      return var1.getSquaredDistance(field_4219.player.getChunkPos()) <= var2 * var2;
   }

   public void method_2(DrawContext var1, ChunkPos var2, Color var3) {
      Vec3d var4 = field_4219.gameRenderer.getCamera().getPos();
      float var5 = (float)(var4.x - (double)var2.getStartX()) / Float.intBitsToFloat(1073741824) - Float.intBitsToFloat(1090519040);
      float var6 = (float)(var4.z - (double)var2.getStartZ()) / Float.intBitsToFloat(1073741824) - Float.intBitsToFloat(1090519040);
      var1.getMatrices().push();
      var1.getMatrices().translate(var5, var6, 0.0F);
      Class_0745.method_4(var1.getMatrices(), 0.0F, 0.0F, Float.intBitsToFloat(1090519040), Float.intBitsToFloat(1090519040), var3);
      var1.getMatrices().pop();
   }

   public void method_2(DrawContext var1, float var2, float var3) {
      var1.getMatrices().push();
      var1.getMatrices().translate(var2, var3, 0.0F);
      if (this.field_2962.getValue() == Class_1087.DOT) {
         var1.fill(-1, -1, 1, 1, (this.field_2965.getValue() != null ? this.field_2965.getValue().hashCode() : 0));
      } else if (this.field_2962.getValue() == Class_1087.ARROW) {
         var1.getMatrices().scale(Float.intBitsToFloat(1028443341), Float.intBitsToFloat(1028443341), Float.intBitsToFloat(1065353216));
         var1.getMatrices().multiply(RotationAxis.POSITIVE_Z.rotationDegrees(Float.intBitsToFloat(1110704128) + field_4219.gameRenderer.getCamera().getYaw()));
         GlStateManager._texParameter(3553, 10240, 9729);
         RenderSystem.setShaderColor(
            (float)(this.field_2965.getValue() != null ? this.field_2965.getValue().getRed() : 0) / Float.intBitsToFloat(1132396544),
            (float)(this.field_2965.getValue() != null ? this.field_2965.getValue().getGreen() : 0) / Float.intBitsToFloat(1132396544),
            (float)(this.field_2965.getValue() != null ? this.field_2965.getValue().getBlue() : 0) / Float.intBitsToFloat(1132396544),
            Float.intBitsToFloat(1065353216)
         );
         var1.drawTexture(field_2959, -128, -128, 0, 0, 256, 256);
         RenderSystem.setShaderColor(
            Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216)
         );
         GlStateManager._texParameter(3553, 10240, 9728);
      }

      var1.getMatrices().pop();
   }
}
