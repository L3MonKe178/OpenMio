package me.mioclient.internal;

import com.mojang.datafixers.util.Pair;
import me.mioclient.api.Class_0597;
import me.mioclient.api.MioAPI;
import me.mioclient.enum_.Class_0016;
import me.mioclient.mixin.ducks.DuckBoatEntityRenderer;
import me.mioclient.record.Class_0661;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.CompositeEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.joml.Quaternionf;

public class Class_1234 implements MioAPI, Class_0597<BoatEntity> {
   public static final Quaternionf field_3881 = new Quaternionf();

   public Class_1234() {
      super();
   }

   public void method_2(BoatEntity var1, float var2, MatrixStack var3) {
      DuckBoatEntityRenderer var4 = (DuckBoatEntityRenderer)field_4219.getEntityRenderDispatcher().getRenderer(var1);
      var3.push();
      var3.translate(0.0F, 0.375F, 0.0F);
      var3.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0F - MathHelper.lerp(var2, var1.prevYaw, var1.getYaw())));
      float var5 = (float)var1.getDamageWobbleTicks() - var2;
      float var6 = var1.getDamageWobbleStrength() - var2;
      if (var6 < 0.0F) {
         var6 = 0.0F;
      }

      if (var5 > 0.0F) {
         var3.multiply(RotationAxis.POSITIVE_X.rotationDegrees(MathHelper.sin(var5) * var5 * var6 / 10.0F * (float)var1.getDamageWobbleSide()));
      }

      float var7 = var1.interpolateBubbleWobble(var2);
      if (!MathHelper.approximatelyEquals(var7, 0.0F)) {
         var3.multiply(field_3881.setAngleAxis(var1.interpolateBubbleWobble(var2) * Constants.field_690, 1.0F, 0.0F, 1.0F));
      }

      Pair<net.minecraft.util.Identifier, CompositeEntityModel<BoatEntity>> var8 = var4.mio$getTexturesAndModels().get(var1.getVariant());
      CompositeEntityModel<BoatEntity> var9 = (CompositeEntityModel<BoatEntity>)var8.getSecond();
      var3.scale(-1.0F, -1.0F, 1.0F);
      var3.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((float)Constants.field_685));
      var9.setAngles(var1, var2, 0.0F, -0.1F, 0.0F, 0.0F);

      for (ModelPart var11 : var9.getParts()) {
         Class_0482.method_2(new Class_0661(var3, Class_0016.BOTH), var11);
      }

      var3.pop();
   }
}
