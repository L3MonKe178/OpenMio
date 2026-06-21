package me.mioclient.internal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;
import me.mioclient.api.Class_1309;
import me.mioclient.event.Event_4;
import me.mioclient.event.Event_5;
import me.mioclient.event.Subscribe;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.network.packet.s2c.play.BlockUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.ChunkDeltaUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;
import net.minecraft.network.packet.s2c.play.UnloadChunkS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.chunk.WorldChunk;

public class Class_0032 implements Class_1309 {
   public final int field_60;
   public final BiConsumer<ChunkPos, WorldChunk> field_61;
   public final BiConsumer<ChunkPos, WorldChunk> field_62;
   public final BiConsumer<BlockPos, BlockState> field_63;
   public ExecutorService field_64;

   public Class_0032(int var1, BiConsumer<ChunkPos, WorldChunk> var2, BiConsumer<ChunkPos, WorldChunk> var3, BiConsumer<BlockPos, BlockState> var4) {
      super();
      this.field_60 = var1;
      this.field_61 = var2;
      this.field_62 = var3;
      this.field_63 = var4;
   }

   public void method_47() {
      this.field_64 = Executors.newFixedThreadPool(Math.max(this.field_60, 1));
      field_4220.method_14(this);
   }

   public void method_48() {
      field_4220.method_17(this);
      this.field_64.shutdownNow();
      this.field_64 = null;
   }

   public void method_49() {
      this.field_64.shutdownNow();
      this.field_64 = Executors.newFixedThreadPool(Math.max(this.field_60, 1));
   }

   public void method_50() {
      if (this.field_61 != null) {
         for (WorldChunk var2 : Class_1225.method_1069()) {
            this.method_30(() -> this.field_61.accept(var2.getPos(), var2));
         }
      }
   }

   @Subscribe
   public void method_9(Event_4 var1) {
      if (field_4219.world != null) {
         if (this.field_63 != null && var1.method_127() instanceof BlockUpdateS2CPacket var2) {
            this.method_30(() -> this.field_63.accept(var2.getPos(), var2.getState()));
         } else if (this.field_63 != null && var1.method_127() instanceof ExplosionS2CPacket var3) {
            for (BlockPos var13 : var3.getAffectedBlocks()) {
               this.method_30(() -> this.field_63.accept(var13, Blocks.AIR.getDefaultState()));
            }
         } else if (this.field_63 != null && var1.method_127() instanceof ChunkDeltaUpdateS2CPacket var4) {
            var4.visitUpdates((var1x, var2x) -> {
               BlockPos var3x = var1x.toImmutable();
               this.method_30(() -> this.field_63.accept(var3x, var2x));
            });
         } else if (this.field_62 != null && var1.method_127() instanceof UnloadChunkS2CPacket var5) {
            ChunkPos var11 = new ChunkPos(var5.pos().x, var5.pos().z);
            WorldChunk var7 = field_4219.world.getChunk(var11.x, var11.z);
            this.method_30(() -> this.field_62.accept(var11, var7));
         }
      }
   }

   @Subscribe
   public void method_2(Event_5 var1) {
      if (this.field_61 != null) {
         this.method_30(() -> this.field_61.accept(var1.method_1021().getPos(), var1.method_1021()));
      }
   }

   public ExecutorService method_51() {
      return this.field_64;
   }

   public void method_30(Runnable var1) {
      if (this.field_60 == 0) {
         field_4219.executeSync(var1);
      } else {
         this.field_64.execute(var1);
      }
   }
}
