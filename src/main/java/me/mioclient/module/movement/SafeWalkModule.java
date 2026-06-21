package me.mioclient.module.movement;

import me.mioclient.event.Event_1;
import me.mioclient.event.Event_8;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0242;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import nick.Settings;

public class SafeWalkModule extends Module {
   public Setting<Boolean> field_2154;
   public Setting<Integer> field_2155;
   public final Class_0242 field_2156;

   public SafeWalkModule() {
      super("SafeWalk", "Helps not to fall from blocks if you're bad.", Category.MOVEMENT);
      Settings.initialize(this);
      this.field_2156 = new Class_0242();
   }

   @Override
   public void onDisable() {
      if (this.field_2154.getValue()) {
         field_4219.options.sneakKey.setPressed(false);
      }
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if (this.field_2154.getValue()) {
         BlockPos var2 = BlockPos.ofFloored(field_4219.player.getPos()).down();
         BlockState var3 = field_4219.world.getBlockState(var2);
         if (!var3.isOf(Blocks.AIR) && !var3.getCollisionShape(field_4219.world, var2).isEmpty()) {
            field_4219.options.sneakKey.setPressed(!this.field_2156.method_9((long)this.field_2155.getValue().intValue()));
         } else {
            this.field_2156.reset();
            field_4219.options.sneakKey.setPressed(true);
         }
      }
   }

   @Subscribe
   public void method_2(Event_8 var1) {
      if (!field_4219.player.isSwimming() && !field_4219.player.isInLava() && !field_4219.player.isTouchingWater() && !this.field_2154.getValue()) {
         var1.method_463();
      }
   }
}
