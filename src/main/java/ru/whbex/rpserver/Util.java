package ru.whbex.rpserver;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class Util {
    public static void registerBukkitCommand(BukkitCommand cmd){
        try {
            Field cmdMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            cmdMapField.setAccessible(true);
            CommandMap cmdMap = (CommandMap) cmdMapField.get(Bukkit.getServer());
            cmdMap.register(cmd.getName(), cmd);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            Main.instance().getLogger().severe("Couldn't register command " + cmd.getName() + ": "  + e.getLocalizedMessage());
            e.printStackTrace();
        }


    }
    @SuppressWarnings("unchecked")
    public static void registerChannel(String channel, Player player){
        try {
            Field channelField = player.getClass().getDeclaredField("channels");
            channelField.setAccessible(true);
            Set<String> channelSet = (Set<String>) channelField.get(player);
            if(channelSet.contains(channel)){
                Main.instance().getLogger().warning("Channel " + channel + " is already registered!");
                return;
            }
            channelSet.add(channel);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            Main.instance().getLogger().severe("Couldn't register channel for player " + player.getName());
            e.printStackTrace();
        }

    }
}
