package me.mioclient.module.render;

import java.util.Set;
import me.mioclient.enum_.Class_0126;
import me.mioclient.enum_.Class_0200;
import me.mioclient.enum_.Class_0710;
import me.mioclient.event.Event_13;
import me.mioclient.event.Event_14;
import me.mioclient.event.Event_27;
import me.mioclient.event.Event_28;
import me.mioclient.event.Event_29;
import me.mioclient.event.Event_4;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_1225;
import me.mioclient.internal.Class_1355;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.block.Blocks;
import net.minecraft.client.particle.ExplosionLargeParticle;
import net.minecraft.client.particle.ExplosionSmokeParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SpellParticle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.network.packet.s2c.play.BlockUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.util.math.BlockPos;
import nick.Settings;

public class NoRenderModule extends Module {
   public Setting<Boolean> field_716;
   public Setting<Boolean> field_717;
   public Setting<Boolean> field_718;
   public Setting<Boolean> field_719;
   public Setting<Boolean> field_720;
   public Setting<Boolean> field_721;
   public Setting<Boolean> field_722;
   public Setting<Boolean> field_723;
   public Setting<Float> field_724;
   public Setting<Boolean> field_725;
   public Setting<Boolean> field_726;
   public Setting<Boolean> field_727;
   public Setting<Boolean> field_728;
   public Setting<Boolean> field_729;
   public Setting<Boolean> field_730;
   public Setting<Boolean> field_731;
   public Setting<Boolean> field_732;
   public Setting<Boolean> field_733;
   public Setting<Boolean> field_734;
   public Setting<Boolean> field_735;
   public Setting<Boolean> field_736;
   public Setting<Boolean> field_737;
   public Setting<Boolean> field_738;
   public Setting<Boolean> field_739;
   public Setting<Float> field_740;
   public Setting<Boolean> field_741;
   public Setting<Boolean> field_742;
   public Setting<Set<ParticleType<?>>> field_743;
   public Setting<Class_0710> field_744;
   public Setting<Boolean> field_745;
   public Setting<Boolean> field_746;
   public Setting<Boolean> field_747;
   public Setting<Boolean> field_748;
   public Setting<Boolean> field_749;
   public Setting<Boolean> field_750;
   public Setting<Boolean> field_751;
   public Setting<Boolean> field_752;
   public Setting<Float> field_753;
   public Setting<Boolean> field_754;
   public Setting<Integer> field_755;
   public Setting<Boolean> field_756;
   public Setting<Integer> field_757;
   public Setting<Boolean> field_758;
   public Setting<Boolean> field_759;
   public Setting<Integer> field_760;
   public Setting<Boolean> field_761;
   public Setting<Set<EntityType<?>>> field_762;
   public Setting<Class_0126> field_763;
   public Setting<Class_0710> field_764;
   public float field_765;
   public BlockPos field_355;

   public NoRenderModule() {
      super("NoRender", "Removes the annoying stuff from your screen.", Category.RENDER);
      Settings.initialize(this);
      this.setDrawn(false);
      this.field_746.method_31("EntitiesPage");
      this.field_744.method_31("ParticleSelection");
      this.field_764.method_31("EntitySelection");
      this.field_724.method_31("HandsOpacity");
   }

   @Subscribe(
      method_800 = -100
   )
   public void method_2(Event_14 var1) {
      if (this.field_754.getValue()) {
         double var2 = field_4219.player.getPos().distanceTo(var1.method_11().getPos());
         float var4 = var1.method_11().getWidth();
         if (var2 <= (double)var4 && var1.method_11() != field_4219.player) {
            float var5 = (float)(var2 / (double)var4);
            float var6 = (float)(this.field_755.getValue() != null ? this.field_755.getValue().intValue() : 0) / Float.intBitsToFloat(1132396544);
            var1.method_425(var6 + var5 * (Float.intBitsToFloat(1065353216) - var6));
            this.field_765 = var1.method_937();
         } else {
            this.field_765 = Float.intBitsToFloat(1065353216);
         }
      } else {
         this.field_765 = Float.intBitsToFloat(1065353216);
      }
   }

   @Subscribe
   public void method_2(Event_13 var1) {
      if (this.method_230(var1.method_11()) && var1.method_11() != field_4219.player && this.field_763.getValue() == Class_0126.FULL) {
         var1.method_463();
      }
   }

   @Subscribe
   public void method_9(Event_27 var1) {
      if (this.field_748.getValue()) {
         var1.method_463();
      }
   }

   @Subscribe
   public void method_9(Event_28 var1) {
      Particle var2 = var1.method_853();
      if ((var2 instanceof ExplosionLargeParticle || var2 instanceof ExplosionSmokeParticle) && this.field_736.getValue()) {
         var1.method_463();
      }

      if (this.field_718.getValue() && var2 instanceof SpellParticle) {
         var1.method_463();
      }
   }

   @Subscribe
   public void method_2(Event_29 var1) {
      if (this.field_720.getValue() && var1.method_1026() == StatusEffects.BLINDNESS) {
         var1.method_463();
      }

      if (this.field_719.getValue() && var1.method_1026() == StatusEffects.DARKNESS) {
         var1.method_463();
      }
   }

   @Subscribe
   public void method_5(Event_4 var1) {
      if (this.field_750.getValue() && Class_1225.method_1071() == Class_0200.THE_END) {
         if (var1.method_127() instanceof BlockUpdateS2CPacket var2 && this.field_355 != null) {
            if (!var2.getPos().equals(this.field_355)) {
               return;
            }

            if (!var2.getState().isOf(Blocks.FIRE)) {
               return;
            }

            this.field_355 = null;
            var1.method_463();
         }

         if (var1.method_127() instanceof EntitySpawnS2CPacket var4 && var4.getEntityType() == EntityType.END_CRYSTAL) {
            this.field_355 = BlockPos.ofFloored(var4.getX(), var4.getY(), var4.getZ());
         }
      }
   }

   public boolean method_279() {
      return this.isToggled() && this.field_723.getValue() && !Class_1355.field_2003 && !Class_1355.field_4422 && this.field_724.getValue() == 0.0F;
   }

   public boolean method_280() {
      return this.isToggled() && this.field_732.getValue();
   }

   public boolean method_2(ParticleEffect var1) {
      if (this.field_744 == null || this.field_744.getValue() == null) return false;
      return this.isToggled() && this.field_742.getValue() ? !(this.field_744.getValue() != null ? this.field_744.getValue().method_2(var1.getType(), this.field_743.getValue()) : false) : false;
   }

   public boolean method_230(Entity var1) {
      return this.method_2(var1.getType());
   }

   public boolean method_2(EntityType<?> var1) {
      if (this.field_764 == null || this.field_764.getValue() == null) return false;
      return this.isToggled() && this.field_761.getValue() ? !(this.field_764.getValue() != null ? this.field_764.getValue().method_2(var1, this.field_762.getValue()) : false) : false;
   }

   public boolean method_281() {
      return this.isToggled() && this.field_745.getValue();
   }

   public float method_282() {
      if (this.isToggled() && this.field_723.getValue() && !Class_1355.field_4422 && !Class_1355.field_2003) {
         float var1 = this.field_724.getValue();
         return var1 == 0.0F ? 0.0F : Math.max(var1, Float.intBitsToFloat(1038174126));
      } else {
         return Float.intBitsToFloat(1065353216);
      }
   }

   public float method_283() {
      if (this.isToggled() && this.field_752.getValue()) {
         float var1 = this.field_753.getValue();
         return var1 == 0.0F ? 0.0F : Math.max(var1, Float.intBitsToFloat(1038174126));
      } else {
         return Float.intBitsToFloat(1065353216);
      }
   }

   public float method_284() {
      return !this.isToggled() ? Float.intBitsToFloat(1065353216) : this.field_765;
   }

   public boolean method_285() {
      return this.isToggled() && this.field_734.getValue();
   }
}
