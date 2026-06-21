package me.mioclient.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Map.Entry;
import me.mioclient.Hub;
import me.mioclient.api.MioAPI;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_10;
import me.mioclient.event.Event_17;
import me.mioclient.event.Event_20;
import me.mioclient.event.Event_34;
import me.mioclient.event.Event_35;
import me.mioclient.event.Event_4;
import me.mioclient.event.Subscribe;
import me.mioclient.mixin.ducks.DuckAbstractBlock;
import me.mioclient.record.Class_0944;
import me.mioclient.record.Class_1003;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.entity.projectile.thrown.ExperienceBottleEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.network.packet.s2c.play.BlockUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.ChunkDeltaUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.ChunkLoadDistanceS2CPacket;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.shape.VoxelShapes;

public class Class_0303 implements MioAPI {
   public final List<BlockEntity> field_970 = Collections.synchronizedList(new ArrayList<>());
   public final Map<BlockPos, Class_0944> field_971 = Collections.synchronizedMap(new HashMap<>());
   public final Set<BlockPos> field_972 = Collections.synchronizedSet(new HashSet<>());
   public final Set<BlockPos> field_973 = Collections.synchronizedSet(new HashSet<>());
   public final Set<Class_1003> field_974 = Collections.synchronizedSet(new HashSet<>());
   @Deprecated
   public BlockPos unconfirmedBreak = null;
   public ItemStack field_975;
   public ItemStack field_976;
   public final Timer field_977 = new Timer();
   public int field_978;

   public Class_0303() {
      super();
      field_4220.method_14(this);
   }

   @Subscribe
   public void method_2(Event_20 var1) {
      this.field_971.clear();
      this.field_972.clear();
      this.field_970.clear();
      this.field_974.clear();
   }

   @Subscribe(
      method_800 = 200
   )
   public void method_2(Event_17 var1) {
      this.method_346();
   }

   @Subscribe(
      method_800 = -200
   )
   public void method_2(Event_1 var1) {
      this.field_973.clear();
      if (this.field_977.method_9(150L)) {
         this.unconfirmedBreak = null;
      }

      this.field_972.removeIf(var0 -> !var0.isWithinDistance(field_4219.player.getBlockPos(), Double.longBitsToDouble(4638707616191610880L)));
      synchronized (this.field_971) {
         ArrayList var3 = new ArrayList();

         for (Entry var5 : this.field_971.entrySet()) {
            if (System.currentTimeMillis() > ((Class_0944)var5.getValue()).method_417() + 250L) {
               var3.add((BlockPos)var5.getKey());
            }
         }

         for (BlockPos var16 : (Iterable<BlockPos>)(Iterable<?>)(var3)) {
            this.field_971.remove(var16);
         }
      }

      synchronized (this.method_347()) {
         this.field_970.clear();
         this.field_970.addAll(Class_1225.method_1070());
         this.field_970.removeIf(Objects::isNull);
         this.field_970.sort(Comparator.comparing(var0 -> field_4219.player.getPos().squaredDistanceTo(var0.getPos().toCenterPos())));
      }

      synchronized (this.field_974) {
         for (Class_1003 var15 : this.field_974) {
            if (var15.method_644()) {
               field_4219.world.setBlockState(var15.method_406(), var15.method_407());
            }
         }
      }

      this.field_974.removeIf(Class_1003::method_644);
   }

   @Subscribe
   public void method_2(Event_10 var1) {
      if (var1.method_127() instanceof PlayerInteractBlockC2SPacket var2 && this.method_29(var2.getHand()).getItem() instanceof BlockItem var3) {
         BlockHitResult var7 = var2.getBlockHitResult();
         BlockPos var5 = var7.getBlockPos().offset(var7.getSide());
         this.field_971.put(var5, Class_0944.method_31(var3.getBlock()));
         this.field_973.add(var5);
         this.method_346();
      }
   }

   @Subscribe
   public void method_2(Event_34 var1) {
      this.method_346();
   }

   @Subscribe
   public void method_5(Event_4 var1) {
      if (var1.method_127() instanceof BlockUpdateS2CPacket var2) {
         this.field_974.removeIf(var1x -> var1x.method_406().equals(var2.getPos()));
         if (var2.getState().isAir()) {
            this.field_972.remove(var2.getPos());
            this.field_971.remove(var2.getPos());
         } else if (this.field_971.remove(var2.getPos()) != null) {
            this.field_972.add(var2.getPos());
         }
      }

      if (var1.method_127() instanceof ChunkDeltaUpdateS2CPacket var4) {
         var4.visitUpdates((var1x, var2x) -> this.field_974.removeIf(var1xx -> var1xx.method_406().equals(var1x)));
      }

      if (var1.method_127() instanceof GameJoinS2CPacket var5) {
         this.field_978 = var5.viewDistance();
      }

      if (var1.method_127() instanceof ChunkLoadDistanceS2CPacket var6) {
         this.field_978 = var6.getDistance();
      }
   }

   @Subscribe
   public void method_2(Event_35 var1) {
      if (var1.method_111() != null && field_4219.player != null) {
         if (this.field_971.containsKey(var1.method_111())) {
            Class_0944 var2 = this.field_971.get(var1.method_111());
            if (var2 == null || var2.method_862() == null) {
               return;
            }

            Box var3 = new Box(var1.method_111());
            if (field_4219.player.getBoundingBox().intersects(var3) || !((DuckAbstractBlock)var2.method_862()).isCollidable()) {
               return;
            }

            if (!field_4219.world
               .getEntitiesByClass(
                  Entity.class,
                  var3,
                  var0 -> !(var0 instanceof ExperienceBottleEntity)
                        && !(var0 instanceof ItemEntity)
                        && !(var0 instanceof ExperienceOrbEntity)
                        && !(var0 instanceof ArrowEntity)
                        && !(var0 instanceof EnderPearlEntity)
               )
               .isEmpty()) {
               return;
            }

            var1.method_9(var2.method_862().getDefaultState().getOutlineShape(field_4219.world, var1.method_111()));
         }

         if (RotationManager.method_513()) {
            if (Hub.field_2602.method_984().method_9(300L)) {
               if (var1.method_111().equals(this.unconfirmedBreak)) {
                  var1.method_9(VoxelShapes.empty());
               }
            }
         }
      }
   }

   public void method_99(BlockPos var1) {
      this.field_974.add(Class_1003.method_340(var1));
      field_4219.interactionManager.breakBlock(var1);
   }

   public void method_100(BlockPos var1) {
      this.unconfirmedBreak = var1;
      this.field_977.reset();
   }

   public BlockPos method_344() {
      return this.unconfirmedBreak;
   }

   public Map<BlockPos, Class_0944> method_345() {
      return this.field_971;
   }

   public ItemStack method_29(Hand var1) {
      return var1 == Hand.MAIN_HAND ? this.field_975 : this.field_976;
   }

   public void method_346() {
      this.field_975 = field_4219.player.getMainHandStack().copy();
      this.field_976 = field_4219.player.getOffHandStack().copy();
   }

   public List<BlockEntity> method_347() {
      return this.field_970;
   }

   public boolean method_101(BlockPos var1) {
      return this.field_972.contains(var1) || this.field_971.containsKey(var1);
   }

   public boolean method_105(BlockPos var1) {
      return !field_4219.world.getBlockState(var1).isReplaceable() || this.field_973.contains(var1);
   }

   public int method_348() {
      return this.field_978;
   }
}
