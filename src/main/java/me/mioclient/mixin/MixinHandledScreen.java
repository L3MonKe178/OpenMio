package me.mioclient.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import java.util.List;
import java.util.Optional;
import me.mioclient.Hub;
import me.mioclient.api.Class_1309;
import me.mioclient.event.Event_41;
import me.mioclient.event.Event_42;
import me.mioclient.internal.Class_0018;
import me.mioclient.internal.Class_0136;
import me.mioclient.internal.Class_0593;
import me.mioclient.internal.Class_0982;
import me.mioclient.mixin.ducks.DuckDrawContext;
import me.mioclient.module.abstract_.AbstractModule_37;
import me.mioclient.module.misc.ChestSearchBarModule;
import me.mioclient.record.Class_0089;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.tooltip.HoveredTooltipPositioner;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.MapIdComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.map.MapState;
import net.minecraft.item.tooltip.TooltipData;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({HandledScreen.class})
public class MixinHandledScreen implements Class_1309 {
   private static final ChestSearchBarModule chestsearchbar = Hub.field_2595.method_78(ChestSearchBarModule.class);
   private static final AbstractModule_37 tooltips = Hub.field_2595.method_78(AbstractModule_37.class);
   @Shadow
   @Nullable
   protected Slot field_2787;
   @Unique
   private int lastMX;
   @Unique
   private int lastMY;
   @Unique
   private int mio$lastTooltipX;
   @Unique
   private int mio$lastTooltipY;
   @Unique
   private Slot mio$lastSlot;
   @Unique
   private MapState mio$cachedState;
   @Unique
   private int mio$cachedId;
   @Unique
   private final Event_42 event = new Event_42((Screen)(Object)this);

   public MixinHandledScreen() {
      super();
   }

   @Inject(
      method = {"keyPressed"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void keyPressed(int var1, int var2, int var3, CallbackInfoReturnable<Boolean> var4) {
      if (chestsearchbar != null
         && chestsearchbar.isToggled()
         && Class_0982.field_3026 != null
         && Class_0982.field_3026.isFocused()
         && field_4219.options.inventoryKey.matchesKey(var1, var2)) {
         var4.setReturnValue(true);
      }
   }

   @Inject(
      method = {"tick"},
      at = {@At("HEAD")}
   )
   private void tick(CallbackInfo var1) {
      this.event.method_273();
   }

   @Inject(
      method = {"drawMouseoverTooltip"},
      at = {@At("HEAD")}
   )
   private void drawMouseoverTooltipHook(DrawContext var1, int var2, int var3, CallbackInfo var4) {
      this.event.method_9(var1);
      this.event.setX((float)var2);
      this.event.setY((float)var3);
      field_4220.method_36(this.event);
   }

   @Inject(
      method = {"render"},
      at = {@At("HEAD")}
   )
   private void render(DrawContext var1, int var2, int var3, float var4, CallbackInfo var5) {
      if (!Class_0018.method_22(342)) {
         this.mio$lastSlot = this.field_2787;
      }

      this.lastMX = var2;
      this.lastMY = var3;
   }

   @Inject(
      method = {"drawMouseoverTooltip"},
      at = {@At("HEAD")}
   )
   private void renderWithTooltipHook(DrawContext var1, int var2, int var3, CallbackInfo var4) {
      if (tooltips.isToggled() && this.mio$lastSlot != null && Class_0136.method_29(this.mio$lastSlot.getStack().getItem()) && Class_0018.method_22(342)) {
         this.field_2787 = this.mio$lastSlot;
      }
   }

   @Redirect(
      method = {"drawMouseoverTooltip"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/DrawContext;drawTooltip(Lnet/minecraft/client/font/TextRenderer;Ljava/util/List;Ljava/util/Optional;II)V"
      )
   )
   private void renderWithTooltip(DrawContext var1, TextRenderer var2, List<Text> var3, Optional<TooltipData> var4, int var5, int var6) {
      boolean var7 = !Class_0018.method_22(342);
      if (!var7 && this.mio$lastSlot != null && Class_0136.method_29(this.mio$lastSlot.getStack().getItem())) {
         var5 = this.mio$lastTooltipX;
         var6 = this.mio$lastTooltipY;
      } else {
         this.mio$lastTooltipX = var5;
         this.mio$lastTooltipY = var6;
      }

      List var8 = Class_0593.method_2(var1, var3, var4);
      Event_41 var9 = new Event_41((Screen)(Object)this, var8, var5, var6, this.lastMX, this.lastMY);
      Class_1309.field_4220.method_36(var9);
      if (!var9.method_464()) {
         ((DuckDrawContext)var1).drawTooltipsHook(var2, var9.method_563(), var5, var6, HoveredTooltipPositioner.INSTANCE);
      }
   }

   @Inject(
      method = {"drawSlot"},
      at = {@At("HEAD")}
   )
   private void drawSlot(DrawContext var1, Slot var2, CallbackInfo var3) {
      this.mio$cachedState = null;
      ItemStack var4 = var2.getStack();
      if (var4.isOf(Items.FILLED_MAP)) {
         int var5 = ((MapIdComponent)var4.getOrDefault(DataComponentTypes.MAP_ID, new MapIdComponent(-1))).id();
         if (var5 != -1) {
            this.mio$cachedId = var5;
            this.mio$cachedState = Hub.field_2624.method_9(var4, var5);
         }
      }
   }

   @WrapWithCondition(
      method = {"drawSlot"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/DrawContext;drawItem(Lnet/minecraft/item/ItemStack;III)V"
      )}
   )
   private boolean drawSlotHook(DrawContext var1, ItemStack var2, int var3, int var4, int var5) {
      return !tooltips.isToggled() || !tooltips.field_1189.getValue() || this.mio$cachedState == null;
   }

   @WrapWithCondition(
      method = {"drawSlot"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/DrawContext;drawItemInSlot(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/item/ItemStack;IILjava/lang/String;)V"
      )}
   )
   private boolean drawSlotHook(DrawContext var1, TextRenderer var2, ItemStack var3, int var4, int var5, String var6, @Local(argsOnly = true) Slot var7) {
      if (tooltips.isToggled() && tooltips.field_1189.getValue() && this.mio$cachedState != null) {
         Class_0089.method_2(var1.getMatrices(), this.mio$cachedState, this.mio$cachedId, (float)var4, (float)var5);
         if (!Class_0018.method_22(342)) {
            var1.drawItemInSlot(var2, var3, var4, var5);
         }

         return false;
      } else {
         return true;
      }
   }
}
