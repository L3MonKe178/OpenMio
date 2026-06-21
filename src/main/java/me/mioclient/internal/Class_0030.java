package me.mioclient.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import me.mioclient.Hub;
import me.mioclient.event.Event_19;
import me.mioclient.event.Event_37;
import me.mioclient.event.Event_4;
import me.mioclient.mixin.ducks.DuckEntityVelocityUpdateS2CPacket;
import me.mioclient.module.movement.FastWebModule;
import me.mioclient.module.movement.VelocityModule;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

public class Class_0030 extends Class_0043 {
   public static final FastWebModule field_51 = Hub.field_2595.method_78(FastWebModule.class);
   public final List<Long> field_52 = Collections.synchronizedList(new ArrayList<>());
   public final Timer field_53 = new Timer();
   public boolean field_54;

   public Class_0030(VelocityModule var1) {
      super(var1);
   }

   @Override
   public void method_5(Event_4 var1) {
      if (var1.method_127() instanceof EntityVelocityUpdateS2CPacket var2 && var2.getEntityId() == field_4219.player.getId() && this.method_41()) {
         boolean var6 = field_51.isToggled() && field_51.method_726() && Class_0382.method_5(field_4219.player);
         DuckEntityVelocityUpdateS2CPacket var4 = (DuckEntityVelocityUpdateS2CPacket)var2;
         float var5 = 0.0F;
         var4.setZ((int)(var2.getVelocityZ() * Double.longBitsToDouble(4665518107723300864L) * (double)var5));
         var4.setX((int)(var2.getVelocityX() * Double.longBitsToDouble(4665518107723300864L) * (double)var5));
         if ((this.field_54 || var6) && var2.getVelocityY() > 0.0) {
            var4.setY((int)(var2.getVelocityY() * Double.longBitsToDouble(-4557853929131474944L)));
         }
      }

      if (var1.method_127() instanceof PlayerPositionLookS2CPacket) {
         this.field_52.add(System.currentTimeMillis());
      }
   }

   @Override
   public void method_9(Event_19 var1) {
   }

   @Override
   public void method_2(Event_37 var1) {
      if (this.method_41()) {
         var1.method_52(0.0F);
         var1.method_185(0.0F);
         var1.method_92(0.0F);
      }
   }

   @Override
   public void method_40() {
      this.field_54 = method_43();
      this.field_52.removeIf(var0 -> System.currentTimeMillis() > var0 + 1000L);
      if (this.field_52.size() > 5) {
         this.field_53.reset();
      }
   }

   public boolean method_41() {
      return field_51.isToggled() && field_51.method_726() && Class_0382.method_5(field_4219.player) ? true : this.field_53.method_9(500L) && this.field_54;
   }

   public static boolean method_42() {
      HashSet<BlockPos> var0 = new HashSet<>();
      var0.add(method_39(Float.intBitsToFloat(1065353216), 0.0F));
      var0.add(method_39(Float.intBitsToFloat(-1082130432), 0.0F));
      var0.add(method_39(0.0F, Float.intBitsToFloat(1065353216)));
      var0.add(method_39(0.0F, Float.intBitsToFloat(-1082130432)));
      return var0.stream().filter(Class_0030::method_44).count() >= 2L;
   }

   public static boolean method_43() {
      Box var0 = field_4219.player
         .getBoundingBox()
         .shrink(Class_0719.field_2280, 0.0, Class_0719.field_2280)
         .withMaxY(field_4219.player.getY())
         .offset(0.0, Double.longBitsToDouble(4591870180174331904L), 0.0);
      return BlockPos.stream(var0).anyMatch(Class_0030::method_44);
   }

   public static BlockPos method_39(float var0, float var1) {
      ClientPlayerEntity var2 = field_4219.player;
      float var3 = (float)(var2.getBoundingBox().getLengthX() / Double.longBitsToDouble(4611686018427387904L));
      var0 *= var3 * Float.intBitsToFloat(1065353224);
      var1 *= var3 * Float.intBitsToFloat(1065353224);
      return BlockPos.ofFloored(var2.getX() + (double)var0, (double)Math.round(var2.getY()), var2.getZ() + (double)var1);
   }

   public static boolean method_44(BlockPos var0) {
      return !field_4219.world.getBlockState(var0).getCollisionShape(field_4219.world, var0).isEmpty();
   }
}
