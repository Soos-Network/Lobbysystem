package eu.wolfsucht.projects.lobbysystemws.commands;

import eu.wolfsucht.projects.lobbysystemws.LobbySystemWS;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Locale;

public class SetWarpCommand implements CommandExecutor {

    String prefix = ChatColor.RED + "Soos Lobby " + ChatColor.GRAY + "| " + ChatColor.RESET;

    public SetWarpCommand(LobbySystemWS main){
        Bukkit.getPluginCommand("setwarp").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (p.hasPermission("Lobbysystem.SetWarp")){
            if(args.length == 3){
                String name = args[0];
                Material material;
                int slot = 0;
                try {
                    material = Material.valueOf(args[1].toUpperCase(Locale.ROOT));
                }catch (Exception e){
                    p.sendMessage(prefix + ChatColor.YELLOW + args[0].toUpperCase(Locale.ROOT) + ChatColor.RED + " is no accepted Material.");
                    return true;
                }
                try {
                    slot = Integer.parseInt(args[2]);
                }catch (Exception e){
                    p.sendMessage(prefix + ChatColor.RED + "slot has to be a number(INT).");
                    return true;
                }
                LobbySystemWS.getTeleportUtils().save(name, material, slot, p.getLocation());
                p.sendMessage(prefix + ChatColor.GREEN + "Succesfully set warp.");
            }else {
                p.sendMessage(prefix + ChatColor.RED + "Please use: " + ChatColor.YELLOW + "/setwarp <Name> <Material> <Slot>");
            }
        }else {
            p.sendMessage(prefix + "Not enough Permissions.");
        }
        return false;
    }
}
