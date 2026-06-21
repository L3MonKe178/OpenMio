package me.mioclient.mixin;

import net.minecraft.util.crash.CrashReport;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({CrashReport.class})
public class MixinCrashReport {
   public MixinCrashReport() {
      super();
   }

   @Inject(
      method = {"addDetails"},
      at = {@At("TAIL")}
   )
   public void addStackTrace(StringBuilder var1, CallbackInfo var2) {
      var1.append("\n\n-- www.mioclient.me --\n");
      var1.append("Details:\n");
      var1.append("\tMagic: rizz\n\n");
   }
}
