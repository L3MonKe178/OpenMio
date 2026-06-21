package baritone.api.process;
import net.minecraft.util.math.BlockPos;
public interface IElytraProcess extends IBaritoneProcess {
   void pathTo(BlockPos pos);
}
