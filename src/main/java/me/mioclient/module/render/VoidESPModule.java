package me.mioclient.module.render;

import java.awt.Color;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_3;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.RotationManager;
import me.mioclient.internal.Class_0612;
import me.mioclient.internal.Class_1035;
import me.mioclient.internal.Class_1081;
import me.mioclient.internal.Class_1225;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import nick.Settings;

public class VoidESPModule extends Module {
   public Setting<Float> field_130;
   public Setting<Float> field_131;
   public Setting<Integer> field_132;
   public Setting<Boolean> field_133;
   public Setting<Float> field_134;
   public Setting<Boolean> field_135;
   public Setting<Color> field_136;
   public Setting<Color> field_137;
   public final List<BlockPos> field_138;

   public VoidESPModule() {
      super("VoidESP", "Highlights void blocks.", Category.RENDER);
      Settings.initialize(this);
      this.field_138 = new CopyOnWriteArrayList<>();
   }

   @Override
   public void onDisable() {
      this.field_138.clear();
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      this.field_138.removeIf(var0 -> Class_1035.method_30(var0) == Blocks.BEDROCK);

      for (BlockPos var3 : Class_1225.method_2(field_4219.gameRenderer.getCamera().getPos(), (float)(this.field_132.getValue() != null ? this.field_132.getValue().intValue() : 0), false)) {
         if (var3.getY() == field_4219.world.getBottomY() && Class_1035.method_30(var3) != Blocks.BEDROCK && !this.field_138.contains(var3)) {
            this.field_138.add(var3);
         }
      }
   }

   @Override
   public String getInfo() {
      return String.valueOf(this.field_138.size());
   }

   @Subscribe
   public void method_9(Event_3 var1) {
      for (BlockPos var3 : this.field_138) {
         if (RotationManager.method_4(new Box(var3))) {
            Vec3d var4 = field_4219.gameRenderer.getCamera().getPos();
            if (!var3.isWithinDistance(var4, (double)(this.field_132.getValue() != null ? this.field_132.getValue().intValue() : 0))) {
               this.field_138.remove(var3);
            } else {
               Color var5 = this.field_136.getValue();
               Color var6 = this.field_137.getValue();
               double var7 = var4.distanceTo(var3.toCenterPos());
               if (this.field_133.getValue() && var7 >= (double)(this.field_134.getValue() != null ? this.field_134.getValue().floatValue() : 0.0f)) {
                  float var9 = Float.intBitsToFloat(1065353216)
                     - (float)MathHelper.clamp(
                        (var7 - (double)(this.field_134.getValue() != null ? this.field_134.getValue().floatValue() : 0.0f))
                           / (double)((float)(this.field_132.getValue() != null ? this.field_132.getValue().intValue() : 0) - this.field_134.getValue()),
                        0.0,
                        Double.longBitsToDouble(4607182418800017408L)
                     );
                  var5 = Class_1081.method_9(var5, (int)(var9 * (float)var5.getAlpha()));
                  var6 = Class_1081.method_9(var6, (int)(var9 * (float)var6.getAlpha()));
               }

               Class_0612.method_5(var1.method_10(), new Box(var3).withMaxY((double)((float)var3.getY() + this.field_131.getValue())), var5);
               Class_0612.method_2(
                  var1.method_10(), new Box(var3).withMaxY((double)((float)var3.getY() + this.field_131.getValue())), var6, this.field_130.getValue()
               );
            }
         }
      }
   }
}
