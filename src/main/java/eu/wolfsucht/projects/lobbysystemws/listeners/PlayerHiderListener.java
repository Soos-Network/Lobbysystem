package eu.wolfsucht.projects.lobbysystemws.listeners;

import eu.wolfsucht.projects.lobbysystemws.LobbySystemWS;
import eu.wolfsucht.projects.lobbysystemws.inventorys.Creator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.List;

public class PlayerHiderListener implements Listener {

    private LobbySystemWS main;

    public PlayerHiderListener(LobbySystemWS main){
        this.main = main;
        Bukkit.getPluginManager().registerEvents(this, main);
    }

    public List<Player> hide = new ArrayList<>();

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        if (e.getItem() != null){
            if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» ┃ §cSpieler verstecken")){
                hide.add(e.getPlayer());
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (e.getPlayer() != player){
                        e.getPlayer().hidePlayer(player);
                    }
                }
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.UI_BUTTON_CLICK, 3, 1);
                e.getPlayer().getInventory().setItem(7, Creator.itemcreator(Material.POTION, "§8» ┃ §cSpieler anzeigen", 1, (short)0, null));
                e.getPlayer().updateInventory();
            }else if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» ┃ §cSpieler anzeigen")){
                hide.remove(e.getPlayer());
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (e.getPlayer() != player){
                        e.getPlayer().showPlayer(player);
                    }
                }
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.UI_BUTTON_CLICK, 3, 1);
                e.getPlayer().getInventory().setItem(7, Creator.itemcreator(Material.POTION, "§8» ┃ §cSpieler verstecken", 1, (short)0, null));
                e.getPlayer().updateInventory();
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (hide.contains(player)) {
                if (player != e.getPlayer()){
                player.hidePlayer(e.getPlayer());
                }
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        if (hide.contains(e.getPlayer())){
            hide.remove(e.getPlayer());
        }
    }
}
