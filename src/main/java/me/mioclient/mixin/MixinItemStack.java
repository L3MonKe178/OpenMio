package me.mioclient.mixin;

import me.mioclient.api.Class_1309;
import me.mioclient.event.Event_30;
import me.mioclient.internal.Class_0114;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({ItemStack.class})
public abstract class MixinItemStack {
   public MixinItemStack() {
      super();
   }

   @Shadow
   public abstract Item method_7909();

   @Inject(
      method = {"finishUsing"},
      at = {@At("HEAD")}
   )
   private void finishUsing(World var1, LivingEntity var2, CallbackInfoReturnable<ItemStack> var3) {
      if (var2 instanceof ClientPlayerEntity) {
         Event_30 var4 = new Event_30((ItemStack)(Object)this);
         Class_1309.field_4220.method_36(var4);
      }
   }

   @Inject(
      method = {"hasGlint"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void hasGlintHook(CallbackInfoReturnable<Boolean> var1) {
      if (Class_0114.method_22(this.method_7909())) {
         var1.setReturnValue(true);
         var1.cancel();
      }
   }
}
