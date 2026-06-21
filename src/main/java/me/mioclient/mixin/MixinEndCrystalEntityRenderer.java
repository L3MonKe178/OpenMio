package me.mioclient.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import me.mioclient.Hub;
import me.mioclient.api.Class_0468;
import me.mioclient.enum_.Class_0670;
import me.mioclient.internal.Class_0392;
import me.mioclient.internal.Class_1355;
import me.mioclient.module.combat.AutoCrystalModule;
import me.mioclient.module.render.AnimationsModule;
import me.mioclient.module.render.ChamsModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EndCrystalEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin({EndCrystalEntityRenderer.class})
public class MixinEndCrystalEntityRenderer {
   @Shadow
   @Final
   private static Identifier TEXTURE;
   private static AutoCrystalModule ac = Hub.field_2595.method_78(AutoCrystalModule.class);
   private static ChamsModule mod = Hub.field_2595.method_78(ChamsModule.class);
   private static AnimationsModule animations = Hub.field_2595.method_78(AnimationsModule.class);
   @Unique
   private static EndCrystalEntity last;
   @Unique
   private int mio$ordinal;

   public MixinEndCrystalEntityRenderer() {
      super();
   }

   @ModifyArgs(
      method = {"render*"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/util/math/MatrixStack;scale(FFF)V",
         ordinal = 0
      )
   )
   private void scaleHook(Args var1) {
      if (animations.isToggled() && animations.field_334.getValue()) {
         var1.set(0, (Float)var1.get(0) * animations.field_337.getValue());
         var1.set(1, (Float)var1.get(1) * animations.field_337.getValue());
         var1.set(2, (Float)var1.get(2) * animations.field_337.getValue());
      }
   }

   @Inject(
      method = {"render(Lnet/minecraft/entity/decoration/EndCrystalEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void renderHook(EndCrystalEntity var1, float var2, float var3, MatrixStack var4, VertexConsumerProvider var5, int var6, CallbackInfo var7) {
      this.mio$ordinal = 0;
      last = var1;
      if (System.currentTimeMillis() - ((Class_0468)var1).getSpawnTime() > (long)Math.max(Hub.field_2602.method_983(), 50)
         && var1.age < 10
         && ac.isToggled()
         && ac.field_4076.getValue()
         && ((Class_0468)var1).isMioAttacked()) {
         var7.cancel();
      }
   }

   @Inject(
      method = {"getYOffset"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void offsetHook(EndCrystalEntity var0, float var1, CallbackInfoReturnable<Float> var2) {
      if (animations.isToggled() && animations.field_334.getValue()) {
         float var3 = (float)var0.endCrystalAge + var1;
         float var4 = MathHelper.sin(var3 * 0.2F) / 2.0F + 0.5F;
         float var5 = 1.0F;
         if (animations.isToggled() && animations.field_334.getValue()) {
            var5 = animations.field_335.getValue();
         }

         if (var0.age < 10 && ac.isToggled() && animations.field_335.getValue() != 0.0F && ac.field_4076.getValue() && ((Class_0468)var0).isMioAttacked()) {
            var5 *= ((Class_0468)var0).getSpawnTime() % 2L == 0L ? 0.0F : 1.0F;
         }

         var4 = (var4 * var4 + var4) * var5;
         var2.cancel();
         var2.setReturnValue(var4 - 1.4F);
      }
   }

   @ModifyArgs(
      method = {"render(Lnet/minecraft/entity/decoration/EndCrystalEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/util/math/RotationAxis;rotationDegrees(F)Lorg/joml/Quaternionf;"
      ),
      require = 0
   )
   private void speedHook(Args var1) {
      if (last.age < 10 && ac.isToggled() && ac.field_4076.getValue() && ((Class_0468)last).isMioAttacked()) {
         var1.set(0, (Float)var1.get(0) * 1.5F);
      }

      if (animations.isToggled() && animations.field_334.getValue()) {
         var1.set(0, (Float)var1.get(0) * animations.field_336.getValue());
      }
   }

   @WrapWithCondition(
      method = {"render(Lnet/minecraft/entity/decoration/EndCrystalEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/model/ModelPart;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;II)V"
      )}
   )
   private boolean renderHook(ModelPart var1, MatrixStack var2, VertexConsumer var3, int var4, int var5) {
      boolean var6 = this.mio$renderHandler(var1, var2, var3, var4, var5, this.mio$ordinal);
      this.mio$ordinal++;
      return var6;
   }

   @Inject(
      method = {"render(Lnet/minecraft/entity/decoration/EndCrystalEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/util/math/MatrixStack;multiply(Lorg/joml/Quaternionf;)V",
         ordinal = 0
      )}
   )
   private void renderHook2(EndCrystalEntity var1, float var2, float var3, MatrixStack var4, VertexConsumerProvider var5, int var6, CallbackInfo var7) {
      if (this.mio$ordinal == 0) {
         this.mio$ordinal++;
      }
   }

   @ModifyExpressionValue(
      method = {"render(Lnet/minecraft/entity/decoration/EndCrystalEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/render/VertexConsumerProvider;getBuffer(Lnet/minecraft/client/render/RenderLayer;)Lnet/minecraft/client/render/VertexConsumer;"
      )}
   )
   private VertexConsumer render3(VertexConsumer var1, @Local(argsOnly = true) VertexConsumerProvider var2) {
      return mod.isToggled() && mod.field_227.getValue() && mod.field_238.getValue() != Class_0670.OFF && !Class_1355.field_4422
         ? var2.getBuffer(Class_0392.method_5(TEXTURE))
         : var1;
   }

   @Unique
   private boolean mio$renderHandler(ModelPart var1, MatrixStack var2, VertexConsumer var3, int var4, int var5, int var6) {
      if (animations.method_132(var6)) {
         return false;
      } else if (mod.isToggled()
         && mod.field_238.getValue().method_670()
         && !(MinecraftClient.getInstance().gameRenderer.getCamera().getPos().distanceTo(last.getPos()) > (double)mod.field_221.getValue().intValue())) {
         if (mod.method_98(last)) {
            render(var1, var2, var5);
            if (mod.method_101(last)) {
               return false;
            }
         }

         return true;
      } else {
         return true;
      }
   }

   private static void render(ModelPart var0, MatrixStack var1, int var2) {
      if (mod.isToggled() && mod.field_238.getValue().method_670() && mod.field_229.getValue() && ChamsModule.field_253) {
         var1.push();
         var0.render(var1, mod.method_104().method_741(), 15728880, var2);
         var1.pop();
      }
   }
}
