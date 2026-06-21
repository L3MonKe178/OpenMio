package me.mioclient.internal;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import me.mioclient.Hub;
import me.mioclient.api.MioAPI;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_20;
import me.mioclient.event.Event_22;
import me.mioclient.event.Event_4;
import me.mioclient.event.Subscribe;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.MapIdComponent;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.map.MapState;
import net.minecraft.network.packet.s2c.play.MapUpdateS2CPacket;

public final class Class_0433 implements MioAPI {
   public final Timer field_1367 = new Timer();
   public final Map<String, MapState> field_1368 = Collections.synchronizedMap(new HashMap<>());
   public String field_1369 = null;

   public Class_0433() {
      super();
      field_4220.method_14(this);
   }

   public MapState method_9(ItemStack var1, int var2) {
      return this.method_2(var1, new MapIdComponent(var2));
   }

   public MapState method_2(ItemStack var1, MapIdComponent var2) {
      MapState var3 = FilledMapItem.getMapState(var2, field_4219.world);
      if (var2.id() != -1 && var3 != null) {
         return var3;
      } else {
         String var4 = var1.getName().getString();
         return var1.contains(DataComponentTypes.CUSTOM_NAME) ? this.field_1368.getOrDefault(var4, null) : null;
      }
   }

   @Subscribe
   public void method_5(Event_4 var1) {
      if (var1.method_127() instanceof MapUpdateS2CPacket) {
         Hub.field_2619.method_2(() -> {
            for (ItemStack var2 : field_4219.player.getInventory().main) {
               this.method_30(var2);
            }
         }, 1);
      }
   }

   @Subscribe
   public void method_2(Event_22 var1) {
      this.field_1369 = var1.method_106();
      this.method_176(this.field_1369);
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if (this.field_1367.method_2(5L, TimeUnit.MINUTES) && this.field_1369 != null) {
         CompletableFuture.runAsync(() -> this.method_468(this.field_1369), field_4221);
      }
   }

   @Subscribe
   public void method_2(Event_20 var1) {
      if (this.field_1369 != null) {
         this.method_468(this.field_1369);
      }
   }

   public void method_30(ItemStack var1) {
      if (var1.getItem() == Items.FILLED_MAP && var1.contains(DataComponentTypes.CUSTOM_NAME)) {
         String var2 = var1.getName().getString();
         MapIdComponent var3 = (MapIdComponent)var1.getOrDefault(DataComponentTypes.MAP_ID, new MapIdComponent(-1));
         this.field_1368.compute(var2, (var1x, var2x) -> FilledMapItem.getMapState(var3, field_4219.world));
      }
   }

   public void method_468(String var1) {
      if (!this.field_1368.isEmpty()) {
         ByteArrayOutputStream var2 = new ByteArrayOutputStream();
         DataOutputStream var3 = new DataOutputStream(var2);

         try {
            var3.writeInt(this.field_1368.size());

            for (Entry var5 : new HashSet<>(this.field_1368.entrySet())) {
               var3.writeUTF((String)var5.getKey());
               var3.writeByte(((MapState)var5.getValue()).scale);

               for (byte var9 : ((MapState)var5.getValue()).colors) {
                  var3.writeByte(var9);
               }
            }

            Class_1222.method_2(
               Class_1328.field_4286.resolve(new TextBuilder().method_2(FontRenderer.method_222(var1)).method_9("\u0001.data")),
               this.method_374(var2.toByteArray())
            );
         } catch (Exception var10) {
            var10.printStackTrace();
         }
      }
   }

   public void method_176(String var1) {
      this.field_1368.clear();

      try {
         Path var2 = Class_1328.field_4286.resolve(new TextBuilder().method_2(FontRenderer.method_222(var1)).method_9("\u0001.data"));
         if (!var2.toFile().exists()) {
            return;
         }

         byte[] var3 = this.method_107(Class_1222.method_2(var2));
         ByteArrayInputStream var4 = new ByteArrayInputStream(var3);
         DataInputStream var5 = new DataInputStream(var4);
         int var6 = var5.readInt();

         for (int var7 = 0; var7 < var6; var7++) {
            String var8 = var5.readUTF();
            byte var9 = var5.readByte();
            MapState var10 = MapState.of(var9, true, null);

            for (int var11 = 0; var11 < 16384; var11++) {
               var10.colors[var11] = var5.readByte();
            }

            this.field_1368.put(var8, var10);
         }
      } catch (Exception var12) {
         var12.printStackTrace();
      }
   }

   public byte[] method_107(byte[] var1) {
      try {
         ByteArrayOutputStream var2 = new ByteArrayOutputStream();
         Inflater var3 = new Inflater();
         var3.setInput(var1);
         byte[] var4 = new byte[8192];

         while (!var3.finished()) {
            int var5 = var3.inflate(var4);
            if (var5 == 0) {
               break;
            }

            var2.write(var4, 0, var5);
         }

         var3.end();
         return var2.toByteArray();
      } catch (Exception var6) {
         throw new RuntimeException();
      }
   }

   public byte[] method_374(byte[] var1) {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      Deflater var3 = new Deflater(9);
      var3.setInput(var1);
      var3.finish();
      byte[] var4 = new byte[8192];

      while (!var3.finished()) {
         int var5 = var3.deflate(var4);
         var2.write(var4, 0, var5);
      }

      var3.end();
      return var2.toByteArray();
   }
}
