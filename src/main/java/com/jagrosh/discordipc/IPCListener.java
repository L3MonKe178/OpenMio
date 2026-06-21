package com.jagrosh.discordipc;

import com.google.gson.JsonObject;
import com.jagrosh.discordipc.entities.Packet;
import com.jagrosh.discordipc.entities.User;

public interface IPCListener {
   void onPacketSent(IPCClient var1, Packet var2);

   void onPacketReceived(IPCClient var1, Packet var2);

   void onActivityJoin(IPCClient var1, String var2);

   void onActivitySpectate(IPCClient var1, String var2);

   void onActivityJoinRequest(IPCClient var1, String var2, User var3);

   void onReady(IPCClient var1);

   void onClose(IPCClient var1, JsonObject var2);

   void onDisconnect(IPCClient var1, Throwable var2);
}
