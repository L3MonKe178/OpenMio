package me.mioclient.module.player;

import java.util.ArrayList;
import java.util.Random;
import me.mioclient.event.Event_10;
import me.mioclient.event.Event_36;
import me.mioclient.event.Subscribe;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;

public class BlockMixerModule extends Module {
   public final Random field_3750 = new Random();

   public BlockMixerModule() {
      super("BlockMixer", "Picks a random block from your hotbar as you build.", Category.PLAYER);
   }

   @Subscribe
   public void method_9(Event_36 var1) {
      if (field_4219.player.getMainHandStack().getItem() instanceof BlockItem) {
         this.method_1055();
      }
   }

   @Subscribe
   public void method_2(Event_10 var1) {
      if (var1.method_127() instanceof PlayerInteractBlockC2SPacket && field_4219.player.getMainHandStack().getItem() instanceof BlockItem) {
         this.method_1055();
      }
   }

   public void method_1055() {
      ArrayList var1 = new ArrayList();

      for (int var2 = 0; var2 < 9; var2++) {
         ItemStack var3 = field_4219.player.getInventory().getStack(var2);
         if (!var3.isEmpty() && var3.getItem() instanceof BlockItem) {
            var1.add(var2);
         }
      }

      if (var1.size() >= 2) {
         field_4219.player.getInventory().selectedSlot = (Integer)var1.get(this.field_3750.nextInt(var1.size()));
      }
   }
}
