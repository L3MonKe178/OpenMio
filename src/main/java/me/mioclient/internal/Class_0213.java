package me.mioclient.internal;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import java.awt.Color;
import me.mioclient.Hub;
import me.mioclient.record.Class_0828;
import net.minecraft.command.CommandSource;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class Class_0213 extends Command {
   public static final Color field_596 = new Color(255, 0, 0, 120);
   public static final Color field_597 = new Color(0, 0, 0, 0);

   public Class_0213() {
      super("highlight");
      Hub.field_2616
         .method_2(
            new Class_0828(
               this,
               () -> field_596,
               () -> field_597,
               () -> Float.intBitsToFloat(1065353216),
               () -> Float.intBitsToFloat(1084227584),
               () -> true,
               () -> true,
               1000
            )
         );
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      var1.then(
         RequiredArgumentBuilder.<CommandSource, Vec3d>argument("pos", new Class_0019())
               .then(
                  LiteralArgumentBuilder.<CommandSource>literal("beam")
                     .executes(
                        var1x -> {
                           Vec3d var2 = (Vec3d)var1x.getArgument("pos", Vec3d.class);
                           Box var3 = Box.of(
                              var2,
                              Double.longBitsToDouble(4598175219545276416L),
                              Double.longBitsToDouble(4598175219545276416L),
                              Double.longBitsToDouble(4598175219545276416L)
                           );
                           var3 = var3.withMaxY((double)field_4219.world.getTopY()).withMinY((double)field_4219.world.getBottomY());
                           Hub.field_2616.method_2(this, var3);
                           return 1;
                        }
                     )
               )
            .executes(
               var1x -> {
                  Vec3d var2 = (Vec3d)var1x.getArgument("pos", Vec3d.class);
                  Box var3 = Box.of(
                     var2,
                     Double.longBitsToDouble(4598175219545276416L),
                     Double.longBitsToDouble(4598175219545276416L),
                     Double.longBitsToDouble(4598175219545276416L)
                  );
                  Hub.field_2616.method_2(this, var3);
                  return 1;
               }
            )
      );
   }
}
