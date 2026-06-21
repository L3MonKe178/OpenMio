package me.mioclient.module.abstract_;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_0274;
import me.mioclient.event.Event_1;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0121;
import me.mioclient.internal.Class_0723;
import me.mioclient.internal.FontRenderer;
import me.mioclient.internal.TextBuilder;
import me.mioclient.setting.BooleanSetting;
import me.mioclient.setting.Setting;
import net.minecraft.block.entity.BarrelBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class AbstractModule_11 extends AbstractModule_26 {
   public final Setting<Boolean> field_3969 = this.add(new BooleanSetting("Speed", true).method_460());
   public final Setting<Boolean> field_3970 = this.add(new BooleanSetting("BPS", false).method_2(this.field_3969));
   public final Setting<Boolean> field_3971 = this.add(new BooleanSetting("TPS", true));
   public final Setting<Boolean> field_3972 = this.add(new BooleanSetting("Ping", true));
   public final Setting<Boolean> field_3973 = this.add(new BooleanSetting("FPS", true));
   public final Setting<Boolean> field_3974 = this.add(new BooleanSetting("ServerBrand", true));
   public final Setting<Boolean> field_3975 = this.add(new BooleanSetting("Durability", false));
   public final Setting<Boolean> field_3976 = this.add(new BooleanSetting("Chest", false).method_460());
   public final Setting<Boolean> field_3977 = this.add(new BooleanSetting("Double", false).method_2(this.field_3976));
   public final List<Class_0121> field_3978 = new ArrayList<>();
   public int field_3979;

   public AbstractModule_11() {
      super("Metrics", "infohud", "info");
      this.method_2(new Class_0723(this, this.field_3978));
      this.method_14().method_2(Class_0274.BOTTOM_RIGHT);
   }

   @Override
   public void onEnable() {
      this.field_3978.clear();
      this.field_3978
         .addAll(
            List.of(
               new Class_0121(
                  () -> Text.literal(
                        new TextBuilder()
                           .method_2(this.field_3970.getValue() ? "b/s" : "km/h")
                           .method_9("Speed %s%.2f\u0001")
                           .formatted(
                              Formatting.WHITE,
                              Hub.field_2614.method_875()
                                 / (this.field_3970.getValue() ? Double.longBitsToDouble(4615288898129284301L) : Double.longBitsToDouble(4607182418800017408L))
                           )
                           .replace(",", ".")
                     ),
                  this.field_3969::getValue
               ),
               new Class_0121(
                  () -> Text.literal("TPS %s%.2f".formatted(Formatting.WHITE, Hub.field_2602.method_989()).replace(",", ".")), this.field_3971::getValue
               ),
               new Class_0121(() -> Text.literal("Ping %s%dms".formatted(Formatting.WHITE, Hub.field_2602.method_983())), this.field_3972::getValue),
               new Class_0121(() -> Text.literal("FPS %s%d".formatted(Formatting.WHITE, Hub.field_2601.method_814())), this.field_3973::getValue),
               new Class_0121(
                  () -> Text.literal("ServerBrand %s%s".formatted(Formatting.WHITE, field_4219.player.networkHandler.getBrand())), this.field_3974::getValue
               ),
               new Class_0121(
                  () -> Text.literal(
                        "Durability %s%s"
                           .formatted(Formatting.WHITE, field_4219.player.getMainHandStack().getMaxDamage() - field_4219.player.getMainHandStack().getDamage())
                     ),
                  () -> this.field_3975.getValue() && field_4219.player.getMainHandStack().isDamageable()
               ),
               new Class_0121(() -> Text.literal("Chests %s%d".formatted(Formatting.WHITE, this.field_3979)), this.field_3976::getValue)
            )
         );
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      this.field_3978.sort(Comparator.comparing(var0 -> -FontRenderer.field_3143.method_221(var0.method_15().getString())));
      this.field_3979 = 0;

      for (BlockEntity var3 : Hub.field_2622.method_347()) {
         if (var3 instanceof ChestBlockEntity || var3 instanceof BarrelBlockEntity) {
            this.field_3979++;
         }
      }

      if (this.field_3977.getValue()) {
         this.field_3979 /= 2;
      }
   }
}
