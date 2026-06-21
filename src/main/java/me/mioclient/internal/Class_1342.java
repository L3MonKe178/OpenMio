package me.mioclient.internal;

import net.caffeinemc.mods.sodium.api.vertex.buffer.VertexBufferWriter;
import net.caffeinemc.mods.sodium.api.vertex.format.VertexFormatDescription;
import org.lwjgl.system.MemoryStack;

public class Class_1342 extends Class_0809 implements VertexBufferWriter {
   public final boolean field_4334 = this.field_2558 instanceof VertexBufferWriter;

   public Class_1342(int var1) {
      super(var1);
   }

   public void push(MemoryStack var1, long var2, int var4, VertexFormatDescription var5) {
      if (this.field_4334) {
         ((VertexBufferWriter)this.field_2558).push(var1, var2, var4, var5);
      }
   }

   public boolean canUseIntrinsics() {
      return this.field_4334;
   }
}
