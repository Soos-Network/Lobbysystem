package eu.wolfsucht.projects.lobbysystemws;

import eu.wolfsucht.projects.lobbysystemws.commands.SetWarpCommand;
import eu.wolfsucht.projects.lobbysystemws.gadgets.EnderpearlGadget;
import eu.wolfsucht.projects.lobbysystemws.gadgets.FireworkGadget;
import eu.wolfsucht.projects.lobbysystemws.gadgets.Gadgets;
import eu.wolfsucht.projects.lobbysystemws.listeners.Events;
import eu.wolfsucht.projects.lobbysystemws.listeners.PlayerHiderListener;
import eu.wolfsucht.projects.lobbysystemws.listeners.PlayerJoinListener;
import eu.wolfsucht.projects.lobbysystemws.listeners.TeleporterListener;
import eu.wolfsucht.projects.lobbysystemws.util.TeleportUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class LobbySystemWS extends JavaPlugin {

    public static TeleportUtils teleportUtils;

    @Override
    public void onEnable() {

        loadListeners();
        new SetWarpCommand(this);

        teleportUtils = new TeleportUtils(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void loadListeners() {

        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        new TeleporterListener(this);
        new Events(this);
        new PlayerHiderListener(this);
        new Gadgets(this);
        new EnderpearlGadget(this);
        new FireworkGadget(this);
    }

    public static TeleportUtils getTeleportUtils(){
        return teleportUtils;
    }

}
