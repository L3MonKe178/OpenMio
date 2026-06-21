package me.mioclient.mixin;

import me.mioclient.Hub;
import me.mioclient.module.render.NoRenderModule;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.TutorialToast;
import net.minecraft.client.toast.SystemToast.Type;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(
   targets = {"net/minecraft/client/toast/ToastManager$Entry"}
)
public class MixinToastManagerEntry {
   private static NoRenderModule norender = Hub.field_2595.method_78(NoRenderModule.class);
   @Final
   @Shadow
   private Toast instance;

   public MixinToastManagerEntry() {
      super();
   }

   @Inject(
      method = {"draw"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void draw(int var1, DrawContext var2, CallbackInfoReturnable<Boolean> var3) {
      if (norender.isToggled()
         && (
            this.instance.getType() == Type.UNSECURE_SERVER_WARNING && norender.field_727.getValue()
               || this.instance instanceof TutorialToast && norender.field_728.getValue()
         )) {
         var3.setReturnValue(true);
      }
   }
}
