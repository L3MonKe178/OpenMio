package me.mioclient.mixin;

import me.mioclient.Hub;
import me.mioclient.internal.Class_0485;
import me.mioclient.internal.Class_0838;
import me.mioclient.module.abstract_.AbstractModule_24;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin({InventoryScreen.class})
public class MixinInventoryScreen {
   private static AbstractModule_24 playermodel = Hub.field_2595.method_78(AbstractModule_24.class);

   public MixinInventoryScreen() {
      super();
   }

   @Inject(
      method = {"drawEntity(Lnet/minecraft/client/gui/DrawContext;IIIIIFFFLnet/minecraft/entity/LivingEntity;)V"},
      at = {@At("HEAD")}
   )
   private static void drawEntityHook(
      DrawContext var0, int var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, LivingEntity var9, CallbackInfo var10
   ) {
      Class_0485.field_1538 = true;
   }

   @Inject(
      method = {"drawEntity(Lnet/minecraft/client/gui/DrawContext;IIIIIFFFLnet/minecraft/entity/LivingEntity;)V"},
      at = {@At("RETURN")}
   )
   private static void drawEntityHook2(
      DrawContext var0, int var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, LivingEntity var9, CallbackInfo var10
   ) {
      Class_0485.field_1538 = false;
   }

   @ModifyArgs(
      method = {"method_29977"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/render/entity/EntityRenderDispatcher;render(Lnet/minecraft/entity/Entity;DDDFFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"
      )
   )
   private static void lambdaHook(Args var0) {
      if (AbstractModule_24.field_1777) {
         var0.set(5, Class_0838.method_776());
      }
   }
}
