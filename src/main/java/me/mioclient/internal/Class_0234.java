package me.mioclient.internal;

import com.mojang.blaze3d.systems.RenderSystem;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import me.mioclient.api.Class_0896;
import me.mioclient.api.MioAPI;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gl.PostEffectPass;
import net.minecraft.client.gl.PostEffectProcessor;
import net.minecraft.util.Identifier;

public class Class_0234 {
   public final PostEffectProcessor field_645;
   public int field_646;
   public int field_647;

   public Class_0234(Identifier var1, Consumer<Class_0234> var2) {
      super();
      MinecraftClient var3 = MinecraftClient.getInstance();

      try {
         this.field_645 = new PostEffectProcessor(var3.getTextureManager(), var3.getResourceManager(), var3.getFramebuffer(), var1);
      } catch (IOException var5) {
         throw new RuntimeException(var5);
      }

      this.method_266();
      var2.accept(this);
   }

   public static Class_0234 method_2(String var0, Consumer<Class_0234> var1) {
      return new Class_0234(Identifier.of(MioAPI.method_244() ? "mio" : "minecraft", String.format("shaders/post/%s.json", var0)), var1);
   }

   public void method_266() {
      MinecraftClient var1 = MinecraftClient.getInstance();
      int var2 = var1.getWindow().getFramebufferWidth();
      int var3 = var1.getWindow().getFramebufferHeight();
      if (this.field_646 != var2 || this.field_647 != var3) {
         this.field_645.setupDimensions(var2, var3);
         this.field_646 = var2;
         this.field_647 = var3;
      }
   }

   public void method_2(String var1, float var2) {
      List<PostEffectPass> var3 = ((Class_0896)this.field_645).getPasses();
      var3.stream().map(var1x -> var1x.getProgram().getUniformByName(var1)).filter(Objects::nonNull).forEach(var1x -> var1x.set(var2));
   }

   public void method_9(String var1, Framebuffer var2) {
      for (PostEffectPass var5 : ((Class_0896)this.field_645).getPasses()) {
         var5.getProgram().bindSampler(var1, var2::getColorAttachment);
      }
   }

   public void method_31(float var1) {
      this.method_266();
      RenderSystem.disableBlend();
      RenderSystem.disableDepthTest();
      RenderSystem.resetTextureMatrix();
      this.field_645.render(var1);
      MinecraftClient.getInstance().getFramebuffer().beginWrite(true);
      RenderSystem.disableBlend();
      RenderSystem.blendFunc(770, 771);
      RenderSystem.enableDepthTest();
   }

   public PostEffectProcessor method_267() {
      return this.field_645;
   }
}
