package me.mioclient.internal;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_0034;
import me.mioclient.enum_.PreType;
import me.mioclient.event.Event_10;
import me.mioclient.event.Event_19;
import me.mioclient.event.Event_20;
import me.mioclient.event.Event_4;
import me.mioclient.event.Subscribe;
import me.mioclient.module.player.FreecamModule;
import me.mioclient.record.Class_0905;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.Entity.RemovalReason;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.network.packet.s2c.common.DisconnectS2CPacket;
import net.minecraft.network.packet.s2c.play.DeathMessageS2CPacket;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.Explosion.DestructionType;

public final class Class_0103 extends Class_0618 {
   public static FreecamModule freecam = Hub.field_2595.method_78(FreecamModule.class);
   public final List<Class_0905> field_323 = new ArrayList<>();
   public Class_0922 field_324;
   public boolean field_325;
   public boolean field_326;
   public String field_327;
   public int current;

   public Class_0103() {
      super("fakeplayer");
      field_4220.method_14(this);
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      ((LiteralArgumentBuilder<CommandSource>)((LiteralArgumentBuilder<CommandSource>)((LiteralArgumentBuilder<CommandSource>)var1.then(
                  RequiredArgumentBuilder.<CommandSource, String>argument("name", StringArgumentType.word()).executes(var1x -> {
                     this.method_105((String)var1x.getArgument("name", String.class));
                     return 1;
                  })
               ))
               .then(LiteralArgumentBuilder.<CommandSource>literal("record").executes(var1x -> {
                  this.field_325 = !this.field_325;
                  if (this.field_325) {
                     this.field_323.clear();
                     Class_1245.method_2(Text.literal("Started recording."), Class_1245.method_38(-1));
                  } else {
                     Class_1245.method_2(Text.literal("Stopped recording."), Class_1245.method_38(-1));
                  }

                  return 1;
               })))
            .then(LiteralArgumentBuilder.<CommandSource>literal("play").executes(var1x -> {
               this.current = 0;
               this.field_326 = !this.field_326;
               return 1;
            })))
         .executes(var1x -> {
            this.method_105("Herobrine");
            return 1;
         });
   }

   public void method_105(String var1) {
      this.field_323.clear();
      this.field_325 = false;
      this.field_326 = false;
      if (this.field_324 == null) {
         this.field_324 = new Class_0922(field_4219.player, field_4219.world, new GameProfile(UUID.randomUUID(), var1));
         if (freecam.isToggled()) {
            this.field_324
               .refreshPositionAndAngles(
                  freecam.field_806.x, freecam.field_806.y, freecam.field_806.z, field_4219.player.getYaw(), field_4219.player.getPitch()
               );
         }

         this.field_324.setId(-9344);
         field_4219.world.addEntity(this.field_324);
         this.field_324.setOnGround(field_4219.player.isOnGround());
         this.field_327 = var1;
         Class_1245.method_2(Text.literal(new Class_1303().method_2((Object)var1).method_9("\u0001 has been spawned.")), Class_1245.method_38(-1));
      } else {
         field_4219.world.removeEntity(-9344, RemovalReason.DISCARDED);
         this.field_324 = null;
         String var2 = this.field_327 == null ? var1 : this.field_327;
         Class_1245.method_2(Text.literal(new Class_1303().method_2((Object)var2).method_9("\u0001 has been removed.")), Class_1245.method_38(-1));
      }
   }

   @Subscribe
   public void method_2(Event_19 var1) {
      if (var1.method_213() == PreType.POST && this.field_325) {
         this.field_323
            .add(
               new Class_0905(
                  field_4219.player.getPos(),
                  field_4219.player.getYaw(),
                  field_4219.player.getPitch(),
                  field_4219.player.getHeadYaw(),
                  field_4219.player.getVelocity(),
                  field_4219.player.isSneaking(),
                  field_4219.player.isOnGround()
               )
            );
      } else if (var1.method_213() == PreType.PRE && this.field_324 != null && this.field_326 && !this.field_323.isEmpty()) {
         this.current = (this.current + 1) % this.field_323.size();
         Class_0905 var2 = this.field_323.get(this.current);
         this.field_324.updateTrackedPositionAndAngles(var2.field_806.x, var2.field_806.y, var2.field_806.z, var2.field_1760, var2.field_1761, 3);
         this.field_324.setVelocity(var2.field_2837);
         this.field_324.setYaw(var2.field_1760);
         this.field_324.setPitch(var2.field_1761);
         this.field_324.setHeadYaw(var2.field_2836);
         this.field_324.setSneaking(var2.field_2838);
         this.field_324.setOnGround(var2.field_2839);
         if (this.field_324.isSneaking()) {
            this.field_324.setPose(EntityPose.CROUCHING);
         } else {
            this.field_324.setPose(EntityPose.STANDING);
         }
      }
   }

   @Subscribe
   public void method_2(Event_20 var1) {
      this.field_324 = null;
   }

   @Subscribe
   public void method_2(Event_10 var1) {
      if (this.field_324 != null) {
         if (var1.method_127() instanceof DisconnectS2CPacket || var1.method_127() instanceof DeathMessageS2CPacket) {
            this.field_324 = null;
         }

         if (var1.method_127() instanceof PlayerInteractEntityC2SPacket var2
            && Class_0144.method_9(var2) == Class_0034.ATTACK
            && Class_0144.method_2(var2).getId() == this.field_324.getId()) {
            field_4219.execute(() -> this.field_324.attack(Class_0144.method_2(var2)));
         }
      }
   }

   @Subscribe
   public void method_5(Event_4 var1) {
      if (var1.method_127() instanceof GameJoinS2CPacket) {
         this.field_324 = null;
      }

      if (this.field_324 != null) {
         if (var1.method_127() instanceof ExplosionS2CPacket var2) {
            double var18 = var2.getX();
            double var5 = var2.getY();
            double var7 = var2.getZ();
            double var9 = this.field_324.getPos().distanceTo(new Vec3d(var18, var5, var7)) / Double.longBitsToDouble(4622945017495814144L);
            if (var9 > Double.longBitsToDouble(4607182418800017408L)) {
               return;
            }

            float var11 = var2.getRadius();
            double var12 = (double)Explosion.getExposure(new Vec3d(var18, var5, var7), this.field_324);
            double var19;
            double var14 = var19 = (Double.longBitsToDouble(4607182418800017408L) - var9) * var12;
            float var16 = (float)(
               (var14 * var14 + var19)
                     / Double.longBitsToDouble(4611686018427387904L)
                     * Double.longBitsToDouble(4619567317775286272L)
                     * (double)var11
                     * Double.longBitsToDouble(4611686018427387904L)
                  + Double.longBitsToDouble(4607182418800017408L)
            );
            DamageSource var17 = field_4219.world
               .getDamageSources()
               .explosion(
                  new Explosion(field_4219.world, field_4219.player, var18, var5, var7, var11, false, DestructionType.DESTROY, var2.getAffectedBlocks())
               );
            this.field_324.damage(var17, var16);
         }
      }
   }
}
