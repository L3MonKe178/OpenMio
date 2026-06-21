package me.mioclient.module.render;

import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_0062;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_12;
import me.mioclient.event.Event_3;
import me.mioclient.event.Event_4;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0104;
import me.mioclient.internal.Class_0612;
import me.mioclient.internal.Class_0719;
import me.mioclient.internal.RenderUtil;
import me.mioclient.internal.Class_0930;
import me.mioclient.internal.Class_0978;
import me.mioclient.internal.Class_1000;
import me.mioclient.internal.FontRenderer;
import me.mioclient.internal.Class_1081;
import me.mioclient.internal.Class_1135;
import me.mioclient.internal.Class_1225;
import me.mioclient.internal.Class_1288;
import me.mioclient.internal.TextBuilder;
import me.mioclient.internal.Class_1355;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.abstract_.AbstractModule_21;
import me.mioclient.setting.Setting;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferRenderer;
import net.minecraft.client.render.BuiltBuffer;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.OutlineVertexConsumerProvider;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.VertexFormat.DrawMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LightType;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.chunk.light.ChunkLightingView;
import nick.Settings;
import org.joml.Matrix4f;

public class ESPModule extends Module {
   public static AbstractModule_21 field_521 = Hub.field_2595.method_78(AbstractModule_21.class);
   public Setting<Boolean> field_3809;
   public Setting<Boolean> field_3810;
   public Setting<Boolean> field_3811;
   public Setting<Boolean> field_3812;
   public Setting<Color> field_3813;
   public Setting<Color> field_3814;
   public Setting<Boolean> field_3815;
   public Setting<Color> field_3816;
   public Setting<Color> field_3817;
   public Setting<Boolean> field_3818;
   public Setting<Color> field_3819;
   public Setting<Color> field_3820;
   public Setting<Boolean> field_3821;
   public Setting<Color> field_3822;
   public Setting<Color> field_3823;
   public Setting<Boolean> field_3824;
   public Setting<Class_0062> field_3825;
   public Setting<Integer> field_3826;
   public Setting<Boolean> field_3827;
   public Setting<Color> field_3828;
   public Setting<Color> field_3829;
   public Setting<Float> field_3830;
   public Setting<Color> field_3831;
   public Setting<Color> field_3832;
   public Setting<Boolean> field_3833;
   public Setting<Color> field_3834;
   public Setting<Color> field_3835;
   public Setting<Boolean> field_3836;
   public Setting<Color> field_3837;
   public Setting<Boolean> field_3838;
   public Setting<Boolean> field_3839;
   public Setting<Boolean> field_3840;
   public Setting<Boolean> field_3841;
   public Setting<Boolean> field_3842;
   public Setting<Boolean> field_3843;
   public Setting<Boolean> field_3844;
   public Setting<Boolean> field_3845;
   public Setting<Boolean> field_3846;
   public Setting<Boolean> field_3847;
   public Setting<Float> field_3848;
   public boolean field_3849;
   public final Class_0104 field_3850;
   public final Map<Vec3d, Long> field_3851;

   public ESPModule() {
      super("ESP", "Highlights entities through walls.", Category.RENDER);
      Settings.initialize(this);
      this.field_3850 = new Class_0104(this);
      this.field_3851 = Collections.synchronizedMap(new HashMap<>());
      this.field_3819.method_31("AnimalsFill");
      this.field_3820.method_31("AnimalsOutline");
      this.field_3813.method_31("HostilesFill");
      this.field_3814.method_31("HostilesOutline");
      this.field_3816.method_31("PlayersFill");
      this.field_3817.method_31("PlayersOutline");
      this.field_3831.method_31("ItemsFill");
      this.field_3832.method_31("ItemsOutline");
      this.field_3834.method_31("ExpFill");
      this.field_3835.method_31("ExpOutline");
      this.field_3825.method_31("ItemsMode");
      this.field_3826.method_31("ItemRange");
      this.setDrawn(false);
   }

   @Subscribe
   public void method_7(Event_12 var1) {
      if (this.field_3811.getValue()) {
         this.method_2(var1);
      }
   }

   @Subscribe(
      method_800 = -99999999
   )
   public void method_29(Event_3 var1) {
      if (!this.method_535() && this.field_3850.method_126()) {
         Class_1000.method_2(Class_1355.field_4420, false);
         var1.method_10().push();
         this.field_3849 = true;
         Vec3d var2 = field_4219.gameRenderer.getCamera().getPos();
         OutlineVertexConsumerProvider var3 = Class_1355.field_4420.field_2301;
         Class_1135.method_1013();

         for (BlockEntity var5 : Hub.field_2622.method_347()) {
            if (this.field_3850.method_9(var5)) {
               double var6 = (double)var5.getPos().getX() - var2.x;
               double var8 = (double)var5.getPos().getY() - var2.y;
               double var10 = (double)var5.getPos().getZ() - var2.z;
               var1.method_10().push();
               var1.method_10().translate(var6, var8, var10);
               Color var12 = Class_0612.method_2(this.field_3850.method_5(var5), field_521 != null && field_521.field_2133 != null && field_521.field_2133.getValue() != null ? field_521.field_2133.getValue().method_727() : new float[]{1.0F, 1.0F, 1.0F, 1.0F});
               var3.setColor(var12.getRed(), var12.getGreen(), var12.getBlue(), 255);
               if (field_4219.getBlockEntityRenderDispatcher().get(var5) == null) {
                  VertexConsumer var13 = var3.getBuffer(RenderLayer.getSolid());
                  field_4219.getBlockRenderManager()
                     .renderBlock(var5.getCachedState(), var5.getPos(), field_4219.world, var1.method_10(), var13, false, field_4219.world.getRandom());
               } else {
                  field_4219.getBlockEntityRenderDispatcher().render(var5, var1.method_880(), var1.method_10(), var3);
               }

               var1.method_10().pop();
            }
         }

         Class_1135.method_1014();
         field_4219.getBufferBuilders().getEntityVertexConsumers().draw();
         this.field_3849 = false;
         var1.method_10().pop();
         Class_1000.method_31(false);
      }
   }

   @Subscribe(
      method_800 = -200
   )
   public void method_9(Event_3 var1) {
      if (!this.method_535()) {
         ArrayList<Entity> var2 = new ArrayList<>();

         for (Entity var4 : field_4219.world.getEntities()) {
            var2.add(var4);
         }

         var2.sort(Comparator.comparing(var0 -> -var0.age));
         ArrayList var13 = new ArrayList();

         for (Entity var5 : (Iterable<Entity>)(Iterable<?>)(var2)) {
            if (this.field_3850.method_98(var5)) {
               Class_0612.method_5(var1.method_10(), Class_0719.method_2(var5, var1.method_880()), this.field_3850.method_9(var5, true));
               Class_0612.method_2(
                  var1.method_10(), Class_0719.method_2(var5, var1.method_880()), this.field_3850.method_9(var5, false), this.field_3848.getValue()
               );
            }

            if (var5 instanceof ItemEntity var6
               && this.field_3824.getValue()
               && (this.field_3825.getValue() == Class_0062.BOTH || this.field_3825.getValue() == Class_0062.TEXT)) {
               Vec3d var7 = field_4219.gameRenderer.getCamera().getPos();
               if (var5 instanceof ItemEntity && var5.getPos().distanceTo(var7) > (double)(this.field_3826.getValue() != null ? this.field_3826.getValue().intValue() : 0)) {
                  continue;
               }

               if (!this.field_3827.getValue()) {
                  String var20 = var6.getStack().getName().getString();
                  if (var6.getStack().getCount() > 1) {
                     var20 = new TextBuilder().method_2(var6.getStack().getCount()).method_2((Object)var20).method_9("\u0001 x\u0001");
                  }

                  this.field_3850
                     .method_2(
                        var1,
                        var20,
                        var6.getLerpedPos(var1.method_880()).add(0.0, Double.longBitsToDouble(4603579539312869376L), 0.0),
                        this.field_3830.getValue(),
                        this.field_3828.getValue(),
                        this.field_3829.getValue()
                     );
                  continue;
               }

               boolean var8 = false;

               for (Class_1288 var10 : (Iterable<Class_1288>)(Iterable<?>)(var13)) {
                  if (var10.method_2(var6)) {
                     var8 = true;
                  }
               }

               if (!var8) {
                  var13.add(new Class_1288(var6));
               }
            }

            String var17 = this.field_3850.method_84(var5);
            if (var17 != null) {
               Vec3d var19 = var5.getLerpedPos(var1.method_880()).add(0.0, Double.longBitsToDouble(4596373779694328218L), 0.0);
               if (!(var5 instanceof EnderPearlEntity)) {
                  var19 = var19.add(0.0, Double.longBitsToDouble(4608533498688228557L), 0.0);
               }

               this.field_3850.method_2(var1, var17, var19, Float.intBitsToFloat(1065353216), Color.white, null);
            }
         }

         if (this.field_3827.getValue()) {
            this.method_2(var1, var13);
         }

         if (this.field_3836.getValue()) {
            synchronized (this.field_3851) {
               for (Entry var18 : this.field_3851.entrySet()) {
                  this.field_3850.method_2(var1, "Player teleport", (Vec3d)var18.getKey(), Float.intBitsToFloat(1065353216), this.field_3837.getValue(), null);
               }
            }
         }
      }
   }

   public void method_2(Class_0978 var1, List<Class_1288> var2) {
      for (Class_1288 var4 : var2) {
         if (!this.field_3827.getValue()) {
            break;
         }

         Vec3d var5 = var4.method_1151().getCenter();
         double var6 = Class_0930.method_2(field_4219.gameRenderer.getCamera().getPos(), var5, (double)(this.field_3830.getValue() != null ? this.field_3830.getValue().floatValue() : 0.0f));
         float var8 = (float)FontRenderer.field_3143.method_66();
         float var9 = 0.0F;
         if (var4.method_1152().size() == 1) {
            this.method_2(var1.method_10(), var4, var6, (float)var4.method_1152().size() * var8);
         } else {
            this.method_2(var1.method_10(), var4, var6, var8);
            this.method_2(var1.method_10(), var4, var6, (float)(var4.method_1152().size() - 1) * -var8 - Float.intBitsToFloat(1073741824));
         }

         for (Entry var11 : var4.method_1152().entrySet()) {
            String var12 = Class_1288.method_5((String)var11.getKey(), (Integer)var11.getValue());
            float var13 = -FontRenderer.field_3143.method_221(var12) / Float.intBitsToFloat(1073741824);
            RenderUtil.field_2672.method_2(var1.method_881(), var12, var5, 0.0F, 0.0F, var13, var9, var6, this.field_3828.getValue(), true);
            var9 -= var8;
         }
      }
   }

   @Subscribe
   public void method_5(Event_4 var1) {
      if (var1.method_127() instanceof PlaySoundS2CPacket var2 && ((SoundEvent)var2.getSound().value()).equals(SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT)) {
         this.field_3851.put(new Vec3d(var2.getX(), var2.getY(), var2.getZ()), System.currentTimeMillis());
      }
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      this.field_3851.entrySet().removeIf(var0 -> System.currentTimeMillis() - var0.getValue() > 3500L);
   }

   public void method_2(MatrixStack var1, Class_1288 var2, double var3, float var5) {
      RenderUtil.field_2672
         .method_2(
            var1, var2.method_1151().getCenter(), 0.0F, 0.0F, var2.method_1153() + Float.intBitsToFloat(1065353216), var5, var3, this.field_3829.getValue()
         );
   }

   public void method_2(Class_0978 var1) {
      float var2 = Float.intBitsToFloat(1061158912);
      Vec3d var3 = field_4219.getEntityRenderDispatcher().camera.getPos();
      MatrixStack var4 = var1.method_10();
      BufferBuilder var5 = Tessellator.getInstance().begin(DrawMode.DEBUG_LINES, VertexFormats.POSITION_COLOR);
      RenderSystem.setShader(GameRenderer::getPositionColorProgram);
      RenderSystem.enableDepthTest();
      RenderSystem.enableBlend();
      ChunkLightingView var6 = field_4219.world.getLightingProvider().get(LightType.BLOCK);

      for (BlockPos var8 : Class_1225.method_2(field_4219.player.getEyePos(), Float.intBitsToFloat(1094713344), true)) {
         BlockState var9 = field_4219.world.getBlockState(var8);
         if (Class_1225.method_14(var8.up()) && var6.getLightLevel(var8.up()) <= 7) {
            boolean var10 = field_4219.world.getBiome(var8).matchesKey(BiomeKeys.MUSHROOM_FIELDS);
            if (var9.allowsSpawning(field_4219.world, var8, EntityType.ZOMBIE) && !var10) {
               double var11 = field_4219.gameRenderer.getCamera().getPos().distanceTo(var8.toCenterPos());
               float var13 = Float.intBitsToFloat(1065353216)
                  - (float)MathHelper.clamp(
                     (var11 - Double.longBitsToDouble(4620693217682128896L)) / Double.longBitsToDouble(4611686018427387904L),
                     0.0,
                     Double.longBitsToDouble(4607182418800017408L)
                  );
               int var14 = Color.red.hashCode();
               if (var11 >= Double.longBitsToDouble(4620693217682128896L)) {
                  var14 = Class_1081.method_9(Color.red, var13);
               }

               var4.push();
               var4.translate(
                  (double)var8.getX() - var3.x, (double)var8.getY() - var3.y + Double.longBitsToDouble(4607272490792564818L), (double)var8.getZ() - var3.z
               );
               Matrix4f var15 = var4.peek().getPositionMatrix();
               var5.vertex(var15, Float.intBitsToFloat(1065353216) - var2, 0.0F, Float.intBitsToFloat(1065353216) - var2).color(var14);
               var5.vertex(var15, var2, 0.0F, var2).color(var14);
               var5.vertex(var15, var2, 0.0F, Float.intBitsToFloat(1065353216) - var2).color(var14);
               var5.vertex(var15, Float.intBitsToFloat(1065353216) - var2, 0.0F, var2).color(var14);
               var4.pop();
            }
         }
      }

      BuiltBuffer var16 = var5.endNullable();
      if (var16 != null) {
         BufferRenderer.drawWithGlobalProgram(var16);
         RenderSystem.disableDepthTest();
         RenderSystem.disableBlend();
      }
   }

   public boolean method_1065() {
      return this.field_3849;
   }
}
