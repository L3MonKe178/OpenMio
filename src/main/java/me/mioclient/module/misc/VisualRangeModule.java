package me.mioclient.module.misc;

import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import me.mioclient.Hub;
import me.mioclient.enum_.Priority;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_20;
import me.mioclient.event.Event_4;
import me.mioclient.event.Event_5;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0211;
import me.mioclient.internal.Class_0382;
import me.mioclient.internal.ChatUtil;
import me.mioclient.internal.TextBuilder;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.HoverEvent.Action;
import net.minecraft.util.Formatting;
import nick.Settings;

public class VisualRangeModule extends Module {
   public static final int field_3603 = -Math.abs("EnderPearl".hashCode());
   public Setting<Boolean> field_3604;
   public Setting<Boolean> field_3605;
   public Setting<Boolean> field_3606;
   public Setting<Boolean> field_3607;
   public Setting<Boolean> field_3608;
   public Setting<Boolean> field_3609;
   public Setting<Boolean> field_3610;
   public Setting<Boolean> field_3611;
   public Setting<Boolean> field_3612;
   public Setting<Boolean> field_3613;
   public Setting<Boolean> field_3614;
   public Setting<Class_0211> field_3615;
   public Setting<Float> field_3616;
   public final ArrayList<String> field_3617;
   public final ArrayList<String> field_3618;

   public VisualRangeModule() {
      super("VisualRange", "Informs you of players who enter/leave your visual range.", Category.MISC);
      Settings.initialize(this);
      this.field_3617 = new ArrayList<>();
      this.field_3618 = new ArrayList<>();
      this.setDrawn(false);
   }

   @Override
   public void onDisable() {
      this.field_3617.clear();
      this.field_3618.clear();
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if (!this.method_535()) {
         this.field_3618.clear();

         try {
            for (AbstractClientPlayerEntity var3 : field_4219.world.getPlayers()) {
               if (!var3.getName().equals(field_4219.player.getName()) && this.method_92(var3)) {
                  this.field_3618.add(var3.getName().getString());
               }
            }

            if (!this.field_3617.equals(this.field_3618)) {
               for (String var10 : this.field_3618) {
                  if (!this.field_3617.contains(var10) && this.field_3604.getValue()) {
                     Color var4 = Hub.field_2603.method_9(var10, Color.WHITE);
                     String var5 = "%s%s entered visual range.".formatted(var10, Formatting.WHITE);
                     MutableText var6 = Text.literal(var5).styled(var1x -> var1x.withColor(var4.hashCode()));
                     ChatUtil.method_2(var6, ChatUtil.method_38(Math.abs(var10.hashCode()) * -1 / 2), Priority.MID);
                     if (!Hub.field_2603.method_1009(var10) && Hub.field_2603.method_289(var10) || !this.field_3614.getValue()) {
                        this.method_387();
                     }
                  }
               }

               for (String var11 : this.field_3617) {
                  if (!this.field_3618.contains(var11) && this.field_3605.getValue()) {
                     Color var12 = Hub.field_2603.method_9(var11, Color.WHITE);
                     String var13 = "%s%s left visual range.".formatted(var11, Formatting.WHITE);
                     MutableText var14 = Text.literal(var13).styled(var1x -> var1x.withColor(var12.hashCode()));
                     ChatUtil.method_2(var14, ChatUtil.method_38(Math.abs(var11.hashCode()) * -1 / 2), Priority.LOW);
                  }
               }

               this.field_3617.clear();
               this.field_3617.addAll(this.field_3618);
            }
         } catch (Exception var7) {
         }
      }
   }

   @Subscribe
   public void method_5(Event_5 var1) {
      if (this.field_3611.getValue()) {
         AtomicBoolean var2 = new AtomicBoolean(false);
         var1.method_1021()
            .getBlockEntities()
            .forEach(
               (var1x, var2x) -> {
                  if (var2x instanceof SignBlockEntity var3) {
                     int var4 = -Math.abs(var1x.hashCode());
                     MutableText var5 = Text.literal("Found a sign: ");
                     boolean var6 = true;

                     for (Text var10 : var3.getFrontText().getMessages(false)) {
                        if (!Formatting.strip(var10.getString()).isBlank()) {
                           var5.append(var10);
                           var5.append(" ");
                           var6 = false;
                        }
                     }

                     if (var6) {
                        var5.append("<empty>");
                     }

                     var5.styled(var0x -> var0x.withFormatting(Formatting.WHITE));
                     String var11 = "%d, %d, %d".formatted(var1x.getX(), var1x.getY(), var1x.getZ());
                     MutableText var12 = Text.literal(new TextBuilder().method_2((Object)var11).method_9("at \u0001"));
                     HoverEvent var13 = new HoverEvent(Action.SHOW_TEXT, var12);
                     var5.styled(var1xx -> var1xx.withHoverEvent(var13));
                     var5.styled(
                        var1xx -> var1xx.withClickEvent(ChatUtil.method_52(new TextBuilder().method_2(var11.replace(",", "")).method_9("highlight \u0001")))
                     );
                     ChatUtil.method_2(var5, ChatUtil.method_38(var4));
                     var2.set(true);
                  }
               }
            );
         if (var2.get()) {
            this.method_387();
         }
      }
   }

   @Subscribe
   public void method_5(Event_4 var1) {
      if (var1.method_127() instanceof EntitySpawnS2CPacket var2 && var2.getEntityType() == EntityType.ENDER_PEARL && this.field_3612.getValue()) {
         MutableText var6 = Text.literal("Ender Pearl entered visual range.");
         String var4 = "%.0f, %.0f, %.0f".formatted(var2.getX(), var2.getY(), var2.getZ());
         MutableText var5 = Text.literal(new TextBuilder().method_2((Object)var4).method_9("at \u0001"));
         var6.styled(var1x -> var1x.withHoverEvent(new HoverEvent(Action.SHOW_TEXT, var5)));
         var6.styled(var1x -> var1x.withClickEvent(ChatUtil.method_52(new TextBuilder().method_2(var4.replace(",", "")).method_9("highlight \u0001"))));
         ChatUtil.method_2(var6, ChatUtil.method_38(field_3603), Priority.MID);
         this.method_387();
      }
   }

   public boolean method_92(PlayerEntity var1) {
      if (!Class_0382.method_29((LivingEntity)var1)) {
         return this.field_3610.getValue();
      } else if (Hub.field_2603.method_30(var1)) {
         return this.field_3608.getValue();
      } else {
         return Hub.field_2603.method_16(var1) ? this.field_3609.getValue() : this.field_3607.getValue();
      }
   }

   @Subscribe
   public void method_2(Event_20 var1) {
      this.field_3617.clear();
      this.field_3618.clear();
   }

   public void method_387() {
      if (this.field_3613.getValue()) {
         Hub.field_2606.method_2(this.field_3615.getValue()).method_230(this.field_3616.getValue());
      }
   }
}
