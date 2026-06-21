package me.mioclient.module.abstract_;

import me.mioclient.Hub;
import me.mioclient.enum_.Class_0207;
import me.mioclient.event.Event_10;
import me.mioclient.event.Event_7;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0242;
import me.mioclient.internal.Class_0245;
import me.mioclient.internal.Class_0485;
import me.mioclient.internal.Class_0838;
import me.mioclient.internal.Class_1225;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket.Action;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket.LookAndOnGround;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.RaycastContext.FluidHandling;
import net.minecraft.world.RaycastContext.ShapeType;
import nick.Settings;

public class AbstractModule_36 extends Module {
   public static final Vec2f[] field_661 = new Vec2f[]{
      new Vec2f(Float.intBitsToFloat(-1082130432), Float.intBitsToFloat(1065353216)),
      new Vec2f(0.0F, Float.intBitsToFloat(1065353216)),
      new Vec2f(Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216)),
      new Vec2f(Float.intBitsToFloat(-1082130432), 0.0F),
      new Vec2f(Float.intBitsToFloat(1065353216), 0.0F),
      new Vec2f(Float.intBitsToFloat(-1082130432), 0.0F),
      new Vec2f(0.0F, 0.0F),
      new Vec2f(Float.intBitsToFloat(1065353216), 0.0F)
   };
   public Setting<Boolean> field_662;
   public Setting<Class_0207> field_663;
   public Setting<Integer> field_664;
   public Setting<Integer> field_665;
   public Setting<Integer> field_666;
   public Setting<Boolean> field_667;
   public Setting<Float> field_668;
   public Setting<Integer> field_669;
   public Setting<Float> field_670;
   public Setting<Float> field_671;
   public Setting<Boolean> field_672;
   public Setting<Float> field_673;
   public final Class_0242 field_674;
   public int field_675;
   public float[] field_676;

   public AbstractModule_36() {
      super("RaytraceBypass", "Does magic to bypass NCP raytracing. \n%sPatched in newer NCP versions.".formatted(Formatting.YELLOW), Category.EXPLOIT);
      Settings.initialize(this);
      this.field_674 = new Class_0242();
   }

   @Subscribe(
      method_800 = -1000
   )
   public void method_38(Event_7 var1) {
      if (this.field_676 != null && this.field_663.getValue() == Class_0207.MOTION) {
         Hub.field_2598.method_2(this.field_676, 1000);
         this.field_675++;
         if (this.field_675 > this.field_664.getValue()) {
            this.field_676 = null;
         }
      }
   }

   @Subscribe
   public void method_2(Event_10 var1) {
      if (var1.method_127() instanceof PlayerInteractBlockC2SPacket var2 && this.field_674.method_9((long)this.field_665.getValue().intValue())) {
         this.method_9(var2.getHand(), var2.getBlockHitResult());
      }

      if (var1.method_127() instanceof PlayerActionC2SPacket var4 && var4.getAction() == Action.START_DESTROY_BLOCK) {
         this.method_9(Hand.MAIN_HAND, new BlockHitResult(var4.getPos().toCenterPos(), null, var4.getPos(), false));
      }
   }

   public void method_9(Hand var1, BlockHitResult var2) {
      if ((this.field_662.getValue() || !Class_1225.method_9(var2.getPos()))
         && (!field_4219.player.isUsingItem() || !field_4219.player.getStackInHand(var1).isOf(Items.EXPERIENCE_BOTTLE))) {
         Object var3 = null;
         boolean var4 = false;
         if (this.field_667.getValue()) {
            float[] var5 = Class_0485.method_78(var2.getPos().add(0.0, (double)this.field_666.getValue().intValue(), 0.0));
            HitResult var6 = this.method_38(var5[0], var5[1]);
            if (!this.method_9(var6)) {
               var6 = this.method_105(Class_0485.method_78(var2.getPos())[0] + Float.intBitsToFloat(1127481344));
            }

            if (!this.method_9(var6)) {
               var4 = true;
            }

            if (var4 && !this.field_672.getValue()) {
               return;
            }

            if (var4 && this.field_672.getValue()) {
               float var7 = Class_0485.method_78(var2.getPos())[0];
               this.field_676 = new float[]{var7, this.field_673.getValue()};
               this.method_38(this.field_676);
               return;
            }

            var3 = var6.getPos();
         } else {
            var3 = var2.getPos().add(0.0, (double)this.field_666.getValue().intValue(), 0.0);
         }

         if (var3 != null) {
            float[] var9 = Class_0485.method_78((Vec3d)var3);
            this.field_676 = var9;
            this.method_38(var9);
         }
      }
   }

   public void method_38(float[] var1) {
      if (this.field_663.getValue() == Class_0207.PACKET) {
         field_4219.player.networkHandler.sendPacket(new LookAndOnGround(var1[0], var1[1], field_4219.player.isOnGround()));
      } else if (this.field_663.getValue() == Class_0207.CLIENT) {
         field_4219.player.setYaw(var1[0]);
         field_4219.player.setPitch(var1[1]);
      }

      this.field_675 = 1;
      this.field_674.reset();
   }

   public HitResult method_38(float var1, float var2) {
      Vec3d var3 = field_4219.player.getCameraPosVec(Class_0838.method_776());
      float var4 = var2 * Class_0245.field_690;
      float var5 = -var1 * Class_0245.field_690;
      float var6 = MathHelper.cos(var5);
      float var7 = MathHelper.sin(var5);
      float var8 = MathHelper.cos(var4);
      float var9 = MathHelper.sin(var4);
      float var10 = Float.intBitsToFloat(1105199104);
      Vec3d var11 = new Vec3d((double)(var7 * var8), (double)(-var9), (double)(var6 * var8));
      Vec3d var12 = var3.add(var11.x * (double)var10, var11.y * (double)var10, var11.z * (double)var10);
      return field_4219.world.raycast(new RaycastContext(var3, var12, ShapeType.OUTLINE, FluidHandling.NONE, field_4219.player));
   }

   public HitResult method_105(float var1) {
      for (float var2 = var1 - this.field_670.getValue(); var2 < var1 + this.field_670.getValue(); var2 += this.field_670.getValue()) {
         for (float var3 = Float.intBitsToFloat(-1028390912); var3 < -this.field_671.getValue(); var3 += this.field_670.getValue()) {
            HitResult var4 = this.method_78(var2, var3);
            if (this.method_9(var4)) {
               return var4;
            }
         }
      }

      return null;
   }

   public HitResult method_78(float var1, float var2) {
      HitResult var3 = this.method_38(var1, var2);
      if (!this.method_9(var3)) {
         return null;
      } else {
         for (Vec2f var7 : field_661) {
            for (int var8 = 0; var8 < this.field_669.getValue(); var8++) {
               if (!this.method_9(
                  this.method_38(var1 + var7.x * this.field_668.getValue() * (float)var8, var2 + var7.y * this.field_668.getValue() * (float)var8)
               )) {
                  return null;
               }
            }
         }

         return var3;
      }
   }

   public boolean method_9(HitResult var1) {
      return var1 == null ? false : var1.getType() == Type.MISS;
   }
}
