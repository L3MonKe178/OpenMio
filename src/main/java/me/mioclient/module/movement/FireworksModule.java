package me.mioclient.module.movement;

import java.util.concurrent.TimeUnit;
import me.mioclient.Hub;
import me.mioclient.enum_.SettingMode;
import me.mioclient.event.Event_17;
import me.mioclient.event.Event_36;
import me.mioclient.event.Event_40;
import me.mioclient.event.Event_55;
import me.mioclient.event.Event_9;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0136;
import me.mioclient.internal.Class_0242;
import me.mioclient.internal.Class_0382;
import me.mioclient.internal.Class_0464;
import me.mioclient.internal.Class_1261;
import me.mioclient.mixin.ducks.DuckFireworkEntity;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.exploit.RocketModule;
import me.mioclient.setting.Setting;
import net.minecraft.entity.Entity;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket.Mode;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import nick.Settings;

public final class FireworksModule extends Module {
   public static final RocketModule field_2675 = Hub.field_2595.method_78(RocketModule.class);
   public static final ElytraFlyModule field_2676 = Hub.field_2595.method_78(ElytraFlyModule.class);
   public Setting<Boolean> field_2677;
   public Setting<Boolean> field_2678;
   public Setting<Boolean> field_2679;
   public Setting<Float> field_2680;
   public Setting<Boolean> field_2681;
   public Setting<Boolean> field_2682;
   public Setting<Boolean> field_2683;
   public Setting<Boolean> field_2684;
   public Setting<Float> field_2685;
   public Setting<Boolean> field_2686;
   public Setting<Integer> field_2687;
   public Setting<Boolean> field_2688;
   public Setting<Float> field_2689;
   public Setting<Float> field_2690;
   public final Class_0242 field_2691;
   public boolean field_2692;
   public int field_2693;
   public int field_2694;
   public boolean field_2695;
   public boolean field_2696;

   public FireworksModule() {
      super("Fireworks", "Enhances the usage of firework rockets.", Category.MOVEMENT, "rockets");
      Settings.initialize(this);
      this.field_2691 = new Class_0242();
      this.field_2685.method_2("Auto", SettingMode.MIN);
   }

   @Subscribe
   public void method_9(Event_36 var1) {
      if (this.field_2677.getValue()) {
         if (field_4219.player.getStackInHand(var1.method_12()).isOf(Items.FIREWORK_ROCKET)) {
            field_4219.interactionManager.interactItem(field_4219.player, var1.method_12());
            field_4219.player.swingHand(var1.method_12());
            var1.method_463();
         }
      }
   }

   @Subscribe
   public void method_2(Event_55 var1) {
      if (this.method_786()) {
         if (field_4219.player.isHolding(Items.FIREWORK_ROCKET)) {
            this.field_2694 = 0;
            this.method_787();
            this.field_2693 = this.field_2687.getValue();
            this.field_2696 = true;
         }
      }
   }

   @Subscribe
   public void method_2(Event_17 var1) {
      if (this.field_2695) {
         field_4219.player.setSneaking(false);
         Class_1261.method_2(field_4219.player, Mode.RELEASE_SHIFT_KEY, 0);
         this.field_2695 = false;
         if (!Class_0382.method_4(field_4219.player)) {
            field_4219.player.stopFallFlying();
         }
      }

      if (this.field_2693 > 0) {
         this.method_787();
         this.field_2693--;
         this.field_2695 = this.field_2693 == 0;
      }

      if (this.field_2681.getValue() && (field_2676.isToggled() || !this.field_2682.getValue())) {
         if (!field_4219.player.isFallFlying()) {
            this.field_2691.setTime(-1L);
         }

         boolean var2 = !this.field_2691.method_2((double)this.field_2685.getValue().floatValue(), TimeUnit.SECONDS)
            || field_2675.isToggled() && field_2675.field_3473 != -1 && field_2675.field_3534 != null;
         if (this.method_784()) {
            var2 = true;
         }

         if (!var2) {
            if (!field_4219.player.isFallFlying()) {
               this.field_2692 = false;
            }

            if (!this.field_2692 || !this.field_2683.getValue()) {
               this.method_783();
            }
         }
      }
   }

   @Subscribe(
      method_800 = -100
   )
   public void method_2(Event_9 var1) {
      if (this.field_2688.getValue() && this.field_2693 > 1 && this.method_784()) {
         this.method_5(var1);
      }

      if (field_4219.player.isFallFlying() && this.field_2684.getValue() && this.field_2681.getValue()) {
         if (!this.method_784()) {
            var1.method_4(new Vec3d(0.0, 0.0, 0.0));
         }
      }
   }

   @Subscribe
   public void method_2(Event_40 var1) {
      if (this.field_2693 > 0
         && var1.method_863() != null
         && var1.method_863().getId() != null
         && var1.method_863().getId().toString().contains("item.armor.equip")) {
         var1.method_463();
      }
   }

   public void method_5(Event_9 var1) {
      float var2 = Float.intBitsToFloat(-1165815185);
      if (field_4219.player.input.jumping) {
         var2 = this.field_2690.getValue();
      } else if (field_4219.player.input.sneaking) {
         var2 = -this.field_2690.getValue();
      }

      float var3 = this.field_2689.getValue();
      if ((double)var3 > Double.longBitsToDouble(4609884578576439706L)) {
         var3 = MathHelper.clamp(
            Float.intBitsToFloat(1070386381) + (float)this.field_2694 * Float.intBitsToFloat(1036831949), Float.intBitsToFloat(1070386381), var3
         );
         this.field_2694++;
      }

      double[] var4 = Class_0464.method_2(var1, (double)var3);
      Vec3d var5 = new Vec3d(var4[0], (double)var2, var4[1]);
      field_4219.player.setVelocity(var5);
      var1.method_4(var5);
   }

   public void method_783() {
      if (this.field_2681.getValue() && field_4219.player.isFallFlying()) {
         Hand var1 = Class_0136.method_7(Items.FIREWORK_ROCKET);
         int var2 = field_4219.player.getInventory().selectedSlot;
         int var3 = Class_0136.method_9(Items.FIREWORK_ROCKET);
         int var4 = Class_0136.method_5(Items.FIREWORK_ROCKET);
         if (var1 != null) {
            field_4219.interactionManager.interactItem(field_4219.player, var1);
            this.field_2691.reset();
         } else if (var3 != -1) {
            boolean var5 = var4 == -1;
            this.method_7(var5 ? var3 : var4, var5);
            field_4219.interactionManager.interactItem(field_4219.player, Hand.MAIN_HAND);
            field_2675.field_3535 = field_4219.player.getPos();
            this.method_7(var5 ? var3 : var2, var5);
            this.field_2691.reset();
         }

         this.field_2692 = true;
      }
   }

   public void method_7(int var1, boolean var2) {
      if (var2) {
         Class_0136.method_39(var1);
      } else {
         Class_0136.method_16(var1);
      }
   }

   public boolean method_784() {
      if (!this.field_2691.method_9(500L)) {
         return true;
      } else {
         for (Entity var2 : field_4219.world.getEntities()) {
            if (var2 instanceof DuckFireworkEntity var3 && var3.mio$getShooter() == field_4219.player) {
               return true;
            }
         }

         return false;
      }
   }

   public float method_100(boolean var1) {
      return this.isToggled() && this.field_2678.getValue() && (var1 || this.field_2679.getValue()) && this.method_784() ? this.field_2680.getValue() : 0.0F;
   }

   public int method_785() {
      return this.field_2693;
   }

   public boolean method_786() {
      return Class_0382.method_4(field_4219.player)
         ? false
         : this.isToggled() && this.field_2686.getValue() && !field_4219.player.isOnGround() && Class_0136.method_9(Items.ELYTRA) != -1;
   }

   public void method_787() {
      int var1 = Class_0136.method_5(Items.ELYTRA);
      boolean var2 = var1 == -1;
      if (var1 == -1) {
         var1 = Class_0136.method_9(Items.ELYTRA);
      }

      if (var1 != -1) {
         int var3 = var1;
         if (var2) {
            var1 = 0;
            field_4219.interactionManager.clickSlot(0, var3, 0, SlotActionType.SWAP, field_4219.player);
         }

         field_4219.interactionManager.clickSlot(0, 6, var1, SlotActionType.SWAP, field_4219.player);
         Class_1261.method_1099();
         field_4219.player.startFallFlying();
         field_4219.interactionManager.clickSlot(0, 6, var1, SlotActionType.SWAP, field_4219.player);
         if (var2) {
            field_4219.interactionManager.clickSlot(0, var3, 0, SlotActionType.SWAP, field_4219.player);
         }
      }
   }
}
