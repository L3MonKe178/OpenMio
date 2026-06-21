package me.mioclient.module.render;

import java.awt.Color;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import me.mioclient.Hub;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_20;
import me.mioclient.event.Event_3;
import me.mioclient.event.Event_4;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0032;
import me.mioclient.internal.Class_0211;
import me.mioclient.internal.Class_0242;
import me.mioclient.internal.Class_0396;
import me.mioclient.internal.Class_0485;
import me.mioclient.internal.Class_0612;
import me.mioclient.internal.Class_0719;
import me.mioclient.internal.Class_0838;
import me.mioclient.internal.Class_1081;
import me.mioclient.internal.Class_1225;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.abstract_.AbstractModule_21;
import me.mioclient.setting.Setting;
import net.minecraft.block.BedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor.Brightness;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.network.packet.s2c.play.PlayerRespawnS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import nick.Settings;

public class SearchModule extends Module {
   public static AbstractModule_21 field_521 = Hub.field_2595.method_78(AbstractModule_21.class);
   public static final Mutable field_2076 = new Mutable();
   public Setting<Set<Block>> field_2077;
   public Setting<Set<EntityType<?>>> field_2078;
   public Setting<Boolean> field_2079;
   public Setting<Boolean> field_2080;
   public Setting<Boolean> field_2081;
   public Setting<Float> field_2082;
   public Setting<Boolean> field_2083;
   public Setting<Boolean> field_2084;
   public Setting<Float> field_2085;
   public Setting<Boolean> field_2086;
   public Setting<Class_0211> field_2087;
   public Setting<Float> field_2088;
   public Setting<Boolean> field_2089;
   public Setting<Boolean> field_2090;
   public Setting<Boolean> field_2091;
   public Setting<Boolean> field_2092;
   public Setting<Boolean> field_2093;
   public Setting<Boolean> field_2094;
   public final Class_0242 field_2095;
   public final Set<BlockPos> field_2096;
   public final Class_0032 field_2097;
   public boolean field_2098;

   public SearchModule() {
      super("Search", "Helps finding whitelisted stuff.", Category.RENDER);
      Settings.initialize(this);
      this.field_2095 = new Class_0242();
      this.field_2096 = Collections.synchronizedSet(new HashSet<>());
      this.field_2097 = new Class_0032(
         1,
         (var1, var2) -> {
            if (this.field_2096.size() <= 100000) {
               Set var3 = this.field_2077.getValue();

               for (int var4 = 0; var4 < 16; var4++) {
                  for (int var5 = this.field_2080.getValue() ? 0 : field_4219.world.getBottomY(); var5 < field_4219.world.getTopY(); var5++) {
                     for (int var6 = 0; var6 < 16; var6++) {
                        field_2076.set(var1.getStartX() + var4, var5, var1.getStartZ() + var6);
                        BlockState var7 = var2.getBlockState(field_2076);
                        if (!var7.isAir()
                           && (
                              !this.field_2089.getValue()
                                 || !Class_1225.method_2(
                                    var2,
                                    field_2076,
                                    this.field_2091.getValue(),
                                    this.field_2092.getValue(),
                                    this.field_2093.getValue(),
                                    this.field_2090.getValue(),
                                    this.field_2094.getValue()
                                 )
                           )
                           && var3.contains(var7.getBlock())) {
                           this.field_2098 = true;
                           this.field_2096.add(field_2076.toImmutable());
                        }
                     }
                  }
               }
            }
         },
         (var1, var2) -> this.field_2096
               .removeIf(
                  var1x -> var1x.getX() >= var1.getStartX()
                        && var1x.getX() <= var1.getEndX()
                        && var1x.getZ() >= var1.getStartZ()
                        && var1x.getZ() <= var1.getEndZ()
               ),
         (var1, var2) -> {
            if (this.field_2077.getValue().contains(var2.getBlock())) {
               if (var2.isAir()) {
                  return;
               }

               if (this.field_2096.add(var1)) {
                  this.field_2098 = true;
               }
            } else {
               this.field_2096.remove(var1);
            }
         }
      );
      this.field_2077.method_9(() -> {
         if (field_4219.world != null && this.field_2097.method_51() != null) {
            this.field_2096.clear();
            this.field_2097.method_50();
         }
      });
   }

   @Override
   public void onEnable() {
      this.field_2098 = false;
      this.field_2097.method_47();
      if (!this.method_535()) {
         this.field_2097.method_50();
      }
   }

   @Override
   public void onDisable() {
      this.field_2096.clear();
      this.field_2097.method_48();
   }

   @Subscribe
   public void method_2(Event_20 var1) {
      this.method_660();
   }

   @Subscribe
   public void method_5(Event_4 var1) {
      if (var1.method_127() instanceof PlayerRespawnS2CPacket) {
         this.method_660();
      }
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if (this.field_2086.getValue() && this.field_2098) {
         this.field_2098 = false;
         if (this.field_2095.method_9(100L)) {
            Hub.field_2606.method_2(this.field_2087.getValue()).method_230(this.field_2088.getValue());
            this.field_2095.reset();
         }
      }
   }

   @Subscribe
   public void method_9(Event_3 var1) {
      if (this.field_2079.getValue()) {
         for (Entity var3 : field_4219.world.getEntities()) {
            if (this.field_2078.getValue().contains(var3.getType())) {
               Box var4 = Class_0719.method_2(var3, var1.method_880());
               Color var5 = this.method_251(var3);
               field_521.field_2133
                  .getValue()
                  .method_5(
                     () -> {
                        if (Class_0485.method_4(var4)) {
                           if (this.field_2081.getValue()) {
                              Class_0612.method_5(
                                 var1.method_10(), var4, Class_1081.method_9(var5, (int)(Float.intBitsToFloat(1132396544) * this.field_2082.getValue()))
                              );
                           }

                           if (this.field_2083.getValue()) {
                              Class_0612.method_2(var1.method_10(), var4, var5, Float.intBitsToFloat(1065353216));
                           }
                        }

                        if (this.field_2084.getValue()) {
                           Camera var4x = field_4219.gameRenderer.getCamera();
                           Vec3d var5x = new Vec3d(0.0, 0.0, Double.longBitsToDouble(4607182418800017408L))
                              .rotateX(-((float)Math.toRadians((double)var4x.getPitch())))
                              .rotateY(-((float)Math.toRadians((double)var4x.getYaw())))
                              .add(field_4219.getEntityRenderDispatcher().camera.getPos());
                           Class_0838.field_2672
                              .method_2(
                                 var1.method_10(),
                                 var5x,
                                 var4.getCenter(),
                                 Class_1081.method_9(var5, (int)(Float.intBitsToFloat(1132396544) * this.field_2085.getValue()))
                              );
                        }
                     }
                  );
            }
         }
      }

      synchronized (this.field_2096) {
         for (BlockPos var13 : this.field_2096) {
            BlockState var14 = field_4219.world.getBlockState(var13);
            if (!var14.isAir() && !var14.isOf(Blocks.AIR)) {
               VoxelShape var6 = var14.getOutlineShape(field_4219.world, var13);
               if (var6.isEmpty()) {
                  var6 = VoxelShapes.cuboid(
                     0.0,
                     0.0,
                     0.0,
                     Double.longBitsToDouble(4607182418800017408L),
                     Double.longBitsToDouble(4607182418800017408L),
                     Double.longBitsToDouble(4607182418800017408L)
                  );
               }

               Box var7 = var6.getBoundingBox().offset(var13);
               Color var8 = this.method_9(var14, var13);
               field_521.field_2133
                  .getValue()
                  .method_5(
                     () -> {
                        if (Class_0485.method_4(var7)) {
                           if (this.field_2081.getValue()) {
                              Class_0612.method_5(
                                 var1.method_10(), var7, Class_1081.method_9(var8, (int)(Float.intBitsToFloat(1132396544) * this.field_2082.getValue()))
                              );
                           }

                           if (this.field_2083.getValue()) {
                              Class_0612.method_2(var1.method_10(), var7, var8, Float.intBitsToFloat(1065353216));
                           }
                        }

                        if (this.field_2084.getValue()) {
                           Camera var5x = field_4219.gameRenderer.getCamera();
                           Vec3d var6x = new Vec3d(0.0, 0.0, Double.longBitsToDouble(4607182418800017408L))
                              .rotateX(-((float)Math.toRadians((double)var5x.getPitch())))
                              .rotateY(-((float)Math.toRadians((double)var5x.getYaw())))
                              .add(field_4219.getEntityRenderDispatcher().camera.getPos());
                           Class_0838.field_2672
                              .method_2(
                                 var1.method_10(),
                                 var6x,
                                 Vec3d.ofCenter(var13),
                                 Class_1081.method_9(var8, (int)(Float.intBitsToFloat(1132396544) * this.field_2085.getValue()))
                              );
                        }
                     }
                  );
            }
         }
      }
   }

   public void method_660() {
      this.field_2097.method_49();
      this.field_2096.clear();
   }

   public Color method_9(BlockState var1, BlockPos var2) {
      if (var1.getBlock() == Blocks.NETHER_PORTAL) {
         return new Color(144, 28, 255);
      } else if (var1.getBlock() == Blocks.ENDER_CHEST) {
         return new Color(125, 40, 180);
      } else {
         int var3 = var1.getMapColor(field_4219.world, var2).getRenderColor(Brightness.HIGH);
         if (var1.getBlock() instanceof BedBlock var4) {
            var3 = var4.getColor().getMapColor().getRenderColor(Brightness.HIGH);
         }

         int var7 = var3 >> 16 & 0xFF;
         int var8 = var3 >> 8 & 0xFF;
         int var6 = var3 & 0xFF;
         return new Color(var6, var8, var7);
      }
   }

   public Color method_251(Entity var1) {
      Color var2 = Color.GRAY;
      if (var1 instanceof AbstractMinecartEntity) {
         var2 = Color.blue;
      }

      if (Class_0396.method_9(var1)) {
         var2 = Color.RED;
      }

      if (var1 instanceof PlayerEntity var3) {
         var2 = Hub.field_2603.method_9(var3.getName().getString(), var2);
      }

      if (var1 instanceof AnimalEntity || var1 instanceof FishEntity) {
         var2 = Color.GREEN;
      }

      return var2;
   }
}
