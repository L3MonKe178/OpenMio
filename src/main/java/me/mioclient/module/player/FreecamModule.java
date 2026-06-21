package me.mioclient.module.player;

import me.mioclient.Hub;
import me.mioclient.enum_.PreType;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_16;
import me.mioclient.event.Event_19;
import me.mioclient.event.Event_31;
import me.mioclient.event.Event_36;
import me.mioclient.event.Event_4;
import me.mioclient.event.Event_52;
import me.mioclient.event.Event_56;
import me.mioclient.event.Event_57;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Constants;
import me.mioclient.internal.Class_0411;
import me.mioclient.internal.Class_0464;
import me.mioclient.internal.RotationManager;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.client.gui.screen.DownloadingTerrainScreen;
import net.minecraft.client.option.Perspective;
import net.minecraft.item.BucketItem;
import net.minecraft.network.packet.s2c.common.DisconnectS2CPacket;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import nick.Settings;

public class FreecamModule extends Module {
   public final Class_0411 field_2822 = new Class_0411();
   public Setting<Float> field_2823;
   public Setting<Float> field_2824;
   public Setting<Boolean> field_2825;
   public Setting<Boolean> field_2826;
   public Setting<Boolean> field_2827;
   public Vec3d field_806;
   public Vec3d field_2828;
   public float[] field_2829;
   public Perspective field_1352;
   public float field_1760;
   public float field_1542;
   public float field_1761;
   public float field_1543;

   public FreecamModule() {
      super("Freecam", "Allows your camera to fly through walls.", Category.PLAYER);
      Settings.initialize(this);
      this.field_2829 = null;
      this.field_2823.method_214();
      this.field_2824.method_214();
   }

   @Override
   public void onEnable() {
      if (this.method_535()) {
         this.disable();
      } else {
         this.field_1352 = field_4219.options.getPerspective();
         new Event_16(field_4219.player.input, 0.0F).reset();
         this.field_2828 = this.field_806 = field_4219.gameRenderer.getCamera().getPos();
         this.field_2829 = new float[]{field_4219.player.getYaw(), field_4219.player.getPitch()};
         this.field_1542 = this.field_1760 = this.field_2829[0];
         this.field_1543 = this.field_1761 = this.field_2829[1];
      }
   }

   @Override
   public void onDisable() {
      if (!this.method_535() && this.field_2829 != null && field_4219.player != null) {
         field_4219.player.setYaw(this.field_2829[0]);
         field_4219.player.setPitch(this.field_2829[1]);
         field_4219.options.setPerspective(this.field_1352);
      }
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if (field_4219.currentScreen instanceof DownloadingTerrainScreen) {
         this.disable();
      }

      field_4219.options.setPerspective(Perspective.FIRST_PERSON);
      this.field_2828 = this.field_806;
      double[] var2 = Class_0464.method_2(this.field_1760, this.field_2822, (double)(this.field_2823.getValue() != null ? this.field_2823.getValue().floatValue() : 0.0f));
      this.field_806 = this.field_806.add(var2[0], 0.0, var2[1]);
      if (field_4219.options.jumpKey.isPressed()) {
         this.field_806 = this.field_806.add(0.0, (double)(this.field_2824.getValue() != null ? this.field_2824.getValue().floatValue() : 0.0f), 0.0);
      }

      if (field_4219.options.sneakKey.isPressed()) {
         this.field_806 = this.field_806.subtract(0.0, (double)(this.field_2824.getValue() != null ? this.field_2824.getValue().floatValue() : 0.0f), 0.0);
      }

      this.field_2822.tick(false, Float.intBitsToFloat(1065353216));
   }

   @Subscribe
   public void method_2(Event_56 var1) {
      var1.method_463();
   }

   @Subscribe(
      method_800 = 1000
   )
   public void method_9(Event_19 var1) {
      boolean var2 = field_4219.interactionManager.isBreakingBlock();
      if (var1.method_213() != PreType.POST
         && this.field_2826.getValue()
         && field_4219.crosshairTarget != null
         && (!RotationManager.method_513() || var2 || this.field_2827.getValue())) {
         var1.method_5(RotationManager.method_78(field_4219.crosshairTarget.getPos()));
      }
   }

   @Subscribe
   public void method_5(Event_36 var1) {
      boolean var2 = field_4219.player.getStackInHand(var1.method_12()).getItem() instanceof BucketItem;
      if (var2 || this.field_2826.getValue() && RotationManager.method_513()) {
         float[] var3 = RotationManager.method_78(var1.method_382().getPos());
         Hub.field_2598.method_2(var3, 1000);
      }
   }

   @Subscribe
   public void method_2(Event_52 var1) {
      if (this.field_2826.getValue() && field_4219.crosshairTarget != null && !this.field_2827.getValue()) {
         var1.method_9(RotationManager.method_78(field_4219.crosshairTarget.getPos()));
      }
   }

   @Subscribe
   public void method_5(Event_4 var1) {
      if (var1.method_127() instanceof DisconnectS2CPacket) {
         this.disable();
      }
   }

   @Subscribe
   public void method_2(Event_57 var1) {
      this.method_29(var1.method_712() * Double.longBitsToDouble(4594572340058128384L), var1.method_713() * Double.longBitsToDouble(4594572340058128384L));
      var1.method_463();
   }

   @Subscribe
   public void method_2(Event_31 var1) {
      float var2 = field_4219.getRenderTickCounter().getTickDelta(true);
      var1.method_38(new Vec3d(this.method_460(var2), this.method_220(var2), this.method_6(var2)));
      var1.method_463();
   }

   public void method_29(double var1, double var3) {
      this.field_1542 = this.field_1760;
      this.field_1543 = this.field_1761;
      this.field_1760 = (float)((double)this.field_1760 + var1);
      this.field_1761 = (float)((double)this.field_1761 + var3);
      this.field_1761 = MathHelper.clamp(this.field_1761, (float)(-Constants.field_685), (float)Constants.field_685);
   }

   public double method_460(float var1) {
      return MathHelper.lerp((double)var1, this.field_2828.x, this.field_806.x);
   }

   public double method_220(float var1) {
      return MathHelper.lerp((double)var1, this.field_2828.y, this.field_806.y);
   }

   public double method_6(float var1) {
      return MathHelper.lerp((double)var1, this.field_2828.z, this.field_806.z);
   }

   public double method_8(float var1) {
      return field_4219.currentScreen != null ? (double)this.field_1760 : (double)MathHelper.lerp(var1, this.field_1542, this.field_1760);
   }

   public double method_334(float var1) {
      return field_4219.currentScreen != null ? (double)this.field_1761 : (double)MathHelper.lerp(var1, this.field_1543, this.field_1761);
   }

   public boolean method_279() {
      return this.isToggled() && this.field_2825.getValue();
   }
}
