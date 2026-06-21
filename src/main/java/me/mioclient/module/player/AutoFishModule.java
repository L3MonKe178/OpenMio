package me.mioclient.module.player;

import java.util.concurrent.TimeUnit;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_4;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0242;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import nick.Settings;

public class AutoFishModule extends Module {
   public Setting<Float> field_3086;
   public final Class_0242 field_3087;

   public AutoFishModule() {
      super("AutoFish", "Fishes.", Category.PLAYER);
      Settings.initialize(this);
      this.field_3087 = new Class_0242();
   }

   @Override
   public void onEnable() {
      this.field_3087.reset();
   }

   @Subscribe
   public void method_5(Event_4 var1) {
      if (var1.method_127() instanceof PlaySoundS2CPacket var2
         && var2.getSound().matches(var0 -> var0.getValue().toString().equalsIgnoreCase("minecraft:entity.fishing_bobber.splash"))
         && field_4219.player.fishHook != null
         && new Vec3d(var2.getX(), var2.getY(), var2.getZ()).distanceTo(field_4219.player.fishHook.getPos()) <= Double.longBitsToDouble(4616189618054758400L)) {
         field_4219.interactionManager.interactItem(field_4219.player, Hand.MAIN_HAND);
         field_4219.player.swingHand(Hand.MAIN_HAND);
         this.field_3087.reset();
      }
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if (field_4219.player.getMainHandStack().isOf(Items.FISHING_ROD)) {
         if (this.field_3087.method_2(Double.longBitsToDouble(4607182418800017408L), TimeUnit.MINUTES)
            || field_4219.player.fishHook == null && this.field_3087.method_2((double)this.field_3086.getValue().floatValue(), TimeUnit.SECONDS)) {
            field_4219.interactionManager.interactItem(field_4219.player, Hand.MAIN_HAND);
            field_4219.player.swingHand(Hand.MAIN_HAND);
            this.field_3087.reset();
         }
      }
   }
}
