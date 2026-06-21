package me.mioclient.api;

import me.mioclient.internal.Class_0585;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.text.OrderedText;

public interface Class_0333 {
   MessageSignatureData getSignature();

   void setSignature(MessageSignatureData var1);

   OrderedText getContent();

   void setContent(OrderedText var1);

   Class_0585 getProgress();

   long mio$getAddTime();

   void mio$setAddTime(long var1);
}
