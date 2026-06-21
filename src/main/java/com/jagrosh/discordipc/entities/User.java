package com.jagrosh.discordipc.entities;

import com.jagrosh.discordipc.impl.ExtendedLong;

public class User {
   public final String username;
   public final String nickname;
   public final String discriminator;
   public final long id;
   public final String avatar;

   public User(String var1, String var2, String var3, long var4, String var6) {
      super();
      this.username = var1;
      this.nickname = var2;
      this.discriminator = var3;
      this.id = var4;
      this.avatar = var6;
   }

   public String getName() {
      return this.username;
   }

   public String getNickname() {
      return this.nickname;
   }

   public String getEffectiveName() {
      return this.nickname == null ? this.username : this.nickname;
   }

   public String getDiscriminator() {
      return this.discriminator;
   }

   public long getIdLong() {
      return this.id;
   }

   public String getId() {
      return Long.toString(this.id);
   }

   public String getAvatarId() {
      return this.avatar;
   }

   public String getAvatarUrl() {
      return this.getAvatarId() == null
         ? null
         : "https://cdn.discordapp.com/avatars/" + this.getId() + "/" + this.getAvatarId() + (this.getAvatarId().startsWith("a_") ? ".gif" : ".png");
   }

   public String getDefaultAvatarId() {
      return User.DefaultAvatar.values()[(this.getDiscriminator().equals("0") ? (int)this.getIdLong() >> 22 : Integer.parseInt(this.getDiscriminator()))
            % User.DefaultAvatar.values().length]
         .toString();
   }

   public String getDefaultAvatarUrl() {
      return "https://discord.com/assets/" + this.getDefaultAvatarId() + ".png";
   }

   public String getEffectiveAvatarUrl() {
      return this.getAvatarUrl() == null ? this.getDefaultAvatarUrl() : this.getAvatarUrl();
   }

   public boolean isBot() {
      return false;
   }

   public String getAsMention() {
      return "<@" + this.id + '>';
   }

   @Override
   public boolean equals(Object var1) {
      if (!(var1 instanceof User)) {
         return false;
      } else {
         User var2 = (User)var1;
         return this == var2 || this.id == var2.id;
      }
   }

   @Override
   public int hashCode() {
      return ExtendedLong.hashCode(this.id);
   }

   @Override
   public String toString() {
      return "U:" + this.getName() + '(' + this.id + ')';
   }

   public static enum DefaultAvatar {
      BLURPLE("6debd47ed13483642cf09e832ed0bc1b"),
      GREY("322c936a8c8be1b803cd94861bdfa868"),
      GREEN("dd4dbc0016779df1378e7812eabaa04d"),
      ORANGE("0e291f67c9274a1abdddeb3fd919cbaa"),
      RED("1cbd08c76f8af6dddce02c5138971129");

      public final String text;

       DefaultAvatar(String var3) {
         this.text = var3;
      }

      @Override
      public String toString() {
         return this.text;
      }
   }
}
