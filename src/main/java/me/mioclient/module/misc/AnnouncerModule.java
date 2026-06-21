package me.mioclient.module.misc;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import me.mioclient.Hub;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_10;
import me.mioclient.event.Event_30;
import me.mioclient.event.Event_4;
import me.mioclient.event.Event_44;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Timer;
import me.mioclient.internal.Class_0930;
import me.mioclient.internal.Class_1035;
import me.mioclient.internal.ChatUtil;
import me.mioclient.internal.TextBuilder;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.network.packet.s2c.play.PlayerListS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerRemoveS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerListS2CPacket.Entry;
import net.minecraft.text.Text;
import nick.Settings;

public class AnnouncerModule extends Module {
   public Setting<Boolean> field_1408;
   public Setting<Boolean> field_1409;
   public Setting<Boolean> field_1410;
   public Setting<Boolean> field_1411;
   public Setting<Boolean> field_1412;
   public Setting<Boolean> field_1413;
   public Setting<Boolean> field_1414;
   public Setting<Boolean> field_1415;
   public Setting<Boolean> field_1416;
   public Setting<Double> field_1417;
   public final Timer field_1418;
   public double field_1419;
   public int field_1420;
   public int field_1421;

   public AnnouncerModule() {
      super("Announcer", "Lets the others know what you're doing.", Category.MISC);
      Settings.initialize(this);
      this.field_1418 = new Timer();
      this.setDrawn(false);
   }

   @Override
   public void onEnable() {
      this.field_1420 = 0;
      this.field_1421 = 0;
      this.field_1418.reset();
   }

   @Subscribe
   public void method_2(Event_10 var1) {
      if (var1.method_127() instanceof ChatMessageC2SPacket) {
         this.field_1418.reset();
      }
   }

   @Subscribe
   public void method_5(Event_4 var1) {
      if (this.field_1410.getValue() && this.field_1418.method_2(this.field_1417.getValue(), TimeUnit.SECONDS)) {
         if (var1.method_127() instanceof PlayerListS2CPacket var2) {
            for (Entry var4 : var2.getPlayerAdditionEntries()) {
               if (var4.listed()) {
                  String var5 = var4.profile().getName();
                  if (!var5.equals(field_4219.player.getName().getString())) {
                     boolean var6 = Hub.field_2603.method_1009(var5);
                     boolean var7 = Hub.field_2603.method_289(var5);
                     if ((this.field_1411.getValue() || !var6) && (this.field_1412.getValue() || !var7) && (this.field_1413.getValue() || var6 || var7)) {
                        if (this.field_1408.getValue() && this.field_1409.getValue()) {
                           var5 = new TextBuilder().method_2((Object)var5).method_9("\u0001 joined");
                           if (var6) {
                              var5 = new TextBuilder().method_2((Object)var5).method_9("\u0001 [friend]");
                           } else if (var7) {
                              var5 = new TextBuilder().method_2((Object)var5).method_9("\u0001 [enemy]");
                           }

                           final String var5_a = var5;
                           field_4219.executeSync(() -> this.method_2(var5_a, ChatUtil.method_38(-254721)));
                        } else {
                           if (var6) {
                              var5 = new TextBuilder().method_2((Object)var5).method_9("My friend \u0001");
                           }

                           final String var5_b = var5;
                           field_4219.executeSync(
                              () -> this.method_2(
                                    new TextBuilder().method_2((Object)var5_b).method_9("\u0001 has joined the game!"), ChatUtil.method_38(-254721)
                                 )
                           );
                        }

                        this.field_1418.reset();
                     }
                  }
               }
            }
         }

         if (var1.method_127() instanceof PlayerRemoveS2CPacket var9) {
            for (UUID var13 : var9.profileIds()) {
               for (PlayerListEntry var16 : field_4219.player.networkHandler.getListedPlayerListEntries()) {
                  if (var16.getProfile().getId().equals(var13)) {
                     String var17 = var16.getProfile().getName();
                     if (this.field_1408.getValue() && this.field_1409.getValue()) {
                        var17 = new TextBuilder().method_2((Object)var17).method_9("\u0001 left");
                        if (Hub.field_2603.method_1009(var17)) {
                           var17 = new TextBuilder().method_2((Object)var17).method_9("\u0001 [friend]");
                        } else if (Hub.field_2603.method_289(var17)) {
                           var17 = new TextBuilder().method_2((Object)var17).method_9("\u0001 [enemy]");
                        }

                        String var19 = var17;
                        field_4219.executeSync(() -> this.method_2(var19, ChatUtil.method_38(-254722)));
                     } else {
                        if (Hub.field_2603.method_1009(var17)) {
                           var17 = new TextBuilder().method_2((Object)var17).method_9("My friend \u0001");
                        }

                        String var8 = var17;
                        field_4219.executeSync(
                           () -> this.method_2(new TextBuilder().method_2((Object)var8).method_9("\u0001 has left the game!"), ChatUtil.method_38(-254722))
                        );
                     }

                     this.field_1418.reset();
                  }
               }
            }
         }
      }
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if (!this.method_535()) {
         this.field_1419 = this.field_1419
            + Hub.field_2614.method_876() / Double.longBitsToDouble(4615288898129284301L) / Double.longBitsToDouble(4626322717216342016L);
         if (this.field_1419 >= Double.longBitsToDouble(4652007308841189376L)) {
            this.field_1419 = 0.0;
         }

         if (this.field_1414.getValue()
            && this.field_1419 >= Double.longBitsToDouble(4607182418800017408L)
            && this.field_1418.method_9((long)(Double.longBitsToDouble(4652007308841189376L) * this.field_1417.getValue()))) {
            this.method_483(this.method_480().replace("{blocks}", new DecimalFormat("0.00").format(this.field_1419)));
            this.field_1419 = 0.0;
            this.field_1418.reset();
         }
      }
   }

   @Subscribe
   public void method_2(Event_30 var1) {
      if (!this.method_535()) {
         int var2 = Class_0930.method_7(1, 6);
         if (this.field_1416.getValue() && var1.method_602().contains(DataComponentTypes.FOOD)) {
            this.field_1420++;
            if (this.field_1420 >= var2 && this.field_1418.method_9((long)(Double.longBitsToDouble(4652007308841189376L) * this.field_1417.getValue()))) {
               int var10003 = this.field_1420;
               this.method_483(
                  this.method_482()
                     .replace("{amount}", new TextBuilder().method_2(var10003).method_9("\u0001"))
                     .replace("{name}", new TextBuilder().method_2(var1.method_602().getItem().getName().getString()).method_9("\u0001"))
               );
               this.field_1420 = 0;
               this.field_1418.reset();
            }
         }
      }
   }

   @Subscribe
   public void method_2(Event_44 var1) {
      if (!this.method_535()) {
         int var2 = Class_0930.method_7(1, 6);
         this.field_1421++;
         if (this.field_1415.getValue()
            && this.field_1421 >= var2
            && this.field_1418.method_9((long)(Double.longBitsToDouble(4652007308841189376L) * this.field_1417.getValue()))) {
            String[] var3 = new String[]{
               new TextBuilder().method_2(Class_1035.method_30(var1.method_111()).getName().getString()).method_9("\u0001"), "Air", "Bedrock", "Barrier"
            };
            int var10003 = this.field_1421;
            this.method_483(
               this.method_481()
                  .replace("{amount}", new TextBuilder().method_2(var10003).method_9("\u0001"))
                  .replace("{name}", var3[new Random().nextInt(var3.length)])
            );
            this.field_1421 = 0;
            this.field_1418.reset();
         }
      }
   }

   public String method_480() {
      String[] var1 = new String[]{
         "I just flew over {blocks} blocks thanks to mioclient.me!",
         "Я только что пролетел над {blocks} блоками с помощью mioclient.me!",
         "mioclient.me sayesinde {blocks} blok uçtum!",
         "我刚刚用 mioclient.me 走了 {blocks} 米!",
         "Dank mioclient.me bin ich gerade über {blocks} Blöcke geflogen!",
         "Jag hoppade precis över {blocks} blocks tack vare mioclient.me!",
         "Właśnie przeleciałem ponad {blocks} bloki dzięki mioclient.me!",
         "Es tikko nolidoju {blocks} blokus, paldies mioclient.me!",
         "Я щойно пролетів понад {blocks} блоками завдяки mioclient.me!",
         "I just fwew ovew {blocks} bwoccs thanks to miocwient.me! :3",
         "Ho appena camminato per {blocks} blocchi grazie a mioclient.me!",
         "עכשיו עפתי {blocks} הודות ל mioclient.me!",
         "Právě jsem proletěl {blocks} bloků díky mioclient.me!",
         "Lensin juuri yli {blocks} blokkia mioclient.me:n ansiosta!",
         "Ravnokar sem preletel {blocks} blokov v zahvali mioclient.me!",
         "أنا هلق طاير فوق {blocks} بلوكس بفضل mioclient.me!"
      };
      return var1[new Random().nextInt(var1.length)];
   }

   public String method_481() {
      String[] var1 = new String[]{
         "I just destroyed {amount} {name} with the power of mioclient.me!",
         "Я только что разрушил {amount} {name} с помощью mioclient.me!",
         "Az önce {amount} tane {name} kırdım. TeŞekkürler mioclient.me!",
         "我刚刚用 mioclient.me 破坏了 {amount} {name}!",
         "Ich habe gerade {amount} {name} mit der Kraft von mioclient.me zerstört!",
         "Jag förstörde precis {amount} {name} tack vare mioclient.me!",
         "Właśnie zniszczyłem {amount} {name} za pomocą mioclient.me",
         "Es tikko salauzu {amount} {name} ar spēku mioclient.me!",
         "Я щойно знищив {amount} {name} за допомогою mioclient.me!",
         "I just destwoyed {amount} {name} with the powew of miocwient.me! :3",
         "Ho appena distrutto {amount} {name} grazie al potere di mioclient.me!",
         "בדיוק חצבתי {amount} {name} בעזרת הכוח של mioclient.me!",
         "Právě jsem zničil {amount} {name} díky mioclient.me!",
         "Rikoin juuri {amount} {name}ia mioclient.me:n ansiosta!",
         "Ravnokar sem uničil {amount} {name} z močjo mioclient.me",
         "أنا هلق دمّرت {amount} {name} بقوة mioclient.me!"
      };
      return var1[new Random().nextInt(var1.length)];
   }

   public String method_482() {
      String[] var1 = new String[]{
         "I just ate {amount} {name} thanks to mioclient.me!",
         "Я только что съел {amount} {name} с помощью mioclient.me!",
         "Tam olarak {amount} tane {name} yedim. TeŞekkürler mioclient.me",
         "我刚用 mioclient.me 吃了 {amount} 个 {name}!",
         "Ich habe gerade {amount} {name} dank mioclient.me gegessen!",
         "Jag åt precis {amount} {name} tack vare mioclient.me",
         "Właśnie zjadłem {amount} {name} dzięki mioclient.me",
         "Es tikko apēdu {amount} {name} ar mioclient.me spēku!",
         "Я щойно з’їв {amount} {name} завдяки mioclient.me!",
         "I just ate {amount} {name} thanks to miocwient.me! ^-^",
         "Ho appena mangiato {amount} {name} grazie a mioclient.me!",
         "כרגע אכלתי {amount} {name} הודות לmioclient.me!",
         "Právě jsem snědl {amount} {name} díky mioclient.me",
         "Söin juuri {amount} {name}a mioclient.me:n ansiosta!",
         "Ravnokar sem pojedel {amount} {name} v zahvali mioclient.me",
         "أنا هلق أكلت {amount} {name} بفضل miocliet.me!"
      };
      return var1[new Random().nextInt(var1.length)];
   }

   public void method_483(String var1) {
      this.method_2(var1, ChatUtil.method_2(this));
   }

   public void method_2(String var1, MessageSignatureData var2) {
      if (this.field_1408.getValue()) {
         ChatUtil.method_2(Text.literal(var1).styled(var0 -> var0.withColor(new Color(161, 161, 161).hashCode())), var2);
      } else {
         field_4219.player.networkHandler.sendChatMessage(var1);
      }
   }
}
