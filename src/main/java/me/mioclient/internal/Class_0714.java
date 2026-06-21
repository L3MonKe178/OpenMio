package me.mioclient.internal;

import java.util.List;
import me.mioclient.record.Class_0974;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;

public final class Class_0714 {
   public static final List<Class_0974> field_2265 = List.of(
      new Class_0974(Enchantments.PROTECTION, 4), new Class_0974(Enchantments.UNBREAKING, 3), new Class_0974(Enchantments.MENDING, 1)
   );
   public static final List<Class_0974> field_2266 = List.of(
      new Class_0974(Enchantments.PROTECTION, 4), new Class_0974(Enchantments.UNBREAKING, 3), new Class_0974(Enchantments.MENDING, 1)
   );
   public static final List<Class_0974> field_2267 = List.of(
      new Class_0974(Enchantments.BLAST_PROTECTION, 4), new Class_0974(Enchantments.UNBREAKING, 3), new Class_0974(Enchantments.MENDING, 1)
   );
   public static final List<Class_0974> field_2268 = List.of(
      new Class_0974(Enchantments.PROTECTION, 4),
      new Class_0974(Enchantments.UNBREAKING, 3),
      new Class_0974(Enchantments.MENDING, 1),
      new Class_0974(Enchantments.FEATHER_FALLING, 4)
   );
   public static final List<Class_0974> field_2269 = List.of(new Class_0974(Enchantments.UNBREAKING, 3), new Class_0974(Enchantments.MENDING, 1));
   public static final List<Class_0974> field_2270 = List.of(
      new Class_0974(Enchantments.SHARPNESS, 5),
      new Class_0974(Enchantments.FIRE_ASPECT, 2),
      new Class_0974(Enchantments.KNOCKBACK, 2),
      new Class_0974(Enchantments.SWEEPING_EDGE, 3),
      new Class_0974(Enchantments.UNBREAKING, 3),
      new Class_0974(Enchantments.MENDING, 1)
   );

   public Class_0714() {
      super();
   }

   public static boolean method_9(ItemStack var0) {
      if (var0.getItem() instanceof ArmorItem var1) {
         return switch (var1.getSlotType()) {
            case MAINHAND, OFFHAND -> false;
            case FEET -> method_2(var0, field_2268);
            case LEGS -> method_2(var0, field_2267);
            case CHEST -> method_2(var0, field_2266);
            case HEAD -> method_2(var0, field_2265);
            case BODY -> false;
            default -> throw new MatchException(null, null);
         };
      } else if (var0.getItem() instanceof SwordItem) {
         return method_2(var0, field_2270);
      } else {
         return var0.isOf(Items.ELYTRA) ? method_2(var0, field_2269) : false;
      }
   }

   public static boolean method_2(ItemStack var0, Iterable<Class_0974> var1) {
      for (Class_0974 var3 : var1) {
         if (Class_0756.method_2(var3.method_576(), var0) != var3.method_473()) {
            return false;
         }
      }

      return true;
   }
}
