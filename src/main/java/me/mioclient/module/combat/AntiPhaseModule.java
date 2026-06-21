package me.mioclient.module.combat;

import java.awt.Color;
import me.mioclient.Hub;
import me.mioclient.api.Class_0742;
import me.mioclient.enum_.Class_1298;
import me.mioclient.event.Event_7;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0003;
import me.mioclient.internal.Class_0035;
import me.mioclient.internal.Class_0088;
import me.mioclient.internal.Class_0136;
import me.mioclient.internal.Class_0242;
import me.mioclient.internal.Class_0382;
import me.mioclient.internal.Class_0485;
import me.mioclient.internal.Class_1161;
import me.mioclient.internal.Class_1261;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.movement.NoSlowModule;
import me.mioclient.record.Class_0828;
import me.mioclient.record.Class_1013;
import me.mioclient.setting.Setting;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import nick.Settings;

public class AntiPhaseModule extends Module {
   public static final NoSlowModule field_843 = Hub.field_2595.method_78(NoSlowModule.class);
   public Setting<Class_1298> field_844;
   public Setting<Float> field_845;
   public Setting<Integer> field_846;
   public Setting<Integer> field_847;
   public Setting<Boolean> field_848;
   public Setting<Boolean> field_849;
   public Setting<Boolean> field_850;
   public Setting<Boolean> field_851;
   public Setting<Color> field_852;
   public Setting<Color> field_853;
   public Setting<Float> field_854;
   public Setting<Boolean> field_855;
   public Setting<Float> field_856;
   public final Class_0242 field_857;

   public AntiPhaseModule() {
      super("AntiPhase", "Prevents your enemies from pearl-phasing into nearest block.", Category.COMBAT);
      Settings.initialize(this);
      this.field_857 = new Class_0242();
      Hub.field_2616.method_2(new Class_0828(this, this.field_852, this.field_853, this.field_854, this.field_856, () -> true, this.field_855, 450));
   }

   @Subscribe
   public void method_9(Event_7 var1) {
      if (this.field_857.method_5((long)this.field_846.getValue().intValue()) && !field_843.method_573()) {
         int var2 = 0;

         for (AbstractClientPlayerEntity var4 : field_4219.world.getPlayers()) {
            if (this.method_33(var4)) {
               Class_1013 var5 = this.method_301().method_2(this, var4);
               if (var5 != null) {
                  if (this.method_2(var5)) {
                     if (this.field_849.getValue()) {
                        float[] var6 = Class_0485.method_78(var5.method_908());
                        Hub.field_2598.method_2(var6, 25);
                     }

                     if (this.field_851.getValue()) {
                        Hub.field_2616.method_2(this, var5.method_172());
                     }

                     var2++;
                  }

                  if (var2 >= this.field_847.getValue()) {
                     break;
                  }
               }
            }
         }
      }
   }

   public boolean method_2(Class_1013 var1) {
      Item var2 = this.field_844.getValue().method_903();
      int var3 = Class_0136.method_5(var2);
      int var4 = field_4219.player.getInventory().selectedSlot;
      if (var3 == -1) {
         return false;
      } else {
         Class_0136.method_78(var3);
         BlockHitResult var5 = new BlockHitResult(var1.method_908(), var1.method_20(), var1.method_406(), false);
         Hub.field_2615.method_3(() -> Class_1261.method_2(Hand.MAIN_HAND, var5));
         Class_1261.method_9(Hand.MAIN_HAND);
         Class_0136.method_78(var4);
         return true;
      }
   }

   public boolean method_33(PlayerEntity var1) {
      if (!var1.isAlive() || field_4219.player == var1 || var1.isSwimming()) {
         return false;
      } else if (Hub.field_2603.method_30(var1)) {
         return false;
      } else if (Class_0382.method_7((LivingEntity)var1).size() != 1) {
         return false;
      } else if (this.method_194(Class_0382.method_9(var1))) {
         return false;
      } else if (!Class_0382.method_29((LivingEntity)var1)) {
         return false;
      } else {
         return this.field_850.getValue() && var1.getBoundingBox().getLengthY() <= Double.longBitsToDouble(4607182418800017408L)
            ? false
            : var1.distanceTo(field_4219.player) <= this.field_845.getValue();
      }
   }

   public boolean method_194(BlockPos var1) {
      Box var2 = new Box(var1);

      for (Entity var4 : field_4219.world.getEntities()) {
         if (var4 instanceof ItemFrameEntity && var4.getBoundingBox().intersects(var2)) {
            return true;
         }
      }

      return false;
   }

   public Class_0742 method_301() {
      return (Class_0742)(switch ((Class_1298)this.field_844.getValue()) {
         case LADDER -> new Class_0088();
         case SCAFFOLDING -> new Class_0035();
         case ITEMFRAME -> new Class_1161();
         case VINE -> new Class_0003();
      });
   }
}
