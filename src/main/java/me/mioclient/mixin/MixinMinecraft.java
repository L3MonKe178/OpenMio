package me.mioclient.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import me.mioclient.Hub;
import me.mioclient.api.MioAPI;
import me.mioclient.event.Event_11;
import me.mioclient.event.Event_20;
import me.mioclient.event.Event_21;
import me.mioclient.event.Event_53;
import me.mioclient.event.Event_61;
import me.mioclient.internal.Class_0631;
import me.mioclient.internal.Class_1117;
import me.mioclient.module.abstract_.AbstractModule_38;
import me.mioclient.module.client.UIModule;
import me.mioclient.module.combat.ArrowsModule;
import me.mioclient.module.combat.AutoClickerModule;
import me.mioclient.module.combat.AutoCrystalModule;
import me.mioclient.module.exploit.MultiTaskModule;
import me.mioclient.module.misc.AntiQuitModule;
import me.mioclient.module.misc.UnfocusedFPSModule;
import me.mioclient.module.player.AutoEatModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.Window;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({MinecraftClient.class})
public class MixinMinecraft {
   private static UIModule clickgui = Hub.field_2595.method_78(UIModule.class);
   private static MultiTaskModule multitask = Hub.field_2595.method_78(MultiTaskModule.class);
   private static AutoEatModule autoeat = Hub.field_2595.method_78(AutoEatModule.class);
   private static UnfocusedFPSModule unfocusedfps = Hub.field_2595.method_78(UnfocusedFPSModule.class);
   private static AntiQuitModule antiquit = Hub.field_2595.method_78(AntiQuitModule.class);
   private static AbstractModule_38 nohitdelay = Hub.field_2595.method_78(AbstractModule_38.class);
   private static AutoClickerModule autoclicker = Hub.field_2595.method_78(AutoClickerModule.class);
   private static final ArrowsModule arrows = Hub.field_2595.method_78(ArrowsModule.class);
   private static final AutoCrystalModule autocrystal = Hub.field_2595.method_78(AutoCrystalModule.class);
   @Unique
   private Screen mio$lastScreen;
   @Shadow
   private boolean windowFocused;
   @Shadow
   public int attackCooldown;
   @Shadow
   @Final
   public GameOptions options;
   @Shadow
   @Nullable
   public Screen currentScreen;
   @Shadow
   @Final
   private Window window;
   private static boolean saved = false;

   public MixinMinecraft() {
      super();
   }

   @Inject(
      method = {"doAttack()Z"},
      at = {@At("HEAD")}
   )
   public void doAttackHook(CallbackInfoReturnable<Boolean> var1) {
      if (nohitdelay.isToggled() || autoclicker.isToggled()) {
         this.attackCooldown = 0;
      }
   }

   @Inject(
      method = {"stop"},
      at = {@At("HEAD")}
   )
   public void scheduleStop(CallbackInfo var1) {
      if (!saved && Hub.field_2597 != null) {
         saved = true;
         clickgui.disable();
         Hub.field_2597.method_357();
      }
   }

   @Inject(
      method = {"disconnect(Lnet/minecraft/client/gui/screen/Screen;)V"},
      at = {@At("HEAD")}
   )
   public void disconnect(Screen var1, CallbackInfo var2) {
      Event_20 var3 = new Event_20();
      MioAPI.field_4220.method_36(var3);
   }

   @ModifyExpressionValue(
      method = {"handleBlockBreaking"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z"
      )}
   )
   public boolean handleBlockBreakingHook(boolean var1) {
      return !multitask.isToggled() && var1;
   }

   @Redirect(
      method = {"doItemUse"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/network/ClientPlayerInteractionManager;isBreakingBlock()Z"
      ),
      require = 0
   )
   public boolean doItemUseHook(ClientPlayerInteractionManager var1) {
      return !multitask.isToggled() && var1.isBreakingBlock();
   }

   @Inject(
      method = {"setScreen"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void setScreenHookPre(Screen var1, CallbackInfo var2) {
      this.mio$lastScreen = this.currentScreen;
      Event_53 var3 = new Event_53(this.mio$lastScreen, var1);
      MioAPI.field_4220.method_36(var3);
      if (var3.method_464()) {
         var2.cancel();
      }
   }

   @Inject(
      method = {"setScreen"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/screen/Screen;init(Lnet/minecraft/client/MinecraftClient;II)V",
         shift = Shift.AFTER
      )},
      cancellable = true
   )
   private void setScreenHookPost(Screen var1, CallbackInfo var2) {
      Event_61 var3 = new Event_61(this.mio$lastScreen, var1);
      MioAPI.field_4220.method_36(var3);
      if (var3.method_464()) {
         var2.cancel();
      }
   }

   @Inject(
      method = {"tick"},
      at = {@At("HEAD")}
   )
   private void tickHook(CallbackInfo var1) {
      MioAPI.field_4220.method_36(Event_21.field_3173);
   }

   @ModifyExpressionValue(
      method = {"handleInputEvents"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z"
      )}
   )
   private boolean handleInputEventsHook(boolean var1) {
      return autoeat.method_892() && autoeat.isToggled() ? false : var1;
   }

   @Inject(
      method = {"getFramerateLimit"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void getFramerateLimit(CallbackInfoReturnable<Integer> var1) {
      if (unfocusedfps.isToggled() && !this.windowFocused) {
         if (unfocusedfps.field_2211.method_468()) {
            var1.setReturnValue(unfocusedfps.method_681());
         } else {
            var1.setReturnValue(unfocusedfps.field_2211.getValue());
         }
      } else {
         if (this.currentScreen instanceof Class_1117 && this.windowFocused) {
            var1.setReturnValue(this.window.getFramerateLimit());
         }
      }
   }

   @WrapWithCondition(
      method = {"render"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/MinecraftClient;scheduleStop()V"
      )}
   )
   private boolean renderHook(MinecraftClient var1) {
      if (antiquit.isToggled() && antiquit.field_3761.getValue()) {
         if (!(var1.currentScreen instanceof Class_0631)) {
            var1.setScreen(new Class_0631());
         }

         return false;
      } else {
         return true;
      }
   }

   @Inject(
      method = {"render"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/MinecraftClient;runTasks()V",
         shift = Shift.AFTER
      )}
   )
   private void render(boolean var1, CallbackInfo var2, @Local int var3) {
      MioAPI.field_4220.method_36(new Event_11(var3 >= 1));
   }

   @ModifyExpressionValue(
      method = {"handleInputEvents"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z"
      )}
   )
   private boolean handleInputEvents(boolean var1) {
      return arrows.isToggled() && arrows.field_654.getValue() ? false : var1;
   }

   @Inject(
      method = {"doItemUse"},
      at = {@At("HEAD")}
   )
   private void doItemUse(CallbackInfo var1) {
      autocrystal.method_463();
   }

   @ModifyExpressionValue(
      method = {"doItemUse"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/network/ClientPlayerEntity;isRiding()Z"
      )}
   )
   private boolean doItemUseHook(boolean var1) {
      return multitask.isToggled() ? false : var1;
   }

   @ModifyExpressionValue(
      method = {"doAttack"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/network/ClientPlayerEntity;isRiding()Z"
      )}
   )
   private boolean doAttack(boolean var1) {
      return multitask.isToggled() ? false : var1;
   }
}
