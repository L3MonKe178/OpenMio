package com.jagrosh.discordipc.entities;

public enum DiscordBuild {
   CANARY("//canary.discord.com/api"),
   PTB("//ptb.discord.com/api"),
   STABLE("//discord.com/api"),
   ANY;

   public final String endpoint;

    DiscordBuild(String var3) {
      this.endpoint = var3;
   }

    DiscordBuild() {
      this(null);
   }

   public static DiscordBuild from(int var0) {
      for (DiscordBuild var4 : values()) {
         if (var4.ordinal() == var0) {
            return var4;
         }
      }

      return ANY;
   }

   public static DiscordBuild from(String var0) {
      for (DiscordBuild var4 : values()) {
         if (var4.endpoint != null && var4.endpoint.equals(var0)) {
            return var4;
         }
      }

      return ANY;
   }
}
