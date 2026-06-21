package me.mioclient.module.player;

import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_0195;
import me.mioclient.event.Event_7;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Timer;
import me.mioclient.internal.RotationManager;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SugarCaneBlock;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Direction.Type;
import net.minecraft.world.BlockStateRaycastContext;
import nick.Settings;

public class AutoFarmModule extends Module {
   public Setting<Class_0195> field_2245;
   public Setting<Float> field_2246;
   public Setting<Integer> field_2247;
   public Setting<Boolean> field_2248;
   public Setting<Boolean> field_2249;
   public Setting<Boolean> field_2250;
   public Setting<Boolean> field_2251;
   public Setting<Float> field_2252;
   public Setting<Boolean> field_2253;
   public Setting<Boolean> field_2254;
   public Setting<Boolean> field_2255;
   public Setting<Boolean> field_2256;
   public Setting<Boolean> field_2257;
   public Setting<Boolean> field_2258;
   public Setting<Boolean> field_2259;
   public Setting<Boolean> field_2260;
   public final Timer field_2261;
   public final Timer field_2262;
   public int field_2263;

   public AutoFarmModule() {
      super("AutoFarm", "Farms your crops for you.", Category.PLAYER);
      Settings.initialize(this);
      this.field_2261 = new Timer();
      this.field_2262 = new Timer();
   }

   @Subscribe
   public void method_9(Event_7 var1) {
      if (this.field_2245 == null || this.field_2245.getValue() == null) return;
      for (BlockPos var3 : (this.field_2245.getValue() != null ? this.field_2245.getValue().method_2(this) : java.util.Collections.<BlockPos>emptyList())) {
         if (this.field_2263 >= this.field_2247.getValue()) {
            break;
         }

         this.method_39(var3, field_4219.world.raycast(new BlockStateRaycastContext(field_4219.player.getEyePos(), var3.toCenterPos(), var0 -> true)).getSide());
      }

      this.field_2263 = 0;
   }

   public void method_39(BlockPos var1, Direction var2) {
      BlockState var3 = field_4219.world.getBlockState(var1);
      boolean var4 = var3.isOf(Blocks.SUGAR_CANE);
      Hand var5 = this.method_38(var1x -> this.method_34(var1x.getItem()));
      if (this.field_2248.getValue() && var5 != null && this.method_2(var1, var3, field_4219.player.getStackInHand(var5).getItem())) {
         this.method_2(var1, var5);
      } else if (this.method_34(var3.getBlock().asItem())) {
         boolean var6 = this.method_2(var3, var1);
         Hand var7 = this.method_7(Items.BONE_MEAL);
         if (this.field_2250.getValue() && !var6 && var7 != null && !var4) {
            this.method_9(var1, var7);
         } else {
            this.method_9(var1, var2, var6);
         }
      }
   }

   public void method_9(BlockPos var1, Direction var2, boolean var3) {
      if (!(field_4219.world.getBlockState(var1).getBlock() instanceof SaplingBlock)) {
         if (this.field_2251.getValue() && this.field_2261.method_2((double)(this.field_2252.getValue() != null ? this.field_2252.getValue().floatValue() : 0.0f), TimeUnit.SECONDS)) {
            if (var3 || !this.field_2253.getValue()) {
               field_4219.interactionManager.updateBlockBreakingProgress(var1, var2);
               field_4219.player.swingHand(Hand.MAIN_HAND);
               this.field_2262.reset();
               this.field_2263++;
               this.field_2261.reset();
               if (this.field_2249.getValue()) {
                  Hub.field_2598.method_2(RotationManager.method_78(var1.toCenterPos()), 0);
               }
            }
         }
      }
   }

   public void method_2(BlockPos var1, Hand var2) {
      field_4219.interactionManager.interactBlock(field_4219.player, var2, new BlockHitResult(var1.toCenterPos(), Direction.UP, var1, false));
      field_4219.player.swingHand(var2);
      this.field_2262.reset();
      this.field_2263++;
      if (this.field_2249.getValue()) {
         Hub.field_2598.method_2(RotationManager.method_78(var1.toCenterPos()), 0);
      }
   }

   public void method_9(BlockPos var1, Hand var2) {
      if (this.field_2249.getValue()) {
         Hub.field_2598.method_2(RotationManager.method_78(Vec3d.ofBottomCenter(var1)), 0);
      }

      field_4219.interactionManager.interactBlock(field_4219.player, var2, new BlockHitResult(Vec3d.ofBottomCenter(var1), Direction.UP, var1, false));
      field_4219.player.swingHand(var2);
      this.field_2262.reset();
      this.field_2263++;
   }

   public boolean method_2(BlockPos var1, BlockState var2, Item var3) {
      if (var3 instanceof BlockItem var4) {
         if (var4.getBlock() instanceof CropBlock) {
            return var2.isOf(Blocks.FARMLAND) && field_4219.world.getBlockState(var1.up()).isOf(Blocks.AIR);
         }

         if (var4.getBlock() instanceof SugarCaneBlock) {
            if (!field_4219.world.getBlockState(var1.up()).isOf(Blocks.AIR)) {
               return false;
            }

            if (!var2.isIn(BlockTags.DIRT) && !var2.isIn(BlockTags.SAND)) {
               return false;
            }

            for (Direction var6 : Type.HORIZONTAL) {
               FluidState var7 = field_4219.world.getFluidState(var1.offset(var6));
               var2 = field_4219.world.getBlockState(var1.offset(var6));
               if (var7.isIn(FluidTags.WATER) || var2.isOf(Blocks.FROSTED_ICE)) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public boolean method_2(BlockState var1, BlockPos var2) {
      if (var1.getBlock() instanceof SaplingBlock) {
         return false;
      } else if (var1.getBlock() instanceof CropBlock var3) {
         return var3.getAge(var1) == var3.getMaxAge();
      } else {
         return var1.getBlock() instanceof SugarCaneBlock ? field_4219.world.getBlockState(var2.down()).isOf(Blocks.SUGAR_CANE) : false;
      }
   }

   public Hand method_7(Item var1) {
      return this.method_38(var1x -> var1x.isOf(var1));
   }

   public Hand method_38(Predicate<ItemStack> var1) {
      if (var1.test(field_4219.player.getMainHandStack())) {
         return Hand.MAIN_HAND;
      } else {
         return var1.test(field_4219.player.getOffHandStack()) ? Hand.OFF_HAND : null;
      }
   }

   public boolean method_34(Item var1) {
      if (var1 instanceof BlockItem var2 && var2.getBlock() instanceof SaplingBlock) {
         return this.field_2260.getValue();
      }

      return var1 == Items.WHEAT_SEEDS && this.field_2255.getValue()
         || var1 == Items.CARROT && this.field_2256.getValue()
         || var1 == Items.POTATO && this.field_2257.getValue()
         || var1 == Items.BEETROOT_SEEDS && this.field_2258.getValue()
         || var1 == Items.SUGAR_CANE && this.field_2259.getValue();
   }
}
