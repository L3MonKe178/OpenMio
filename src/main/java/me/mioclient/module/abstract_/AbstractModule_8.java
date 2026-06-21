package me.mioclient.module.abstract_;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import me.mioclient.Hub;
import me.mioclient.api.Class_0468;
import me.mioclient.event.Event_50;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.PlayerUtil;
import me.mioclient.internal.Class_0382;
import me.mioclient.internal.RotationManager;
import me.mioclient.internal.Class_0922;
import me.mioclient.internal.Class_1035;
import me.mioclient.mixin.ducks.DuckLivingEntity;
import me.mioclient.module.Category;
import me.mioclient.setting.BooleanSetting;
import me.mioclient.setting.CustomSetting3;
import me.mioclient.setting.Setting;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import nick.Settings;

public class AbstractModule_8 extends AbstractModule_32 {
   public Setting<Float> field_2281 = this.add(
      new CustomSetting3<>("Range", Float.intBitsToFloat(1084227584), Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1090519040)).method_22("m")
   );
   public Setting<Boolean> field_2282 = this.add(new BooleanSetting("Sequential", false));
   public Setting<Boolean> field_2283 = this.add(new BooleanSetting("Targets", true).method_220());
   public Setting<Boolean> field_2284 = this.add(new BooleanSetting("Head", true).method_2(this.field_2283));
   public Setting<Boolean> field_2285 = this.add(new BooleanSetting("Face", true).method_2(this.field_2283));
   public Setting<Boolean> field_2286 = this.add(new BooleanSetting("Legs", false).method_2(this.field_2283));
   public Setting<Boolean> field_2287 = this.add(new BooleanSetting("AntiStep", false));
   public Setting<Boolean> field_2288 = this.add(new BooleanSetting("IgnoreNakeds", false));
   public Setting<Boolean> field_2289 = this.add(new BooleanSetting("OnlyObby", false), this.field_4251);
   public PlayerEntity field_2290;

   public AbstractModule_8(String var1, String var2, Category var3) {
      super(var1, var2, var3);
   }

   public AbstractModule_8() {
      super("AutoTrap", "Traps your enemies with obby.", Category.COMBAT);
      Settings.initialize(this);
      this.method_5(true);
      this.method_185();
      this.field_4255.method_39(false);
   }

   @Override
   public String getInfo() {
      return this.field_2290 != null ? this.field_2290.getGameProfile().getName() : null;
   }

   @Override
   public List<BlockPos> method_17() {
      this.field_2290 = this.method_691();
      if (this.field_2290 == null
         || Math.hypot(this.field_2290.getX() - this.field_2290.prevX, this.field_2290.getZ() - this.field_2290.prevZ)
               * Double.longBitsToDouble(4626322717216342016L)
               * Double.longBitsToDouble(4615288898129284301L)
            > Double.longBitsToDouble(4626322717216342016L)) {
         return Collections.emptyList();
      } else if (Math.abs(this.field_2290.prevY - this.field_2290.getY()) > Double.longBitsToDouble(4603579539098121011L)) {
         return Collections.emptyList();
      } else {
         Vec3d var1 = this.method_14(this.field_2290);
         List<BlockPos> var2 = this.method_2(new ArrayList<>(), BlockPos.ofFloored(var1), 0);
         var2.removeIf(this::method_157);
         var2.sort(Comparator.comparing(var0 -> -var0.getY()));
         if (this.field_4251.getValue()) {
            var2.sort(Comparator.comparing(var0 -> -field_4219.player.getPos().distanceTo(var0.toCenterPos())));
         }

         return var2;
      }
   }

   @Override
   public int method_34() {
      return this.field_2289.getValue() ? PlayerUtil.method_5(Items.OBSIDIAN) : super.method_34();
   }

   @Subscribe
   public void method_2(Event_50 var1) {
      if (this.field_2282.getValue()) {
         this.method_33();
      }
   }

   public List<BlockPos> method_2(List<Integer> var1, BlockPos var2, int var3) {
      ArrayList var4 = new ArrayList();
      var1.add(var2.hashCode());

      for (Vec3d var6 : this.method_2(var2, var3, this.field_2290.getHeight())) {
         BlockPos var7 = var2.add((int)var6.x, (int)var6.y, (int)var6.z);
         if (field_4219.world.getBlockState(var7).isReplaceable()) {
            if (!field_4219.world.getEntitiesByClass(PlayerEntity.class, new Box(var7), var0 -> true).isEmpty()
               && !var1.contains(var7.hashCode())
               && var6.y == (double)var3) {
               var4.addAll(this.method_2(var1, var7, var3));
            } else {
               Direction var8 = this.method_2(var7, this.field_4250.getValue(), var4);
               if (var8 == null && !this.field_4249.getValue()) {
                  BlockPos var9 = this.method_2(var7, var4);
                  if (var9 != null) {
                     var4.add(var9);
                  }
               }

               var4.add(var7);
            }
         }
      }

      return var4;
   }

   public BlockPos method_2(BlockPos var1, List<BlockPos> var2) {
      return Arrays.stream(Direction.values())
         .<BlockPos>map(var1::offset)
         .sorted(Comparator.comparing(this::method_294))
         .filter(var2x -> this.method_2(var2x, this.field_4250.getValue(), var2) != null && Class_1035.method_9(var2x, true, this.field_4253.getValue()))
         .findFirst()
         .orElse(null);
   }

   public double method_294(BlockPos var1) {
      return this.field_4251.getValue() ? -field_4219.player.getPos().squaredDistanceTo(var1.toCenterPos()) : (double)var1.getY();
   }

   public boolean method_157(BlockPos var1) {
      if (this.field_2290 == null) {
         return true;
      } else {
         Box var2 = new Box(var1.up());

         for (Entity var4 : field_4219.world.getEntities()) {
            if (var4 instanceof Class_0468 var5
               && !var5.isMioAttacked()
               && var4.getBoundingBox().intersects(var2)
               && BlockPos.ofFloored(var4.getPos()).equals(var1.up())) {
               return true;
            }
         }

         return field_4219.player.getEyePos().distanceTo(var1.toCenterPos()) > (double)(this.field_2281.getValue() != null ? this.field_2281.getValue().floatValue() : 0.0f)
            ? true
            : !this.field_2286.getValue() && var1.getY() == this.field_2290.getBlockPos().getY() && !field_4219.world.getBlockState(var1.up()).isReplaceable();
      }
   }

   public Direction method_2(BlockPos var1, boolean var2, List<BlockPos> var3) {
      for (Direction var7 : Direction.values()) {
         BlockPos var8 = var1.offset(var7);
         if ((!field_4219.world.getBlockState(var8).isReplaceable() || var3.contains(var8))
            && (!var2 || Class_1035.method_39(var8).contains(var7.getOpposite()))) {
            return var7;
         }
      }

      return null;
   }

   public List<Vec3d> method_2(BlockPos var1, int var2, float var3) {
      ArrayList var4 = new ArrayList();
      if (this.method_692()) {
         var4.add(new Vec3d(0.0, (double)((float)var2 + var3 + Float.intBitsToFloat(1065353216)), 0.0));
         Iterator var5 = this.method_9(var2, var3).iterator();
         if (var5.hasNext()) {
            Vec3d var6 = (Vec3d)var5.next();
            BlockPos var7 = BlockPos.ofFloored(var6).add(var1);
            if (field_4219.world.getBlockState(var7).isReplaceable()
               && Class_1035.method_2(var7, this.field_4250.getValue(), this.field_4251.getValue()) == null) {
               if (var6.getY() > (double)var2) {
                  var4.add(new Vec3d(Double.longBitsToDouble(-4616189618054758400L), (double)(var2 + 1), 0.0));
               } else {
                  var4.add(new Vec3d(Double.longBitsToDouble(-4616189618054758400L), (double)var2, 0.0));
               }
            }
         }
      } else if (this.field_2284.getValue()) {
         var4.add(new Vec3d(0.0, (double)((float)var2 + var3 + Float.intBitsToFloat(1065353216)), 0.0));
      }

      for (Vec3d var9 : this.method_9(var2, var3)) {
         if (!(var9.getY() > (double)var3)
            && (this.field_2285.getValue() || !(var9.getY() > (double)var2))
            && (this.field_2286.getValue() || var9.getY() != (double)var2)) {
            var4.add(var9);
         }
      }

      if (this.field_2287.getValue()) {
         var4.add(new Vec3d(0.0, (double)((float)var2 + var3 + Float.intBitsToFloat(1073741824)), 0.0));
      }

      return var4;
   }

   public List<Vec3d> method_9(int var1, float var2) {
      return Arrays.asList(
         new Vec3d(0.0, (double)((float)var1 + var2 + Float.intBitsToFloat(1065353216)), 0.0),
         new Vec3d(Double.longBitsToDouble(4607182418800017408L), (double)var1, 0.0),
         new Vec3d(0.0, (double)var1, Double.longBitsToDouble(4607182418800017408L)),
         new Vec3d(0.0, (double)var1, Double.longBitsToDouble(-4616189618054758400L)),
         new Vec3d(Double.longBitsToDouble(-4616189618054758400L), (double)var1, 0.0),
         new Vec3d(Double.longBitsToDouble(4607182418800017408L), (double)(var1 + 1), 0.0),
         new Vec3d(0.0, (double)(var1 + 1), Double.longBitsToDouble(4607182418800017408L)),
         new Vec3d(0.0, (double)(var1 + 1), Double.longBitsToDouble(-4616189618054758400L)),
         new Vec3d(Double.longBitsToDouble(-4616189618054758400L), (double)(var1 + 1), 0.0)
      );
   }

   public boolean method_36(PlayerEntity var1) {
      if (var1.isDead() || !var1.isAlive() || var1 == field_4219.player) {
         return false;
      } else if (Hub.field_2603.method_30(var1)) {
         return false;
      } else {
         return this.field_2288.getValue() && !Class_0382.method_29((LivingEntity)var1)
            ? false
            : var1.distanceTo(field_4219.player) <= this.field_2281.getValue() && var1.isOnGround();
      }
   }

   public Vec3d method_14(PlayerEntity var1) {
      Vec3d var2 = var1.getPos();
      if (!(this.field_2290 instanceof Class_0922) && !(this.field_2290 instanceof ClientPlayerEntity)) {
         DuckLivingEntity var3 = (DuckLivingEntity)this.field_2290;
         var2 = new Vec3d(var3.mio$getServerX(), var3.mio$getServerY(), var3.mio$getServerZ());
      }

      return var2;
   }

   public PlayerEntity method_691() {
      return field_4219.world
         .getPlayers()
         .stream()
         .filter(this::method_36)
         .min(Comparator.comparing(var0 -> MathHelper.angleBetween(field_4219.player.getYaw(), RotationManager.method_14(var0)[0])))
         .orElse(null);
   }

   public boolean method_692() {
      return this.field_2284.getValue() && !this.field_2285.getValue();
   }

   @Override
   public int method_52() {
      return 150;
   }
}
