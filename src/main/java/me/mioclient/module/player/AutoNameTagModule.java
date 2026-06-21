package me.mioclient.module.player;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import me.mioclient.enum_.Class_0285;
import me.mioclient.enum_.Class_0710;
import me.mioclient.enum_.PreType;
import me.mioclient.event.Event_19;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.PlayerUtil;
import me.mioclient.internal.Timer;
import me.mioclient.internal.RotationManager;
import me.mioclient.internal.Class_1035;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import nick.Settings;

public class AutoNameTagModule extends Module {
   public Setting<Class_0710> field_4224;
   public Setting<Set<EntityType<?>>> field_4225;
   public Setting<Float> field_4226;
   public Setting<Integer> field_4227;
   public Setting<Float> field_4228;
   public Setting<Boolean> field_4229;
   public Setting<Class_0285> field_4230;
   public final Timer field_4231;

   public AutoNameTagModule() {
      super("AutoNameTag", "Will use name tags on nearby entities.", Category.PLAYER);
      Settings.initialize(this);
      this.field_4231 = new Timer();
   }

   @Subscribe
   public void method_9(Event_19 var1) {
      if (var1.method_213() != PreType.POST) {
         if (this.field_4231.method_2((double)(this.field_4228.getValue() != null ? this.field_4228.getValue().floatValue() : 0.0f), TimeUnit.SECONDS)) {
            int var2 = PlayerUtil.method_7(this::method_340);
            int var3 = field_4219.player.getInventory().selectedSlot;
            int var4 = 0;
            if (this.field_4230.getValue() != Class_0285.NONE) {
               PlayerUtil.method_16(var2);
            }

            for (Entity var6 : field_4219.world.getEntities()) {
               if (!this.method_340(field_4219.player.getMainHandStack())) {
                  break;
               }

               if (this.field_4224 == null || this.field_4224.getValue() == null) continue;
               if ((this.field_4224.getValue() != null ? this.field_4224.getValue().method_2(var6.getType(), this.field_4225) : false)
                  && !(var6 instanceof PlayerEntity)
                  && !field_4219.player.getMainHandStack().getName().getString().equalsIgnoreCase(var6.hasCustomName() ? var6.getCustomName().getString() : "")
                  && !(field_4219.player.getEyePos().distanceTo(var6.getPos()) > (double)(this.field_4226.getValue() != null ? this.field_4226.getValue().floatValue() : 0.0f))) {
                  Class_1035.method_2(var6, Hand.MAIN_HAND);
                  var4++;
                  if (this.field_4229.getValue()) {
                     var1.method_5(RotationManager.method_78(var6.getPos()));
                  }

                  if (var4 >= this.field_4227.getValue()) {
                     break;
                  }
               }
            }

            if (this.field_4230.getValue() == Class_0285.SILENT) {
               PlayerUtil.method_16(var3);
            }

            this.field_4231.reset();
         }
      }
   }

   public boolean method_340(ItemStack var1) {
      return var1.isOf(Items.NAME_TAG) && var1.contains(DataComponentTypes.CUSTOM_NAME);
   }
}
