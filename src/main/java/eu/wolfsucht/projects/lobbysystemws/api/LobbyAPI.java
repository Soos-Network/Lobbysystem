package eu.wolfsucht.projects.lobbysystemws.api;

import eu.wolfsucht.projects.coinssystem.CoinsSystem;
import eu.wolfsucht.projects.lobbysystemws.inventorys.Creator;
import net.luckperms.api.LuckPermsProvider;
import onlinetime.utils.OnlineTimeAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.ScoreboardManager;

public class LobbyAPI {

    public void getItems(Player player) {

        player.getInventory().clear();
        player.getInventory().setArmorContents(null);

        player.getInventory().setItem(0, Creator.itemcreator(Material.COMPASS, "§8» ┃ §9Teleporter", 1, (short)0, null));
        player.getInventory().setItem(2, Creator.itemcreator(Material.GOLDEN_AXE, "§8» ┃ §6PvP", 1, (short)0, null));
        player.getInventory().setItem(4, Creator.itemcreator(Material.BARRIER, "§fKein Gadget ausgewählt", 1, (short)0, null));
        player.getInventory().setItem(6, Creator.itemcreator(Material.CAKE, "§8» ┃ §5Gadgets", 1, (short)0, null));
        player.getInventory().setItem(8, Creator.itemcreator(Material.POTION, "§8» ┃ §cSpieler verstecken", 1, (short)0, null));

    }

    public void getScoreboard(Player player) {

        ScoreboardManager sm = Bukkit.getScoreboardManager();
        org.bukkit.scoreboard.Scoreboard s = sm.getNewScoreboard();
        s.registerNewObjective("board", "dummy");
        Objective o = s.getObjective("board");
        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        o.setDisplayName("§7 §8- §c§lWOLFSUCHT §8- §7");
        o.getScore("§9§8§9§7§e§7").setScore(18);
        o.getScore("§7 §8┃ §7Rang").setScore(17);
        if (LuckPermsProvider.get().getUserManager().getUser(player.getUniqueId()).getPrimaryGroup().equalsIgnoreCase("default")) {
            o.getScore("§7  §8» §9Spieler").setScore(16);
        } else if (LuckPermsProvider.get().getUserManager().getUser(player.getUniqueId()).getPrimaryGroup().equalsIgnoreCase("Prime")) {
            o.getScore("§7  §8» §6§lPRIME").setScore(16);
        } else if (LuckPermsProvider.get().getUserManager().getUser(player.getUniqueId()).getPrimaryGroup().equalsIgnoreCase("Hydra")) {
            o.getScore("§7  §8» §b§lHYDRA").setScore(16);
        } else if (LuckPermsProvider.get().getUserManager().getUser(player.getUniqueId()).getPrimaryGroup().equalsIgnoreCase("Wolf")) {
            o.getScore("§7  §8» §c§lWOLF").setScore(16);
        } else if (LuckPermsProvider.get().getUserManager().getUser(player.getUniqueId()).getPrimaryGroup().equalsIgnoreCase("Vip")) {
            o.getScore("§7  §8» §5§lVIP").setScore(16);
        } else if (LuckPermsProvider.get().getUserManager().getUser(player.getUniqueId()).getPrimaryGroup().equalsIgnoreCase("test-builder")) {
            o.getScore("§7  §8» §2T-Builder").setScore(16);
        } else if (LuckPermsProvider.get().getUserManager().getUser(player.getUniqueId()).getPrimaryGroup().equalsIgnoreCase("test-supporter")) {
            o.getScore("§7  §8» §9T-Supporter").setScore(16);
        } else if (LuckPermsProvider.get().getUserManager().getUser(player.getUniqueId()).getPrimaryGroup().equalsIgnoreCase("test-moderator")) {
            o.getScore("§7  §8» §cT-Moderator").setScore(16);
        } else if (LuckPermsProvider.get().getUserManager().getUser(player.getUniqueId()).getPrimaryGroup().equalsIgnoreCase("test-developer")) {
            o.getScore("§7  §8» §3T-Developer").setScore(16);
        } else if (LuckPermsProvider.get().getUserManager().getUser(player.getUniqueId()).getPrimaryGroup().equalsIgnoreCase("builder")) {
            o.getScore("§7  §8» §2Builder").setScore(16);
        } else if (LuckPermsProvider.get().getUserManager().getUser(player.getUniqueId()).getPrimaryGroup().equalsIgnoreCase("Supporter")) {
            o.getScore("§7  §8» §9Supporter").setScore(16);
        } else if (LuckPermsProvider.get().getUserManager().getUser(player.getUniqueId()).getPrimaryGroup().equalsIgnoreCase("Moderator")) {
            o.getScore("§7  §8» §cModerator").setScore(16);
        } else if (LuckPermsProvider.get().getUserManager().getUser(player.getUniqueId()).getPrimaryGroup().equalsIgnoreCase("developer")) {
            o.getScore("§7  §8» §3Developer").setScore(16);
        } else if (LuckPermsProvider.get().getUserManager().getUser(player.getUniqueId()).getPrimaryGroup().equalsIgnoreCase("content")) {
            o.getScore("§7  §8» §cContent").setScore(16);
        } else if (LuckPermsProvider.get().getUserManager().getUser(player.getUniqueId()).getPrimaryGroup().equalsIgnoreCase("leitung")) {
            o.getScore("§7  §8» §4Leitung").setScore(16);
        } else if (LuckPermsProvider.get().getUserManager().getUser(player.getUniqueId()).getPrimaryGroup().equalsIgnoreCase("admin")) {
            o.getScore("§7  §8» §4Admin").setScore(16);
        }
        o.getScore("§7§8§9§a§e§f").setScore(15);
        o.getScore("§7 §8┃ §7Coins").setScore(11);
        o.getScore("§7  §8» §6" + CoinsSystem.getApi().getCoins(player)).setScore(10);
        o.getScore("§8§9").setScore(9);
        o.getScore("§7 §8┃ §7Spieler").setScore(8);
        o.getScore("§7  §8» §c"  + Bukkit.getServer().getOnlinePlayers().size()).setScore(7);
        o.getScore("§8§9§f§m").setScore(6);
        o.getScore("§7 §8┃ §7Spielzeit").setScore(5);
        o.getScore("§7  §8» §3" + OnlineTimeAPI.getZeit(player.getUniqueId().toString()) + "§3h").setScore(4);
        o.getScore("§8§9§f§k§d").setScore(3);
        o.getScore("§7 §8┃ §7TeamSpeak").setScore(2);
        o.getScore("§7  §8» §fts.wolfsucht.eu").setScore(1);

        player.setScoreboard(s);

    }

}
