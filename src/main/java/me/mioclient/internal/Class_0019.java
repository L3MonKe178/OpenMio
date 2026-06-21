package me.mioclient.internal;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.CommandSource;
import net.minecraft.command.CommandSource.RelativePosition;
import net.minecraft.command.argument.CoordinateArgument;
import net.minecraft.command.argument.Vec3ArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.util.math.Vec3d;

public class Class_0019 implements ArgumentType<Vec3d> {
   public static final Collection<String> field_29 = Arrays.asList("0 0 0", "~ ~ ~", "^ ^ ^", "^1 ^ ^-5", "0.1 -0.5 .9", "~0.5 ~1 ~-5");

   public Class_0019() {
      super();
   }

   public Vec3d parse(StringReader var1) throws com.mojang.brigadier.exceptions.CommandSyntaxException {
      int var2 = var1.getCursor();
      CoordinateArgument var3 = CoordinateArgument.parse(var1);
      if (var1.canRead() && var1.peek() == ' ') {
         var1.skip();
         CoordinateArgument var4 = CoordinateArgument.parse(var1, false);
         if (var1.canRead() && var1.peek() == ' ') {
            var1.skip();
            Vec3d var5 = MinecraftClient.getInstance().player.getPos();
            CoordinateArgument var6 = CoordinateArgument.parse(var1);
            return new Vec3d(var3.toAbsoluteCoordinate(var5.x), var4.toAbsoluteCoordinate(var5.y), var6.toAbsoluteCoordinate(var5.z));
         } else {
            var1.setCursor(var2);
            throw Vec3ArgumentType.INCOMPLETE_EXCEPTION.createWithContext(var1);
         }
      } else {
         var1.setCursor(var2);
         throw Vec3ArgumentType.INCOMPLETE_EXCEPTION.createWithContext(var1);
      }
   }

   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> var1, SuggestionsBuilder var2) {
      if (!(var1.getSource() instanceof CommandSource)) {
         return Suggestions.empty();
      } else {
         String var3 = var2.getRemaining();
         Object var4;
         if (!var3.isEmpty() && var3.charAt(0) == '^') {
            var4 = Collections.singleton(RelativePosition.ZERO_LOCAL);
         } else {
            var4 = ((CommandSource)var1.getSource()).getPositionSuggestions();
         }

         return CommandSource.suggestPositions(var3, (Collection)var4, var2, CommandManager.getCommandValidator(this::parse));
      }
   }

   public Collection<String> getExamples() {
      return field_29;
   }
}
