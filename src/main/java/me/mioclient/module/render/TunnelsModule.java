package me.mioclient.module.render;

import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.awt.Color;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_3;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0032;
import me.mioclient.internal.Timer;
import me.mioclient.internal.RotationManager;
import me.mioclient.internal.Class_0612;
import me.mioclient.internal.Class_0919;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.util.math.Direction.Type;
import nick.Settings;

public class TunnelsModule extends Module {
   public Setting<Boolean> field_381;
   public Setting<Float> field_382;
   public Setting<Float> field_383;
   public Setting<Integer> field_384;
   public Setting<Color> field_385;
   public Setting<Color> field_386;
   public static final Mutable field_387 = new Mutable();
   public final Timer field_388;
   public final List<Class_0919> field_389;
   public final Queue<Class_0919> field_390;
   public final ObjectSet<Class_0919> field_391;
   public final Class_0032 field_392;

   public TunnelsModule() {
      super("Tunnels", "Highlights dug-out tunnels.", Category.RENDER);
      Settings.initialize(this);
      this.field_388 = new Timer();
      this.field_389 = Collections.synchronizedList(new ArrayList<>());
      this.field_390 = new ArrayDeque<>();
      this.field_391 = new ObjectOpenHashSet();
      this.field_392 = new Class_0032(
         1,
         (var1, var2) -> this.method_7(var1),
         (var1, var2) -> this.field_389.removeIf(var2x -> this.method_2(BlockPos.ofFloored(var2x.field_1887.getCenter()), var1)),
         null
      );
   }

   @Override
   public void onEnable() {
      this.field_392.method_47();
      if (!this.method_535()) {
         this.field_392.method_50();
      }
   }

   @Override
   public void onDisable() {
      this.field_389.clear();
      this.field_392.method_48();
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if (!this.method_535()) {
         synchronized (this.field_389) {
            boolean var3 = !this.field_390.isEmpty();
            if (var3 && this.field_388.method_9(1000L)) {
               this.field_388.reset();
               field_4221.submit(() -> this.method_30(new ArrayList<>(this.field_389)));
            }

            this.field_389
               .removeIf(
                  var0 -> Math.sqrt(field_4219.player.getEyePos().squaredDistanceTo(var0.field_1887.getCenter()))
                        > Double.longBitsToDouble(4643211215818981376L)
               );
         }
      }
   }

   @Subscribe
   public void method_9(Event_3 var1) {
      if (!this.method_535()) {
         synchronized (this.field_389) {
            for (Class_0919 var4 : this.field_389) {
               if (!(var4.method_832() < (double)(this.field_384.getValue() != null ? this.field_384.getValue().intValue() : 0))) {
                  float var5 = this.field_383.getValue();
                  if (var4.field_2899) {
                     var5 = (float)var4.field_1887.getLengthY();
                  }

                  Box var6 = var4.field_1887.withMaxY(var4.field_1887.minY + (double)var5);
                  if (RotationManager.method_4(var6)) {
                     Class_0612.method_5(var1.method_10(), var6, this.field_385.getValue());
                     Class_0612.method_2(var1.method_10(), var6, this.field_386.getValue(), this.field_382.getValue());
                  }
               }
            }
         }
      }
   }

   public void method_30(List<Class_0919> var1) {
      while (true) {
         if (!this.field_390.isEmpty()) {
            Class_0919 var2 = this.field_390.poll();
            if (var2 != null) {
               boolean var12 = false;

               for (Class_0919 var14 : var1) {
                  if (var14.method_9(var2)) {
                     var14.method_833(BlockPos.ofFloored(var2.field_1887.getCenter()));
                     var12 = true;
                     break;
                  }
               }

               if (!var12) {
                  var1.add(var2);
               }
               continue;
            }
         }

         for (int var8 = 0; var8 < var1.size(); var8++) {
            for (int var3 = var8; var3 < var1.size(); var3++) {
               Class_0919 var4 = (Class_0919)var1.get(var8);
               Class_0919 var5 = (Class_0919)var1.get(var3);
               if (var4.method_9(var5)) {
                  this.field_391.add(var5);
               }
            }
         }

         ObjectIterator var9 = this.field_391.iterator();

         while (var9.hasNext()) {
            Class_0919 var11 = (Class_0919)var9.next();
            var1.remove(var11);
         }

         this.field_391.clear();
         synchronized (this.field_389) {
            this.field_389.clear();
            this.field_389.addAll(var1);
            var1.clear();
            return;
         }
      }
   }

   public void method_7(ChunkPos var1) {
      for (int var2 = 0; var2 < 16; var2++) {
         for (int var3 = field_4219.world.getBottomY(); var3 < field_4219.world.getTopY(); var3++) {
            for (int var4 = 0; var4 < 16; var4++) {
               field_387.set(var1.getStartX() + var2, var3, var1.getStartZ() + var4);
               if (this.method_152(field_387)) {
                  boolean var5 = this.method_151(field_387);
                  if (var5 || this.method_150(field_387)) {
                     if (var5) {
                        this.field_390.add(new Class_0919(field_387, true));
                     } else {
                        for (Direction var7 : Type.HORIZONTAL) {
                           BlockPos var8 = field_387.offset(var7);
                           if (this.method_150(var8)) {
                              this.field_390.add(new Class_0919(field_387, false));
                              break;
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public boolean method_150(BlockPos var1) {
      if (!this.method_152(var1.up()) || this.method_152(var1.down()) || this.method_152(var1.up().up())) {
         return false;
      } else if (this.method_152(var1.north()) && this.method_152(var1.south()) && this.method_152(var1.up().north()) && this.method_152(var1.up().south())) {
         return !this.method_152(var1.east()) && !this.method_152(var1.west()) && !this.method_152(var1.up().east()) && !this.method_152(var1.up().west());
      } else {
         return this.method_152(var1.east()) && this.method_152(var1.west()) && this.method_152(var1.up().east()) && this.method_152(var1.up().west())
            ? !this.method_152(var1.north()) && !this.method_152(var1.south()) && !this.method_152(var1.up().north()) && !this.method_152(var1.up().south())
            : false;
      }
   }

   public boolean method_151(BlockPos var1) {
      if (!this.field_381.getValue()) {
         return false;
      } else {
         for (Direction var3 : Type.HORIZONTAL) {
            BlockPos var4 = var1.offset(var3);
            BlockState var5 = field_4219.world.getBlockState(var4);
            if (!var5.isSolid() || var5.isOf(Blocks.BAMBOO_BLOCK) || var5.isOf(Blocks.BASALT)) {
               return false;
            }
         }

         for (Direction var7 : Type.VERTICAL) {
            BlockPos var8 = var1.offset(var7);
            if (!this.method_152(var8) || !this.method_152(var8.offset(var7))) {
               return false;
            }
         }

         return true;
      }
   }

   public boolean method_2(BlockPos var1, ChunkPos var2) {
      return var1.getX() >= var2.getStartX() && var1.getX() <= var2.getEndX() && var1.getZ() >= var2.getStartZ() && var1.getZ() <= var2.getEndZ();
   }

   public boolean method_152(BlockPos var1) {
      return !field_4219.world.getBlockState(var1).isSolid();
   }
}
