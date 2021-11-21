package eu.wolfsucht.projects.lobbysystemws.listeners;

import eu.wolfsucht.projects.lobbysystemws.api.LobbyAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    LobbyAPI lobbyAPI = new LobbyAPI();

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

        for (Player all : Bukkit.getOnlinePlayers()) {
            lobbyAPI.getScoreboard(all);
        }

    }

}
