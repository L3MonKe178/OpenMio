package me.mioclient.internal;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.concurrent.CompletableFuture;
import me.mioclient.Hub;
import me.mioclient.mixin.ducks.DuckTextureManager;
import net.minecraft.client.texture.ResourceTexture;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

public class Class_0348 extends Command {
   public static final byte[] field_1147 = new byte[]{-119, 80, 78, 71, 13, 10, 26, 10};
   public CompletableFuture<Void> field_1145 = CompletableFuture.completedFuture(null);

   public Class_0348() {
      super("reload");
      this.method_9("rl");
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      var1.executes(
         var1x -> {
            if (!this.field_1145.isDone()) {
               return 0;
            } else {
               ChatUtil.method_2(Text.literal("Reloading Mio resources..."), ChatUtil.method_22(this));
               this.field_1145 = CompletableFuture.runAsync(
                  () -> {
                     HashSet<Identifier> var0 = new HashSet<>();

                     for (Identifier var2 : ((DuckTextureManager)field_4219.getTextureManager()).getAllTextures().keySet()) {
                        if (var2.getNamespace().equals("mio-mount")) {
                           var0.add(var2);
                        }
                     }

                     Hub.field_2597.method_1173();
                     var0.forEach(
                        var0x -> {
                           Path var1xx = Class_1328.field_4281.resolve(var0x.getPath());

                           try {
                              byte[] var2x = Files.readAllBytes(var1xx);
                              if (var2x.length < 8) {
                                 throw new RuntimeException("File size is too small to be png");
                              }

                              for (int var3 = 0; var3 < 8; var3++) {
                                 if (var2x[var3] != field_1147[var3]) {
                                    throw new RuntimeException("File is not a png");
                                 }
                              }
                           } catch (RuntimeException | IOException var4) {
                              ChatUtil.method_2(
                                 Text.literal(new TextBuilder().method_2(var4.getMessage()).method_9("Invalid Image! \u0001"))
                                    .styled(var0xx -> var0xx.withFormatting(Formatting.RED)),
                                 ChatUtil.method_38(-999)
                              );
                           }

                           field_4219.getTextureManager().destroyTexture(var0x);
                           field_4219.getTextureManager().registerTexture(var0x, new ResourceTexture(var0x));
                        }
                     );
                     Hub.field_2606.method_534();
                  },
                  field_4221
               );
               this.field_1145
                  .whenComplete(
                     (var1xx, var2) -> ChatUtil.method_2(
                           Text.literal("Reload complete!").styled(var0 -> var0.withFormatting(Formatting.GREEN)), ChatUtil.method_22(this)
                        )
                  );
               return 1;
            }
         }
      );
   }
}
