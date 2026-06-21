package me.mioclient.module.client;

import java.awt.Color;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_0703;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_23;
import me.mioclient.event.Event_24;
import me.mioclient.event.Event_3;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0211;
import me.mioclient.internal.Class_0245;
import me.mioclient.internal.Class_0485;
import me.mioclient.internal.Class_0612;
import me.mioclient.internal.Class_0838;
import me.mioclient.internal.Class_0930;
import me.mioclient.internal.Class_1016;
import me.mioclient.internal.Class_1081;
import me.mioclient.internal.Class_1257;
import me.mioclient.internal.Class_1334;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import nick.Settings;

public class IRCModule extends Module {
   public static final Identifier field_563 = Identifier.of("mio", "textures/warning.png");
   public Setting<Boolean> field_564;
   public Setting<Boolean> field_565;
   public Setting<Boolean> field_566;
   public Setting<String> field_567;
   public Setting<Boolean> field_568;
   public Setting<Class_0211> field_569;
   public Setting<Float> field_570;
   public Setting<Boolean> field_571;
   public Setting<Class_0703> field_572;
   public Setting<Boolean> field_573;
   public Setting<Class_0211> field_574;
   public Setting<Float> field_575;
   public int field_576;

   public IRCModule() {
      super("IRC", "Client user chat.", Category.CLIENT);
      Settings.initialize(this);
      this.setDrawn(false);
      this.field_564.method_9(() -> Hub.field_2610.method_15(this.method_237()));
      this.field_569.method_31("C-SoundMode");
      this.field_570.method_31("C-SoundVolume");
      this.field_574.method_31("P-SoundMode");
      this.field_575.method_31("P-SoundVolume");
   }

   @Override
   public void onEnable() {
      Hub.field_2610.method_322();
   }

   @Override
   public void onDisable() {
      Hub.field_2610.method_202();
   }

   @Subscribe
   public void method_2(Event_23 var1) {
      float var2 = (float)field_4219.player.getEyePos().distanceTo(var1.method_149().method_111().toCenterPos());
      if (var2 >= Float.intBitsToFloat(1176255488)) {
         var1.method_463();
      } else {
         if (this.field_573.getValue() && var1.method_149().method_1098()) {
            Hub.field_2606.method_2(this.field_574.getValue(), var1.method_149().method_111().toCenterPos(), this.field_575.getValue());
         }
      }
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      Hub.field_2610.method_328().removeIf(var0 -> System.currentTimeMillis() - var0.getSpawnTime() > 7500L);
   }

   @Subscribe
   public void method_9(Event_3 var1) {
      if (this.field_571.getValue()) {
         synchronized (Hub.field_2610.method_328()) {
            for (Class_1257 var4 : Hub.field_2610.method_328()) {
               if (var4.method_1098()) {
                  float var5 = (float)field_4219.player.getEyePos().distanceTo(var4.method_111().toCenterPos());
                  if (!(var5 >= Float.intBitsToFloat(1176255488))) {
                     Vec3d var6 = Vec3d.of(var4.method_111());
                     Vec3d var7 = var6;
                     if (var5 >= (float)Class_0245.field_685) {
                        float[] var8 = Class_0485.method_78(var6);
                        var7 = new Vec3d(
                           field_4219.player.getX()
                              + (double)(Float.intBitsToFloat(1116471296) * (float)Math.cos(Math.toRadians((double)(var8[0] + (float)Class_0245.field_685)))),
                           var6.y,
                           field_4219.player.getZ()
                              + (double)(Float.intBitsToFloat(1116471296) * (float)Math.sin(Math.toRadians((double)(var8[0] + (float)Class_0245.field_685))))
                        );
                     }

                     float var21 = MathHelper.clamp(
                        Float.intBitsToFloat(1065353216) - (float)(System.currentTimeMillis() - var4.getSpawnTime() - 6500L) / Float.intBitsToFloat(1148846080),
                        0.0F,
                        Float.intBitsToFloat(1065353216)
                     );
                     float var9 = MathHelper.clamp(
                        (float)(System.currentTimeMillis() - var4.getSpawnTime()) / Float.intBitsToFloat(1153138688), 0.0F, Float.intBitsToFloat(1065353216)
                     );
                     var9 = this.method_186(var9);
                     var21 = this.method_98(var21);
                     float var10 = Math.min(Class_1334.method_5(field_4219.player.getLerpedPos(var1.method_880()), var6), Float.intBitsToFloat(1086324736))
                        / Float.intBitsToFloat(1086324736);
                     Vec3d var11 = var7.add(
                        Double.longBitsToDouble(4602678819172646912L),
                        Double.longBitsToDouble(4602678819172646912L),
                        Double.longBitsToDouble(4602678819172646912L)
                     );
                     double var12 = Class_0930.method_2(field_4219.gameRenderer.getCamera().getPos(), var11, Double.longBitsToDouble(4613937818241073152L));
                     Box var14 = new Box(
                        var11.add(-Class_0245.field_689, -Class_0245.field_689, -Class_0245.field_689),
                        var11.add(Class_0245.field_689, Class_0245.field_689, Class_0245.field_689)
                     );
                     var14 = var14.withMaxY(MathHelper.lerp((double)var9, var11.y, (double)field_4219.world.getTopY()))
                        .withMinY(MathHelper.lerp((double)var9, var11.y, (double)field_4219.world.getBottomY()));
                     Class_0612.method_5(
                        var1.method_10(),
                        var14,
                        Class_1081.method_2(
                           Class_1081.method_2(Color.red, Color.yellow, Double.longBitsToDouble(4652007308841189376L), 0.0),
                           Float.intBitsToFloat(1050253722) * var9 * var21 * var10
                        )
                     );
                     Class_0838.field_2672
                        .method_2(
                           var1.method_881(),
                           var11,
                           Float.intBitsToFloat(1132462080),
                           Float.intBitsToFloat(1132462080),
                           var12 / Double.longBitsToDouble(4627898977085921690L) * (double)var9 * (double)var21,
                           Class_1081.method_2(Color.red, Color.yellow, Double.longBitsToDouble(4652007308841189376L), 0.0),
                           field_563
                        );
                     if (this.field_572.getValue().method_670()) {
                        double var15 = Class_0930.method_2(field_4219.gameRenderer.getCamera().getPos(), var11, Double.longBitsToDouble(4609434218613702656L))
                           * (double)var9
                           * (double)var21;
                        String var17 = this.field_572.getValue().method_9(var4);
                        float var18 = Float.intBitsToFloat(-1102263091) * (float)var12;
                        Class_0838.field_2672
                           .method_2(
                              var1.method_10(),
                              var11,
                              0.0F,
                              var18,
                              -(Class_1016.field_3143.method_221(var17) / Float.intBitsToFloat(1073741824)) - Float.intBitsToFloat(1073741824),
                              Float.intBitsToFloat(1084227584),
                              var15 * Double.longBitsToDouble(4611686018427387904L),
                              new Color(0, 0, 0, 100)
                           );
                        Class_0838.field_2672
                           .method_2(
                              var1.method_881(),
                              var17,
                              var11,
                              0.0F,
                              var18,
                              -(Class_1016.field_3143.method_221(var17) / Float.intBitsToFloat(1073741824)),
                              0.0F,
                              var15,
                              Color.WHITE,
                              true
                           );
                     }
                  }
               }
            }
         }
      }
   }

   public float method_186(float var1) {
      double var2 = Double.longBitsToDouble(4611686018427387904L) * Class_0245.field_687 / Double.longBitsToDouble(4613937818241073152L);
      return var1 == 0.0F
         ? 0.0F
         : (float)(
            var1 == Float.intBitsToFloat(1065353216)
               ? Double.longBitsToDouble(4607182418800017408L)
               : Math.pow(Double.longBitsToDouble(4611686018427387904L), (double)(Float.intBitsToFloat(-1054867456) * var1))
                     * Math.sin(((double)(var1 * Float.intBitsToFloat(1092616192)) - Double.longBitsToDouble(4604930618986332160L)) * var2)
                  + Double.longBitsToDouble(4607182418800017408L)
         );
   }

   public float method_98(float var1) {
      return var1 * var1 * var1;
   }

   @Subscribe
   public void method_2(Event_24 var1) {
      if (this.field_566.getValue()) {
         if (var1.method_219().length() > this.field_567.getValue().length() && var1.method_219().startsWith(this.field_567.getValue())) {
            var1.method_463();
            Hub.field_2610.method_214(var1.method_219().substring(this.field_567.getValue().length()).trim());
         }
      }
   }

   public String method_237() {
      if (!this.field_564.getValue()) {
         return "none";
      } else {
         return switch (Hub.field_2609.method_802()) {
            case 3 -> "pepsi";
            case 5 -> "tetris";
            default -> "mio";
         };
      }
   }
}
