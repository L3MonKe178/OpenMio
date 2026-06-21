package me.mioclient.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import me.mioclient.Hub;
import me.mioclient.api.MioAPI;
import me.mioclient.enum_.Class_0692;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_4;
import me.mioclient.event.Subscribe;
import me.mioclient.module.player.SpeedMineModule;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.BlockBreakingProgressS2CPacket;
import net.minecraft.network.packet.s2c.play.BlockUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.ChunkDeltaUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;

public final class Class_1094 implements MioAPI {
   public static final SpeedMineModule speedmine = Hub.field_2595.method_78(SpeedMineModule.class);
   public static ItemStack field_3403;
   public final List<Class_0421> field_3404 = Collections.synchronizedList(new ArrayList<>());

   public Class_1094() {
      super();
      field_4220.method_14(this);
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      this.field_3404.removeIf(Class_0421::method_462);

      for (Class_0421 var3 : this.field_3404) {
         BlockState var4 = field_4219.world.getBlockState(var3.method_111());
         float var5 = (float)Class_1225.method_2(method_975(), var4, true) * Hub.field_2602.method_990();
         if (speedmine.isToggled()) {
            var5 /= speedmine.field_3988.getValue();
         }

         if (Class_1225.method_3(var3.method_111())) {
            var5 *= (float)Math.max(var3.method_165(), 1);
            var3.method_460(0);
         } else {
            var5 = 0.0F;
            var3.method_460(var3.method_165() + 1);
            var3.method_461();
         }

         float var6 = var3.method_200() + var5;
         var3.method_263(var6);
      }
   }

   @Subscribe
   public void method_5(Event_4 var1) {
      if (var1.method_127() instanceof BlockBreakingProgressS2CPacket var2) {
         field_4219.execute(() -> {
            if (var2.getProgress() >= 0 && var2.getProgress() <= 10) {
               this.method_9(var2.getPos(), var2.getEntityId());
            }
         });
      }

      if (var1.method_127() instanceof BlockUpdateS2CPacket var4 && var4.getState().isAir()) {
         this.method_230(var4.getPos());
      }

      if (var1.method_127() instanceof ChunkDeltaUpdateS2CPacket var5) {
         var5.visitUpdates((var1x, var2x) -> {
            if (var2x.isAir()) {
               this.method_230(var1x);
            }
         });
      }
   }

   public void method_230(BlockPos var1) {
      if (speedmine.field_3994.getValue() != Class_0692.INSTANT) {
         field_4219.execute(() -> {
            for (Class_0421 var3 : this.field_3404) {
               if (var1.equals(var3.method_111())) {
                  var3.method_263(0.0F);
                  var3.method_460(0);
               }
            }
         });
      }
   }

   public void method_9(BlockPos var1, int var2) {
      boolean var3 = false;

      for (Class_0421 var5 : this.field_3404) {
         if (var5.getEntityId() == var2 && var5.method_111().equals(var1)) {
            var3 = true;
            break;
         }
      }

      if (!var3) {
         this.field_3404.removeIf(var2x -> var2x.method_111().equals(var1) || var2x.getEntityId() == var2);
         if (Class_1225.method_3(var1)) {
            this.field_3404.add(new Class_0421(var2, var1));
         }
      }
   }

   public float method_251(BlockPos var1) {
      return !Class_1225.method_3(var1)
         ? 0.0F
         : this.field_3404.stream().filter(var1x -> var1x.method_111().equals(var1)).map(Class_0421::method_200).findAny().orElse(0.0F);
   }

   public List<Class_0421> method_974() {
      return this.field_3404;
   }

   public static ItemStack method_975() {
      if (field_3403 == null) {
         field_3403 = new ItemStack(Items.NETHERITE_PICKAXE);
         field_3403.addEnchantment(Class_0756.method_2(Enchantments.EFFICIENCY), 5);
      }

      return field_3403;
   }
}
