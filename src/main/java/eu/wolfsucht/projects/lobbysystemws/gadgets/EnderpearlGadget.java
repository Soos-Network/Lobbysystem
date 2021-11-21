package eu.wolfsucht.projects.lobbysystemws.gadgets;

import eu.wolfsucht.projects.lobbysystemws.LobbySystemWS;
import eu.wolfsucht.projects.lobbysystemws.inventorys.Creator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;

import java.util.HashMap;

public class EnderpearlGadget implements Listener {

    private LobbySystemWS main;
    private HashMap<Player, EnderPearl> enderpearls = new HashMap<>();

    public EnderpearlGadget(LobbySystemWS main){
        this.main = main;
        Bukkit.getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onEnderpearlGadget(PlayerInteractEvent e){
        if (e.getItem() != null && e.getItem().getType() == Material.ENDER_PEARL){
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getAction() == Action.RIGHT_CLICK_AIR){
                Player p = (Player) e.getPlayer();
                e.setCancelled(true);

                p.getInventory().setItem(4, Creator.itemcreator(Material.FIRE_CHARGE, "§fEnderperle", 1, (short)0, null));
                p.updateInventory();

                EnderPearl enderPearl = p.launchProjectile(EnderPearl.class);
                enderPearl.setPassenger(p);
                enderpearls.put(p, enderPearl);

                Bukkit.getScheduler().runTaskLater(main, new Runnable() {
                    @Override
                    public void run() {
                        p.getInventory().setItem(0, Creator.itemcreator(Material.ENDER_PEARL, "§fEnderperle", 1, (short)0, null));
                        p.updateInventory();
                    }
                }, 20*5);
            }
        }
    }

    public void onVehicleLeave(VehicleExitEvent e){
        if (e.getExited() instanceof Player){
            if (enderpearls.containsKey(e.getExited())){
                enderpearls.get(e.getExited()).remove();
            }
        }
    }
}
