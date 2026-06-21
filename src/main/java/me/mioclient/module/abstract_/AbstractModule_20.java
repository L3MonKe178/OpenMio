package me.mioclient.module.abstract_;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import me.mioclient.enum_.Class_0710;
import me.mioclient.enum_.Class_0801;
import me.mioclient.event.Event_1;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0149;
import me.mioclient.internal.Class_0199;
import me.mioclient.internal.Class_1016;
import me.mioclient.record.Class_0197;
import me.mioclient.setting.BooleanSetting;
import me.mioclient.setting.CustomSetting;
import me.mioclient.setting.Setting;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Formatting;

public class AbstractModule_20 extends AbstractModule_26 {
   public final Setting<Set<EntityType<?>>> field_2134 = this.add(new Class_0199<>("WhiteList", Registries.ENTITY_TYPE, EntityType.PLAYER));
   public final Setting<Set<Item>> field_2135 = this.add(new Class_0199<>("Items", Registries.ITEM, Items.OBSIDIAN));
   public final Setting<Class_0710> field_2136 = this.add(new CustomSetting<>("Selection", Class_0710.BLACKLIST));
   public final Setting<Class_0710> field_2137 = this.add(new CustomSetting<>("ItemSelection", Class_0710.ANY));
   public final Setting<Class_0801> field_2138 = this.add(new CustomSetting<>("Sorting", Class_0801.ALPHABET));
   public final Setting<Boolean> field_2139 = this.add(new BooleanSetting("CustomNames", true));
   public final Setting<Boolean> field_2140 = this.add(new BooleanSetting("ColoredCount", true));
   public final Map<String, Integer> field_2141 = new HashMap<>();
   public final List<Class_0197> field_2142 = new ArrayList<>();

   public AbstractModule_20() {
      super("EntityList");
      this.field_2137.method_5(var1 -> this.field_2136.getValue().method_2(EntityType.ITEM, this.field_2134.getValue()));
      this.method_2(new Class_0149(this));
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      synchronized (this.field_2142) {
         this.field_2141.clear();

         for (Entity var4 : field_4219.world.getEntities()) {
            if (this.field_2136.getValue().method_2(var4.getType(), this.field_2134.getValue())) {
               if (var4 instanceof ItemEntity) {
                  ItemEntity var5 = (ItemEntity)var4;
                  if (var5.getStack().isEmpty()) {
                     continue;
                  }

                  Item var6 = var5.getStack().getItem();
                  if (!this.field_2137.getValue().method_2(var6, this.field_2135)) {
                     continue;
                  }
               }

               String var10 = this.field_2139.getValue() ? var4.getName().getString() : var4.getType().getName().getString();
               if (var4 instanceof ItemEntity var11) {
                  var10 = var11.getStack().getName().getString();
               }

               int var12 = var4 instanceof ItemEntity var7 ? var7.getStack().getCount() : 1;
               this.field_2141.compute(var10, (var1x, var2) -> var2 == null ? var12 : var2 + var12);
            }
         }

         this.field_2142.clear();
         this.field_2142.addAll(this.field_2141.entrySet().stream().map(Class_0197::method_5).toList());
         switch ((Class_0801)this.field_2138.getValue()) {
            case ALPHABET:
               this.field_2142.sort(Comparator.comparing(var0 -> var0.field_553));
               break;
            case COUNT:
               this.field_2142.sort(Comparator.comparing(var0 -> -var0.field_554));
               break;
            case LENGTH:
               this.field_2142.sort(Comparator.comparing(var0 -> Class_1016.field_3143.method_221(var0.method_2(Formatting.WHITE))));
         }
      }
   }

   @Override
   public void method_2(DrawContext var1) {
      float var2 = this.field_1844.method_194((float)Class_1016.field_3143.method_66()) - this.field_1844.method_60();
      synchronized (this.field_2142) {
         for (Class_0197 var5 : this.field_2142) {
            Color var6 = this.method_9(var2);
            Formatting var7 = this.field_2140.getValue() ? Formatting.WHITE : Formatting.RESET;
            String var8 = var5.method_2(var7);
            float var9 = Class_1016.field_3143.method_221(var8);
            Class_1016.field_3143.method_9(var1, var8, this.field_1844.method_176(var9) - this.field_1844.method_59(), var2, var6);
            var2 += (float)((Class_1016.field_3143.method_66() + 1) * this.field_1844.method_196());
         }
      }
   }

   @Override
   public float[] method_31() {
      float var1 = 0.0F;
      float var2 = 0.0F;
      synchronized (this.field_2142) {
         for (Class_0197 var5 : this.field_2142) {
            float var6 = Class_1016.field_3143.method_221(var5.method_2(Formatting.WHITE));
            var1 += (float)(Class_1016.field_3143.method_66() + 1);
            if (var6 > var2) {
               var2 = var6;
            }
         }
      }

      return new float[]{var2, var1};
   }
}
