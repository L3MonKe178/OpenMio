package me.mioclient.module.abstract_;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import me.mioclient.Hub;
import me.mioclient.event.Event_4;
import me.mioclient.event.Event_41;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0177;
import me.mioclient.internal.Class_0539;
import me.mioclient.internal.Class_1303;
import me.mioclient.mixin.ducks.DuckHandledScreen;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.record.Class_0089;
import me.mioclient.record.Class_0576;
import me.mioclient.record.Class_1356;
import me.mioclient.setting.Setting;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.block.entity.BeehiveBlockEntity.BeeData;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.component.type.MapIdComponent;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.map.MapState;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.packet.s2c.play.InventoryS2CPacket;
import net.minecraft.network.packet.s2c.play.ScreenHandlerSlotUpdateS2CPacket;
import net.minecraft.screen.ShulkerBoxScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.MutableText;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import nick.Settings;
import org.lwjgl.glfw.GLFW;

public class AbstractModule_37 extends Module {
   public Setting<Boolean> field_1184;
   public Setting<Boolean> field_1185;
   public Setting<Boolean> field_1186;
   public Setting<Boolean> field_1187;
   public Setting<Boolean> field_1188;
   public Setting<Boolean> field_1189;
   public Setting<Boolean> field_1190;
   public Color field_1191;
   public final List<Class_0576> field_1192;

   public AbstractModule_37() {
      super(
         "Tooltips",
         new Class_1303()
            .method_2(String.valueOf(Formatting.YELLOW))
            .method_2(String.valueOf(Formatting.YELLOW))
            .method_9(
               "Draws advanced tool tips on items.\n\u0001Middle Click a shulker to open the preview screen.\n\u0001Hold Left Alt when hovering over a shulker to inspect it's items."
            ),
         Category.RENDER
      );
      Settings.initialize(this);
      this.field_1192 = Collections.synchronizedList(new ArrayList<>());
      this.setDrawn(false);
   }

   @Subscribe
   public void method_5(Event_4 var1) {
      if (var1.method_127() instanceof InventoryS2CPacket var2 && this.field_1185.getValue()) {
         field_4219.executeSync(() -> {
            this.field_1192.clear();

            for (ItemStack var3 : var2.getContents()) {
               Class_0576 var4 = Class_0576.method_460(var3);
               if (var4 != null) {
                  this.field_1192.add(var4);
               }
            }
         });
      }

      if (var1.method_127() instanceof ScreenHandlerSlotUpdateS2CPacket && this.field_1185.getValue()) {
         Hub.field_2619.method_2(() -> {
            if (field_4219.player.currentScreenHandler != null) {
               this.field_1192.clear();

               for (Slot var2x : field_4219.player.currentScreenHandler.slots) {
                  Class_0576 var3 = Class_0576.method_460(var2x.getStack());
                  if (var3 != null) {
                     this.field_1192.add(var3);
                  }
               }
            }
         }, 0);
      }
   }

   @Subscribe
   public void method_2(Event_41 var1) {
      if (var1.method_562() instanceof HandledScreen) {
         Slot var2 = ((DuckHandledScreen)var1.method_562()).mio$getFocusedSlot();
         if (var2 != null) {
            ItemStack var3 = var2.getStack();
            DefaultedList<ItemStack> var4 = DefaultedList.ofSize(27, ItemStack.EMPTY);
            if (var3.getItem() instanceof BlockItem var5 && var5.getBlock() instanceof ShulkerBoxBlock var6 && var6.getColor() != null) {
               Color var17 = new Color(var6.getColor().getMapColor().color, false);
               float[] var8 = new float[3];
               var17.getColorComponents(var8);
               this.method_16(new Color(var8[0], var8[1], var8[2], Float.intBitsToFloat(1056964608)));
            }

            ContainerComponent var12 = (ContainerComponent)var3.get(DataComponentTypes.CONTAINER);
            if (var12 != null) {
               List var13 = var12.stream().toList();

               for (int var18 = 0; var18 < var13.size() && var18 < 27; var18++) {
                  ItemStack var22 = (ItemStack)var13.get(var18);
                  if (var22.getCount() == 0) {
                     var22.setCount(69);
                  }

                  var4.set(var18, var22);
               }
            }

            this.method_2(var3, var4);
            if (!var4.stream().allMatch(ItemStack::isEmpty) && this.field_1187.getValue()) {
               if (GLFW.glfwGetMouseButton(field_4219.getWindow().getHandle(), 2) == 1 && !(var1.method_562() instanceof Class_0539)) {
                  field_4219.setScreen(
                     new Class_0539(
                        new ShulkerBoxScreenHandler(1337, field_4219.player.getInventory(), new SimpleInventory(var4.toArray(new ItemStack[27]))),
                        field_4219.player.getInventory(),
                        var3.getName(),
                        var1.method_562()
                     )
                  );
               }

               if (this.field_1186.getValue()) {
                  var1.method_563().add(new Class_0089(var4));
               } else {
                  var1.method_563().clear();
                  var1.method_563().addAll(List.of(TooltipComponent.of(var3.getName().asOrderedText()), new Class_0089(var4)));
               }
            }

            if (var3.getItem() == Items.FILLED_MAP) {
               MapIdComponent var14 = (MapIdComponent)var3.get(DataComponentTypes.MAP_ID);
               int var19 = var14 == null ? -1 : var14.id();
               MapState var23 = Hub.field_2624.method_9(var3, var19);
               if (var23 != null) {
                  if (!this.field_1186.getValue()) {
                     var1.method_563().clear();
                     var1.method_563().addAll(List.of(TooltipComponent.of(var3.getName().asOrderedText()), new Class_1356(var19, var23)));
                  } else {
                     var1.method_563().add(new Class_1356(var19, var23));
                  }

                  if (this.field_1189.getValue()) {
                     MutableText var9 = Text.literal("Hold Left-Alt to hide item amount.");
                     var9 = var9.styled(var0 -> var0.withFormatting(Formatting.YELLOW));
                     var1.method_563().add(TooltipComponent.of(var9.asOrderedText()));
                  }
               }
            }

            List var15 = (List)var3.get(DataComponentTypes.BEES);
            if (var15 != null && this.field_1188.getValue()) {
               for (BeeData var24 : (Iterable<BeeData>)(Iterable<?>)(var15)) {
                  NbtCompound var27 = var24.entityData().copyNbt();
                  if (var27 != null && var27.contains("FlowerPos")) {
                     BlockPos var10 = (BlockPos)NbtHelper.toBlockPos(var27, "FlowerPos").orElse(null);
                     if (var10 != null) {
                        var1.method_563()
                           .add(TooltipComponent.of(Text.literal(new Class_1303().method_2(var10.toShortString()).method_9("Position \u0001")).asOrderedText()));
                     }
                  }
               }
            }

            if (this.field_1190.getValue()) {
               try {
                  Class_0177 var21 = new Class_0177();
                  var3.encode(field_4219.player.getRegistryManager()).write(var21);
                  int var25 = var21.method_212();
                  OrderedText var28 = Text.literal(this.method_78((long)var25)).asOrderedText();
                  var1.method_563().add(TooltipComponent.of(var28));
               } catch (Throwable var11) {
               }
            }
         }
      }
   }

   public void method_2(ItemStack var1, List<ItemStack> var2) {
      NbtComponent var3 = (NbtComponent)var1.get(DataComponentTypes.CUSTOM_DATA);
      if (var3 != null && var3.contains("BlockEntityTag")) {
         NbtCompound var4 = var3.getNbt().getCompound("BlockEntityTag");
         if (var4.contains("Items")) {
            NbtList var5 = var4.getList("Items", 10);

            for (int var6 = 0; var6 < var5.size(); var6++) {
               NbtCompound var7 = var5.getCompound(var6);
               ItemStack var8 = (ItemStack)ItemStack.fromNbt(field_4219.player.getRegistryManager(), var7).orElse(null);
               byte var9 = -1;
               if (var7.contains("Count", 1)) {
                  var9 = var7.getByte("Count");
               }

               if (var8 != null && var9 == 0) {
                  var8.setCount(69);
                  var2.set(var6, var8);
               }
            }
         }
      }
   }

   public String method_78(long var1) {
      long var3 = var1 / 1024L;
      long var5 = var3 / 1024L;
      if (var5 > 0L) {
         return String.format("%,d MB", var5);
      } else {
         return var3 > 0L ? String.format("%,d KB", var3) : String.format("%,d B", var1);
      }
   }

   public Color method_408() {
      return !this.field_1184.getValue() ? null : this.field_1191;
   }

   public void method_16(Color var1) {
      this.field_1191 = var1;
   }
}
