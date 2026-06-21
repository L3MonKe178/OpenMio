package me.mioclient.module.render;

import java.awt.Color;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_0387;
import me.mioclient.enum_.Class_0800;
import me.mioclient.event.Event_3;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0382;
import me.mioclient.internal.RenderUtil;
import me.mioclient.internal.Class_1081;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.abstract_.AbstractModule_21;
import me.mioclient.setting.Setting;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.Camera;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import nick.Settings;
import org.lwjgl.opengl.GL32C;

public class TracersModule extends Module {
   public static AbstractModule_21 field_521 = Hub.field_2595.method_78(AbstractModule_21.class);
   public Setting<Class_0387> field_522;
   public Setting<Float> field_523;
   public Setting<Float> field_524;
   public Setting<Color> field_525;
   public Setting<Boolean> field_526;
   public Setting<Boolean> field_527;
   public Setting<Boolean> field_528;
   public Setting<Boolean> field_529;
   public Setting<Boolean> field_530;

   public TracersModule() {
      super("Tracers", "Draws lines from your crosshair to the players nearby.", Category.RENDER);
      Settings.initialize(this);
   }

   @Subscribe
   public void method_9(Event_3 var1) {
      Camera var2 = field_4219.gameRenderer.getCamera();
      Vec3d var3 = new Vec3d(0.0, 0.0, Double.longBitsToDouble(4636737291354636288L))
         .rotateX(-((float)Math.toRadians((double)var2.getPitch())))
         .rotateY(-((float)Math.toRadians((double)var2.getYaw())))
         .add(field_4219.getEntityRenderDispatcher().camera.getPos());
      GL32C.glLineWidth(this.field_524.getValue());

      for (AbstractClientPlayerEntity var5 : field_4219.world.getPlayers()) {
         if (field_4219.player != var5
            && !(field_4219.gameRenderer.getCamera().getPos().distanceTo(var5.getPos()) > (double)(this.field_523.getValue() != null ? this.field_523.getValue().floatValue() : 0.0f))
            && (!this.field_529.getValue() || !Hub.field_2603.method_30(var5))
            && (!this.field_530.getValue() || Class_0382.method_29(var5))) {
            if (this.field_522 == null || this.field_522.getValue() == null) continue;
            Vec3d var6 = var5.getLerpedPos(RenderUtil.method_776()).add(0.0, (double)(var5.getHeight() * (this.field_522.getValue() != null ? this.field_522.getValue().method_438() : 0.5f)), 0.0);
            Color var7 = this.field_525.getValue();
            boolean var8 = this.field_527.getValue();
            if (var8) {
               double var9 = MathHelper.clamp(
                  field_4219.player.getEyePos().distanceTo(var6), Double.longBitsToDouble(4620693217682128896L), Double.longBitsToDouble(4634204016564240384L)
               );
               float var11 = Float.intBitsToFloat(1065353216)
                  - (float)((var9 - Double.longBitsToDouble(4620693217682128896L)) / Double.longBitsToDouble(4633078116657397760L));
               var7 = Class_1081.method_9(Class_1081.method_2(Color.red, Color.green, var11), (this.field_525.getValue() != null ? this.field_525.getValue().getAlpha() : 255));
            }

            if (Hub.field_2603.method_30(var5)) {
               var7 = Class_1081.method_9(Hub.field_2603.method_1145(), (this.field_525.getValue() != null ? this.field_525.getValue().getAlpha() : 255));
            } else if (Hub.field_2603.method_16(var5) && this.field_528.getValue()) {
               var7 = Class_1081.method_9(Hub.field_2603.method_1146(), (this.field_525.getValue() != null ? this.field_525.getValue().getAlpha() : 255));
            }

            Class_0800 var12 = var8 ? field_521.field_2133.getValue() : Class_0800.NORMAL;
            Color var10 = var7;
            var12.method_5(
               () -> {
                  RenderUtil.field_2672.method_2(var1.method_10(), var3, var6, var10);
                  if (this.field_526.getValue()) {
                     RenderUtil.field_2672
                        .method_2(
                           var1.method_10(),
                           var5.getLerpedPos(RenderUtil.method_776()),
                           var5.getLerpedPos(RenderUtil.method_776()).add(0.0, (double)var5.getEyeHeight(var5.getPose()), 0.0),
                           var10
                        );
                  }
               }
            );
         }
      }

      GL32C.glLineWidth(Float.intBitsToFloat(1065353216));
   }
}
