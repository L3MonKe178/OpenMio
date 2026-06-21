package me.mioclient.mixin;

import me.mioclient.Hub;
import me.mioclient.api.MioAPI;
import me.mioclient.event.Event_13;
import me.mioclient.internal.Class_0483;
import me.mioclient.internal.Class_1355;
import me.mioclient.module.render.ChamsModule;
import me.mioclient.module.render.NoRenderModule;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.world.WorldView;
import org.joml.Quaternionf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({EntityRenderDispatcher.class})
public class MixinEntityRenderDispatcher {
   private static NoRenderModule norender = Hub.field_2595.method_78(NoRenderModule.class);
   private static ChamsModule chams = Hub.field_2595.method_78(ChamsModule.class);

   public MixinEntityRenderDispatcher() {
      super();
   }

   @Inject(
      method = {"renderShadow"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void onRenderShadow(
      MatrixStack var0, VertexConsumerProvider var1, Entity var2, float var3, float var4, WorldView var5, float var6, CallbackInfo var7
   ) {
      if (chams.method_101(var2)) {
         var7.cancel();
      }

      if (Class_1355.field_4422) {
         var7.cancel();
      }
   }

   @Inject(
      method = {"render"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public <E extends Entity> void onRenderPre(
      E var1, double var2, double var4, double var6, float var8, float var9, MatrixStack var10, VertexConsumerProvider var11, int var12, CallbackInfo var13
   ) {
      Event_13 var14 = Event_13.method_9(var1, var10, var11);
      MioAPI.field_4220.method_36(var14);
      if (var14.method_464() || var1 == null) {
         var13.cancel();
      }
   }

   @Inject(
      method = {"render"},
      at = {@At("RETURN")}
   )
   public <E extends Entity> void onRenderPost(
      E var1, double var2, double var4, double var6, float var8, float var9, MatrixStack var10, VertexConsumerProvider var11, int var12, CallbackInfo var13
   ) {
      Class_0483 var14 = Class_0483.method_2(var1, var10, var11);
      MioAPI.field_4220.method_36(var14);
   }

   @Inject(
      method = {"renderFire"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void renderFireHook(MatrixStack var1, VertexConsumerProvider var2, Entity var3, Quaternionf var4, CallbackInfo var5) {
      if (chams.method_101(var3)) {
         var5.cancel();
      }

      if (norender.isToggled()
         && norender.field_750.getValue()
         && (norender.field_751.getValue() || var3 instanceof ClientPlayerEntity || var3 instanceof EndCrystalEntity)) {
         var5.cancel();
      }
   }

   @Inject(
      method = {"renderHitbox"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void renderHitboxHook(MatrixStack var0, VertexConsumer var1, Entity var2, float var3, float var4, float var5, float var6, CallbackInfo var7) {
      if (ChamsModule.field_253) {
         var7.cancel();
      }
   }
}
