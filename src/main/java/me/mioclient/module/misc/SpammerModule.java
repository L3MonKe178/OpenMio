package me.mioclient.module.misc;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import me.mioclient.Hub;
import me.mioclient.enum_.Priority;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_10;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Timer;
import me.mioclient.internal.Class_1222;
import me.mioclient.internal.ChatUtil;
import me.mioclient.internal.Class_1328;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.client.IRCModule;
import me.mioclient.setting.Setting;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import nick.Settings;
import org.apache.commons.io.FileUtils;

public class SpammerModule extends Module {
   public Setting<String> field_1824;
   public Setting<Float> field_1825;
   public Setting<Boolean> field_1826;
   public Setting<Boolean> field_1827;
   public Setting<Boolean> field_1828;
   public Setting<Boolean> field_1829;
   public static IRCModule field_906 = Hub.field_2595.method_78(IRCModule.class);
   public final Random field_1830;
   public final List<String> field_1831;
   public final Timer field_1832;
   public int current;

   public SpammerModule() {
      super("Spammer", "Spams messages from a selected text file.", Category.MISC);
      Settings.initialize(this);
      this.field_1830 = new Random();
      this.field_1831 = new ArrayList<>();
      this.field_1832 = new Timer();
      this.current = 0;
      this.field_1824.method_9(this::method_142);
      this.field_1826.method_9(() -> {
         if (this.field_1826.getValue()) {
            this.field_1826.method_78(false);
            this.method_142();
         }
      });
   }

   @Override
   public void onEnable() {
      this.method_142();
   }

   @Subscribe
   public void method_2(Event_10 var1) {
      if (var1.method_127() instanceof ChatMessageC2SPacket && this.field_1827.getValue()) {
         this.field_1832.reset();
      }
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if (this.field_1832.method_2((double)(this.field_1825.getValue() != null ? this.field_1825.getValue().floatValue() : 0.0f), TimeUnit.SECONDS) && !this.field_1831.isEmpty()) {
         String var2 = this.field_1831
            .get(this.field_1828.getValue() && this.field_1831.size() > 1 ? this.field_1830.nextInt(this.field_1831.size() - 1) : this.current);
         if (!field_906.isToggled() || !var2.startsWith(field_906.field_567.getValue())) {
            ChatUtil.method_425(var2);
         }

         this.current = (this.current + 1) % this.field_1831.size();
         this.field_1832.reset();
         if (this.field_1829.getValue()) {
            this.method_68();
         }
      }
   }

   public void method_142() {
      try {
         Path var1 = Class_1222.method_2(Class_1328.field_4283.resolve(this.field_1824.getValue()), new String[]{".txt"});
         if (!var1.toFile().exists()) {
            throw new RuntimeException("Invalid spammer file path");
         }

         this.current = 0;
         this.field_1831.clear();
         this.field_1831.addAll(FileUtils.readLines(var1.toFile(), StandardCharsets.UTF_8));
      } catch (Exception var2) {
         if (!this.method_535()) {
            ChatUtil.method_2(
               Text.literal("Failed to open spammer file").styled(var0 -> var0.withColor(Formatting.RED)),
               ChatUtil.method_38(var2.toString().hashCode()),
               Priority.LOW
            );
         }
      }
   }
}
