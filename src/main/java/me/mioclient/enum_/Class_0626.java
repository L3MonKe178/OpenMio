package me.mioclient.enum_;

import me.mioclient.api.Nameable;
import me.mioclient.api.MioAPI;
import me.mioclient.module.player.NukerModule;
import net.minecraft.block.BedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.block.enums.BedPart;
import net.minecraft.util.math.BlockPos;

public enum Class_0626 implements Nameable {
   SHULKERS("Shulkers") {
      @Override
      public boolean method_2(BlockPos var1, Block var2, NukerModule var3) {
         return var2 instanceof ShulkerBoxBlock;
      }
   },
   BEDS("Bed") {
      @Override
      public boolean method_2(BlockPos var1, Block var2, NukerModule var3) {
         return var2 instanceof BedBlock && MioAPI.field_4219.world.getBlockState(var1).get(BedBlock.PART) == BedPart.HEAD;
      }
   },
   WHITELIST("WhiteList") {
      @Override
      public boolean method_2(BlockPos var1, Block var2, NukerModule var3) {
         return var3.field_3160.getValue().contains(var2);
      }
   },
   BLACKLIST("BlackList") {
      @Override
      public boolean method_2(BlockPos var1, Block var2, NukerModule var3) {
         return !WHITELIST.method_2(var1, var2, var3);
      }
   };

   public final String field_1997;

    Class_0626(String var3) {
      this.field_1997 = var3;
   }

   @Override
   public String getName() {
      return this.field_1997;
   }

   public abstract boolean method_2(BlockPos var1, Block var2, NukerModule var3);
}
