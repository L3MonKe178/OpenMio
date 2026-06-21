package me.mioclient.module.client;

import java.awt.Color;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_1176;
import me.mioclient.enum_.Priority;
import me.mioclient.event.Event_15;
import me.mioclient.event.Event_45;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_1222;
import me.mioclient.internal.ChatUtil;
import me.mioclient.internal.TextBuilder;
import me.mioclient.internal.Class_1328;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.network.message.ChatVisibility;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import nick.Settings;
import org.apache.commons.io.FileUtils;

public class NotificationsModule extends Module {
   public static IRCModule field_906 = Hub.field_2595.method_78(IRCModule.class);
   public Setting<Color> field_2159;
   public Setting<Color> field_2160;
   public Setting<Boolean> field_2161;
   public Setting<Boolean> field_2162;
   public Setting<Boolean> field_2163;
   public Setting<Color> field_2164;
   public Setting<Color> field_2165;
   public Setting<Boolean> field_2166;
   public Setting<String> field_2167;
   public Setting<Float> field_2168;
   public Setting<Boolean> field_2169;
   public Setting<Boolean> field_2170;
   public int field_2171;
   public final List<String> field_2172;
   public final Random field_2173;

   public NotificationsModule() {
      super("Notifications", "Lets you know about what's going on in chat.", Category.CLIENT);
      Settings.initialize(this);
      this.field_2171 = 0;
      this.field_2172 = new ArrayList<>();
      this.field_2173 = new Random();
      this.setDrawn(false);
      this.field_2166.method_9(() -> {
         if (this.field_2166.getValue()) {
            this.method_674();
         }
      });
      this.field_2170.method_9(() -> {
         if (this.field_2170.getValue()) {
            this.field_2170.method_78(false);
            this.method_674();
         }
      });
      this.field_2169.method_9(() -> this.field_2171 = 0);
      this.field_2167.method_9(this::method_674);
   }

   @Override
   public void onEnable() {
      this.method_674();
   }

   @Subscribe
   public void method_2(Event_15 var1) {
      boolean var2 = field_4219.player == var1.method_1048();
      if (this.field_2162.getValue() && (!var2 || this.field_2163.getValue())) {
         String var3 = var2 ? "You" : var1.getName();
         int var4 = var1.method_1049();
         if (var1.method_1050() == Class_1176.TOTEM_POP) {
            String var5 = var2 ? " have" : " has";
            this.method_2(var3, new TextBuilder().method_2((Object)var5).method_9("\u0001 popped "), var4);
            if (!Hub.field_2603.method_1009(var3) && !var2 && this.field_2166.getValue() && !this.field_2172.isEmpty()) {
               field_4221.submit(() -> {
                  try {
                     if (this.field_2168.getValue() > 0.0F) {
                        Thread.sleep((long)(this.field_2168.getValue() * Float.intBitsToFloat(1148846080)) + this.field_2173.nextLong(500L));
                     }

                     String var3x = null;
                     if (this.field_2169.getValue()) {
                        if (this.field_2172.size() == 1) {
                           var3x = this.field_2172.get(0);
                        } else {
                           while (var3x == null) {
                              int var4x = this.field_2173.nextInt(this.field_2172.size());
                              if (this.field_2171 != var4x) {
                                 this.field_2171 = var4x;
                                 var3x = this.field_2172.get(var4x);
                              }
                           }
                        }
                     } else {
                        var3x = this.field_2172.get(this.field_2171);
                        this.field_2171 = (this.field_2171 + 1) % this.field_2172.size();
                     }

                     var3x = var3x.replace("{name}", var3);
                     var3x = var3x.replace("{totems}", Integer.toString(var4));
                     if (!field_906.isToggled() || !var3x.startsWith(field_906.field_567.getValue())) {
                        ChatUtil.method_425(var3x);
                     }
                  } catch (Exception var5x) {
                  }
               });
            }
         } else {
            this.method_2(var3, " has died after popping ", var4);
         }
      }
   }

   @Subscribe
   public void method_2(Event_45 var1) {
      if (!this.method_535() && this.field_2161.getValue() && !(var1.method_65() instanceof HUDModule)) {
         MutableText var2 = Text.empty();
         if (var1.method_65().isToggled()) {
            var2.append(Text.literal("[").styled(var1x -> var1x.withColor(this.method_263(true).darker().hashCode())));
            var2.append(Text.literal("+").styled(var1x -> var1x.withColor(this.method_263(true).hashCode())));
            var2.append(Text.literal("] ").styled(var1x -> var1x.withColor(this.method_263(true).darker().hashCode())));
         } else {
            var2.append(Text.literal("[").styled(var1x -> var1x.withColor(this.method_263(false).darker().hashCode())));
            var2.append(Text.literal("-").styled(var1x -> var1x.withColor(this.method_263(false).hashCode())));
            var2.append(Text.literal("] ").styled(var1x -> var1x.withColor(this.method_263(false).darker().hashCode())));
         }

         var2.append(Text.literal(Hub.field_2626.method_7(var1.method_65())));
         Hub.field_2619.method_2(() -> ChatUtil.method_2(var2, ChatUtil.method_2(var1.method_65())), 0);
      }
   }

   public String method_8(int var1) {
      return var1 == 1 ? " totem." : " totems.";
   }

   public void method_2(String var1, String var2, int var3) {
      if (field_4219.options.getChatVisibility().getValue() != ChatVisibility.HIDDEN) {
         MutableText var4 = Text.empty()
            .append(Text.literal(var1).styled(var1x -> var1x.withColor((this.field_2164.getValue() != null ? this.field_2164.getValue().hashCode() : 0))))
            .append(Text.literal(var2).styled(var1x -> var1x.withColor((this.field_2165.getValue() != null ? this.field_2165.getValue().hashCode() : 0))))
            .append(Text.literal(String.valueOf(var3)).styled(var1x -> var1x.withColor((this.field_2164.getValue() != null ? this.field_2164.getValue().hashCode() : 0))))
            .append(Text.literal(this.method_8(var3)).styled(var1x -> var1x.withColor((this.field_2165.getValue() != null ? this.field_2165.getValue().hashCode() : 0))));
         ChatUtil.method_2(var4, ChatUtil.method_38(Math.abs(var1.hashCode()) * -1), Priority.MID);
      }
   }

   public Color method_263(boolean var1) {
      return var1 ? new Color(0, 190, 50) : new Color(200, 0, 0);
   }

   public Color method_672() {
      return this.field_2159.getValue();
   }

   public Color method_673() {
      return this.field_2160.getValue();
   }

   public void method_674() {
      try {
         Path var1 = Class_1222.method_2(Class_1328.field_4281.resolve(this.field_2167.getValue()), new String[]{".txt"});
         if (!var1.toFile().exists()) {
            throw new RuntimeException("Invalid totem pop file path");
         }

         this.field_2171 = 0;
         this.field_2172.clear();
         this.field_2172.addAll(FileUtils.readLines(var1.toFile(), StandardCharsets.UTF_8));
      } catch (Exception var2) {
         if (this.field_2166.getValue()) {
            ChatUtil.method_2(
               Text.literal("Failed to open totem pop file").styled(var0 -> var0.withColor(Formatting.RED)),
               ChatUtil.method_38(var2.toString().hashCode()),
               Priority.LOW
            );
            var2.printStackTrace();
         }
      }
   }
}
