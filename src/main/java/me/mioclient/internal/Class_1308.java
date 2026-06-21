package me.mioclient.internal;

import me.mioclient.Hub;
import me.mioclient.api.MioAPI;
import me.mioclient.enum_.Class_0766;
import me.mioclient.mixin.ducks.DuckHandledScreen;
import me.mioclient.module.player.ChestStealerModule;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents.AfterInit;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.client.gui.screen.ingame.ShulkerBoxScreen;
import net.minecraft.client.gui.widget.ButtonWidget.Builder;
import net.minecraft.text.Text;

public class Class_1308 implements MioAPI, AfterInit {
   public final ChestStealerModule field_4217;

   public Class_1308(ChestStealerModule var1) {
      super();
      this.field_4217 = var1;
   }

   public void afterInit(MinecraftClient var1, Screen var2, int var3, int var4) {
      if ((var2 instanceof GenericContainerScreen || var2 instanceof ShulkerBoxScreen) && this.field_4217.isToggled() && this.field_4217.field_3207.getValue()) {
         this.field_4217.method_2((Class_0766)null);
         int var5 = Constants.field_684;
         int var6 = (var2.width - ((DuckHandledScreen)var2).getBgWidth()) / 2 - var5 - 2;
         int var7 = (var2.height - ((DuckHandledScreen)var2).getBgHeight()) / 2;

         for (Class_0766 var11 : Class_0766.values()) {
            Builder var12 = new Builder(Text.literal(var11.getName()), var3x -> {
               if (var11 == this.field_4217.method_933()) {
                  Hub.field_2619.method_2(() -> var2.setFocused(null), 0);
                  this.field_4217.method_2((Class_0766)null);
               } else {
                  this.field_4217.method_2(var11);
               }
            });
            var12.dimensions(var6, var7, var5, 15);
            var7 += 17;
            Screens.getButtons(var2).add(var12.build());
         }
      }
   }
}
