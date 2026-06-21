package me.mioclient.internal;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import me.mioclient.api.MioAPI;
import net.minecraft.entity.ItemEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class Class_1288 implements MioAPI {
   public final Map<String, Integer> field_4169 = new TreeMap<>(Comparator.<String, String>comparing(var0 -> (String)var0).reversed());
   public final Box field_4170;
   public Box field_4171;
   public float field_4172;

   public Class_1288(ItemEntity var1) {
      super();
      float var2 = field_4219.getRenderTickCounter().getTickDelta(true);
      this.field_4170 = Box.of(
         var1.getLerpedPos(var2),
         Double.longBitsToDouble(4617315517961601024L),
         Double.longBitsToDouble(4617315517961601024L),
         Double.longBitsToDouble(4617315517961601024L)
      );
      this.field_4171 = Class_0719.method_2(var1, var2);
      this.method_2(var1);
   }

   public Box method_370() {
      return this.field_4170;
   }

   public Box method_1151() {
      return this.field_4171;
   }

   public boolean method_2(ItemEntity var1) {
      if (this.field_4170.intersects(var1.getBoundingBox())) {
         float var2 = field_4219.getRenderTickCounter().getTickDelta(true);
         String var3 = var1.getName().getString();
         this.field_4169.compute(var3, (var1x, var2x) -> var1.getStack().getCount() + (var2x == null ? 0 : var2x));
         this.field_4172 = Math.max(FontRenderer.field_3143.method_221(method_5(var3, this.field_4169.get(var3))), this.field_4172);
         Vec3d var4 = new Vec3d(var1.getX() - var1.prevX, var1.getY() - var1.prevY, var1.getZ() - var1.prevZ);
         if (var4.lengthSquared() < Double.longBitsToDouble(4547007122018943789L)) {
            this.field_4171 = this.field_4171.union(Class_0719.method_2(var1, var2));
         }

         return true;
      } else {
         return false;
      }
   }

   public Map<String, Integer> method_1152() {
      return this.field_4169;
   }

   public float method_1153() {
      return this.field_4172;
   }

   public static String method_5(String var0, int var1) {
      return var1 <= 1 ? var0 : new TextBuilder().method_2(var1).method_2((Object)var0).method_9("\u0001 x\u0001");
   }
}
