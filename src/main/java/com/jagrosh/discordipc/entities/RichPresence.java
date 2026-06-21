package com.jagrosh.discordipc.entities;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class RichPresence {
   public final String state;
   public final String details;
   public final long startTimestamp;
   public final long endTimestamp;
   public final String largeImageKey;
   public final String largeImageText;
   public final String smallImageKey;
   public final String smallImageText;
   public final String partyId;
   public final int partySize;
   public final int partyMax;
   public final int partyPrivacy;
   public final String matchSecret;
   public final String joinSecret;
   public final String spectateSecret;
   public final JsonArray buttons;
   public final boolean instance;

   public RichPresence(
      String var1,
      String var2,
      long var3,
      long var5,
      String var7,
      String var8,
      String var9,
      String var10,
      String var11,
      int var12,
      int var13,
      int var14,
      String var15,
      String var16,
      String var17,
      JsonArray var18,
      boolean var19
   ) {
      super();
      this.state = var1;
      this.details = var2;
      this.startTimestamp = var3;
      this.endTimestamp = var5;
      this.largeImageKey = var7;
      this.largeImageText = var8;
      this.smallImageKey = var9;
      this.smallImageText = var10;
      this.partyId = var11;
      this.partySize = var12;
      this.partyMax = var13;
      this.partyPrivacy = var14;
      this.matchSecret = var15;
      this.joinSecret = var16;
      this.spectateSecret = var17;
      this.buttons = var18;
      this.instance = var19;
   }

   public JsonObject toJson() {
      JsonObject var1 = new JsonObject();
      JsonObject var2 = new JsonObject();
      JsonObject var3 = new JsonObject();
      JsonObject var4 = new JsonObject();
      JsonObject var5 = new JsonObject();
      if (this.startTimestamp > 0L) {
         var1.addProperty("start", this.startTimestamp);
         if (this.endTimestamp > this.startTimestamp) {
            var1.addProperty("end", this.endTimestamp);
         }
      }

      if (this.largeImageKey != null && !this.largeImageKey.isEmpty()) {
         var2.addProperty("large_image", this.largeImageKey);
         if (this.largeImageText != null && !this.largeImageText.isEmpty()) {
            var2.addProperty("large_text", this.largeImageText);
         }
      }

      if (this.smallImageKey != null && !this.smallImageKey.isEmpty()) {
         var2.addProperty("small_image", this.smallImageKey);
         if (this.smallImageText != null && !this.smallImageText.isEmpty()) {
            var2.addProperty("small_text", this.smallImageText);
         }
      }

      if (this.partyId != null && !this.partyId.isEmpty() || this.partySize > 0 && this.partyMax > 0 || this.partyPrivacy >= 0) {
         if (this.partyId != null && !this.partyId.isEmpty()) {
            var3.addProperty("id", this.partyId);
         }

         JsonArray var6 = new JsonArray();
         if (this.partySize > 0) {
            var6.add(new JsonPrimitive(this.partySize));
            if (this.partyMax >= this.partySize) {
               var6.add(new JsonPrimitive(this.partyMax));
            }
         }

         var3.add("size", var6);
         if (this.partyPrivacy >= 0) {
            var3.add("privacy", new JsonPrimitive(this.partyPrivacy));
         }
      }

      if (this.joinSecret != null && !this.joinSecret.isEmpty()) {
         var4.addProperty("join", this.joinSecret);
      }

      if (this.spectateSecret != null && !this.spectateSecret.isEmpty()) {
         var4.addProperty("spectate", this.spectateSecret);
      }

      if (this.matchSecret != null && !this.matchSecret.isEmpty()) {
         var4.addProperty("match", this.matchSecret);
      }

      if (this.state != null && !this.state.isEmpty()) {
         var5.addProperty("state", this.state);
      }

      if (this.details != null && !this.details.isEmpty()) {
         var5.addProperty("details", this.details);
      }

      if (var1.has("start")) {
         var5.add("timestamps", var1);
      }

      if (var2.has("large_image")) {
         var5.add("assets", var2);
      }

      if (var3.has("id")) {
         var5.add("party", var3);
      }

      if (var4.has("join") || var4.has("spectate") || var4.has("match")) {
         var5.add("secrets", var4);
      }

      if (this.buttons != null && !this.buttons.isJsonNull() && this.buttons.size() > 0 && this.buttons.size() < 3) {
         var5.add("buttons", this.buttons);
      }

      var5.addProperty("instance", this.instance);
      return var5;
   }

   public String toDecodedJson(String var1) {
      try {
         return new String(this.toJson().toString().getBytes(var1));
      } catch (Exception var3) {
         return this.toJson().toString();
      }
   }

   public static class Builder {
      public String state;
      public String details;
      public long startTimestamp;
      public long endTimestamp;
      public String largeImageKey;
      public String largeImageText;
      public String smallImageKey;
      public String smallImageText;
      public String partyId;
      public int partySize;
      public int partyMax;
      public int partyPrivacy;
      public String matchSecret;
      public String joinSecret;
      public String spectateSecret;
      public JsonArray buttons;
      public boolean instance;

      public Builder() {
         super();
      }

      public RichPresence build() {
         return new RichPresence(
            this.state,
            this.details,
            this.startTimestamp,
            this.endTimestamp,
            this.largeImageKey,
            this.largeImageText,
            this.smallImageKey,
            this.smallImageText,
            this.partyId,
            this.partySize,
            this.partyMax,
            this.partyPrivacy,
            this.matchSecret,
            this.joinSecret,
            this.spectateSecret,
            this.buttons,
            this.instance
         );
      }

      public RichPresence.Builder setState(String var1) {
         this.state = var1;
         return this;
      }

      public RichPresence.Builder setDetails(String var1) {
         this.details = var1;
         return this;
      }

      public RichPresence.Builder setStartTimestamp(long var1) {
         this.startTimestamp = var1;
         return this;
      }

      public RichPresence.Builder setEndTimestamp(long var1) {
         this.endTimestamp = var1;
         return this;
      }

      public RichPresence.Builder setLargeImage(String var1, String var2) {
         this.largeImageKey = var1;
         this.largeImageText = var2;
         return this;
      }

      public RichPresence.Builder setLargeImage(String var1) {
         return this.setLargeImage(var1, null);
      }

      public RichPresence.Builder setSmallImage(String var1, String var2) {
         this.smallImageKey = var1;
         this.smallImageText = var2;
         return this;
      }

      public RichPresence.Builder setSmallImage(String var1) {
         return this.setSmallImage(var1, null);
      }

      public RichPresence.Builder setParty(String var1, int var2, int var3, int var4) {
         this.partyId = var1;
         this.partySize = var2;
         this.partyMax = var3;
         this.partyPrivacy = var4;
         return this;
      }

      public RichPresence.Builder setMatchSecret(String var1) {
         this.matchSecret = var1;
         return this;
      }

      public RichPresence.Builder setJoinSecret(String var1) {
         this.joinSecret = var1;
         return this;
      }

      public RichPresence.Builder setSpectateSecret(String var1) {
         this.spectateSecret = var1;
         return this;
      }

      public RichPresence.Builder setButtons(JsonArray var1) {
         this.buttons = var1;
         return this;
      }

      public RichPresence.Builder setInstance(boolean var1) {
         this.instance = var1;
         return this;
      }
   }
}
