package ru.whbex.rpserver;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
public class LoginListener implements Listener {
    private static final long DELAY = 5L;
    @EventHandler(priority = EventPriority.HIGHEST)
    public void on(PlayerJoinEvent event){
        //CraftPlayer cpl = (CraftPlayer) event.getPlayer();
        // channel add workaround (reflection)
        Util.registerChannel(Main.CHANNEL_ID_INCOMING, event.getPlayer());
        Util.registerChannel(Main.CHANNEL_ID_OUTGOUING, event.getPlayer());
        Main.instance().getLogger().info(String.format("Player %s join, channels: %s, running task after %d ticks",
                event.getPlayer().getName(),
                event.getPlayer().getListeningPluginChannels(),
                DELAY
                ));

        Bukkit.getScheduler().runTaskLater(Main.instance(), () -> {
            Main.instance().getLogger().info("Sending serial request to player " + event.getPlayer().getName());
            event.getPlayer().sendPluginMessage(Main.instance(),
                    Main.CHANNEL_ID_OUTGOUING,
                    new byte[0]);
                }, DELAY);

    }
}
