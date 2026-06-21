package me.mioclient.module.misc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import me.mioclient.Hub;
import me.mioclient.enum_.PreType;
import me.mioclient.enum_.Priority;
import me.mioclient.event.Event_20;
import me.mioclient.event.Event_21;
import me.mioclient.event.Event_46;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0242;
import me.mioclient.internal.Class_1032;
import me.mioclient.internal.Class_1245;
import me.mioclient.internal.Class_1303;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import nick.Settings;

public class DiscordNotifsModule extends Module {
   public Setting<Float> field_2539;
   public Setting<Boolean> field_2540;
   public Setting<Boolean> field_2541;
   public Setting<Boolean> field_2542;
   public Setting<Boolean> field_2543;
   public Setting<Boolean> field_2544;
   public Setting<Boolean> field_2545;
   public final DateTimeFormatter field_2546;
   public final List<String> field_2547;
   public final List<String> field_2548;
   public final Class_0242 field_2549;
   public String field_2550;
   public ServerInfo field_2551;

   public DiscordNotifsModule() {
      super("DiscordNotifs", "Sends chat messages to your Discord webhook.", Category.MISC);
      Settings.initialize(this);
      this.field_2546 = DateTimeFormatter.ofPattern("HH:mm");
      this.field_2547 = Collections.synchronizedList(new ArrayList<>());
      this.field_2548 = new ArrayList<>();
      this.field_2549 = new Class_0242();
   }

   @Override
   public void onEnable() {
      this.field_2549.reset();
      if (Hub.field_2621.method_695() == null || Hub.field_2621.method_695().isEmpty()) {
         String var1 = Class_1032.method_927();
         String var2 = new Class_1303().method_2((Object)var1).method_9("\u0001webhook set <url>");
         Class_1245.method_2(
            Text.literal("You don't have a webhook URL set. Set one by typing \"").append(var2).append("\"."), Class_1245.method_38(-10395), Priority.MID
         );
      }
   }

   @Subscribe
   public void method_5(Event_21 var1) {
      if (field_4219.world != null) {
         this.field_2551 = Hub.field_2602.method_991();
      }

      if (Hub.field_2621.method_695() != null && !Hub.field_2621.method_695().isEmpty()) {
         if (this.field_2549.method_2((double)this.field_2539.getValue().floatValue(), TimeUnit.SECONDS) && !this.field_2547.isEmpty()) {
            this.field_2549.reset();
            synchronized (this.field_2547) {
               StringBuilder var3 = new StringBuilder();
               LocalDateTime var4 = LocalDateTime.now();
               String var5 = "[%s] ".formatted(this.field_2546.format(var4));

               for (String var7 : this.field_2547) {
                  if (var3.length() > 1900) {
                     break;
                  }

                  if (this.field_2540.getValue()) {
                     var3.append(var5);
                  }

                  var3.append(var7);
                  var3.append("\n");
                  this.field_2548.add(var7);
               }

               String var11 = var3.toString();
               var11 = var11.substring(0, var11.length() - 1);
               Hub.field_2621.method_214("```%s```".formatted(var11));

               for (String var8 : this.field_2548) {
                  this.field_2547.remove(var8);
               }

               this.field_2548.clear();
            }
         }
      }
   }

   @Subscribe
   public void method_9(Event_46 var1) {
      if (!var1.method_464()) {
         if (Hub.field_2621.method_695() != null && !Hub.field_2621.method_695().isEmpty()) {
            if (var1.method_213() == PreType.PRE && var1.method_1035() != null) {
               if (this.field_2545.getValue() && var1.method_1036() == Class_1245.field_3910) {
                  return;
               }

               String var2 = var1.method_1035();
               String[] var3 = var2.split("\n");
               boolean var4 = Class_1245.method_107(var2);
               boolean var5 = Class_1245.method_374(var2);
               if (!var4 && !var5) {
                  if (var2.contains("Position in queue:") && var3.length > 15) {
                     if (this.field_2550 == null) {
                        this.field_2550 = var2;
                     } else {
                        if (this.field_2550.equals(var2)) {
                           return;
                        }

                        if (this.field_2541.getValue()) {
                           this.method_7(var3);
                           this.field_2550 = var2;
                        }
                     }
                  } else if (this.field_2544.getValue()) {
                     this.method_7(var3);
                  }
               } else if (this.field_2543.getValue()) {
                  this.method_7(var3);
               }
            }
         }
      }
   }

   @Subscribe
   public void method_2(Event_20 var1) {
      if (this.field_2542.getValue() && this.field_2551 != null) {
         String var2 = "You have been disconnected from %s!".formatted(this.field_2551.address);
         String[] var3 = var2.split("\n");
         this.method_7(var3);
         this.field_2551 = null;
      }
   }

   public void method_7(String[] var1) {
      StringBuilder var2 = new StringBuilder();

      for (String var6 : var1) {
         if (!var6.isEmpty()) {
            var2.append(Formatting.strip(var6));
            var2.append("\n");
         }
      }

      String var7 = var2.toString();
      if (var7.length() >= 2) {
         var7 = var7.substring(0, var7.length() - 1);
         this.field_2547.add(var7);
      }
   }
}
