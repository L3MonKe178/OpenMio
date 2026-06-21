package me.mioclient.module.combat;

import java.text.DecimalFormat;
import me.mioclient.Hub;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_11;
import me.mioclient.event.Event_4;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.PlayerUtil;
import me.mioclient.internal.Timer;
import me.mioclient.internal.Class_0382;
import me.mioclient.internal.Class_0396;
import me.mioclient.internal.TextBuilder;
import me.mioclient.internal.Class_1323;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.exploit.IllegalDisconnectModule;
import me.mioclient.module.misc.AutoReconnectModule;
import me.mioclient.setting.Setting;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.TntMinecartEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.s2c.common.DisconnectS2CPacket;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import nick.Settings;

public class AutoLogModule extends Module {
   public static final IllegalDisconnectModule illegaldisconnect = Hub.field_2595.method_78(IllegalDisconnectModule.class);
   public static AutoReconnectModule autoreconnect = Hub.field_2595.method_78(AutoReconnectModule.class);
   public Setting<Integer> field_190;
   public Setting<Float> field_191;
   public Setting<Boolean> field_192;
   public Setting<Boolean> field_193;
   public Setting<Boolean> field_194;
   public Setting<Boolean> field_195;
   public Setting<Boolean> field_196;
   public Setting<Boolean> field_197;
   public Setting<Boolean> field_198;
   public Setting<Integer> field_199;
   public Setting<Boolean> field_200;
   public Setting<Integer> field_201;
   public Setting<Boolean> field_202;
   public Setting<Boolean> field_203;
   public Setting<Float> field_204;
   public Runnable field_205;
   public final Timer field_206;

   public AutoLogModule() {
      super("AutoLog", "Logs you out so you don't have to fight anyone.", Category.COMBAT);
      Settings.initialize(this);
      this.field_206 = new Timer();
   }

   @Override
   public void onEnable() {
      this.field_205 = null;
   }

   @Override
   public String getInfo() {
      return new DecimalFormat("0.0").format(this.field_190.getValue());
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if (!this.method_535()) {
         if (!field_4219.player.isSpectator() && !field_4219.player.isInCreativeMode()) {
            if (!this.field_193.getValue() || Class_0382.method_29(field_4219.player)) {
               if (this.field_200.getValue() && this.method_90()) {
                  this.method_91("Your elytra durability is too low!");
               } else if (this.field_203.getValue() && field_4219.player.getY() < (double)(this.field_204.getValue() != null ? this.field_204.getValue().floatValue() : 0.0f)) {
                  this.method_91(new TextBuilder().method_2(String.valueOf(this.field_204.getValue())).method_9("You are below Y level \u0001"));
               } else if (!((float)field_4219.player.age / Float.intBitsToFloat(1101004800) < this.field_191.getValue())) {
                  if (this.field_195.getValue()) {
                     for (PlayerEntity var3 : field_4219.world.getPlayers()) {
                        if (!Hub.field_2603.method_1009(var3.getName().getString()) && var3 != field_4219.player) {
                           this.method_91(new TextBuilder().method_2(var3.getName().getString()).method_9("\u0001 has entered your render distance!"));
                        }
                     }
                  }

                  if (!field_4219.world
                        .getEntitiesByClass(
                           TntMinecartEntity.class,
                           field_4219.player
                              .getBoundingBox()
                              .expand(
                                 Double.longBitsToDouble(4616189618054758400L),
                                 Double.longBitsToDouble(4616189618054758400L),
                                 Double.longBitsToDouble(4616189618054758400L)
                              ),
                           TntMinecartEntity::isPrimed
                        )
                        .isEmpty()
                     && this.field_197.getValue()) {
                     this.method_91("You are at risk of getting blown up by a TNT minecart!");
                  }

                  if (this.field_196.getValue()) {
                     field_4219.world
                        .getEntities()
                        .forEach(
                           var1x -> {
                              if (var1x instanceof EndCrystalEntity
                                 && field_4219.player.squaredDistanceTo(var1x) <= Double.longBitsToDouble(4638707616191610880L)) {
                                 double var2 = (double)Class_1323.method_2(
                                    var1x.getPos(),
                                    field_4219.player,
                                    field_4219.player.getBoundingBox(),
                                    Double.longBitsToDouble(4618441417868443648L),
                                    true,
                                    null,
                                    null
                                 );
                                 if (var2 >= (double)Class_0396.method_76()) {
                                    this.method_91("You are at risk of getting blown up by an end crystal!");
                                 }
                              }
                           }
                        );
                  }

                  int var5 = (int)Class_0396.method_76();
                  if (!field_4219.player.isDead() && var5 != 0) {
                     if (!field_4219.isWindowFocused() || !this.field_194.getValue()) {
                        int var6 = field_4219.player
                           .getInventory()
                           .main
                           .stream()
                           .filter(var0 -> var0.getItem() == Items.TOTEM_OF_UNDYING)
                           .mapToInt(ItemStack::getCount)
                           .sum();
                        if (field_4219.player.getOffHandStack().getItem() == Items.TOTEM_OF_UNDYING) {
                           var6 += field_4219.player.getOffHandStack().getCount();
                        }

                        boolean var4 = var6 <= this.field_199.getValue();
                        if (!this.field_198.getValue()) {
                           var4 = true;
                        }

                        if (var5 <= this.field_190.getValue() && var4) {
                           this.method_91(new TextBuilder().method_2(var5).method_9("Your health is too low! (\u0001)."));
                        }
                     }
                  }
               }
            }
         }
      }
   }

   @Subscribe
   public void method_9(Event_11 var1) {
      if (this.method_535()) {
         this.field_205 = null;
      }

      if (this.field_205 != null && this.field_206.method_9(1500L)) {
         this.field_205.run();
         this.field_205 = null;
      }
   }

   @Subscribe
   public void method_5(Event_4 var1) {
      if (var1.method_127() instanceof DisconnectS2CPacket) {
         this.field_205 = null;
      }
   }

   public boolean method_90() {
      ItemStack var1 = field_4219.player.getInventory().getArmorStack(EquipmentSlot.CHEST.getEntitySlotId());
      if (var1.isOf(Items.ELYTRA) && var1.isDamageable()) {
         boolean var2 = PlayerUtil.method_29(var1) > (float)(this.field_201.getValue() != null ? this.field_201.getValue().intValue() : 0);
         if (var2) {
            return false;
         } else {
            if (this.field_202.getValue()) {
               for (ItemStack var4 : field_4219.player.getInventory().main) {
                  if (var4.isOf(Items.ELYTRA) && (!var4.isDamageable() || PlayerUtil.method_29(var4) > (float)(this.field_201.getValue() != null ? this.field_201.getValue().intValue() : 0))) {
                     return false;
                  }
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public void method_91(String var1) {
      if (this.field_192.getValue()) {
         this.disable();
      }

      ClientConnection var2 = field_4219.player.networkHandler.getConnection();
      if (var2 != null) {
         Hub.field_2602.field_3453 = true;
         autoreconnect.disable();
         Runnable var3 = () -> var2.disconnect(
               Text.empty()
                  .append(
                     Text.of(
                        new TextBuilder()
                           .method_2((Object)var1)
                           .method_2(String.valueOf(Formatting.RED))
                           .method_2(String.valueOf(Formatting.GRAY))
                           .method_9("\u0001[AutoLog] \u0001\u0001")
                     )
                  )
            );
         boolean var4 = !illegaldisconnect.field_480.getValue() || !IllegalDisconnectModule.method_205();
         if (illegaldisconnect.isToggled() && var4) {
            IllegalDisconnectModule.method_203();
            if (this.field_205 == null) {
               this.field_206.reset();
            }

            this.field_205 = var3;
            return;
         }

         var3.run();
      }
   }
}
