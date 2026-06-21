package me.mioclient;

import java.util.Locale;
import java.util.Locale.Category;
import me.mioclient.api.Class_0230;
import me.mioclient.api.Class_1309;
import me.mioclient.event.Event_63;
import me.mioclient.internal.Class_0005;
import me.mioclient.internal.Class_0092;
import me.mioclient.internal.Class_0114;
import me.mioclient.internal.Class_0160;
import me.mioclient.internal.Class_0178;
import me.mioclient.internal.Class_0191;
import me.mioclient.internal.Class_0291;
import me.mioclient.internal.Class_0295;
import me.mioclient.internal.Class_0303;
import me.mioclient.internal.Class_0306;
import me.mioclient.internal.Class_0314;
import me.mioclient.internal.Class_0406;
import me.mioclient.internal.Class_0429;
import me.mioclient.internal.Class_0433;
import me.mioclient.internal.Class_0447;
import me.mioclient.internal.Class_0465;
import me.mioclient.internal.Class_0473;
import me.mioclient.internal.Class_0485;
import me.mioclient.internal.Class_0505;
import me.mioclient.internal.Class_0508;
import me.mioclient.internal.Class_0617;
import me.mioclient.internal.Class_0625;
import me.mioclient.internal.Class_0628;
import me.mioclient.internal.Class_0668;
import me.mioclient.internal.Class_0724;
import me.mioclient.internal.Class_0873;
import me.mioclient.internal.Class_0904;
import me.mioclient.internal.Class_0928;
import me.mioclient.internal.Class_0965;
import me.mioclient.internal.Class_0972;
import me.mioclient.internal.Class_0975;
import me.mioclient.internal.Class_1032;
import me.mioclient.internal.Class_1094;
import me.mioclient.internal.Class_1114;
import me.mioclient.internal.Class_1192;
import me.mioclient.internal.Class_1280;
import me.mioclient.internal.Class_1297;
import me.mioclient.internal.Class_1328;
import me.mioclient.internal.Class_1337;

public class Hub {
   public static Class_0668 field_2595 = new Class_0668();
   public static Class_0508 field_2596;
   public static Class_1328 field_2597;
   public static Class_0485 field_2598;
   public static Class_0965 field_2599;
   public static Class_1032 field_2600;
   public static Class_0904 field_2601;
   public static Class_1114 field_2602;
   public static Class_1280 field_2603;
   public static Class_0314 field_2604;
   public static Class_0191 field_2605;
   public static Class_0505 field_2606;
   public static Class_0092 field_2607;
   public static Class_0465 field_2608;
   public static Class_0873 field_2609;
   public static Class_0291 field_2610;
   public static Class_0928 field_2611;
   public static Class_0628 field_2612;
   public static Class_0160 field_2613;
   public static Class_0972 field_2614;
   public static Class_1297 field_2615;
   public static Class_0178 field_2616;
   public static Class_1337 field_2617;
   public static Class_0306 field_2618;
   public static Class_0625 field_2619;
   public static Class_1192 field_2620;
   public static Class_0724 field_2621;
   public static Class_0303 field_2622;
   public static Class_0975 field_2623;
   public static Class_0433 field_2624;
   public static Class_0447 field_2625;
   public static Class_0005 field_2626;
   public static Class_0295 field_2627;
   public static Class_0114 field_2628;
   public static Class_1094 field_2629;
   public static Class_0230 field_2630;
   public static Class_0406 field_2631;
   public static Class_0617 field_2632;

   public Hub() {
      super();
   }

   public static void method_753() {
      try {
         Class.forName("baritone.api.BaritoneAPI");
         field_2630 = new Class_0473();
      } catch (Throwable var1) {
         field_2630 = new Class_0429();
      }

      field_2609 = new Class_0873();
      field_2615 = new Class_1297();
      field_2602 = new Class_1114();
      field_2629 = new Class_1094();
      field_2596 = new Class_0508();
      field_2617 = new Class_1337();
      field_2612 = new Class_0628();
      field_2605 = new Class_0191();
      field_2613 = new Class_0160();
      field_2614 = new Class_0972();
      field_2598 = new Class_0485();
      field_2604 = new Class_0314();
      field_2616 = new Class_0178();
      field_2619 = new Class_0625();
      field_2599 = new Class_0965();
      field_2620 = new Class_1192();
      field_2610 = new Class_0291();
      field_2600 = new Class_1032();
      field_2601 = new Class_0904();
      field_2607 = new Class_0092();
      field_2603 = new Class_1280();
      field_2606 = new Class_0505();
      field_2611 = new Class_0928();
      field_2608 = new Class_0465();
      field_2618 = new Class_0306();
      field_2621 = new Class_0724();
      field_2622 = new Class_0303();
      field_2623 = new Class_0975();
      field_2624 = new Class_0433();
      field_2625 = new Class_0447();
      field_2626 = new Class_0005();
      field_2627 = new Class_0295();
      field_2628 = new Class_0114();
      Locale.setDefault(Category.FORMAT, Locale.US);
      method_754();
   }

   public static void method_754() {
      field_2595.method_669();
      Class_1309.field_4220.method_36(new Event_63());
      field_2606.method_534();
      field_2597 = new Class_1328();
      field_2597.method_840();
   }

   public static Class_0406 method_755() {
      if (field_2631 == null) {
         field_2631 = new Class_0406();
      }

      return field_2631;
   }

   public static Class_0617 method_756() {
      if (field_2632 == null) {
         field_2632 = new Class_0617();
      }

      return field_2632;
   }
}
