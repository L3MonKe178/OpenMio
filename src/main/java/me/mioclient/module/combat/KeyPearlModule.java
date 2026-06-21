package me.mioclient.module.combat;

import me.mioclient.Hub;
import me.mioclient.enum_.PreType;
import me.mioclient.event.Event_19;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.PlayerUtil;
import me.mioclient.internal.Class_0382;
import me.mioclient.internal.PacketUtil;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.movement.ElytraFlyModule;
import me.mioclient.setting.Setting;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket.Full;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult.Type;
import nick.Settings;

public class KeyPearlModule extends Module {
   public static final ElytraFlyModule elytrafly = Hub.field_2595.method_78(ElytraFlyModule.class);
   public Setting<Boolean> field_858;
   public Setting<Boolean> field_859;
   public Setting<Boolean> field_860;
   public Setting<Boolean> field_861;

   public KeyPearlModule() {
      super("KeyPearl", "Throws an ender pearl.", Category.COMBAT);
      Settings.initialize(this);
      this.setDrawn(false);
   }

   @Override
   public void onEnable() {
      if (this.method_535()) {
         this.disable();
      } else {
         boolean var1 = elytrafly.isToggled() && this.field_860.getValue() && !field_4219.player.isOnGround() && Class_0382.method_4(field_4219.player);
         if (field_4219.player.hasVehicle() && this.field_861.getValue()) {
            var1 = true;
         }

         if (var1) {
            this.disable();
         }
      }
   }

   @Subscribe
   public void method_9(Event_19 var1) {
      if (var1.method_213() == PreType.PRE) {
         int var2 = PlayerUtil.method_5(Items.ENDER_PEARL);
         int var3 = PlayerUtil.method_9(Items.ENDER_PEARL);
         int var4 = field_4219.player.getInventory().selectedSlot;
         boolean var5 = field_4219.crosshairTarget instanceof EntityHitResult && this.field_858.getValue();
         if (var3 != -1 && !var5 && !field_4219.player.isFallFlying() && !elytrafly.method_1183() && !field_4219.player.hasVehicle()) {
            if (field_4219.crosshairTarget instanceof BlockHitResult var6
               && !field_4219.world.getBlockState(var6.getBlockPos()).isReplaceable()
               && var6.getType() != Type.MISS
               && !this.field_859.getValue()) {
               this.disable();
               return;
            }

            if (!OffhandModule.method_639()) {
               boolean var8 = var2 == -1;
               this.method_7(var8 ? var3 : var2, var8);
               field_4219.player
                  .networkHandler
                  .sendPacket(
                     new Full(
                        field_4219.player.getX(),
                        field_4219.player.getY(),
                        field_4219.player.getZ(),
                        field_4219.player.getYaw(),
                        field_4219.player.getPitch(),
                        field_4219.player.isOnGround()
                     )
                  );
               PacketUtil.method_2(Hand.MAIN_HAND);
               PacketUtil.method_9(Hand.MAIN_HAND);
               this.method_7(var8 ? var3 : var4, var8);
               Hub.field_2599.method_867().stream().filter(var0 -> var0.field_4257.getValue()).forEach(Module::disable);
               this.disable();
            }
         } else {
            this.disable();
         }
      }
   }

   public void method_7(int var1, boolean var2) {
      if (var2) {
         PlayerUtil.method_39(var1);
      } else {
         PlayerUtil.method_16(var1);
      }
   }
}
