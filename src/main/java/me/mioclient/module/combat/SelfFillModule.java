package me.mioclient.module.combat;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_0710;
import me.mioclient.enum_.PreType;
import me.mioclient.enum_.Priority;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_19;
import me.mioclient.event.Event_4;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0127;
import me.mioclient.internal.PlayerUtil;
import me.mioclient.internal.Constants;
import me.mioclient.internal.Class_0382;
import me.mioclient.internal.Class_0464;
import me.mioclient.internal.RotationManager;
import me.mioclient.internal.Class_0719;
import me.mioclient.internal.Class_1035;
import me.mioclient.internal.ChatUtil;
import me.mioclient.mixin.ducks.DuckAbstractBlock;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket.Mode;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket.Full;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import nick.Settings;

public class SelfFillModule extends Module {
   public Setting<Class_0710> field_4232;
   public Setting<Boolean> field_4233;
   public Setting<Boolean> field_4234;
   public Setting<Boolean> field_4235;
   public Setting<Set<Block>> field_4236;
   public Setting<Boolean> field_4237;
   public Setting<Boolean> field_4238;
   public Entity field_4239;
   public boolean field_3965;
   public double field_609;
   public final List<Double> field_4240;

   public SelfFillModule() {
      super("SelfFill", "Places solid blocks inside you blocking your lower hitbox.", Category.COMBAT, "burrow");
      Settings.initialize(this);
      this.field_4240 = List.of(
         Double.longBitsToDouble(4600877379321698714L),
         Double.longBitsToDouble(4604930618986332160L),
         Double.longBitsToDouble(4607227454796291113L),
         Double.longBitsToDouble(4607857958744122982L)
      );
   }

   @Override
   public void onEnable() {
      if (this.method_535()) {
         this.disable();
      } else {
         this.field_609 = field_4219.player.getY();
         this.field_3965 = false;
      }
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if (field_4219.player.getY() > this.field_609 && this.field_4234.getValue() && this.field_4235.getValue()) {
         this.method_68();
      }
   }

   @Subscribe
   public void method_5(Event_4 var1) {
      if (var1.method_127() instanceof PlayerPositionLookS2CPacket var2
         && field_4219.player.getPos().squaredDistanceTo(new Vec3d(var2.getX(), var2.getY(), var2.getZ())) > Double.longBitsToDouble(4621256167635550208L)) {
         this.field_609 = var2.getY();
      }
   }

   @Subscribe
   public void method_9(Event_19 var1) {
      if (var1.method_213() != PreType.POST) {
         if (!field_4219.player.hasVehicle()) {
            int var2 = 0;

            for (int var3 = -8; var3 < 8; var3++) {
               if (var3 != 0 && var3 != 1) {
                  BlockPos var4 = BlockPos.ofFloored(field_4219.player.getPos()).add(0, var3, 0);
                  if (field_4219.world.getBlockState(var4).getCollisionShape(field_4219.world, var4).isEmpty()) {
                     var2 = var3;
                     break;
                  }
               }
            }

            BlockPos var12 = Class_0382.method_425();
            if (field_4219.world.getEntitiesByClass(PlayerEntity.class, new Box(var12), var0 -> var0 != field_4219.player).isEmpty()) {
               BlockState var13 = field_4219.world.getBlockState(var12);
               Box var5 = field_4219.player.getBoundingBox().expand(-Class_0719.field_2280);
               boolean var6 = BlockPos.stream(var5.withMaxY(var5.minY)).count() != 1L;
               if (!var6 || !Class_0464.method_363()) {
                  if (var13.isReplaceable() && !var13.isLiquid() && field_4219.player.isOnGround() && var2 != 0) {
                     if (this.field_4237.getValue()) {
                        this.field_4239 = Class_1035.method_2(var12, 0);
                     }

                     if (this.field_4239 == null) {
                        int var7 = PlayerUtil.method_7((Predicate<ItemStack>)(var1x -> {
                           if (var1x.getItem() instanceof BlockItem var2x && (this.field_4232.getValue() != null ? this.field_4232.getValue().method_2(var2x.getBlock(), this.field_4236) : false)) {
                              return true;
                           }

                           return false;
                        }));
                        int var8 = field_4219.player.getInventory().selectedSlot;
                        if (var7 == -1) {
                           ChatUtil.method_2(Text.literal(this.getName()).append(" is out of blocks!"), ChatUtil.method_38(-2), Priority.HIGH);
                           this.disable();
                        } else {
                           Direction var9 = Class_1035.method_78(var12);
                           if (var9 != null) {
                              float[] var10 = new float[]{var1.method_500(), (float)Constants.field_685};
                              if (this.field_4239 != null && this.field_4233.getValue()) {
                                 var10 = RotationManager.method_14(this.field_4239);
                              }

                              int var11 = var2;
                              Class_0127.method_7(() -> this.method_2(var12, var9, var8, var7, var11));
                              if (this.field_4233.getValue()) {
                                 Hub.field_2598.method_2(var10, 101);
                              }

                              if (this.field_3965 && !this.field_4234.getValue()) {
                                 this.field_3965 = false;
                                 this.disable();
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public void method_2(BlockPos var1, Direction var2, int var3, int var4, int var5) {
      boolean var6 = true;

      for (int var7 = 1; var7 < 3; var7++) {
         if (!field_4219.world.getBlockState(var1.up(var7)).isReplaceable()) {
            var6 = false;
            break;
         }
      }

      if (var6) {
         field_4219.player.setSneaking(false);
         field_4219.player.networkHandler.sendPacket(new ClientCommandC2SPacket(field_4219.player, Mode.PRESS_SHIFT_KEY));
         PlayerUtil.method_16(var4);
         Item var13 = field_4219.player.getMainHandStack().getItem();
         Block var8 = ((BlockItem)var13).getBlock();
         boolean var9 = ((DuckAbstractBlock)var8).isCollidable();
         if (var9) {
            for (double var11 : this.field_4240) {
               field_4219.player
                  .networkHandler
                  .sendPacket(
                     new Full(
                        field_4219.player.getX(),
                        field_4219.player.getY() + var11,
                        field_4219.player.getZ(),
                        field_4219.player.getYaw(),
                        (float)Constants.field_685,
                        false
                     )
                  );
            }
         }

         Box var14 = field_4219.player.getBoundingBox();
         field_4219.player.setBoundingBox(new Box(0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
         if (this.field_4238.getValue()) {
            Class_1035.method_2(var1, null, var2, false, Hand.MAIN_HAND);
         } else {
            Class_1035.method_2(var1, var2, false, Hand.MAIN_HAND);
         }

         field_4219.player.setBoundingBox(var14);
         if (var9) {
            this.method_38((double)var5);
         }

         PlayerUtil.method_16(var3);
         field_4219.player.networkHandler.sendPacket(new ClientCommandC2SPacket(field_4219.player, Mode.RELEASE_SHIFT_KEY));
         this.field_3965 = true;
      }
   }

   public void method_38(double var1) {
      if (this.field_4238.getValue()) {
         field_4219.player
            .networkHandler
            .sendPacket(
               new Full(
                  field_4219.player.getX(),
                  field_4219.player.getY() + Double.longBitsToDouble(4607182418800017408L),
                  field_4219.player.getZ(),
                  field_4219.player.getYaw(),
                  (float)Constants.field_685,
                  true
               )
            );
         Hub.field_2619
            .method_2(
               () -> field_4219.player
                     .setPosition(field_4219.player.getX(), field_4219.player.getY() + Double.longBitsToDouble(4607182418800017408L), field_4219.player.getZ()),
               1
            );
      } else {
         field_4219.player
            .networkHandler
            .sendPacket(
               new Full(
                  field_4219.player.getX(),
                  field_4219.player.getY() + var1,
                  field_4219.player.getZ(),
                  field_4219.player.getYaw(),
                  (float)Constants.field_685,
                  false
               )
            );
      }
   }
}
