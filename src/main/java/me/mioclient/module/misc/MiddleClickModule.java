package me.mioclient.module.misc;

import me.mioclient.Hub;
import me.mioclient.event.Event_17;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0136;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.exploit.RocketModule;
import me.mioclient.module.movement.ElytraFlyModule;
import me.mioclient.module.movement.FireworksModule;
import me.mioclient.setting.Setting;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import nick.Settings;
import org.lwjgl.glfw.GLFW;

public class MiddleClickModule extends Module {
   public static final FireworksModule field_1129 = Hub.field_2595.method_78(FireworksModule.class);
   public static final RocketModule field_1130 = Hub.field_2595.method_78(RocketModule.class);
   public static ElytraFlyModule elytrafly = Hub.field_2595.method_78(ElytraFlyModule.class);
   public Setting<Boolean> field_1131;
   public Setting<Boolean> field_1132;
   public Setting<Boolean> field_1133;
   public boolean field_1134;

   public MiddleClickModule() {
      super("MiddleClick", "Middle click actions.", Category.MISC);
      Settings.initialize(this);
      this.setDrawn(false);
   }

   @Subscribe
   public void method_2(Event_17 var1) {
      if (field_4219.currentScreen == null) {
         if (GLFW.glfwGetMouseButton(field_4219.getWindow().getHandle(), 2) == 1 && !this.field_1134) {
            this.field_1134 = true;
            HitResult var2 = field_4219.crosshairTarget;
            boolean var3 = field_4219.player.isFallFlying() || field_1129.method_786() || elytrafly.method_1183();
            if (var3 && this.field_1132.getValue()) {
               this.method_298();
            } else if (var2 instanceof EntityHitResult var4 && var4.getEntity() instanceof PlayerEntity var5 && this.field_1131.getValue()) {
               if (Hub.field_2603.method_1009(var5.getGameProfile().getName())) {
                  Hub.field_2603.method_869(var5.getGameProfile().getName());
               } else {
                  Hub.field_2603.method_632(var5.getGameProfile().getName());
               }
            }
         } else if (GLFW.glfwGetMouseButton(field_4219.getWindow().getHandle(), 2) == 0) {
            this.field_1134 = false;
         }
      }
   }

   public void method_298() {
      Hand var1 = Class_0136.method_7(Items.FIREWORK_ROCKET);
      int var2 = field_4219.player.getInventory().selectedSlot;
      int var3 = Class_0136.method_9(Items.FIREWORK_ROCKET);
      int var4 = Class_0136.method_5(Items.FIREWORK_ROCKET);
      if (var1 != null) {
         field_4219.interactionManager.interactItem(field_4219.player, var1);
      } else if (var3 != -1) {
         boolean var5 = var4 == -1 || this.field_1133.getValue();
         this.method_2(var4, var3, var5);
         field_4219.interactionManager.interactItem(field_4219.player, Hand.MAIN_HAND);
         field_1130.field_3535 = field_4219.player.getPos();
         this.method_2(var2, var3, var5);
      }
   }

   public void method_2(int var1, int var2, boolean var3) {
      if (var3) {
         Class_0136.method_39(var2);
      } else {
         Class_0136.method_16(var1);
      }
   }
}
