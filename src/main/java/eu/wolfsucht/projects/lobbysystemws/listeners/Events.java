package eu.wolfsucht.projects.lobbysystemws.listeners;

import eu.wolfsucht.projects.lobbysystemws.LobbySystemWS;
import eu.wolfsucht.projects.lobbysystemws.api.LobbyAPI;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class Events implements Listener {
    private LobbySystemWS main;

    public Events(LobbySystemWS main){
        this.main = main;
        Bukkit.getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onFood(FoodLevelChangeEvent e){
        e.setCancelled(true);
        e.setFoodLevel(20);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e){
        if (e.getEntity() instanceof Player){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent e){
        if (e.getWhoClicked().getGameMode() != GameMode.CREATIVE){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void  onBlockPlace(BlockPlaceEvent e){
        if (e.getPlayer().getGameMode() != GameMode.CREATIVE){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void  onBlockBreak(BlockBreakEvent e){
        if (e.getPlayer().getGameMode() != GameMode.CREATIVE){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onGamemodeChange(PlayerGameModeChangeEvent e){
        e.getPlayer().getInventory().clear();
        e.getPlayer().getInventory().setArmorContents(null);
        e.getPlayer().updateInventory();
        if (e.getNewGameMode() != GameMode.CREATIVE){
            new LobbyAPI().getItems(e.getPlayer());
        }
    }
}
