package me.mioclient.enum_;

import me.mioclient.api.Nameable;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

public enum Class_1298 implements Nameable {
   LADDER("Ladder", Items.LADDER),
   VINE("Vine", Items.VINE),
   SCAFFOLDING("Scaffolding", Items.SCAFFOLDING),
   ITEMFRAME("ItemFrame", Items.ITEM_FRAME);

   public final String field_4201;
   public final Item field_4202;

    Class_1298(String var3, Item var4) {
      this.field_4201 = var3;
      this.field_4202 = var4;
   }

   @Override
   public String getName() {
      return this.field_4201;
   }

   public Item method_903() {
      return this.field_4202;
   }
}
