package me.mioclient.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.systems.RenderSystem;
import me.mioclient.Hub;
import me.mioclient.api.Class_1309;
import me.mioclient.internal.Class_0127;
import me.mioclient.internal.Class_0245;
import me.mioclient.internal.Class_1000;
import me.mioclient.internal.Class_1334;
import me.mioclient.internal.Class_1355;
import me.mioclient.module.exploit.ReachModule;
import me.mioclient.module.player.FreecamModule;
import me.mioclient.module.render.FreeLookModule;
import me.mioclient.module.render.NoBobModule;
import me.mioclient.module.render.NoRenderModule;
import me.mioclient.module.render.ShaderModule;
import me.mioclient.module.render.ViewModelModule;
import me.mioclient.module.render.ZoomModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.BufferBuilderStorage;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.render.VertexConsumerProvider.Immediate;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.MathHelper;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({GameRenderer.class})
public abstract class MixinGameRenderer {
   private static ZoomModule zoom = Hub.field_2595.method_78(ZoomModule.class);
   private static ReachModule reach = Hub.field_2595.method_78(ReachModule.class);
   private static NoRenderModule norender = Hub.field_2595.method_78(NoRenderModule.class);
   private static ShaderModule shader = Hub.field_2595.method_78(ShaderModule.class);
   private static FreecamModule freecam = Hub.field_2595.method_78(FreecamModule.class);
   private static FreeLookModule freelook = Hub.field_2595.method_78(FreeLookModule.class);
   private static ViewModelModule viewmodel = Hub.field_2595.method_78(ViewModelModule.class);
   private static NoBobModule nobob = Hub.field_2595.method_78(NoBobModule.class);
   @Final
   @Shadow
   MinecraftClient field_4015;
   @Final
   @Shadow
   private Camera field_18765;
   @Shadow
   private boolean field_3992;
   @Shadow
   @Final
   public HeldItemRenderer field_4012;
   @Shadow
   @Final
   private BufferBuilderStorage field_20948;
   @Unique
   private boolean bobbing;
   @Unique
   private boolean prevBobbing;
   private boolean freecamSet = false;

   public MixinGameRenderer() {
      super();
   }

   @Shadow
   private void method_3172(Camera var1, float var2, Matrix4f var3) {
   }

   @Shadow
   public abstract void method_3182();

   @Shadow
   public abstract void method_3190(float var1);

   @Inject(
      at = {@At(
         value = "FIELD",
         target = "Lnet/minecraft/client/render/GameRenderer;renderHand:Z",
         opcode = 180,
         ordinal = 0
      )},
      method = {"renderWorld"}
   )
   private void renderWorldFieldHook(RenderTickCounter var1, CallbackInfo var2) {
      if (this.bobbing) {
         this.field_4015.options.getBobView().setValue(true);
         this.bobbing = false;
      }
   }

   @Inject(
      at = {@At("HEAD")},
      method = {"renderWorld"}
   )
   private void renderWorldPreHook(RenderTickCounter var1, CallbackInfo var2) {
      this.prevBobbing = true;
   }

   @Inject(
      at = {@At("HEAD")},
      method = {"bobView"},
      cancellable = true
   )
   private void bobViewHook(MatrixStack var1, float var2, CallbackInfo var3) {
      if ((Boolean)this.field_4015.options.getBobView().getValue() && this.prevBobbing) {
         this.bobbing = true;
         this.field_4015.options.getBobView().setValue(false);
         this.prevBobbing = false;
         var3.cancel();
      }
   }

   @Inject(
      method = {"tiltViewWhenHurt"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void bobViewWhenHurtHook(MatrixStack var1, float var2, CallbackInfo var3) {
      if (norender.isToggled() && norender.field_717.getValue()) {
         var3.cancel();
      }
   }

   @Inject(
      method = {"showFloatingItem"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void showFloatingItemHook(ItemStack var1, CallbackInfo var2) {
      if (var1.getItem() == Items.TOTEM_OF_UNDYING && norender.isToggled() && norender.field_722.getValue()) {
         var2.cancel();
      }
   }

   @WrapOperation(
      method = {"renderWorld"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/util/math/MathHelper;lerp(FFF)F"
      )},
      require = 0
   )
   private float applyCameraTransformationsMathHelperLerpProxy(float var1, float var2, float var3, Operation<Float> var4) {
      return norender.isToggled() && norender.field_720.getValue() ? 0.0F : MathHelper.lerp(var1, var2, var3);
   }

   @Inject(
      method = {"findCrosshairTarget"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/entity/projectile/ProjectileUtil;raycast(Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/math/Box;Ljava/util/function/Predicate;D)Lnet/minecraft/util/hit/EntityHitResult;"
      )},
      cancellable = true
   )
   private void onUpdateTargetedEntity(Entity var1, double var2, double var4, float var6, CallbackInfoReturnable<HitResult> var7, @Local HitResult var8) {
      if (reach.method_41() && var8.getType() == Type.BLOCK) {
         var7.setReturnValue(var8);
      }
   }

   @WrapWithCondition(
      method = {"renderHand"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/render/item/HeldItemRenderer;renderItem(FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider$Immediate;Lnet/minecraft/client/network/ClientPlayerEntity;I)V"
      )}
   )
   private boolean renderHand(HeldItemRenderer var1, float var2, MatrixStack var3, Immediate var4, ClientPlayerEntity var5, int var6) {
      if (!Class_1355.field_2003) {
         return true;
      } else {
         Class_1000.method_2(shader.field_2017.getValue().method_177(), true, () -> var1.renderItem(var2, var3, var4, var5, var6));
         return false;
      }
   }

   @Inject(
      method = {"renderWorld"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/render/GameRenderer;renderHand(Lnet/minecraft/client/render/Camera;FLorg/joml/Matrix4f;)V",
         shift = Shift.BEFORE
      )},
      cancellable = true
   )
   private void renderWorldHook(
      RenderTickCounter var1, CallbackInfo var2, @Local(ordinal = 1) Matrix4f var3, @Local(ordinal = 0) float var4, @Local(ordinal = 0) Quaternionf var5
   ) {
      if (this.field_3992 && shader.isToggled() && shader.field_2041.getValue()) {
         var2.cancel();
         if (!norender.method_279()) {
            this.method_3172(this.field_18765, var4, var3);
         }

         Class_1355.field_2003 = true;
         this.method_3172(this.field_18765, var4, var3);
         Class_1355.field_2003 = false;
         Class_1309.field_4219.getProfiler().pop();
      }
   }

   @Redirect(
      method = {"bobView"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/util/math/MatrixStack;translate(FFF)V"
      )
   )
   private void mio$redirectTranslate(MatrixStack var1, float var2, float var3, float var4) {
      float var5 = nobob.field_4483.getValue();
      if (var5 <= 0.0F || !nobob.isToggled()) {
         var5 = 1.0F;
      }

      if ((Boolean)this.field_4015.options.getBobView().getValue()) {
         var1.translate(var2 * var5, var3 * var5, var4 * var5);
      }
   }

   @Inject(
      method = {"renderHand"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void renderHandHook(Camera var1, float var2, Matrix4f var3, CallbackInfo var4) {
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, norender.method_282());
      if (viewmodel.isToggled() && !viewmodel.field_1907.getValue()) {
         float var5 = var1.getPitch() * Class_0245.field_690;
         float var6 = -(var1.getYaw() - 45.0F) * Class_0245.field_690;
         float var7 = MathHelper.cos(var6);
         float var8 = MathHelper.sin(var6);
         float var9 = MathHelper.cos(var5);
         float var10 = MathHelper.sin(var5);
         Vector3f var11 = new Vector3f(var8 * var9, -var10, var7 * var9);
         RenderSystem.setShaderLights(var11, new Vector3f(var11).mul(-1.0F));
      }

      if (freecam.method_279() || norender.method_279()) {
         var4.cancel();
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      }
   }

   @WrapOperation(
      method = {"renderHand"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/render/GameRenderer;getFov(Lnet/minecraft/client/render/Camera;FZ)D"
      )},
      require = 0
   )
   private double renderHandHook(GameRenderer var1, Camera var2, float var3, boolean var4, Operation<Double> var5) {
      float var6 = zoom.isToggled() ? 1.0F - zoom.method_1176() : 1.0F;
      return viewmodel.isToggled() && viewmodel.field_1941.getValue()
         ? (double)((float)viewmodel.field_1942.getValue().intValue() * var6)
         : (Double)var5.call(new Object[]{var1, var2, var3, var4}) * (double)var6;
   }

   @Inject(
      method = {"renderHand"},
      at = {@At("RETURN")}
   )
   private void renderHandHook2(Camera var1, float var2, Matrix4f var3, CallbackInfo var4) {
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
   }

   @Inject(
      method = {"updateCrosshairTarget"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void updateTargetedEntityInvoke(float var1, CallbackInfo var2) {
      if (freecam.isToggled() && this.field_4015.getCameraEntity() != null && !this.freecamSet) {
         var2.cancel();
         Entity var3 = this.field_4015.getCameraEntity();
         double var4 = var3.getX();
         double var6 = var3.getY();
         double var8 = var3.getZ();
         double var10 = var3.prevX;
         double var12 = var3.prevY;
         double var14 = var3.prevZ;
         float var16 = var3.getYaw();
         float var17 = var3.getPitch();
         float var18 = var3.prevYaw;
         float var19 = var3.prevPitch;
         var3.prevX = freecam.field_2828.x;
         var3.prevY = freecam.field_2828.y - (double)var3.getEyeHeight(var3.getPose());
         var3.prevZ = freecam.field_2828.z;
         var3.setYaw(freecam.field_1760);
         var3.setHeadYaw(freecam.field_1760);
         var3.setPitch(freecam.field_1761);
         var3.prevYaw = freecam.field_1542;
         var3.prevPitch = freecam.field_1543;
         this.freecamSet = true;
         Class_0127.method_7(() -> {
            Class_1334.method_2(var3.getPos(), freecam.field_806.x, freecam.field_806.y - (double)var3.getEyeHeight(var3.getPose()), freecam.field_806.z);
            this.method_3190(var1);
            Class_1334.method_2(var3.getPos(), var4, var6, var8);
         });
         this.freecamSet = false;
         var3.prevX = var10;
         var3.prevY = var12;
         var3.prevZ = var14;
         var3.setYaw(var16);
         var3.setPitch(var17);
         var3.prevYaw = var18;
         var3.prevPitch = var19;
      }

      if (freelook.isToggled() && !freecam.isToggled()) {
         var2.cancel();
      }
   }

   @Inject(
      method = {"render"},
      at = {@At("HEAD")}
   )
   private void render(RenderTickCounter var1, boolean var2, CallbackInfo var3) {
      Hub.field_2601.method_813();
   }
}
