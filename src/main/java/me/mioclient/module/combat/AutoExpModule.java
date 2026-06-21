package me.mioclient.module.combat;

import me.mioclient.Hub;
import me.mioclient.enum_.Class_0456;
import me.mioclient.enum_.PreType;
import me.mioclient.event.Event_19;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0136;
import me.mioclient.internal.Class_0242;
import me.mioclient.internal.Class_0485;
import me.mioclient.internal.Class_0719;
import me.mioclient.internal.Class_1261;
import me.mioclient.internal.Class_1323;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.misc.AntiAimModule;
import me.mioclient.setting.Setting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.Box;
import nick.Settings;

public class AutoExpModule extends Module {
   public Setting<Class_0456> field_3419;
   public Setting<Integer> field_3420;
   public Setting<Integer> field_3421;
   public Setting<Boolean> field_3422;
   public Setting<Boolean> field_3423;
   public Setting<Boolean> field_3424;
   public Setting<Integer> field_3425;
   public Setting<Boolean> field_3426;
   public Setting<Boolean> field_3427;
   public static AntiAimModule field_3428 = Hub.field_2595.method_78(AntiAimModule.class);
   public final Class_0242 field_3429;
   public int field_3430;
   public boolean field_1702;

   public AutoExpModule() {
      super("AutoExp", "Mends your armor for you.", Category.COMBAT);
      Settings.initialize(this);
      this.field_3429 = new Class_0242();
      this.field_1702 = false;
   }

   @Override
   public void onEnable() {
      if (!this.method_535()) {
         if (this.field_3422.getValue()) {
            this.field_3429.reset();
         }

         if (field_3428.isToggled() && !this.field_1702) {
            field_3428.method_745();
         }

         this.field_3430 = field_4219.player.getInventory().selectedSlot;
      }
   }

   @Override
   public void onDisable() {
      if (!this.method_535()) {
         if (field_4219.player.getInventory().selectedSlot != this.field_3430 && this.field_3419.getValue() == Class_0456.NORMAL) {
            Class_0136.method_16(this.field_3430);
         }
      }
   }

   @Override
   public String getInfo() {
      if (this.field_1702) {
         return "Stopped";
      } else {
         int var1 = field_4219.player
            .getInventory()
            .main
            .stream()
            .filter(var0 -> var0.getItem() == Items.EXPERIENCE_BOTTLE)
            .mapToInt(ItemStack::getCount)
            .sum();
         return String.valueOf(var1);
      }
   }

   @Subscribe(
      method_800 = 250
   )
   public void method_9(Event_19 var1) {
      if (!this.method_404()) {
         if (var1.method_213() == PreType.PRE) {
            Box var2 = Hub.field_2612.method_2(field_4219.player, 1);
            float[] var3 = Class_0485.method_78(Class_0719.method_2(var2));
            if (!this.field_1702 && this.field_3422.getValue()) {
               Hub.field_2598.method_2(var3, 4, true);
            }

            boolean var4 = field_4219.player.isHolding(Items.EXPERIENCE_BOTTLE);
            int var5 = Class_0136.method_5(Items.EXPERIENCE_BOTTLE);
            if (this.field_3424.getValue()) {
               boolean var6 = true;

               for (ItemStack var8 : field_4219.player.getInventory().armor) {
                  if (this.method_374(var8)) {
                     var6 = false;
                     break;
                  }
               }

               if ((this.method_374(field_4219.player.getMainHandStack()) || this.method_374(field_4219.player.getOffHandStack()))
                  && this.field_3427.getValue()) {
                  var6 = false;
               }

               if (var6 || var5 == -1) {
                  this.field_1702 = true;
                  if (this.field_3426.getValue()) {
                     this.disable();
                  }

                  return;
               }
            }

            if ((var5 != -1 || var4) && this.field_3429.method_9((long)(this.field_3420.getValue() * 50))) {
               Hand var9 = field_4219.player.getOffHandStack().isOf(Items.EXPERIENCE_BOTTLE) ? Hand.OFF_HAND : Hand.MAIN_HAND;
               this.field_1702 = false;
               int var10 = field_4219.player.getInventory().selectedSlot;
               if (field_4219.player.getMainHandStack().getItem() != Items.EXPERIENCE_BOTTLE && var9 == Hand.MAIN_HAND) {
                  Class_0136.method_16(var5);
               }

               for (int var11 = 0; var11 < this.field_3421.getValue(); var11++) {
                  if (this.field_3422.getValue()) {
                     Class_1261.method_2(var9, var3[0], var3[1]);
                  } else {
                     Class_1261.method_2(var9);
                  }
               }

               if (this.field_3419.getValue() == Class_0456.SILENT && field_4219.player.getInventory().selectedSlot != var10) {
                  Class_0136.method_16(var10);
               }

               this.field_3429.reset();
            }
         }
      }
   }

   public boolean method_374(ItemStack var1) {
      return var1.isDamageable() && !var1.isEmpty() && Class_1323.method_5(var1) < this.field_3425.getValue();
   }

   public boolean method_404() {
      return (
               this.field_3423.getValue()
                  || field_4219.crosshairTarget.getType() != Type.MISS
                     && (!(field_4219.crosshairTarget instanceof BlockHitResult var1) || !field_4219.world.getBlockState(var1.getBlockPos()).isReplaceable())
            )
            && !OffhandModule.method_639()
         ? field_4219.player.isFallFlying()
         : true;
   }
}
