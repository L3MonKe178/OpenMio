package me.mioclient.module.render;

import java.awt.Color;
import me.mioclient.Hub;
import me.mioclient.event.Event_3;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0421;
import me.mioclient.internal.Class_0485;
import me.mioclient.internal.Class_0612;
import me.mioclient.internal.Class_0930;
import me.mioclient.internal.Class_1081;
import me.mioclient.internal.Class_1225;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import nick.Settings;

public class BreakHighlightModule extends Module {
   public Setting<Double> field_46;
   public Setting<Boolean> field_47;
   public Setting<Boolean> field_48;
   public Setting<Color> field_49;
   public Setting<Color> field_50;

   public BreakHighlightModule() {
      super("BreakHighlight", "Highlights the blocks that are being broken.", Category.RENDER);
      Settings.initialize(this);
   }

   @Subscribe
   public void method_9(Event_3 var1) {
      for (Class_0421 var3 : Hub.field_2629.method_974()) {
         if (!(Class_0930.method_35(var3.method_111()) > this.field_46.getValue())
            && Class_0485.method_4(new Box(var3.method_111()))
            && Class_1225.method_3(var3.method_111())) {
            Color var4 = this.field_49.getValue();
            Color var5 = this.field_50.getValue();
            VoxelShape var6 = field_4219.world.getBlockState(var3.method_111()).getOutlineShape(field_4219.world, var3.method_111());
            float var7 = var3.method_209(var1.method_880());
            Entity var8 = var3.method_11();
            Box var9 = this.method_2(
               var6.isEmpty()
                  ? new Box(
                     0.0,
                     0.0,
                     0.0,
                     Double.longBitsToDouble(4607182418800017408L),
                     Double.longBitsToDouble(4607182418800017408L),
                     Double.longBitsToDouble(4607182418800017408L)
                  )
                  : var6.getBoundingBox(),
               var7
            );
            var9 = var9.offset(var3.method_111());
            if (var8 instanceof PlayerEntity) {
               PlayerEntity var10 = (PlayerEntity)var8;
               Color var11 = Hub.field_2603.method_9(var10.getGameProfile().getName(), null);
               if (!this.field_47.getValue() && Hub.field_2603.method_30(var10)) {
                  continue;
               }

               if (var11 != null && (!Hub.field_2603.method_289(var10.getGameProfile().getName()) || this.field_48.getValue())) {
                  var4 = Class_1081.method_9(var11, this.field_49.getValue().getAlpha());
                  var5 = Class_1081.method_9(var11, this.field_50.getValue().getAlpha());
               }
            }

            Class_0612.method_5(var1.method_10(), this.method_2(var9, var7), var4);
            Class_0612.method_2(var1.method_10(), this.method_2(var9, var7), var5, Float.intBitsToFloat(1065353216));
         }
      }
   }

   public Box method_2(Box var1, float var2) {
      float var3 = MathHelper.clamp(var2, 0.0F, Float.intBitsToFloat(1065353216));
      double var4 = Math.abs(var1.getLengthX());
      double var6 = Math.abs(var1.getLengthY());
      double var8 = Math.abs(var1.getLengthZ());
      Vec3d var10 = var1.getCenter();
      return new Box(
         var10.subtract(
            (double)var3 * var4 / Double.longBitsToDouble(4611686018427387904L),
            (double)var3 * var6 / Double.longBitsToDouble(4611686018427387904L),
            (double)var3 * var8 / Double.longBitsToDouble(4611686018427387904L)
         ),
         var10.add(
            (double)var3 * var4 / Double.longBitsToDouble(4611686018427387904L),
            (double)var3 * var6 / Double.longBitsToDouble(4611686018427387904L),
            (double)var3 * var8 / Double.longBitsToDouble(4611686018427387904L)
         )
      );
   }
}
