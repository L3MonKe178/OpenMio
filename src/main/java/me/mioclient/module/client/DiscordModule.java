package me.mioclient.module.client;

import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.entities.RichPresence;
import java.util.concurrent.CompletableFuture;
import me.mioclient.Hub;
import me.mioclient.event.Event_21;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Timer;
import me.mioclient.internal.TextBuilder;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.client.network.ServerInfo;
import nick.Settings;

public class DiscordModule extends Module {
   public Setting<Boolean> field_2013;
   public Setting<Boolean> field_2014;
   public CompletableFuture<IPCClient> field_2015;
   public long field_788;
   public final Timer field_2016;

   public DiscordModule() {
      super("Discord", "Displays mioclient.me as your discord activity.", Category.CLIENT);
      Settings.initialize(this);
      this.field_788 = System.currentTimeMillis();
      this.field_2016 = new Timer();
      this.setDrawn(false);
   }

   @Override
   public void onEnable() {
      IPCClient var1 = new IPCClient(1162833254580228176L);
      this.field_2015 = CompletableFuture.<IPCClient>supplyAsync(() -> {
         try {
            var1.connect();
            return var1;
         } catch (Exception var2) {
            throw new RuntimeException(var2);
         }
      }).exceptionally(var1x -> {
         if (this.isToggled()) {
            this.method_68();
         }

         return null;
      });
   }

   @Override
   public void onDisable() {
      if (this.field_2015.isDone() && !this.field_2015.isCancelled()) {
         try {
            this.field_2015.get().close();
         } catch (Exception var2) {
            var2.printStackTrace();
         }
      }
   }

   @Subscribe
   public void method_5(Event_21 var1) {
      try {
         if (!this.field_2015.isDone() || !this.field_2016.method_9(2500L)) {
            return;
         }

         this.field_2016.reset();
         IPCClient var2 = this.field_2015.getNow(null);
         if (var2 == null) {
            return;
         }

         RichPresence.Builder var3 = new RichPresence.Builder();
         var3.setLargeImage("https://mioclient.me/assets/dcrpc3.png", this.method_649());
         var3.setStartTimestamp(this.field_788);
         if (this.field_2013.getValue()) {
            var3.setDetails(new TextBuilder().method_2(Hub.field_2609.method_802()).method_9("uid \u0001"));
         }

         if (this.field_2014.getValue()) {
            ServerInfo var4 = null;
            String var5 = "Not playing";
            if (!this.method_535()) {
               var4 = field_4219.player.networkHandler.getServerInfo();
               String var10000 = !field_4219.isInSingleplayer() && var4 != null ? var4.address : "singleplayer";
               var5 = new TextBuilder().method_2((Object)var10000).method_9("Playing \u0001");
            }

            var3.setState(var5);
            if (var4 != null && var4.players != null) {
               var3.setParty(Integer.toString(var5.hashCode()), var4.players.online(), var4.players.max(), 0);
            }
         }

         var2.sendRichPresence(var3.build());
      } catch (Exception var6) {
      }
   }

   public String method_649() {
      return "Mio v2.1.7";
   }
}
