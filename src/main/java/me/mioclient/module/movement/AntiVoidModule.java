package me.mioclient.module.movement;

import me.mioclient.enum_.Class_1360;
import me.mioclient.event.Event_1;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_1016;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Direction.Axis;
import net.minecraft.world.BlockStateRaycastContext;
import nick.Settings;

public class AntiVoidModule extends Module {
   public Setting<Class_1360> field_127;
   public Setting<Integer> field_128;
   public Setting<Boolean> field_129;

   public AntiVoidModule() {
      super("AntiVoid", "Prevents you from falling into the void.", Category.MOVEMENT);
      Settings.initialize(this);
      this.setDrawn(false);
   }

   @Override
   public String getInfo() {
      return Class_1016.method_3(this.field_127.getValue());
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if (!field_4219.player.isOnGround() && !(field_4219.player.fallDistance <= 0.0F)) {
         BlockHitResult var2 = field_4219.world
            .raycast(
               new BlockStateRaycastContext(
                  field_4219.player.getPos(),
                  new Vec3d(field_4219.player.getX(), (double)(field_4219.world.getBottomY() - 1), field_4219.player.getZ()),
                  var0 -> var0.isSolid()
               )
            );
         if (var2.getType() == Type.MISS) {
            if (!((double)(field_4219.world.getBottomY() + this.field_128.getValue()) < field_4219.player.getY())) {
               if (this.field_127.getValue() == Class_1360.TELEPORT) {
                  field_4219.player.setVelocity(0.0, Double.longBitsToDouble(4621819117588971520L), 0.0);
               } else {
                  field_4219.player.setVelocity(field_4219.player.getVelocity().withAxis(Axis.Y, 0.0));
                  if (this.field_129.getValue()) {
                     field_4219.player.setOnGround(true);
                  }
               }
            }
         }
      }
   }
}
