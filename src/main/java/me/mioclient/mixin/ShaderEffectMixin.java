package me.mioclient.mixin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import me.mioclient.api.Class_0896;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gl.PostEffectPass;
import net.minecraft.client.gl.PostEffectProcessor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({PostEffectProcessor.class})
public class ShaderEffectMixin implements Class_0896 {
   private final List<String> fakedBufferNames = new ArrayList<>();
   @Shadow
   @Final
   private Map<String, Framebuffer> field_1495;
   @Shadow
   @Final
   private List<PostEffectPass> field_1497;

   public ShaderEffectMixin() {
      super();
   }

   @Override
   public List<PostEffectPass> getPasses() {
      return this.field_1497;
   }

   @Override
   public void addFakeTarget(String var1, Framebuffer var2) {
      Framebuffer var3 = this.field_1495.get(var1);
      if (var3 != var2) {
         if (var3 != null) {
            for (PostEffectPass var5 : this.field_1497) {
               if (var5.input == var3) {
                  ((PostProcessShaderMixin)var5).setInput(var2);
               }

               if (var5.output == var3) {
                  ((PostProcessShaderMixin)var5).setOutput(var2);
               }
            }

            this.field_1495.remove(var1);
            this.fakedBufferNames.remove(var1);
         }

         this.field_1495.put(var1, var2);
         this.fakedBufferNames.add(var1);
      }
   }

   @Inject(
      method = {"close"},
      at = {@At("HEAD")}
   )
   void close(CallbackInfo var1) {
      try {
         for (String var3 : this.fakedBufferNames) {
            this.field_1495.remove(var3);
         }
      } catch (Exception var4) {
      }
   }
}
