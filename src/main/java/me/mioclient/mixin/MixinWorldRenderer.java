package me.mioclient.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.systems.RenderSystem;
import me.mioclient.Hub;
import me.mioclient.api.Class_0514;
import me.mioclient.api.Class_1171;
import me.mioclient.api.Class_1309;
import me.mioclient.enum_.Class_0574;
import me.mioclient.enum_.Class_1296;
import me.mioclient.event.Event_12;
import me.mioclient.event.Event_3;
import me.mioclient.event.Event_6;
import me.mioclient.internal.Class_0500;
import me.mioclient.internal.Class_0838;
import me.mioclient.internal.Class_0947;
import me.mioclient.internal.Class_1355;
import me.mioclient.module.player.FreecamModule;
import me.mioclient.module.render.AmbienceModule;
import me.mioclient.module.render.ChamsModule;
import me.mioclient.module.render.NoRenderModule;
import me.mioclient.module.render.SkyColorModule;
import me.mioclient.module.render.ViewClipModule;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.BufferBuilderStorage;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({WorldRenderer.class})
public abstract class MixinWorldRenderer implements Class_1309, Class_1171 {
   private static final ViewClipModule viewclip = Hub.field_2595.method_78(ViewClipModule.class);
   private static AmbienceModule ambience = Hub.field_2595.method_78(AmbienceModule.class);
   private static NoRenderModule norender = Hub.field_2595.method_78(NoRenderModule.class);
   private static FreecamModule freecam = Hub.field_2595.method_78(FreecamModule.class);
   private static SkyColorModule skycolor = Hub.field_2595.method_78(SkyColorModule.class);
   private static ChamsModule chams = Hub.field_2595.method_78(ChamsModule.class);
   @Unique
   private MatrixStack mio$stack;
   @Shadow
   private Framebuffer field_4101;
   @Shadow
   @Nullable
   private ClientWorld field_4085;

   public MixinWorldRenderer() {
      super();
   }

   @Shadow
   protected abstract void method_22977(Entity var1, double var2, double var4, double var6, float var8, MatrixStack var9, VertexConsumerProvider var10);

   @Shadow
   private void method_22712(MatrixStack var1, VertexConsumer var2, Entity var3, double var4, double var6, double var8, BlockPos var10, BlockState var11) {
   }

   @Inject(
      method = {"renderWeather"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void renderWeatherHook(LightmapTextureManager var1, float var2, double var3, double var5, double var7, CallbackInfo var9) {
      if (ambience.isToggled() && ambience.field_213.getValue() && ambience.field_214.getValue() == Class_1296.CLEAR) {
         var9.cancel();
      }
   }

   @Inject(
      method = {"render"},
      at = {@At("RETURN")}
   )
   private void render(
      RenderTickCounter var1,
      boolean var2,
      Camera var3,
      GameRenderer var4,
      LightmapTextureManager var5,
      Matrix4f var6,
      Matrix4f var7,
      CallbackInfo var8,
      @Local MatrixStack var9
   ) {
      var9.push();
      Class_0838.method_9(var9);
      RenderSystem.clear(256, MinecraftClient.IS_SYSTEM_MAC);
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      Event_3 var10 = Event_3.method_9(var9, Class_0838.method_776());
      Class_0838.field_2672.method_38(() -> field_4220.method_36(var10));
      Class_0947 var11 = Class_0947.method_2(var9, Class_0838.method_776());
      field_4220.method_36(var11);
      var9.pop();
   }

   @Inject(
      method = {"render"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/render/BufferBuilderStorage;getEntityVertexConsumers()Lnet/minecraft/client/render/VertexConsumerProvider$Immediate;",
         shift = Shift.BEFORE
      )}
   )
   private void render_pre(
      RenderTickCounter var1,
      boolean var2,
      Camera var3,
      GameRenderer var4,
      LightmapTextureManager var5,
      Matrix4f var6,
      Matrix4f var7,
      CallbackInfo var8,
      @Local MatrixStack var9
   ) {
      Event_12 var10 = Event_12.method_5(var9, Class_0838.method_776());
      field_4220.method_36(var10);
   }

   @Redirect(
      method = {"render"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/render/WorldRenderer;drawBlockOutline(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;Lnet/minecraft/entity/Entity;DDDLnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)V"
      )
   )
   private void render_drawBlockOutline(
      WorldRenderer var1, MatrixStack var2, VertexConsumer var3, Entity var4, double var5, double var7, double var9, BlockPos var11, BlockState var12
   ) {
      Event_6 var13 = new Event_6(var2, var3, var11, var12);
      field_4220.method_36(var13);
      if (!var13.method_464()) {
         this.method_22712(var13.method_1089(), var13.method_1090(), var4, var5, var7, var9, var13.method_111(), var13.method_958());
      }
   }

   @Inject(
      method = {"hasBlindnessOrDarkness"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void invokeBlindnessHook(Camera var1, CallbackInfoReturnable<Boolean> var2) {
      if (norender.isToggled() && norender.field_720.getValue()) {
         var2.setReturnValue(null);
      }
   }

   @Redirect(
      method = {"render"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/network/ClientPlayerEntity;isSpectator()Z"
      )
   )
   private boolean renderHook(ClientPlayerEntity var1) {
      return !freecam.isToggled() && (!viewclip.isToggled() || !field_4219.gameRenderer.getCamera().isThirdPerson()) ? var1.isSpectator() : true;
   }

   @Inject(
      method = {"<init>"},
      at = {@At("RETURN")}
   )
   private void initHook(MinecraftClient var1, EntityRenderDispatcher var2, BlockEntityRenderDispatcher var3, BufferBuilderStorage var4, CallbackInfo var5) {
      Class_0500.init();
      Class_1355.init();
   }

   @Inject(
      method = {"render"},
      at = {@At("HEAD")}
   )
   private void onRenderHead(
      RenderTickCounter var1, boolean var2, Camera var3, GameRenderer var4, LightmapTextureManager var5, Matrix4f var6, Matrix4f var7, CallbackInfo var8
   ) {
      Class_0838.method_39(var1.getTickDelta(false));
      this.mio$stack = new MatrixStack();
      Class_0838.method_2(this.mio$stack);
      Class_1355.method_528();
   }

   @Inject(
      method = {"render"},
      at = {@At("TAIL")}
   )
   private void onRenderTail(
      RenderTickCounter var1, boolean var2, Camera var3, GameRenderer var4, LightmapTextureManager var5, Matrix4f var6, Matrix4f var7, CallbackInfo var8
   ) {
      Class_1355.method_434();
   }

   @Inject(
      method = {"renderEntity"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void renderEntity(Entity var1, double var2, double var4, double var6, float var8, MatrixStack var9, VertexConsumerProvider var10, CallbackInfo var11) {
      if (norender.field_761.getValue() && norender.method_230(var1) || chams.method_100(var1)) {
         var11.cancel();
      }
   }

   @Inject(
      method = {"onResized"},
      at = {@At("HEAD")}
   )
   private void onResized(int var1, int var2, CallbackInfo var3) {
      Class_1355.method_39(var1, var2);
   }

   @Inject(
      method = {"renderWorldBorder"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void renderWorldBorderHook(Camera var1, CallbackInfo var2) {
      if (norender.isToggled() && norender.field_737.getValue()) {
         var2.cancel();
      }
   }

   @Override
   public Framebuffer getFramebuffer() {
      return this.field_4101;
   }

   @Override
   public void setFramebuffer(Framebuffer var1) {
      this.field_4101 = var1;
   }

   @ModifyVariable(
      method = {"getLightmapCoordinates(Lnet/minecraft/world/BlockRenderView;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;)I"},
      at = @At("STORE"),
      ordinal = 0
   )
   private static int getLightmapCoordinates(int var0) {
      return ambience.isToggled() && ambience.field_207.getValue() == Class_0574.SKY ? Math.max(ambience.field_209.getValue(), var0) : Math.max(0, var0);
   }

   @Redirect(
      method = {"render"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/render/Camera;isThirdPerson()Z"
      )
   )
   private boolean renderHook(Camera var1) {
      return freecam.isToggled() ? true : var1.isThirdPerson();
   }

   @ModifyExpressionValue(
      method = {"render"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/render/DimensionEffects;isDarkened()Z"
      )}
   )
   private boolean isDarkened(boolean var1) {
      return skycolor.isToggled() && skycolor.method_125() ? ((Class_0514)this.field_4085).mio$getOriginalEffects().isDarkened() : var1;
   }

   @Inject(
      method = {"renderWeather"},
      at = {@At("HEAD")}
   )
   private void renderWeatherHook2(LightmapTextureManager var1, float var2, double var3, double var5, double var7, CallbackInfo var9) {
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.25F);
   }

   @Inject(
      method = {"renderWeather"},
      at = {@At("TAIL")}
   )
   private void renderWeatherHook3(LightmapTextureManager var1, float var2, double var3, double var5, double var7, CallbackInfo var9) {
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
   }

   @ModifyExpressionValue(
      method = {"renderWeather"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/world/biome/Biome;hasPrecipitation()Z"
      )}
   )
   private boolean renderWeatherHook(boolean var1) {
      return var1 || ambience.method_95();
   }

   @ModifyExpressionValue(
      method = {"renderWeather"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/world/World;getTopY(Lnet/minecraft/world/Heightmap$Type;II)I"
      )}
   )
   private int renderWeatherHook(int var1) {
      if (!ambience.method_95()) {
         return var1;
      } else {
         byte var2 = 5;
         if (MinecraftClient.isFancyGraphicsOrBetter()) {
            var2 = 10;
         }

         return (int)Math.floor(field_4219.gameRenderer.getCamera().getPos().y) - var2 - 1;
      }
   }
}
