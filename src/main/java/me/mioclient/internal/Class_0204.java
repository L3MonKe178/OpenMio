package me.mioclient.internal;

import java.util.ArrayList;
import java.util.List;
import me.mioclient.api.Class_1309;
import me.mioclient.event.Event_3;
import me.mioclient.module.render.TrajectoriesModule;
import net.minecraft.entity.Entity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class Class_0204 {
   public final List<Vec3d> field_578;
   public boolean field_579;
   public Box field_580;
   public Entity field_581;
   public Entity field_582;
   public final TrajectoriesModule field_583;

   public Class_0204(TrajectoriesModule var1) {
      super();
      this.field_583 = var1;
      this.field_578 = new ArrayList<>();
   }

   public void method_208() {
      this.field_578.clear();
      this.field_579 = false;
      this.field_581 = null;
   }

   public void method_80(Entity var1) {
      this.field_582 = var1;
   }

   public void method_239() {
      this.method_240();

      for (int var1 = 0; var1 < 2000; var1++) {
         HitResult var2 = this.field_583.field_2360.method_870();
         if (var2 != null) {
            this.method_5(var2);
            break;
         }

         this.method_240();
      }
   }

   public void method_240() {
      this.field_578.add(new Vec3d(this.field_583.field_2360.field_2983.x, this.field_583.field_2360.field_2983.y, this.field_583.field_2360.field_2983.z));
   }

   public void method_5(HitResult var1) {
      if (var1.getType() == Type.BLOCK) {
         BlockHitResult var2 = (BlockHitResult)var1;
         this.field_579 = true;
         this.field_580 = new Box(var2.getPos(), var2.getPos());
         if (var2.getSide() == Direction.UP || var2.getSide() == Direction.DOWN) {
            this.field_580 = this.field_580.expand(Double.longBitsToDouble(4598175219545276416L), 0.0, Double.longBitsToDouble(4598175219545276416L));
         } else if (var2.getSide() != Direction.NORTH && var2.getSide() != Direction.SOUTH) {
            this.field_580 = this.field_580.expand(0.0, Double.longBitsToDouble(4598175219545276416L), Double.longBitsToDouble(4598175219545276416L));
         } else {
            this.field_580 = this.field_580.expand(Double.longBitsToDouble(4598175219545276416L), Double.longBitsToDouble(4598175219545276416L), 0.0);
         }

         this.field_578.add(var1.getPos());
      } else if (var1.getType() == Type.ENTITY) {
         this.field_581 = ((EntityHitResult)var1).getEntity();
      }
   }

   public void method_4(Event_3 var1) {
      Vec3d var2 = null;

      for (Vec3d var4 : this.field_578) {
         if (var2 != null) {
            Class_0838.field_2672
               .method_2(
                  var1.method_10(),
                  this.field_583.field_2373,
                  var2,
                  var4,
                  this.field_583.field_2365.getValue().getRGB(),
                  this.field_583.field_2365.getValue().getRGB()
               );
         }

         var2 = var4;
      }

      if (this.field_579) {
         Class_0612.method_5(var1.method_10(), this.field_580, Class_1081.method_9(this.field_583.field_2365.getValue(), 60));
         Class_0612.method_2(var1.method_10(), this.field_580, this.field_583.field_2365.getValue(), this.field_583.field_2364.getValue());
      }

      if (this.field_581 != null && this.field_582 == Class_1309.field_4219.player) {
         Class_0612.method_5(
            var1.method_10(), Class_0719.method_2(this.field_581, var1.method_880()), Class_1081.method_9(this.field_583.field_2365.getValue(), 60)
         );
      }
   }
}
