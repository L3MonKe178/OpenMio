package me.mioclient.internal;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import java.util.ArrayList;
import java.util.List;
import me.mioclient.Hub;
import me.mioclient.event.Event_21;
import me.mioclient.event.Event_32;
import me.mioclient.event.Subscribe;
import net.minecraft.command.CommandSource;

public class Class_0283 extends Class_0618 {
   public final List<String> field_884 = new ArrayList<>();
   public boolean field_885;
   public int field_31;

   public Class_0283() {
      super("wait");
      field_4220.method_14(this);
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      var1.then(RequiredArgumentBuilder.<CommandSource, Integer>argument("ticks", IntegerArgumentType.integer(0)).executes(var1x -> {
         this.field_31 = (Integer)var1x.getArgument("ticks", Integer.class);
         this.field_885 = true;
         return 1;
      }));
   }

   @Subscribe
   public void method_2(Event_32 var1) {
      if (this.field_885) {
         this.field_884.add(var1.method_207());
         var1.method_463();
      }
   }

   @Subscribe
   public void method_2(Event_21 var1) {
      if (this.field_885) {
         this.field_885 = false;
         ArrayList<String> var2 = new ArrayList<>(this.field_884);
         this.field_884.clear();
         Hub.field_2619.method_2(() -> {
            for (String var2x : var2) {
               Class_1032.method_7(var2x);
            }
         }, this.field_31);
      }
   }
}
