package me.mioclient.mixin;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Consumer;
import me.mioclient.enum_.Class_1202;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Overlay;
import net.minecraft.client.gui.screen.SplashOverlay;
import net.minecraft.resource.ResourceReload;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({SplashOverlay.class})
public abstract class MixinSplashOverlay extends Overlay {
   @Shadow
   private long field_17771;
   @Shadow
   @Final
   private MinecraftClient field_18217;
   @Unique
   private Class_1202 flag;

   public MixinSplashOverlay() {
      super();
   }

   @Inject(
      method = {"<init>"},
      at = {@At("TAIL")}
   )
   private void initHook(MinecraftClient var1, ResourceReload var2, Consumer<?> var3, boolean var4, CallbackInfo var5) {
      try {
         HttpRequest var6 = HttpRequest.newBuilder().uri(new URI("https://api.country.is/")).timeout(Duration.of(3L, ChronoUnit.SECONDS)).GET().build();
         HttpResponse var7 = HttpClient.newHttpClient().send(var6, BodyHandlers.ofString());
         String var8 = (String)var7.body();
         JsonObject var9 = JsonParser.parseString(var8).getAsJsonObject();
         if (var9.has("country")) {
            this.flag = Class_1202.method_35(var9.get("country").getAsString());
         }
      } catch (Throwable var10) {
         this.flag = Class_1202.DEFAULT;
      }

      Calendar var11 = Calendar.getInstance();
      var11.setTime(new Date());
      int var12 = var11.get(2);
      int var13 = var11.get(5);
      if (var12 == 1 && var13 == 23 || var12 == 4 && var13 == 9) {
         this.flag = Class_1202.field_3728;
      }
   }

   @Shadow
   private static int method_35732(int var0, int var1) {
      return 0;
   }

   @Inject(
      method = {"render"},
      at = {@At(
         value = "INVOKE",
         target = "Lcom/mojang/blaze3d/systems/RenderSystem;disableDepthTest()V",
         ordinal = 0,
         shift = Shift.BEFORE,
         remap = false
      )}
   )
   private void renderHook(DrawContext var1, int var2, int var3, float var4, CallbackInfo var5) {
      int var6 = this.field_18217.getWindow().getScaledWidth();
      int var7 = this.field_18217.getWindow().getScaledHeight();
      int var8 = MathHelper.ceil(
         (1.0F - MathHelper.clamp((this.field_17771 > -1L ? (float)(Util.getMeasuringTimeMs() - this.field_17771) / 1000.0F : -1.0F) - 1.0F, 0.0F, 1.0F))
            * 255.0F
      );

      for (int var9 = 0; var9 < this.flag.method_492().size(); var9++) {
         var1.fill(
            0,
            var9 * (var7 / this.flag.method_492().size()),
            var6,
            (var9 + 1) * (var7 / this.flag.method_492().size()),
            method_35732(this.flag.method_492().get(var9).hashCode(), var8)
         );
      }
   }
}
