package me.mioclient.module.combat;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import me.mioclient.Hub;
import me.mioclient.api.Class_0558;
import me.mioclient.event.Event_17;
import me.mioclient.event.Event_25;
import me.mioclient.event.Event_7;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0136;
import me.mioclient.internal.Class_0242;
import me.mioclient.internal.Class_0356;
import me.mioclient.internal.Class_0485;
import me.mioclient.internal.Class_1261;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket.Action;
import net.minecraft.potion.Potion;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import nick.Settings;

public class ArrowsModule extends Module {
   public static final AtomicBoolean field_653 = new AtomicBoolean();
   public Setting<Boolean> field_654;
   public Setting<Float> field_655;
   public Setting<Integer> field_656;
   public final Map<StatusEffect, Long> field_657;
   public final Class_0242 field_658;
   public int field_659;
   public Potion field_660;

   public ArrowsModule() {
      super("Arrows", "Will swap between effect arrows in your inventory.", Category.COMBAT, "projectiles");
      Settings.initialize(this);
      this.field_657 = new HashMap<>();
      this.field_658 = new Class_0242();
   }

   @Override
   public void onEnable() {
      if (field_653.get()) {
         this.disable();
      }
   }

   @Subscribe
   public void method_2(Event_25 var1) {
      PlayerEntity var2 = this.method_268();
      Hand var3 = field_4219.player.getActiveHand();
      if (var3 != null && field_4219.player.getStackInHand(var3).getItem() instanceof RangedWeaponItem) {
         if (var2 == null || this.field_659 != -1 && this.method_2(field_4219.player.getInventory().getStack(this.field_659), var2) > 0) {
            if (this.field_658.method_9(50L)) {
               for (int var8 = 0; var8 < field_4219.player.getInventory().size(); var8++) {
                  ItemStack var5 = field_4219.player.getInventory().getStack(var8);
                  if (var5.getItem() instanceof ArrowItem) {
                     if (var8 != this.field_659) {
                        this.field_658.reset();
                        Hub.field_2611.method_154(true);
                        Class_0136.method_5(Class_0136.method_30(this.field_659), Class_0136.method_30(var8));
                        Hub.field_2611.method_154(false);
                        this.field_659 = -1;
                     }

                     if (this.field_660 != null) {
                        for (StatusEffectInstance var7 : this.field_660.getEffects()) {
                           this.field_657.put((StatusEffect)var7.getEffectType().value(), System.currentTimeMillis());
                        }
                     }
                     break;
                  }
               }
            }
         } else {
            int var4 = field_4219.player.getInventory().selectedSlot;
            Class_0136.method_16((var4 + 1) % 9);
            Class_1261.method_2(Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, Direction.DOWN);
            Class_0136.method_16(var4);
            var1.method_463();
         }
      }
   }

   @Subscribe
   public void method_9(Event_17 var1) {
      if (field_4219.player.getMainHandStack().getItem() instanceof RangedWeaponItem) {
         Object var2 = this.method_268();
         if (var2 == null) {
            var2 = field_4219.player;
         }

         this.field_657.entrySet().removeIf(var0 -> var0.getValue() + 750L < System.currentTimeMillis());
         int var3 = -1;
         int var4 = -1;

         for (int var5 = 0; var5 < field_4219.player.getInventory().size(); var5++) {
            ItemStack var6 = field_4219.player.getInventory().getStack(var5);
            int var7 = this.method_2(var6, var2);
            if (var7 >= 0 && var7 > var3) {
               var4 = var5;
               var3 = var7;
            }
         }

         this.field_659 = var4;
         if (this.field_659 != -1) {
            this.field_660 = Class_0356.method_78(field_4219.player.getInventory().getStack(this.field_659));
         }
      }
   }

   @Subscribe
   public void method_5(Event_7 var1) {
      if (this.field_654.getValue()) {
         if (this.field_659 == -1 || this.method_2(field_4219.player.getInventory().getStack(this.field_659), field_4219.player) <= 0) {
            this.disable();
            return;
         }

         this.method_269();
      }
   }

   public PlayerEntity method_268() {
      if (this.field_654.getValue()) {
         return field_4219.player;
      } else if (field_4219.player.getPitch() < Float.intBitsToFloat(-1029046272)) {
         return field_4219.player;
      } else {
         AbstractClientPlayerEntity var1 = null;
         double var2 = Double.longBitsToDouble(5183643170566569984L);

         for (AbstractClientPlayerEntity var5 : field_4219.world.getPlayers()) {
            if (field_4219.player != var5 && !Hub.field_2603.method_30(var5)) {
               float[] var6 = Class_0485.method_14(var5);
               float var7 = MathHelper.angleBetween(field_4219.player.getYaw(), var6[0])
                  - var5.getDimensions(var5.getPose()).width() * Float.intBitsToFloat(1056964608);
               float var8 = MathHelper.angleBetween(field_4219.player.getPitch(), var6[1])
                  - var5.getDimensions(var5.getPose()).height() * Float.intBitsToFloat(1056964608);
               if (!(var7 > this.field_655.getValue()) && !(var8 > this.field_655.getValue())) {
                  double var9 = Math.hypot((double)var7, (double)var8);
                  if (var9 < var2) {
                     var1 = var5;
                     var2 = var9;
                  }
               }
            }
         }

         return var1;
      }
   }

   public int method_2(ItemStack var1, Object var2) {
      if (var1.getItem() instanceof ArrowItem && var2 instanceof PlayerEntity var3) {
         Potion var4 = Class_0356.method_78(var1);
         boolean var5 = var3 == field_4219.player || Hub.field_2603.method_30(var3);
         int var6 = 0;
         if (var4.getEffects().isEmpty()) {
            var6 = var5 ? -1 : 1;
         }

         for (StatusEffectInstance var8 : var4.getEffects()) {
            RegistryEntry var9 = var8.getEffectType();
            boolean var10 = var3.hasStatusEffect(var9);
            if (var10) {
               StatusEffectInstance var11 = var3.getStatusEffect(var9);
               float var12 = ((Class_0558)var11).mio$getDurationRation();
               if (var12 * Float.intBitsToFloat(1120403456) < (float)this.field_656.getValue().intValue()) {
                  var10 = false;
               }
            }

            if (!var10 && !this.field_657.containsKey(var9.value())) {
               StatusEffect var13 = (StatusEffect)var9.value();
               int var14 = (!var13.isInstant() ? var8.getAmplifier() : 0) + 1;
               if (this.method_9(var9) != var5) {
                  var14 *= -1;
               }

               var6 += var14;
            }
         }

         return var6;
      } else {
         return -1;
      }
   }

   public boolean method_9(RegistryEntry<StatusEffect> var1) {
      return var1 == StatusEffects.SLOW_FALLING ? false : ((StatusEffect)var1.value()).isBeneficial();
   }

   public void method_269() {
      int var1 = field_4219.player.getInventory().selectedSlot;
      int var2 = Class_0136.method_5(Items.BOW);
      Hub.field_2598.method_2(new float[]{field_4219.player.getYaw(), Float.intBitsToFloat(-1028390912)}, 999);
      if (var2 != -1 && !field_4219.player.isUsingItem()) {
         Class_0136.method_78(var2);
         field_4219.interactionManager.interactItem(field_4219.player, Hand.MAIN_HAND);
         if (!field_653.get()) {
            field_653.set(true);
            Hub.field_2619.method_2(() -> {
               field_4219.interactionManager.stopUsingItem(field_4219.player);
               Class_0136.method_78(var1);
               field_653.set(false);
               this.disable();
            }, 3);
         }
      }
   }
}
