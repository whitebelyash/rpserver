package ru.whbex.rpserver;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommandYamlParser;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SerialCommand extends BukkitCommand {
    public static final String NAME = "serial",
                                DESCRIPTION = "Get player serial",
                                USAGE = "Usage: /" + NAME + " <player>";
    public static final String[] ALIAS = {"getserial", "playerserial"};
    public SerialCommand(){
        super(NAME, DESCRIPTION, USAGE, Arrays.asList(ALIAS));
    }
    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        boolean other = !(sender instanceof Player);
        if(args.length > 0)
            other = true;
        if(other){
            if(args.length == 0){
                sender.sendMessage(USAGE);
                return true;
            }
            String username = args[0];
            Player player;
            if((player = Bukkit.getPlayer(username)) == null){
                sender.sendMessage("User " + username + " not found!");
                return true;
            }
            return displaySerial(sender, player);
            }
        else
            return displaySerial(sender, (Player) sender);
        }
    private boolean displaySerial(CommandSender viewer, Player target){
        boolean self = viewer.equals(target);
        if(!Main.instance().hasSavedSerial(target)){
            String err = self ? "You don't have any saved serials" : "Target player " + target.getName() + "doesn't have any saved serials";
            viewer.sendMessage(err);
            return true;
        }
        String display = self ? "Your serial is " + Main.instance().getSerial(target) :
                                "Target " + target.getName() + " serial is " + Main.instance().getSerial(target);
        viewer.sendMessage(display);
        return true;
    }

}
