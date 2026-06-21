package me.mioclient.internal;

import me.mioclient.Hub;
import me.mioclient.api.Class_0468;
import me.mioclient.api.Class_0597;
import me.mioclient.enum_.Class_0016;
import me.mioclient.mixin.ducks.DuckEndCrystalEntityRenderer;
import me.mioclient.module.combat.AutoCrystalModule;
import me.mioclient.module.render.AnimationsModule;
import me.mioclient.record.Class_0661;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.EndCrystalEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.util.math.RotationAxis;
import org.joml.Quaternionf;

public class Class_0275 implements Class_0597<EndCrystalEntity> {
   public static AutoCrystalModule ac = Hub.field_2595.method_78(AutoCrystalModule.class);
   public static AnimationsModule animations = Hub.field_2595.method_78(AnimationsModule.class);
   public static final float field_871 = (float)Math.sin(Math.PI / 4);
   public final Quaternionf field_872 = new Quaternionf();
   public EndCrystalEntity last;

   public Class_0275() {
      super();
   }

   public void method_2(EndCrystalEntity var1, float var2, MatrixStack var3) {
      this.last = var1;
      Class_0661 var4 = new Class_0661(var3, Class_0016.BOTH);
      DuckEndCrystalEntityRenderer var5 = (DuckEndCrystalEntityRenderer)MinecraftClient.getInstance().getEntityRenderDispatcher().getRenderer(var1);
      var3.push();
      float var6 = EndCrystalEntityRenderer.getYOffset(var1, var2);
      float var7 = ((float)var1.endCrystalAge + var2) * 3.0F;
      var3.scale(2.0F * this.method_174(), 2.0F * this.method_174(), 2.0F * this.method_174());
      var3.translate(0.0, -Constants.field_688, 0.0);
      if (var1.shouldShowBottom() && !animations.method_136()) {
         Class_0482.method_2(var4, var5.mio$getBottom());
      }

      var3.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(var7 * this.method_309()));
      var3.translate(0.0F, 1.5F + var6 / 2.0F, 0.0F);
      var3.multiply(this.field_872.setAngleAxis((float) (Math.PI / 3), field_871, 0.0F, field_871));
      if (!animations.method_134()) {
         Class_0482.method_2(var4, var5.mio$getFrame());
      }

      var3.scale(0.875F, 0.875F, 0.875F);
      var3.multiply(this.field_872.setAngleAxis((float) (Math.PI / 3), field_871, 0.0F, field_871));
      var3.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(var7 * this.method_309()));
      if (!animations.method_133()) {
         Class_0482.method_2(var4, var5.mio$getFrame());
      }

      var3.pop();
   }

   public float method_174() {
      return animations.isToggled() && animations.field_334.getValue() ? animations.field_337.getValue() : 1.0F;
   }

   public float method_309() {
      if (this.last.age < 10 && ac.isToggled() && ac.field_4076.getValue() && ((Class_0468)this.last).isMioAttacked()) {
         return 1.5F;
      } else {
         return animations.isToggled() && animations.field_334.getValue() ? animations.field_336.getValue() : 1.0F;
      }
   }
}
