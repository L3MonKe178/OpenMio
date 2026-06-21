package me.mioclient.module.movement;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import me.mioclient.enum_.Class_0099;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_10;
import me.mioclient.event.Event_20;
import me.mioclient.event.Event_3;
import me.mioclient.event.Event_4;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0242;
import me.mioclient.internal.Class_0612;
import me.mioclient.internal.Class_1261;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.common.CommonPongC2SPacket;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.util.math.Box;
import nick.Settings;

public class FakeLagModule extends Module {
   public final Queue<Packet<?>> field_3037 = new ArrayDeque<>();
   public Setting<Class_0099> field_3038;
   public Setting<Integer> field_3039;
   public Setting<Boolean> field_3040;
   public Setting<Boolean> field_3041;
   public Setting<Boolean> field_3042;
   public Setting<Boolean> field_3043;
   public Setting<Boolean> field_3044;
   public Setting<Boolean> field_3045;
   public Setting<Boolean> field_3046;
   public Setting<Boolean> field_3047;
   public Setting<Boolean> field_3048;
   public Setting<Float> field_3049;
   public Setting<Boolean> field_3050;
   public Setting<Color> field_3051;
   public Setting<Color> field_3052;
   public Setting<Float> field_3053;
   public final Class_0242 field_3054;
   public final Class_0242 field_3055;
   public Box field_1887;

   public FakeLagModule() {
      super("FakeLag", "Cancels movement packets until toggled off.", Category.MOVEMENT);
      Settings.initialize(this);
      this.field_3054 = new Class_0242();
      this.field_3055 = new Class_0242();
   }

   @Override
   public void onEnable() {
      this.field_3037.clear();
      if (this.method_535()) {
         this.disable();
      } else {
         this.field_1887 = field_4219.player.getBoundingBox();
         this.field_3054.reset();
         this.field_3055.reset();
      }
   }

   @Override
   public void onDisable() {
      if (!this.method_535()) {
         this.method_159();
      }
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if (this.field_3038.getValue() == Class_0099.PULSE && this.field_3055.method_9((long)this.field_3039.getValue().intValue())) {
         this.method_159();
         this.field_3055.reset();
      }

      if (this.field_3048.getValue() && this.field_3054.method_2((double)this.field_3049.getValue().floatValue(), TimeUnit.SECONDS)) {
         this.method_68();
      }
   }

   @Subscribe(
      method_800 = -200
   )
   public void method_2(Event_10 var1) {
      if (this.method_5(var1.method_127()) && !var1.method_464()) {
         this.field_3037.add(var1.method_127());
         var1.method_463();
      }
   }

   @Subscribe
   public void method_5(Event_4 var1) {
      if (var1.method_127() instanceof PlayerPositionLookS2CPacket && this.field_3038.getValue() == Class_0099.PULSE) {
         this.field_3055.setTime(-1L);
      }
   }

   @Subscribe
   public void method_9(Event_3 var1) {
      if (this.field_3050.getValue()) {
         Class_0612.method_5(var1.method_10(), this.field_1887, this.field_3051.getValue());
         Class_0612.method_2(var1.method_10(), this.field_1887, this.field_3052.getValue(), this.field_3053.getValue());
      }
   }

   @Subscribe
   public void method_2(Event_20 var1) {
      this.disable();
   }

   public boolean method_5(Packet<?> var1) {
      return var1 instanceof PlayerMoveC2SPacket && this.field_3041.getValue()
         || var1 instanceof PlayerInteractBlockC2SPacket && this.field_3042.getValue()
         || var1 instanceof PlayerInteractEntityC2SPacket && this.field_3045.getValue()
         || var1 instanceof PlayerActionC2SPacket && this.field_3046.getValue()
         || var1 instanceof HandSwingC2SPacket && this.field_3043.getValue()
         || var1 instanceof UpdateSelectedSlotC2SPacket && this.field_3044.getValue()
         || var1 instanceof CommonPongC2SPacket
         || this.field_3047.getValue();
   }

   public void method_159() {
      while (!this.field_3037.isEmpty()) {
         Packet var1 = this.field_3037.poll();
         Class_1261.method_9(var1);
      }

      this.field_1887 = field_4219.player.getBoundingBox();
   }
}
