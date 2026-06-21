package me.mioclient.module.render;

import java.awt.Color;
import me.mioclient.Hub;
import me.mioclient.event.Event_3;
import me.mioclient.event.Event_6;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0612;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.exploit.ReachModule;
import me.mioclient.setting.Setting;
import net.minecraft.block.AirBlock;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.BlockItem;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import nick.Settings;

public class HighlightModule extends Module {
   public static final ReachModule reach = Hub.field_2595.method_78(ReachModule.class);
   public Setting<Boolean> field_122;
   public Setting<Boolean> field_123;
   public Setting<Float> field_124;
   public Setting<Color> field_125;
   public Setting<Color> field_126;

   public HighlightModule() {
      super("Highlight", "Highlights the block you're looking at.", Category.RENDER, "blockhighlight");
      Settings.initialize(this);
      this.setDrawn(false);
      this.field_123.method_5(var0 -> reach.isToggled() && (reach.field_3337.getValue() || reach.field_3340.getValue()));
   }

   @Subscribe
   public void method_2(Event_6 var1) {
      if (!this.method_75()) {
         var1.method_463();
      }
   }

   @Subscribe
   public void method_9(Event_3 var1) {
      if (!this.method_535()) {
         BlockPos var2 = this.method_73();
         if (var2 != null) {
            BlockState var3 = field_4219.world.getBlockState(var2);
            if (this.method_74() || !(var3.getBlock() instanceof AirBlock)) {
               VoxelShape var4 = var3.getOutlineShape(field_4219.world, var2);
               if (var4.isEmpty()) {
                  if (this.method_74()) {
                     this.method_2(var1.method_10(), VoxelShapes.fullCube().getBoundingBox(), var2);
                  }
               } else if (!this.method_75()) {
                  if (this.field_122.getValue()) {
                     for (Box var6 : var4.getBoundingBoxes()) {
                        this.method_2(var1.method_10(), var6, var2);
                     }
                  } else {
                     this.method_2(var1.method_10(), var4.getBoundingBox(), var2);
                  }
               }
            }
         }
      }
   }

   public BlockPos method_73() {
      if (reach.isToggled() && reach.field_3338.getValue()) {
         BlockHitResult var1 = reach.method_961();
         if (var1 != null && var1.getType() != Type.MISS && var1.getBlockPos() != null) {
            return reach.method_961().getBlockPos();
         }
      }

      if (!this.method_74()) {
         if (field_4219.crosshairTarget instanceof BlockHitResult var4 && var4.getType() != Type.MISS) {
            return var4.getBlockPos();
         }

         return null;
      } else {
         return ReachModule.method_960() instanceof BlockHitResult var2 ? var2.getBlockPos() : null;
      }
   }

   public void method_2(MatrixStack var1, Box var2, BlockPos var3) {
      Class_0612.method_5(var1, var2.offset(var3).expand(Double.longBitsToDouble(4566758108544739836L)), this.field_126.getValue());
      Class_0612.method_2(var1, var2.offset(var3).expand(Double.longBitsToDouble(4566758108544739836L)), this.field_125.getValue(), this.field_124.getValue());
   }

   public boolean method_74() {
      return reach.isToggled()
         && reach.field_3340.getValue()
         && (field_4219.player.getMainHandStack().getItem() instanceof BlockItem || field_4219.player.getOffHandStack().getItem() instanceof BlockItem);
   }

   public boolean method_75() {
      return this.field_123.getValue() && this.field_123.method_176();
   }
}
