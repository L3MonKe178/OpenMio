package me.mioclient.module.abstract_;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_0861;
import me.mioclient.event.Event_1;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0149;
import me.mioclient.internal.Class_0382;
import me.mioclient.internal.Class_0396;
import me.mioclient.internal.Class_0485;
import me.mioclient.internal.Class_1016;
import me.mioclient.module.render.NameTagsModule;
import me.mioclient.setting.BooleanSetting;
import me.mioclient.setting.CustomSetting;
import me.mioclient.setting.CustomSetting3;
import me.mioclient.setting.Setting;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.MathHelper;

public class AbstractModule_34 extends AbstractModule_26 {
   public final Setting<Class_0861> field_698 = this.add(new CustomSetting<>("Sort", Class_0861.DISTANCE));
   public final Setting<Boolean> field_699 = this.add(new BooleanSetting("Health", true));
   public final Setting<Boolean> field_700 = this.add(new BooleanSetting("Distance", false));
   public final Setting<Boolean> field_701 = this.add(new BooleanSetting("TotemPops", false));
   public final Setting<Boolean> field_702 = this.add(new BooleanSetting("FriendColor", true));
   public final Setting<Boolean> field_703 = this.add(new BooleanSetting("EnemyColor", true));
   public final Setting<Boolean> field_704 = this.add(new BooleanSetting("Armor", false));
   public final Setting<Boolean> field_705 = this.add(new BooleanSetting("Limit", false).method_460());
   public final Setting<Integer> field_706 = this.add(new CustomSetting3<>("Max", 8, 1, 32).method_2(this.field_705));
   public final Setting<Boolean> field_707 = this.add(new BooleanSetting("Ignore", false).method_220());
   public final Setting<Boolean> field_708 = this.add(new BooleanSetting("Friends", false).method_2(this.field_707));
   public final Setting<Boolean> field_709 = this.add(new BooleanSetting("Nakeds", false).method_2(this.field_707));
   public final List<PlayerEntity> field_710 = new ArrayList<>();

   public AbstractModule_34() {
      super("TextRadar", "playerlist");
      this.method_2(new Class_0149(this));
   }

   @Override
   public void onEnable() {
      this.field_710.clear();
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      synchronized (this.field_710) {
         this.field_710.clear();
         field_4219.world
            .getPlayers()
            .stream()
            .filter(this::method_22)
            .sorted(Comparator.comparing(this::method_37))
            .limit(this.field_705.getValue() ? (long)this.field_706.getValue().intValue() : Long.MAX_VALUE)
            .forEachOrdered(this.field_710::add);
      }
   }

   @Override
   public void method_2(DrawContext var1) {
      float var2 = this.field_1844.method_194((float)Class_1016.field_3143.method_66()) - this.field_1844.method_60();

      for (PlayerEntity var4 : this.field_710) {
         Color var5 = this.method_9(var2);
         if (Hub.field_2603.method_30(var4) && this.field_702.getValue()) {
            var5 = Hub.field_2603.method_9(var4.getGameProfile().getName(), var5);
         } else if (Hub.field_2603.method_16(var4) && this.field_703.getValue()) {
            var5 = Hub.field_2603.method_9(var4.getGameProfile().getName(), var5);
         }

         String var6 = this.method_31(var4);
         float var7 = Class_1016.field_3143.method_221(var6);
         Class_1016.field_3143.method_9(var1, var6, this.field_1844.method_176(var7) - this.field_1844.method_59(), var2, var5);
         var2 += (float)((Class_1016.field_3143.method_66() + 1) * this.field_1844.method_196());
      }
   }

   @Override
   public float[] method_31() {
      float var1 = 0.0F;
      float var2 = 0.0F;

      for (PlayerEntity var4 : this.field_710) {
         float var5 = Class_1016.field_3143.method_221(this.method_31(var4));
         var1 += (float)(Class_1016.field_3143.method_66() + 1);
         if (var5 > var2) {
            var2 = var5;
         }
      }

      return new float[]{var2, var1};
   }

   public String method_31(PlayerEntity var1) {
      StringBuilder var2 = new StringBuilder();
      float var3 = Class_0396.method_2((net.minecraft.entity.Entity)var1);
      if (this.field_699.getValue()) {
         var2.append(this.method_4((double)var3)).append(Math.round(var3)).append(" ");
      }

      var2.append(Formatting.RESET);
      var2.append(Hub.field_2626.method_8(var1.getGameProfile().getName()));
      if (this.field_701.getValue()) {
         int var4 = Hub.field_2613.method_39(var1);
         if (var4 > 0) {
            var2.append(NameTagsModule.method_323(var4)).append(" -").append(var4);
         }
      }

      if (this.field_700.getValue()) {
         var2.append(Formatting.WHITE).append(" ").append(Math.round(field_4219.player.distanceTo(var1))).append("m");
      }

      if (this.field_704.getValue()) {
         String var9 = "G";

         for (ItemStack var6 : var1.getArmorItems()) {
            Item var8 = var6.getItem();
            if (var8 instanceof ArmorItem) {
               ArmorItem var7 = (ArmorItem)var8;
               RegistryEntry var10 = var7.getMaterial();
               if (var10 != ArmorMaterials.DIAMOND && var10 != ArmorMaterials.NETHERITE || !var6.hasEnchantments()) {
                  var9 = "";
               }
            } else {
               if (var6.getItem() == Items.ELYTRA) {
                  var9 = "W";
                  break;
               }

               var9 = "";
            }
         }

         if (!var9.isEmpty()) {
            var2.append(Formatting.WHITE);
            var2.append(" [");
            var2.append(var9);
            var2.append("]");
         }
      }

      return var2.toString();
   }

   public Formatting method_4(double var1) {
      if (var1 >= Double.longBitsToDouble(4626322717216342016L)) {
         return Formatting.GREEN;
      } else if (var1 >= Double.longBitsToDouble(4625196817309499392L)) {
         return Formatting.DARK_GREEN;
      } else if (var1 >= Double.longBitsToDouble(4621819117588971520L)) {
         return Formatting.GOLD;
      } else {
         return var1 >= Double.longBitsToDouble(4616189618054758400L) ? Formatting.RED : Formatting.DARK_RED;
      }
   }

   public double method_37(Entity var1) {
      return this.field_698.getValue() == Class_0861.DISTANCE
         ? var1.squaredDistanceTo(field_4219.player)
         : (double)MathHelper.angleBetween(field_4219.gameRenderer.getCamera().getYaw(), Class_0485.method_14(var1)[0]);
   }

   public boolean method_22(PlayerEntity var1) {
      if (!this.method_22() && field_4219.player == var1) {
         return false;
      } else {
         return this.field_708.getValue() && Hub.field_2603.method_30(var1) ? false : !this.field_709.getValue() || Class_0382.method_29(var1);
      }
   }
}
