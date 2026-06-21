package me.mioclient.mixin;

import me.mioclient.Hub;
import me.mioclient.module.player.NameProtectModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.TextVisitFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin({TextVisitFactory.class})
public class MixinTextVisitFactory {
   private static NameProtectModule nameprotect;

   public MixinTextVisitFactory() {
      super();
   }

   @ModifyArg(
      method = {"visitFormatted(Ljava/lang/String;ILnet/minecraft/text/Style;Lnet/minecraft/text/CharacterVisitor;)Z"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/text/TextVisitFactory;visitFormatted(Ljava/lang/String;ILnet/minecraft/text/Style;Lnet/minecraft/text/Style;Lnet/minecraft/text/CharacterVisitor;)Z",
         ordinal = 0
      ),
      index = 0
   )
   private static String adjustText(String var0) {
      if (nameprotect == null) {
         if (Hub.field_2595 == null) return var0;
         nameprotect = Hub.field_2595.method_78(NameProtectModule.class);
         if (nameprotect == null) return var0;
      }
      if (nameprotect.isToggled() && MinecraftClient.getInstance().world != null) {
         var0 = var0.replace(MinecraftClient.getInstance().getSession().getUsername(), nameprotect.field_1851.getValue());
      }

      return var0;
   }
}
