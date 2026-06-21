package me.mioclient.module.player;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_1172;
import me.mioclient.enum_.NoneType;
import me.mioclient.event.Event_17;
import me.mioclient.event.Event_53;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0136;
import me.mioclient.internal.Class_0242;
import me.mioclient.internal.Class_0485;
import me.mioclient.internal.Class_1035;
import me.mioclient.internal.Class_1225;
import me.mioclient.internal.Class_1261;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.record.Class_0123;
import me.mioclient.setting.Setting;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.ingame.CraftingScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FireworksComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.network.packet.c2s.play.CloseHandledScreenC2SPacket;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import nick.Settings;

public class AutoCraftModule extends Module {
   public static final RecipeEntry<?> field_2467 = new RecipeEntry(Identifier.of("minecraft:firework_rocket_simple"), null);
   public static final List<Ingredient> field_2468 = List.of(
      Ingredient.ofItems(new ItemConvertible[]{Items.PAPER}), Ingredient.ofItems(new ItemConvertible[]{Items.GUNPOWDER})
   );
   public Setting<Set<Item>> field_2469;
   public Setting<Set<Item>> field_2470;
   public Setting<Integer> field_2471;
   public Setting<Integer> field_2472;
   public Setting<NoneType> field_2473;
   public Setting<Boolean> field_2474;
   public Setting<Boolean> field_2475;
   public Setting<Boolean> field_2476;
   public Setting<Float> field_2477;
   public Setting<Boolean> field_2478;
   public Setting<Boolean> field_2479;
   public Setting<Boolean> field_2480;
   public Setting<Boolean> field_2481;
   public Setting<Integer> field_2482;
   public Setting<Boolean> field_2483;
   public final Class_0242 field_2484;
   public final Class_0242 field_2485;

   public AutoCraftModule() {
      super("AutoCraft", "Automatically crafts listed items.", Category.PLAYER, "craftbot");
      Settings.initialize(this);
      this.field_2484 = new Class_0242();
      this.field_2485 = new Class_0242();
   }

   @Subscribe
   public void method_9(Event_17 var1) {
      RecipeEntry var2 = this.method_731();
      if (var2 != null) {
         if (this.field_2476.getValue() && this.field_2485.method_9(500L) && !(field_4219.currentScreen instanceof HandledScreen)) {
            Class_0123 var3 = this.method_732();
            if (var3 != null) {
               if (this.field_2480.getValue()) {
                  Class_1261.method_2(Class_0485.method_78(((BlockPos)var3.method_144()).toCenterPos()), field_4219.player.isOnGround());
               }

               Class_1261.method_2(
                  Hand.MAIN_HAND,
                  new BlockHitResult(((BlockPos)var3.method_144()).toCenterPos(), (Direction)var3.method_145(), (BlockPos)var3.method_144(), false)
               );
               Class_1261.method_9(Hand.MAIN_HAND);
               this.field_2485.reset();
            }
         }

         if (field_4219.player.currentScreenHandler instanceof CraftingScreenHandler
            && !this.field_2469.getValue().isEmpty()
            && this.field_2484.method_9((long)this.field_2471.getValue().intValue())) {
            CraftingScreenHandler var13 = (CraftingScreenHandler)field_4219.player.currentScreenHandler;
            ItemStack var4 = var2.value().getResult(field_4219.world.getRegistryManager());
            if (!var4.isOf(Items.FIREWORK_ROCKET)) {
               for (int var14 = 0; var14 < this.method_33(var4.getItem()); var14++) {
                  field_4219.interactionManager.clickRecipe(var13.syncId, var2, this.field_2474.getValue());
               }
            } else {
               int var5 = -1;
               int var6 = -1;

               for (int var7 = 10; var7 < 46; var7++) {
                  ItemStack var8 = var13.getSlot(var7).getStack();
                  if (var8.isOf(Items.GUNPOWDER) && var5 == -1) {
                     var5 = var7;
                  }

                  if (var8.isOf(Items.PAPER) && var6 == -1) {
                     var6 = var7;
                  }
               }

               if (var6 != -1 && var13.getSlot(1).getStack().isEmpty()) {
                  this.field_2484.reset();
                  Class_0136.method_5(var6, 1);
                  return;
               }

               int[] var15 = new int[]{2, 4, 5};
               boolean var16 = true;

               for (int var12 : var15) {
                  if (!var13.getSlot(var12).getStack().isOf(Items.GUNPOWDER)) {
                     var16 = false;
                  }
               }

               if (var5 != -1 && !var16) {
                  field_4219.interactionManager.clickSlot(var13.syncId, var5, 0, SlotActionType.PICKUP, field_4219.player);

                  for (int var22 : var15) {
                     if (!var13.getSlot(var22).getStack().isOf(Items.GUNPOWDER)) {
                        if (!var13.getCursorStack().isEmpty()) {
                           field_4219.interactionManager.clickSlot(var13.syncId, var22, 1, SlotActionType.PICKUP, field_4219.player);
                        }
                        break;
                     }
                  }

                  field_4219.interactionManager.clickSlot(var13.syncId, var5, 0, SlotActionType.PICKUP, field_4219.player);
                  this.field_2484.reset();
                  return;
               }

               ItemStack var17 = var13.getSlot(0).getStack();
               FireworksComponent var19 = (FireworksComponent)var17.getOrDefault(
                  DataComponentTypes.FIREWORKS, new FireworksComponent(0, Collections.emptyList())
               );
               if (!var17.isOf(Items.FIREWORK_ROCKET)
                  || var19.flightDuration() != 3
                  || !var16
                  || !this.field_2484.method_9((long)Math.max(200, this.field_2471.getValue()))) {
                  return;
               }
            }

            field_4219.interactionManager.clickSlot(var13.syncId, 0, 1, this.field_2473.getValue().method_1103(), field_4219.player);
            if (this.field_2473.getValue() == NoneType.PICKUP && var4.getCount() + var13.getCursorStack().getCount() > var13.getCursorStack().getMaxCount()) {
               field_4219.interactionManager.clickSlot(var13.syncId, -999, 0, SlotActionType.PICKUP, field_4219.player);
            }

            if (this.field_2475.getValue()) {
               Hub.field_2619.method_2(() -> {
                  int var0 = field_4219.player.currentScreenHandler.syncId;
                  Class_1261.method_2(new CloseHandledScreenC2SPacket(var0));
                  field_4219.player.currentScreenHandler = field_4219.player.playerScreenHandler;
               }, 1);
            }

            this.field_2484.reset();
         }
      }
   }

   @Subscribe
   public void method_2(Event_53 var1) {
      if (var1.method_562() instanceof CraftingScreen && this.field_2475.getValue()) {
         var1.method_562().init(field_4219, 0, 0);
         var1.method_463();
      }
   }

   public RecipeEntry<?> method_731() {
      if (this.field_2469.getValue().isEmpty()) {
         return null;
      } else {
         for (RecipeEntry var2 : field_4219.world.getRecipeManager().values()) {
            if (var2.value().getType() == RecipeType.CRAFTING) {
               ItemStack var3 = var2.value().getResult(field_4219.world.getRegistryManager());
               if (var3 != null && var3.getItem() != null && !(var3.getItem() instanceof SmithingTemplateItem)) {
                  Object var4 = var2.value().getIngredients();
                  if (var3.isOf(Items.FIREWORK_ROCKET)) {
                     var4 = field_2468;
                  }

                  if (this.field_2469.getValue().contains(var3.getItem()) && this.method_9((Collection<Ingredient>)var4) && this.method_37(var3.getItem())) {
                     return var2;
                  }
               }
            }
         }

         return null;
      }
   }

   public boolean method_37(Item var1) {
      if (this.field_2481.getValue() && !this.field_2474.getValue()) {
         int var2 = 0;
         int var3 = this.field_2482.getValue();
         if (this.field_2483.getValue()) {
            var3 *= var1.getMaxCount();
         }

         for (ItemStack var5 : field_4219.player.getInventory().main) {
            if (var5.isOf(var1)) {
               var2 += var5.getCount();
            }
         }

         return var2 < var3;
      } else {
         return true;
      }
   }

   public int method_33(Item var1) {
      if (!this.field_2481.getValue()) {
         return this.field_2472.getValue();
      } else {
         int var2 = 0;
         int var3 = this.field_2482.getValue();
         if (this.field_2483.getValue()) {
            var3 *= var1.getMaxCount();
         }

         for (ItemStack var5 : field_4219.player.getInventory().main) {
            if (var5.isOf(var1)) {
               var2 += var5.getCount();
            }
         }

         return MathHelper.clamp(var3 - var2, 0, this.field_2472.getValue());
      }
   }

   public boolean method_9(Collection<Ingredient> var1) {
      HashMap<Item, Integer> var2 = new HashMap<>();

      for (Slot var4 : field_4219.player.currentScreenHandler.slots) {
         var2.compute(
            var4.getStack().getItem(), (var1x, var2x) -> var1x != null && var2x != null ? var4.getStack().getCount() + var2x : var4.getStack().getCount()
         );
      }

      for (Ingredient var12 : var1) {
         if (!var12.isEmpty()) {
            boolean var5 = false;

            for (ItemStack var9 : var12.getMatchingStacks()) {
               if (this.field_2470.getValue().contains(var9.getItem())) {
                  return false;
               }

               if (var2.containsKey(var9.getItem())) {
                  Integer var10 = (Integer)var2.get(var9.getItem());
                  if (var10 - var9.getCount() >= 0) {
                     var2.replace(var9.getItem(), var10 - var9.getCount());
                     var5 = true;
                     break;
                  }
               }
            }

            if (!var5) {
               return false;
            }
         }
      }

      return true;
   }

   public Class_0123<BlockPos, Direction> method_732() {
      for (BlockPos var2 : Class_1225.method_2(field_4219.player.getEyePos(), this.field_2477.getValue(), true)) {
         if (field_4219.world.getBlockState(var2).isOf(Blocks.CRAFTING_TABLE)
            && (Class_1225.method_2(Class_1172.field_3634.method_38(var2)) || !this.field_2478.getValue())) {
            List var3 = Class_1035.method_39(var2);
            if (!var3.isEmpty() || !this.field_2479.getValue()) {
               return new Class_0123<>(var2, var3.isEmpty() ? Direction.UP : (Direction)var3.get(0));
            }
         }
      }

      return null;
   }
}
