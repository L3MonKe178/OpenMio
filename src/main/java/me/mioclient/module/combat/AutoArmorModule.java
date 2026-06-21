package me.mioclient.module.combat;

import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_0577;
import me.mioclient.event.Event_1;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0136;
import me.mioclient.internal.Class_0242;
import me.mioclient.internal.Class_0382;
import me.mioclient.internal.Class_0396;
import me.mioclient.internal.Class_0756;
import me.mioclient.internal.Class_1117;
import me.mioclient.internal.Class_1261;
import me.mioclient.internal.Class_1264;
import me.mioclient.internal.Class_1323;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.AnimalArmorItem;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.EnchantmentTags;
import nick.Settings;

public class AutoArmorModule extends Module {
   public Setting<Integer> field_2922;
   public Setting<Boolean> field_2923;
   public Setting<Boolean> field_2924;
   public Setting<Boolean> field_2925;
   public Setting<Boolean> field_2926;
   public Setting<Boolean> field_2927;
   public Setting<Boolean> field_2928;
   public Setting<Boolean> field_2929;
   public Setting<Boolean> field_2930;
   public Setting<Boolean> field_2931;
   public Setting<Integer> field_2932;
   public Setting<Boolean> field_2933;
   public Setting<Float> field_2934;
   public Setting<Boolean> field_2935;
   public final Class_0242 field_2936;
   public final Class_0242 field_2937;
   public final Class_0242 field_2938;
   public Class_1264 field_2939;
   public boolean field_2940;

   public AutoArmorModule() {
      super("AutoArmor", "Equips the best armor in your inventory for you.", Category.COMBAT);
      Settings.initialize(this);
      this.field_2936 = new Class_0242();
      this.field_2937 = new Class_0242();
      this.field_2938 = new Class_0242();
      this.field_2939 = null;
      Runnable var1 = () -> this.field_2936.setTime(-1L);
      this.field_2925.method_9(var1);
      this.field_2927.method_9(var1);
      this.field_2928.method_9(var1);
   }

   @Override
   public void onEnable() {
      this.field_2939 = null;
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if (this.field_2940 != Hub.field_2605.method_224()) {
         this.field_2938.reset();
      }

      this.field_2940 = Hub.field_2605.method_224();
      if (this.method_2(field_4219.currentScreen)) {
         if (this.field_2936.method_9((long)this.field_2922.getValue().intValue())) {
            if (field_4219.player.isFallFlying()) {
               this.field_2937.reset();
            }

            if (Class_0382.method_4(field_4219.player)
               && !field_4219.player.isFallFlying()
               && !Class_0396.method_2(field_4219.player)
               && !this.field_2936.method_9(300L)
               && !this.field_2937.method_9(500L)) {
               Class_1261.method_1099();
               field_4219.player.startFallFlying();
            }

            boolean var2 = false;
            if (this.field_2933.getValue()) {
               for (AbstractClientPlayerEntity var4 : field_4219.world.getPlayers()) {
                  if (var4 != field_4219.player
                     && var4.distanceTo(field_4219.player) <= this.field_2934.getValue()
                     && (!Hub.field_2603.method_1009(var4.getGameProfile().getName()) || !this.field_2935.getValue())) {
                     var2 = true;
                  }
               }
            } else {
               var2 = true;
            }

            if (var2) {
               if (this.field_2939 != null && !this.field_2939.method_497()) {
                  this.field_2939.method_273();
                  this.field_2936.reset();
               } else {
                  for (Class_0577 var6 : Class_0577.values()) {
                     ItemStack var7 = field_4219.player.getInventory().getStack(var6.method_34());
                     if (!Class_0756.method_9(Enchantments.BINDING_CURSE, var7)) {
                        double var8 = this.method_2(var7, var6);
                        int var10 = var6.method_34();

                        for (int var11 = 0; var11 < 36; var11++) {
                           ItemStack var12 = field_4219.player.getInventory().getStack(var11);
                           double var13 = this.method_2(var12, var6);
                           if (var13 > var8) {
                              var8 = var13;
                              var10 = var11;
                           }
                        }

                        if (var10 != var6.method_34() && var8 != Double.longBitsToDouble(-4616189618054758400L)) {
                           this.field_2939 = new Class_1264(this, var6, var10);
                           break;
                        }
                     }
                  }

                  if (this.method_848() && (this.field_2939 == null || this.field_2939.method_497())) {
                     ItemStack var16 = field_4219.player.getInventory().getStack(Class_0577.HELMET.method_34());
                     if (!var16.isEmpty() && !Class_0756.method_9(Enchantments.BINDING_CURSE, var16)) {
                        this.field_2939 = new Class_1264(this, Class_0577.HELMET, -1);
                        this.field_2939.method_222(true);
                     }
                  }
               }
            }
         }
      }
   }

   public double method_2(ItemStack var1, Class_0577 var2) {
      float var3 = Class_0136.method_29(var1);
      if (this.field_2931.getValue() && var3 < (float)this.field_2932.getValue().intValue()) {
         return Double.longBitsToDouble(-4616189618054758400L);
      } else if (!this.field_2924.getValue() && Class_0756.method_9(Enchantments.BINDING_CURSE, var1)) {
         return Double.longBitsToDouble(-4616189618054758400L);
      } else {
         if (var1.getItem() instanceof ArmorItem var4 && var4.getSlotType().getEntitySlotId() == var2.method_596()) {
            double var12 = 0.0;
            if (var4 instanceof AnimalArmorItem) {
               return Double.longBitsToDouble(-4616189618054758400L);
            }

            if (this.method_848() && var4.getSlotType() == EquipmentSlot.HEAD) {
               return Double.longBitsToDouble(-4616189618054758400L);
            }

            if (this.field_2928.getValue() && var1.isOf(Items.TURTLE_HELMET)) {
               var12 = Double.longBitsToDouble(4666722622711529472L);
            }

            var12 += (double)(var4.getProtection() * 10);
            var12 += (double)((int)var4.getToughness() * 10);
            if (var1.hasEnchantments()) {
               RegistryKey var7 = var2 != Class_0577.LEGGINGS && !this.field_2925.getValue() ? Enchantments.PROTECTION : Enchantments.BLAST_PROTECTION;

               for (Entry var9 : var1.getEnchantments().getEnchantmentEntries()) {
                  if (!((RegistryEntry)var9.getKey()).isIn(EnchantmentTags.CURSE)) {
                     if (var9.getKey() == var7) {
                        var12 += (double)(var9.getValue() * 3);
                     } else {
                        var12 += (double)var9.getValue().intValue() * Double.longBitsToDouble(4591870180066957722L);
                     }

                     if (((RegistryEntry)var9.getKey()).matchesKey(Enchantments.THORNS) && !this.field_2926.getValue()) {
                        var12 -= (double)var9.getValue().intValue();
                     }
                  }
               }
            }

            return var12;
         }

         if (var1.isOf(Items.ELYTRA) && var2 == Class_0577.CHESTPLATE) {
            if (ElytraItem.isUsable(var1) && Class_1323.method_5(var1) > 1) {
               double var10 = Double.longBitsToDouble(4607182418800017408L);
               if (this.field_2927.getValue()) {
                  var10 *= Double.longBitsToDouble(4666722622711529472L);
               }

               if (var1.hasEnchantments()) {
                  var10 += (double)Class_0756.method_2(Enchantments.UNBREAKING, var1);
                  var10 += (double)Class_0756.method_2(Enchantments.MENDING, var1);
               }

               return var10;
            } else {
               return Double.longBitsToDouble(-4616189618054758400L);
            }
         } else {
            return Double.longBitsToDouble(-4616189618054758400L);
         }
      }
   }

   public boolean method_2(Screen var1) {
      if (var1 == null) {
         return true;
      } else if (var1 instanceof Class_1117) {
         return true;
      } else {
         return var1 instanceof HandledScreen ? var1 instanceof InventoryScreen : true;
      }
   }

   public boolean method_848() {
      return !this.field_2929.getValue() ? false : !this.field_2930.getValue() || this.field_2940 && this.field_2938.method_9(500L);
   }
}
