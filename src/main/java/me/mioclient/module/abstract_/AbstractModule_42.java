package me.mioclient.module.abstract_;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import me.mioclient.Hub;
import me.mioclient.api.Class_1153;
import me.mioclient.enum_.Class_0214;
import me.mioclient.internal.PlayerUtil;
import me.mioclient.internal.Class_0351;
import me.mioclient.internal.Class_0382;
import me.mioclient.internal.Class_0700;
import me.mioclient.mixin.ducks.DuckAbstractBlock;
import me.mioclient.module.Category;
import me.mioclient.record.Class_0681;
import me.mioclient.setting.Setting;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import nick.Settings;

public class AbstractModule_42 extends AbstractModule_32 {
   public Setting<Set<Block>> field_766;
   public Setting<Boolean> field_767;
   public Setting<Class_0214> field_768;
   public Setting<Float> field_769;
   public Setting<Float> field_770;
   public Setting<Float> field_771;
   public Setting<Boolean> field_772;
   public Setting<Boolean> field_773;
   public Setting<Boolean> field_774;
   public Setting<Boolean> field_775;
   public Setting<Boolean> field_776;
   public Setting<Boolean> field_777;
   public Setting<Integer> field_778;
   public final Class_1153 field_779;
   public final Class_1153 field_780;

   public AbstractModule_42() {
      super("HoleFill", "Blocks safe holes nearby with obsidian.", Category.COMBAT);
      Settings.initialize(this);
      this.field_779 = new Class_0351(this);
      this.field_780 = new Class_0700(this);
      this.unregister(this.field_4249);
      this.unregister(this.field_4256);
      this.field_4255.method_39(false);
      this.field_776.method_5(var1 -> {
         for (Block var3 : this.field_766.getValue()) {
            if (!((DuckAbstractBlock)var3).isCollidable()) {
               return true;
            }
         }

         return false;
      });
   }

   @Override
   public String getInfo() {
      return String.valueOf(this.method_17().size());
   }

   @Override
   public void method_33() {
      if ((!field_4219.player.isUsingItem() || this.field_775.getValue()) && (Hub.field_2605.method_224() || !this.field_772.getValue())) {
         super.method_33();
      }
   }

   @Override
   public List<BlockPos> method_17() {
      ArrayList var1 = new ArrayList();

      for (Class_0681 var3 : Hub.field_2605.method_223()) {
         if (this.field_768 == null || this.field_768.getValue() == null) continue;
         if (this.field_768.getValue() != null && this.field_768.getValue().method_2(this).method_2(var3)
            && (this.field_776.getValue() && this.field_776.method_176() || !var3.method_406().equals(Class_0382.method_425()))) {
            var1.add(var3.method_406());
         }
      }

      return var1;
   }

   @Override
   public int method_34() {
      return (this.field_766.getValue() != null ? this.field_766.getValue().isEmpty() : true)
         ? super.method_34()
         : PlayerUtil.method_7((Predicate<ItemStack>)(var1 -> (this.field_766.getValue() != null ? this.field_766.getValue().contains(this.method_263(var1)) : false)));
   }

   public Block method_263(ItemStack var1) {
      return var1.getItem() instanceof BlockItem var2 ? var2.getBlock() : Blocks.VOID_AIR;
   }
}
