package me.mioclient.module.render;

import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import me.mioclient.event.Event_17;
import me.mioclient.event.Event_3;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0204;
import me.mioclient.internal.Class_0809;
import me.mioclient.internal.Class_0838;
import me.mioclient.internal.Class_0969;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.VertexFormat.DrawMode;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.item.BowItem;
import net.minecraft.util.math.Vec3d;
import nick.Settings;
import org.lwjgl.opengl.GL32C;

public class TrajectoriesModule extends Module {
   public final Class_0969 field_2360 = new Class_0969();
   public Setting<Boolean> field_2361;
   public Setting<Boolean> field_2362;
   public Setting<Boolean> field_2363;
   public Setting<Float> field_2364;
   public Setting<Color> field_2365;
   public Setting<Boolean> field_2366;
   public Setting<Boolean> field_2367;
   public Setting<Boolean> field_2368;
   public Setting<Boolean> field_2369;
   public Setting<Boolean> field_2370;
   public Setting<Boolean> field_2371;
   public Setting<Boolean> field_2372;
   public BufferBuilder field_2373;
   public Vec3d field_2374;
   public float field_876;

   public TrajectoriesModule() {
      super("Trajectories", "Draws trajectories for projectiles and bows.", Category.RENDER);
      Settings.initialize(this);
      this.field_2374 = Vec3d.ZERO;
      this.setDrawn(false);
   }

   @Subscribe
   public void method_9(Event_17 var1) {
      this.field_2374 = field_4219.player.getVelocity();
      this.field_876 = BowItem.getPullProgress(field_4219.player.getItemUseTime());
   }

   @Subscribe
   public void method_9(Event_3 var1) {
      Tessellator var2 = Tessellator.getInstance();
      BufferBuilder var3 = var2.begin(DrawMode.DEBUG_LINES, VertexFormats.POSITION_COLOR);
      this.field_2373 = var3;

      for (Entity var5 : field_4219.world.getEntities()) {
         if (this.method_221(var5)) {
            Class_0204 var6 = new Class_0204(this);
            if (var5 instanceof ProjectileEntity) {
               ProjectileEntity var7 = (ProjectileEntity)var5;
               if (!this.field_2360.method_2(var7, true, (double)var1.method_880())) {
                  continue;
               }
            } else if (var5 instanceof PlayerEntity) {
               PlayerEntity var8 = (PlayerEntity)var5;
               if (!this.field_2360.method_2(var8, var8.getStackInHand(var8.getActiveHand()), 0.0, true, (double)var1.method_880())) {
                  continue;
               }
            }

            var6.method_239();
            var6.method_80(var5);
            var6.method_4(var1);
            var6.method_208();
         }
      }

      RenderSystem.setShader(GameRenderer::getPositionColorProgram);
      Class_0838.field_2672.method_780();
      GL32C.glLineWidth(this.field_2364.getValue());
      Class_0809.method_2(var3);
      Class_0838.field_2672.method_782();
   }

   public boolean method_221(Entity var1) {
      if (var1 instanceof WitherSkullEntity) {
         return false;
      } else {
         return var1 instanceof PlayerEntity && this.field_2362.getValue()
            ? var1 == field_4219.player || !this.field_2361.getValue()
            : var1 instanceof ProjectileEntity && this.field_2363.getValue();
      }
   }

   public Vec3d method_710() {
      return this.field_2374;
   }

   public float method_711() {
      return this.field_876;
   }
}
