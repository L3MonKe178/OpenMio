package me.mioclient.mixin;

import me.mioclient.Hub;
import me.mioclient.module.misc.AutoReconnectModule;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.DisconnectedScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.ConnectScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.DirectionalLayoutWidget;
import net.minecraft.client.gui.widget.ButtonWidget.Builder;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.network.DisconnectionInfo;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({DisconnectedScreen.class})
public class MixinDisconnectedScreen extends Screen {
   private static AutoReconnectModule autoreconnect = Hub.field_2595.method_78(AutoReconnectModule.class);
   @Unique
   private long startTime;
   @Shadow
   @Final
   private DisconnectionInfo info;
   @Shadow
   @Final
   private DirectionalLayoutWidget grid;
   @Unique
   private ButtonWidget reconnectButton;
   @Unique
   private ButtonWidget autoReconnectButton;

   protected MixinDisconnectedScreen(Text var1) {
      super(var1);
   }

   @Inject(
      method = {"init"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/screen/DisconnectedScreen;initTabNavigation()V",
         shift = Shift.BEFORE
      )}
   )
   private void initHook(CallbackInfo var1) {
      this.startTime = System.currentTimeMillis();
      Builder var2 = ButtonWidget.builder(Text.literal("Reconnect"), var1x -> {
         ServerInfo var2x = Hub.field_2602.method_991();
         if (var2x != null) {
            ConnectScreen.connect(new MultiplayerScreen(new TitleScreen()), this.client, ServerAddress.parse(var2x.address), var2x, true, null);
         }
      }).width(200);
      Builder var3 = ButtonWidget.builder(Text.literal("AutoReconnect"), var1x -> {
         if (!autoreconnect.isToggled()) {
            Hub.field_2602.field_3453 = false;
         }

         autoreconnect.method_68();
         this.startTime = System.currentTimeMillis();
         var1x.setFocused(false);
      }).width(200);
      this.reconnectButton = var2.build();
      this.autoReconnectButton = var3.build();
      this.grid.refreshPositions();
      this.addDrawableChild(this.reconnectButton);
      this.addDrawableChild(this.autoReconnectButton);
   }

   @Inject(
      method = {"initTabNavigation"},
      at = {@At("TAIL")}
   )
   private void initTabHook(CallbackInfo var1) {
      if (this.reconnectButton != null) {
         int var2 = this.width / 2 - 100;
         this.reconnectButton.setPosition(var2, Math.min(this.height / 2 + this.grid.getHeight() / 2, this.height - 30) - 1);
         this.autoReconnectButton.setPosition(var2, Math.min(this.height / 2 + this.grid.getHeight() / 2, this.height - 30) + 20);
      }
   }

   public void tick() {
      String var1 = this.info.reason().getString();
      if (var1.contains("Mio") && var1.contains("[AutoLog]")) {
         this.autoReconnectButton.setX(-1000);
         this.autoReconnectButton.setY(-1000);
      } else if (autoreconnect.isToggled()) {
         float var2 = this.getAutoReconnectTime(autoreconnect);
         if (!(var2 > 0.0F)) {
            ServerInfo var3 = Hub.field_2602.method_991();
            if (var3 != null) {
               ConnectScreen.connect(new MultiplayerScreen(new TitleScreen()), this.client, ServerAddress.parse(var3.address), var3, true, null);
            }
         }
      }
   }

   public void render(DrawContext context, int mouseX, int mouseY, float delta) {
      String var5 = this.info.reason().getString();
      if (var5.contains("Mio") && var5.contains("[AutoLog]")) {
         this.autoReconnectButton.setX(-1000);
         this.autoReconnectButton.setY(-1000);
      } else {
         this.autoReconnectButton.setMessage(this.getAutoReconnectText());
         this.autoReconnectButton.setFocused(false);
         super.render(context, mouseX, mouseY, delta);
      }
   }

   private Text getAutoReconnectText() {
      MutableText var1 = Text.literal("AutoReconnect ");
      String var2 = this.info.reason().getString();
      boolean var3 = autoreconnect.isToggled() && (!var2.contains("Mio") || !var2.contains("[AutoLog]"));
      float var4 = this.getAutoReconnectTime(autoreconnect);
      if (Hub.field_2602.field_3453) {
         return var1.append(Formatting.RED + "AutoLogged");
      } else {
         return var3 ? var1.append(Formatting.GREEN + "%.1fs".formatted(var4)) : var1.append(Formatting.RED + "OFF");
      }
   }

   private float getAutoReconnectTime(AutoReconnectModule var1) {
      return var1 == null ? 0.1F : Math.max(var1.field_2007.getValue() - (float)(System.currentTimeMillis() - this.startTime) / 1000.0F, 0.0F);
   }
}
