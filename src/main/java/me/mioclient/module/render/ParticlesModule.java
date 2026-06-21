package me.mioclient.module.render;

import java.awt.Color;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import me.mioclient.api.Class_0549;
import me.mioclient.event.Event_28;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_1081;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.client.particle.AscendingParticle;
import net.minecraft.client.particle.AshParticle;
import net.minecraft.client.particle.DamageParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.PortalParticle;
import net.minecraft.client.particle.TotemParticle;
import net.minecraft.client.particle.WhiteAshParticle;
import net.minecraft.client.particle.FireworksSparkParticle.Explosion;
import net.minecraft.client.particle.FireworksSparkParticle.FireworkParticle;
import nick.Settings;

public class ParticlesModule extends Module {
   public final Random field_2712 = new Random();
   public Setting<Boolean> field_2713;
   public Setting<Float> field_2714;
   public Setting<Float> field_2715;
   public Setting<Color> field_2716;
   public Setting<Color> field_2717;
   public Setting<Boolean> field_2718;
   public Setting<Boolean> field_2719;
   public Setting<Float> field_2720;
   public Setting<Color> field_2721;
   public Setting<Boolean> field_2722;
   public Setting<Float> field_2723;
   public Setting<Float> field_2724;
   public Setting<Color> field_2725;
   public Setting<Boolean> field_2726;
   public Setting<Float> field_2727;
   public Setting<Color> field_2728;
   public Setting<Boolean> field_2729;
   public Setting<Color> field_2730;
   public Setting<Color> field_2731;

   public ParticlesModule() {
      super("Particles", "Allows you to adjust certain particles.", Category.RENDER);
      Settings.initialize(this);
      this.setDrawn(false);
   }

   @Subscribe
   public void method_9(Event_28 var1) {
      if (var1.method_853() instanceof TotemParticle var2) {
         if (this.field_2718.getValue() && field_4219.player.getBoundingBox().intersects(var2.getBoundingBox())) {
            var1.method_463();
         }

         if (!this.field_2713.getValue()) {
            return;
         }

         if (this.field_2712.nextInt(4) == 0) {
            this.method_2(var2, this.field_2716.getValue().hashCode());
         } else {
            this.method_2(var2, this.field_2717.getValue().hashCode());
         }

         var2.scale(this.field_2714.getValue());
         var2.move(this.field_2715.getValue());
      }

      if (this.field_2719.getValue() && var1.method_853() instanceof FireworkParticle var5) {
         this.method_2(var5, this.field_2721.getValue().hashCode());
         var5.scale(this.field_2720.getValue());
      }

      if (this.field_2719.getValue() && var1.method_853() instanceof Explosion var6) {
         this.method_2(var6, this.field_2721.getValue().hashCode());
         var6.scale(this.field_2720.getValue());
      }

      if (this.field_2722.getValue() && var1.method_853() instanceof DamageParticle var7) {
         this.method_2(var7, this.field_2725.getValue().hashCode());
         var7.scale(this.field_2723.getValue());
         var7.move(this.field_2724.getValue());
      }

      if (this.field_2726.getValue() && var1.method_853() instanceof PortalParticle var8) {
         this.method_2(var8, this.field_2728.getValue().hashCode());
         var8.scale(this.field_2727.getValue());
      }

      if (this.field_2729.getValue() && var1.method_853() instanceof AscendingParticle var9) {
         if (var9 instanceof WhiteAshParticle) {
            this.method_2(var9, this.field_2731.getValue().brighter().hashCode());
         }

         if (var9 instanceof AshParticle) {
            ThreadLocalRandom var15 = ThreadLocalRandom.current();
            Color var4 = Class_1081.method_2(this.field_2730.getValue(), this.field_2731.getValue(), var15.nextFloat());
            this.method_2(var9, var4.hashCode());
         }
      }
   }

   public void method_2(Particle var1, int var2) {
      float var3 = (float)(var2 >> 24) / Float.intBitsToFloat(1132396544);
      float var4 = (float)((var2 & 0xFF0000) >> 16) / Float.intBitsToFloat(1132396544);
      float var5 = (float)((var2 & 0xFF00) >> 8) / Float.intBitsToFloat(1132396544);
      float var6 = (float)(var2 & 0xFF) / Float.intBitsToFloat(1132396544);
      var1.setColor(var4, var5, var6);
      ((Class_0549)var1).mio$setInitialAlpha(var3);
   }
}
