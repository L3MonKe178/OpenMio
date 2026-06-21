package me.mioclient.internal;

import com.mojang.blaze3d.systems.RenderSystem;
import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import java.awt.Color;
import java.util.Locale;
import java.util.regex.Pattern;
import me.mioclient.Hub;
import me.mioclient.api.Class_0833;
import me.mioclient.mixin.ducks.DuckHandledScreen;
import me.mioclient.module.misc.ChestSearchBarModule;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.render.DiffuseLighting;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.MutableText;
import net.minecraft.util.Formatting;

public class Class_0982 {
   public static final Class_0982 field_3024 = new Class_0982();
   public static ChestSearchBarModule field_3025 = Hub.field_2595.method_78(ChestSearchBarModule.class);
   public static TextFieldWidget field_3026;
   public static String text = "";
   public static int field_3027 = 0;

   public Class_0982() {
      super();
   }

   public void init() {
      field_3026 = null;
      text = "";
      ScreenEvents.AFTER_INIT.register(new Class_0665());
   }

   public void onDrawGui(DrawContext var1, Screen var2, MatrixStack var3, float var4, float var5, float var6) {
      if (field_3025.isToggled() && field_3026 != null) {
         var3.push();
         var3.translate(0.0F, 0.0F, Float.intBitsToFloat(1140457472));
         DuckHandledScreen var7 = (DuckHandledScreen)var2;
         if (!field_3026.getText().isBlank() && var2 instanceof HandledScreen var8) {
            ScreenHandler var9 = var8.getScreenHandler();
            int var10 = var7.getX();
            int var11 = var7.getY();
            field_3027 = 0;

            for (Slot var13 : var9.slots) {
               int var14 = var10 + var13.x;
               int var15 = var11 + var13.y;
               ItemStack var16 = var13.getStack();
               if (!namesMatch(var16, field_3026.getText())) {
                  RenderSystem.disableDepthTest();
                  RenderUtil.field_2672.method_2(var3, (float)var14, (float)var15, (float)(var14 + 16), (float)(var15 + 16), -1442840576);
               } else {
                  field_3027++;
               }

               if (isFull(var16, field_3026.getText())) {
                  var1.fill(var14, var15, var14 + 16, var15 + 16, -500, Class_1081.method_5(Color.green, 100));
               }
            }
         }

         if (field_3027 == 0 && !field_3026.getText().isEmpty()) {
            field_3026.setEditableColor(16733525);
         } else {
            field_3026.setEditableColor(16777215);
         }

         DiffuseLighting.enableGuiDepthLighting();
         var3.pop();
      }
   }

   public static boolean namesMatch(ItemStack var0, String var1) {
      var1 = Formatting.strip(var1.trim().toLowerCase(Locale.ROOT));
      if (var1 == null || var1.isEmpty()) {
         return true;
      } else if (var0.isEmpty()) {
         return false;
      } else {
         Item var2 = var0.getItem();
         if (var2.getTranslationKey().contains("shulker_box")) {
            ContainerComponent var3 = (ContainerComponent)var0.get(DataComponentTypes.CONTAINER);
            if (var3 != null) {
               for (ItemStack var5 : var3.iterateNonEmpty()) {
                  if (namesMatch(var5, var1)) {
                     return true;
                  }
               }
            }
         }

         String var11 = var0.getName().getString();
         var11 = Formatting.strip(var11.trim().toLowerCase(Locale.ROOT));
         Class_0833 var13 = String::contains;
         if (var1.length() >= 3 && var1.startsWith("\"") && var1.endsWith("\"")) {
            var1 = var1.substring(1, var1.length() - 1);
            var13 = String::equals;
         }

         if (var1.length() >= 3 && var1.startsWith("/") && var1.endsWith("/")) {
            var1 = var1.substring(1, var1.length() - 1);
            var13 = (var0x, var1x) -> Pattern.compile(var1x).matcher(var0x).find();
         }

         ItemEnchantmentsComponent var14 = (ItemEnchantmentsComponent)var0.get(DataComponentTypes.STORED_ENCHANTMENTS);
         if (var14 != null) {
            for (Entry var7 : var14.getEnchantmentEntries()) {
               if (testEnchant(var13, var1, var7)) {
                  return true;
               }
            }
         }

         if (var0.hasEnchantments()) {
            for (Entry var17 : var0.getEnchantments().getEnchantmentEntries()) {
               if (testEnchant(var13, var1, var17)) {
                  return true;
               }
            }
         }

         if (var13.test(I18n.translate(var2.getTranslationKey(var0), new Object[0]).toLowerCase(Locale.ROOT), var1)) {
            return true;
         } else {
            PotionContentsComponent var16 = (PotionContentsComponent)var0.get(DataComponentTypes.POTION_CONTENTS);
            if (var16 != null) {
               for (StatusEffectInstance var8 : var16.getEffects()) {
                  String var9 = I18n.translate(var8.getTranslationKey(), new Object[0]);
                  if (var13.test(var9.toLowerCase(Locale.ROOT), var1)) {
                     return true;
                  }
               }
            }

            return var13.test(var11, var1);
         }
      }
   }

   public static boolean testEnchant(Class_0833 var0, String var1, Entry<RegistryEntry<Enchantment>> var2) {
      if (var2 == null) {
         return false;
      } else {
         MutableText var3 = (MutableText)Enchantment.getName((RegistryEntry)var2.getKey(), var2.getIntValue());
         String var4 = var3.toString().toLowerCase(Locale.ROOT);
         var4 = var4.replaceAll("enchantment", "");
         return var0.test(var4, var1);
      }
   }

   public static boolean isFull(ItemStack var0, String var1) {
      if (!field_3025.field_1197.getValue()) {
         return false;
      } else {
         var1 = Formatting.strip(var1.trim().toLowerCase(Locale.ROOT));
         if (var1 != null && !var1.isEmpty()) {
            if (var0.isEmpty()) {
               return false;
            } else {
               Item var2 = var0.getItem();
               if (!var2.getTranslationKey().contains("shulker_box")) {
                  return false;
               } else {
                  ContainerComponent var3 = (ContainerComponent)var0.get(DataComponentTypes.CONTAINER);
                  if (var3 == null) {
                     return false;
                  } else if (var3.stream().toList().size() != 27) {
                     return false;
                  } else {
                     for (ItemStack var5 : var3.stream().toList()) {
                        if (!namesMatch(var5, var1)) {
                           return false;
                        }
                     }

                     return true;
                  }
               }
            }
         } else {
            return false;
         }
      }
   }
}
