package me.mioclient.module.abstract_;

import me.mioclient.Hub;
import me.mioclient.enum_.Class_1010;
import me.mioclient.enum_.Class_1054;
import me.mioclient.enum_.PreType;
import me.mioclient.event.Event_10;
import me.mioclient.event.Event_17;
import me.mioclient.event.Event_19;
import me.mioclient.event.Event_4;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0030;
import me.mioclient.internal.Constants;
import me.mioclient.internal.Class_0382;
import me.mioclient.internal.Class_0910;
import me.mioclient.internal.PacketUtil;
import me.mioclient.module.Category;
import me.mioclient.module.movement.VelocityModule;
import me.mioclient.setting.Setting;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket.Action;
import net.minecraft.network.packet.s2c.play.CloseScreenS2CPacket;
import net.minecraft.util.math.MathHelper;
import nick.Settings;

public class AbstractModule_28 extends AbstractModule_41 {
   public static final VelocityModule velocity = Hub.field_2595.method_78(VelocityModule.class);
   public static final float field_3741 = (float)(
      Math.pow(Double.longBitsToDouble(4596373779801702400L), Double.longBitsToDouble(4613937818241073152L))
            * Double.longBitsToDouble(4620693217682128896L)
            * Double.longBitsToDouble(4594572339843380019L)
         - Double.longBitsToDouble(4562254508917369340L)
   );
   public Setting<Boolean> field_3742;
   public Setting<Float> field_3743;
   public Setting<Boolean> field_3744;
   public Setting<Class_1054> field_3745;
   public Setting<Class_1010> field_3746;
   public final Class_0910 field_3747;
   public boolean field_3748;
   public boolean field_3749;

   public AbstractModule_28() {
      super("AntiCheat", "Manages the client's behavior on different anti-cheats.", Category.CLIENT);
      Settings.initialize(this);
      this.field_3747 = new Class_0910(this);
      field_4220.method_14(this);
   }

   @Subscribe
   public void method_2(Event_17 var1) {
      this.field_3747.method_221(false);
   }

   @Subscribe
   public void method_9(Event_19 var1) {
      this.field_3749 = var1.method_213() == PreType.PRE;
      if (var1.method_213() == PreType.PRE && this.field_3744.getValue()) {
         if (this.method_1052() && field_4219.currentScreen instanceof HandledScreen && !this.field_3747.method_826()) {
            return;
         }

         if (Class_0382.method_428()) {
            return;
         }

         if (velocity.isToggled() && velocity.method_735() instanceof Class_0030 var2 && var2.method_41()) {
            return;
         }

         float var4 = field_3741 * Float.intBitsToFloat(1073741824) * (float)(this.field_3748 ? -1 : 1);
         this.field_3748 = !this.field_3748;
         var1.setPitch(MathHelper.clamp(var1.method_501() + var4, (float)(-Constants.field_685), (float)Constants.field_685));
      }
   }

   @Subscribe(
      method_800 = 999
   )
   public void method_2(Event_10 var1) {
      if (var1.method_127() instanceof PlayerActionC2SPacket var2 && this.method_1052() && var2.getAction() == Action.START_DESTROY_BLOCK) {
         PacketUtil.method_2(Action.STOP_DESTROY_BLOCK, var2.getPos(), var2.getDirection());
      }
   }

   @Subscribe
   public void method_5(Event_4 var1) {
      if (this.method_1052()) {
         if (var1.method_127() instanceof CloseScreenS2CPacket var2 && var2.getSyncId() == 0) {
            var1.method_463();
         }
      }
   }

   public float method_1051() {
      return this.field_3743.getValue();
   }

   public boolean method_1052() {
      return this.field_3742.getValue();
   }

   public Class_0910 method_1053() {
      return this.field_3747;
   }
}
