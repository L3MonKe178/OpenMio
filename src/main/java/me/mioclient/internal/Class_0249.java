package me.mioclient.internal;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import me.mioclient.Hub;
import me.mioclient.module.player.RotationLockModule;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

public class Class_0249 extends Command {
   public static final RotationLockModule field_711 = Hub.field_2595.method_78(RotationLockModule.class);

   public Class_0249() {
      super("angle");
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      var1.then(
         RequiredArgumentBuilder.<CommandSource, Vec3d>argument("pos", new Class_0019()).then(LiteralArgumentBuilder.<CommandSource>literal("yawlock").executes(var0 -> {
            float[] var1x = RotationManager.method_78((Vec3d)var0.getArgument("pos", Vec3d.class));
            field_711.field_1477.method_78(var1x[0]);
            return 1;
         })).executes(var0 -> {
            float[] var1x = RotationManager.method_78((Vec3d)var0.getArgument("pos", Vec3d.class));
            ChatUtil.method_2(Text.literal("Calculated angle. Yaw: %.1f, Pitch: %.1f.".formatted(var1x[0], var1x[1])), ChatUtil.method_38(-1));
            return 1;
         })
      );
   }
}
