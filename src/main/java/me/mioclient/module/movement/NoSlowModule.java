package me.mioclient.module.movement;

import java.util.function.Predicate;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_0614;
import me.mioclient.enum_.PreType;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_10;
import me.mioclient.event.Event_3;
import me.mioclient.event.Event_36;
import me.mioclient.event.Event_47;
import me.mioclient.event.Event_48;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0031;
import me.mioclient.internal.Class_0136;
import me.mioclient.internal.Class_0185;
import me.mioclient.internal.Class_0242;
import me.mioclient.internal.Class_0245;
import me.mioclient.internal.Class_0382;
import me.mioclient.internal.Class_0406;
import me.mioclient.internal.Class_0464;
import me.mioclient.internal.Class_0617;
import me.mioclient.internal.Class_0746;
import me.mioclient.internal.Class_0982;
import me.mioclient.internal.Class_1035;
import me.mioclient.internal.Class_1261;
import me.mioclient.mixin.ducks.DuckPlayerMoveC2SPacket;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.abstract_.AbstractModule_28;
import me.mioclient.setting.Setting;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.ingame.AbstractCommandBlockScreen;
import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import net.minecraft.client.gui.screen.ingame.SignEditScreen;
import net.minecraft.client.gui.screen.ingame.StructureBlockScreen;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket.Mode;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import nick.Settings;
import org.lwjgl.glfw.GLFW;

public class NoSlowModule extends Module {
   public static AbstractModule_28 field_144 = Hub.field_2595.method_78(AbstractModule_28.class);
   public Setting<Boolean> field_1685;
   public Setting<Boolean> field_1686;
   public Setting<Boolean> field_1687;
   public Setting<Boolean> field_1688;
   public Setting<Boolean> field_1689;
   public Setting<Boolean> field_1690;
   public Setting<Class_0614> field_1691;
   public Setting<Boolean> field_1692;
   public Setting<Boolean> field_1693;
   public Setting<Boolean> field_1694;
   public Setting<Boolean> field_1695;
   public Setting<Boolean> field_1696;
   public Setting<Boolean> field_1697;
   public Setting<Boolean> field_1698;
   public Setting<Boolean> field_1699;
   public Setting<Boolean> field_1700;
   public boolean field_1701;
   public boolean field_1702;
   public boolean field_1703;
   public final Class_0242 field_1704;
   public final Class_0031 field_1705;
   public final Class_0031 field_1706;

   public NoSlowModule() {
      super("NoSlow", "Cancels several things that may slow you down.", Category.MOVEMENT);
      Settings.initialize(this);
      this.field_1704 = new Class_0242();
      this.field_1705 = new Class_0031(Float.intBitsToFloat(1073741824), true);
      this.field_1706 = new Class_0031(Float.intBitsToFloat(1073741824), true);
      this.setDrawn(false);
   }

   @Subscribe
   public void method_2(Event_10 var1) {
      if (var1.method_127() instanceof PlayerMoveC2SPacket var2) {
         Hand var6 = field_4219.player.getActiveHand();
         ItemStack var4 = field_4219.player.getStackInHand(var6);
         if (field_4219.player.isUsingItem()
            && this.field_1687.getValue()
            && !field_4219.player.isRiding()
            && !field_4219.player.isFallFlying()
            && var4.contains(DataComponentTypes.FOOD)
            && this.method_571()) {
            field_4219.player.networkHandler.sendPacket(new UpdateSelectedSlotC2SPacket(field_4219.player.getInventory().selectedSlot));
         }

         DuckPlayerMoveC2SPacket var5 = (DuckPlayerMoveC2SPacket)var2;
         if (this.field_1685.getValue()
            && this.field_1692.getValue()
            && !this.method_572()
            && this.field_1701
            && !field_4219.player.isRiding()
            && field_4219.player.isOnGround()
            && this.field_1704.method_9(200L)
            && !field_4219.player.isFallFlying()) {
            if (this.field_1691.getValue() == Class_0614.GRIMV3) {
               return;
            }

            var5.setOnGround(false);
            this.field_1701 = false;
         }
      }
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      this.field_1702 = false;
      this.field_1703 = false;
      if (!field_4219.player.isOnGround()) {
         this.field_1704.reset();
      }
   }

   @Subscribe
   public void method_5(Event_36 var1) {
      if (this.field_1691.getValue() == Class_0614.CONSTANTIAM && this.field_1687.getValue() && !field_4219.player.isFallFlying()) {
         if (field_4219.player.getStackInHand(var1.method_12()).contains(DataComponentTypes.FOOD)) {
            BlockPos var2 = var1.method_382().getBlockPos();
            if (!Class_1035.method_31(var2)) {
               field_4219.interactionManager.interactItem(field_4219.player, Hand.MAIN_HAND);
               var1.method_463();
            }
         }
      }
   }

   @Subscribe
   public void method_2(Event_47 var1) {
      if (var1.method_213() == PreType.PRE && this.field_1692.getValue() && !this.method_572() && this.field_1685.getValue()) {
         if (!this.field_1693.getValue() && !this.field_1702) {
            field_4219.interactionManager.stopUsingItem(field_4219.player);
            this.field_1702 = true;
         }

         if (!Class_0464.method_363()) {
            return;
         }

         if (this.field_1691.getValue() == Class_0614.GRIMV3) {
            return;
         }

         if (Hub.field_2615.method_1161() > 1 && !this.field_1703) {
            Class_1261.method_2(
               field_4219.player.getX(), field_4219.player.getY() + Double.longBitsToDouble(4589175226049939217L), field_4219.player.getZ(), false
            );
            this.field_1703 = true;
            this.field_1701 = true;
         }

         if (field_4219.player.isSprinting()) {
            field_4219.player.networkHandler.sendPacket(new ClientCommandC2SPacket(field_4219.player, Mode.STOP_SPRINTING));
         }

         if (field_4219.player.isSneaking()) {
            field_4219.player.networkHandler.sendPacket(new ClientCommandC2SPacket(field_4219.player, Mode.RELEASE_SHIFT_KEY));
         }
      } else if (var1.method_213() == PreType.POST && Class_0464.method_363() && this.method_571() && this.field_1685.getValue()) {
         if (field_4219.player.isSprinting()) {
            field_4219.player.networkHandler.sendPacket(new ClientCommandC2SPacket(field_4219.player, Mode.START_SPRINTING));
         }

         if (field_4219.player.isSneaking()) {
            field_4219.player.networkHandler.sendPacket(new ClientCommandC2SPacket(field_4219.player, Mode.PRESS_SHIFT_KEY));
         }
      }
   }

   @Subscribe
   public void method_2(Event_48 var1) {
      if (this.field_1687.getValue() && field_4219.player.isUsingItem() && !field_4219.player.hasVehicle()) {
         boolean var2 = true;
         if (this.field_1691.getValue() == Class_0614.GRIMV3) {
            var2 = field_4219.player.age % 3 == 0 || field_4219.player.age % 4 == 0;
            if (field_4219.player.age % 12 == 0) {
               var2 = false;
            }

            if (field_4219.player.isFallFlying()) {
               var2 = false;
            }
         }

         if (var2) {
            field_4219.player.input.movementSideways = field_4219.player.input.movementSideways / Float.intBitsToFloat(1045220557);
            field_4219.player.input.movementForward = field_4219.player.input.movementForward / Float.intBitsToFloat(1045220557);
         }

         if (this.method_572()) {
            this.method_38(field_4219.player.getActiveHand());
         }
      }
   }

   @Subscribe
   public void method_5(Event_3 var1) {
      if (this.method_569()
         && this.field_1685.getValue()
         && !(field_4219.currentScreen instanceof Class_0406)
         && !(field_4219.currentScreen instanceof Class_0617)) {
         if (this.method_138(264)) {
            this.field_1705
               .method_3(
                  Math.min(
                     MathHelper.lerp(var1.method_880(), field_4219.player.prevPitch, field_4219.player.getPitch()) + Float.intBitsToFloat(1090519040),
                     (float)Class_0245.field_685
                  )
               );
         } else if (this.method_138(265)) {
            this.field_1705
               .method_3(
                  Math.max(
                     MathHelper.lerp(var1.method_880(), field_4219.player.prevPitch, field_4219.player.getPitch()) - Float.intBitsToFloat(1090519040),
                     (float)(-Class_0245.field_685)
                  )
               );
         }

         if (this.method_138(262)) {
            this.field_1706
               .method_3(MathHelper.lerp(var1.method_880(), field_4219.player.prevYaw, field_4219.player.getYaw()) + Float.intBitsToFloat(1090519040));
         } else if (this.method_138(263)) {
            this.field_1706
               .method_3(MathHelper.lerp(var1.method_880(), field_4219.player.prevYaw, field_4219.player.getYaw()) - Float.intBitsToFloat(1090519040));
         }

         if (this.method_570()) {
            field_4219.player.setPitch(this.field_1705.method_45());
            field_4219.player.setYaw(this.field_1706.method_45());
         }
      } else {
         this.field_1706.method_36(field_4219.player.getYaw());
         this.field_1705.method_36(field_4219.player.getPitch());
      }
   }

   public void method_38(Hand var1) {
      Hand var2 = Class_0382.method_5(var1);
      if (this.method_251(field_4219.player.getStackInHand(var2))) {
         if (var2 == Hand.MAIN_HAND) {
            int var4 = field_4219.player.getInventory().selectedSlot;
            Class_0136.method_78((var4 + 1) % 9);
            Class_1261.method_2(var2);
            Class_0136.method_78(var4);
            return;
         }

         int var3 = Class_0136.method_30(Class_0136.method_2((Predicate<ItemStack>)(var1x -> !this.method_251(var1x)), true));
         field_4219.interactionManager.clickSlot(0, var3, 40, SlotActionType.SWAP, field_4219.player);
         Class_1261.method_2(var2);
         field_4219.interactionManager.clickSlot(0, var3, 40, SlotActionType.SWAP, field_4219.player);
      } else {
         Class_1261.method_2(var2);
      }
   }

   public boolean method_251(ItemStack var1) {
      return var1.contains(DataComponentTypes.FOOD) || var1.isOf(Items.BOW) || var1.isOf(Items.SHIELD) || var1.isOf(Items.ENDER_PEARL);
   }

   public boolean method_569() {
      if (field_4219.currentScreen instanceof Class_0406) {
         for (Class_0746 var2 : Hub.method_755().method_999()) {
            if (var2 instanceof Class_0185 && var2.method_194()) {
               return false;
            }
         }

         if (Hub.method_755().field_1310.isFocused() || !Hub.method_755().field_1310.getText().isBlank()) {
            return false;
         }
      }

      return field_4219.currentScreen != null
         && !(field_4219.currentScreen instanceof ChatScreen)
         && !(field_4219.currentScreen instanceof SignEditScreen)
         && !(field_4219.currentScreen instanceof AnvilScreen)
         && !(field_4219.currentScreen instanceof AbstractCommandBlockScreen)
         && !(field_4219.currentScreen instanceof StructureBlockScreen)
         && !(field_4219.currentScreen instanceof me.mioclient.clickgui.ClickGuiScreen)
         && (Class_0982.field_3026 == null || !Class_0982.field_3026.isActive());
   }

   public boolean method_570() {
      return this.method_138(264) || this.method_138(265) || this.method_138(262) || this.method_138(263);
   }

   public boolean method_138(int var1) {
      return GLFW.glfwGetKey(field_4219.getWindow().getHandle(), var1) == 1;
   }

   public boolean method_571() {
      return this.field_1691.getValue() == Class_0614.NCP;
   }

   public boolean method_572() {
      return this.field_1691.getValue() == Class_0614.GRIM;
   }

   public boolean method_573() {
      return this.isToggled()
         && !this.field_1693.getValue()
         && field_4219.player.isUsingItem()
         && !field_4219.player.hasVehicle()
         && field_4219.player.getActiveHand() == Hand.MAIN_HAND;
   }
}
