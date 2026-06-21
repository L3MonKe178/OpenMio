package me.mioclient.module.misc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import me.mioclient.enum_.Orientation2;
import me.mioclient.event.Event_1;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0242;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.entity.player.PlayerModelPart;
import nick.Settings;

public class SkinFlickerModule extends Module {
   public static final PlayerModelPart[] field_2758 = new PlayerModelPart[]{
      PlayerModelPart.LEFT_SLEEVE,
      PlayerModelPart.JACKET,
      PlayerModelPart.HAT,
      PlayerModelPart.LEFT_PANTS_LEG,
      PlayerModelPart.RIGHT_PANTS_LEG,
      PlayerModelPart.RIGHT_SLEEVE
   };
   public static final PlayerModelPart[] field_2759 = new PlayerModelPart[]{
      PlayerModelPart.HAT,
      PlayerModelPart.JACKET,
      PlayerModelPart.LEFT_SLEEVE,
      PlayerModelPart.RIGHT_SLEEVE,
      PlayerModelPart.LEFT_PANTS_LEG,
      PlayerModelPart.RIGHT_PANTS_LEG
   };
   public Setting<Orientation2> field_2760;
   public Setting<Boolean> field_2761;
   public Setting<Float> field_2762;
   public final Map<PlayerModelPart, Boolean> field_2763;
   public final int field_2764;
   public final Class_0242 field_2765;
   public int field_2766;

   public SkinFlickerModule() {
      super("SkinFlicker", "Flicks your skin parts.", Category.MISC, "skinblinker");
      Settings.initialize(this);
      this.field_2763 = new HashMap<>();
      this.field_2764 = PlayerModelPart.values().length;
      this.field_2765 = new Class_0242();
   }

   @Override
   public void onEnable() {
      for (PlayerModelPart var4 : PlayerModelPart.values()) {
         this.field_2763.put(var4, field_4219.options.isPlayerModelPartEnabled(var4));
      }
   }

   @Override
   public void onDisable() {
      this.field_2763.forEach(field_4219.options::togglePlayerModelPart);
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if (this.field_2765.method_2((double)this.field_2762.getValue().floatValue(), TimeUnit.SECONDS)) {
         switch ((Orientation2)this.field_2760.getValue()) {
            case FULL:
               for (PlayerModelPart var5 : PlayerModelPart.values()) {
                  if (this.method_9(var5)) {
                     this.method_2(var5);
                  }
               }
               break;
            case VERTICAL:
            case HORIZONTAL:
               PlayerModelPart var6 = (this.field_2760.getValue() == Orientation2.HORIZONTAL ? field_2758 : field_2759)[this.field_2766 % 6];
               if (this.method_9(var6)) {
                  this.method_2(var6);
                  this.field_2766 = (this.field_2766 + 1) % 6;
               }
               break;
            case RANDOM:
               PlayerModelPart var2 = PlayerModelPart.values()[ThreadLocalRandom.current().nextInt(this.field_2764)];
               if (this.method_9(var2)) {
                  this.method_2(var2);
               }
         }

         this.field_2765.reset();
      }
   }

   public void method_2(PlayerModelPart var1) {
      field_4219.options.togglePlayerModelPart(var1, !field_4219.options.isPlayerModelPartEnabled(var1));
   }

   public boolean method_9(PlayerModelPart var1) {
      return this.field_2761.getValue() ? true : var1 != PlayerModelPart.CAPE;
   }
}
