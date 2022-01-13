package eu.wolfsucht.projects.lobbysystemws.gadgets;

import eu.wolfsucht.projects.lobbysystemws.LobbySystemWS;
import eu.wolfsucht.projects.lobbysystemws.inventorys.Creator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class Gadgets implements Listener {
    private LobbySystemWS main;
    private Inventory inv;

    String prefix = ChatColor.RED + "Soos Lobby " + ChatColor.GRAY + "| " + ChatColor.RESET;

    public Gadgets(LobbySystemWS main){
        this.main = main;
        Bukkit.getPluginManager().registerEvents(this, main);

        inv = Bukkit.createInventory(null, 9, "§8» ┃ §5Gadgets");
        inv.setItem(0, Creator.itemcreator(Material.ENDER_PEARL, "§fEnderperle", 1, (short)0, null));
        inv.setItem(1, Creator.itemcreator(Material.FIREWORK_ROCKET, "§fFeuerwerk", 1, (short)0, null));
        inv.setItem(2, Creator.itemcreator(Material.FISHING_ROD, "§fEnterhacken", 1, (short)0, null));
        inv.setItem(8, Creator.itemcreator(Material.BARRIER, "§fKein Gadget ausgewählt", 1, (short)0, null));
    }

    @EventHandler
    public void onGadgets(PlayerInteractEvent e){
        if (e.getItem() != null && e.getItem().getType() == Material.CAKE){
            e.getPlayer().openInventory(inv);
            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_CHEST_OPEN, 3, 1);
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        if (e.getClickedInventory() != null && e.getView().getTitle().equalsIgnoreCase("§8» ┃ §5Gadgets")){
            Player p = (Player) e.getWhoClicked();
            if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR){
                Material material = e.getCurrentItem().getType();
                if (material == Material.ENDER_PEARL){
                    p.getInventory().setItem(4, Creator.itemcreator(Material.ENDER_PEARL, "§fEnderperle", 1, (short)0, null));
                }else if (material == Material.FIREWORK_ROCKET){
                    p.getInventory().setItem(4, Creator.itemcreator(Material.FIREWORK_ROCKET, "§fFeuerwerk", 1, (short)0, null));
                }else if (material == Material.FISHING_ROD){
                    p.getInventory().setItem(4, Creator.itemcreator(Material.FISHING_ROD, "§fEnterhacken", 1, (short)0, null));
                }else if (material == Material.BARRIER){
                    p.getInventory().setItem(4, Creator.itemcreator(Material.BARRIER, "§fKein Gadget ausgewählt", 1, (short)0, null));
                }else {
                    p.sendMessage(prefix + ChatColor.RED + "Gadget coming soon");
                }
                p.closeInventory();
                p.updateInventory();
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3, 1);
            }
        }
    }
}
