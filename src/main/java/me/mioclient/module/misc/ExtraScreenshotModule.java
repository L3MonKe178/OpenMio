package me.mioclient.module.misc;

import com.google.gson.JsonObject;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Base64;
import javax.imageio.ImageIO;
import me.mioclient.Hub;
import me.mioclient.api.Class_1309;
import me.mioclient.enum_.Class_0568;
import me.mioclient.enum_.Priority;
import me.mioclient.internal.Class_0211;
import me.mioclient.internal.Class_0242;
import me.mioclient.internal.Class_1016;
import me.mioclient.internal.Class_1134;
import me.mioclient.internal.Class_1245;
import me.mioclient.internal.Class_1303;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import nick.Settings;

public class ExtraScreenshotModule extends Module {
   public static final String field_2791 = "Client-ID e7c0b6a4c926098";
   public static final HttpClient field_2792 = HttpClient.newHttpClient();
   public static final long field_2793 = 1500L;
   public Setting<Class_0568> field_2794;
   public Setting<Boolean> field_2795;
   public Setting<Boolean> field_2796;
   public Setting<Class_0211> field_2797;
   public Setting<Float> field_2798;
   public final Class_0242 field_2799;
   public int field_576;

   public ExtraScreenshotModule() {
      super("ExtraScreenshot", "Uploads your screenshots to Imgur/clipboard.", Category.MISC);
      Settings.initialize(this);
      this.field_2799 = new Class_0242();
      this.field_576 = 0;
   }

   @Override
   public String getInfo() {
      return Class_1016.method_3(this.field_2794.getValue());
   }

   @Override
   public void onEnable() {
      this.field_576 = 0;
   }

   public void method_425(byte[] var1) {
      if (this.field_2794.getValue() == Class_0568.IMGUR && !this.field_2799.method_9(1500L)) {
         this.field_576++;
         this.field_576 %= 3;
         Class_1245.method_2(
            Text.literal(String.format("Wait %dms before uploading a screenshot", 1500L - this.field_2799.method_271()))
               .styled(var0 -> var0.withColor(Formatting.YELLOW)),
            Class_1245.method_38(-34596741 + this.field_576),
            Priority.LOW
         );
      } else {
         try {
            this.method_387();
         } catch (Exception var10) {
         }

         this.field_2799.reset();
         if (this.field_2794.getValue() == Class_0568.COPY) {
            try {
               BufferedImage var12 = ImageIO.read(new ByteArrayInputStream(var1));
               BufferedImage var13 = new BufferedImage(var12.getWidth(), var12.getHeight(), 1);
               Graphics2D var14 = var13.createGraphics();
               var14.drawImage(var12, 0, 0, null);
               var14.dispose();
               Class_1134 var15 = new Class_1134(var13);
               this.method_2(var15);
               Class_1245.method_2(Text.of("Copied screenshot to clipboard"), Class_1245.method_2(this));
            } catch (Exception var9) {
               var9.printStackTrace();
               Class_1245.method_2(
                  Text.literal("Failed to copy the screenshot").styled(var0 -> var0.withColor(Formatting.RED)), Class_1245.method_2(this), Priority.MID
               );
            }
         } else if (this.field_2794.getValue() == Class_0568.IMGUR) {
            Class_1245.method_2(Text.of("Uploading screenshot..."), Class_1245.method_2(this));

            try {
               String var2 = URLEncoder.encode(Base64.getEncoder().encodeToString(var1), StandardCharsets.UTF_8);
               HttpRequest var3 = HttpRequest.newBuilder()
                  .uri(new URI("https://api.imgur.com/3/image"))
                  .headers("Content-Type", "application/x-www-form-urlencoded")
                  .headers("Authorization", "Client-ID e7c0b6a4c926098")
                  .POST(BodyPublishers.ofString(new Class_1303().method_2((Object)var2).method_9("image=\u0001")))
                  .timeout(Duration.ofSeconds(10L))
                  .build();
               HttpResponse var4 = field_2792.send(var3, BodyHandlers.ofString());
               JsonObject var5 = (JsonObject)Class_1309.field_4218.fromJson((String)var4.body(), JsonObject.class);
               if (!var5.has("success") || !var5.has("data")) {
                  throw new IOException("Invalid server response");
               }

               if (!var5.get("success").getAsBoolean()) {
                  throw new RuntimeException("Upload failed");
               }

               JsonObject var6 = var5.getAsJsonObject("data");
               if (!var6.has("link")) {
                  throw new IOException("Invalid server response");
               }

               String var7 = var6.get("link").getAsString();
               StringSelection var8 = new StringSelection(var7);
               Toolkit.getDefaultToolkit().getSystemClipboard().setContents(var8, null);
               Class_1245.method_2(Text.of("Copied screenshot link"), Class_1245.method_38(-43296436));
            } catch (Exception var11) {
               var11.printStackTrace();
               Class_1245.method_2(
                  Text.literal("Failed to upload screenshot to Imgur").styled(var0 -> var0.withColor(Formatting.RED)),
                  Class_1245.method_38(-43296435),
                  Priority.MID
               );
            }
         }
      }
   }

   public void method_2(NativeImage var1) {
      byte[] var2;
      label47: {
         try {
            var2 = var1.getBytes();
            break label47;
         } catch (Exception var7) {
            var7.printStackTrace();
            Class_1245.method_2(Text.of("Failed to save the screenshot"), Class_1245.method_2(this), Priority.MID);
         } finally {
            if (this.method_807()) {
               var1.close();
            }
         }

         return;
      }

      Class_1309.field_4221.submit(() -> {
         try {
            this.method_425(var2);
         } catch (Exception var3) {
            var3.printStackTrace();
         }
      });
   }

   public void method_2(Class_1134 var1) {
      Clipboard var2 = Toolkit.getDefaultToolkit().getSystemClipboard();
      var2.setContents(var1, null);
   }

   public void method_387() {
      if (this.field_2796.getValue()) {
         Hub.field_2606.method_2(this.field_2797.getValue()).method_230(this.field_2798.getValue());
      }
   }

   public boolean method_807() {
      return this.field_2794.getValue() != Class_0568.NONE && this.field_2795.getValue();
   }

   public boolean method_808() {
      return this.field_2794.getValue() == Class_0568.NONE;
   }
}
