package me.mioclient.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import java.awt.Color;
import java.util.Optional;
import me.mioclient.Hub;
import me.mioclient.internal.Class_0436;
import me.mioclient.internal.Class_1016;
import me.mioclient.module.client.FontsModule;
import me.mioclient.module.misc.BetterChatModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextHandler;
import net.minecraft.client.util.ChatMessages;
import net.minecraft.text.MutableText;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin({ChatMessages.class})
public class MixinChatMessages {
   private static final FontsModule fonts = Hub.field_2595.method_78(FontsModule.class);
   @Unique
   private static final BetterChatModule betterchat = Hub.field_2595.method_78(BetterChatModule.class);

   public MixinChatMessages() {
      super();
   }

   @ModifyExpressionValue(
      method = {"breakRenderedChatMessageLines"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/font/TextRenderer;getTextHandler()Lnet/minecraft/client/font/TextHandler;"
      )}
   )
   private static TextHandler mio$breakRenderedChatMessageLines(TextHandler var0) {
      Class_0436 var1 = Class_1016.field_3143.method_914();
      return (TextHandler)(var1 != null && fonts.isToggled() && fonts.field_370.getValue() ? var1.method_477() : var0);
   }

   @ModifyVariable(
      method = {"breakRenderedChatMessageLines"},
      at = @At("HEAD"),
      ordinal = 0,
      argsOnly = true
   )
   private static StringVisitable mio$breakRenderedChatMessageLines(StringVisitable var0) {
      if (betterchat.isToggled() && betterchat.field_3944.getValue()) {
         String var1 = MinecraftClient.getInstance().getSession().getUsername();
         MutableText var2 = Text.empty();
         var0.visit(
            (var2x, var3) -> {
               if (!var3.contains(var1)) {
                  var2.append(Text.literal(var3).setStyle(var2x));
               } else {
                  String[] var4 = var3.split("((?=%s)|(?<=%s))".formatted(var1, var1));

                  for (String var8 : var4) {
                     if (!var8.equals(var1)) {
                        var2.append(Text.literal(var8).setStyle(var2x));
                     } else {
                        Color var9 = betterchat.field_3945.getValue();
                        MutableText var10 = Text.literal(var8)
                           .styled(var1xx -> var1xx.withColor(new Color(var9.getRed(), var9.getGreen(), var9.getBlue(), 255).getRGB()));
                        var10.styled(var1xx -> {
                           var1xx.withBold(var2x.isBold());
                           var1xx.withItalic(var2x.isItalic());
                           var1xx.withUnderline(var2x.isUnderlined());
                           var1xx.withObfuscated(var2x.isObfuscated());
                           var1xx.withStrikethrough(var2x.isStrikethrough());
                           return var1xx;
                        });
                        var2.append(var10);
                     }
                  }
               }

               return Optional.empty();
            },
            Style.EMPTY
         );
         return var2;
      } else {
         return var0;
      }
   }
}
