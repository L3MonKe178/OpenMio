package me.mioclient.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import java.util.List;
import java.util.Optional;
import me.mioclient.Hub;
import me.mioclient.internal.Class_0593;
import me.mioclient.module.abstract_.AbstractModule_37;
import me.mioclient.record.Class_0576;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipData;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin({DrawContext.class})
public abstract class MixinDrawContext {
   @Unique
   private static AbstractModule_37 tooltips = Hub.field_2595.method_78(AbstractModule_37.class);
   @Unique
   private boolean translate = false;

   public MixinDrawContext() {
      super();
   }

   @Shadow
   public abstract void drawItem(ItemStack var1, int var2, int var3);

   @Shadow
   public abstract MatrixStack getMatrices();

   @Inject(
      at = {@At(
         value = "INVOKE",
         target = "net/minecraft/item/ItemStack.isItemBarVisible()Z"
      )},
      method = {"drawItemInSlot(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/item/ItemStack;IILjava/lang/String;)V"}
   )
   private void drawItemInSlotHook(TextRenderer var1, ItemStack var2, int var3, int var4, @Nullable String var5, CallbackInfo var6) {
      if (tooltips.isToggled() && tooltips.field_1185.getValue()) {
         for (Class_0576 var8 : tooltips.field_1192) {
            if (var8.method_594() == var2 && var8.method_595() != null) {
               this.translate = true;
               this.drawItem(var8.method_595(), var3, var4);
               this.translate = false;
            }
         }
      }
   }

   @ModifyArgs(
      method = {"drawItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/world/World;Lnet/minecraft/item/ItemStack;IIII)V"},
      at = @At(
         value = "INVOKE",
         target = "net/minecraft/client/util/math/MatrixStack.translate(FFF)V"
      )
   )
   private void drawItemHook(Args var1) {
      if (this.translate) {
         var1.set(0, (Float)var1.get(0) + 3.5F);
         var1.set(1, (Float)var1.get(1) - 4.0F);
         var1.set(2, (Float)var1.get(2) + 100.0F);
      }
   }

   @ModifyArgs(
      method = {"drawItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/world/World;Lnet/minecraft/item/ItemStack;IIII)V"},
      at = @At(
         value = "INVOKE",
         target = "net/minecraft/client/util/math/MatrixStack.scale(FFF)V"
      )
   )
   private void drawItemHook2(Args var1) {
      if (this.translate) {
         var1.set(0, 10.0F);
         var1.set(1, -10.0F);
      }
   }

   @Inject(
      method = {"drawTooltip(Lnet/minecraft/client/font/TextRenderer;Ljava/util/List;Ljava/util/Optional;II)V"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/DrawContext;drawTooltip(Lnet/minecraft/client/font/TextRenderer;Ljava/util/List;IILnet/minecraft/client/gui/tooltip/TooltipPositioner;)V",
         shift = Shift.BEFORE
      )},
      cancellable = true
   )
   private void drawTooltipHook(
      TextRenderer var1, List<Text> var2, Optional<TooltipData> var3, int var4, int var5, CallbackInfo var6, @Local(ordinal = 1) List<TooltipComponent> var7
   ) {
      if (Class_0593.method_607()) {
         Class_0593.method_29(var7);
         var6.cancel();
      }
   }
}
