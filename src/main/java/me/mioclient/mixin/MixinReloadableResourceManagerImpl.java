package me.mioclient.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import java.io.FileInputStream;
import java.util.Optional;
import me.mioclient.api.MioAPI;
import me.mioclient.internal.Class_0550;
import me.mioclient.internal.Class_0859;
import me.mioclient.internal.Class_1328;
import net.minecraft.resource.ReloadableResourceManagerImpl;
import net.minecraft.resource.Resource;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({ReloadableResourceManagerImpl.class})
public class MixinReloadableResourceManagerImpl {
   public MixinReloadableResourceManagerImpl() {
      super();
   }

   @ModifyReturnValue(
      method = {"getResource"},
      at = {@At("RETURN")}
   )
   private Optional<Resource> getResource(Optional<Resource> var1, @Local(argsOnly = true) Identifier var2) {
      if (var2.getNamespace().equals("mio-mount")) {
         return Optional.of(new Resource(new Class_0859(), () -> new FileInputStream(Class_1328.field_4281.resolve(var2.getPath()).toFile())));
      } else if (var1.isEmpty() && (var2.getNamespace().equals("mio") || var2.getPath().contains("/blur_mask.")) && MioAPI.method_244()) {
         String var3 = var2.getPath().contains("blur_mask") ? "minecraft" : "mio";
         return Optional.of(
            new Resource(new Class_0859(), () -> Class_0550.class.getClassLoader().getResourceAsStream("assets/%s/%s".formatted(var3, var2.getPath())))
         );
      } else {
         return var1;
      }
   }
}
