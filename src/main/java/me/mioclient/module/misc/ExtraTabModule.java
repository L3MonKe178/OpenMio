package me.mioclient.module.misc;

import java.awt.Color;
import java.util.Comparator;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_0304;
import me.mioclient.enum_.Class_0829;
import me.mioclient.enum_.Class_1018;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.Nullables;
import net.minecraft.world.GameMode;
import nick.Settings;

public class ExtraTabModule extends Module {
   public Setting<Class_0829> field_69;
   public Setting<Float> field_70;
   public Setting<Boolean> field_71;
   public Setting<Boolean> field_72;
   public Setting<Color> field_73;
   public Setting<Boolean> field_74;
   public Setting<Boolean> field_75;
   public Setting<Class_1018> field_76;
   public Setting<Boolean> field_77;

   public ExtraTabModule() {
      super("ExtraTab", "Allows you to customize your TAB-screen.", Category.MISC);
      Settings.initialize(this);
      this.setDrawn(false);
   }

   public float method_54() {
      return !this.isToggled() ? Float.intBitsToFloat(1065353216) : this.field_70.getValue();
   }

   public int method_2(PlayerListEntry var1) {
      return switch ((Class_1018)this.field_76.getValue()) {
         case ALPHABET -> 0;
         case SOCIALS -> {
            Class_0304 var2 = Hub.field_2603.method_253(var1.getProfile().getName());
            byte var3 = 0;
            if (var2 == Class_0304.FRIEND) {
               var3 = -1;
            }

            if (var2 == Class_0304.ENEMY) {
               var3 = -2;
            }

            yield var3;
         }
         case LATENCY -> -var1.getLatency();
         case LENGTH -> -field_4219.textRenderer.getWidth(var1.getProfile().getName());
      };
   }

   public Comparator<PlayerListEntry> method_55() {
      Comparator var1 = Comparator.<PlayerListEntry>comparingInt(var1x -> {
            int var2 = var1x.getGameMode() == GameMode.SPECTATOR ? 1 : 0;
            if (this.field_77.getValue()) {
               var2 *= -1;
            }

            return var2;
         })
         .thenComparing(var0 -> (String)Nullables.mapOrElse(var0.getScoreboardTeam(), Team::getName, ""))
         .thenComparing(var1x -> this.method_2(var1x))
         .thenComparing(var0 -> var0.getProfile().getName(), String::compareToIgnoreCase);
      if (this.field_77.getValue()) {
         var1 = var1.reversed();
      }

      return var1;
   }
}
