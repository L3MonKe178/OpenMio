package me.mioclient.mixin.brigadier;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({BoolArgumentType.class})
public class MixinBoolArgumentType {
   public MixinBoolArgumentType() {
      super();
   }

   @Inject(
      method = {"parse(Lcom/mojang/brigadier/StringReader;)Ljava/lang/Boolean;"},
      at = {@At("HEAD")},
      remap = false,
      cancellable = true
   )
   private void parseHook(StringReader var1, CallbackInfoReturnable<Boolean> var2) throws com.mojang.brigadier.exceptions.CommandSyntaxException {
      String var3 = var1.readString();
      if (!var3.equals("0") && !var3.equalsIgnoreCase("false")) {
         if (!var3.equals("1") && !var3.equalsIgnoreCase("true")) {
            throw CommandSyntaxException.BUILT_IN_EXCEPTIONS.readerInvalidBool().createWithContext(var1, var3);
         }

         var2.setReturnValue(true);
      } else {
         var2.setReturnValue(false);
      }
   }
}
