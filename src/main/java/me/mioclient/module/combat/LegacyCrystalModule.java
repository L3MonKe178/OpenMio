package me.mioclient.module.combat;

import java.util.HashMap;
import java.util.Map;
import me.mioclient.enum_.Class_0034;
import me.mioclient.event.Event_10;
import me.mioclient.event.Event_17;
import me.mioclient.event.Event_36;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0136;
import me.mioclient.internal.Class_0144;
import me.mioclient.internal.Class_0242;
import me.mioclient.internal.Class_1035;
import me.mioclient.mixin.ducks.DuckMinecraftClient;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.Entity.RemovalReason;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import nick.Settings;

public class LegacyCrystalModule extends Module {
   public Setting<Integer> field_4339;
   public Setting<Boolean> field_4340;
   public final Map<BlockPos, Long> field_4341;
   public final Class_0242 field_4342;
   public long field_4343;

   public LegacyCrystalModule() {
      super("LegacyCrystal", "Legit auto crystal (crystal aura).", Category.COMBAT);
      Settings.initialize(this);
      this.field_4341 = new HashMap<>();
      this.field_4342 = new Class_0242();
      this.field_4343 = 150L;
   }

   @Subscribe
   public void method_9(Event_17 var1) {
      synchronized (this.field_4341) {
         this.field_4341.entrySet().removeIf(var0 -> var0.getValue() + 15000L < System.currentTimeMillis());
      }

      if (field_4219.crosshairTarget instanceof EntityHitResult) {
         Entity var5 = ((EntityHitResult)field_4219.crosshairTarget).getEntity();
         if (this.field_4342.method_9(this.field_4343) && var5 instanceof EndCrystalEntity && this.field_4341.containsKey(var5.getBlockPos().down())) {
            ((DuckMinecraftClient)field_4219).attack();
            this.field_4343 = (long)(Math.random() * (double)this.field_4339.getValue().intValue());
            this.field_4342.reset();
         }
      }
   }

   @Subscribe
   public void method_2(Event_36 var1) {
      if (field_4219.world.getBlockState(var1.method_382().getBlockPos().up(2)).isAir()) {
         boolean var2 = Class_1035.method_2(var1.method_382().getBlockPos(), false, false, true, false, false, false);
         if (field_4219.player.getMainHandStack().getItem() instanceof SwordItem) {
            Class_0136.method_16(Class_0136.method_5(var2 ? Items.END_CRYSTAL : Items.OBSIDIAN));
         } else if (field_4219.player.getMainHandStack().isOf(Items.OBSIDIAN) && var2) {
            Class_0136.method_78(Class_0136.method_5(Items.END_CRYSTAL));
         }
      }
   }

   @Subscribe
   public void method_2(Event_10 var1) {
      if (var1.method_127() instanceof PlayerInteractBlockC2SPacket var2 && field_4219.player.getStackInHand(var2.getHand()).isOf(Items.END_CRYSTAL)) {
         this.field_4341.compute(var2.getBlockHitResult().getBlockPos(), (var0, var1x) -> System.currentTimeMillis());
      }

      if (var1.method_127() instanceof PlayerInteractEntityC2SPacket var4
         && this.field_4340.getValue()
         && Class_0144.method_9(var4) == Class_0034.ATTACK
         && Class_0144.method_2(var4) instanceof EndCrystalEntity) {
         Class_0144.method_2(var4).setRemoved(RemovalReason.KILLED);
      }
   }
}
