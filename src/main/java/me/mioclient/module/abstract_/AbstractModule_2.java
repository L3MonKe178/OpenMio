package me.mioclient.module.abstract_;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Queue;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_0823;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_10;
import me.mioclient.event.Event_16;
import me.mioclient.event.Event_20;
import me.mioclient.event.Event_3;
import me.mioclient.event.Event_30;
import me.mioclient.event.Event_31;
import me.mioclient.event.Event_4;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0382;
import me.mioclient.internal.Class_1138;
import me.mioclient.internal.Class_1225;
import me.mioclient.internal.TextBuilder;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.combat.SelfFillModule;
import me.mioclient.setting.Setting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.item.Items;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.c2s.play.TeleportConfirmC2SPacket;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import nick.Settings;

public class AbstractModule_2 extends Module {
   public Setting<Class_0823> field_791;
   public Setting<Boolean> field_792;
   public Setting<Float> field_793;
   public Setting<Boolean> field_794;
   public Setting<Color> field_795;
   public Setting<Color> field_796;
   public Setting<Float> field_797;
   public static AbstractModule_17 field_798 = Hub.field_2595.method_78(AbstractModule_17.class);
   public static SelfFillModule field_799 = Hub.field_2595.method_78(SelfFillModule.class);
   public Queue<Packet<?>> field_800;
   public final Class_1138 field_801;
   public PlayerPositionLookS2CPacket field_802;
   public boolean field_803;
   public boolean field_804;
   public Vec3d field_805;
   public Vec3d field_806;

   public AbstractModule_2() {
      super(
         "ChorusControl",
         new TextBuilder().method_2(String.valueOf(Formatting.YELLOW)).method_9("Allows you to pick the position to teleport. \n\u0001Sneak to teleport."),
         Category.EXPLOIT
      );
      Settings.initialize(this);
      this.field_800 = new LinkedList<>();
      this.field_801 = new Class_1138();
   }

   @Override
   public void onToggle() {
      this.field_806 = null;
      this.field_803 = false;
      this.field_804 = false;
      this.field_802 = null;
   }

   @Override
   public void onDisable() {
      if (this.field_803) {
         this.method_293();
      }
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if (!this.method_535()) {
         if (this.field_803
            && !Class_0382.method_425().equals(this.field_806)
            && !this.field_804
            && field_4219.player.getEyePos().distanceTo(new Vec3d(this.field_806.getX(), this.field_806.getY(), this.field_806.getZ()))
               > Double.longBitsToDouble(4607182418800017408L)) {
            field_4219.player.setPosition(this.field_806.getX(), this.field_806.getY(), this.field_806.getZ());
            this.field_804 = true;
         }

         if (this.field_802 != null && this.field_803) {
            Box var2 = field_4219.player.getBoundingBox(EntityPose.STANDING);
            Box var3 = new Box(0.0, 0.0, 0.0, var2.getLengthX(), var2.getLengthY(), var2.getLengthZ())
               .offset(this.field_802.getX(), this.field_802.getY(), this.field_802.getZ());
            this.field_801.method_29(var3);
            this.field_801.method_1016();
         }

         if (this.field_803) {
            field_4219.player.setVelocity(0.0, 0.0, 0.0);
            field_4219.player.input.movementForward = 0.0F;
            field_4219.player.input.movementSideways = 0.0F;
            if (this.field_802 != null) {
               Entity var4 = Class_1225.method_2(true, false, false, false, false, false, Float.intBitsToFloat(1098907648), Float.intBitsToFloat(1098907648));
               BlockPos var5 = new BlockPos((int)this.field_802.getX(), (int)this.field_802.getY(), (int)this.field_802.getZ());
               if (this.field_792.getValue()) {
                  if (var4 == null) {
                     this.method_293();
                     return;
                  }

                  if (var5.toCenterPos().distanceTo(var4.getBoundingBox().getCenter()) >= (double)(this.field_793.getValue() != null ? this.field_793.getValue().floatValue() : 0.0f)) {
                     this.method_293();
                     return;
                  }
               }

               if (field_4219.player.isSneaking() || !field_4219.player.isAlive()) {
                  this.method_293();
               }
            }
         }
      }
   }

   @Subscribe
   public void method_2(Event_30 var1) {
      if (var1.method_602().getItem() == Items.CHORUS_FRUIT && !field_4219.player.isSneaking()) {
         this.field_805 = field_4219.player.getEyePos();
         this.field_806 = field_4219.player.getPos();
         this.field_804 = false;
         this.field_803 = true;
         Hub.field_2602.method_985().reset();
      }
   }

   @Subscribe
   public void method_2(Event_10 var1) {
      if ((var1.method_127() instanceof TeleportConfirmC2SPacket || var1.method_127() instanceof PlayerMoveC2SPacket)
         && this.field_803
         && !field_4219.player.isSneaking()) {
         this.field_800.add(var1.method_127());
         var1.method_463();
      }
   }

   @Subscribe
   public void method_5(Event_4 var1) {
      if (var1.method_127() instanceof PlayerPositionLookS2CPacket var2) {
         this.field_802 = var2;
      }
   }

   @Subscribe
   public void method_9(Event_3 var1) {
      if (this.field_794.getValue()) {
         this.field_801.method_17(this.field_797.getValue());
         this.field_801.method_2(var1.method_10(), this.field_795.getValue(), this.field_796.getValue(), Float.intBitsToFloat(1134395392), true);
      }
   }

   @Subscribe
   public void method_2(Event_31 var1) {
      if (this.field_803 && this.field_805 != null) {
         var1.method_38(this.field_805);
         var1.method_463();
      }
   }

   @Subscribe
   public void method_2(Event_20 var1) {
      this.field_806 = null;
      this.field_803 = false;
      this.field_804 = false;
      this.field_802 = null;
   }

   @Subscribe
   public void method_2(Event_16 var1) {
      boolean var2 = var1.method_276().sneaking;
      if (this.field_803) {
         var1.reset();
         var1.method_276().sneaking = var2;
      }
   }

   public void method_293() {
      this.field_803 = false;
      if (this.field_802 != null) {
         field_798.field_609 = this.field_802.getY();
         field_799.field_609 = this.field_802.getY();
      }

      this.field_802 = null;

      while (!this.field_800.isEmpty()) {
         field_4219.player.networkHandler.sendPacket(this.field_800.poll());
      }

      Hub.field_2602.method_985().reset();
      Hub.field_2599.method_867().stream().filter(var0 -> var0.field_4256.getValue()).forEach(Module::disable);
      if (this.field_791 == null || this.field_791.getValue() == null) return;
      field_4219.player.setYaw((this.field_791.getValue() != null ? this.field_791.getValue().method_3(this.field_806) : 0.0f));
   }
}
