package me.mioclient.module.abstract_;

import me.mioclient.event.Event_1;
import me.mioclient.event.Event_17;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0136;
import me.mioclient.internal.Class_0242;
import me.mioclient.internal.Class_1303;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.client.world.ClientChunkManager;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.ChunkPos;
import nick.Settings;

public class AbstractModule_9 extends Module {
   public final Class_0242 field_923 = new Class_0242();
   public final Class_0242 field_924 = new Class_0242();
   public Setting<Boolean> field_925;
   public Setting<Boolean> field_926;
   public Setting<Float> field_927;
   public Setting<Integer> field_928;

   public AbstractModule_9() {
      super(
         "Trident",
         new Class_1303().method_2(String.valueOf(Formatting.RED)).method_9("Improves your experience with tridents. \n\u0001Riptide enchantment required."),
         Category.EXPLOIT
      );
      Settings.initialize(this);
   }

   @Subscribe
   public void method_2(Event_17 var1) {
      int var2 = this.method_318();
      if (var2 != -1 && !field_4219.player.isUsingItem()) {
         field_4219.interactionManager
            .clickSlot(0, Class_0136.method_30(var2), field_4219.player.getInventory().selectedSlot, SlotActionType.SWAP, field_4219.player);
         field_4219.interactionManager.interactItem(field_4219.player, Hand.MAIN_HAND);
         field_4219.interactionManager.stopUsingItem(field_4219.player);
         field_4219.interactionManager
            .clickSlot(0, Class_0136.method_30(var2), field_4219.player.getInventory().selectedSlot, SlotActionType.SWAP, field_4219.player);
         this.field_923.reset();
      }
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if (this.method_319()) {
         this.field_924.reset();
      }

      field_4219.player.horizontalSpeed = 0.0F;
   }

   public int method_318() {
      return this.field_923.method_9((long)(this.field_928.getValue() * 50)) && !this.field_924.method_9(700L) ? Class_0136.method_9(Items.TRIDENT) : -1;
   }

   public boolean method_319() {
      ClientChunkManager var1 = field_4219.world.getChunkManager();
      return ChunkPos.stream(field_4219.player.getChunkPos(), 2).allMatch(var1x -> var1.isChunkLoaded(var1x.x, var1x.z)) || !this.field_926.getValue();
   }

   public float method_320() {
      return !this.isToggled() ? Float.intBitsToFloat(1065353216) : this.field_927.getValue();
   }

   public boolean method_321() {
      return this.isToggled() && this.field_925.getValue();
   }
}
