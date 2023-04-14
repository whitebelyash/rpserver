package ru.whbex.rpserver;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class Main extends JavaPlugin {
    public static final String CHANNEL_ID_OUTGOUING = "protect:get_serial";
    public static final String CHANNEL_ID_INCOMING = "protect:serial";
    private static Main instance;
    private Map<String, String> serialMap;

    @Override
    public void onEnable() {
        instance = this;
        serialMap = new HashMap<>();
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, CHANNEL_ID_OUTGOUING);
        Bukkit.getMessenger().registerIncomingPluginChannel(this, CHANNEL_ID_INCOMING, new IncomingListener());
        Bukkit.getPluginManager().registerEvents(new LoginListener(), this);
        Util.registerBukkitCommand(this, new SerialCommand());
        getLogger().info("Startup done");


    }

    @Override
    public void onDisable() {
        Bukkit.getMessenger().unregisterOutgoingPluginChannel(this);
        Bukkit.getMessenger().unregisterIncomingPluginChannel(this);

    }
    public static Main instance(){
        return instance;
    }
    public void saveSerial(Player player, String serial){
        serialMap.put(player.getUniqueId().toString(), serial);
    }
    public boolean hasSavedSerial(Player player){
        return serialMap.containsKey(player.getUniqueId().toString());
    }
    public String getSerial(Player player){
        return serialMap.get(player.getUniqueId().toString());
    }
    private static void registerBukkitCommand(String name, CommandExecutor executor){
        // NMS

    }
}
