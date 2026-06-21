package me.mioclient.event;

import me.mioclient.enum_.PreType;
import me.mioclient.internal.Class_0605;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.client.gui.hud.ChatHudLine.Visible;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.text.Text;

public class Event_46 extends Class_0605 {
   public final PreType field_3643;
   public final MessageIndicator field_3644;
   public MessageSignatureData signature;
   public Text field_3645;
   public Visible field_3646;
   public String field_3647;

   public Event_46(MessageIndicator var1, MessageSignatureData var2, Text var3) {
      super();
      this.field_3644 = var1;
      this.signature = var2;
      this.field_3645 = var3;
      this.field_3643 = PreType.PRE;
      this.field_3647 = var3 == null ? null : var3.getString();
   }

   public Event_46(Visible var1) {
      super();
      this.field_3646 = var1;
      this.field_3643 = PreType.POST;
      this.field_3644 = null;
   }

   public MessageSignatureData getSignature() {
      return this.signature;
   }

   public void setSignature(MessageSignatureData var1) {
      this.signature = var1;
   }

   public Text method_1033() {
      return this.field_3645;
   }

   public void method_9(Text var1) {
      this.field_3645 = var1;
   }

   public Visible method_1034() {
      return this.field_3646;
   }

   public void method_9(Visible var1) {
      this.field_3646 = var1;
   }

   public PreType method_213() {
      return this.field_3643;
   }

   public String method_1035() {
      return this.field_3647;
   }

   public MessageIndicator method_1036() {
      return this.field_3644;
   }
}
