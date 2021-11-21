package eu.wolfsucht.projects.lobbysystemws.gadgets;

import eu.wolfsucht.projects.lobbysystemws.LobbySystemWS;
import eu.wolfsucht.projects.lobbysystemws.inventorys.Creator;
import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.FireworkMeta;

public class FireworkGadget implements Listener {
    private LobbySystemWS main;

    public FireworkGadget(LobbySystemWS main){
        this.main = main;
        Bukkit.getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onEnderpearlGadget(PlayerInteractEvent e){
        if (e.getItem() != null && e.getItem().getType() == Material.FIREWORK_ROCKET){
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getAction() == Action.RIGHT_CLICK_AIR) {
                Player p = (Player) e.getPlayer();
                e.setCancelled(true);

                p.getInventory().setItem(4, Creator.itemcreator(Material.FIRE_CHARGE, "§fFeuerwerk", 1, (short)0, null));
                p.updateInventory();

                Location location = p.getLocation();
                if (e.getAction() == Action.RIGHT_CLICK_BLOCK){
                    location = e.getClickedBlock().getRelative(BlockFace.UP).getLocation();
                }

                FireworkMeta fireworkMeta = (FireworkMeta) e.getItem().getItemMeta();
                fireworkMeta.setPower(1);
                fireworkMeta.addEffect(FireworkEffect.builder().withFade(Color.RED).withColor(Color.WHITE).trail(true).build());

                Firework firework = location.getWorld().spawn(location, Firework.class);
                firework.setFireworkMeta(fireworkMeta);

                Bukkit.getScheduler().runTaskLater(main, new Runnable() {
                    @Override
                    public void run() {
                        p.getInventory().setItem(0, Creator.itemcreator(Material.FIREWORK_ROCKET, "§fFeuerwerk", 1, (short)0, null));
                        p.updateInventory();
                    }
                }, 20*5);
            }
        }
    }
}
