package me.mioclient.module.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.GlStateManager.DstFactor;
import com.mojang.blaze3d.platform.GlStateManager.SrcFactor;
import com.mojang.blaze3d.systems.RenderSystem;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import me.mioclient.Hub;
import me.mioclient.api.Class_0415;
import me.mioclient.enum_.Class_1025;
import me.mioclient.enum_.Side2;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_27;
import me.mioclient.event.Event_3;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Constants;
import me.mioclient.internal.Class_0396;
import me.mioclient.internal.RotationManager;
import me.mioclient.internal.Class_0714;
import me.mioclient.internal.Class_0745;
import me.mioclient.internal.Class_0756;
import me.mioclient.internal.RenderUtil;
import me.mioclient.internal.Class_0930;
import me.mioclient.internal.FontRenderer;
import me.mioclient.internal.Class_1081;
import me.mioclient.internal.TextBuilder;
import me.mioclient.internal.Class_1323;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.client.FontsModule;
import me.mioclient.module.player.FreecamModule;
import me.mioclient.setting.Setting;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.render.DiffuseLighting;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import nick.Settings;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL20;

public class NameTagsModule extends Module {
   public static final Identifier field_1708 = Identifier.of("mio", "textures/mio.png");
   public static AnimationsModule animations = Hub.field_2595.method_78(AnimationsModule.class);
   public static FreecamModule freecam = Hub.field_2595.method_78(FreecamModule.class);
   public static FontsModule field_984 = Hub.field_2595.method_78(FontsModule.class);
   public static boolean field_1709 = false;
   public Setting<Integer> field_1710;
   public Setting<Float> field_1711;
   public Setting<Boolean> field_1712;
   public Setting<Boolean> field_1713;
   public Setting<Boolean> field_1714;
   public Setting<Boolean> field_1715;
   public Setting<Boolean> field_1716;
   public Setting<Boolean> field_1717;
   public Setting<Boolean> field_1718;
   public Setting<Boolean> field_1719;
   public Setting<Boolean> field_1720;
   public Setting<Class_1025> field_1721;
   public Setting<Boolean> field_1722;
   public Setting<Boolean> field_1723;
   public Setting<Boolean> field_1724;
   public Setting<Boolean> field_1725;
   public Setting<Boolean> field_1726;
   public Setting<Boolean> field_1727;
   public Setting<Boolean> field_1728;
   public Setting<Boolean> field_1729;
   public Setting<Boolean> field_1730;
   public Setting<Color> field_1731;
   public Setting<Color> field_1732;
   public Setting<Boolean> field_1733;
   public Setting<Color> field_1734;
   public Setting<Color> field_1735;
   public Setting<Color> field_1736;
   public Setting<Color> field_1737;
   public Setting<Color> field_1738;
   public Setting<Color> field_1739;
   public final Color field_1740;
   public final List<PlayerEntity> field_1741;
   public boolean field_1742;
   public boolean field_1743;
   public boolean field_1744;
   public Vector3f[] field_1745;

   public NameTagsModule() {
      super("NameTags", "Draws advanced nametags for players.", Category.RENDER);
      Settings.initialize(this);
      this.field_1740 = new Color(153, 69, 58);
      this.field_1741 = new ObjectArrayList();
      this.field_1745 = null;
   }

   @Override
   public void onDisable() {
      field_1709 = false;
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      synchronized (this.field_1741) {
         this.field_1741.clear();
         this.field_1741.addAll(field_4219.world.getPlayers());
         this.field_1741.sort(Comparator.comparing(var0 -> field_4219.gameRenderer.getCamera().getPos().squaredDistanceTo(var0.getPos())));
      }
   }

   @Subscribe(
      method_800 = -100
   )
   public void method_9(Event_3 var1) {
      this.field_1745 = Arrays.copyOf(RenderSystem.shaderLightDirections, RenderSystem.shaderLightDirections.length);
      Vec3d var2 = field_4219.gameRenderer.getCamera().getPos();
      synchronized (this.field_1741) {
         for (PlayerEntity var5 : this.field_1741) {
            if ((var5 != field_4219.player || freecam.isToggled()) && (!var5.isDead() || this.field_1712.getValue())) {
               this.field_1742 = Hub.field_2610.method_326() && Hub.field_2610.method_68(var5.getGameProfile().getName()) != null && this.field_1727.getValue();
               boolean var6 = !RotationManager.method_4(var5.getBoundingBox())
                  || field_4219.gameRenderer.getCamera().getPos().distanceTo(var5.getPos()) > (double)(this.field_1710.getValue() != null ? this.field_1710.getValue().intValue() : 0);
               if (!var6) {
                  float var7 = RenderUtil.method_776();
                  double var8 = MathHelper.lerp((double)var7, var5.lastRenderX, var5.getX()) - var2.x;
                  double var10 = MathHelper.lerp((double)var7, var5.lastRenderY, var5.getY()) - var2.y;
                  double var12 = MathHelper.lerp((double)var7, var5.lastRenderZ, var5.getZ()) - var2.z;
                  Vec3d var14 = field_4219.getEntityRenderDispatcher().getRenderer(var5).getPositionOffset(var5, var7);
                  double var15 = var8 + var14.getX();
                  double var17 = var10 + var14.getY();
                  double var19 = var12 + var14.getZ();
                  var1.method_10().push();
                  var1.method_10().translate(var15, var17, var19);
                  this.method_2(var1.method_881(), var1.method_10(), var5);
                  var1.method_10().pop();
               }
            }
         }
      }

      RenderSystem.enablePolygonOffset();
      RenderSystem.polygonOffset(Float.intBitsToFloat(1065353216), Float.intBitsToFloat(-910746880));
      GL20.glDepthRange(0.0, Double.longBitsToDouble(4591870180066957722L));
      RenderSystem.enableBlend();
      RenderSystem.blendFunc(SrcFactor.SRC_ALPHA, DstFactor.ONE_MINUS_SRC_ALPHA);
      DiffuseLighting.disableGuiDepthLighting();
      var1.method_881().getVertexConsumers().draw();
      RenderSystem.setShaderLights(this.field_1745[0], this.field_1745[1]);
      RenderSystem.disableBlend();
      Class_0745.method_30(false);
      RenderSystem.polygonOffset(Float.intBitsToFloat(1065353216), Float.intBitsToFloat(-979615744));
      FontRenderer.field_3143.method_474();
      RenderSystem.polygonOffset(Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1167867904));
      GL20.glDepthRange(0.0, Double.longBitsToDouble(4607182418800017408L));
      RenderSystem.polygonOffset(Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1236736768));
      RenderSystem.disablePolygonOffset();
   }

   @Subscribe
   public void method_2(Event_27 var1) {
      if (var1.method_11() instanceof PlayerEntity) {
         var1.method_463();
      }
   }

   public void method_9(MatrixStack var1, Vec3d var2) {
      RenderSystem.enablePolygonOffset();
      RenderSystem.polygonOffset(Float.intBitsToFloat(1065353216), Float.intBitsToFloat(-913146880));
      GL20.glDepthRange(0.0, Double.longBitsToDouble(4591870180066957722L));

      for (PlayerEntity var4 : this.field_1741) {
         if (field_4219.player != var4) {
            float var5 = (float)Class_0930.method_2(
               field_4219.gameRenderer.getCamera().getPos(), var4.getPos(), (double)(this.field_1711.getValue() != null ? this.field_1711.getValue().floatValue() : 0.0f)
            );
            float var6 = RenderUtil.method_776();
            double var7 = MathHelper.lerp((double)var6, var4.lastRenderX, var4.getX()) - var2.x;
            double var9 = MathHelper.lerp((double)var6, var4.lastRenderY, var4.getY()) - var2.y;
            double var11 = MathHelper.lerp((double)var6, var4.lastRenderZ, var4.getZ()) - var2.z;
            Vec3d var13 = field_4219.getEntityRenderDispatcher().getRenderer(var4).getPositionOffset(var4, var6);
            double var14 = var7 + var13.getX();
            double var16 = var9 + var13.getY();
            double var18 = var11 + var13.getZ();
            var1.push();
            var1.translate(var14, var16, var18);
            var1.push();
            boolean var20 = animations.method_128() && var4 != field_4219.player;
            var18 = (double)(var4.getHeight() * (var20 ? animations.field_346.getValue() : Float.intBitsToFloat(1065353216)) + Float.intBitsToFloat(1056964608));
            var1.translate(0.0, var18, 0.0);
            var1.multiply(field_4219.getEntityRenderDispatcher().getRotation());
            var1.scale(-var5 * Float.intBitsToFloat(1020054733), -var5 * Float.intBitsToFloat(1020054733), var5 * Float.intBitsToFloat(1020054733));
            String var21 = this.method_263(var4);
            float var22 = (float)((double)(-this.method_221(var21)) * Double.longBitsToDouble(4602678819172646912L));
            RenderUtil.field_2672
               .method_9(
                  var1,
                  var22 - Float.intBitsToFloat(1065353216),
                  Float.intBitsToFloat(-1082130432),
                  var22 * Float.intBitsToFloat(-1082130432) + Float.intBitsToFloat(1065353216),
                  this.method_575() + Float.intBitsToFloat(1065353216),
                  Color.white
               );
            var1.pop();
            var1.pop();
         }
      }

      GL20.glDepthRange(0.0, Double.longBitsToDouble(4607182418800017408L));
      RenderSystem.disablePolygonOffset();
      RenderSystem.polygonOffset(Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1234336768));
   }

   public void method_2(DrawContext var1, MatrixStack var2, PlayerEntity var3) {
      this.field_1744 = this.field_1743 = false;
      boolean var4 = animations.method_128() && var3 != field_4219.player;
      float var5 = var3.getHeight() * (var4 ? animations.field_346.getValue() : Float.intBitsToFloat(1065353216)) + Float.intBitsToFloat(1056964608);
      float var6 = (float)Class_0930.method_2(field_4219.gameRenderer.getCamera().getPos(), var3.getPos(), (double)(this.field_1711.getValue() != null ? this.field_1711.getValue().floatValue() : 0.0f));
      var2.push();
      var2.translate(0.0F, var5, 0.0F);
      var2.multiply(field_4219.getEntityRenderDispatcher().getRotation());
      var2.scale(var6 * Float.intBitsToFloat(1020054733), -var6 * Float.intBitsToFloat(1020054733), -var6 * Float.intBitsToFloat(1020054733));
      String var7 = this.method_263(var3);
      float var8 = (float)((double)(-this.method_221(var7)) * Double.longBitsToDouble(4602678819172646912L));
      RenderSystem.enablePolygonOffset();
      RenderSystem.polygonOffset(Float.intBitsToFloat(1065353216), Float.intBitsToFloat(-910746880));
      GL20.glDepthRange(0.0, Double.longBitsToDouble(4591870180066957722L));
      Color var9 = this.field_1732.getValue();
      if (this.field_1733.getValue()) {
         if (this.method_222(var3)) {
            var9 = this.field_1735.getValue();
         } else if (Hub.field_2605.method_221(var3.getBlockPos())) {
            var9 = this.field_1734.getValue();
         }
      }

      float var10 = Float.intBitsToFloat(1056964608);
      Class_0745.method_4(
         var2,
         var8 - Float.intBitsToFloat(1065353216),
         Float.intBitsToFloat(-1082130432),
         var8 * Float.intBitsToFloat(-1082130432) + Float.intBitsToFloat(1065353216),
         this.method_575() + Float.intBitsToFloat(1065353216),
         this.field_1731.getValue()
      );
      Class_0745.method_2(
         var2,
         var8 - Float.intBitsToFloat(1073741824) + var10,
         Float.intBitsToFloat(-1073741824) + var10,
         var8 * Float.intBitsToFloat(-1082130432) + Float.intBitsToFloat(1065353216),
         this.method_575() + Float.intBitsToFloat(1065353216),
         var10,
         var9
      );
      Color var11 = this.field_1736.getValue();
      if (var3.isSneaking()) {
         var11 = this.field_1738.getValue();
      }

      if (var3.isInvisible()) {
         var11 = this.field_1737.getValue();
      }

      if (Hub.field_2603.method_30(var3) && this.field_1729.getValue()) {
         var11 = Hub.field_2603.method_1145();
      }

      if (Hub.field_2603.method_16(var3) && this.field_1730.getValue()) {
         var11 = Hub.field_2603.method_1146();
      }

      boolean var12 = field_984.isToggled();
      float var13 = Float.intBitsToFloat(1065353216);
      FontRenderer.field_3143.method_9(var1, var7, var8, var13, var11);
      field_1709 = true;
      this.method_9(var1, var2, var3);
      field_1709 = false;
      if (this.field_1742) {
         var2.push();
         float var14 = Float.intBitsToFloat(1032847360);
         var2.translate(var8 - Float.intBitsToFloat(1082130432), Float.intBitsToFloat(-1065353216), 0.0F);
         var2.scale(var14, var14, var14);
         if (var12) {
            var2.translate(
               Double.longBitsToDouble(-4616189618054758400L),
               Double.longBitsToDouble(4608533498688228557L) + (double)field_984.field_372.getValue().intValue(),
               0.0
            );
         }

         RenderSystem.setShader(GameRenderer::getPositionTexColorProgram);
         GlStateManager._blendFunc(770, 771);
         GlStateManager._texParameter(3553, 10240, 9729);
         var1.drawTexture(field_1708, 0, 0, 0, 0, 256, 256);
         GlStateManager._texParameter(3553, 10240, 9728);
         var2.pop();
      }

      GL20.glDepthRange(0.0, Double.longBitsToDouble(4607182418800017408L));
      RenderSystem.disablePolygonOffset();
      RenderSystem.polygonOffset(Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1236736768));
      var2.pop();
   }

   public void method_9(DrawContext var1, MatrixStack var2, PlayerEntity var3) {
      ArrayList var4 = new ArrayList();
      if (this.field_1722.getValue() && !var3.getOffHandStack().isEmpty()) {
         var4.add(var3.getOffHandStack());
      }

      for (ItemStack var6 : var3.getArmorItems()) {
         if (!var6.isEmpty() && this.field_1720.getValue()) {
            var4.add(var6);
         }
      }

      if (this.field_1722.getValue() && !var3.getMainHandStack().isEmpty()) {
         var4.add(var3.getMainHandStack());
      }

      int var10 = -3;

      for (ItemStack var7 : (Iterable<ItemStack>)(Iterable<?>)(var4)) {
         int var8 = Class_0756.method_7(var7).size();
         if (this.field_1725.getValue() && Class_0714.method_9(var7)) {
            var8 = 0;
         }

         if (var8 > var10) {
            var10 = var8;
         }
      }

      var10 = Math.max(var10, 0);
      if (this.field_1721.getValue() == Class_1025.ONLY || !this.field_1724.getValue()) {
         var10 = 0;
      }

      float var13 = (float)var10 * this.method_575() * Float.intBitsToFloat(1056964608) / Float.intBitsToFloat(1067030938) / Float.intBitsToFloat(1099563008);
      float var14 = -Math.max(var13, Float.intBitsToFloat(1058642330));

      for (int var15 = 0; var15 < var4.size(); var15++) {
         this.method_2(
            var1,
            var3,
            var2,
            (ItemStack)var4.get(var4.size() - 1 - var15),
            (float)var15 + Float.intBitsToFloat(1056964608) - (float)var4.size() * Float.intBitsToFloat(1056964608),
            var14,
            Float.intBitsToFloat(1099563008)
         );
      }

      if (!var3.getMainHandStack().isEmpty() && this.field_1719.getValue()) {
         float var16 = var14 * Float.intBitsToFloat(1099563008) - Float.intBitsToFloat(1082130432) - this.method_575();
         if (!this.field_1743) {
            var16 = -this.method_575();
         }

         if (this.field_1744) {
            var16 -= this.method_575() * Float.intBitsToFloat(1056964608);
            if (!this.field_1743) {
               var16 -= Float.intBitsToFloat(1065353216);
            }
         }

         String var9 = Formatting.strip(var3.getMainHandStack().getName().getString());
         this.method_2(var1, var9, 0.0F, var16, Double.longBitsToDouble(4627005293803339776L), Color.white, false, Side2.CENTER);
      }
   }

   public void method_2(DrawContext var1, PlayerEntity var2, MatrixStack var3, ItemStack var4, float var5, float var6, float var7) {
      float var8 = Float.intBitsToFloat(1058642330);
      int var9 = Class_1323.method_5(var4);
      if (this.field_1722.getValue()
         && this.field_1723.getValue()
         && var2.isUsingItem()
         && var2.getStackInHand(var2.getActiveHand()) == var4
         && var4.contains(DataComponentTypes.FOOD)) {
         float var10 = var6 * var7 + Float.intBitsToFloat(1089575322);
         float var11 = var6 * var7 - Float.intBitsToFloat(1089575322);
         long var12 = System.currentTimeMillis() - ((Class_0415)var2).mio$getLastEatingTime();
         float var14 = Float.intBitsToFloat(1063675494)
            * MathHelper.clamp(
               (float)var12 / ((float)var4.getMaxUseTime(var2) * Float.intBitsToFloat(1112014848) + Float.intBitsToFloat(1112014848)),
               0.0F,
               Float.intBitsToFloat(1065353216)
            );
         Class_0745.method_9(
            var3,
            (var5 - Float.intBitsToFloat(1055286886)) * var7,
            var11,
            (var5 - Float.intBitsToFloat(1055286886) + var14) * var7,
            var10,
            Class_1081.method_9(this.field_1739.getValue(), Float.intBitsToFloat(1045220557))
         );
      }

      boolean var19 = false;
      if (this.field_1721.getValue() != Class_1025.ONLY || !(var4.getItem() instanceof ArmorItem) && !var4.isOf(Items.ELYTRA)) {
         var19 = true;
         this.field_1743 = true;
         RenderSystem.polygonOffset(Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1167867904));
         var3.push();
         var3.translate(var5 * var7, var6 * var7, 0.0F);
         var3.scale(-var7, -var7, Float.intBitsToFloat(981668463));
         BakedModel var20 = field_4219.getItemRenderer().getModel(var4, field_4219.world, null, 0);
         field_4219.getItemRenderer()
            .renderItem(var4, ModelTransformationMode.FIXED, false, var3, var1.getVertexConsumers(), 15728880, OverlayTexture.DEFAULT_UV, var20);
         var3.pop();
         RenderSystem.polygonOffset(Float.intBitsToFloat(1065353216), Float.intBitsToFloat(-979615744));
      }

      if (var4.getCount() > 1) {
         this.method_2(
            var1,
            String.valueOf(var4.getCount()),
            (var5 + Float.intBitsToFloat(1056964608)) * var7,
            var6 * var7 + Float.intBitsToFloat(1065353216),
            (double)var7 * Double.longBitsToDouble(4612248968380809216L),
            Color.white,
            true,
            Side2.RIGHT
         );
      }

      if (var4.isItemBarVisible() && var19) {
         float var21 = (float)((int)(var5 + Float.intBitsToFloat(1045220557))) * var7;
         float var23 = var6 * var7 + Float.intBitsToFloat(1085276160);
         Class_0745.method_4(
            var3,
            (var5 - Float.intBitsToFloat(1055286886)) * var7,
            var23 - Float.intBitsToFloat(1028443341),
            (var5 + Float.intBitsToFloat(1055286886)) * var7,
            var23 + Float.intBitsToFloat(1073741824),
            new Color(-16777216)
         );
         Class_0745.method_4(
            var3,
            (var5 - Float.intBitsToFloat(1055286886)) * var7,
            var23 - Float.intBitsToFloat(1028443341),
            (
                  var5
                     + MathHelper.lerp(
                        (float)var4.getItemBarStep() / Float.intBitsToFloat(1095761920), Float.intBitsToFloat(-1092196762), Float.intBitsToFloat(1055286886)
                     )
               )
               * var7,
            var23 + Float.intBitsToFloat(1065772646),
            new Color(var4.getItemBarColor(), false)
         );
      }

      Set var22 = var4.getEnchantments().getEnchantmentEntries();
      float var24 = -var8 * Float.intBitsToFloat(1073741824);
      if (var4.isOf(Items.ENCHANTED_GOLDEN_APPLE) && var19) {
         this.method_2(
            var1,
            "God",
            var5 * var7,
            var6 * var7 - Float.intBitsToFloat(1065353216) + this.method_575() * var8 * var24,
            (double)(var7 * Float.intBitsToFloat(1067869798)),
            this.field_1740,
            false,
            Side2.RIGHT
         );
      } else {
         if (var19 && this.field_1724.getValue()) {
            boolean var13 = this.field_1725.getValue() && Class_0714.method_9(var4);
            if (var13) {
               this.method_2(
                  var1,
                  "Max",
                  (var5 - Float.intBitsToFloat(1056964608)) * var7,
                  var6 * var7 - Float.intBitsToFloat(1073741824) + this.method_575() * var8 / Float.intBitsToFloat(1067030938) * var24,
                  (double)(var7 * Float.intBitsToFloat(1067869798)),
                  Color.RED,
                  false,
                  Side2.LEFT
               );
            }

            for (Entry var15 : (Iterable<Entry>)(Iterable<?>)(var22)) {
               if (var13) {
                  break;
               }

               Enchantment var16 = (Enchantment)((RegistryEntry)var15.getKey()).value();
               boolean var17 = var16.getMaxLevel() == 1;
               String var18 = Enchantment.getName((RegistryEntry)var15.getKey(), var15.getIntValue()).getString();
               var18 = var18.substring(0, Math.min(var17 ? 3 : 2, var18.length()));
               if (!var18.equalsIgnoreCase("Cu")) {
                  if (!var17) {
                     var18 = new TextBuilder().method_2(var15.getIntValue()).method_2((Object)var18).method_9("\u0001\u0001");
                  }

                  this.method_2(
                     var1,
                     var18,
                     (var5 - Float.intBitsToFloat(1056964608)) * var7,
                     var6 * var7 - Float.intBitsToFloat(1073741824) + this.method_575() * var8 / Float.intBitsToFloat(1067030938) * var24,
                     (double)(var7 * Float.intBitsToFloat(1067869798)),
                     Color.WHITE,
                     false,
                     Side2.LEFT
                  );
                  var24 += Float.intBitsToFloat(1065353216);
               }
            }
         }

         if (var4.isDamageable() && this.field_1721.getValue() != Class_1025.HIDE) {
            float var25 = var6 * var7 - Float.intBitsToFloat(1082130432) - this.method_575();
            if (!var19) {
               var25 = var6 - this.method_575();
            }

            this.field_1744 = true;
            this.method_2(
               var1,
               String.format("%d%s", var9, "%"),
               (float)(((double)var5 - Constants.field_688) * (double)var7) + Float.intBitsToFloat(1065353216),
               var25,
               (double)(var7 * Float.intBitsToFloat(1069547520)),
               new Color(var4.getItemBarColor(), false),
               false,
               Side2.LEFT
            );
         }
      }
   }

   public void method_2(DrawContext var1, String var2, float var3, float var4, double var5, Color var7, boolean var8, Side2 var9) {
      MatrixStack var10 = var1.getMatrices();
      var10.push();
      var10.translate(var3, var4, 0.0F);
      var10.scale(Float.intBitsToFloat(1020054733) * (float)var5, Float.intBitsToFloat(1020054733) * (float)var5, Float.intBitsToFloat(1065353216));
      float var11 = var9.method_9(var2, var8);
      if (var8) {
         Class_0745.method_2(var1.getMatrices(), var2, (int)var11, 0, var7.hashCode(), true);
      } else {
         FontRenderer.field_3143.method_9(var1, var2, var11, 0.0F, var7);
      }

      var10.pop();
   }

   public String method_263(PlayerEntity var1) {
      StringBuilder var2 = new StringBuilder();
      if (this.field_1742) {
         var2.append(Formatting.RESET);
         var2.append("   ");
      }

      if (this.field_1714.getValue()) {
         var2.append(Hub.field_2626.method_8(var1.getGameProfile().getName()));
      }

      if (this.field_1718.getValue()) {
         var2.append(" [");

         try {
            PlayerListEntry var3 = field_4219.player.networkHandler.getPlayerListEntry(var1.getGameProfile().getId());
            var2.append(var3 == null ? "S" : this.method_574(var3.getGameMode().getName()));
         } catch (Throwable var5) {
            var2.append(0);
         }

         var2.append("]");
      }

      if (this.field_1715.getValue()) {
         var2.append(" ");

         try {
            PlayerListEntry var6 = field_4219.player.networkHandler.getPlayerListEntry(var1.getGameProfile().getId());
            var2.append(var6 == null ? 0 : var6.getLatency());
         } catch (Throwable var4) {
            var2.append(0);
         }

         var2.append("ms");
      }

      if (this.field_1716.getValue()) {
         float var7 = Class_0396.method_2((net.minecraft.entity.Entity)var1);
         if (this.field_1717.getValue()) {
            var2.append(this.method_4((double)var7));
         }

         var2.append(" ").append(String.format("%.1f", var7)).append(Formatting.RESET);
      }

      if (this.field_1726.getValue()) {
         int var8 = Hub.field_2613.method_39(var1);
         if (var8 > 0) {
            var2.append(method_323(var8)).append(" -").append(var8);
         }
      }

      return var2.toString().trim();
   }

   public Formatting method_4(double var1) {
      if (var1 >= Double.longBitsToDouble(4626322717216342016L)) {
         return Formatting.GREEN;
      } else if (var1 >= Double.longBitsToDouble(4625196817309499392L)) {
         return Formatting.DARK_GREEN;
      } else if (var1 >= Double.longBitsToDouble(4621819117588971520L)) {
         return Formatting.GOLD;
      } else {
         return var1 >= Double.longBitsToDouble(4616189618054758400L) ? Formatting.RED : Formatting.DARK_RED;
      }
   }

   public static Formatting method_323(int var0) {
      if (var0 > 6) {
         return Formatting.DARK_RED;
      } else {
         return var0 > 3 ? Formatting.GOLD : Formatting.YELLOW;
      }
   }

   public String method_574(String var1) {
      String var2 = var1.toLowerCase(Locale.ROOT);

      return switch (var2) {
         case "survival" -> "S";
         case "creative" -> "C";
         case "adventure" -> "A";
         case "spectator" -> "SP";
         default -> "";
      };
   }

   public float method_575() {
      return (float)FontRenderer.field_3143.method_66();
   }

   public float method_221(String var1) {
      return FontRenderer.field_3143.method_221(var1);
   }

   public boolean method_222(Entity var1) {
      float var2 = var1.getDimensions(var1.getPose()).width() * Float.intBitsToFloat(1061997773);
      Vec3d var3 = var1.getBoundingBox().getCenter();
      Box var4 = Box.of(var3, (double)var2, (double)(var1.getHeight() * Float.intBitsToFloat(1056964608)), (double)var2);
      return BlockPos.stream(var4).anyMatch(var0 -> {
         BlockState var1x = field_4219.world.getBlockState(var0);
         return !var1x.isAir() && var1x.shouldSuffocate(field_4219.world, var0);
      });
   }
}
