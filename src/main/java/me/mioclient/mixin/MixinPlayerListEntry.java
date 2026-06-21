package me.mioclient.mixin;

import com.mojang.authlib.GameProfile;
import me.mioclient.api.Class_0822;
import net.minecraft.client.network.PlayerListEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({PlayerListEntry.class})
public class MixinPlayerListEntry implements Class_0822 {
   @Unique
   private long mio$joinTime;

   public MixinPlayerListEntry() {
      super();
   }

   @Inject(
      method = {"<init>(Lcom/mojang/authlib/GameProfile;Z)V"},
      at = {@At("TAIL")}
   )
   private void mio$init(GameProfile var1, boolean var2, CallbackInfo var3) {
      this.mio$joinTime = System.currentTimeMillis();
   }

   @Override
   public long mio$getJoinTime() {
      return this.mio$joinTime;
   }
}
