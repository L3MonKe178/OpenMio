package me.mioclient.internal;

import java.io.InputStream;
import java.util.concurrent.CompletableFuture;
import me.mioclient.api.MioAPI;
import net.minecraft.client.sound.AbstractSoundInstance;
import net.minecraft.client.sound.AudioStream;
import net.minecraft.client.sound.OggAudioStream;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.client.sound.SoundLoader;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

public class Class_0423 extends AbstractSoundInstance {
   public InputStream field_1338;

   public Class_0423(Vec3d var1, InputStream var2) {
      super(Identifier.of(MioAPI.method_244() ? "mioloader" : "mio", "sound"), SoundCategory.MASTER, SoundInstance.createRandom());
      this.x = var1.x;
      this.y = var1.y;
      this.z = var1.z;
      this.field_1338 = var2;
   }

   public Class_0423(InputStream var1) {
      super(Identifier.of(MioAPI.method_244() ? "mioloader" : "mio", "sound"), SoundCategory.MASTER, SoundInstance.createRandom());
      this.field_1338 = var1;
      this.relative = true;
      this.x = 0.0;
      this.y = 0.0;
      this.z = Double.longBitsToDouble(-4616189618054758400L);
   }

   public CompletableFuture<AudioStream> getAudioStream(SoundLoader var1, Identifier var2, boolean var3) {
      try {
         return CompletableFuture.completedFuture(new OggAudioStream(this.field_1338));
      } catch (Exception var5) {
         return CompletableFuture.failedFuture(var5);
      }
   }
}
