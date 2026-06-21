package me.mioclient.module.combat;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import me.mioclient.Hub;
import me.mioclient.api.Class_1291;
import me.mioclient.enum_.Class_1172;
import me.mioclient.enum_.Class_1239;
import me.mioclient.event.Event_7;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0077;
import me.mioclient.internal.Class_0168;
import me.mioclient.internal.Class_0435;
import me.mioclient.internal.RotationManager;
import me.mioclient.internal.Class_0771;
import me.mioclient.internal.Class_0994;
import me.mioclient.internal.Class_1035;
import me.mioclient.internal.Class_1096;
import me.mioclient.internal.Class_1128;
import me.mioclient.internal.Class_1164;
import me.mioclient.internal.Class_1174;
import me.mioclient.internal.Class_1225;
import me.mioclient.internal.TextBuilder;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.abstract_.AbstractModule_28;
import me.mioclient.module.player.SpeedMineModule;
import me.mioclient.setting.Setting;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import nick.Settings;

public final class CombatmineModule extends Module {
   public static final int field_3689 = 1500;
   public static final SpeedMineModule field_3690 = Hub.field_2595.method_78(SpeedMineModule.class);
   public static final AbstractModule_28 field_3691 = Hub.field_2595.method_78(AbstractModule_28.class);
   public Setting<Class_1239> field_3692;
   public Setting<Boolean> field_3693;
   public Setting<Boolean> field_3694;
   public Setting<Boolean> field_3695;
   public Setting<Boolean> field_3696;
   public Setting<Boolean> field_3697;
   public Setting<Boolean> field_3698;
   public Setting<Boolean> field_3699;
   public Setting<Boolean> field_3700;
   public Setting<Boolean> field_3701;
   public Setting<Boolean> field_3702;
   public Setting<Boolean> field_3703;
   public Setting<Boolean> field_3704;
   public Setting<Boolean> field_3705;
   public Setting<Boolean> field_3706;
   public Setting<Boolean> field_3707;
   public Setting<Boolean> field_3708;
   public Setting<Integer> field_3709;
   public final List<Class_1291> field_3710;
   public final Class_1096 field_3711;
   public final Class_0077 field_3712;
   public final AtomicBoolean field_3713;
   public boolean field_3714;

   public CombatmineModule() {
      super(
         "AutoMine",
         new TextBuilder()
            .method_2(String.valueOf(Formatting.RED))
            .method_9("Mines out traps for your enemies and farms obsidian. \n\u0001Requires SpeedMine module enabled."),
         Category.COMBAT,
         "combatmine",
         "autocity"
      );
      Settings.initialize(this);
      this.field_3710 = List.of(
         new Class_1174(this), new Class_0168(this), new Class_0435(this), new Class_0771(this), new Class_1164(this), new Class_1128(this)
      );
      this.field_3711 = new Class_1096(this);
      this.field_3712 = new Class_0077(this);
      this.field_3713 = new AtomicBoolean();
   }

   @Subscribe(
      method_800 = 300
   )
   public void method_7(Event_7 var1) {
      if (field_3690.isToggled()) {
         Class_0994 var2 = new Class_0994();

         for (Class_1291 var4 : this.field_3710) {
            if (var4.method_206()) {
               var4.method_2(var2);
            }
         }

         if ((!this.method_1045() || var2.method_52() >= 1500) && !this.field_3713.get()) {
            if (var2.method_896()) {
               field_3690.method_1116();
            } else {
               if (var2.method_111() == null) {
                  if (var2.method_897() == null || var2.method_897().equals(this.field_3712.method_111())) {
                     return;
                  }

                  var2.method_425(var2.method_897());
                  var2.method_465(null);
               }

               if (!this.method_1047() || !this.field_3712.method_110() || var2.method_52() >= 1500) {
                  BlockPos var6 = var2.method_111();
                  BlockPos var7 = var2.method_897();
                  Direction var5 = this.method_158(var6);
                  if (var5 != null) {
                     if (!var6.equals(field_3690.method_1120())) {
                        if (this.method_1047() && var7 != null && !var7.equals(var6) && !this.field_3712.method_110()) {
                           this.method_2(var7, var5, false);
                        }

                        if (field_3691.method_1052()) {
                           this.field_3713.set(true);
                           Hub.field_2619.method_2(() -> {
                              this.field_3713.set(false);
                              this.method_2(var6, var5, true);
                           }, this.field_3709.getValue());
                        } else {
                           this.method_2(var6, var5, true);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public void method_2(BlockPos var1, Direction var2, boolean var3) {
      if (this.field_3695.getValue()) {
         float[] var4 = RotationManager.method_78(var1.toCenterPos());
         if (this.field_3694.getValue()) {
            Vec3d var5 = Class_1225.method_2(var1, Class_1172.field_3634);
            if (var5 != null) {
               var4 = RotationManager.method_78(var5);
            }
         }

         Hub.field_2598.method_2(var4, 100);
      }

      SpeedMineModule.field_3986 = var3;
      field_4219.interactionManager.attackBlock(var1, var2);
      SpeedMineModule.field_3986 = false;
   }

   public Direction method_158(BlockPos var1) {
      if (this.field_3693.getValue()) {
         List var2 = Class_1035.method_39(var1);
         return var2.isEmpty() ? null : (Direction)var2.getFirst();
      } else {
         return Direction.UP;
      }
   }

   public boolean method_1045() {
      if (this.method_1046()) {
         if (field_3690.method_1120() == null) {
            this.method_251(false);
         } else {
            this.method_251(!Class_1225.method_14(field_3690.method_1120()));
         }
      }

      return this.method_1046();
   }

   public void method_251(boolean var1) {
      this.field_3714 = var1;
   }

   public boolean method_1046() {
      return this.field_3714;
   }

   public float method_884() {
      return field_3690.field_3989.getValue();
   }

   public boolean method_1047() {
      return field_3690.field_3990.getValue();
   }
}
