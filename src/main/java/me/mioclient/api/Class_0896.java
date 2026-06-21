package me.mioclient.api;

import java.util.List;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gl.PostEffectPass;

public interface Class_0896 {
   void addFakeTarget(String var1, Framebuffer var2);

   List<PostEffectPass> getPasses();
}
