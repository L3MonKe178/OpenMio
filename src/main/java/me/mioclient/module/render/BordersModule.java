package me.mioclient.module.render;

import java.awt.Color;
import me.mioclient.enum_.SettingMode;
import me.mioclient.event.Event_3;
import me.mioclient.event.Subscribe;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.record.Class_1030;
import me.mioclient.setting.Setting;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import nick.Settings;

public class BordersModule extends Module {
   public Setting<Color> field_617;
   public Setting<Color> field_618;
   public Setting<Float> field_619;
   public Setting<Integer> field_620;
   public Setting<Integer> field_621;

   public BordersModule() {
      super("Borders", "Shows region borders on your screen.", Category.RENDER);
      Settings.initialize(this);
      this.field_621.method_2("~", SettingMode.MIN);
   }

   @Subscribe
   public void method_9(Event_3 var1) {
      if ((this.field_617.getValue() != null ? this.field_617.getValue().getAlpha() : 255) != 0) {
         this.method_255().method_2(var1.method_10(), this.field_617.getValue(), this.field_621.getValue(), this.field_619.getValue());
      }

      if ((this.field_618.getValue() != null ? this.field_618.getValue().getAlpha() : 255) != 0) {
         this.method_256().method_2(var1.method_10(), this.field_618.getValue(), this.field_621.getValue(), this.field_619.getValue());
      }
   }

   public Class_1030 method_255() {
      ChunkPos var1 = field_4219.player.getChunkPos();
      return new Class_1030(var1.getCenterX(), var1.getCenterZ(), 16);
   }

   public Class_1030 method_256() {
      int var1 = 128 * (1 << this.field_620.getValue());
      int var2 = MathHelper.floor(((double)field_4219.player.getBlockX() + Double.longBitsToDouble(4634204016564240384L)) / (double)var1);
      int var3 = MathHelper.floor(((double)field_4219.player.getBlockZ() + Double.longBitsToDouble(4634204016564240384L)) / (double)var1);
      int var4 = var2 * var1 + var1 / 2 - 64;
      int var5 = var3 * var1 + var1 / 2 - 64;
      return new Class_1030(var4, var5, var1);
   }
}
