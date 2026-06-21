package me.mioclient.module.abstract_;

import com.google.gson.JsonObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import me.mioclient.api.MioAPI;
import me.mioclient.enum_.Class_0698;
import me.mioclient.event.Event_1;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0121;
import me.mioclient.internal.Timer;
import me.mioclient.internal.Class_0723;
import me.mioclient.setting.CustomSetting;
import me.mioclient.setting.Setting;
import me.mioclient.setting.StringSetting;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class AbstractModule_3 extends AbstractModule_26 {
   public static final String field_3432 = "MioClient/2.0";
   public final Setting<String> field_3433 = this.add(new StringSetting("Coin", "BTC"));
   public final Setting<Class_0698> field_3434 = this.add(new CustomSetting<>("Fiat", Class_0698.USD));
   public final HttpClient field_3435 = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10L)).build();
   public String field_3436 = "0.00";
   public Formatting field_3437 = Formatting.WHITE;
   public final Timer field_3438 = new Timer();

   public AbstractModule_3() {
      super("Crypto");
      this.method_2(
         new Class_0723(
            this,
            new Class_0121(() -> Text.literal("%s %s%s".formatted((this.field_3433.getValue() != null ? this.field_3433.getValue().toUpperCase() : ""), this.field_3437, this.field_3436)), () -> true)
         )
      );
      this.field_3433.method_9(() -> {
         this.field_3437 = Formatting.WHITE;
         this.field_3436 = "0.00";
         if (!this.method_982()) {
            this.field_3433.method_78("UNKNOWN");
         } else {
            this.field_3438.setTime(5000L);
         }
      });
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if (this.field_3438.method_2(Double.longBitsToDouble(4617315517961601024L), TimeUnit.SECONDS)) {
         field_4221.submit(this::method_981);
         this.field_3438.reset();
      }
   }

   public void method_981() {
      if (!this.method_982()) {
         this.field_3433.method_78("UNKNOWN");
         this.field_3436 = "0.00";
         this.field_3437 = Formatting.WHITE;
      } else {
         String var1 = this.field_3433.getValue().toUpperCase(Locale.ROOT);
         String var2 = (this.field_3434.getValue() != null ? this.field_3434.getValue().getName() : "").toUpperCase(Locale.ROOT);

         try {
            URI var3 = new URI("https://api.coinconvert.net/convert/%s/%s?amount=1".formatted(var1, var2));
            HttpRequest var4 = HttpRequest.newBuilder().GET().uri(var3).header("Accept", "application/json").header("User-Agent", "MioClient/2.0").build();
            HttpResponse var5 = this.field_3435.send(var4, BodyHandlers.ofString());
            if (var5.statusCode() != 200) {
               this.field_3436 = "UNKNOWN";
               this.field_3437 = Formatting.WHITE;
               return;
            }

            JsonObject var6 = (JsonObject)MioAPI.field_4218.fromJson((String)var5.body(), JsonObject.class);
            if (var6.has("status") && var6.has(var2)) {
               float var7 = var6.get(var2).getAsFloat();
               String var8 = "%.2f".formatted(var7);
               float var9 = Float.parseFloat(var8);
               if (!this.field_3436.equalsIgnoreCase("0.00") && !this.field_3436.equalsIgnoreCase("UNKNOWN")) {
                  this.method_99(var9);
               }

               this.field_3436 = var8;
            }
         } catch (Exception var10) {
         }
      }
   }

   public void method_99(float var1) {
      float var2 = Float.parseFloat(this.field_3436);
      if ((double)Math.abs(var1 - var2) < Double.longBitsToDouble(4576918229304087675L)) {
         this.field_3437 = Formatting.WHITE;
      } else {
         if (var2 > var1) {
            this.field_3437 = Formatting.RED;
         } else if (var2 < var1) {
            this.field_3437 = Formatting.GREEN;
         }
      }
   }

   public boolean method_982() {
      String var1 = this.field_3433.getValue();
      return !var1.isEmpty() && var1.matches("[a-zA-Z]+");
   }
}
