package me.mioclient.module.misc;

import java.util.concurrent.ThreadLocalRandom;
import me.mioclient.enum_.Class_1379;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.text.Text;
import nick.Settings;

public class CustomDeathTextModule extends Module {
   public Setting<Class_1379> field_493;
   public Setting<String> field_494;
   public static final String[] field_495 = new String[]{
      "YOU USELESS SHITSTAIN",
      "GAME OVER MOTHERFUCKER! YOU SUCK!",
      "HOW THE HELL ARE YOU SO BAD AT THIS GAME?",
      "HOW DO YOU FUCK UP SO BADLY JESUS FUCKING CHRIST?",
      "YOU DIED! CONGRATS FUCKFACE",
      "OH MY GOD YOU SUCK! AUTO-UNINSTALLING",
      "HOLY FUCKING SHIT SERIOUSLY",
      "YOU FUCKING RETARD!",
      "YOU DENSE FUCK"
   };

   public CustomDeathTextModule() {
      super("CustomDeathText", "Displays a custom message on your death screen.", Category.MISC);
      Settings.initialize(this);
      this.setDrawn(false);
   }

   public Text method_210() {
      return (Text)(this.field_493.getValue() == Class_1379.CUSTOM
         ? Text.of((this.field_494.getValue() != null ? this.field_494.getValue().trim() : ""))
         : Text.literal(field_495[ThreadLocalRandom.current().nextInt(field_495.length)]));
   }
}
