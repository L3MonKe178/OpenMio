package me.mioclient.internal;

import java.util.function.Predicate;
import me.mioclient.Hub;
import me.mioclient.api.MioAPI;
import me.mioclient.enum_.Class_0286;
import me.mioclient.enum_.Class_0446;
import me.mioclient.enum_.Class_0685;
import me.mioclient.enum_.Class_0813;
import me.mioclient.event.Event_1;
import me.mioclient.event.Subscribe;
import me.mioclient.module.combat.AutoCrystalModule;
import me.mioclient.module.misc.SwingModule;
import me.mioclient.module.render.HitmarkerModule;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
import net.minecraft.util.Hand;

public class Class_0975 implements MioAPI {
   public static final SwingModule swing = Hub.field_2595.method_78(SwingModule.class);
   public static AutoCrystalModule ac = Hub.field_2595.method_78(AutoCrystalModule.class);
   public static HitmarkerModule field_2993 = Hub.field_2595.method_78(HitmarkerModule.class);
   public final Timer field_2994 = new Timer();
   public long field_2995;
   public int current;

   public Class_0975() {
      super();
      field_4220.method_14(this);
   }

   @Subscribe(
      method_800 = -300
   )
   public void method_2(Event_1 var1) {
      this.current = -1;
   }

   public void method_157(int var1) {
      this.current = var1;
   }

   public boolean method_80(int var1) {
      return this.method_5(var1, false);
   }

   public boolean method_5(int var1, boolean var2) {
      if (!this.method_877() && var2) {
         return false;
      } else if (this.current != -1 && !var2) {
         return false;
      } else {
         int var3 = field_4219.player.getInventory().selectedSlot;
         boolean var4 = this.method_879();
         this.current = var1;
         PacketUtil.method_36(this.current);
         Entity var5 = field_4219.world.getEntityById(var1);
         if (var5 != null
            && var5.getWorld() != null
            && var5 != field_4219.player
            && var5.getWorld().isClient
            && field_2993.method_221(var5)
            && var5 instanceof EndCrystalEntity) {
            field_2993.field_1610 = System.currentTimeMillis();
            field_2993.field_1611 = 255;
            field_2993.field_1612 = true;
         }

         Hand var6 = field_4219.player.getOffHandStack().getItem() == Items.END_CRYSTAL ? Hand.OFF_HAND : Hand.MAIN_HAND;
         if (!(var5 instanceof EndCrystalEntity) && var5 != null) {
            var6 = Hand.MAIN_HAND;
         }

         this.method_7(var6);
         if (var2) {
            PacketUtil.method_9(Hand.MAIN_HAND);
         } else {
            field_4219.player.swingHand(var6);
         }

         if (var4 && ac.field_4080.getValue() == Class_0685.SILENT) {
            ac.method_1143();
            PlayerUtil.method_16(var3);
         }

         return true;
      }
   }

   public boolean method_877() {
      return ac.field_4077.getValue() != Class_0446.NONE;
   }

   public int method_878() {
      return ac.field_4067.getValue();
   }

   public int method_523() {
      return ac.field_4079.getValue();
   }

   public void method_7(Hand var1) {
      if (this.field_2994.method_5(200L + this.field_2995)
         && var1 == Hand.MAIN_HAND
         && swing.field_504.getValue() != Class_0813.OFFHAND
         && swing.field_505.getValue() != Class_0286.VANILLA) {
         field_4219.player.resetLastAttackedTicks();
         this.field_2995 = (long)(Math.random() * Double.longBitsToDouble(4643985272004935680L));
      }
   }

   public boolean method_879() {
      if (ac.field_4080.getValue() != Class_0685.NONE
         && field_4219.player.hasStatusEffect(StatusEffects.WEAKNESS)
         && !(field_4219.player.getMainHandStack().getItem() instanceof ToolItem)) {
         int var1 = PlayerUtil.method_7((Predicate<ItemStack>)(var0 -> var0.getItem() instanceof SwordItem));
         if (var1 != -1) {
            if (ac.field_4080.getValue() == Class_0685.SILENT) {
               ac.method_1143();
            }

            PlayerUtil.method_16(var1);
            return true;
         }
      }

      return false;
   }
}
