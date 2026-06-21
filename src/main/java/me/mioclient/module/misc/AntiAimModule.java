package me.mioclient.module.misc;

import me.mioclient.Hub;
import me.mioclient.enum_.Class_0555;
import me.mioclient.enum_.Class_0616;
import me.mioclient.enum_.Class_0717;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_10;
import me.mioclient.event.Event_7;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0485;
import me.mioclient.internal.Class_0981;
import me.mioclient.internal.Class_1035;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.item.TridentItem;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractItemC2SPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import nick.Settings;

public class AntiAimModule extends Module {
   public Setting<Boolean> field_2570;
   public Setting<Class_0616> field_2571;
   public Setting<Float> field_2572;
   public Setting<Float> field_2573;
   public Setting<Float> field_2574;
   public Setting<Float> field_2575;
   public Setting<Float> field_2576;
   public Setting<Boolean> field_2577;
   public Setting<Class_0717> field_2578;
   public Setting<Float> field_2579;
   public Setting<Boolean> field_2580;
   public Setting<Class_0555> field_2581;
   public boolean field_2315;
   public float field_2582;
   public int field_2583;

   public AntiAimModule() {
      super("AntiAim", "Sets your rotations server-side.", Category.MISC);
      Settings.initialize(this);
      this.field_2571.method_31("YawMode");
      this.field_2578.method_31("PitchMode");
   }

   @Subscribe(
      method_800 = -100
   )
   public void method_29(Event_7 var1) {
      this.field_2315 = false;
      if (Hub.field_2598.method_510() == null) {
         if (this.field_2583 > 0) {
            this.field_2583--;
         } else {
            float var2 = var1.method_500();
            float var3 = var1.method_501();
            if (this.field_2570.getValue()) {
               var2 = this.field_2571.getValue().method_9(this, field_4219.player.getYaw());
               this.method_746();
            }

            if (this.field_2577.getValue()) {
               var3 = this.field_2578.getValue().method_2(this, field_4219.player.getPitch());
               this.method_746();
            }

            if (this.field_2580.getValue()) {
               EndermanEntity var4 = this.method_744();
               if (var4 != null) {
                  if (this.field_2581.getValue() == Class_0555.AVOID) {
                     var3 = Float.intBitsToFloat(1119092736);
                     this.method_746();
                  }

                  if (this.field_2581.getValue() == Class_0555.STARE) {
                     float[] var5 = Class_0485.method_78(var4.getEyePos());
                     var2 = var5[0];
                     var3 = var5[1];
                     this.method_746();
                  }
               }
            }

            if (this.field_2315) {
               Hub.field_2598.method_2(new float[]{var2, var3}, -999, true);
            }
         }
      }
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      Item var2 = field_4219.player.getMainHandStack().getItem();
      boolean var3 = var2 instanceof RangedWeaponItem || var2 instanceof TridentItem;
      if (var3 && field_4219.player.isUsingItem()) {
         this.field_2583 = 2;
      }
   }

   @Subscribe
   public void method_2(Event_10 var1) {
      if (var1.method_127() instanceof PlayerInteractItemC2SPacket var2) {
         this.method_4(var2.getHand());
      }

      if (var1.method_127() instanceof PlayerInteractBlockC2SPacket var4) {
         this.method_4(var4.getHand());
      }
   }

   public EndermanEntity method_744() {
      for (Entity var2 : field_4219.world.getEntities()) {
         if (var2 instanceof EndermanEntity var3
            && !var3.isAngry()
            && var3.isAlive()
            && (this.field_2581.getValue() != Class_0555.AVOID || this.method_2(var3))
            && Class_0981.method_30(var2)) {
            return var3;
         }
      }

      return null;
   }

   public boolean method_2(EndermanEntity var1) {
      Vec3d var2 = field_4219.player.getRotationVec(Float.intBitsToFloat(1065353216)).normalize();
      Vec3d var3 = new Vec3d(var1.getX() - field_4219.player.getX(), var1.getEyeY() - field_4219.player.getEyeY(), var1.getZ() - field_4219.player.getZ());
      double var4 = var3.length();
      var3 = var3.normalize();
      double var6 = var2.dotProduct(var3);
      return var6 > Double.longBitsToDouble(4607182418800017408L) - Double.longBitsToDouble(4582862980812216730L) / var4;
   }

   public void method_4(Hand var1) {
      if (field_4219.player != null) {
         ItemStack var2 = field_4219.player.getStackInHand(var1);
         if (!var2.isEmpty()) {
            Item var3 = var2.getItem();
            if (var3 instanceof BlockItem || Class_1035.method_2(var3)) {
               this.field_2583 = 2;
            }
         }
      }
   }

   public boolean method_706() {
      return this.field_2315;
   }

   public void method_745() {
      this.field_2583 = 2;
   }

   public void method_746() {
      this.field_2315 = true;
   }
}
