package me.mioclient.module.combat;

import java.util.Comparator;
import java.util.stream.StreamSupport;
import me.mioclient.Hub;
import me.mioclient.enum_.Priority;
import me.mioclient.event.Event_7;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0136;
import me.mioclient.internal.Class_0245;
import me.mioclient.internal.Class_1035;
import me.mioclient.internal.Class_1245;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import nick.Settings;

public class SelfWebModule extends Module {
   public Setting<Boolean> field_486;
   public Setting<Boolean> field_487;
   public Setting<Boolean> field_488;
   public Setting<Boolean> field_489;
   public Setting<Float> field_490;
   public Setting<Boolean> field_491;

   public SelfWebModule() {
      super("SelfWeb", "Places a cobweb at your feet.", Category.COMBAT);
      Settings.initialize(this);
   }

   @Subscribe
   public void method_29(Event_7 var1) {
      Entity var2 = this.method_5(this.field_490);
      if (this.field_489.getValue()) {
         if (var2 != null) {
            this.method_4(var1);
         }
      } else {
         this.method_4(var1);
      }
   }

   public void method_4(Event_7 var1) {
      if (!field_4219.player.hasVehicle() && (Hub.field_2605.method_224() || !this.field_488.getValue())) {
         BlockPos var2 = BlockPos.ofFloored(field_4219.player.getPos());
         BlockState var3 = field_4219.world.getBlockState(var2);
         if (var3.isReplaceable() && !var3.isLiquid() && field_4219.player.isOnGround()) {
            int var4 = Class_0136.method_5(Items.COBWEB);
            int var5 = field_4219.player.getInventory().selectedSlot;
            if (var4 == -1) {
               Class_1245.method_2(Text.literal(this.getName()).append(" is out of blocks!"), Class_1245.method_38(-2), Priority.HIGH);
               this.disable();
            } else {
               Direction var6 = Class_1035.method_78(var2);
               if (var6 != null) {
                  Class_0136.method_16(var4);
                  Box var7 = field_4219.player.getBoundingBox();
                  field_4219.player.setBoundingBox(new Box(0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
                  Class_1035.method_2(var2, var6, false, Hand.MAIN_HAND);
                  field_4219.player.setBoundingBox(var7);
                  Class_0136.method_16(var5);
                  if (this.field_486.getValue()) {
                     Hub.field_2598.method_2(new float[]{var1.method_500(), (float)Class_0245.field_685}, 5);
                  }

                  if (this.field_487.getValue()) {
                     this.disable();
                  }
               }
            }
         }
      }
   }

   public Entity method_5(Setting<Float> var1) {
      return StreamSupport.<Entity>stream(field_4219.world.getEntities().spliterator(), false)
         .filter(
            var2 -> var2 != field_4219.player
                  && field_4219.player.distanceTo(var2) <= (Float)var1.getValue()
                  && this.method_209(var2)
                  && var2 instanceof PlayerEntity
                  && !Hub.field_2603.method_1009(var2.getName().getString())
                  && var2.isAlive()
         )
         .min(Comparator.comparing(field_4219.player::squaredDistanceTo))
         .orElse(null);
   }

   public boolean method_209(Entity var1) {
      return this.field_491.getValue() ? var1.getY() >= field_4219.player.getY() : var1.getY() > field_4219.player.getY();
   }
}
