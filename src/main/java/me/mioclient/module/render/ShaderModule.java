package me.mioclient.module.render;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.awt.Color;
import java.util.Comparator;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_0151;
import me.mioclient.enum_.Class_0986;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_3;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0396;
import me.mioclient.internal.RotationManager;
import me.mioclient.internal.Class_0914;
import me.mioclient.internal.Class_0978;
import me.mioclient.internal.Class_1000;
import me.mioclient.internal.FontRenderer;
import me.mioclient.internal.Class_1081;
import me.mioclient.internal.Class_1355;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.player.FreecamModule;
import me.mioclient.setting.Setting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.entity.projectile.thrown.ExperienceBottleEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import nick.Settings;

public class ShaderModule extends Module {
   public static FreecamModule freecam = Hub.field_2595.method_78(FreecamModule.class);
   public static NoRenderModule norender = Hub.field_2595.method_78(NoRenderModule.class);
   public Setting<Class_0151> field_2017;
   public Setting<Integer> field_2018;
   public Setting<Integer> field_2019;
   public Setting<Boolean> field_2020;
   public Setting<Float> field_2021;
   public Setting<Integer> field_2022;
   public Setting<Float> field_2023;
   public Setting<Float> field_2024;
   public Setting<Float> field_2025;
   public Setting<Float> field_2026;
   public Setting<Float> field_2027;
   public Setting<Float> field_2028;
   public Setting<Boolean> field_2029;
   public Setting<Float> field_2030;
   public Setting<Boolean> field_2031;
   public Setting<Class_0986> field_2032;
   public Setting<Integer> field_2033;
   public Setting<Float> field_2034;
   public Setting<Boolean> field_2035;
   public Setting<Color> field_2036;
   public Setting<Color> field_2037;
   public Setting<Color> field_2038;
   public Setting<Color> field_2039;
   public Setting<Boolean> field_2040;
   public Setting<Boolean> field_2041;
   public Setting<Boolean> field_2042;
   public Setting<Boolean> field_2043;
   public Setting<Boolean> field_2044;
   public Setting<Integer> field_2045;
   public Setting<Boolean> field_2046;
   public Setting<Integer> field_2047;
   public Setting<Boolean> field_2048;
   public Setting<Boolean> field_2049;
   public Setting<Boolean> field_2050;
   public Setting<Boolean> field_2051;
   public Setting<Boolean> field_2052;
   public Setting<Boolean> field_2053;
   public Setting<Boolean> field_2054;
   public Setting<Boolean> field_2055;
   public final ObjectArrayList<Entity> field_2056;
   public float field_765;

   public ShaderModule() {
      super("Shader", "Advanced ESP using shaders.", Category.RENDER);
      Settings.initialize(this);
      this.field_2056 = new ObjectArrayList();
      this.field_765 = Float.intBitsToFloat(1065353216);
      this.field_2033.method_31("DecoratorRadius");
      this.field_2034.method_31("DecoratorAlpha");
      this.field_2042.method_31("HandsFill");
      this.field_2043.method_31("HandsOutline");
      this.field_2047.method_31("ItemRange");
      this.field_2045.method_31("CrystalRange");
   }

   @Override
   public String getInfo() {
      return FontRenderer.method_3(this.field_2017.getValue());
   }

   @Subscribe(
      method_800 = -99999999
   )
   public void method_9(Event_3 var1) {
      if (this.field_2017 == null || this.field_2017.getValue() == null) return;
      Class_0914 var2 = (this.field_2017.getValue() != null ? this.field_2017.getValue().method_177() : null);
      this.field_2056.clear();

      for (Entity var4 : field_4219.world.getEntities()) {
         if (this.method_98(var4)) {
            this.field_2056.add(var4);
         }
      }

      this.field_2056.sort(Comparator.comparing(var0 -> field_4219.gameRenderer.getCamera().getPos().squaredDistanceTo(var0.getPos())));
      Class_1000.method_2(var2, false, () -> {
         var1.method_10().push();
         this.field_2056.forEach(var3 -> this.method_2(var1, var3, var2));
         var1.method_10().pop();
      });
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      this.field_765 = this.method_186(this.field_2056.stream().min(Comparator.comparing(this::method_186)).orElse(null));
   }

   public void method_2(Class_0978 var1, Entity var2, Class_0914 var3) {
      if (var2.isAlive() || this.field_2055.getValue()) {
         Vec3d var4 = field_4219.gameRenderer.getCamera().getPos();
         float var5 = var1.method_880();
         double var6 = MathHelper.lerp((double)var5, var2.lastRenderX, var2.getX()) - var4.x;
         double var8 = MathHelper.lerp((double)var5, var2.lastRenderY, var2.getY()) - var4.y;
         double var10 = MathHelper.lerp((double)var5, var2.lastRenderZ, var2.getZ()) - var4.z;
         float var12 = MathHelper.lerp(var5, var2.prevYaw, var2.getYaw());
         var3.field_2301.setColor(255, 255, 255, 255);
         field_4219.getEntityRenderDispatcher().render(var2, var6, var8, var10, var12, var5, var1.method_10(), var3.field_2301, 0);
      }
   }

   public float method_186(Entity var1) {
      if (var1 != null && norender.isToggled() && norender.field_754.getValue()) {
         double var2 = field_4219.player.getPos().distanceTo(var1.getPos());
         float var4 = var1.getWidth();
         if (var2 <= (double)var4 && var1 != field_4219.player) {
            float var5 = (float)(var2 / (double)var4);
            float var6 = (float)norender.field_755.getValue().intValue() / Float.intBitsToFloat(1132396544);
            return var6 + var5 * (Float.intBitsToFloat(1065353216) - var6);
         }
      }

      return Float.intBitsToFloat(1065353216);
   }

   public Color method_30(Color var1) {
      float var2 = this.field_765;
      if (Class_1355.field_2003) {
         var2 = Float.intBitsToFloat(1065353216);
      }

      return Class_1081.method_9(var1, (int)(var2 * (float)var1.getAlpha()));
   }

   public float method_650() {
      return Class_1355.field_2003 ? this.field_2030.getValue() : this.field_2030.getValue() * this.field_765;
   }

   public boolean method_98(Entity var1) {
      float var2 = (float)(this.field_2018.getValue() != null ? this.field_2018.getValue().intValue() : 0);
      if (var1 instanceof EndCrystalEntity) {
         var2 = (float)(this.field_2045.getValue() != null ? this.field_2045.getValue().intValue() : 0);
      }

      if (var1 instanceof ItemEntity) {
         var2 = (float)(this.field_2047.getValue() != null ? this.field_2047.getValue().intValue() : 0);
      }

      boolean var3 = !RotationManager.method_4(var1.getBoundingBox()) || field_4219.player.distanceTo(var1) > var2;
      if (var1 instanceof PlayerEntity var4 && var4.isDead()) {
         return false;
      }

      if (var3) {
         return false;
      } else if (Class_0396.method_7(var1)) {
         return false;
      } else {
         boolean var5 = field_4219.gameRenderer.getCamera().isThirdPerson() || freecam.isToggled();
         if (var1 instanceof PlayerEntity) {
            return var5 && this.field_2051.getValue() && var1 == field_4219.player ? true : this.field_2050.getValue() && var1 != field_4219.player;
         } else {
            return var1 instanceof ItemEntity && this.field_2046.getValue()
               || var1 instanceof ExperienceBottleEntity && this.field_2052.getValue()
               || (var1 instanceof PassiveEntity || var1 instanceof FishEntity || var1 instanceof SquidEntity) && this.field_2048.getValue()
               || var1 instanceof Monster && this.field_2049.getValue()
               || var1 instanceof EnderPearlEntity && this.field_2053.getValue()
               || var1 instanceof EndCrystalEntity && this.field_2044.getValue()
               || var1 instanceof AbstractMinecartEntity && this.field_2054.getValue();
         }
      }
   }

   public boolean method_651() {
      return Class_1355.field_2003 && !this.field_2042.getValue();
   }

   public boolean method_652() {
      return Class_1355.field_2003 && !this.field_2043.getValue();
   }
}
