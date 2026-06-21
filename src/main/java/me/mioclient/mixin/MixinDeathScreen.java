package me.mioclient.mixin;

import me.mioclient.Hub;
import me.mioclient.module.misc.CustomDeathTextModule;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({DeathScreen.class})
public class MixinDeathScreen {
   private static CustomDeathTextModule customdeath = Hub.field_2595.method_78(CustomDeathTextModule.class);
   @Mutable
   @Shadow
   @Final
   private Text message;

   public MixinDeathScreen() {
      super();
   }

   @Inject(
      method = {"<init>"},
      at = {@At("RETURN")}
   )
   public void mio$init(Text var1, boolean var2, CallbackInfo var3) {
      if (customdeath != null && customdeath.isToggled()) {
         this.message = customdeath.method_210();
      }
   }
}
