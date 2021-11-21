package eu.wolfsucht.projects.lobbysystemws.inventorys;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.Inventory;

public class TeleporterInventory {

    public void openInventory(Player player) {

        Inventory inventory = Bukkit.createInventory(null, 9*6, "§9§lTeleporter");

        inventory.setItem(0, Creator.itemcreator(Material.IRON_SWORD, "§fPVP", 1, (short)0, null));
        inventory.setItem(3, Creator.itemcreator(Material.COMPASS, "§fSpawn", 1, (short)0, null));
        inventory.setItem(6, Creator.itemcreator(Material.GRASS_BLOCK, "§fCitybuild", 1, (short)0, null));

        player.openInventory(inventory);

    }
}
