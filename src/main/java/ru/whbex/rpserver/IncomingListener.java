package ru.whbex.rpserver;

import org.bukkit.entity.Player;
import org.bukkit.entity.memory.MemoryKey;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class IncomingListener implements PluginMessageListener {
    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        String msg = new String(message);
        Main.instance().getLogger().info(String.format("Received serial %s from player %s (channel: %s)",
                msg,
                player.getName(),
                channel));
        Main.instance().saveSerial(player, msg);
    }
}
