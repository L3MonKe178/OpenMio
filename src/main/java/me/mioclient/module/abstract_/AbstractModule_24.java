package me.mioclient.module.abstract_;

import me.mioclient.internal.Class_0149;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.network.ClientPlayerEntity;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class AbstractModule_24 extends AbstractModule_26 {
   public static boolean field_1777 = false;

   public AbstractModule_24() {
      super("PlayerModel");
      Class_0149 var1 = new Class_0149(this);
      var1.method_2(this);
      this.method_2(var1);
   }

   @Override
   public void method_2(DrawContext var1) {
      ClientPlayerEntity var2 = field_4219.player;
      Quaternionf var3 = new Quaternionf().rotateZ(Float.intBitsToFloat(1078530011));
      Quaternionf var4 = new Quaternionf().rotateX(0.0F);
      var3.mul(var4);
      Vector3f var5 = new Vector3f(0.0F, var2.getHeight() / Float.intBitsToFloat(1073741824), 0.0F);
      field_1777 = true;
      InventoryScreen.drawEntity(
         var1, Float.intBitsToFloat(1103626240), Float.intBitsToFloat(1109393408), Float.intBitsToFloat(1108082688), var5, var3, var4, var2
      );
      field_1777 = false;
   }

   @Override
   public float[] method_31() {
      return new float[]{Float.intBitsToFloat(1112014848), Float.intBitsToFloat(1117782016)};
   }
}
