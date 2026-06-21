package me.mioclient.module.player;

import me.mioclient.enum_.Class_0186;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.EnderPearlItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ProjectileItem;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import nick.Settings;

public class NoInteractModule extends Module {
   public Setting<Class_0186> field_1452;
   public Setting<Boolean> field_1453;
   public boolean field_1454;

   public NoInteractModule() {
      super("NoInteract", "Prevents you from interacting with tile entities.", Category.PLAYER);
      Settings.initialize(this);
   }

   public static boolean method_105(ItemStack var0) {
      return var0.contains(DataComponentTypes.FOOD)
         || var0.getItem() instanceof EnderPearlItem
         || var0.getItem() instanceof ProjectileItem
         || var0.getItem() instanceof RangedWeaponItem;
   }

   public static Hand method_490() {
      ItemStack var0 = field_4219.player.getMainHandStack();
      ItemStack var1 = field_4219.player.getOffHandStack();
      return !method_105(var0) && method_105(var1) ? Hand.OFF_HAND : Hand.MAIN_HAND;
   }

   public boolean method_2(ItemStack var1, BlockPos var2) {
      if (this.isToggled() && (!this.field_1453.getValue() || var1.contains(DataComponentTypes.FOOD))) {
         BlockState var3 = field_4219.world.getBlockState(var2);
         return var3.isOf(Blocks.ANVIL) ? true : var3.hasBlockEntity() && !(var3.getBlock() instanceof BedBlock);
      } else {
         return false;
      }
   }
}
