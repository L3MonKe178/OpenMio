package me.mioclient.internal;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import me.mioclient.mixin.ducks.DuckSession;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.ConnectScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;

public final class Class_0591 extends Class_0618 {
   public Class_0591() {
      super("session");
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      var1.then(
         RequiredArgumentBuilder.<CommandSource, String>argument("name", StringArgumentType.string())
            .executes(
               var0 -> {
                  String var1x = (String)var0.getArgument("name", String.class);
                  if (var1x.length() > 16) {
                     return 0;
                  } else {
                     if (field_4219.getNetworkHandler().getServerInfo() != null) {
                        field_4219.getNetworkHandler().getConnection().disconnect(Text.of(""));
                        ServerInfo var2 = field_4219.getNetworkHandler().getServerInfo();
                        ConnectScreen.connect(new MultiplayerScreen(new TitleScreen()), field_4219, ServerAddress.parse(var2.address), var2, true, null);
                     }

                     ((DuckSession)field_4219.getSession()).setUsername(var1x);
                     Class_1245.method_2(
                        Text.literal(new Class_1303().method_2((Object)var1x).method_9("Session's name has been set to \u0001.")), Class_1245.method_38(-1)
                     );
                     return 1;
                  }
               }
            )
      );
   }
}
