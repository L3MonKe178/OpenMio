package me.mioclient.internal;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;

public class Class_0518<S> extends LiteralArgumentBuilder<S> {
   public Class_0518(String var1) {
      super(var1);
   }

   public LiteralCommandNode<S> build() {
      Class_1198 var1 = new Class_1198(
         this.getLiteral(), this.getCommand(), this.getRequirement(), this.getRedirect(), this.getRedirectModifier(), this.isFork()
      );

      for (CommandNode var3 : this.getArguments()) {
         var1.addChild(var3);
      }

      return var1;
   }
}
