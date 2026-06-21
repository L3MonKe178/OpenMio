package me.mioclient.mixin;

import me.mioclient.Hub;
import me.mioclient.api.MioAPI;
import me.mioclient.event.Event_22;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.multiplayer.ConnectScreen;
import net.minecraft.client.network.CookieStorage;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.network.ServerInfo.ServerType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ConnectScreen.class})
public class MixinConnectScreen {
   public MixinConnectScreen() {
      super();
   }

   @Inject(
      method = {"connect(Lnet/minecraft/client/MinecraftClient;Lnet/minecraft/client/network/ServerAddress;Lnet/minecraft/client/network/ServerInfo;Lnet/minecraft/client/network/CookieStorage;)V"},
      at = {@At("HEAD")}
   )
   private void connectHook(MinecraftClient var1, ServerAddress var2, ServerInfo var3, CookieStorage var4, CallbackInfo var5) {
      ServerInfo var6 = var3;
      if (var3 == null) {
         var6 = new ServerInfo("mioclient", var2.getAddress(), ServerType.OTHER);
      }

      MioAPI.field_4220.method_36(new Event_22(var6.address));
      Hub.field_2602.method_2(var6);
   }
}
