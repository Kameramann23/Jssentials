package io.github.jagswag2014.events;

import io.github.jagswag2014.Jssentials;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

public class QUITEvent extends ZEventParent {

    public QUITEvent(Jssentials plugin) {
        super(plugin);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {

    }
}
