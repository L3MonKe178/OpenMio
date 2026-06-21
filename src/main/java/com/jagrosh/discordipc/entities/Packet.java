package com.jagrosh.discordipc.entities;

import com.google.gson.JsonObject;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class Packet {
   public final Packet.OpCode op;
   public final JsonObject data;
   public final String encoding;

   public Packet(Packet.OpCode var1, JsonObject var2, String var3) {
      super();
      this.op = var1;
      this.data = var2;
      this.encoding = var3;
   }

   @Deprecated
   public Packet(Packet.OpCode var1, JsonObject var2) {
      this(var1, var2, "UTF-8");
   }

   public byte[] toBytes() {
      String var1 = this.data.toString();

      byte[] var2;
      try {
         var2 = var1.getBytes(this.encoding);
      } catch (UnsupportedEncodingException var4) {
         var2 = var1.getBytes();
      }

      ByteBuffer var3 = ByteBuffer.allocate(var2.length + 8);
      var3.putInt(Integer.reverseBytes(this.op.ordinal()));
      var3.putInt(Integer.reverseBytes(var2.length));
      var3.put(var2);
      return var3.array();
   }

   public Packet.OpCode getOp() {
      return this.op;
   }

   public JsonObject getJson() {
      return this.data;
   }

   @Override
   public String toString() {
      return "Pkt:" + this.getOp() + this.getJson().toString();
   }

   public String toDecodedString() {
      try {
         return "Pkt:" + this.getOp() + new String(this.getJson().toString().getBytes(this.encoding));
      } catch (UnsupportedEncodingException var2) {
         return "Pkt:" + this.getOp() + this.getJson().toString();
      }
   }

   public static enum OpCode {
      HANDSHAKE,
      FRAME,
      CLOSE,
      PING,
      PONG;
   }
}
