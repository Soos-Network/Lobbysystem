package eu.wolfsucht.projects.lobbysystemws.listeners;

import eu.wolfsucht.projects.lobbysystemws.LobbySystemWS;
import eu.wolfsucht.projects.lobbysystemws.inventorys.Creator;
import eu.wolfsucht.projects.lobbysystemws.util.TeleportUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class TeleporterListener implements Listener {

    private LobbySystemWS main;

    public TeleporterListener(LobbySystemWS main){
        this.main = main;
        Bukkit.getPluginManager().registerEvents(this, main);
    }

    public HashMap<String, Location> locationHashMap = new HashMap<>();

    @EventHandler
    public void onClick(PlayerInteractEvent e){
        if (e.getItem() != null){
            if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» ┃ §9Teleporter")){
                Inventory inv = Bukkit.createInventory(null, 36, "§8» ┃ §9Teleporter");
                List<String> list = getWarps();

                for (String s : list){
                    try {
                        String name = s.split(":")[0];
                        Material material = Material.valueOf(s.split(":")[1].toUpperCase(Locale.ROOT));
                        int slot = Integer.parseInt(s.split(":")[2]);
                        Location location = new Location(Bukkit.getWorld(s.split(":")[3]), Double.valueOf(s.split(":")[4]), Double.valueOf(s.split(":")[5]), Double.valueOf(s.split(":")[6]), Float.valueOf(s.split(":")[7]), Float.valueOf(s.split(":")[8]));
                        locationHashMap.put(name, location);
                        inv.setItem(slot, Creator.itemcreator(material, name, 1, (short)0, null));
                    }catch (Exception e1){

                    }
                }

                e.getPlayer().openInventory(inv);
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        if (e.getView().getTitle().equalsIgnoreCase("§8» ┃ §9Teleporter")){
            if (e.getCurrentItem() != null){
                if (e.getCurrentItem().getItemMeta().hasDisplayName()){
                    String displayname = e.getCurrentItem().getItemMeta().getDisplayName();
                    if (locationHashMap.containsKey(displayname)){
                        e.getWhoClicked().closeInventory();
                        e.getWhoClicked().teleport(locationHashMap.get(displayname));
                    }
                }
            }
            e.setCancelled(true);
        }
    }

    public List<String> getWarps(){
        try {
            return TeleportUtils.cfg.getStringList("teleporter");
        }catch (Exception e){

        }
        return new ArrayList<>();
    }
}
