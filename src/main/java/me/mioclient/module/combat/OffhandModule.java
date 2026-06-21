package me.mioclient.module.combat;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import me.mioclient.Hub;
import me.mioclient.enum_.Priority;
import me.mioclient.enum_.SettingMode;
import me.mioclient.enum_.TotemType;
import me.mioclient.event.Event_11;
import me.mioclient.event.Event_36;
import me.mioclient.event.Event_4;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0018;
import me.mioclient.internal.PlayerUtil;
import me.mioclient.internal.Class_0199;
import me.mioclient.internal.Timer;
import me.mioclient.internal.Class_0396;
import me.mioclient.internal.Class_0509;
import me.mioclient.internal.Class_1225;
import me.mioclient.internal.ChatUtil;
import me.mioclient.internal.PacketUtil;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.abstract_.AbstractModule_28;
import me.mioclient.module.player.NoInteractModule;
import me.mioclient.setting.Setting;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.screen.ingame.ShulkerBoxScreen;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.SwordItem;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket.Mode;
import net.minecraft.network.packet.s2c.play.DeathMessageS2CPacket;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.HoverEvent.Action;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import nick.Settings;

public class OffhandModule extends Module {
   public static final AutoCrystalModule field_1959 = Hub.field_2595.method_78(AutoCrystalModule.class);
   public static final OffhandModule field_1960 = Hub.field_2595.method_78(OffhandModule.class);
   public static final NoInteractModule field_1961 = Hub.field_2595.method_78(NoInteractModule.class);
   public static AbstractModule_28 field_144 = Hub.field_2595.method_78(AbstractModule_28.class);
   public Setting<TotemType> field_1962;
   public Setting<Integer> field_1963;
   public Setting<Float> field_1964;
   public Setting<Boolean> field_1965;
   public Setting<Boolean> field_1966;
   public Setting<Boolean> field_1967;
   public Setting<Boolean> field_1968;
   public Setting<Float> field_1969;
   public Setting<Boolean> field_1970;
   public Setting<Boolean> field_1971;
   public Setting<Boolean> field_1972;
   public Setting<Boolean> field_1973;
   public Setting<Set<Item>> field_1974;
   public final Timer field_1975;
   public final Timer field_1976;
   public final Timer field_1977;
   public final Timer field_1978;
   public final List<Long> field_1979;
   public int field_1980;
   public boolean field_1981;
   public boolean field_1982;
   public boolean field_478;
   public final Class_0509 field_1983;
   public static boolean field_1984;

   public OffhandModule() {
      super("Offhand", "Manages your offhand automatically.", Category.COMBAT, "autototem");
      Settings.initialize(this);
      this.field_1975 = new Timer();
      this.field_1976 = new Timer();
      this.field_1977 = new Timer();
      this.field_1978 = new Timer();
      this.field_1979 = new ArrayList<>();
      this.field_1980 = -1;
      this.field_1983 = new Class_0509() {
         @Override
         public boolean method_206() {
            return field_4219.player.getMainHandStack().isOf(Items.TOTEM_OF_UNDYING)
               ? false
               : OffhandModule.this.field_1965.getValue() || OffhandModule.this.field_1964.method_105();
         }
      };
      this.field_1964.method_2("Adaptive", SettingMode.MIN);
      this.field_1965.method_5(var1 -> !this.field_1964.method_105());
      this.field_1968.method_31("GappleBind");
      this.field_1969.method_31("SafeHealth");
      this.method_7(this.field_1974);
   }

   @Override
   public String getInfo() {
      return (this.field_1962.getValue() != null ? this.field_1962.getValue().toString() : "");
   }

   @Override
   public void onToggle() {
      this.field_1980 = -1;
   }

   @Subscribe(
      method_800 = 100
   )
   public void method_9(Event_11 var1) {
      this.field_1982 = var1.method_93();
      this.method_633();
   }

   public void method_633() {
      if (!this.method_535()) {
         if (field_4219.player.isAlive()) {
            this.field_478 = false;
         }

         field_1984 = false;
         this.field_1983.method_142();
         if (!this.method_638()) {
            if (this.method_569() && this.field_1975.method_9((long)(this.field_1963.getValue() != null ? this.field_1963.getValue().intValue() : 0))) {
               this.field_1979.removeIf(var0 -> var0 == null || System.currentTimeMillis() > var0 + 1000L);
               byte var1 = 4;
               if (this.field_1979.size() > var1) {
                  this.field_1976.reset();
               }

               this.method_300();
            }
         }
      }
   }

   @Subscribe
   public void method_5(Event_4 var1) {
      if (var1.method_127() instanceof EntityStatusS2CPacket var2
         && !this.method_638()
         && this.method_569()
         && var2.getEntity(field_4219.world) == field_4219.player
         && var2.getStatus() == 35) {
         if (!field_4219.player.getMainHandStack().isOf(Items.TOTEM_OF_UNDYING) && field_4219.player.getOffHandStack().isOf(Items.TOTEM_OF_UNDYING)) {
            field_4219.player.getOffHandStack().decrement(1);
         }

         this.field_1978.reset();
         this.field_1979.clear();
         this.method_300();
      }

      if (var1.method_127() instanceof EntitySpawnS2CPacket var4 && this.field_1983.method_206() && var4.getEntityType() == EntityType.END_CRYSTAL) {
         Vec3d var6 = new Vec3d(var4.getX(), var4.getY(), var4.getZ());
         if (this.field_1983.method_29(var6)) {
            this.field_1977.reset();
         }
      }

      if (var1.method_127() instanceof DeathMessageS2CPacket) {
         if (!field_4219.player.getOffHandStack().isOf(Items.TOTEM_OF_UNDYING)) {
            return;
         }

         this.method_637();
      }
   }

   @Subscribe
   public void method_2(Event_36 var1) {
      if (var1.method_12() == Hand.MAIN_HAND && this.method_2((TotemType)null) != null && !field_144.method_1052()) {
         field_4219.interactionManager.interactItem(field_4219.player, Hand.OFF_HAND);
         var1.method_463();
      }
   }

   public TotemType method_634() {
      if (field_1959.isToggled() && field_1959.field_4106.getValue() && this.field_1962.getValue() != TotemType.TOTEM) {
         return TotemType.CRYSTAL;
      } else {
         boolean var1 = this.field_1973.getValue();
         TotemType var2 = TotemType.TOTEM;
         if (this.field_1983.method_538()) {
            return TotemType.TOTEM;
         } else {
            float var3 = Class_0396.method_76();
            if (var3 > this.field_1964.getValue()
               && field_4219.player.fallDistance < Float.intBitsToFloat(1098907648)
               && PlayerUtil.method_29((Predicate<ItemStack>)(var2x -> var2x.isOf((this.field_1962.getValue() != null ? this.field_1962.getValue().method_98(var1) : null)))) > 0) {
               var2 = this.field_1962.getValue();
            }

            return this.method_2(var2);
         }
      }
   }

   public boolean method_14(Item var1) {
      if (var1 instanceof SwordItem || var1 instanceof AxeItem) {
         return true;
      } else if (var1 instanceof PickaxeItem && this.field_1970.getValue()) {
         return true;
      } else {
         return var1 == Items.TOTEM_OF_UNDYING && this.field_1972.getValue() ? true : var1 == Items.END_CRYSTAL && this.field_1971.getValue();
      }
   }

   public TotemType method_2(TotemType var1) {
      if (var1 != null && var1.method_98(this.field_1973.getValue()) instanceof ShieldItem) {
         return var1;
      } else if (Class_1225.method_2(field_4219.crosshairTarget) && !field_4219.player.isSneaking() && !field_1961.isToggled()) {
         return var1;
      } else {
         float var2 = Class_0396.method_76();
         Item var3 = field_4219.player.getMainHandStack().getItem();
         boolean var4 = this.field_1969.getValue() < var2;
         if (var3 == Items.TOTEM_OF_UNDYING) {
            var4 = true;
         }

         return this.field_1968.getValue()
               && field_4219.options.useKey.isPressed()
               && var4
               && this.method_14(var3)
               && field_4219.currentScreen == null
               && this.field_1962.getValue() != TotemType.GAPPLE
            ? TotemType.GAPPLE
            : var1;
      }
   }

   public void method_635() {
      this.field_1979.add(System.currentTimeMillis());
      Hub.field_2611.method_154(true);
      if (Hub.field_2602.method_992()) {
         PacketUtil.method_2(field_4219.player, Mode.STOP_SPRINTING, 0);
         this.field_1981 = true;
      }
   }

   public void method_636() {
      Hub.field_2611.method_154(false);
      if (!(field_4219.currentScreen instanceof HandledScreen)) {
         Hub.field_2611.close();
      }

      if (this.field_1981) {
         PacketUtil.method_2(field_4219.player, Mode.START_SPRINTING, 0);
         this.field_1981 = false;
      }
   }

   public void method_300() {
      TotemType var1 = this.method_634();
      int var2 = var1.method_186(this.field_1973.getValue());
      if (this.field_1980 != -1
         && (!(field_4219.currentScreen instanceof HandledScreen) || field_4219.currentScreen instanceof InventoryScreen)
         && field_4219.player
            .getInventory()
            .getStack(this.field_1980 >= 36 ? this.field_1980 - 36 : this.field_1980)
            .isOf(var1.method_98(this.field_1973.getValue()))
         && var2 != -1) {
         var2 = this.field_1980;
      }

      if (var2 != -1) {
         if (this.field_1967.getValue()
            || !(field_4219.currentScreen instanceof ShulkerBoxScreen) && !(field_4219.currentScreen instanceof GenericContainerScreen)) {
            if (var2 < field_4219.player.currentScreenHandler.slots.size()) {
               ItemStack var3 = field_4219.player.currentScreenHandler.getCursorStack();
               ItemStack var4 = field_4219.player.currentScreenHandler.getSlot(var2).getStack();
               if (!var4.contains(DataComponentTypes.FOOD) || this.field_1982) {
                  this.method_635();
                  int var5 = field_4219.player.currentScreenHandler.syncId;
                  if (!var3.isEmpty() && !PlayerUtil.method_161()) {
                     field_4219.interactionManager.clickSlot(var5, PlayerUtil.method_9(Items.AIR), 0, SlotActionType.PICKUP, field_4219.player);
                  }

                  if (this.field_1966.getValue() && field_4219.player.currentScreenHandler.getCursorStack().isEmpty()) {
                     this.field_1980 = var2;
                     field_4219.interactionManager.clickSlot(var5, var2, 40, SlotActionType.SWAP, field_4219.player);
                     this.field_1975.reset();
                  } else {
                     this.method_78(var2);
                     this.field_1980 = var2;
                  }

                  field_1984 = true;
                  this.method_636();
                  if (Class_0018.method_2(field_4219.options.useKey) && var4.contains(DataComponentTypes.FOOD)) {
                     field_4219.interactionManager.interactItem(field_4219.player, Hand.OFF_HAND);
                  }
               }
            }
         }
      }
   }

   public void method_78(int var1) {
      if (this.field_1975.method_9((long)(this.field_1963.getValue() != null ? this.field_1963.getValue().intValue() : 0))) {
         if (field_4219.player.currentScreenHandler.slots.size() > 45) {
            PlayerUtil.method_5(var1, 45);
            this.field_1975.reset();
         }
      }
   }

   public void method_7(Setting<Set<Item>> var1) {
      var1.method_9(() -> {
         Class_0199 var1x = (Class_0199)var1;
         if (var1x.method_98() != null) {
            ((Set)var1.getValue()).clear();
            ((Set)var1.getValue()).add((Item)var1x.method_98());
         }
      });
   }

   public void method_637() {
      long var1 = this.field_1978.method_271();
      int var3 = Hub.field_2602.method_983();
      if (!this.field_478) {
         MutableText var4 = Text.literal("Since last pop: %dms. Latency: %dms.".formatted(var1, var3))
            .append(
               Text.literal(" Possible death reasons: Damage override/Lag spike.*")
                  .styled(var0 -> var0.withHoverEvent(new HoverEvent(Action.SHOW_TEXT, Text.literal("More info in the game log."))))
            );
         if ((long)var3 > var1) {
            ChatUtil.method_2(var4, ChatUtil.method_38(Math.abs(this.getName().hashCode()) * -1 - 1), Priority.HIGH);
            System.out
               .println(
                  "*Death was most likely caused by you taking damage before the server receives the update slot packet, making you die holding a totem client-side."
               );
            this.field_478 = true;
         }
      }
   }

   public boolean method_638() {
      return field_1959.isToggled() && field_1959.field_4106.getValue() && this.field_1962.getValue() == TotemType.TOTEM;
   }

   public boolean method_569() {
      return this.field_1966.getValue()
         ? true
         : !(field_4219.currentScreen instanceof CreativeInventoryScreen)
            && field_4219.player.playerScreenHandler == field_4219.player.currentScreenHandler
            && !(field_4219.currentScreen instanceof GenericContainerScreen);
   }

   public static boolean method_639() {
      return field_1984 && field_1960.isToggled();
   }
}
