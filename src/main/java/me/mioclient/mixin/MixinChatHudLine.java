package me.mioclient.mixin;

import me.mioclient.api.Class_0333;
import me.mioclient.internal.Class_0585;
import me.mioclient.module.misc.BetterChatModule;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.client.gui.hud.ChatHudLine.Visible;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.text.OrderedText;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({Visible.class})
public class MixinChatHudLine implements Class_0333 {
   @Unique
   private Class_0585 progress = null;
   @Unique
   private MessageSignatureData signature;
   @Mutable
   @Final
   @Shadow
   private OrderedText comp_896;
   @Unique
   private long mio$addTime;

   public MixinChatHudLine() {
      super();
   }

   @Inject(
      method = {"<init>"},
      at = {@At("RETURN")}
   )
   private void onInit(int var1, OrderedText var2, MessageIndicator var3, boolean var4, CallbackInfo var5) {
      this.getProgress().method_36(false);
      this.mio$addTime = System.currentTimeMillis();
   }

   @Override
   public MessageSignatureData getSignature() {
      return this.signature;
   }

   @Override
   public void setSignature(MessageSignatureData var1) {
      this.signature = var1;
   }

   @Override
   public OrderedText getContent() {
      return this.comp_896;
   }

   @Override
   public void setContent(OrderedText var1) {
      this.comp_896 = var1;
   }

   @Override
   public Class_0585 getProgress() {
      if (this.progress == null) {
         this.progress = new Class_0585(() -> BetterChatModule.method_1093().field_3939.getValue() * 2.0F, true);
      }

      return this.progress;
   }

   @Override
   public long mio$getAddTime() {
      return this.mio$addTime;
   }

   @Override
   public void mio$setAddTime(long var1) {
      this.mio$addTime = var1;
   }
}
