package me.mioclient.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import java.util.function.Consumer;
import me.mioclient.api.MioAPI;
import me.mioclient.event.Event_5;
import net.minecraft.client.world.ClientChunkManager;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.play.ChunkData.BlockEntityVisitor;
import net.minecraft.world.chunk.WorldChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({ClientChunkManager.class})
public class MixinClientChunkManager {
   public MixinClientChunkManager() {
      super();
   }

   @Inject(
      method = {"loadChunkFromPacket"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/world/ClientWorld;resetChunkColor(Lnet/minecraft/util/math/ChunkPos;)V",
         shift = Shift.BEFORE
      )}
   )
   private void loadChunkFromPacketHook(
      int var1,
      int var2,
      PacketByteBuf var3,
      NbtCompound var4,
      Consumer<BlockEntityVisitor> var5,
      CallbackInfoReturnable<WorldChunk> var6,
      @Local WorldChunk var7
   ) {
      MioAPI.field_4220.method_36(new Event_5(var7));
   }
}
