package me.mioclient.module.movement;

import java.util.HashSet;
import java.util.List;
import me.mioclient.enum_.Class_0137;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_10;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.FontRenderer;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import nick.Settings;

public class NoFallModule extends Module {
   public Setting<Class_0137> field_4157;
   public Setting<Float> field_4158;

   public NoFallModule() {
      super("NoFall", "Prevents falling damage.", Category.MOVEMENT);
      Settings.initialize(this);
   }

   @Override
   public String getInfo() {
      return FontRenderer.method_3(this.field_4157.getValue());
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if (this.field_4157 == null || this.field_4157.getValue() == null) return;
      if (this.field_4157.getValue() != null) this.field_4157.getValue().method_2(this);
   }

   @Subscribe
   public void method_2(Event_10 var1) {
      if (this.field_4157 == null || this.field_4157.getValue() == null) return;
      if (this.field_4157.getValue() != null) this.field_4157.getValue().method_2(var1);
   }

   public HashSet<BlockPos> method_78(Box var1) {
      return new HashSet<>(
         List.of(
            BlockPos.ofFloored(var1.maxX, var1.minY, var1.maxZ),
            BlockPos.ofFloored(var1.maxX, var1.minY, var1.minZ),
            BlockPos.ofFloored(var1.minX, var1.minY, var1.maxZ),
            BlockPos.ofFloored(var1.minX, var1.minY, var1.minZ)
         )
      );
   }

   public boolean method_1147() {
      return field_4219.player.getInventory().getArmorStack(EquipmentSlot.CHEST.getEntitySlotId()).isOf(Items.ELYTRA) && field_4219.player.isFallFlying();
   }
}
