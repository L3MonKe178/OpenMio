package me.mioclient.internal;

import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;

public class Class_1134 implements Transferable {
   public Image field_3513;

   public Class_1134(Image var1) {
      super();
      this.field_3513 = var1;
   }

   @Override
   public DataFlavor[] getTransferDataFlavors() {
      return new DataFlavor[]{DataFlavor.imageFlavor};
   }

   @Override
   public boolean isDataFlavorSupported(DataFlavor var1) {
      return DataFlavor.imageFlavor.equals(var1);
   }

   @Override
   public Object getTransferData(DataFlavor var1) throws java.awt.datatransfer.UnsupportedFlavorException {
      if (!DataFlavor.imageFlavor.equals(var1)) {
         throw new UnsupportedFlavorException(var1);
      } else {
         return this.field_3513;
      }
   }
}
