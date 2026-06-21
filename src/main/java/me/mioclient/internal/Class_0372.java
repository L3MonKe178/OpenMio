package me.mioclient.internal;

import me.mioclient.record.Class_0012;
import net.minecraft.client.gui.screen.ConfirmScreen;
import net.minecraft.text.Text;

public class Class_0372 extends ConfirmScreen {
   public Class_0372(Runnable var1) {
      super(new Class_0012(var1), Text.literal("Confirm Disconnect"), Text.literal("Are you sure that you want to disconnect?"));
   }
}
