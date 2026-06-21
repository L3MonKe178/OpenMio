package me.mioclient.module.abstract_;

import me.mioclient.event.Event_36;
import me.mioclient.event.Event_44;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_1035;
import me.mioclient.internal.PacketUtil;
import me.mioclient.internal.TextBuilder;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import nick.Settings;

public class AbstractModule_10 extends Module {
   public Setting<Boolean> field_2756;
   public Setting<Boolean> field_2757;

   public AbstractModule_10() {
      super(
         "NoGlitchBlocks",
         new TextBuilder()
            .method_2(String.valueOf(Formatting.RED))
            .method_9("Makes sure there are no ghost blocks as you interact with them. \n\u0001Not recommended on 2b2t/Grim servers."),
         Category.PLAYER
      );
      Settings.initialize(this);
   }

   @Subscribe
   public void method_9(Event_36 var1) {
      Hand var2 = var1.method_12();
      if (this.field_2756.getValue() && field_4219.player.getStackInHand(var2).getItem() instanceof BlockItem var4) {
         BlockPos var5 = var1.method_382().getBlockPos();
         if (Class_1035.method_2(var5.offset(var1.method_382().getSide()), var4.getBlock(), false)) {
            PacketUtil.method_2(var1.method_12(), var1.method_382());
            field_4219.player.swingHand(var1.method_12());
            var1.method_463();
         }
      }
   }

   @Subscribe
   public void method_9(Event_44 var1) {
      if (this.field_2757.getValue()) {
         var1.method_463();
      }
   }
}
