package me.mioclient.module.render;

import com.mojang.authlib.GameProfile;
import com.mojang.blaze3d.systems.RenderSystem;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicBoolean;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_0842;
import me.mioclient.enum_.Class_0927;
import me.mioclient.enum_.SettingMode;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_22;
import me.mioclient.event.Event_3;
import me.mioclient.event.Event_4;
import me.mioclient.event.Event_49;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0211;
import me.mioclient.internal.Class_0382;
import me.mioclient.internal.Class_0396;
import me.mioclient.internal.Class_0482;
import me.mioclient.internal.Class_0612;
import me.mioclient.internal.Class_0838;
import me.mioclient.internal.Class_0878;
import me.mioclient.internal.Class_0922;
import me.mioclient.internal.Class_0930;
import me.mioclient.internal.Class_1016;
import me.mioclient.internal.Class_1225;
import me.mioclient.internal.Class_1303;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.client.FontsModule;
import me.mioclient.record.Class_0375;
import me.mioclient.record.Class_0643;
import me.mioclient.setting.Setting;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.PlayerListS2CPacket;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Direction.Axis;
import nick.Settings;

public class LogoutSpotsModule extends Module {
   public static final FontsModule field_984 = Hub.field_2595.method_78(FontsModule.class);
   public static boolean field_2321 = false;
   public Setting<Float> field_2322;
   public Setting<Class_0842> field_2323;
   public Setting<Boolean> field_2324;
   public Setting<Float> field_2325;
   public Setting<Float> field_2326;
   public Setting<Boolean> field_2327;
   public Setting<Float> field_2328;
   public Setting<Boolean> field_2329;
   public Setting<Float> field_2330;
   public Setting<Class_0927> field_2331;
   public Setting<Boolean> field_2332;
   public Setting<Boolean> field_2333;
   public Setting<Boolean> field_2334;
   public Setting<Boolean> field_2335;
   public Setting<Boolean> field_2336;
   public Setting<Class_0211> field_2337;
   public Setting<Boolean> field_2338;
   public Setting<Class_0211> field_2339;
   public Setting<Boolean> field_2340;
   public Setting<Color> field_2341;
   public Setting<Color> field_2342;
   public Setting<Color> field_2343;
   public Setting<Color> field_2344;
   public Setting<Color> field_2345;
   public Setting<Color> field_2346;
   public Setting<Boolean> field_2347;
   public Setting<Boolean> field_2348;
   public Setting<Boolean> field_2349;
   public final Object2ObjectOpenHashMap<String, Class_0375> field_2350;
   public final List<Class_0643> field_2351;

   public LogoutSpotsModule() {
      super("LogoutSpots", "Highlights log out spots.", Category.RENDER);
      Settings.initialize(this);
      this.field_2350 = new Object2ObjectOpenHashMap();
      this.field_2351 = new ArrayList<>();
      this.field_2322.method_2("Unlimited", SettingMode.MAX);
      this.field_2337.method_31("LoginSoundPath");
      this.field_2339.method_31("LogoutSoundPath");
      this.setDrawn(false);
   }

   @Override
   public void onToggle() {
      this.field_2350.clear();
   }

   @Subscribe
   public void method_9(Event_3 var1) {
      Camera var2 = field_4219.gameRenderer.getCamera();
      Vec3d var3 = new Vec3d(0.0, 0.0, Double.longBitsToDouble(4607182418800017408L))
         .rotateX(-((float)Math.toRadians((double)var2.getPitch())))
         .rotateY(-((float)Math.toRadians((double)var2.getYaw())))
         .add(field_4219.getEntityRenderDispatcher().camera.getPos());
      synchronized (this.field_2350) {
         ObjectIterator var5 = this.field_2350.entrySet().iterator();

         while (var5.hasNext()) {
            Entry var6 = (Entry)var5.next();
            if (((Class_0375)var6.getValue()).method_423().equals(this.method_106())
               && ((Class_0375)var6.getValue()).method_418() == Class_1225.method_1071()
               && (
                  !(
                        ((Class_0375)var6.getValue()).field_1211.getCenter().distanceTo(field_4219.gameRenderer.getCamera().getPos())
                           > (double)this.field_2322.getValue().floatValue()
                     )
                     || this.field_2322.getValue() == Float.intBitsToFloat(1133936640)
               )) {
               if (this.field_2323.getValue() == Class_0842.SIMPLE || this.field_2323.getValue() == Class_0842.BOTH) {
                  Class_0612.method_5(var1.method_10(), ((Class_0375)var6.getValue()).method_172(), this.field_2343.getValue());
                  Class_0612.method_2(var1.method_10(), ((Class_0375)var6.getValue()).method_172(), this.field_2344.getValue(), this.field_2326.getValue());
               }

               if (this.field_2323.getValue() == Class_0842.COMPLEX || this.field_2323.getValue() == Class_0842.BOTH) {
                  Class_0878 var7 = ((Class_0375)var6.getValue()).method_422();
                  if (this.field_2324.getValue()) {
                     field_2321 = true;
                     RenderSystem.setShaderColor(
                        Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216), this.field_2325.getValue()
                     );
                     Class_0838.field_2672
                        .method_2(var7, Float.intBitsToFloat(1065353216), var1.method_10(), field_4219.getBufferBuilders().getEntityVertexConsumers());
                     field_4219.getBufferBuilders().getEntityVertexConsumers().draw();
                     RenderSystem.setShaderColor(
                        Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216)
                     );
                     field_2321 = false;
                  }

                  Class_0482.method_5((double)this.field_2326.getValue().floatValue());
                  Class_0482.method_2(this.field_2342.getValue(), this.field_2341.getValue());
                  Class_0482.method_2(var1.method_10(), var7);
               }

               if (this.field_2327.getValue()) {
                  Class_0838.field_2672
                     .method_2(
                        var1.method_10(), var3, ((Class_0375)var6.getValue()).method_172().getCenter(), this.field_2342.getValue(), this.field_2328.getValue()
                     );
               }

               if (this.field_2329.getValue()) {
                  Vec3d var15 = ((Class_0375)var6.getValue())
                     .method_172()
                     .getCenter()
                     .withAxis(Axis.Y, ((Class_0375)var6.getValue()).method_172().maxY + Double.longBitsToDouble(4602678819172646912L));
                  String var8 = new Class_1303().method_2((String)var6.getKey()).method_9("\u0001 logout spot");
                  String var9 = this.method_2((Class_0375)var6.getValue());
                  float var10 = field_984.isToggled() ? Float.intBitsToFloat(1065353216) : 0.0F;
                  if (!var9.isEmpty()) {
                     var8 = new Class_1303().method_2((Object)var9).method_2((Object)var8).method_9("\u0001\u0001");
                  }

                  double var11 = Class_0930.method_2(field_4219.gameRenderer.getCamera().getPos(), var15, (double)this.field_2330.getValue().floatValue());
                  Class_0838.field_2672
                     .method_2(
                        var1.method_10(),
                        var15,
                        Float.intBitsToFloat(-1124744561),
                        0.0F,
                        -(Class_1016.field_3143.method_221(var8) / Float.intBitsToFloat(1073741824)) - Float.intBitsToFloat(1073741824),
                        Float.intBitsToFloat(1084227584) + var10,
                        var11 * Double.longBitsToDouble(4611686018427387904L),
                        this.field_2346.getValue()
                     );
                  Class_0838.field_2672
                     .method_2(
                        var1.method_881(),
                        var8,
                        var15,
                        0.0F,
                        0.0F,
                        -(Class_1016.field_3143.method_221(var8) / Float.intBitsToFloat(1073741824)),
                        Float.intBitsToFloat(1073741824),
                        var11,
                        this.field_2345.getValue(),
                        true
                     );
               }
            }
         }
      }
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      for (Class_0643 var3 : this.field_2351) {
         PlayerEntity var4 = var3.method_654();
         if (this.method_154(var4)) {
            var3.field_2060.set(true);
            this.field_2350.put(var4.getGameProfile().getName(), Class_0375.method_2(var4, this.method_106()));
            if (this.field_2335.getValue() && this.field_2338.getValue()) {
               Hub.field_2606.method_2(this.field_2339.getValue(), Float.intBitsToFloat(1065353216));
            }
         }
      }

      this.field_2351.removeIf(Class_0643::method_653);
   }

   @Subscribe
   public void method_5(Event_4 var1) {
      if (var1.method_127() instanceof PlayerListS2CPacket var2) {
         for (net.minecraft.network.packet.s2c.play.PlayerListS2CPacket.Entry var4 : var2.getPlayerAdditionEntries()) {
            field_4219.executeSync(() -> {
               GameProfile var2x = var4.profile();
               String var3 = var2x == null ? "" : var2x.getName();
               if (this.field_2335.getValue() && this.field_2336.getValue() && this.field_2350.containsKey(var3)) {
                  Hub.field_2606.method_2(this.field_2337.getValue(), Float.intBitsToFloat(1065353216));
               }

               this.field_2350.remove(var3);
            });
         }
      }
   }

   @Subscribe
   public void method_5(Event_22 var1) {
      if (Hub.field_2602.method_991() == null || var1.method_106().equals(Hub.field_2602.method_991().address)) {
         this.field_2350.clear();
      }
   }

   @Subscribe
   public void method_2(Event_49 var1) {
      if (field_4219.world.getEntityById(var1.getId()) instanceof PlayerEntity var2) {
         if (var2 instanceof Class_0922) {
            return;
         }

         if (var2.deathTime > 0 || Class_0396.method_2((net.minecraft.entity.Entity)var2) <= 0.0F) {
            return;
         }

         if (this.field_2348.getValue() && Hub.field_2603.method_30(var2)) {
            return;
         }

         if (this.field_2349.getValue() && !Class_0382.method_29((LivingEntity)var2)) {
            return;
         }

         this.field_2351.add(new Class_0643(var2, System.currentTimeMillis(), new AtomicBoolean()));
      }
   }

   public String method_2(Class_0375 var1) {
      StringBuilder var2 = new StringBuilder();
      if (this.field_2331.getValue() == Class_0927.COORDINATES) {
         Vec3d var3 = var1.method_421();
         var2.append(" ");
         var2.append(String.format(Locale.US, "X: %.1f, Y: %.1f, Z: %.1f", var3.getX(), var3.getY(), var3.getZ()));
      }

      if (this.field_2331.getValue() == Class_0927.DISTANCE) {
         double var5 = field_4219.player.getPos().distanceTo(var1.method_421());
         var2.append(" ");
         var2.append("%.1fm".formatted(var5));
      }

      if (this.field_2332.getValue()) {
         String var6 = " %s".formatted(new SimpleDateFormat("HH:mm").format(new Date(var1.method_417())));
         var2.append(var6);
      }

      if (this.field_2333.getValue()) {
         var2.append(" (");
         var2.append(var1.field_1213);
         var2.append("hp)");
      }

      if (var1.field_1214 > 0 && this.field_2334.getValue()) {
         var2.append(" -");
         var2.append(var1.field_1214);
      }

      return var2.toString();
   }

   public boolean method_154(PlayerEntity var1) {
      return field_4219.player.networkHandler.getPlayerList().stream().noneMatch(var1x -> var1x.getProfile().getName().equals(var1.getGameProfile().getName()));
   }

   public static boolean method_708() {
      return field_2321;
   }

   public String method_106() {
      return field_4219.world != null && !field_4219.isInSingleplayer() && field_4219.player.networkHandler.getServerInfo() != null
         ? field_4219.player.networkHandler.getServerInfo().address
         : "singleplayer";
   }
}
