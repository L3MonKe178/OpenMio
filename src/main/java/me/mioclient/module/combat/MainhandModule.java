package me.mioclient.module.combat;

import java.util.function.Predicate;
import me.mioclient.Hub;
import me.mioclient.event.Event_11;
import me.mioclient.event.Event_36;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0018;
import me.mioclient.internal.PlayerUtil;
import me.mioclient.internal.Timer;
import me.mioclient.internal.Class_0396;
import me.mioclient.internal.Class_0509;
import me.mioclient.internal.Class_1225;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.player.NoInteractModule;
import me.mioclient.setting.Setting;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import nick.Settings;

public class MainhandModule extends Module {
   public static final NoInteractModule field_3096 = Hub.field_2595.method_78(NoInteractModule.class);
   public Setting<Integer> field_3097;
   public Setting<Integer> field_3098;
   public Setting<Boolean> field_3099;
   public Setting<Integer> field_3100;
   public Setting<Boolean> field_3101;
   public Setting<Float> field_3102;
   public Setting<Boolean> field_3103;
   public Setting<Float> field_3104;
   public final Timer field_3105;
   public final Timer field_3106;
   public final Timer field_3107;
   public boolean field_1982;
   public int field_1071;
   public final Class_0509 field_3108;

   public MainhandModule() {
      super("Mainhand", "Auto totem for main hand.", Category.COMBAT);
      Settings.initialize(this);
      this.field_3105 = new Timer();
      this.field_3106 = new Timer();
      this.field_3107 = new Timer();
      this.field_3108 = new Class_0509() {
         @Override
         public boolean method_206() {
            return MainhandModule.this.field_3101.getValue() && MainhandModule.this.field_3099.getValue();
         }
      };
      this.field_3100.method_31("SwapDelay");
   }

   @Override
   public void onEnable() {
      this.field_1071 = -1;
   }

   @Override
   public String getInfo() {
      int var1 = field_4219.player.getInventory().main.stream().filter(var1x -> var1x.getItem() == this.method_903()).mapToInt(ItemStack::getCount).sum();
      return String.valueOf(var1);
   }

   @Subscribe
   public void method_2(Event_36 var1) {
      boolean var2 = Class_1225.method_2(var1.method_382()) && !field_4219.player.isSneaking() && !field_3096.isToggled();
      if (var2 && !var1.method_464()) {
         this.field_3107.reset();
      }
   }

   @Subscribe(
      method_800 = 100
   )
   public void method_9(Event_11 var1) {
      if (!this.method_535()) {
         this.field_1982 = var1.method_93();
         this.field_3108.method_142();
         if (field_4219.player.currentScreenHandler == field_4219.player.playerScreenHandler) {
            this.method_902();
            Item var2 = this.method_903();
            if (var2 != field_4219.player.getInventory().getStack(this.method_905()).getItem()) {
               if (field_4219.player.currentScreenHandler.getCursorStack().isEmpty()) {
                  this.method_36(var2);
               }
            }
         }
      }
   }

   public void method_36(Item var1) {
      int var2 = PlayerUtil.method_2((Predicate<ItemStack>)(var1x -> var1x.isOf(var1)), true);
      if (this.field_1071 != -1 && field_4219.player.getInventory().getStack(this.field_1071).isOf(var1) && var2 != -1) {
         var2 = this.field_1071;
      }

      if (var2 != -1) {
         if (var2 < field_4219.player.currentScreenHandler.slots.size()) {
            ItemStack var3 = field_4219.player.currentScreenHandler.getSlot(var2).getStack();
            if (!var3.contains(DataComponentTypes.FOOD) || this.field_1982) {
               if (this.field_3105.method_5((long)(this.field_3098.getValue() != null ? this.field_3098.getValue().intValue() : 0)) && this.field_3107.method_9(200L)) {
                  PlayerUtil.method_9(var2, this.method_905());
                  this.field_1071 = var2;
               }

               if (Class_0018.method_2(field_4219.options.useKey) && var3.contains(DataComponentTypes.FOOD)) {
                  field_4219.interactionManager.interactItem(field_4219.player, Hand.OFF_HAND);
               }
            }
         }
      }
   }

   public void method_902() {
      if (this.field_3099.getValue()) {
         Item var1 = field_4219.player.getInventory().getStack(this.method_905()).getItem();
         if (var1 == Items.TOTEM_OF_UNDYING && (!field_4219.player.isUsingItem() || field_4219.player.getActiveHand() != Hand.MAIN_HAND)) {
            if ((this.field_3108.method_538() || Class_0396.method_76() <= this.field_3102.getValue())
               && this.field_3106.method_5((long)(this.field_3100.getValue() != null ? this.field_3100.getValue().intValue() : 0))) {
               PlayerUtil.method_16(this.method_905());
            }
         }
      }
   }

   public Item method_903() {
      boolean var1 = Class_1225.method_2(field_4219.crosshairTarget)
         && !field_4219.player.isSneaking()
         && !field_3096.isToggled()
         && (
            !field_4219.player.isUsingItem()
               || field_4219.player.getActiveHand() != Hand.MAIN_HAND
               || !field_4219.player.getMainHandStack().contains(DataComponentTypes.FOOD)
         );
      int var2 = field_4219.player.getInventory().selectedSlot;
      return var2 == this.method_905()
            && this.field_3103.getValue()
            && field_4219.options.useKey.isPressed()
            && this.method_904()
            && !var1
            && Class_0396.method_76() >= this.field_3104.getValue()
         ? Items.ENCHANTED_GOLDEN_APPLE
         : Items.TOTEM_OF_UNDYING;
   }

   public boolean method_904() {
      ItemStack var1 = field_4219.player.getMainHandStack();
      return field_4219.player.getOffHandStack().contains(DataComponentTypes.FOOD)
         ? false
         : var1.isOf(Items.TOTEM_OF_UNDYING) || var1.isOf(Items.ENCHANTED_GOLDEN_APPLE);
   }

   public int method_905() {
      return this.field_3097.getValue() - 1;
   }
}
