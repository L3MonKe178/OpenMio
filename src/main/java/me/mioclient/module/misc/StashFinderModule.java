package me.mioclient.module.misc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import me.mioclient.Hub;
import me.mioclient.enum_.Priority;
import me.mioclient.event.Event_5;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0211;
import me.mioclient.internal.Class_0242;
import me.mioclient.internal.Class_0580;
import me.mioclient.internal.Class_1225;
import me.mioclient.internal.Class_1245;
import me.mioclient.internal.Class_1303;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.block.entity.BarrelBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.ChestMinecartEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.chunk.WorldChunk;
import nick.Settings;

public class StashFinderModule extends Module {
   public Setting<Boolean> field_1495;
   public Setting<Integer> field_1496;
   public Setting<Boolean> field_1497;
   public Setting<Boolean> field_1498;
   public Setting<Boolean> field_1499;
   public Setting<Boolean> field_1500;
   public Setting<Boolean> field_1501;
   public Setting<Boolean> field_1502;
   public Setting<Class_0211> field_1503;
   public Setting<Float> field_1504;
   public Setting<Boolean> field_1505;
   public Setting<Boolean> field_1506;
   public Setting<Boolean> field_1507;
   public Setting<Boolean> field_1508;
   public Setting<Boolean> field_1509;
   public Setting<Boolean> field_1510;
   public final Class_0242 field_1511;

   public StashFinderModule() {
      super("StashFinder", "Logs and/or notifies you about possible stashes.", Category.MISC);
      Settings.initialize(this);
      this.field_1511 = new Class_0242();
      this.setDrawn(false);
   }

   @Subscribe
   public void method_2(Event_5 var1) {
      String var2 = new SimpleDateFormat("MM/dd HH:mm a").format(new Date());
      WorldChunk var3 = var1.method_1021();
      ChunkPos var4 = var3.getPos();
      int var5 = 0;

      for (BlockEntity var7 : var3.getBlockEntities().values()) {
         if (!this.field_1505.getValue()
            || !Class_1225.method_2(
               field_4219.world.getChunk(var7.getPos()),
               var7.getPos(),
               this.field_1507.getValue(),
               this.field_1508.getValue(),
               this.field_1509.getValue(),
               this.field_1506.getValue(),
               this.field_1510.getValue()
            )) {
            if (var7 instanceof ChestBlockEntity || var7 instanceof BarrelBlockEntity && !Hub.field_2622.method_101(var7.getPos())) {
               var5++;
            }

            if (this.field_1495.getValue() && var5 >= this.field_1496.getValue()) {
               String var15 = new Class_1303().method_2(var5).method_9("\u0001 chests");
               Class_0580 var18 = this.method_2(
                  new Class_1303().method_2((Object)var2).method_2((Object)var15).method_9("\u0001 (\u0001)"), var4.getCenterX(), var4.getCenterZ()
               );
               if (this.method_2(var18, var15)) {
                  break;
               }
            } else if (var7 instanceof ShulkerBoxBlockEntity) {
               ShulkerBoxBlockEntity var9 = (ShulkerBoxBlockEntity)var7;
               if (this.field_1497.getValue() && !Hub.field_2622.method_101(var9.getPos())) {
                  String var8 = "a shulkerbox";
                  Class_0580 var10 = this.method_2(
                     new Class_1303().method_2((Object)var2).method_2((Object)var8).method_9("\u0001 (\u0001)"), var4.getCenterX(), var4.getCenterZ()
                  );
                  if (this.method_2(var10, var8)) {
                     break;
                  }
               }
            }
         }
      }

      if (this.field_1498.getValue()) {
         ArrayList var13 = new ArrayList();

         for (Entity var16 : field_4219.world.getEntities()) {
            if (var16 instanceof ChestMinecartEntity var17 && var17.getChunkPos().equals(var17.getChunkPos())) {
               for (ChestMinecartEntity var11 : (Iterable<ChestMinecartEntity>)(Iterable<?>)(var13)) {
                  if (var17.getBoundingBox().intersects(var11.getBoundingBox())) {
                     Class_0580 var12 = this.method_2(
                        new Class_1303().method_2((Object)var2).method_9("Stacked minecarts (\u0001)"), var4.getCenterX(), var4.getCenterZ()
                     );
                     this.method_2(var12, "Stacked minecarts");
                     return;
                  }
               }

               var13.add(var17);
            }
         }
      }
   }

   public Class_0580 method_2(String var1, int var2, int var3) {
      String var4 = Class_1225.method_1071().name();
      String var5 = field_4219.player.networkHandler.getServerInfo() == null ? "singleplayer" : field_4219.player.networkHandler.getServerInfo().address;
      return new Class_0580(var1, var2, var3, var4, var5);
   }

   public boolean method_2(Class_0580 var1, String var2) {
      if (Hub.field_2618.method_2(var1x -> var1x.method_380() == var1.method_380() && var1x.method_396() == var1.method_396()).isEmpty()
         && Hub.field_2618.method_2(var1)) {
         if (this.field_1499.getValue()) {
            MutableText var3 = Text.literal(this.getName()).append(new Class_1303().method_2((Object)var2).method_9(" has found \u0001 at: "));
            if (!this.field_1500.getValue()) {
               var3.append(var1.method_599());
            }

            Class_1245.method_2(var3, Class_1245.method_38(-13579), Priority.LOW);
         }

         if (this.field_1502.getValue() && this.field_1511.method_2(Double.longBitsToDouble(4617315517961601024L), TimeUnit.SECONDS)) {
            Hub.field_2606.method_2(this.field_1503.getValue(), this.field_1504.getValue());
            this.field_1511.reset();
         }

         return true;
      } else {
         return false;
      }
   }
}
