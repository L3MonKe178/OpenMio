package me.mioclient.internal;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.mioclient.enum_.Class_0060;
import me.mioclient.event.Event_1;
import me.mioclient.event.Subscribe;
import me.mioclient.mixin.ducks.DuckMinecraftClient;
import me.mioclient.record.Class_0600;
import me.mioclient.record.Class_0635;
import net.minecraft.command.CommandSource;
import net.minecraft.item.AnimalArmorItem;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;

public class Class_0985 extends Command {
   public final Map<Class_0060, List<Class_0600>> field_3031 = new HashMap<>();
   public boolean field_943;

   public Class_0985() {
      super("action");
      field_4220.method_14(this);

      for (Class_0060 var4 : Class_0060.values()) {
         this.field_3031.put(var4, new ArrayList<>());
      }
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      var1.then(
         RequiredArgumentBuilder.<CommandSource, Class_0060>argument("swap", new Class_0699<>(Class_0060.class))
               .then(RequiredArgumentBuilder.<CommandSource, Item>argument("item", Class_0624.registry(Registries.ITEM, this::method_2)).executes(var1x -> {
                  Item var2 = (Item)var1x.getArgument("item", Item.class);
                  Class_0060 var3 = (Class_0060)var1x.getArgument("swap", Class_0060.class);
                  Class_0635 var4 = var3.method_87().method_31(var2);
                  if (var4.method_644()) {
                     this.field_943 = true;
                  }

                  Class_0600 var5 = new Class_0600(var3.method_87(), var4);
                  this.field_3031.get(var3).add(var5);
                  if (!var4.method_644()) {
                     var3.method_87().method_2(var4);
                  }

                  return 1;
               })))
            .then(LiteralArgumentBuilder.<CommandSource>literal("back").executes(var1x -> {
               Class_0060 var2 = (Class_0060)var1x.getArgument("swap", Class_0060.class);
               List var3 = this.field_3031.get(var2);
               if (var3.isEmpty()) {
                  return 1;
               } else {
                  Class_0600 var4 = (Class_0600)var3.removeLast();
                  if (var4.method_612().method_644()) {
                     return 1;
                  } else {
                     var4.method_611().method_9(var4.field_1869);
                     return 1;
                  }
               }
            })
      );
      var1.then(LiteralArgumentBuilder.<CommandSource>literal("attack").executes(var1x -> {
         if (!this.field_943) {
            ((DuckMinecraftClient)field_4219).attack();
         }

         return 1;
      }));
      var1.then(LiteralArgumentBuilder.<CommandSource>literal("interact").executes(var1x -> {
         if (!this.field_943) {
            ((DuckMinecraftClient)field_4219).interact();
         }

         return 1;
      }));
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      this.field_943 = false;
   }

   public boolean method_2(CommandContext<?> var1, Item var2) {
      Class_0060 var3 = (Class_0060)var1.getArgument("swap", Class_0060.class);
      return var3.method_78(var2);
   }

   public static boolean method_38(Item var0) {
      return var0 instanceof AnimalArmorItem ? false : var0 instanceof ArmorItem || var0 == Items.ELYTRA;
   }
}
