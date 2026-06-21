package me.mioclient.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import me.mioclient.Hub;
import me.mioclient.api.Class_1226;
import me.mioclient.api.MioAPI;
import me.mioclient.enum_.Class_0186;
import me.mioclient.event.Event_25;
import me.mioclient.event.Event_36;
import me.mioclient.event.Event_44;
import me.mioclient.event.Event_52;
import me.mioclient.event.Event_55;
import me.mioclient.event.Event_58;
import me.mioclient.internal.PlayerUtil;
import me.mioclient.internal.PacketUtil;
import me.mioclient.module.combat.AutoClickerModule;
import me.mioclient.module.player.InventoryTweaksModule;
import me.mioclient.module.player.NoInteractModule;
import me.mioclient.module.player.SpeedMineModule;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.PlayerInteractItemC2SPacket;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket.Mode;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({ClientPlayerInteractionManager.class})
public abstract class MixinClientPlayerInteractionManager implements MioAPI, Class_1226 {
   private static final AutoClickerModule autoclicker = Hub.field_2595.method_78(AutoClickerModule.class);
   private static final NoInteractModule nointeract = Hub.field_2595.method_78(NoInteractModule.class);
   private static final InventoryTweaksModule inventorytweaks = Hub.field_2595.method_78(InventoryTweaksModule.class);
   private static SpeedMineModule speedmine = Hub.field_2595.method_78(SpeedMineModule.class);
   @Shadow
   private boolean breakingBlock;
   @Shadow
   private float currentBreakingProgress;
   @Shadow
   private BlockPos currentBreakingPos;
   @Shadow
   private int blockBreakingCooldown;

   public MixinClientPlayerInteractionManager() {
      super();
   }

   @Shadow
   public abstract void clickSlot(int var1, int var2, int var3, SlotActionType var4, PlayerEntity var5);

   @Inject(
      method = {"breakBlock"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void breakBlock(BlockPos var1, CallbackInfoReturnable<Boolean> var2) {
      Event_44 var3 = new Event_44(var1);
      field_4220.method_36(var3);
      if (var3.method_464()) {
         var2.setReturnValue(false);
         var2.cancel();
      }
   }

   @Inject(
      method = {"attackBlock"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void attackBlockHook(BlockPos var1, Direction var2, CallbackInfoReturnable<Boolean> var3) {
      Event_58 var4 = new Event_58(var1, var2);
      field_4220.method_36(var4);
      if (var4.method_464()) {
         var3.setReturnValue(true);
         var3.cancel();
      }
   }

   @Inject(
      method = {"clickSlot"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void onClickArmorSlot(int var1, int var2, int var3, SlotActionType var4, PlayerEntity var5, CallbackInfo var6) {
      if (inventorytweaks.isToggled()) {
         ScreenHandler var7 = var5.currentScreenHandler;
         if (inventorytweaks.field_680.getValue() && var3 == 1 && var4 == SlotActionType.QUICK_MOVE) {
            Slot var8 = var7.getSlot(var2);
            if (var8.getStack().getItem() instanceof ArmorItem || var8.getStack().isOf(Items.ELYTRA)) {
               PlayerUtil.method_5(var2, 8 - field_4219.player.getPreferredEquipmentSlot(var8.getStack()).getEntitySlotId());
               var6.cancel();
               return;
            }
         }

         if (inventorytweaks.field_681.getValue() && var3 == 1 && var4 == SlotActionType.QUICK_MOVE && var2 > 4 && var7 instanceof PlayerScreenHandler) {
            for (int var10 = 0; var10 < 4; var10++) {
               ItemStack var9 = (ItemStack)field_4219.player.playerScreenHandler.getCraftingInput().getHeldStacks().get(var10);
               if (var9.isEmpty()) {
                  this.clickSlot(var1, var2, 0, SlotActionType.PICKUP, field_4219.player);
                  this.clickSlot(var1, var10 + 1, 0, SlotActionType.PICKUP, field_4219.player);
                  var6.cancel();
                  return;
               }
            }
         }
      }
   }

   @Inject(
      method = {"interactBlock"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void interactBlockHook(ClientPlayerEntity var1, Hand var2, BlockHitResult var3, CallbackInfoReturnable<ActionResult> var4) {
      Event_36 var5 = new Event_36(var3, var2);
      field_4220.method_36(var5);
      if (var5.method_464()) {
         var4.setReturnValue(ActionResult.FAIL);
         var4.cancel();
      }

      var2 = NoInteractModule.method_490();
      ItemStack var6 = field_4219.player.getStackInHand(NoInteractModule.method_490());
      if (nointeract.method_2(var6, var3.getBlockPos())) {
         if (nointeract.field_1452.getValue() == Class_0186.SHIFT) {
            nointeract.field_1454 = !Hub.field_2602.method_993();
            PacketUtil.method_2(var1, Mode.PRESS_SHIFT_KEY, 0);
         } else {
            var4.setReturnValue(ActionResult.FAIL);
            var4.cancel();
         }

         field_4219.interactionManager.interactItem(field_4219.player, var2);
      }
   }

   @Inject(
      method = {"interactBlock"},
      at = {@At("RETURN")}
   )
   private void interactBlockHook2(ClientPlayerEntity var1, Hand var2, BlockHitResult var3, CallbackInfoReturnable<ActionResult> var4) {
      if (nointeract.field_1454) {
         PacketUtil.method_2(var1, Mode.RELEASE_SHIFT_KEY, 0);
         nointeract.field_1454 = false;
      }
   }

   @Inject(
      method = {"stopUsingItem"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void stopUsingItemHook(PlayerEntity var1, CallbackInfo var2) {
      Event_25 var3 = new Event_25();
      field_4220.method_36(var3);
      if (var3.method_464()) {
         var2.cancel();
      }
   }

   @Inject(
      method = {"interactItem"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void interactItemHook(PlayerEntity var1, Hand var2, CallbackInfoReturnable<ActionResult> var3) {
      Event_55 var4 = new Event_55(var2);
      field_4220.method_36(var4);
      if (var4.method_464()) {
         var3.cancel();
         var3.setReturnValue(ActionResult.FAIL);
      }
   }

   @Inject(
      method = {"attackEntity"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void attackEntityHook(PlayerEntity var1, Entity var2, CallbackInfo var3) {
      if (autoclicker.method_185(var2) && var1 == field_4219.player) {
         var3.cancel();
      }
   }

   @WrapWithCondition(
      method = {"cancelBlockBreaking"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/network/ClientPlayNetworkHandler;sendPacket(Lnet/minecraft/network/packet/Packet;)V"
      )}
   )
   private boolean cancelBlockBreaking(ClientPlayNetworkHandler var1, Packet var2) {
      return !speedmine.isToggled();
   }

   @Inject(
      method = {"updateBlockBreakingProgress"},
      at = {@At("HEAD")}
   )
   private void attackBlock(BlockPos var1, Direction var2, CallbackInfoReturnable<Boolean> var3) {
      if (speedmine.isToggled()) {
         this.blockBreakingCooldown = 0;
      }
   }

   @ModifyExpressionValue(
      method = {"method_41929"},
      at = {@At(
         value = "NEW",
         target = "(Lnet/minecraft/util/Hand;IFF)Lnet/minecraft/network/packet/c2s/play/PlayerInteractItemC2SPacket;"
      )}
   )
   private PlayerInteractItemC2SPacket interactItemHook(PlayerInteractItemC2SPacket var1) {
      Event_52 var2 = new Event_52(var1);
      field_4220.method_36(var2);
      return var2.method_464() ? var2.method_1102() : var1;
   }

   @Override
   public boolean isBreakingBlock() {
      return this.breakingBlock;
   }

   @Override
   public void setBreakingBlock(boolean var1) {
      this.breakingBlock = var1;
   }

   @Override
   public float getBreakingProgress() {
      return this.currentBreakingProgress;
   }

   @Override
   public void setBreakingProgress(float var1) {
      this.currentBreakingProgress = var1;
   }

   @Override
   public BlockPos getCurrentBreakingBlock() {
      return this.currentBreakingPos;
   }

   @Override
   public void setCurrentBreakingBlock(BlockPos var1) {
      this.currentBreakingPos = var1;
   }
}
