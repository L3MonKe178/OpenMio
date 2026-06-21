package me.mioclient.module.misc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import me.mioclient.enum_.PreType;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_16;
import me.mioclient.event.Event_18;
import me.mioclient.event.Event_46;
import me.mioclient.event.Event_57;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Timer;
import me.mioclient.internal.Constants;
import me.mioclient.internal.ChatUtil;
import me.mioclient.internal.TextBuilder;
import me.mioclient.mixin.ducks.DuckMinecraftClient;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.util.Hand;
import nick.Settings;

public class AntiAFKModule extends Module {
   public Setting<Float> field_3232;
   public Setting<Boolean> field_3233;
   public Setting<Boolean> field_3234;
   public Setting<Boolean> field_3235;
   public Setting<Boolean> field_3236;
   public Setting<Boolean> field_3237;
   public Setting<Boolean> field_3238;
   public final Timer field_3239;
   public final Timer field_3240;
   public boolean field_3241;
   public boolean field_3242;

   public AntiAFKModule() {
      super("AntiAFK", "Prevents you from being kicked for AFK-ing.", Category.MISC);
      Settings.initialize(this);
      this.field_3239 = new Timer();
      this.field_3240 = new Timer();
   }

   @Override
   public void onEnable() {
      this.field_3239.reset();
      this.field_3240.reset();
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if (this.method_942()) {
         if (field_4219.options.jumpKey.isPressed() && this.field_3234.getValue()) {
            field_4219.options.jumpKey.setPressed(false);
         }

         if (this.field_3240.method_2((double)(this.field_3232.getValue() != null ? this.field_3232.getValue().floatValue() : 0.0f), TimeUnit.SECONDS)) {
            List var2 = this.method_941();
            Collections.shuffle(var2);
            if (!var2.isEmpty()) {
               ((Runnable)var2.get(0)).run();
            }

            this.field_3240.reset();
         }
      }
   }

   @Subscribe(
      method_800 = 100
   )
   public void method_9(Event_46 var1) {
      if (this.field_3233.getValue()
         && this.method_942()
         && var1.method_213() == PreType.PRE
         && var1.method_1033() != null
         && ChatUtil.method_107(var1.method_1033().getString())) {
         field_4219.executeSync(
            () -> field_4219.player
                  .networkHandler
                  .sendChatCommand(
                     new TextBuilder()
                        .method_2((int)Math.floor(Math.random() * Double.longBitsToDouble(4666723172467343360L)))
                        .method_2(field_4219.player.getName().getString())
                        .method_9("r Hello! This is auto reply talking. \u0001 is currently AFK :'). [\u0001]")
                  )
         );
      }
   }

   @Subscribe
   public void method_2(Event_57 var1) {
      this.field_3239.reset();
   }

   @Subscribe
   public void method_9(Event_18 var1) {
      this.field_3239.reset();
   }

   @Subscribe
   public void method_9(Event_16 var1) {
      if (this.field_3241) {
         var1.method_276().jumping = true;
      }

      if (this.field_3242) {
         var1.method_276().sneaking = true;
      }

      this.field_3241 = this.field_3242 = false;
   }

   public List<Runnable> method_941() {
      ArrayList<Runnable> var1 = new ArrayList<>();
      if (this.field_3234.getValue()) {
         var1.add(() -> this.field_3241 = true);
      }

      if (this.field_3235.getValue()) {
         var1.add(() -> this.field_3242 = true);
      }

      if (this.field_3236.getValue()) {
         Random var2 = new Random();
         var1.add(() -> {
            field_4219.player.setYaw(var2.nextFloat((float)Constants.field_686));
            field_4219.player.setPitch(var2.nextFloat(Float.intBitsToFloat(1127481344)) - (float)Constants.field_685);
         });
      }

      if (this.field_3237.getValue()) {
         var1.add(() -> {
            if (this.field_3238.getValue()) {
               field_4219.player.swingHand(Hand.MAIN_HAND);
            } else {
               ((DuckMinecraftClient)field_4219).attack();
            }
         });
      }

      return var1;
   }

   public boolean method_942() {
      return this.field_3239.method_9((long)(this.field_3232.getValue() * Float.intBitsToFloat(1148846080)));
   }
}
