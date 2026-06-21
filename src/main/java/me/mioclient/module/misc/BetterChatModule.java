package me.mioclient.module.misc;

import java.awt.Color;
import java.util.Date;
import java.util.regex.Pattern;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_0341;
import me.mioclient.enum_.Orientation;
import me.mioclient.enum_.PreType;
import me.mioclient.enum_.SettingMode;
import me.mioclient.event.Event_10;
import me.mioclient.event.Event_24;
import me.mioclient.event.Event_26;
import me.mioclient.event.Event_46;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0211;
import me.mioclient.internal.Class_0585;
import me.mioclient.internal.ChatUtil;
import me.mioclient.internal.TextBuilder;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.network.packet.c2s.play.CommandExecutionC2SPacket;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import nick.Settings;

public class BetterChatModule extends Module {
   public static final Pattern field_3920 = Pattern.compile("^\\d+$");
   public static boolean field_3921 = false;
   public static boolean field_3922 = false;
   public static BetterChatModule field_3923;
   public static PMSoundModule field_3924 = Hub.field_2595.method_78(PMSoundModule.class);
   public Setting<Boolean> field_3925;
   public Setting<Boolean> field_3926;
   public Setting<Class_0341> field_3927;
   public Setting<Color> field_3928;
   public Setting<Color> field_3929;
   public Setting<Boolean> field_3930;
   public Setting<Integer> field_3931;
   public Setting<String> field_3932;
   public Setting<String> field_3933;
   public Setting<Boolean> field_3934;
   public Setting<Class_0211> field_3935;
   public Setting<Float> field_3936;
   public Setting<Boolean> field_3937;
   public Setting<Orientation> field_3938;
   public Setting<Float> field_3939;
   public Setting<Integer> field_3940;
   public Setting<Boolean> field_3941;
   public Setting<Boolean> field_3942;
   public Setting<String> field_3943;
   public Setting<Boolean> field_3944;
   public Setting<Color> field_3945;
   public Setting<Boolean> field_3946;
   public Setting<Boolean> field_3947;
   public final Class_0585 field_3948;
   public boolean field_3949;

   public BetterChatModule() {
      super("BetterChat", "Allows you to customize your chat as you please.", Category.MISC);
      Settings.initialize(this);
      this.field_3948 = new Class_0585(this.field_3939::getValue, true);
      this.field_3940.method_2("None", SettingMode.MIN);
      field_3923 = this;
      this.setDrawn(false);
      this.field_3945.method_31("HighlightColor");
   }

   @Subscribe
   public void method_2(Event_10 var1) {
      if (!var1.method_464()) {
         if (var1.method_127() instanceof ChatMessageC2SPacket || var1.method_127() instanceof CommandExecutionC2SPacket) {
            this.field_3949 = true;
         }
      }
   }

   @Subscribe
   public void method_9(Event_46 var1) {
      if (var1.method_213() == PreType.PRE) {
         this.method_7(var1);
         this.method_5(var1);
         this.method_1091();
         this.field_3949 = false;
      }
   }

   @Subscribe
   public void method_5(Event_24 var1) {
      String var2 = var1.method_219();
      if (!field_3920.matcher(var2).matches()) {
         if (this.field_3942.getValue()) {
            String var3 = new TextBuilder().method_2(this.field_3943.getValue()).method_9(" \u0001");
            int var4 = MathHelper.clamp(256 - var2.length(), 0, var3.length());
            var1.method_220(new TextBuilder().method_2(var3.substring(0, var4)).method_2((Object)var2).method_9("\u0001\u0001"));
         }
      }
   }

   @Subscribe
   public void method_2(Event_26 var1) {
      this.field_3948.method_3(false);
      this.field_3948.method_45();
   }

   public void method_5(Event_46 var1) {
      if (this.field_3925.getValue() && (this.method_2(var1.method_1036()) || !this.field_3926.getValue())) {
         MutableText var2 = Text.empty()
            .append(Text.literal(this.field_3932.getValue()).styled(var1x -> var1x.withColor((this.field_3929.getValue() != null ? this.field_3929.getValue().hashCode() : 0))))
            .append(Text.literal((this.field_3927.getValue() != null ? this.field_3927.getValue().method_2(new Date()) : "")).styled(var1x -> var1x.withColor((this.field_3928.getValue() != null ? this.field_3928.getValue().hashCode() : 0))))
            .append(Text.literal(this.field_3933.getValue()).styled(var1x -> var1x.withColor((this.field_3929.getValue() != null ? this.field_3929.getValue().hashCode() : 0))))
            .append(" ");
         if (this.field_3930.getValue()) {
            var2 = Text.empty().append(ChatUtil.method_2(var2.getString(), () -> this.field_3931.getValue() * 10, this.field_3928.getValue()));
         }

         var1.method_9(var2.append(var1.method_1033()));
      }
   }

   public void method_7(Event_46 var1) {
      if (this.field_3934.getValue()) {
         String var2 = var1.method_1033().getString().toLowerCase();
         if (!field_3924.isToggled() || !ChatUtil.method_107(var2)) {
            if (!this.field_3949 && var2.contains(field_4219.getSession().getUsername().toLowerCase())) {
               Hub.field_2606.method_2(this.field_3935.getValue()).method_230(this.field_3936.getValue());
            }
         }
      }
   }

   public void method_1091() {
      if (this.method_1092() && this.field_3938.getValue() == Orientation.BOUNCE) {
         this.field_3948.method_36(true);
      }
   }

   public boolean method_2(MessageIndicator var1) {
      return var1 == MessageIndicator.system() || var1 == MessageIndicator.singlePlayer() || var1 == MessageIndicator.notSecure();
   }

   public boolean method_1092() {
      return this.isToggled() && this.field_3937.getValue();
   }

   public static BetterChatModule method_1093() {
      return field_3923;
   }
}
