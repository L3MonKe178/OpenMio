package me.mioclient.internal;

import java.awt.Color;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import me.mioclient.Hub;
import me.mioclient.api.Class_1232;
import me.mioclient.api.Class_1309;
import me.mioclient.enum_.Priority;
import me.mioclient.module.Module;
import me.mioclient.module.client.NotificationsModule;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.ClickEvent.Action;
import net.minecraft.util.Formatting;

public class Class_1245 implements Class_1309 {
   public static final NotificationsModule field_3909 = Hub.field_2595.method_78(NotificationsModule.class);
   public static final MessageIndicator field_3910 = new MessageIndicator(-1, null, Text.empty(), "Mio");
   public static final Pattern field_3911 = Pattern.compile("^(\\w{3,16}) -> (\\w{3,16}): (.*)");
   public static final List<Pattern> field_3912 = List.of(
      Pattern.compile("^From (\\w{3,16}): (.*)"),
      Pattern.compile("^from (\\w{3,16}): (.*)"),
      Pattern.compile("^(\\w{3,16}) whispers: (.*)"),
      Pattern.compile("^(\\w{3,16}) -> me (.*)"),
      Pattern.compile("^(\\w{3,16}) whispers to you: (.*)"),
      Pattern.compile("^(\\w{3,16}) says: (.*)"),
      Pattern.compile("^(\\w{3,16}) пишет: (.*)"),
      field_3911
   );
   public static final List<Pattern> field_3913 = List.of(
      Pattern.compile("^To (\\w{3,16}): (.*)"),
      Pattern.compile("^to (\\w{3,16}): (.*)"),
      Pattern.compile("^\\[me -> (\\w{3,16})\\] (.*)"),
      Pattern.compile("^You whisper to (\\w{3,16}): (.*)"),
      Pattern.compile("^К (\\w{3,16}): (.*)"),
      field_3911
   );

   public Class_1245() {
      super();
   }

   public static boolean method_107(String var0) {
      if (var0.toLowerCase(Locale.ROOT).startsWith(field_4219.getSession().getUsername().toLowerCase(Locale.ROOT))) {
         return false;
      } else if (var0.contains(" шепчет: ")) {
         return true;
      } else if (var0.contains(" шепчет тебе: ")) {
         return true;
      } else {
         for (Pattern var2 : field_3912) {
            Matcher var3 = var2.matcher(var0);
            if (var3.find()) {
               return true;
            }
         }

         return false;
      }
   }

   public static boolean method_374(String var0) {
      boolean var1 = var0.toLowerCase(Locale.ROOT).startsWith(field_4219.getSession().getUsername().toLowerCase(Locale.ROOT));

      for (Pattern var3 : field_3913) {
         if (var3 != field_3911 || var1) {
            Matcher var4 = var3.matcher(var0);
            if (var4.find()) {
               return true;
            }
         }
      }

      return false;
   }

   public static MessageSignatureData method_38(int var0) {
      return new MessageSignatureData(ByteBuffer.allocate(256).putInt(var0).array());
   }

   public static MessageSignatureData method_22(Object var0) {
      return method_38(Math.abs(var0.hashCode()) * -1);
   }

   public static MessageSignatureData method_2(Module var0) {
      return method_38(Math.abs(var0.getName().hashCode()) * -1);
   }

   public static Style method_2(Style var0, Supplier<Integer> var1) {
      return ((Class_1232)var0.withColor((Integer)var1.get())).mio$withColor(var1);
   }

   public static MutableText method_5(String var0) {
      return var0 == null ? Text.empty() : Text.literal(var0).styled(var0x -> method_2(var0x, () -> field_3909.field_2159.getValue().hashCode()));
   }

   public static Text method_287() {
      MutableText var0 = Text.empty();
      var0.append(Text.literal("[").styled(var0x -> method_2(var0x, () -> field_3909.method_673().hashCode())));
      var0.append(Text.literal("Mio").styled(var0x -> method_2(var0x, () -> field_3909.method_672().hashCode())));
      var0.append(Text.literal("]").styled(var0x -> method_2(var0x, () -> field_3909.method_673().hashCode())));
      return var0;
   }

   public static void method_2(Text var0, int var1) {
      method_2(var0, method_38(var1));
   }

   public static void method_2(Text var0, MessageSignatureData var1) {
      field_4219.execute(() -> {
         try {
            field_4219.inGameHud.getChatHud().addMessage(Text.empty().append(method_287()).append(" ").append(var0), var1, field_3910);
         } catch (Throwable var3) {
         }
      });
   }

   public static void method_2(Text var0, MessageSignatureData var1, Priority var2) {
      MutableText var3 = Text.empty();
      var3.append(Text.literal("[").styled(var1x -> var1x.withColor(var2.method_152().darker().hashCode())));
      var3.append(Text.literal("!").styled(var1x -> var1x.withColor(var2.method_152().hashCode())));
      var3.append(Text.literal("] ").styled(var1x -> var1x.withColor(var2.method_152().darker().hashCode())));
      method_2(var3.append(var0), var1);
   }

   public static Text method_2(String var0, Supplier<Integer> var1, Color var2) {
      MutableText var3 = Text.empty();
      float[] var4 = Color.RGBtoHSB(var2.getRed(), var2.getGreen(), var2.getBlue(), null);

      for (int var5 = 0; var5 < var0.length(); var5++) {
         int var6 = var5;
         var3.append(
            Text.literal(String.valueOf(var0.charAt(var5)))
               .styled(var3x -> method_2(var3x, () -> Class_1081.method_2(var6 * (Integer)var1.get(), var4[1], var4[2], 255).hashCode()))
         );
      }

      return var3;
   }

   public static void method_425(String var0) {
      if (var0.startsWith("/")) {
         field_4219.player.networkHandler.sendChatCommand(var0.substring(1));
      } else {
         field_4219.player.networkHandler.sendChatMessage(var0);
      }
   }

   public static ClickEvent method_52(String var0) {
      return new ClickEvent(Action.SUGGEST_COMMAND, new Class_1303().method_2((Object)var0).method_2(Class_1032.method_927()).method_9("\u0001\u0001"));
   }

   public static Text method_185(String var0) {
      return Text.empty().append(Text.literal("[").formatted(Formatting.GRAY)).append(var0).append(Text.literal("]").formatted(Formatting.GRAY));
   }
}
