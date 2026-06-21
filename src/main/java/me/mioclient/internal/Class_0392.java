package me.mioclient.internal;

import java.util.function.Function;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderPhase;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.RenderLayer.MultiPhase;
import net.minecraft.client.render.RenderLayer.MultiPhaseParameters;
import net.minecraft.client.render.RenderPhase.Texture;
import net.minecraft.client.render.VertexFormat.DrawMode;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public final class Class_0392 {
   public static final Function<Identifier, RenderLayer> field_1265 = Util.memoize(var0 -> method_2("armor_cutout_no_cull", var0, false));
   public static final Function<Identifier, RenderLayer> field_1266 = Util.memoize(
      var0 -> {
         MultiPhaseParameters var1 = MultiPhaseParameters.builder()
            .program(RenderPhase.ENTITY_SOLID_PROGRAM)
            .texture(new Texture(var0, false, false))
            .cull(RenderPhase.DISABLE_CULLING)
            .transparency(RenderPhase.TRANSLUCENT_TRANSPARENCY)
            .lightmap(RenderPhase.ENABLE_LIGHTMAP)
            .overlay(RenderPhase.ENABLE_OVERLAY_COLOR)
            .build(true);
         return RenderLayer.of("entity_solid", VertexFormats.POSITION_COLOR_TEXTURE_OVERLAY_LIGHT_NORMAL, DrawMode.QUADS, 1536, true, true, var1);
      }
   );
   public static final Function<Identifier, RenderLayer> field_1267 = Util.memoize(
      var0 -> {
         MultiPhaseParameters var1 = MultiPhaseParameters.builder()
            .program(RenderPhase.ENTITY_CUTOUT_NONULL_PROGRAM)
            .texture(new Texture(var0, false, false))
            .transparency(RenderPhase.TRANSLUCENT_TRANSPARENCY)
            .cull(RenderPhase.DISABLE_CULLING)
            .lightmap(RenderPhase.ENABLE_LIGHTMAP)
            .overlay(RenderPhase.ENABLE_OVERLAY_COLOR)
            .build(false);
         return RenderLayer.of("entity_cutout_no_cull", VertexFormats.POSITION_COLOR_TEXTURE_OVERLAY_LIGHT_NORMAL, DrawMode.QUADS, 1536, true, false, var1);
      }
   );

   public Class_0392() {
      super();
   }

   public static MultiPhase method_2(String var0, Identifier var1, boolean var2) {
      MultiPhaseParameters var3 = MultiPhaseParameters.builder()
         .program(RenderPhase.ARMOR_CUTOUT_NO_CULL_PROGRAM)
         .texture(new Texture(var1, false, false))
         .transparency(RenderPhase.TRANSLUCENT_TRANSPARENCY)
         .cull(RenderPhase.DISABLE_CULLING)
         .lightmap(RenderPhase.ENABLE_LIGHTMAP)
         .overlay(RenderPhase.ENABLE_OVERLAY_COLOR)
         .layering(RenderPhase.VIEW_OFFSET_Z_LAYERING)
         .depthTest(var2 ? RenderPhase.EQUAL_DEPTH_TEST : RenderPhase.LEQUAL_DEPTH_TEST)
         .build(true);
      return RenderLayer.of(var0, VertexFormats.POSITION_COLOR_TEXTURE_OVERLAY_LIGHT_NORMAL, DrawMode.QUADS, 1536, true, false, var3);
   }

   public static RenderLayer method_2(Identifier var0) {
      return field_1265.apply(var0);
   }

   public static RenderLayer method_9(Identifier var0) {
      return field_1266.apply(var0);
   }

   public static RenderLayer method_5(Identifier var0) {
      return field_1267.apply(var0);
   }
}
