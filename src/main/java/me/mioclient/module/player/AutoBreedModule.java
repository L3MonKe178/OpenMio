package me.mioclient.module.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import me.mioclient.Hub;
import me.mioclient.event.Event_7;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0136;
import me.mioclient.internal.Class_0242;
import me.mioclient.internal.Class_0250;
import me.mioclient.internal.Class_0485;
import me.mioclient.internal.Class_1035;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.DonkeyEntity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import nick.Settings;

public class AutoBreedModule extends Module {
   public Setting<Float> field_807;
   public Setting<Integer> field_808;
   public Setting<Float> field_809;
   public Setting<Boolean> field_810;
   public Setting<Boolean> field_811;
   public Setting<Boolean> field_812;
   public Setting<Boolean> field_813;
   public Setting<Boolean> field_814;
   public Setting<Boolean> field_815;
   public Setting<Boolean> field_816;
   public Setting<Boolean> field_817;
   public final Class_0242 field_818;
   public final List<Class_0250> field_819;

   public AutoBreedModule() {
      super("AutoBreed", "Breeds animals in between each other automatically.", Category.PLAYER);
      Settings.initialize(this);
      this.field_818 = new Class_0242();
      this.field_819 = Collections.synchronizedList(new ArrayList<>());
   }

   @Override
   public void onEnable() {
      this.field_819.clear();
   }

   @Subscribe
   public void method_5(Event_7 var1) {
      this.field_819.forEach(Class_0250::method_273);
      this.field_819.removeIf(var0 -> var0.method_275() >= 6000);
      if (this.field_818.method_2((double)this.field_809.getValue().floatValue(), TimeUnit.SECONDS)) {
         int var2 = 0;

         label49:
         for (Entity var4 : field_4219.world.getEntities()) {
            if (var4 instanceof AnimalEntity) {
               AnimalEntity var5 = (AnimalEntity)var4;

               for (Class_0250 var7 : this.field_819) {
                  if (var5 == var7.method_274()) {
                     continue label49;
                  }
               }

               if (this.method_9(var5) && var5.distanceTo(field_4219.player) <= this.field_807.getValue()) {
                  int var8 = Class_0136.method_7(method_2(var5));
                  if (var8 == -1) {
                     return;
                  }

                  Vec3d var9 = var5.getBoundingBox().getCenter();
                  if (var9 != null) {
                     Class_0136.method_16(var8);
                     Class_1035.method_2(var5, Hand.MAIN_HAND);
                     this.field_819.add(new Class_0250(var5));
                     var2++;
                     if (this.field_810.getValue()) {
                        Hub.field_2598.method_2(Class_0485.method_78(var9), 5);
                     }

                     if (var2 >= this.field_808.getValue()) {
                        break;
                     }
                  }
               }
            }
         }

         this.field_818.reset();
      }
   }

   public static Predicate<ItemStack> method_2(AnimalEntity var0) {
      return var0 instanceof ParrotEntity
         ? var0x -> List.of(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS).contains(var0x.getItem())
         : var0::isBreedingItem;
   }

   public boolean method_9(AnimalEntity var1) {
      if (var1.isBaby()) {
         return false;
      } else {
         Objects.requireNonNull(var1);

         return switch (var1) {
            case WolfEntity var4 -> this.field_812.getValue();
            case CatEntity var5 -> this.field_813.getValue();
            case ParrotEntity var6 -> this.field_814.getValue();
            case DonkeyEntity var7 -> this.field_815.getValue();
            case HorseEntity var8 -> this.field_816.getValue();
            default -> var1.getBreedingAge() != 0 && this.field_817.getValue();
         };
      }
   }
}
