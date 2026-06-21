package me.mioclient.internal;

import java.lang.reflect.Field;
import me.mioclient.api.Class_0597;
import me.mioclient.api.MioAPI;
import me.mioclient.enum_.Class_0016;
import me.mioclient.record.Class_0661;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.EnderDragonEntityRenderer;
import net.minecraft.client.render.entity.EnderDragonEntityRenderer.DragonEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class Class_0192 implements MioAPI, Class_0597<EnderDragonEntity> {
   private static final Field LEFT_FRONT_LEG = method_dragonField("leftFrontLeg");
   private static final Field LEFT_FRONT_LEG_TIP = method_dragonField("leftFrontLegTip");
   private static final Field LEFT_FRONT_FOOT = method_dragonField("leftFrontFoot");
   private static final Field LEFT_HIND_LEG = method_dragonField("leftHindLeg");
   private static final Field LEFT_HIND_LEG_TIP = method_dragonField("leftHindLegTip");
   private static final Field LEFT_HIND_FOOT = method_dragonField("leftHindFoot");
   private static final Field RIGHT_FRONT_LEG = method_dragonField("rightFrontLeg");
   private static final Field RIGHT_FRONT_LEG_TIP = method_dragonField("rightFrontLegTip");
   private static final Field RIGHT_FRONT_FOOT = method_dragonField("rightFrontFoot");
   private static final Field RIGHT_HIND_LEG = method_dragonField("rightHindLeg");
   private static final Field RIGHT_HIND_LEG_TIP = method_dragonField("rightHindLegTip");
   private static final Field RIGHT_HIND_FOOT = method_dragonField("rightHindFoot");

   private static Field method_dragonField(String name) {
      try {
         Field f = DragonEntityModel.class.getDeclaredField(name);
         f.setAccessible(true);
         return f;
      } catch (NoSuchFieldException e) {
         throw new RuntimeException(e);
      }
   }

   private static ModelPart method_dragonPart(Field f, DragonEntityModel model) {
      try {
         return (ModelPart) f.get(model);
      } catch (IllegalAccessException e) {
         throw new RuntimeException(e);
      }
   }

   public Class_0192() {
      super();
   }

   public void method_2(EnderDragonEntity var1, float var2, MatrixStack var3) {
      EnderDragonEntityRenderer var4 = (EnderDragonEntityRenderer)MinecraftClient.getInstance().getEntityRenderDispatcher().getRenderer(var1);
      var3.push();
      float var5 = (float)var1.getSegmentProperties(7, var2)[0];
      float var6 = (float)(var1.getSegmentProperties(5, var2)[1] - var1.getSegmentProperties(10, var2)[1]);
      var3.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-var5));
      var3.multiply(RotationAxis.POSITIVE_X.rotationDegrees(var6 * 10.0F));
      var3.translate(0.0F, 0.0F, 1.0F);
      var3.scale(-1.0F, -1.0F, 1.0F);
      var3.translate(0.0F, -1.501F, 0.0F);
      var4.model.animateModel(var1, 0.0F, 0.0F, var2);
      this.method_2(var3, var1, var4.model, var2);
      var3.pop();
   }

   public void method_2(MatrixStack var1, EnderDragonEntity var2, DragonEntityModel var3, float var4) {
      Class_0661 var5 = new Class_0661(var1, Class_0016.BOTH);
      var1.push();
      float var6 = MathHelper.lerp(var4, var2.prevWingPosition, var2.wingPosition);
      var3.jaw.pitch = (float)(Math.sin((double)(var6 * (float) (Math.PI * 2))) + 1.0) * 0.2F;
      float var7 = (float)(Math.sin((double)(var6 * (float) (Math.PI * 2) - 1.0F)) + 1.0);
      var7 = (var7 * var7 + var7 * 2.0F) * 0.05F;
      var1.translate(0.0F, var7 - 2.0F, -3.0F);
      var1.multiply(RotationAxis.POSITIVE_X.rotationDegrees(var7 * 2.0F));
      float var8 = 0.0F;
      float var9 = 20.0F;
      float var10 = -12.0F;
      float var11 = 1.5F;
      double[] var12 = var2.getSegmentProperties(6, var4);
      float var13 = MathHelper.wrapDegrees((float)(var2.getSegmentProperties(5, var4)[0] - var2.getSegmentProperties(10, var4)[0]));
      float var14 = MathHelper.wrapDegrees((float)(var2.getSegmentProperties(5, var4)[0] + (double)(var13 / 2.0F)));
      float var15 = var6 * (float) (Math.PI * 2);

      for (int var17 = 0; var17 < 5; var17++) {
         double[] var18 = var2.getSegmentProperties(5 - var17, var4);
         float var16 = (float)Math.cos((double)((float)var17 * 0.45F + var15)) * 0.15F;
         var3.neck.yaw = MathHelper.wrapDegrees((float)(var18[0] - var12[0])) * Constants.field_690 * 1.5F;
         var3.neck.pitch = var16 + var2.getChangeInNeckPitch(var17, var12, var18) * Constants.field_690 * 1.5F * 5.0F;
         var3.neck.roll = -MathHelper.wrapDegrees((float)(var18[0] - (double)var14)) * Constants.field_690 * 1.5F;
         var3.neck.pivotY = var9;
         var3.neck.pivotZ = var10;
         var3.neck.pivotX = var8;
         var9 += MathHelper.sin(var3.neck.pitch) * 10.0F;
         var10 -= MathHelper.cos(var3.neck.yaw) * MathHelper.cos(var3.neck.pitch) * 10.0F;
         var8 -= MathHelper.sin(var3.neck.yaw) * MathHelper.cos(var3.neck.pitch) * 10.0F;
         Class_0482.method_2(var5, var3.neck);
      }

      var3.head.pivotY = var9;
      var3.head.pivotZ = var10;
      var3.head.pivotX = var8;
      double[] var27 = var2.getSegmentProperties(0, var4);
      var3.head.yaw = MathHelper.wrapDegrees((float)(var27[0] - var12[0])) * Constants.field_690;
      var3.head.pitch = MathHelper.wrapDegrees(var2.getChangeInNeckPitch(6, var12, var27)) * Constants.field_690 * 1.5F * 5.0F;
      var3.head.roll = -MathHelper.wrapDegrees((float)(var27[0] - (double)var14)) * Constants.field_690;
      Class_0482.method_2(var5, var3.head);
      var1.push();
      var1.translate(0.0F, 1.0F, 0.0F);
      var1.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(-var13 * 1.5F));
      var1.translate(0.0F, -1.0F, 0.0F);
      var3.body.roll = 0.0F;
      Class_0482.method_2(var5, var3.body);
      float var29 = var6 * (float) (Math.PI * 2);
      var3.leftWing.pitch = 0.125F - (float)Math.cos((double)var29) * 0.2F;
      var3.leftWing.yaw = -0.25F;
      var3.leftWing.roll = -((float)(Math.sin((double)var29) + 0.125)) * 0.8F;
      var3.leftWingTip.roll = (float)(Math.sin((double)(var29 + 2.0F)) + Constants.field_688) * 0.75F;
      var3.rightWing.pitch = var3.leftWing.pitch;
      var3.rightWing.yaw = -var3.leftWing.yaw;
      var3.rightWing.roll = -var3.leftWing.roll;
      var3.rightWingTip.roll = -var3.leftWingTip.roll;
      this.method_2(
         var5, var7, var3.leftWing,
         method_dragonPart(LEFT_FRONT_LEG, var3),
         method_dragonPart(LEFT_FRONT_LEG_TIP, var3),
         method_dragonPart(LEFT_FRONT_FOOT, var3),
         method_dragonPart(LEFT_HIND_LEG, var3),
         method_dragonPart(LEFT_HIND_LEG_TIP, var3),
         method_dragonPart(LEFT_HIND_FOOT, var3)
      );
      this.method_2(
         var5,
         var7,
         var3.rightWing,
         method_dragonPart(RIGHT_FRONT_LEG, var3),
         method_dragonPart(RIGHT_FRONT_LEG_TIP, var3),
         method_dragonPart(RIGHT_FRONT_FOOT, var3),
         method_dragonPart(RIGHT_HIND_LEG, var3),
         method_dragonPart(RIGHT_HIND_LEG_TIP, var3),
         method_dragonPart(RIGHT_HIND_FOOT, var3)
      );
      var1.pop();
      float var26 = -MathHelper.sin(var6 * (float) (Math.PI * 2)) * 0.0F;
      var15 = var6 * (float) (Math.PI * 2);
      var9 = 10.0F;
      var10 = 60.0F;
      var8 = 0.0F;
      var12 = var2.getSegmentProperties(11, var4);

      for (int var19 = 0; var19 < 12; var19++) {
         var27 = var2.getSegmentProperties(12 + var19, var4);
         var26 += MathHelper.sin((float)var19 * 0.45F + var15) * 0.05F;
         var3.neck.yaw = (MathHelper.wrapDegrees((float)(var27[0] - var12[0])) * 1.5F + 180.0F) * Constants.field_690;
         var3.neck.pitch = var26 + (float)(var27[1] - var12[1]) * Constants.field_690 * 1.5F * 5.0F;
         var3.neck.roll = MathHelper.wrapDegrees((float)(var27[0] - (double)var14)) * Constants.field_690 * 1.5F;
         var3.neck.pivotY = var9;
         var3.neck.pivotZ = var10;
         var3.neck.pivotX = var8;
         var9 += MathHelper.sin(var3.neck.pitch) * 10.0F;
         var10 -= MathHelper.cos(var3.neck.yaw) * MathHelper.cos(var3.neck.pitch) * 10.0F;
         var8 -= MathHelper.sin(var3.neck.yaw) * MathHelper.cos(var3.neck.pitch) * 10.0F;
         Class_0482.method_2(var5, var3.neck);
      }

      var1.pop();
   }

   public void method_2(
      Class_0661 var1, float var2, ModelPart var3, ModelPart var4, ModelPart var5, ModelPart var6, ModelPart var7, ModelPart var8, ModelPart var9
   ) {
      var7.pitch = 1.0F + var2 * 0.1F;
      var8.pitch = (float)(Constants.field_688 + (double)(var2 * 0.1F));
      var9.pitch = 0.75F + var2 * 0.1F;
      var4.pitch = 1.3F + var2 * 0.1F;
      var5.pitch = (float)(-Constants.field_688 - (double)(var2 * 0.1F));
      var6.pitch = 0.75F + var2 * 0.1F;
      Class_0482.method_2(var1, var3);
      Class_0482.method_2(var1, var4);
      Class_0482.method_2(var1, var7);
   }
}
