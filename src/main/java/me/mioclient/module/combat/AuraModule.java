package me.mioclient.module.combat;

import java.awt.Color;
import java.util.function.Predicate;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_0001;
import me.mioclient.enum_.Class_0448;
import me.mioclient.enum_.Class_0768;
import me.mioclient.enum_.Class_0885;
import me.mioclient.enum_.Class_1215;
import me.mioclient.enum_.SettingMode;
import me.mioclient.event.Event_10;
import me.mioclient.event.Event_3;
import me.mioclient.event.Event_7;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0055;
import me.mioclient.internal.PlayerUtil;
import me.mioclient.internal.Constants;
import me.mioclient.internal.Class_0382;
import me.mioclient.internal.Class_0396;
import me.mioclient.internal.RotationManager;
import me.mioclient.internal.Class_0612;
import me.mioclient.internal.Class_0719;
import me.mioclient.internal.Class_0756;
import me.mioclient.internal.RenderUtil;
import me.mioclient.internal.Class_0981;
import me.mioclient.internal.Class_1035;
import me.mioclient.internal.Class_1081;
import me.mioclient.internal.Class_1225;
import me.mioclient.mixin.ducks.DuckPlayerMoveC2SPacket;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.abstract_.AbstractModule_28;
import me.mioclient.module.movement.FastSwimModule;
import me.mioclient.module.movement.FlightModule;
import me.mioclient.setting.Setting;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MaceItem;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.TridentItem;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket.Mode;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import nick.Settings;

public class   AuraModule extends Module {
   public static final CriticalsModule field_1038 = Hub.field_2595.method_78(CriticalsModule.class);
   public static final FastSwimModule field_1039 = Hub.field_2595.method_78(FastSwimModule.class);
   public static final FlightModule field_1040 = Hub.field_2595.method_78(FlightModule.class);
   public static final AbstractModule_28 field_1041 = Hub.field_2595.method_78(AbstractModule_28.class);
   public Setting<Class_1215> field_1042;
   public Setting<Float> field_1043;
   public Setting<Float> field_1044;
   public Setting<Float> field_1045;
   public Setting<Float> field_1046;
   public Setting<Boolean> field_1047;
   public Setting<Boolean> field_1048;
   public Setting<Boolean> field_1049;
   public Setting<Float> field_1050;
   public Setting<Boolean> field_1051;
   public Setting<Color> field_1052;
   public Setting<Color> field_1053;
   public Setting<Float> field_1054;
   public Setting<Boolean> field_1055;
   public Setting<Class_0768> field_1056;
   public Setting<Class_0001> field_1057;
   public Setting<Class_0885> field_1058;
   public Setting<Boolean> field_1059;
   public Setting<Boolean> field_1060;
   public Setting<Boolean> field_1061;
   public Setting<Boolean> field_1062;
   public Setting<Boolean> field_1063;
   public Setting<Boolean> field_1064;
   public Setting<Boolean> field_1065;
   public Setting<Boolean> field_1066;
   public Setting<Boolean> field_1067;
   public Setting<Boolean> field_1068;
   public Setting<Boolean> field_1069;
   public final Class_0055 field_1070;
   public int field_1071;
   public boolean field_1072;
   public boolean field_1073;
   public Entity field_582;
   public Entity field_1074;

   public AuraModule() {
      super("Aura", "Attacks entities nearby.", Category.COMBAT, "killaura", "forcefield", "ka");
      Settings.initialize(this);
      this.field_1070 = new Class_0055();
      this.field_1071 = -1;
      this.field_1050.method_2("None", SettingMode.MIN);
      this.field_1048.method_334();
   }

   @Override
   public void onDisable() {
      if (this.method_535()) {
         this.field_1071 = -1;
      } else {
         this.method_372();
      }
   }

   @Override
   public void onToggle() {
      this.field_582 = null;
      this.field_1070.method_36(0.0F);
      Hub.field_2630.method_9(this);
   }

   @Override
   public String getInfo() {
      return this.field_582 != null && this.field_582 instanceof PlayerEntity ? this.field_582.getName().getString() : null;
   }

   @Subscribe
   public void method_7(Event_7 var1) {
      boolean var2 = this.field_582 != null && !this.field_582.isAlive();
      if ((this.method_3(field_4219.player.getMainHandStack().getItem()) || this.field_1056.getValue() != Class_0768.REQUIRE) && !var2) {
         if (field_4219.player.currentScreenHandler == field_4219.player.playerScreenHandler || !field_1041.method_1052()) {
            this.field_582 = Class_1225.method_2(
               this.field_1062.getValue(),
               this.field_1066.getValue(),
               this.field_1064.getValue(),
               this.field_1065.getValue(),
               this.field_1067.getValue(),
               this.field_1068.getValue(),
               this.field_1046.getValue() + Float.intBitsToFloat(1056964608),
               this.field_1045.getValue() + Float.intBitsToFloat(1056964608),
               this.field_1069.getValue(),
               this.field_1063.getValue(),
               (this.field_1042.getValue() != null ? this.field_1042.getValue().method_55() : null),
               this::method_374
            );
            Hub.field_2630.method_9(this);
            if (this.field_1048.getValue() && this.field_582 != null) {
               Hub.field_2630.method_2(this);
            }

            if (this.field_582 == null) {
               this.method_372();
            } else if (!RotationManager.method_513() || this.method_107(this.field_582) || field_4219.player.isFallFlying()) {
               this.method_300();
               if (this.field_1049.getValue()) {
                  if (!RotationManager.method_513()
                     && this.field_1050.getValue() != 0.0F
                     && this.field_1050.getValue() * this.field_1044.getValue() > this.method_371()) {
                     return;
                  }

                  float[] var3 = RotationManager.method_2(RotationManager.method_14(this.field_582), Hub.field_2598.method_509());
                  Hub.field_2598.method_2(var3, 5);
               }
            }
         }
      } else {
         this.field_582 = null;
      }
   }

   @Subscribe
   public void method_2(Event_10 var1) {
      if (var1.method_127() instanceof PlayerMoveC2SPacket var2
         && field_4219.player.fallDistance > Float.intBitsToFloat(1077936128)
         && !field_4219.world
            .isSpaceEmpty(
               field_4219.player.getBoundingBox().stretch(0.0, field_4219.player.getVelocity().y * Double.longBitsToDouble(4607632778762754458L), 0.0)
            )
         && this.field_1072) {
         ((DuckPlayerMoveC2SPacket)var2).setY(var2.getY(0.0) + Double.longBitsToDouble(4611686018427387904L));
         field_4219.player.setPos(field_4219.player.getX(), field_4219.player.getY() + Double.longBitsToDouble(4613937818241073152L), field_4219.player.getZ());
         this.field_1072 = false;
      }
   }

   @Subscribe
   public void method_9(Event_3 var1) {
      if (this.field_1051.getValue()) {
         this.field_1070.method_2(this.field_582 == null ? 0.0F : Float.intBitsToFloat(1065353216), 250L);
         float var2 = this.field_1070.method_45();
         Box var3 = this.method_370();
         if (var3 == null || var2 == 0.0F) {
            return;
         }

         Color var4 = Class_1081.method_9(this.field_1052.getValue(), (int)((float)(this.field_1052.getValue() != null ? this.field_1052.getValue().getAlpha() : 255) * var2));
         Color var5 = Class_1081.method_9(this.field_1053.getValue(), (int)((float)(this.field_1053.getValue() != null ? this.field_1053.getValue().getAlpha() : 255) * var2));
         Class_0612.method_5(var1.method_10(), var3, var4);
         Class_0612.method_2(var1.method_10(), var3, var5, this.field_1054.getValue());
      }
   }

   public Box method_370() {
      Box var1 = null;
      Entity var2 = this.field_582;
      if (var2 == null) {
         var2 = this.field_1074;
      }

      if (var2 != null) {
         var1 = Class_0719.method_2(var2, RenderUtil.method_776());
         this.field_1074 = var2;
      }

      return var1;
   }

   public void method_300() {
      if (this.field_582 != null) {
         if (this.method_107(this.field_582)) {
            float var1 = Class_0981.method_30(this.field_582) ? this.field_1045.getValue() : this.field_1046.getValue();
            if (!(Class_1225.method_39(this.field_582) > (double)var1)) {
               boolean var2 = field_4219.player.fallDistance <= 0.0F && !field_4219.player.isOnGround();
               if (field_4219.player.isOnGround() && Hub.field_2615.method_1161() <= 1) {
                  var2 = true;
               }

               if (field_4219.player.hasVehicle()) {
                  var2 = false;
               }

               if ((field_1040 != null && field_1040.isToggled())
                  || field_4219.player.getAbilities().flying
                  || field_4219.player.isFallFlying()
                  || Class_0382.method_29(field_4219.player)
                  || field_4219.player.isClimbing()
                  || !(this.field_582 instanceof LivingEntity)
                  || !var2
                  || field_1038.isToggled() && field_1038.field_3406.getValue() == Class_0448.GRIM
                  || field_4219.player.hasStatusEffect(StatusEffects.LEVITATION)) {
                  int var3 = field_4219.player.getInventory().selectedSlot;
                  int var4 = PlayerUtil.method_7((Predicate<ItemStack>)(var0 -> var0.getItem() instanceof AxeItem || var0.getItem() instanceof SwordItem));
                  boolean var5 = this.field_1057.getValue() == Class_0001.SILENT && this.method_375();
                  if (this.method_375()) {
                     if ((!var5 || this.field_1060.getValue())
                        && var4 != var3
                        && field_4219.player.isUsingItem()
                        && field_4219.player.getActiveHand() == Hand.MAIN_HAND) {
                        return;
                     }

                     PlayerUtil.method_16(var4);
                     if (var3 != var4) {
                        this.field_1071 = var3;
                     }
                  }

                  boolean var6 = field_4219.player.isSprinting();
                  if (var6) {
                     field_4219.player.networkHandler.sendPacket(new ClientCommandC2SPacket(field_4219.player, Mode.STOP_SPRINTING));
                  }

                  int var7 = this.method_373();
                  if (this.field_582 instanceof LivingEntity var8 && !Class_0382.method_29(var8)) {
                     var7 = -1;
                  }

                  if (var7 != -1) {
                     PlayerUtil.method_16(var7);
                  }

                  Class_1035.method_29(this.field_582);
                  field_4219.player.resetLastAttackedTicks();
                  if (var6) {
                     field_4219.player.networkHandler.sendPacket(new ClientCommandC2SPacket(field_4219.player, Mode.START_SPRINTING));
                  }

                  if (field_1039 != null) field_1039.field_4445 = false;
                  if (var7 != -1) {
                     if (this.field_1059.getValue()) {
                        this.field_1072 = Class_0756.method_9(Enchantments.DENSITY, field_4219.player.getInventory().getStack(var7));
                     }

                     PlayerUtil.method_16(var3);
                  }

                  if (var5 && var4 != -1) {
                     PlayerUtil.method_16(var3);
                  }
               }
            }
         }
      }
   }

   public boolean method_107(Entity var1) {
      if (!(this.field_582 instanceof PlayerEntity)
         && var1 instanceof LivingEntity var2
         && (double)Class_0396.method_2((Entity)var2) <= Double.longBitsToDouble(4607182418800017408L)
         && var2.getMaxHealth() <= Float.intBitsToFloat(1082130432)) {
         return true;
      }

      double var4 = (double)(this.field_1044.getValue() != null ? this.field_1044.getValue().floatValue() : 0.0f);
      return (double)this.method_371() >= var4;
   }

   public float method_371() {
      double var1 = Constants.field_688;
      if (this.field_1047.getValue()) {
         var1 -= (double)(Float.intBitsToFloat(1101004800) - Hub.field_2602.method_989());
      }

      return field_4219.player.getAttackCooldownProgress((float)var1);
   }

   public boolean method_3(Item var1) {
      return var1 instanceof SwordItem
         || var1 instanceof TridentItem
         || var1 instanceof AxeItem
         || var1 instanceof MaceItem
         || var1 instanceof PickaxeItem
            && PlayerUtil.method_7((Predicate<ItemStack>)(var0 -> var0.getItem() instanceof SwordItem || var0.getItem() instanceof AxeItem)) == -1;
   }

   public void method_372() {
      if (this.method_375() && this.field_1057.getValue() == Class_0001.SWAPBACK && this.field_1071 != -1) {
         PlayerUtil.method_16(this.field_1071);
         this.field_1071 = -1;
      }
   }

   public int method_373() {
      if (this.field_1057.getValue() == Class_0001.SILENT && this.method_375()) {
         return -1;
      } else {
         if (this.field_1058 == null || this.field_1058.getValue() == null) return 0;
         int var1 = PlayerUtil.method_7((Predicate<ItemStack>)(var1x -> var1x.isOf(Items.MACE) && (this.field_1058.getValue() != null ? this.field_1058.getValue().method_16(var1x) : false)));
         return this.field_1058.getValue() == Class_0885.SMART && field_4219.player.fallDistance > Float.intBitsToFloat(1077936128) && var1 == -1
            ? PlayerUtil.method_7((Predicate<ItemStack>)(var0 -> var0.isOf(Items.MACE)))
            : var1;
      }
   }

   public boolean method_374(Entity var1) {
      float[] var2 = RotationManager.method_14(var1);
      float var3 = MathHelper.angleBetween(var2[0], field_4219.player.getYaw());
      float var4 = Math.abs(field_4219.player.getPitch() - var2[1]);
      return var3 <= this.field_1043.getValue() && var4 <= this.field_1043.getValue();
   }

   public boolean method_375() {
      return this.field_1056.getValue() == Class_0768.SWAP;
   }
}
