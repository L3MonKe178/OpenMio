package me.mioclient.module.player;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import me.mioclient.enum_.Class_0494;
import me.mioclient.enum_.PreType;
import me.mioclient.event.Event_19;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0242;
import me.mioclient.internal.Class_0485;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.Vec3d;
import nick.Settings;

public class AutoTameModule extends Module {
   public Setting<Class_0494> field_1357;
   public Setting<Float> field_1358;
   public Setting<Integer> field_1359;
   public Setting<Float> field_1360;
   public Setting<Boolean> field_1361;
   public Setting<Boolean> field_1362;
   public Setting<Boolean> field_1363;
   public Setting<Boolean> field_1364;
   public Setting<Boolean> field_1365;
   public final Class_0242 field_1366;

   public AutoTameModule() {
      super("AutoTame", "Automatically interacts with tame-able entities.", Category.PLAYER);
      Settings.initialize(this);
      this.field_1366 = new Class_0242();
   }

   @Subscribe
   public void method_2(Event_19 var1) {
      if (var1.method_213() != PreType.POST) {
         if (this.field_1366.method_2((double)this.field_1360.getValue().floatValue(), TimeUnit.SECONDS)) {
            int var2 = 0;

            for (Entity var4 : field_4219.world.getEntities()) {
               if (var4 instanceof TameableEntity) {
                  TameableEntity var5 = (TameableEntity)var4;
                  if (this.method_2(var5) && var4.distanceTo(field_4219.player) <= this.field_1358.getValue()) {
                     Vec3d var6 = this.field_1357.getValue().method_5(var5);
                     if (var6 != null) {
                        var2++;
                        if (this.field_1361.getValue()) {
                           var1.method_5(Class_0485.method_78(var6));
                        }

                        if (var2 >= this.field_1359.getValue()) {
                           break;
                        }
                     }
                  }
               }
            }

            this.field_1366.reset();
         }
      }
   }

   public boolean method_2(TameableEntity var1) {
      return var1 instanceof CatEntity && this.field_1364.getValue()
         || var1 instanceof WolfEntity && this.field_1363.getValue()
         || var1 instanceof ParrotEntity && this.field_1365.getValue();
   }

   public static Predicate<ItemStack> method_9(TameableEntity var0) {
      if (var0 instanceof WolfEntity) {
         return var0x -> var0x.isOf(Items.BONE);
      } else {
         return var0 instanceof ParrotEntity
            ? var0x -> List.of(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS).contains(var0x.getItem())
            : var0::isBreedingItem;
      }
   }
}
