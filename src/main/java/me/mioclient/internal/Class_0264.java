package me.mioclient.internal;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.List;
import net.minecraft.command.CommandSource;
import net.minecraft.command.EntityDataObject;
import net.minecraft.command.argument.NbtPathArgumentType.NbtPath;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

public class Class_0264 extends Class_0618 {
   public Class_0264() {
      super("nbt");
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      var1.executes(var0 -> {
         EntityDataObject var1x = new EntityDataObject(field_4219.player);
         NbtPath var2 = NbtPath.parse("SelectedItem");
         MutableText var3 = Text.empty();
         var3 = var3.append("Nbt: ");

         try {
            List var4 = var2.get(var1x.getNbt());
            if (!var4.isEmpty()) {
               var3.append(" ").append(NbtHelper.toPrettyPrintedText((NbtElement)var4.getFirst()));
            }
         } catch (CommandSyntaxException var5) {
            var3.append("{}");
         }

         Class_1245.method_2(var3, Class_1245.method_38(-1));
         return 1;
      });
   }
}
