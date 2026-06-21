package me.mioclient.module.render;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_1056;
import me.mioclient.enum_.SettingMode;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_3;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0245;
import me.mioclient.internal.Class_0485;
import me.mioclient.internal.Class_0612;
import me.mioclient.internal.Class_0838;
import me.mioclient.internal.Class_0930;
import me.mioclient.internal.Class_1016;
import me.mioclient.internal.Class_1081;
import me.mioclient.internal.Class_1225;
import me.mioclient.internal.Class_1303;
import me.mioclient.internal.Class_1334;
import me.mioclient.internal.Class_1368;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.client.FontsModule;
import me.mioclient.setting.Setting;
import net.minecraft.client.render.Camera;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Direction.Axis;
import nick.Settings;
import org.lwjgl.opengl.GL32C;

public class WaypointsModule extends Module {
   public static FontsModule field_984 = Hub.field_2595.method_78(FontsModule.class);
   public Setting<Float> field_3305;
   public Setting<Float> field_3306;
   public Setting<Boolean> field_3307;
   public Setting<Boolean> field_3308;
   public Setting<Boolean> field_3309;
   public Setting<Boolean> field_3310;
   public Setting<Class_1056> field_3311;
   public Setting<Float> field_3312;
   public Setting<Boolean> field_3313;
   public Setting<Boolean> field_3314;
   public Setting<Color> field_3315;
   public Setting<Integer> field_3316;
   public final CopyOnWriteArrayList<Class_1368> field_3317;

   public WaypointsModule() {
      super("Waypoints", "Renders certain places you mark using ;waypoints.", Category.RENDER);
      Settings.initialize(this);
      this.field_3317 = new CopyOnWriteArrayList<>();
      this.field_3305.method_2("Unlimited", SettingMode.MAX);
      this.setDrawn(false);
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if (field_4219.player.age % 2 != 0) {
         ArrayList var2 = new ArrayList();

         for (Class_1368 var4 : (Iterable<Class_1368>)(Iterable<?>)((List<Class_1368>)Hub.field_2604.getRegistry())) {
            if (var4 != null && (var4.method_106() == null || var4.method_600() == null || var4.getName() == null)) {
               var2.add(var4);
            }
         }

         for (Class_1368 var7 : (Iterable<Class_1368>)(Iterable<?>)(var2)) {
            Hub.field_2604.method_5(var7);
         }

         this.field_3317.clear();

         for (Class_1368 var8 : (Iterable<Class_1368>)(Iterable<?>)((List<Class_1368>)Hub.field_2604.getRegistry())) {
            if (var8 != null
               && var8.method_600() != null
               && var8.getName() != null
               && var8.method_106() != null
               && var8.isToggled()
               && (
                  field_4219.getCurrentServerEntry() == null && var8.method_106().equalsIgnoreCase("singleplayer")
                     || field_4219.getCurrentServerEntry() != null && var8.method_106().equalsIgnoreCase(field_4219.getCurrentServerEntry().address)
               )) {
               this.field_3317.add(var8);
            }
         }

         this.field_3317.sort(Comparator.comparing(var0 -> field_4219.player.squaredDistanceTo(var0.method_733())));
      }
   }

   @Subscribe
   public void method_9(Event_3 var1) {
      if (!field_4219.options.hudHidden) {
         Camera var2 = field_4219.gameRenderer.getCamera();
         Vec3d var3 = new Vec3d(0.0, 0.0, Double.longBitsToDouble(4607182418800017408L))
            .rotateX(-((float)Math.toRadians((double)var2.getPitch())))
            .rotateY(-((float)Math.toRadians((double)var2.getYaw())))
            .add(field_4219.getEntityRenderDispatcher().camera.getPos());

         for (Class_1368 var5 : this.field_3317) {
            if (var5 != null
               && var5.method_600() != null
               && var5.getName() != null
               && var5.method_106() != null
               && (
                  !(
                        var5.method_733().distanceTo(field_4219.gameRenderer.getCamera().getPos())
                           > (double)(this.field_3305.getValue() * Float.intBitsToFloat(1148846080))
                     )
                     || this.field_3305.getValue() == Float.intBitsToFloat(1120534528)
               )) {
               Vec3d var6 = method_4(var5);
               if (var6 != null) {
                  float var7 = Class_1334.method_5(field_4219.player.getPos(), var6);
                  Vec3d var8 = var6;
                  if (var7 >= Float.intBitsToFloat(1123024896)) {
                     float[] var9 = Class_0485.method_78(var6);
                     Vec3d var10 = field_4219.player.getLerpedPos(Class_0838.method_776());
                     var8 = new Vec3d(
                        var10.getX()
                           + (double)(Float.intBitsToFloat(1120403456) * (float)Math.cos(Math.toRadians((double)(var9[0] + (float)Class_0245.field_685)))),
                        var6.y,
                        var10.getZ()
                           + (double)(Float.intBitsToFloat(1120403456) * (float)Math.sin(Math.toRadians((double)(var9[0] + (float)Class_0245.field_685))))
                     );
                  }

                  if (this.field_3308.getValue()) {
                     Box var13 = new Box(
                           var8.add(-Class_0245.field_689, -Class_0245.field_689, -Class_0245.field_689),
                           var8.add(Class_0245.field_689, Class_0245.field_689, Class_0245.field_689)
                        )
                        .withMaxY((double)field_4219.world.getTopY())
                        .withMinY((double)field_4219.world.getBottomY());
                     Class_0612.method_5(var1.method_10(), var13, Class_1081.method_9(this.field_3315.getValue(), this.field_3316.getValue()));
                  }

                  if (this.field_3307.getValue()) {
                     Class_0612.method_2(
                        var1.method_10(),
                        new Box(
                           var6.add(-Class_0245.field_689, -Class_0245.field_689, -Class_0245.field_689),
                           var6.add(Class_0245.field_689, Class_0245.field_689, Class_0245.field_689)
                        ),
                        this.field_3315.getValue(),
                        this.field_3306.getValue()
                     );
                  }

                  if (this.field_3309.getValue()) {
                     GL32C.glLineWidth(this.field_3306.getValue());
                     Class_0838.field_2672.method_2(var1.method_10(), var3, var8, this.field_3315.getValue());
                     GL32C.glLineWidth(Float.intBitsToFloat(1065353216));
                  }

                  if (this.field_3310.getValue()) {
                     if (this.field_3314.getValue()) {
                        double var14 = MathHelper.lerp((double)var1.method_880(), field_4219.player.prevY, field_4219.player.getY());
                        var8 = var8.withAxis(Axis.Y, var14 + (double)field_4219.player.getEyeHeight(field_4219.player.getPose()));
                     }

                     String var15 = new Class_1303().method_2(this.field_3311.getValue().method_37(var6)).method_2(var5.getName()).method_9("\u0001 \u0001");
                     double var16 = Class_0930.method_2(field_4219.gameRenderer.getCamera().getPos(), var8, (double)this.field_3312.getValue().floatValue());
                     float var12 = (float)Class_0245.field_689;
                     if (this.field_3313.getValue()) {
                        Class_0838.field_2672
                           .method_2(
                              var1.method_10(),
                              var8,
                              Float.intBitsToFloat(-1124744561),
                              (float)Class_0245.field_689,
                              -(Class_1016.field_3143.method_221(var15) / Float.intBitsToFloat(1073741824)) - Float.intBitsToFloat(1073741824),
                              Float.intBitsToFloat(1084227584),
                              var16 * Double.longBitsToDouble(4611686018427387904L),
                              new Color(0, 0, 0, 100)
                           );
                        if (!field_984.isToggled()) {
                           var12 = Float.intBitsToFloat(1047233823);
                        }
                     }

                     Class_0838.field_2672
                        .method_2(
                           var1.method_881(),
                           var15,
                           var8,
                           0.0F,
                           var12,
                           -(Class_1016.field_3143.method_221(var15) / Float.intBitsToFloat(1073741824)),
                           0.0F,
                           var16,
                           Color.WHITE,
                           true
                        );
                  }
               }
            }
         }
      }
   }

   public static Vec3d method_4(Class_1368 var0) {
      Vec3d var1 = var0.method_733();
      String var2 = Class_1225.method_1071().method_235().toLowerCase();
      if (var0.method_600().equals("overworld") && var2.equals("nether")) {
         return var1.multiply(
            Double.longBitsToDouble(4593671619917905920L), Double.longBitsToDouble(4607182418800017408L), Double.longBitsToDouble(4593671619917905920L)
         );
      } else if (var0.method_600().equals("nether") && var2.equals("overworld")) {
         return var1.multiply(
            Double.longBitsToDouble(4620693217682128896L), Double.longBitsToDouble(4607182418800017408L), Double.longBitsToDouble(4620693217682128896L)
         );
      } else {
         return !var0.method_600().equals(var2) ? null : var1;
      }
   }
}
