package me.mioclient.internal;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.LinkedList;
import me.mioclient.api.MioAPI;
import org.joml.Vector4i;

public class Class_0041 implements MioAPI {
   public static final LinkedList<Vector4i> field_81 = new LinkedList<>();

   public Class_0041() {
      super();
   }

   public static void method_9(int var0, int var1, int var2, int var3) {
      double var4 = field_4219.getWindow().getScaleFactor();
      Vector4i var6 = new Vector4i(
         (int)((double)var0 * var4),
         (int)((double)(field_4219.getWindow().getScaledHeight() - (var1 + var3)) * var4),
         (int)((double)var2 * var4),
         (int)((double)var3 * var4)
      );
      RenderSystem.enableScissor(var6.x, var6.y, var6.z, var6.w);
      field_81.addLast(var6);
   }

   public static void method_57() {
      RenderSystem.disableScissor();
      field_81.removeLast();
      if (!field_81.isEmpty()) {
         Vector4i var0 = field_81.getLast();
         RenderSystem.enableScissor(var0.x, var0.y, var0.z, var0.w);
      }
   }
}
