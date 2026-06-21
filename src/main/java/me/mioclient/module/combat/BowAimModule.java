package me.mioclient.module.combat;

import me.mioclient.Hub;
import me.mioclient.enum_.PreType;
import me.mioclient.event.Event_19;
import me.mioclient.event.Event_52;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Constants;
import me.mioclient.internal.Class_0356;
import me.mioclient.internal.RotationManager;
import me.mioclient.internal.Class_1225;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.item.TridentItem;
import net.minecraft.network.packet.c2s.play.PlayerInteractItemC2SPacket;
import net.minecraft.potion.Potion;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import nick.Settings;

public class BowAimModule extends Module {
   public Setting<Boolean> field_2419;
   public Setting<Boolean> field_2420;
   public Setting<Boolean> field_2421;
   public Setting<Boolean> field_2422;
   public Setting<Boolean> field_2423;
   public Setting<Boolean> field_2424;
   public float[] field_2425;

   public BowAimModule() {
      super("BowAim", "Helps aiming with bows.", Category.COMBAT);
      Settings.initialize(this);
   }

   @Subscribe
   public void method_9(Event_19 var1) {
      Item var2 = field_4219.player.getMainHandStack().getItem();
      boolean var3 = var2 instanceof RangedWeaponItem || var2 instanceof TridentItem;
      boolean var4 = field_4219.player.isUsingItem();
      if (CrossbowItem.isCharged(field_4219.player.getMainHandStack())) {
         var4 = true;
      }

      if (var3 && var4) {
         if (var1.method_213() == PreType.PRE) {
            for (StatusEffectInstance var6 : this.method_720().getEffects()) {
               RegistryEntry var7 = var6.getEffectType();
               if (((StatusEffect)var7.value()).isBeneficial() && var7 != StatusEffects.SLOW_FALLING && var7 != StatusEffects.JUMP_BOOST) {
                  return;
               }
            }

            Entity var9 = Class_1225.method_2(
               this.field_2420.getValue(),
               this.field_2424.getValue(),
               this.field_2422.getValue(),
               this.field_2423.getValue(),
               false,
               false,
               0.0F,
               Float.intBitsToFloat(1124073472),
               false,
               this.field_2421.getValue()
            );
            if (var9 != null && !(var9 instanceof EndermanEntity)) {
               Box var10 = var9.getBoundingBox();
               if (var9 instanceof PlayerEntity var11) {
                  var10 = Hub.field_2612.method_2(var11, 5);
               }

               Vec3d var12 = var10.getCenter();
               if (RotationManager.method_4(var10) && Class_1225.method_9(var12)) {
                  float[] var8 = method_2(var9, true, Float.intBitsToFloat(1073741824));
                  if (Float.isNaN(var8[0]) || Float.isNaN(var8[1])) {
                     return;
                  }

                  this.field_2425 = var8;
                  Hub.field_2598.method_2(var8, 5, true);
               }
            }
         }
      }
   }

   @Subscribe
   public void method_2(Event_52 var1) {
      PlayerInteractItemC2SPacket var2 = var1.method_1102();
      if (field_4219.player.getStackInHand(var2.getHand()).isOf(Items.CROSSBOW) && this.field_2425 != null) {
         var1.method_9(this.field_2425);
      }
   }

   public Potion method_720() {
      for (int var1 = 0; var1 < field_4219.player.getInventory().size(); var1++) {
         ItemStack var2 = field_4219.player.getInventory().getStack(var1);
         if (var2.getItem() instanceof ArrowItem) {
            return Class_0356.method_78(var2);
         }
      }

      return Class_0356.field_1158;
   }

   public static float[] method_2(Entity var0, boolean var1, float var2) {
      ClientPlayerEntity var3 = field_4219.player;
      double var4 = var0.getX() + (var1 ? (var0.getX() - var0.prevX) * (double)var2 : 0.0) - (var3.getX() + (var1 ? var3.getX() - var3.prevX : 0.0));
      double var10001 = var1 ? (var0.getBoundingBox().minY - var0.prevY) * (double)var2 : 0.0;
      double var10000 = var0.getBoundingBox().minY + var10001 + (double)var0.getEyeHeight(var0.getPose()) - Double.longBitsToDouble(4594572339843380019L);
      double var10002 = var1 ? var3.getY() - var3.prevY : 0.0;
      double var6 = var10000 - (var3.getBoundingBox().minY + var10002) - (double)var3.getEyeHeight(var3.getPose());
      double var8 = var0.getZ() + (var1 ? (var0.getZ() - var0.prevZ) * (double)var2 : 0.0) - (var3.getZ() + (var1 ? var3.getZ() - var3.prevZ : 0.0));
      double var10 = Math.hypot(var4, var8);
      float var12 = (float)var3.getItemUseTime() / Float.intBitsToFloat(1101004800);
      if (var3.isHolding(Items.CROSSBOW)) {
         var12 = Float.intBitsToFloat(1065353216);
      }

      var12 = (var12 * var12 + var12 * Float.intBitsToFloat(1073741824)) / Float.intBitsToFloat(1077936128);
      if (var12 > Float.intBitsToFloat(1065353216)) {
         var12 = Float.intBitsToFloat(1065353216);
      }

      return new float[]{
         (float)(Math.atan2(var8, var4) * Double.longBitsToDouble(4640537203540230144L) / Constants.field_687) - (float)Constants.field_685,
         (float)(
            -Math.toDegrees(
               Math.atan(
                  (
                        (double)(var12 * var12)
                           - Math.sqrt(
                              (double)(var12 * var12 * var12 * var12)
                                 - Double.longBitsToDouble(4573567551241453568L)
                                    * (
                                       Double.longBitsToDouble(4573567551241453568L) * var10 * var10
                                          + Double.longBitsToDouble(4611686018427387904L) * var6 * (double)(var12 * var12)
                                    )
                           )
                     )
                     / (Double.longBitsToDouble(4573567551241453568L) * var10)
               )
            )
         )
      };
   }
}
