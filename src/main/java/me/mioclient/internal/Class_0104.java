package me.mioclient.internal;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.Color;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.UUID;
import me.mioclient.api.Class_1309;
import me.mioclient.enum_.Class_0062;
import me.mioclient.event.Event_3;
import me.mioclient.module.render.ESPModule;
import net.minecraft.block.MapColor.Brightness;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BarrelBlockEntity;
import net.minecraft.block.entity.BedBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.entity.DecoratedPotBlockEntity;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.block.entity.EnderChestBlockEntity;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.entity.projectile.thrown.ExperienceBottleEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class Class_0104 implements Class_1309 {
   public final ESPModule field_328;
   public final HashMap<UUID, String> field_329 = new HashMap<>();

   public Class_0104(ESPModule var1) {
      super();
      this.field_328 = var1;
   }

   public boolean method_98(Entity var1) {
      if (var1 == null || var1.getBoundingBox() == null) {
         return false;
      } else if (!Class_0485.method_4(var1.getBoundingBox())) {
         return false;
      } else {
         Vec3d var2 = field_4219.gameRenderer.getCamera().getPos();
         return var1 instanceof ItemEntity && var1.getPos().distanceTo(var2) > (double)this.field_328.field_3826.getValue().intValue()
            ? false
            : var1 instanceof PlayerEntity && this.field_328.field_3815.getValue() && var1 != field_4219.player
               || var1 instanceof ItemEntity && this.field_328.field_3824.getValue() && this.field_328.field_3825.getValue() != Class_0062.TEXT
               || var1 instanceof ExperienceBottleEntity && this.field_328.field_3833.getValue()
               || (var1 instanceof PassiveEntity || var1 instanceof FishEntity || var1 instanceof SquidEntity) && this.field_328.field_3818.getValue()
               || var1 instanceof Monster && this.field_328.field_3812.getValue()
               || var1 instanceof EnderPearlEntity && this.field_328.field_3821.getValue();
      }
   }

   public boolean method_9(BlockEntity var1) {
      if (var1 == null) {
         return false;
      } else {
         return !Class_0485.method_4(new Box(var1.getPos()))
            ? false
            : (var1 instanceof ChestBlockEntity || var1 instanceof BarrelBlockEntity) && this.field_328.field_3839.getValue()
               || var1 instanceof BedBlockEntity && this.field_328.field_3842.getValue()
               || var1 instanceof EnderChestBlockEntity && this.field_328.field_3840.getValue()
               || var1 instanceof ShulkerBoxBlockEntity && this.field_328.field_3841.getValue()
               || var1 instanceof AbstractFurnaceBlockEntity && this.field_328.field_3846.getValue()
               || var1 instanceof DispenserBlockEntity && this.field_328.field_3844.getValue()
               || var1 instanceof HopperBlockEntity && this.field_328.field_3845.getValue()
               || var1 instanceof SignBlockEntity && this.field_328.field_3843.getValue()
               || var1 instanceof DecoratedPotBlockEntity && this.field_328.field_3847.getValue();
      }
   }

   public boolean method_126() {
      return this.field_328.field_3839.getValue()
         || this.field_328.field_3840.getValue()
         || this.field_328.field_3841.getValue()
         || this.field_328.field_3842.getValue()
         || this.field_328.field_3843.getValue()
         || this.field_328.field_3844.getValue()
         || this.field_328.field_3845.getValue()
         || this.field_328.field_3846.getValue();
   }

   public void method_2(Event_3 var1, String var2, Vec3d var3, float var4, Color var5, Color var6) {
      double var7 = Class_0930.method_2(field_4219.gameRenderer.getCamera().getPos(), var3, (double)var4);
      if (var6 != null) {
         Class_0838.field_2672
            .method_2(var1.method_10(), var3, 0.0F, 0.0F, Class_1016.field_3143.method_221(var2), (float)Class_1016.field_3143.method_66(), var7, var6);
      }

      Class_0838.field_2672.method_2(var1.method_881(), var2, var3, 0.0F, 0.0F, var7, var5, true);
   }

   public void method_2(UUID var1) {
      try (HttpClient var2 = HttpClient.newHttpClient()) {
         HttpRequest var3 = HttpRequest.newBuilder()
            .uri(
               new URI(
                  new Class_1303().method_2(var1.toString().replace("-", "")).method_9("https://sessionserver.mojang.com/session/minecraft/profile/\u0001")
               )
            )
            .timeout(Duration.of(3L, ChronoUnit.SECONDS))
            .GET()
            .build();
         HttpResponse var4 = var2.send(var3, BodyHandlers.ofString());
         JsonObject var5 = JsonParser.parseString((String)var4.body()).getAsJsonObject();
         if (var5.has("name")) {
            String var6 = var5.get("name").getAsString();
            this.field_329.put(var1, var6);
         }
      } catch (Exception var9) {
      }
   }

   public String method_84(Entity var1) {
      UUID var2 = null;
      if (!this.field_328.field_3810.getValue()) {
         return null;
      } else {
         if (var1 instanceof EnderPearlEntity var3 && var3.getOwner() != null) {
            return var3.getOwner().getName().getString();
         }

         if (var1 instanceof TameableEntity var4) {
            var2 = var4.getOwnerUuid();
         }

         if (var2 != null) {
            PlayerEntity var5 = field_4219.world.getPlayerByUuid(var2);
            if (var5 != null) {
               String var7 = var5.getGameProfile().getName();
               this.field_329.putIfAbsent(var2, var7);
               return var7;
            }

            if (this.field_329.containsKey(var2)) {
               return this.field_329.get(var2);
            }

            this.field_329.put(var2, null);
            UUID var6 = var2;
            field_4221.submit(() -> this.method_2(var6));
         }

         return null;
      }
   }

   public Color method_9(Entity var1, boolean var2) {
      Color var3 = Color.white;
      if (var1 instanceof PlayerEntity) {
         var3 = var2 ? this.field_328.field_3816.getValue() : this.field_328.field_3817.getValue();
      } else if (var1 instanceof ItemEntity) {
         var3 = var2 ? this.field_328.field_3831.getValue() : this.field_328.field_3832.getValue();
      } else if (var1 instanceof ExperienceBottleEntity) {
         var3 = var2 ? this.field_328.field_3834.getValue() : this.field_328.field_3835.getValue();
      } else if (!(var1 instanceof PassiveEntity) && !(var1 instanceof FishEntity) && !(var1 instanceof SquidEntity)) {
         if (var1 instanceof Monster) {
            var3 = var2 ? this.field_328.field_3813.getValue() : this.field_328.field_3814.getValue();
         } else if (var1 instanceof EnderPearlEntity) {
            var3 = var2 ? this.field_328.field_3822.getValue() : this.field_328.field_3823.getValue();
         }
      } else {
         var3 = var2 ? this.field_328.field_3819.getValue() : this.field_328.field_3820.getValue();
      }

      int var4 = (int)MathHelper.clamp(
         Class_0930.method_5((float)(var1.age - 1), (float)var1.age) * Float.intBitsToFloat(1101004800), 0.0F, (float)var3.getAlpha()
      );
      if (var1 instanceof EnderPearlEntity || var1 instanceof ExperienceBottleEntity) {
         var4 = var3.getAlpha();
      }

      return Class_1081.method_9(var3, var4);
   }

   public Color method_5(BlockEntity var1) {
      int var2 = field_4219.world.getBlockState(var1.getPos()).getMapColor(field_4219.world, var1.getPos()).getRenderColor(Brightness.HIGH);
      if (var1 instanceof ShulkerBoxBlockEntity) {
         return new Color(255, 0, 175);
      } else if (var1 instanceof EnderChestBlockEntity) {
         return new Color(125, 40, 180);
      } else {
         if (var1 instanceof BedBlockEntity var3) {
            var2 = var3.getColor().getMapColor().getRenderColor(Brightness.HIGH);
         }

         int var6 = var2 >> 16 & 0xFF;
         int var4 = var2 >> 8 & 0xFF;
         int var5 = var2 & 0xFF;
         return new Color(var5, var4, var6).brighter();
      }
   }
}
