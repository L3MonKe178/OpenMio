package me.mioclient.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import me.mioclient.Hub;
import me.mioclient.event.Event_10;
import me.mioclient.event.Event_37;
import me.mioclient.event.Event_4;
import me.mioclient.module.abstract_.AbstractModule_28;
import me.mioclient.module.movement.VelocityModule;
import me.mioclient.module.player.SpeedMineModule;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket.Action;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket.Full;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;

public class Class_0552 extends Class_0043 {
   public static final SpeedMineModule field_1756 = Hub.field_2595.method_78(SpeedMineModule.class);
   public static final AbstractModule_28 field_1757 = Hub.field_2595.method_78(AbstractModule_28.class);
   public final Timer field_1758 = new Timer();
   public final List<Long> field_1759 = Collections.synchronizedList(new ArrayList<>());
   public boolean field_54;
   public float field_1760;
   public float field_1761;

   public Class_0552(VelocityModule var1) {
      super(var1);
   }

   @Override
   public void method_5(Event_4 var1) {
      if (var1.method_127() instanceof EntityVelocityUpdateS2CPacket var2 && var2.getEntityId() == field_4219.player.getId() && !this.method_581()) {
         var1.method_463();
      }

      if (var1.method_127() instanceof PlayerPositionLookS2CPacket) {
         this.field_1759.add(System.currentTimeMillis());
      }
   }

   @Override
   public void method_2(Event_10 var1) {
      if (var1.method_127() instanceof PlayerMoveC2SPacket var2 && var2.changesLook()) {
         this.field_1760 = var2.getYaw(0.0F);
         this.field_1761 = var2.getPitch(0.0F);
      }
   }

   @Override
   public void method_2(Event_37 var1) {
      boolean var2 = var1.method_398() != 0.0F || var1.method_399() != 0.0F || var1.method_400() != 0.0F;
      if (var2 && this.field_88.field_2513.getValue() && !this.method_581()) {
         var1.method_52(0.0F);
         var1.method_185(0.0F);
         var1.method_92(0.0F);
      }
   }

   @Override
   public void method_40() {
      this.field_54 = BlockPos.stream(field_4219.player.getBoundingBox()).anyMatch(var0 -> !field_4219.world.isAir(var0));
      if (field_4219.player.age > 20 && !this.method_581()) {
         this.field_1759.removeIf(var0 -> System.currentTimeMillis() > var0 + 1000L);
         if (this.field_1759.size() > 2) {
            this.field_1758.reset();
            this.field_1759.clear();
         } else {
            this.method_578();
         }
      }
   }

   public void method_578() {
      BlockPos var1 = this.method_579();
      if (var1 != null) {
         PacketUtil.method_2(
            new Full(
               field_4219.player.getX(), field_4219.player.getY(), field_4219.player.getZ(), this.field_1760, this.field_1761, field_4219.player.isOnGround()
            )
         );
         PacketUtil.method_2(new PlayerActionC2SPacket(Action.STOP_DESTROY_BLOCK, var1, Direction.DOWN));
      }
   }

   public BlockPos method_579() {
      return BlockPos.stream(field_4219.player.getBoundingBox())
         .<BlockPos>map(BlockPos::toImmutable)
         .filter(Class_1225::method_14)
         .max(Comparator.comparing(this::method_580))
         .orElse(null);
   }

   public float method_580(BlockPos var1) {
      Box var2 = new Box(var1);
      return (float)field_4219.player.getBoundingBox().intersection(var2).getAverageSideLength();
   }

   public boolean method_581() {
      boolean var1 = this.method_579() == null && !this.field_54;
      return field_4219.player.isFallFlying() || Class_0382.method_5(field_4219.player) || var1 || !this.field_1758.method_9(200L);
   }
}
