package me.mioclient.mixin;

import com.mojang.authlib.GameProfile;
import me.mioclient.Hub;
import me.mioclient.module.client.IRCModule;
import me.mioclient.module.player.NameProtectModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.util.SkinTextures;
import net.minecraft.client.util.SkinTextures.Model;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({AbstractClientPlayerEntity.class})
public abstract class MixinAbstractClientPlayerEntity extends PlayerEntity {
   private static Identifier PEPSI = Identifier.of("mio", "capes/pepsi.png");
   private static Identifier TETRIS = Identifier.of("mio", "capes/tetris.png");
   private static Identifier HEROBRINE = Identifier.of("mio-mount", "textures/skin_protect.png");
   private static final IRCModule irc = Hub.field_2595.method_78(IRCModule.class);
   private static final NameProtectModule nameprotect = Hub.field_2595.method_78(NameProtectModule.class);

   public MixinAbstractClientPlayerEntity(World var1, BlockPos var2, float var3, GameProfile var4) {
      super(var1, var2, var3, var4);
   }

   @Inject(
      method = {"getSkinTextures"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void getCape(CallbackInfoReturnable<SkinTextures> var1) {
      SkinTextures var2 = (SkinTextures)var1.getReturnValue();
      Identifier var3 = var2.texture();
      Model var4 = var2.model();
      if (nameprotect.isToggled() && nameprotect.field_1852.getValue() && MinecraftClient.getInstance().player.equals(this)) {
         var3 = HEROBRINE;
         var4 = Model.WIDE;
         if (nameprotect.field_1853.getValue()) {
            var4 = Model.SLIM;
         }

         var1.setReturnValue(new SkinTextures(var3, var2.textureUrl(), var2.capeTexture(), var2.elytraTexture(), var4, var2.secure()));
      }

      if ("cattyyyn".equals(this.getGameProfile().getName())) {
         var1.setReturnValue(new SkinTextures(var3, var2.textureUrl(), PEPSI, var2.elytraTexture(), var4, var2.secure()));
      }

      if ("u3o".equals(this.getGameProfile().getName())) {
         var1.setReturnValue(new SkinTextures(var3, var2.textureUrl(), TETRIS, var2.elytraTexture(), var4, var2.secure()));
      }

      if (Hub.field_2610.method_326() && irc.field_564.getValue()) {
         Identifier var5 = Hub.field_2610.method_68(this.getGameProfile().getName());
         if (var5 != null) {
            var1.setReturnValue(new SkinTextures(var3, var2.textureUrl(), var5, var2.elytraTexture(), var4, var2.secure()));
            var1.cancel();
         }
      }
   }
}
