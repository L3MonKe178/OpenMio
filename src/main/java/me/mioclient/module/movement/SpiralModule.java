package me.mioclient.module.movement;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_3;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0485;
import me.mioclient.internal.Class_0838;
import me.mioclient.internal.Class_1334;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import nick.Settings;

public class SpiralModule extends Module {
   public Setting<Integer> field_509;
   public final List<Vec3d> field_510;
   public int current;

   public SpiralModule() {
      super("Spiral", Category.MOVEMENT);
      Settings.initialize(this);
      this.field_510 = new ArrayList<>(5000);
   }

   @Override
   public void onEnable() {
      this.current = 0;
      this.field_510.clear();
      Vec3d var1 = field_4219.player.getPos();
      float var2 = 0.0F;
      short var3 = 5000;

      for (int var4 = 0; var4 < var3 * 4; var4++) {
         this.field_510.add(var1);
         Vec3d var5 = Vec3d.fromPolar(0.0F, var2).multiply((double)(var4 * this.field_509.getValue() * 16));
         var1 = var1.add(var5);
         var2 = MathHelper.wrapDegrees(var2 + Float.intBitsToFloat(1119092736));
      }
   }

   public Vec3d method_214(int var1) {
      return this.field_510.get(var1);
   }

   @Subscribe
   public void method_5(Event_3 var1) {
      for (int var2 = Math.max(this.current, 1); var2 < Math.max(this.current, 1) + 2; var2++) {
         Vec3d var3 = this.field_510.get(var2 - 1);
         Vec3d var4 = this.field_510.get(var2);
         Class_0838.field_2672.method_2(var1.method_10(), var3, var4, Color.white);
      }
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      Vec3d var2 = this.method_214(this.current);
      float var3 = Class_0485.method_78(var2)[0];
      field_4219.player.setYaw(var3);
      if (Class_1334.method_5(var2, field_4219.player.getPos()) < Float.intBitsToFloat(1077936128)) {
         this.current++;
      }
   }
}
