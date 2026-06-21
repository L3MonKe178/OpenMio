package me.mioclient.enum_;

import java.util.function.Predicate;
import me.mioclient.api.Nameable;
import me.mioclient.api.Class_1019;
import me.mioclient.internal.Class_0867;
import me.mioclient.internal.Class_0891;
import me.mioclient.internal.Class_0985;
import me.mioclient.internal.Class_1160;
import me.mioclient.internal.Class_1259;
import net.minecraft.item.Item;

public enum Class_0060 implements Nameable {
   field_175("swap", var0 -> true, new Class_0891()),
   field_176("alternative", var0 -> true, new Class_1160()),
   field_177("armor", Class_0985::method_38, new Class_0867()),
   field_178("offhand", var0 -> true, new Class_1259());

   public final String field_179;
   public final Class_1019 field_180;
   public final Predicate<Item> field_181;

    Class_0060(String var3, Predicate<Item> var4, Class_1019 var5) {
      this.field_179 = var3;
      this.field_181 = var4;
      this.field_180 = var5;
   }

   public boolean method_78(Item var1) {
      return this.field_181.test(var1);
   }

   public Class_1019 method_87() {
      return this.field_180;
   }

   @Override
   public String getName() {
      return this.field_179;
   }
}
