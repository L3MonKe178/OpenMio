package me.mioclient.module.render;

import java.util.Set;
import me.mioclient.api.Class_0718;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import nick.Settings;

public class XrayModule extends Module {
   public static XrayModule field_2913;
   public Setting<Boolean> field_2914;
   public Setting<Set<Block>> field_2915;
   public double field_217;

   public XrayModule() {
      super("Xray", "Wallhack on ores.", Category.RENDER);
      Settings.initialize(this);
      field_2913 = this;
      this.field_2915.method_9(this::method_534);
      this.field_2914.method_9(this::method_534);
   }

   @Override
   public void onEnable() {
      this.field_217 = (Double)field_4219.options.getGamma().getValue();
      ((Class_0718)(Object)field_4219.options.getGamma()).forceSetValue(Double.longBitsToDouble(4652007308841189376L));
   }

   @Override
   public void onDisable() {
      ((Class_0718)(Object)field_4219.options.getGamma()).forceSetValue(this.field_217);
   }

   @Override
   public void onToggle() {
      this.method_534();
   }

   public boolean method_2(BlockPos var1, Block var2) {
      return var2 != null && var1 != null && !this.method_535()
         ? this.field_2915.getValue().contains(var2) && (!this.field_2914.getValue() || this.method_391(var1))
         : false;
   }

   public boolean method_391(BlockPos var1) {
      for (Direction var5 : Direction.values()) {
         if (!field_4219.world.getBlockState(var1.offset(var5)).isOpaque()) {
            return true;
         }
      }

      return false;
   }

   public void method_534() {
      if (field_4219.world != null) {
         field_4219.worldRenderer.reload();
      }
   }

   public static XrayModule method_844() {
      return field_2913;
   }
}
