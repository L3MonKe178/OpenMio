package me.mioclient.module.combat;

import java.util.function.Predicate;
import me.mioclient.event.Event_17;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.PlayerUtil;
import me.mioclient.internal.PacketUtil;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.network.packet.c2s.play.PlayerInteractItemC2SPacket;
import nick.Settings;

public class AutoBowReleaseModule extends Module {
   public Setting<Integer> field_267;

   public AutoBowReleaseModule() {
      super("AutoBowRelease", "Shoots your bow automatically.", Category.COMBAT);
      Settings.initialize(this);
   }

   @Subscribe
   public void method_9(Event_17 var1) {
      if (!this.method_535() && PlayerUtil.method_29((Predicate<ItemStack>)(var0 -> var0.getItem() instanceof ArrowItem)) > 0) {
         int var2 = this.field_267.getValue();
         if (field_4219.player.getItemUseTime() >= var2
            && field_4219.player.getActiveItem().getItem() instanceof RangedWeaponItem var3
            && field_4219.player.isUsingItem()) {
            if (var3 instanceof CrossbowItem var5 && !CrossbowItem.isCharged(field_4219.player.getActiveItem())) {
               return;
            }

            field_4219.interactionManager.stopUsingItem(field_4219.player);
            PacketUtil.method_2(
               new PlayerInteractItemC2SPacket(
                  field_4219.player.getActiveHand(), PacketUtil.method_1101(), field_4219.player.getYaw(), field_4219.player.getPitch()
               )
            );
         }
      }
   }
}
